package com.cmb.okr.api.controller;

import com.cmb.okr.api.BaseController;
import com.cmb.okr.dao.domain.HomeBanner;
import com.cmb.okr.frame.base.JsonResponse;
import com.cmb.okr.frame.db.PagingParam;
import com.cmb.okr.service.HomeBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("首页banner图片管理API测试类")
@RestController
@RequestMapping("/homebanner")
public class HomeBannerController extends BaseController {

	@Autowired
	HomeBannerService homeBannerService;

	@ApiOperation(value = "新增banner图片", notes = "")
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public JsonResponse insert(@RequestBody HomeBanner homeBanner) {

		return doBusiness((res) -> {
			HomeBanner hb = homeBannerService.insert(homeBanner);
			res.setResult(hb);
		}, "新增banner图片失败");

	}

	@ApiOperation(value = "更新banner图片", notes = "")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public JsonResponse update(@RequestBody HomeBanner homeBanner) {
		return doBusiness((res) -> {
			homeBannerService.update(homeBanner);
		}, "banner图片更新失败");

	}

	@ApiOperation(value = "删除banner图片", notes = "")
	@ApiImplicitParam(paramType = "path", required = true, name = "id")
	@RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
	public JsonResponse delete(@PathVariable("id") String id) {

		return doBusiness((res) -> {
			homeBannerService.delete(id);
		}, "删除banner图片失败");
	}

	@ApiOperation(value = "获取banner图片", notes = "")
	@ApiImplicitParam(paramType = "path", required = true, name = "id")
	@RequestMapping(value = "load/{id}", method = RequestMethod.POST)
	public JsonResponse load(@PathVariable("id") String id) {
		return doBusiness((res) -> {
			res.setResult(homeBannerService.load(id));
		}, "获取banner图片失败");

	}

	@ApiOperation(value = "分页查询banner图片", notes = "")
	@RequestMapping(value = "paging", method = RequestMethod.POST)
	public JsonResponse queryForPage(@RequestBody PagingParam<HomeBanner> param) {
		return doBusiness((res) -> {
			res.setResult(homeBannerService.queryForPage(param));
		}, "分页查询banner图片失败");
	}
}
