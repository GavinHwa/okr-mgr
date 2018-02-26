package com.cmb.okr.api.controller.okr;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cmb.okr.api.BaseController;
import com.cmb.okr.api.controller.okr.vo.AddKeyResultReq;
import com.cmb.okr.api.controller.okr.vo.AddObjectiveReq;
import com.cmb.okr.api.controller.okr.vo.ModifyKRProgressReq;
import com.cmb.okr.api.controller.okr.vo.ModifyObjectiveReq;
import com.cmb.okr.api.controller.okr.vo.ObjectiveResponse;
import com.cmb.okr.api.util.ContextUtils;
import com.cmb.okr.dao.domain.auth.User;
import com.cmb.okr.dao.domain.okr.KeyResult;
import com.cmb.okr.dao.domain.okr.ObjectiveInfo;
import com.cmb.okr.dao.domain.okr.enums.ObjectiveType;
import com.cmb.okr.frame.auth.LoginRequired;
import com.cmb.okr.frame.base.JsonResponse;
import com.cmb.okr.frame.exception.AppException;
import com.cmb.okr.frame.util.ObjectConvert;
import com.cmb.okr.service.okr.OkrService;
import com.cmb.okr.service.subject.SubjectService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api("okr接口")
@RestController
@RequestMapping("/okr")
public class ObjectiveController extends BaseController {

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private OkrService okrService;

	/**
	 * 创建目标 必须要有至少一条关键结果 课题目标只有负责人才能创建
	 * 
	 * @param request
	 * @param req
	 * @return
	 */
	@ApiOperation(value = "添加目标信息", notes = "")
	@ApiImplicitParam(value = "okraccesstoken", paramType = "header", required = false, name = "okraccesstoken")
	@LoginRequired
	@RequestMapping(value = "/objective/add", method = RequestMethod.POST)
	public JsonResponse addObjective(HttpServletRequest request, @RequestBody AddObjectiveReq req) {
		return doBusiness((res) -> {
			// 数据校验
			if (req.getObjectiveType() == null) {
				throw new AppException("课题类型不能为空!");
			}
			if (CollectionUtils.isEmpty(req.getKeyResults())) {
				throw new AppException("关键结果不能为空!");
			}
			if (req.getObjectiveType() == ObjectiveType.team && StringUtils.isEmpty(req.getSubjectId())) {
				throw new AppException("课题不能为空!");
			}
			if (req.getObjectiveType() == ObjectiveType.personal && StringUtils.isEmpty(req.getParentId())) {
				throw new AppException("父目标不能为空!");
			}
			if (StringUtils.isEmpty(req.getObjectiveContent())) {
				throw new AppException("目标内容不能为空");
			}
			if (req.getStartDate() == null || req.getEndDate() == null || req.getStartDate().after(req.getEndDate())) {
				throw new AppException("目标周期不能为空且开始时间必须早于结束时间");
			}
			for (AddKeyResultReq krReq : req.getKeyResults()) {
				this.checkKeyResult(krReq, false);
			}
			User curUser = ContextUtils.getCurrentUser(request);
			if (req.getObjectiveType() == ObjectiveType.team
					&& !this.subjectService.checkIsLeader(req.getSubjectId(), curUser.getId())) {
				throw new AppException("只有负责人才能创建课题目标");
			}
			List<KeyResult> krs = ObjectConvert.convertList(req.getKeyResults(), KeyResult.class);
			ObjectiveInfo objective = ObjectConvert.convert(req, ObjectiveInfo.class);
			this.okrService.addObjective(curUser, objective, krs);
		}, "添加目标失败");
	}

	/**
	 * 添加关键结果
	 * 
	 * @param request
	 * @param req
	 * @return
	 */
	@ApiOperation(value = "添加关键结果", notes = "")
	@ApiImplicitParam(value = "okraccesstoken", paramType = "header", required = false, name = "okraccesstoken")
	@LoginRequired
	@RequestMapping(value = "/kr/add", method = RequestMethod.POST)
	public JsonResponse addKeyResult(HttpServletRequest request, @RequestBody AddKeyResultReq req) {
		return doBusiness((res) -> {
			this.checkKeyResult(req, true);
			User curUser = ContextUtils.getCurrentUser(request);
			if (!this.okrService.checkIsObjectiveOwner(curUser.getId(), req.getObjectiveId())) {
				throw new AppException("只能添加自己目标的关键结果");
			}
			KeyResult kr = ObjectConvert.convert(req, KeyResult.class);
			res.setResult(this.okrService.addKeyResult(kr));
		}, "添加关键结果失败");
	}

	private void checkKeyResult(AddKeyResultReq req, boolean checkObject) {
		if (req == null) {
			throw new AppException("关键结果不能为空");
		}
		if (checkObject && StringUtils.isEmpty(req.getObjectiveId())) {
			throw new AppException("目标信息未指定");
		}
		if (StringUtils.isEmpty(req.getResultInfo())) {
			throw new AppException("结果信息未填写");
		}
		if (req.getProgress() == null || req.getProgress() > 100 || req.getProgress() < 0) {
			throw new AppException("进度必须在0-100之间");
		}
	}

	/**
	 * 删除关键结果
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "删除关键结果", notes = "只有自己可以删除")
	@ApiImplicitParam(value = "okraccesstoken", paramType = "header", required = false, name = "okraccesstoken")
	@LoginRequired
	@RequestMapping(value = "/kr/del/{id}", method = RequestMethod.POST)
	public JsonResponse delKeyResult(HttpServletRequest request, @PathVariable("id") String id) {
		return doBusiness((res) -> {
			if (StringUtils.isEmpty(id)) {
				throw new AppException("未指定需要删除的信息");
			}
			User curUser = ContextUtils.getCurrentUser(request);
			if (!this.okrService.checkIsKrOwner(curUser.getId(), id)) {
				throw new AppException("不能删除别人的关键结果");
			}
			this.okrService.delKeyResult(id);
		}, "删除关键结果失败");
	}

	/**
	 * 修改结果描述和权重
	 * 
	 * @param request
	 * @param req
	 * @return
	 */
	@ApiOperation(value = "修改关键结果", notes = "只有自己可以修改")
	@ApiImplicitParam(value = "okraccesstoken", paramType = "header", required = false, name = "okraccesstoken")
	@LoginRequired
	@RequestMapping(value = "/kr/modify", method = RequestMethod.POST)
	public JsonResponse modifyKeyResult(HttpServletRequest request, @RequestBody AddKeyResultReq req) {
		return doBusiness((res) -> {
			if (StringUtils.isEmpty(req.getId())) {
				throw new AppException("未指定需修改的关键结果");
			}
			this.checkKeyResult(req, false);
			User curUser = ContextUtils.getCurrentUser(request);
			if (!this.okrService.checkIsKrOwner(curUser.getId(), req.getId())) {
				throw new AppException("只能修改自己的关键结果");
			}
			this.okrService.modifyKR(req.getId(), req.getResultInfo(), req.getWeight());
		}, "修改关键结果失败");
	}

	/**
	 * 修改关键结果进度
	 * 
	 * @param request
	 * @param req
	 * @return
	 */
	@ApiOperation(value = "修改关键结果进度", notes = "只有自己可以修改")
	@ApiImplicitParam(value = "okraccesstoken", paramType = "header", required = false, name = "okraccesstoken")
	@LoginRequired
	@RequestMapping(value = "/kr/progress/modify", method = RequestMethod.POST)
	public JsonResponse modifyKRProgress(HttpServletRequest request, @RequestBody ModifyKRProgressReq req) {
		return doBusiness((res) -> {
			if (req.getProgress() == null || req.getProgress() > 100 || req.getProgress() < 0) {
				throw new AppException("进度必须在0-100之间");
			}
			User curUser = ContextUtils.getCurrentUser(request);
			if (!this.okrService.checkIsKrOwner(curUser.getId(), req.getId())) {
				throw new AppException("只能修改自己的关键结果");
			}
			this.okrService.modifyKRProgress(req.getId(), req.getRemark(), req.getProgress());
		}, "修改进度失败");
	}

	/**
	 * 修改目标 包括目标说明、目标类型、目标开始-结束时间
	 * 
	 * @param request
	 * @param req
	 * @return
	 */
	@ApiOperation(value = "修改目标信息", notes = "只有自己能修改")
	@ApiImplicitParam(value = "okraccesstoken", paramType = "header", required = false, name = "okraccesstoken")
	@LoginRequired
	@RequestMapping(value = "/objective/modify", method = RequestMethod.POST)
	public JsonResponse modifyObjective(HttpServletRequest request, @RequestBody ModifyObjectiveReq req) {
		return doBusiness((res) -> {
			if (StringUtils.isEmpty(req.getId())) {
				throw new AppException("未指定需要修改的目标信息");
			}
			if (StringUtils.isEmpty(req.getObjectiveContent())) {
				throw new AppException("目标内容不能为空");
			}
			if (req.getStartDate() == null || req.getEndDate() == null || req.getStartDate().after(req.getEndDate())) {
				throw new AppException("目标周期不能为空且开始时间必须早于结束时间");
			}
			User curUser = ContextUtils.getCurrentUser(request);
			if (!this.okrService.checkIsObjectiveOwner(curUser.getId(), req.getId())) {
				throw new AppException("只能修改自己的目标");
			}
			ObjectiveInfo oi = ObjectConvert.convert(req, ObjectiveInfo.class);
			this.okrService.modifyObjective(oi);
		}, "修改目标信息失败");
	}

	@ApiOperation(value = "获取目标下所有关键结果")
	@RequestMapping(value = "/kr/query/{objectiveId}", method = RequestMethod.GET)
	public JsonResponse queryKRsForObjective(@PathVariable("objectiveId") String objectiveId) {
		return doBusiness((res) -> {
			res.setResult(this.okrService.loadKRSByObjective(objectiveId));
		}, "获取关键结果列表失败");
	}

	/**
	 * 获取我的目标集合
	 * 
	 * @param objectiveId
	 * @return
	 */
	@LoginRequired
	@ApiOperation(value = "获取我的目标集合")
	@ApiImplicitParam(value = "okraccesstoken", paramType = "header", required = false, name = "okraccesstoken")
	@RequestMapping(value = "/objective/self/query", method = RequestMethod.GET)
	public JsonResponse queryMyObjectives(HttpServletRequest request) {
		return doBusiness((res) -> {
			User curUser = ContextUtils.getCurrentUser(request);
			List<ObjectiveResponse> result = ObjectConvert
					.convert(this.okrService.loadObjectiveByUser(curUser.getId(), false), ObjectiveResponse.class);
			res.setResult(result);
		}, "获取关键结果列表失败");
	}

	@ApiOperation(value = "获取课题下的所有课题目标")
	@RequestMapping(value = "/objective/team/query/{subjectId}", method = RequestMethod.GET)
	public JsonResponse queryObjectivesBySubject(HttpServletRequest request,
			@PathVariable("subjectId") String subjectId) {
		return doBusiness((res) -> {
			List<ObjectiveResponse> result = ObjectConvert
					.convert(this.okrService.loadObjectiveBySubject(subjectId, false), ObjectiveResponse.class);
			res.setResult(result);
		}, "获取关键结果列表失败");
	}

	@ApiOperation(value = "获取课题目标下的所有子目标")
	@RequestMapping(value = "/objective/sub/query/{parentId}", method = RequestMethod.GET)
	public JsonResponse loadSubObjectives(@PathVariable("parentId") String parentId) {
		return doBusiness((res) -> {
			List<ObjectiveResponse> result = ObjectConvert.convert(this.okrService.loadSubObjectives(parentId, false),
					ObjectiveResponse.class);
			res.setResult(result);
		}, "获取关键结果列表失败");
	}

}
