package com.cmb.okr.service.subject;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmb.okr.dao.domain.auth.User;
import com.cmb.okr.dao.domain.subject.Subject;
import com.cmb.okr.dao.domain.subject.SubjectParticipant;
import com.cmb.okr.dao.enums.UserType;
import com.cmb.okr.dao.subject.enums.SubjectStatus;
import com.cmb.okr.frame.db.BaseDao;
import com.cmb.okr.frame.db.PagingParam;
import com.cmb.okr.frame.db.PagingResult;
import com.cmb.okr.frame.exception.AppException;
import com.cmb.okr.frame.util.IdUtils;
import com.cmb.okr.service.constants.ErrorMessage;

@Service
public class SubjectService {

	private static final String SQL_PREFIX = "com.cmb.okr.service.subject.SubjectService.";

	private static final String COUNT_PAGE_SQL = "countForPage";
	private static final String PAGE_SQL = "queryForPage";
	private static final String LOAD_SUBJECT_DETAIL = "loadSubjectDetail";
	private static final String CHECK_NAME_EXISTS = "checkNameExists";
	private static final String LOAD_FOR_CHECK = "loadForCheck";
	private static final String QUIT_SUBJECT = "quitSubject";

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
		if (this.checkSubjectNameExists(null, subject.getName())) {
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
		Subject sbj = this.baseDao.load(SQL_PREFIX.concat(LOAD_FOR_CHECK), query);
		if (sbj == null) {
			throw new AppException("课题信息不存在");
		}
		// 只用管理员或者课题负责人才能修改状态
		if (UserType.admin == curUser.getUserType() || curUser.getId().equals(sbj.getLeaderId())) {
			query.setStatus(status);
			this.baseDao.update(query);
		} else {
			throw new AppException(ErrorMessage.NO_AUTH);
		}
	}

	/**
	 * 校验用户是否为课题负责人
	 * 
	 * @param subjectId
	 * @param userId
	 * @return
	 */
	public boolean checkIsLeader(String subjectId, String userId) {
		Subject query = new Subject();
		query.setId(subjectId);
		Subject sbj = this.baseDao.load(SQL_PREFIX.concat(LOAD_FOR_CHECK), query);
		if (sbj == null) {
			throw new AppException("课题信息不存在");
		}
		return sbj.getLeaderId().equals(userId);
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
		Subject sbj = this.baseDao.load(SQL_PREFIX.concat(LOAD_FOR_CHECK), query);
		if (sbj == null) {
			throw new AppException("课题信息不存在");
		}
		// 只用管理员或者课题负责人才能修改状态
		if (UserType.admin == curUser.getUserType() || curUser.getId().equals(sbj.getLeaderId())) {
			query.setIcon(iconId);
			this.baseDao.update(query);
		} else {
			throw new AppException(ErrorMessage.NO_AUTH);
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
	 * 
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

	/**
	 * 邀请成员
	 * 
	 * @param curUser
	 * @param subjectId
	 * @param memId
	 */
	public void inviteMember(User curUser, String subjectId, String memId) {
		if (StringUtils.isEmpty(subjectId) || StringUtils.isEmpty(memId)) {
			throw new AppException("缺少课题信息或成员信息");
		}
		Subject query = new Subject();
		query.setId(subjectId);
		Subject sbj = this.baseDao.load(SQL_PREFIX.concat(LOAD_FOR_CHECK), query);
		if (sbj == null) {
			throw new AppException("课题信息不存在");
		}
		User uq = new User();
		uq.setId(memId);
		User userEntity = this.baseDao.load(uq);
		if (userEntity == null) {
			throw new AppException("用户不存在");
		}
		// 只用课题负责人才能邀请成员
		if (curUser.getId().equals(sbj.getLeaderId())) {
			SubjectParticipant spQuery = new SubjectParticipant();
			spQuery.setSubjectId(subjectId);
			spQuery.setUserId(memId);
			SubjectParticipant spEntity = this.baseDao.load(spQuery);
			if (spEntity != null) {
				if (0 == spEntity.getIsQuit()) {
					throw new AppException("该用户已是该课题成员");
				} else {
					spEntity.setIsQuit(0);
					this.baseDao.update(spEntity);
				}
			} else {
				this.addSubjectParticipant(sbj, userEntity);
			}
		} else {
			throw new AppException(ErrorMessage.NO_AUTH);
		}
	}

	private void addSubjectParticipant(Subject subject, User member) {
		SubjectParticipant sp = new SubjectParticipant();
		sp.setId(IdUtils.getUUID());
		sp.setIsQuit(0);
		sp.setJoinDate(new Date());
		sp.setSubjectId(subject.getId());
		sp.setUserId(member.getId());
		sp.setUserName(member.getName());
		this.baseDao.insert(sp);
	}

	/**
	 * 获取课题成员列表
	 * 
	 * @param subjectId
	 * @return
	 */
	public List<SubjectParticipant> loadMems(String subjectId) {
		SubjectParticipant query = new SubjectParticipant();
		query.setSubjectId(subjectId);
		query.setIsQuit(0);
		return this.baseDao.queryForAll(query);
	}

	/**
	 * 删除课题成员
	 * 
	 * @param curUser
	 * @param subjectId
	 * @param memId
	 * @param remark
	 */
	public void delMem(User curUser, String subjectId, String memId, String remark) {
		Subject query = new Subject();
		query.setId(subjectId);
		Subject sbj = this.baseDao.load(SQL_PREFIX.concat(LOAD_FOR_CHECK), query);
		if (sbj == null) {
			throw new AppException("课题信息不存在");
		}
		if (!curUser.getId().equals(sbj.getLeaderId())) {
			throw new AppException(ErrorMessage.NO_AUTH);
		}
		this.quitSubject(subjectId, memId, remark);
	}

	/**
	 * 退出课题
	 * 
	 * @param subjectId
	 * @param memId
	 * @param remark
	 */
	public void quitSubject(String subjectId, String memId, String remark) {
		SubjectParticipant sp = new SubjectParticipant();
		sp.setSubjectId(subjectId);
		sp.setUserId(memId);
		sp.setQuitRemark(remark);
		sp.setIsQuit(1);
		sp.setQuitDate(new Date());
		this.baseDao.update(SQL_PREFIX.concat(QUIT_SUBJECT), sp);
	}

}
