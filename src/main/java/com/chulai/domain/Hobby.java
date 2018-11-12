package com.chulai.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 兴趣爱好实体类
 */

@Entity
@Table(name = "hobby")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
public class Hobby {

    @Version
    @JsonIgnore
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "HobbyGenerator")
    @TableGenerator(name = "HobbyGenerator", table = "pk_generator",
            pkColumnName = "gen_name",
            valueColumnName = "gen_value",
            pkColumnValue = "hobby_pk",
            initialValue = 0,
            allocationSize = 1)
    private Long id;

    private String sport;
    private String city;
    private Date time;
    private Boolean isPush;
}
