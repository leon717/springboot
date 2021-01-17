package com.leo.boot.jpa.domain.projection;

import com.leo.boot.jpa.enumeration.Gender;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@Getter
@Setter
public class UserVO {
    protected String id;
    protected String createdBy;
    protected Date creationDate;
    protected String lastModifiedBy;
    protected Date lastModifiedDate;

    private String name;
    private String nick;
    private Double account;
    private Gender gender;
    private String temp;
    private Integer version;
    private Date time;
}
