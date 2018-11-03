package com.chulai;

import com.chulai.interceptor.AuthorizationInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import io.swagger.annotations.ApiOperation;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * News Application.
 */
@SpringBootApplication
@EnableJpaAuditing
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ServletComponentScan
public class ChuLaiApplication {

    /**
     * Main method, This is just a standard method that follows
     * the Java convention for an application entry point.
     *
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(ChuLaiApplication.class,args);
    }

    /**
     * Web security configurer.
     */
    @Order(SecurityProperties.DEFAULT_FILTER_ORDER)
    @Configuration
    protected static class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        /**
         * Override the access rules without changing any other auto-configured features.
         *
         * <p>The basic features in a web application are:
         * HTTP Basic security for all other endpoints.
         * Security events published to Spring’s ApplicationEventPublisher (successful and unsuccessful authentication and access denied).
         * Common low-level features (HSTS, XSS, CSRF, caching) provided by Spring Security are on by default.
         */
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/**").authorizeRequests()
                    .antMatchers("/", "/favicon.ico", "/css/**", "/js/**", "/images/**", "/fonts/**", "/webjars/**", "/api/**", "/websocket/**", "/sockjs/**", "/callback/**").permitAll()
                    .antMatchers("/swagger-ui.html", "/swagger-resources/**", "/v2/api-docs", "/configuration/ui", "/configuration/security").permitAll()
                    .antMatchers("/manager/**").hasRole("ADMIN")
                    .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
                    .anyRequest().authenticated()
                    .and().formLogin().loginPage("/login").successHandler(authenticationSuccessHandler())
                    .and().logout().logoutSuccessUrl("/").permitAll().and().csrf().disable()
                    .sessionManagement().invalidSessionUrl("/").maximumSessions(1).expiredUrl("/login");
        }

        /**
         * Authentication success handler.
         */
        private SavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler() {
            return new SavedRequestAwareAuthenticationSuccessHandler() {
                @Override
                public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                    super.onAuthenticationSuccess(request, response, authentication);
                }
            };
        }
    }

    /**
     * Repository rest configurer.
     */
    @Configuration
    public class RepositoryRestConfig extends RepositoryRestConfigurerAdapter {

        @Override
        public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
            config.setRepositoryDetectionStrategy(RepositoryDetectionStrategy.RepositoryDetectionStrategies.ANNOTATED)
                    .exposeIdsFor(ChuLaiApplication.class).getCorsRegistry().addMapping("/api/**");
        }

        @Override
        public void configureJacksonObjectMapper(ObjectMapper objectMapper) {
            super.configureJacksonObjectMapper(objectMapper);
            objectMapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        }
    }

    /**
     * Web mvc configurer.
     *
     * @return WebMvcConfig
     */
    @Bean
    public WebMvcConfig webMvcConfig() {
        return new WebMvcConfig();
    }

    @Configuration
    protected static class WebMvcConfig implements WebMvcConfigurer {

        /**
         * For the interceptor to take effect, need to add it to the application’s interceptor registry.
         * Get a handle and override Spring Boot Web’s addInterceptor configuration method.
         */
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
//            registry.addInterceptor(new AuthorizationInterceptor()).addPathPatterns("/**").excludePathPatterns("/api/users/login","/swagger-ui.html");
        }

        /**
         * Define global CORS configuration as well.
         * This is similar to using a Filter based solution, but can be declared within Spring MVC
         * and combined with fine-grained @CrossOrigin configuration.
         * By default all origins and GET, HEAD and POST methods are allowed.
         */
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**").allowedOrigins("*").allowedHeaders("*").allowedMethods("*");
        }
    }

    /**
     * Swagger is the framework of API developer tools for the OpenAPI
     * Specification(OAS).j The specification creates the RESTful contract for
     * API, detailing all of its resources and operations in a human and machine
     * readable format for easy development, discovery, and integration.
     * <p>
     * After running visit /swagger-ui.html. When not using localhost, visit
     * /swagger-ui.html?validatorUrl=
     */
    @Configuration
    @EnableSwagger2
    public class Swagger {

        @Bean
        public Docket createApi() {
            return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                    .apis(RequestHandlerSelectors.basePackage("com.chulai.controller"))
                    .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).paths(PathSelectors.any())
                    .build();
        }

        private ApiInfo apiInfo() {
            return new ApiInfoBuilder().title("TISOLL chulai Specification").description("")
                    .version("0.0.1-SNAPSHOT").build();
        }
    }

    /**
     * Embedded tomcat configurer.
     *
     * @return TomcatServletWebServerFactory
     */
    @Bean
    public TomcatServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addConnectorCustomizers(new MyTomcatConnectorCustomizer());
        return tomcat;
    }

    class MyTomcatConnectorCustomizer implements TomcatConnectorCustomizer {
        public void customize(Connector connector) {
            Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
            protocol.setMaxThreads(500);
            protocol.setMinSpareThreads(20);
            protocol.setAcceptCount(400);
            protocol.setConnectionTimeout(20000);
        }
    }
}