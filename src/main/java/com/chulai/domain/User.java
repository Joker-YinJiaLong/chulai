package com.chulai.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class User {
    @Version
    @JsonIgnore
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "UserGenerator")
    @TableGenerator(name = "UserGenerator", table = "pk_generator",
            pkColumnName = "gen_name",
            valueColumnName = "gen_value",
            pkColumnValue = "user_pk",
            initialValue = 0,
            allocationSize = 1)
    private Long id;

    //用户名
    private String username;

    //职业
    private String profession;

    //昵称
    private String nickname;

    //手机号
    private String mobile;

    //个性签名
    private String signature;

    //家乡
    private String hometown;

    //现住地址
    private String address;

    //是否结婚
    private Integer marriage;

    //爱好
    private List<Dictionary> hobbies;

    //邮箱
    private String email;

    //城市
    private String city;

    //是否推送
    private Boolean push;

    //头像
    private String headerPhoto;
}
