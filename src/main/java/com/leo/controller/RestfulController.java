package com.leo.controller;

import java.util.Arrays;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leo.vo.ResultVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/", produces = "application/json")
@Api(tags="Restful测试")
public class RestfulController {

	@GetMapping("/info/{param}")
	@ApiOperation("用于查询")
	public ResultVO<String> get(@ApiParam(value = "path参数") @PathVariable String param){
		return new ResultVO<String>().addData(param);
	}
	
	@PostMapping("/info")
	@ApiOperation("用于设置")
	public ResultVO<String> post(
			@ApiParam(value = "url参数") @RequestParam String param,
			@ApiParam(value = "body参数") @RequestBody String body){
		return new ResultVO<String>().addData(param).addData(body);
	}
	
	@GetMapping("/infos")
	@ApiOperation("用于分页")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "page", type = "int", value = "当前页数", paramType = "query"),
		@ApiImplicitParam(name = "size", type = "int", value = "每页记录数", paramType = "query"),
		@ApiImplicitParam(name = "sort", value = "排序相关信息", paramType = "query", allowMultiple = true)
	})
	public ResultVO<Page<?>> page(
			@ApiParam(value = "参数") @RequestParam String param,
			@PageableDefault(value = 20, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable){
		return new ResultVO<Page<?>>().addData(new PageImpl<>(Arrays.asList(param)));
	}
}
