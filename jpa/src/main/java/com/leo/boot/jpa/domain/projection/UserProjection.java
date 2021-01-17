package com.leo.boot.jpa.domain.projection;

import com.leo.boot.jpa.enumeration.Gender;

import java.util.Date;

public interface UserProjection {

    String getId();

    String getCreatedBy();

    Date getCreationDate();

    String getLastModifiedBy();

    Date getLastModifiedDate();

    String getName();

    String getNick();

    Double getAccount();

    Gender getGender();

    String getTemp();

    Integer getVersion();

    Date getTime();
}
