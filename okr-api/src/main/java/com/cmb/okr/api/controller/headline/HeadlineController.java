package com.cmb.okr.api.controller.headline;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cmb.okr.api.BaseController;
import com.cmb.okr.api.controller.headline.vo.AddHeadlineReq;
import com.cmb.okr.api.controller.headline.vo.BannerResp;
import com.cmb.okr.api.controller.headline.vo.LoadHLPageReq;
import com.cmb.okr.api.util.ContextUtils;
import com.cmb.okr.attachment.AttachmentService;
import com.cmb.okr.dao.domain.auth.User;
import com.cmb.okr.dao.domain.headline.Headline;
import com.cmb.okr.frame.auth.AuthRequired;
import com.cmb.okr.frame.base.JsonResponse;
import com.cmb.okr.frame.db.PagingParam;
import com.cmb.okr.frame.db.PagingResult;
import com.cmb.okr.frame.exception.AppException;
import com.cmb.okr.frame.util.ObjectConvert;
import com.cmb.okr.service.headline.HeadlineService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/hl")
public class HeadlineController extends BaseController {

	@Autowired
	private HeadlineService headlineService;

	@Autowired
	private AttachmentService attachmentService;

	/**
	 * 获取首页轮播图
	 * 
	 * @return
	 */
	@ApiOperation(value = "获取首页轮播图", notes = "")
	@RequestMapping(value = "/banner", method = RequestMethod.GET)
	public JsonResponse bannerList() {
		return doBusiness((res) -> {
			List<Object> list = this.headlineService.getBannerList();
			List<BannerResp> result = ObjectConvert.convertList(list, BannerResp.class);
			for (BannerResp br : result) {
				br.setImageUrl(attachmentService.getUrl(br.getImageId()));
			}
			res.setResult(result);
		}, "获取轮播图失败");
	}

	/**
	 * 添加头条信息
	 * 
	 * @param request
	 * @param req
	 * @return
	 */
	@AuthRequired("hl0001")
	@ApiOperation(value = "添加头条信息", notes = "只有管理员可以操作")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ApiImplicitParam(value = "okraccesstoken", paramType = "header", required = true, name = "okraccesstoken")
	public JsonResponse addHeadline(HttpServletRequest request, @RequestBody AddHeadlineReq req) {
		return doBusiness((res) -> {
			User curUser = ContextUtils.getCurrentUser(request);
			Headline hl = ObjectConvert.convert(req, Headline.class);
			hl.setCreateId(curUser.getId());
			hl.setIsValid(1);
			res.setResult(headlineService.add(hl));
		}, "添加头条信息失败");
	}

	/**
	 * 逻辑删除头条信息
	 * 
	 * @param id
	 * @return
	 */
	@AuthRequired("hl0001")
	@ApiOperation(value = "删除头条", notes = "只有管理员可以操作")
	@RequestMapping(value = "/del/{id}", method = RequestMethod.POST)
	@ApiImplicitParam(value = "okraccesstoken", paramType = "header", required = true, name = "okraccesstoken")
	public JsonResponse delHeadline(@PathVariable("id") String id) {
		return doBusiness((res) -> {
			this.headlineService.delete(id);
		}, "删除头条信息失败");
	}

	/**
	 * 修改头条优先级
	 * 
	 * @param id
	 * @param rank
	 * @return
	 */
	@AuthRequired("hl0002")
	@ApiOperation(value = "修改头条优先级", notes = "只有管理员可以操作")
	@ApiImplicitParams({ @ApiImplicitParam(value = "id", paramType = "path", required = true, name = "id"),
			@ApiImplicitParam(value = "rank", paramType = "path", required = true, name = "rank"),
			@ApiImplicitParam(value = "okraccesstoken", paramType = "header", required = true, name = "okraccesstoken") })
	@RequestMapping(value = "/md/rank/{id}/{rank}", method = RequestMethod.POST)
	public JsonResponse modifyRank(@PathVariable("id") String id, @PathVariable("rank") int rank) {
		return doBusiness((res) -> {
			this.headlineService.modifyRank(id, rank);
		}, "添加头条优先级失败");
	}

	/**
	 * 修改头条图片
	 * 
	 * @param file
	 * @return
	 */
	@AuthRequired("hl0002")
	@ApiOperation(value = "修改头条图片", notes = "只有管理员可以操作")
	@RequestMapping(value = "/change/image/{id}", method = RequestMethod.POST)
	@ApiImplicitParam(value = "okraccesstoken", paramType = "header", required = true, name = "okraccesstoken")
	public JsonResponse changeImage(HttpServletRequest request, @RequestParam("file") MultipartFile file,
			@PathVariable("id") String id) {
		return doBusiness((res) -> {
			InputStream is;
			try {
				is = file.getInputStream();
			} catch (Exception e) {
				throw new AppException("获取附件失败");
			}
			String imageId = attachmentService.putObject(file.getOriginalFilename(), is);
			this.headlineService.changeImage(id, imageId);
		}, "修改头条图片失败");
	}

	/**
	 * 分页查询头条信息
	 * 
	 * @param param
	 * @return
	 */
	@ApiOperation(value = "分页查询课题信息")
	@RequestMapping(value = "/load/page", method = RequestMethod.POST)
	public JsonResponse loadPage(@RequestBody PagingParam<LoadHLPageReq> param) {
		return doBusiness((res) -> {
			PagingResult<Object> pageResult = this.headlineService.loadForPage(param);
			List<BannerResp> pageList = ObjectConvert.convertList(pageResult.getResult(), BannerResp.class);
			// 生成url
			for (BannerResp response : pageList) {
				response.setImageUrl(attachmentService.getUrl(response.getImageId()));
			}
			res.setResult(pageResult.copy(pageList));
		}, "分页查询头条信息失败");
	}

}
