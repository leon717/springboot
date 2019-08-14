package com.leo.boot.web.vo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel("结果集")
@Getter
@Setter
@NoArgsConstructor
public class ResultVO<T> {

    @ApiModelProperty("代码")
    private String code;

    @ApiModelProperty("信息")
    private String msg;

    @ApiModelProperty("数据")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<T> data;

    @ApiModelProperty("分页")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Meta meta;

    public ResultVO(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultVO<T> addData(T data) {
        if (this.data == null) {
            this.data = new ArrayList<T>();
        }
        this.data.add(data);
        return this;
    }

    public ResultVO<T> setPage(Page<T> page) {
        this.data = page.getContent();
        this.meta = new Meta(page.getNumber(), page.getSize(), page.getTotalElements());
        return this;
    }

    public static <T> ResultVO<T> success() {
        return new ResultVO<>("200", "OK");
    }

    public static <T> ResultVO<T> error() {
        return new ResultVO<>("500", "Internal Server Error");
    }

    @AllArgsConstructor
    @Getter
    private class Meta {
        private int page;
        private int size;
        private long total;
    }

}
