package com.cmb.okr.api.controller.subject;

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
import com.cmb.okr.api.controller.subject.vo.AddSubjectReq;
import com.cmb.okr.api.controller.subject.vo.ChangeLeaderReq;
import com.cmb.okr.api.controller.subject.vo.ChangeStatusReq;
import com.cmb.okr.api.controller.subject.vo.LoadPageReq;
import com.cmb.okr.api.controller.subject.vo.SubjectResponse;
import com.cmb.okr.api.util.ContextUtils;
import com.cmb.okr.attachment.AttachmentService;
import com.cmb.okr.dao.domain.auth.User;
import com.cmb.okr.dao.domain.subject.Subject;
import com.cmb.okr.frame.auth.AuthRequired;
import com.cmb.okr.frame.base.JsonResponse;
import com.cmb.okr.frame.db.PagingParam;
import com.cmb.okr.frame.db.PagingResult;
import com.cmb.okr.frame.exception.AppException;
import com.cmb.okr.frame.util.ObjectConvert;
import com.cmb.okr.service.subject.SubjectService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api("课题管理API测试类")
@RestController
@RequestMapping("/subject")
public class SubjectController extends BaseController {

	@Autowired
	SubjectService subjectService;

	@Autowired
	private AttachmentService attachmentService;

	@ApiOperation(value = "检查课题名称是否存在", notes = "")
	@RequestMapping(value = "/check/name/{name}/{id}", method = RequestMethod.GET)
	public JsonResponse checkNameExists(@PathVariable("name") String name,
			@PathVariable(value = "id", required = false) String id) {
		return doBusiness((res) -> {
			res.setResult(this.subjectService.checkSubjectNameExists(id, name));
		}, "课题名称校验失败");
	}

	/**
	 * 新增课题
	 * 
	 * @param subject
	 * @return
	 */
	@AuthRequired("sbj0001")
	@ApiImplicitParam(value = "okraccesstoken", paramType = "header", required = true, name = "okraccesstoken")
	@ApiOperation(value = "新增课题", notes = "")
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public JsonResponse insert(HttpServletRequest request, @RequestBody AddSubjectReq req) {

		return doBusiness((res) -> {
			Subject sub = ObjectConvert.convert(req, Subject.class);
			sub.setCreateId(ContextUtils.getCurrentUser(request).getId());
			res.setResult(subjectService.add(sub));
		}, "新建课题失败");
	}

	/**
	 * 修改课题负责人
	 * 
	 * @param req
	 * @return
	 */
	@AuthRequired("sbj0002")
	@ApiOperation(value = "修改课题负责人", notes = "只有管理员可以修改")
	@ApiImplicitParam(value = "okraccesstoken", paramType = "header", required = true, name = "okraccesstoken")
	@RequestMapping(value = "/change/leader", method = RequestMethod.POST)
	public JsonResponse changeSubjectLeader(@RequestBody ChangeLeaderReq req) {
		return doBusiness((res) -> {
			this.subjectService.changeLeader(req.getSubjectId(), req.getLeaderId());
		}, "新建课题失败");
	}

	/**
	 * 修改课题图标
	 * 
	 * @param file
	 * @return
	 */
	@ApiOperation(value = "修改课题图标", notes = "只有负责人和管理员可以修改状态")
	@RequestMapping(value = "/change/icon/{subjectId}", method = RequestMethod.POST)
	@ApiImplicitParam(value = "okraccesstoken", paramType = "header", required = true, name = "okraccesstoken")
	public JsonResponse changeIcon(HttpServletRequest request, @RequestParam("file") MultipartFile file,
			@PathVariable("subjectId") String subjectId) {
		return doBusiness((res) -> {
			InputStream is;
			try {
				is = file.getInputStream();
			} catch (Exception e) {
				throw new AppException("获取附件失败");
			}
			String iconId = attachmentService.putObject(file.getOriginalFilename(), is);
			User user = ContextUtils.getCurrentUser(request);
			this.subjectService.changeIcon(user, subjectId, iconId);
		}, "修改课题图标失败");
	}

	/**
	 * 修改课题进度 只有负责人和管理员可以修改状态
	 * 
	 * @param request
	 * @param req
	 * @return
	 */
	@ApiOperation(value = "修改课题进度", notes = "只有负责人和管理员可以修改状态")
	@ApiImplicitParam(value = "okraccesstoken", paramType = "header", required = true, name = "okraccesstoken")
	@RequestMapping(value = "/change/status", method = RequestMethod.POST)
	public JsonResponse changeStatus(HttpServletRequest request, @RequestBody ChangeStatusReq req) {
		return doBusiness((res) -> {
			User user = ContextUtils.getCurrentUser(request);
			this.subjectService.changeStatus(user, req.getSubjectId(), req.getStatus());
		}, "修改课题状态失败");
	}

	/**
	 * 分页查询课题信息
	 * 
	 * @param param
	 * @return
	 */
	@ApiOperation(value = "分页查询课题信息")
	@RequestMapping(value = "/load/page", method = RequestMethod.POST)
	public JsonResponse loadPage(@RequestBody PagingParam<LoadPageReq> param) {
		return doBusiness((res) -> {
			PagingResult<Object> pageResult = this.subjectService.loadForPage(param);
			List<SubjectResponse> pageList = ObjectConvert.convertList(pageResult.getResult(), SubjectResponse.class);
			// 生成url
			for (SubjectResponse response : pageList) {
				response.setIconUrl(attachmentService.getUrl(response.getIcon()));
			}
			res.setResult(pageResult.copy(pageList));
		}, "获取课题列表失败");
	}

	/**
	 * 获取课题详情
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "获取课题详情")
	@ApiImplicitParam(value = "id", paramType = "path", required = true, name = "id")
	@RequestMapping(value = "/load/detail/{id}", method = RequestMethod.GET)
	public JsonResponse load(@PathVariable("id") String id) {
		return doBusiness((res) -> {
			SubjectResponse sr = ObjectConvert.convert(this.subjectService.loadSubject(id), SubjectResponse.class);
			if (sr == null) {
				throw new AppException("课题信息不存在");
			}
			sr.setIconUrl(attachmentService.getUrl(sr.getIcon()));
			res.setResult(sr);
		}, "获取课题详情失败");
	}

	// TODO 邀请课题成员
	// TODO 接受课题邀请
	// TODO 退出课题

}
