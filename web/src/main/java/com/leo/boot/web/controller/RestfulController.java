package com.leo.boot.web.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leo.boot.web.vo.ResultVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Restful测试")
@RestController
@RequestMapping(value = "/rest", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RestfulController {

    @ApiOperation("查询")
    @GetMapping("/info/{param}")
    public ResultVO<String> get(@ApiParam(value = "path参数") @PathVariable String param) {
        return ResultVO.<String>success().addData(param);
    }

    @ApiOperation("设置")
    @PostMapping("/info")
    public ResultVO<String> post(@ApiParam(value = "url参数") @RequestParam String param,
        @ApiParam(value = "body参数") @RequestBody String body) {
        return ResultVO.<String>success().addData(param).addData(body);
    }

    @ApiOperation("分页")
    @GetMapping("/infos")
    @ApiImplicitParams({@ApiImplicitParam(name = "page", type = "int", value = "当前页数", paramType = "query"),
        @ApiImplicitParam(name = "size", type = "int", value = "每页记录数", paramType = "query"),
        @ApiImplicitParam(name = "sort", value = "排序相关信息", paramType = "query", allowMultiple = true)})
    public ResultVO<String> page(@ApiParam(value = "参数") @RequestParam String param,
        @PageableDefault(value = 20, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        List<String> content = Arrays.asList("a", "b", "c");
        return ResultVO.<String>success().setPage(new PageImpl<>(content, pageable, 100));
    }
    
}
