package com.leo.boot.jpa.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import com.leo.boot.jpa.enumeration.Gender;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "t_user")
public class User extends BaseEntity<String> {
    private String name;
    private String nick;
    private Double account;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Transient
    private String temp;
    
    @Version
    private Integer version;

    private Date time;
}
