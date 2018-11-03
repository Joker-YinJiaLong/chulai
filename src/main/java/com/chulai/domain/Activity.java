package com.chulai.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    //标题
    private String title;

    //活动类别
    private Long activityType;

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

    //发布方式 1：公开；2：办公开；3：AA；4：面报名
    private Integer publishType;

    //主办方
    private String organizer;

    //联系人
    private String contacts;

    //联系电话
    private String contactTelephone;

//    //活动图片
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "activity_image", joinColumns = {
//            @JoinColumn(name = "activity_id", referencedColumnName = "id")}, inverseJoinColumns = {
//            @JoinColumn(name = "image")})
//    private List<String> imageList;

    //标签
    private String labels;

    //报名设置
    @OneToMany(mappedBy = "activity", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RegisterField> registerFields;

//    //创建者
//    private User creator;

    private Long creatorId;

    //是否为模板
    private Boolean template;

    //创建时间
    private Date createTime;
}
