package com.cmb.okr.service.okr;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cmb.okr.dao.domain.auth.User;
import com.cmb.okr.dao.domain.okr.KeyResult;
import com.cmb.okr.dao.domain.okr.KeyResultUpdateLog;
import com.cmb.okr.dao.domain.okr.ObjectiveInfo;
import com.cmb.okr.dao.domain.okr.enums.ObjectiveType;
import com.cmb.okr.frame.db.BaseDao;
import com.cmb.okr.frame.util.IdUtils;

/**
 * okr管理类
 * 
 * @author hf
 *
 */
@Component
public class OkrService {

	private static final String SQL_PREFIX = "com.cmb.okr.service.okr.OkrService.";
	private static final String CHECK_IS_OBJECTIVE_OWNER = "checkIsObjectiveOwner";
	private static final String CHECK_IS_KR_OWNER = "checkIsKROwner";

	private static final String LOAD_OBJECTIVE_BY_USER = "loadObjectiveByUser";
	private static final String LOAD_OBJECTIVE_BY_SUBJECT = "loadObjectiveBySubject";
	private static final String LOAD_OBJECTIVE_BY_PARENT = "loadObjectiveByParent";

	@Autowired
	private BaseDao baseDao;

	/**
	 * 添加目标信息
	 * 
	 * @param curUser
	 * @param objective
	 * @param krs
	 */
	@Transactional
	public void addObjective(User curUser, ObjectiveInfo objective, List<KeyResult> krs) {
		String oId = IdUtils.getUUID();
		if(ObjectiveType.team == objective.getObjectiveType()){
			objective.setParentId(null);
		}
		objective.setId(oId);
		objective.setCreateId(curUser.getId());
		for (KeyResult kr : krs) {
			kr.setId(IdUtils.getUUID());
			kr.setObjectiveId(oId);
		}
		this.baseDao.insert(objective);
		this.baseDao.batchInsert(krs);
	}

	/**
	 * 修改目标信息
	 * 
	 * @param objective
	 */
	public void modifyObjective(ObjectiveInfo objective) {
		objective.setLastModifiedTime(new Date());
		this.baseDao.update(objective);
	}

	/**
	 * 判断是否是目标的拥有者
	 * 
	 * @param userId
	 * @param objectiveId
	 * @return
	 */
	public boolean checkIsObjectiveOwner(String userId, String objectiveId) {
		Map<String, String> map = new HashMap<>();
		map.put("createId", userId);
		map.put("id", objectiveId);
		return this.baseDao.selectCount(SQL_PREFIX.concat(CHECK_IS_OBJECTIVE_OWNER), map) == 1;
	}

	/**
	 * 判断是否是关键结果的拥有者
	 * 
	 * @param userId
	 * @param krId
	 * @return
	 */
	public boolean checkIsKrOwner(String userId, String krId) {
		Map<String, String> map = new HashMap<>();
		map.put("createId", userId);
		map.put("id", krId);
		return this.baseDao.selectCount(SQL_PREFIX.concat(CHECK_IS_KR_OWNER), map) == 1;
	}

	/**
	 * 添加关键结果
	 * 
	 * @param kr
	 * @return
	 */
	public String addKeyResult(KeyResult kr) {
		String id = IdUtils.getUUID();
		kr.setId(id);
		this.baseDao.insert(kr);
		return id;
	}

	/**
	 * 删除关键结果
	 * 
	 * @param id
	 */
	public void delKeyResult(String id) {
		KeyResult qdel = new KeyResult();
		qdel.setId(id);
		this.baseDao.delete(qdel);
	}

	/**
	 * 修改进度
	 * 
	 * @param id
	 * @param remark
	 * @param progress
	 */
	@Transactional
	public void modifyKRProgress(String id, String remark, int progress) {
		KeyResult query = new KeyResult();
		query.setId(id);
		KeyResult kr = this.baseDao.load(query);
		if (kr.getProgress() == progress) {
			return;
		}
		kr.setProgress(progress);
		KeyResultUpdateLog log = new KeyResultUpdateLog();
		log.setId(IdUtils.getUUID());
		log.setKeyResultId(id);
		log.setProgressBefore(kr.getProgress());
		log.setProgressAfter(progress);
		log.setRemark(remark);
		Date now = new Date();
		log.setUpdateDate(now);
		kr.setLastModifiedTime(now);
		this.baseDao.update(kr);
		this.baseDao.insert(log);
	}

	/**
	 * 修改结果描述和权重
	 * 
	 * @param id
	 * @param content
	 * @param weight
	 */
	public void modifyKR(String id, String content, Integer weight) {
		KeyResult kr = new KeyResult();
		kr.setId(id);
		kr.setLastModifiedTime(new Date());
		kr.setResultInfo(content);
		kr.setWeight(weight);
		this.baseDao.update(kr);
	}

	/**
	 * 获取目标下的所有关键结果
	 * 
	 * @param objectiveId
	 * @return
	 */
	public List<KeyResult> loadKRSByObjective(String objectiveId) {
		KeyResult query = new KeyResult();
		query.setObjectiveId(objectiveId);
		return this.baseDao.queryForAll(query);
	}

	/**
	 * 获取我的所有目标
	 * 
	 * @param objectiveId
	 * @param isOverDueInclude
	 * @return
	 */
	public List<Map<String, Object>> loadObjectiveByUser(String userId, boolean isOverDueInclude) {
		Map<String, Object> query = new HashMap<>();
		query.put("userId", userId);
		query.put("isOverDueInclude", isOverDueInclude);
		return this.baseDao.queryForAll(SQL_PREFIX.concat(LOAD_OBJECTIVE_BY_USER), query);
	}

	/**
	 * 获取课题下的所有课题目标
	 * @param subjectId
	 * @param isOverDueInclude
	 * @return
	 */
	public List<Map<String, Object>> loadObjectiveBySubject(String subjectId, boolean isOverDueInclude) {
		Map<String, Object> query = new HashMap<>();
		query.put("subjectId", subjectId);
		query.put("isOverDueInclude", isOverDueInclude);
		return this.baseDao.queryForAll(SQL_PREFIX.concat(LOAD_OBJECTIVE_BY_SUBJECT), query);
	}

	/**
	 * 获取课题目标下的所有子目标
	 * @param parentId
	 * @param isOverDueInclude
	 * @return
	 */
	public List<Map<String, Object>> loadSubObjectives(String parentId, boolean isOverDueInclude) {
		Map<String, Object> query = new HashMap<>();
		query.put("parentId", parentId);
		query.put("isOverDueInclude", isOverDueInclude);
		return this.baseDao.queryForAll(SQL_PREFIX.concat(LOAD_OBJECTIVE_BY_PARENT), query);
	}

}
