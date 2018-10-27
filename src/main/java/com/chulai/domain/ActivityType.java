package com.chulai.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "activityType")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class ActivityType {
    @Version
    @JsonIgnore
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ActivityTypeGenerator")
    @TableGenerator(name = "ActivityTypeGenerator", table = "pk_generator",
            pkColumnName = "gen_name",
            valueColumnName = "gen_value",
            pkColumnValue = "activity_type_pk",
            initialValue = 0,
            allocationSize = 1)
    private Long id;

    /**
     * 类型标题
     */
    private String title;

    /**
     * 图片
     */
    private String  logo;
}
