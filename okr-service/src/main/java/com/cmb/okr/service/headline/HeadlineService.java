package com.cmb.okr.service.headline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cmb.okr.dao.domain.headline.Headline;
import com.cmb.okr.frame.config.OkrConfig;
import com.cmb.okr.frame.db.BaseDao;
import com.cmb.okr.frame.db.PagingParam;
import com.cmb.okr.frame.db.PagingResult;
import com.cmb.okr.frame.exception.AppException;
import com.cmb.okr.frame.util.IdUtils;

@Component
public class HeadlineService {

	@Autowired
	private BaseDao baseDao;

	@Autowired
	private OkrConfig okrConfig;

	private static final String SQL_PREFIX = "com.cmb.okr.service.headline.HeadlineService.";

	private static final String SELECT_BANNER_LIST = "selectBannerList";

	private static final String COUNT_PAGE_SQL = "countForPage";
	private static final String PAGE_SQL = "queryForPage";

	/**
	 * 添加头条
	 * 
	 * @param entity
	 */
	public String add(Headline entity) {
		entity.setId(IdUtils.getUUID());
		this.baseDao.insert(entity);
		return entity.getId();
	}

	/**
	 * 修改优先级
	 * 
	 * @param id
	 * @param rank
	 */
	public void modifyRank(String id, int rank) {
		Headline queryEntity = new Headline();
		queryEntity.setId(id);
		queryEntity.setIsValid(1);
		int count = this.baseDao.selectCount(queryEntity);
		if (count == 0) {
			throw new AppException("该头条不存在或已失效");
		}
		queryEntity.setRank(rank);
		this.baseDao.update(queryEntity);
	}

	/**
	 * 查询首页轮播图
	 * 
	 * @return
	 */
	public List<Object> getBannerList() {
		return this.baseDao.queryForAll(SQL_PREFIX.concat(SELECT_BANNER_LIST), okrConfig.getHlNum());
	}

	/**
	 * 修改图片
	 * 
	 * @param user
	 * @param id
	 * @param imageId
	 */
	public void changeImage(String id, String imageId) {
		Headline queryEntity = new Headline();
		queryEntity.setId(id);
		queryEntity.setIsValid(1);
		int count = this.baseDao.selectCount(queryEntity);
		if (count == 0) {
			throw new AppException("该头条不存在或已失效");
		}
		queryEntity.setImageId(imageId);
		this.baseDao.update(queryEntity);
	}

	/**
	 * 逻辑删除
	 * 
	 * @param id
	 */
	public void delete(String id) {
		Headline entity = new Headline();
		entity.setId(id);
		entity.setIsValid(0);
		this.baseDao.update(entity);
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
	
	
}
