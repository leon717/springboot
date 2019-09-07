package com.leo.boot.web.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("参数集")
@Getter
@Setter
public class ParamVO {

    @ApiModelProperty("id")
    @NotEmpty(message = "id不能为空")
    private String id;

    @ApiModelProperty("name")
    @NotEmpty(message = "name不能为空")
    private String name;

    @ApiModelProperty("email")
    @Email(message = "email格式错误")
    private String email;

}
