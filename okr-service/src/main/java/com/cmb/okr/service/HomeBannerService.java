package com.cmb.okr.service;

import com.cmb.okr.dao.domain.HomeBanner;
import com.cmb.okr.dao.domain.Subject;
import com.cmb.okr.frame.db.BaseDao;
import com.cmb.okr.frame.db.PagingParam;
import com.cmb.okr.frame.db.PagingResult;
import com.cmb.okr.frame.util.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeBannerService {

	@Autowired
	BaseDao baseDao;

	public HomeBanner insert(HomeBanner homeBanner) {
		homeBanner.setId(IdUtils.getUUID());
		baseDao.insert(homeBanner);
		return homeBanner;
	}

	public void delete(String id) {
		HomeBanner homeBanner = new HomeBanner();
		homeBanner.setId(id);
		baseDao.delete(homeBanner);
	}

	public void update(HomeBanner homeBanner) {
		baseDao.update(homeBanner);
	}

	public HomeBanner load(String id) {
		HomeBanner homeBanner = new HomeBanner();
		homeBanner.setId(id);
		return this.baseDao.load(homeBanner);
	}

	public List<HomeBanner> queryAll(HomeBanner homeBanner) {
		return baseDao.queryForAll(homeBanner);
	}

	public PagingResult<HomeBanner> queryForPage(PagingParam<HomeBanner> param) {
		return this.baseDao.queryForPage(param);
	}
}
