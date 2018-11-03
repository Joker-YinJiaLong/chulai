package com.chulai.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * 主办方管理
 * @Data注解将自动生成getter和setter方法
 */

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Table(name="host")
public class Host {

    @Version //版本注解,用来处理数据安全
    @JsonIgnore //当转换成json返回给前端的时候忽略这个字段
    private Long version;

    @GeneratedValue(strategy = GenerationType.TABLE,generator = "HostGenerator")
    @TableGenerator(name = "HostGenerator", table = "pk_generator",
            pkColumnName = "gen_name",
            valueColumnName = "gen_value",
            pkColumnValue = "host_pk",
            initialValue = 0,
            allocationSize = 1)
    //用户ID
    private Long userId;
    //联系人
    private String contact;
    //联系电话
    private String telephone;


}
