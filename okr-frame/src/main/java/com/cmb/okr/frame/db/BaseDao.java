package com.cmb.okr.frame.db;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionHolder;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.Assert;

import com.cmb.okr.frame.exception.AppException;
import com.cmb.okr.frame.util.ReflectUtils;

/**
 * 数据操作统一入口
 * 
 * @author hf
 *
 */
@Repository
public class BaseDao extends SqlSessionDaoSupport {

	private SqlSession batchSqlSession;

	private SqlSessionFactory sqlSessionFactory;

	@Resource
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
		this.sqlSessionFactory = sqlSessionFactory;
		this.batchSqlSession = new SqlSessionTemplate(sqlSessionFactory, ExecutorType.BATCH);
	}

	private static final String INSERT_SUFFIX = "insert";
	private static final String UPDATE_SUFFIX = "update";
	private static final String DELETE_SUFFIX = "delete";
	private static final String LOAD_SUFFIX = "load";
	private static final String QUERYALL_SUFFIX = "queryForAll";
	private static final String PAGE_QUERY_SUFFIX = "queryForPage";
	private static final String PAGE_COUNT_SUFFIX = "countForPage";
	private static final String SELECT_COUNT_SUFFIX = "selectCount";

	private static final String NOT_NULL_MSG = "数据不能为null";

	private void assertNotNull(Object entity) {
		Assert.notNull(entity, NOT_NULL_MSG);
	}

	public int selectCount(String sqlId, Object entity) {
		this.assertNotNull(entity);
		return getSqlSession().selectOne(sqlId, entity);
	}

	public int selectCount(Object entity) {
		String sqlId = this.genSqlId(entity.getClass(), SELECT_COUNT_SUFFIX);
		return this.selectCount(sqlId, entity);
	}

	public int insert(String sqlId, Object entity) {
		this.assertNotNull(entity);
		return getSqlSession().insert(sqlId, entity);
	}

	public int insert(Object entity) {
		this.assertNotNull(entity);
		String sqlId = genSqlId(entity.getClass(), INSERT_SUFFIX);
		return this.insert(sqlId, entity);
	}

	public int delete(String sqlId, Object entity) {
		this.assertNotNull(entity);
		return this.getSqlSession().delete(sqlId, entity);
	}

	public int delete(Object entity) {
		this.assertNotNull(entity);
		String sqlId = genSqlId(entity.getClass(), DELETE_SUFFIX);
		return this.delete(sqlId, entity);
	}

	public int update(String sqlId, Object entity) {
		this.assertNotNull(entity);
		return this.getSqlSession().update(sqlId, entity);
	}

	public int update(Object entity) {
		this.assertNotNull(entity);
		String sqlId = genSqlId(entity.getClass(), UPDATE_SUFFIX);
		return this.update(sqlId, entity);
	}

	public <T> T load(String sqlId, Object entity) {
		this.assertNotNull(entity);
		return this.getSqlSession().selectOne(sqlId, entity);
	}

	public <T> T load(Object entity) {
		this.assertNotNull(entity);
		String sqlId = genSqlId(entity.getClass(), LOAD_SUFFIX);
		return this.load(sqlId, entity);
	}

	public <T> List<T> queryForAll(String sqlId, Object entity) {
		this.assertNotNull(entity);
		return this.getSqlSession().selectList(sqlId, entity);
	}

	public <T> List<T> queryForAll(Object entity) {
		this.assertNotNull(entity);
		String sqlId = genSqlId(entity.getClass(), QUERYALL_SUFFIX);
		return this.queryForAll(sqlId, entity);
	}

	public void batchInsert(List<?> listEntity) {
		this.assertNotNull(listEntity);
		String sqlId = genSqlId(listEntity.get(0).getClass(), INSERT_SUFFIX);
		this.batchInsert(sqlId, listEntity);
	}

	public void batchInsert(String sqlId, List<?> listEntity) {
		this.assertNotNull(listEntity);
		this.batchOperator(new BatchOperator() {
			@Override
			public void doBatch(SqlSession batchSqlSession) {
				for (int i = 0; i < listEntity.size(); i++) {
					batchSqlSession.insert(sqlId, listEntity.get(i));
				}
			}
		});
	}

	public <T> PagingResult<T> queryForPage(PagingParam<T> pagingParam) {
		assertNotNull(pagingParam);
		assertNotNull(pagingParam.getParam());
		String countSql = genSqlId(pagingParam.getParam().getClass(), PAGE_COUNT_SUFFIX);
		String queryId = this.genSqlId(pagingParam.getParam().getClass(), PAGE_QUERY_SUFFIX);
		return queryForPage(countSql, queryId, pagingParam);
	}

	public <T> PagingResult<T> queryForPage(String countSqlId, String querySqlId, PagingParam<?> pagingParam) {
		assertNotNull(pagingParam);
		assertNotNull(pagingParam.getParam());
		PagingResult<T> result = new PagingResult<>();
		int count = this.getSqlSession().selectOne(countSqlId, pagingParam);
		List<T> rst = this.getSqlSession().selectList(querySqlId, pagingParam);
		result.setResult(rst);
		result.setTotalNum(count);
		result.initTotalPage(pagingParam.getPageSize());
		return result;
	}

	public void batchUpdate(String sqlId, List<?> listEntity) {
		this.assertNotNull(listEntity);
		this.batchOperator(new BatchOperator() {
			@Override
			public void doBatch(SqlSession batchSqlSession) {
				for (int i = 0; i < listEntity.size(); i++) {
					batchSqlSession.update(sqlId, listEntity.get(i));
				}
			}
		});
	}

	public void batchUpdate(List<?> listEntity) {
		this.assertNotNull(listEntity);
		String sqlId = genSqlId(listEntity.get(0).getClass(), INSERT_SUFFIX);
		this.batchUpdate(sqlId, listEntity);
	}

	public void batchDelete(String sqlId, List<?> listEntity) {
		this.assertNotNull(listEntity);
		this.batchOperator((batchSqlSession) -> {
			for (int i = 0; i < listEntity.size(); i++) {
				batchSqlSession.delete(sqlId, listEntity.get(i));
			}
		});
	}

	public void batchDelete(List<?> listEntity) {
		this.assertNotNull(listEntity);
		String sqlId = genSqlId(listEntity.get(0).getClass(), INSERT_SUFFIX);
		this.batchDelete(sqlId, listEntity);
	}

	private void batchOperator(BatchOperator operator) {
		SqlSessionHolder holder = (SqlSessionHolder) TransactionSynchronizationManager.getResource(sqlSessionFactory);
		ExecutorType eType = holder.getExecutorType();
		try {
			if (holder != null && holder.getExecutorType() != ExecutorType.BATCH) {
				ReflectUtils.setField(holder, "executorType", ExecutorType.BATCH);
			}
			operator.doBatch(this.batchSqlSession);
		} finally {
			if (holder != null) {
				ReflectUtils.setField(holder, "executorType", eType);
			}
		}

	}

	private String genSqlId(Class<?> clazz, String sqlSuffix) {
		checkParamType(clazz);
		return clazz.getName().concat(".").concat(sqlSuffix);
	}

	private void checkParamType(Class<?> clazz) {
		if (ReflectUtils.isPrimitiveTypes(clazz) || Map.class.isAssignableFrom(clazz)) {
			throw new AppException("param must be an entity");
		}
	}

}