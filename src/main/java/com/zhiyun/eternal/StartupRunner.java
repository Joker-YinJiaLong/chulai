package com.zhiyun.eternal;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.dynamic.DynamicGecco;
import com.geccocrawler.gecco.request.HttpGetRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Run some specific code once the SpringApplication has started.
 */
@Component
public class StartupRunner implements ApplicationRunner {

    private static final Logger LOG = LoggerFactory.getLogger(StartupRunner.class);

    @Override
    public void run(ApplicationArguments args) {
        DynamicGecco.html().gecco("https://bytecoin.org/blog","consolePipeline","columnPipeline")
                .stringField("article").csspath(".content-list").text().build()
                .listField("articleList",
                        DynamicGecco.html()
                                .stringField("url").csspath("a").href().build()
                                .stringField("title").csspath("a").text().build()
                                .register()).csspath(".article").build()
                .register();

        HttpGetRequest start = new HttpGetRequest("https://bytecoin.org/blog");
        start.setCharset("utf-8");
        GeccoEngine ge=GeccoEngine.create()
                .classpath("com.jgz.module.news")
                .start(start)
                .interval(1000).loop(false);
        ge.start();
    }
}