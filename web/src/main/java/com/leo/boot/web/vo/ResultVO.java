package com.leo.boot.web.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.domain.Page;

import java.util.Collection;

@ApiModel("结果集")
@JsonInclude(Include.NON_NULL)
@Accessors(chain = true)
@Data
public class ResultVO<T> {

    @ApiModelProperty("代码")
    private String code;

    @ApiModelProperty("信息")
    private String msg;

    @ApiModelProperty("数据")
    private T data;

    @ApiModelProperty("分页")
    private Meta meta;

    public ResultVO(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultVO<T> setPage(Page<?> page) {
        this.data = (T) page.getContent();
        this.meta = new Meta().setPage(page.getNumber()).setSize(page.getSize()).setTotal(page.getTotalElements());
        return this;
    }

    public ResultVO<T> setData(T data) {
        this.data = data;
        if (Collection.class.isAssignableFrom(data.getClass())) {
            this.meta = new Meta().setTotal(((Collection) data).size());
        }
        return this;
    }

    public static <T> ResultVO<T> success() {
        return new ResultVO<>("200", "OK");
    }

    public static <T> ResultVO<T> error() {
        return new ResultVO<>("500", "Internal Server Error");
    }

    @Data
    @Accessors(chain = true)
    private class Meta {
        private int page;
        private int size;
        private long total;
    }

}
