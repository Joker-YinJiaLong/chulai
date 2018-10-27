package com.chulai.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "activity")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class Activity {
    @Version
    @JsonIgnore
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ActivityGenerator")
    @TableGenerator(name = "ActivityGenerator", table = "pk_generator",
            pkColumnName = "gen_name",
            valueColumnName = "gen_value",
            pkColumnValue = "activity_pk",
            initialValue = 0,
            allocationSize = 1)
    private Long id;

    //开始时间
    private Date startTime;

    //结束时间
    private Date endTime;

    //报名截止时间
    private Date lastSignUp;

    //地址
    private String address;

    //费用设置
    private Integer costType;

    //费用
    private Double cost;

    //名额上线设置
    private Integer MaxNumber;

    //名额下线设置
    private Integer minNumber;

    //报名设置
    private Integer signUpContent;

    //活动介绍
    private String introduction;

    //发布方式
    private Integer publishType;

    //主办方
    private String organizer;

    //联系人
    private String contacts;

    //联系电话
    private String contactTelephone;

}
