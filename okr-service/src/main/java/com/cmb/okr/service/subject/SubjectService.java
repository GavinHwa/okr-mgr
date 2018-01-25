package com.cmb.okr.service.subject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmb.okr.dao.domain.auth.User;
import com.cmb.okr.dao.domain.subject.Subject;
import com.cmb.okr.dao.enums.UserType;
import com.cmb.okr.dao.subject.enums.SubjectStatus;
import com.cmb.okr.frame.db.BaseDao;
import com.cmb.okr.frame.db.PagingParam;
import com.cmb.okr.frame.db.PagingResult;
import com.cmb.okr.frame.exception.AppException;
import com.cmb.okr.frame.util.IdUtils;

@Service
public class SubjectService {

	private static final String SQL_PREFIX = "com.cmb.okr.service.subject.SubjectService.";

	private static final String COUNT_PAGE_SQL = "countForPage";
	private static final String PAGE_SQL = "queryForPage";
	private static final String LOAD_SUBJECT_DETAIL = "loadSubjectDetail";
	private static final String CHECK_NAME_EXISTS = "checkNameExists";

	@Autowired
	BaseDao baseDao;

	/**
	 * 新增课题
	 * 
	 * @param subject
	 * @return
	 */
	public Subject add(Subject subject) {
		subject.setId(IdUtils.getUUID());
		if(this.checkSubjectNameExists(null, subject.getName())){
			throw new AppException("课题名称已经存在");
		}
		baseDao.insert(subject);
		return subject;
	}

	/**
	 * 修改课题负责人
	 * 
	 * @param subjectId
	 * @param leaderId
	 */
	public void changeLeader(String subjectId, String leaderId) {
		if (StringUtils.isEmpty(subjectId) || StringUtils.isEmpty(leaderId)) {
			throw new AppException("缺少课题信息或负责人信息");
		}
		Subject subj = new Subject();
		subj.setId(subjectId);
		int count = this.baseDao.selectCount(subj);
		if (count == 0) {
			throw new AppException("课题不存在");
		}
		subj.setLeaderId(leaderId);
		this.baseDao.update(subj);
	}

	/**
	 * 更新课题状态
	 * 
	 * @param curUser
	 * @param subjectId
	 * @param status
	 */
	public void changeStatus(User curUser, String subjectId, SubjectStatus status) {
		if (StringUtils.isEmpty(subjectId) || status == null) {
			throw new AppException("缺少课题信息或状态信息");
		}
		Subject query = new Subject();
		query.setId(subjectId);
		Subject sbj = this.baseDao.load(query);
		if (sbj == null) {
			throw new AppException("课题信息不存在");
		}
		// 只用管理员或者课题负责人才能修改状态
		if (UserType.admin == curUser.getUserType() || subjectId.equals(sbj.getLeaderId())) {
			query.setStatus(status);
			this.baseDao.update(query);
		} else {
			throw new AppException("你没有权限修改该课题状态");
		}
	}

	/**
	 * 修改课题图标
	 * 
	 * @param curUser
	 * @param subjectId
	 * @param iconId
	 */
	public void changeIcon(User curUser, String subjectId, String iconId) {
		if (StringUtils.isEmpty(subjectId) || StringUtils.isEmpty(iconId)) {
			throw new AppException("缺少课题信息或附件信息");
		}
		Subject query = new Subject();
		query.setId(subjectId);
		Subject sbj = this.baseDao.load(query);
		if (sbj == null) {
			throw new AppException("课题信息不存在");
		}
		// 只用管理员或者课题负责人才能修改状态
		if (UserType.admin == curUser.getUserType() || subjectId.equals(sbj.getLeaderId())) {
			query.setIcon(iconId);
			this.baseDao.update(query);
		} else {
			throw new AppException("你没有权限修改该课题图标");
		}
	}

	/**
	 * 分页查询数据
	 * 
	 * @param param
	 * @return
	 */
	public PagingResult<Object> loadForPage(PagingParam<?> param) {
		PagingResult<Object> result = this.baseDao.queryForPage(SQL_PREFIX.concat(COUNT_PAGE_SQL),
				SQL_PREFIX.concat(PAGE_SQL), param);
		return result;
	}

	/**
	 * 获取课题信息
	 * 
	 * @param id
	 * @return
	 */
	public Object loadSubject(String id) {
		return this.baseDao.load(SQL_PREFIX.concat(LOAD_SUBJECT_DETAIL), id);
	}

	/**
	 * name唯一性校验
	 * @param subjectId
	 * @param name
	 * @return
	 */
	public boolean checkSubjectNameExists(String subjectId, String name) {
		if (StringUtils.isEmpty(name)) {
			return false;
		}
		Subject queryEntity = new Subject();
		queryEntity.setName(name);
		int count = this.baseDao.selectCount(SQL_PREFIX.concat(CHECK_NAME_EXISTS), queryEntity);
		return count > 0;
	}

}
