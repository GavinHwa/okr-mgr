package com.cmb.okr.codegen.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Condition;

import com.cmb.okr.codegen.cfg.Config;
import com.cmb.okr.codegen.db.Column;
import com.cmb.okr.codegen.db.Table;

/**
 * TODO
 * 
 * @author: guohm
 * @date:2015年1月12日 下午9:45:58
 * @since 1.0.0
 */
public class Context {
	private String packageName;
	private String tableName;
	private Entity entity;
	private Condition condition;
	private Mapper mapper;
	private String auth;
	private String version;
	private String datetime;

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public Mapper getMapper() {
		return mapper;
	}

	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	/**
	 * @return the condition
	 */
	public Condition getCondition() {
		return condition;
	}

	/**
	 * @param condition
	 *            the condition to set
	 */
	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	/**
	 * @return the packageName
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * @param packageName
	 *            the packageName to set
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public void parse(Table table) {
		tableName = parseName(table.getTableName());
		Config cfg = table.getDatabase().getCfg();
		packageName = cfg.getPackageName();
		this.auth = cfg.getAuth();
		this.version = cfg.getVersion();
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dateStr = simpleDateFormat.format(date);
		this.datetime = dateStr;
		parseEntity(table);
		parseMapper(table);
		parseColumn(table, entity, mapper);
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	private void parseEntity(Table table) {
		entity = new Entity();
		entity.setClazzName(tableName);
		entity.setPackageName(packageName);
	}

	private void parseMapper(Table table) {
		this.mapper = new Mapper();
		mapper.setTableName(table.getTableName());
		String cn = entity.getClazzName();
		String resultMapId = cn.substring(0, 1).toLowerCase() + cn.substring(1, cn.length());
		mapper.setResultMapId(resultMapId);
	}

	private String parseName(String name) {
		String[] names = name.split("_");
		StringBuilder tableNames = new StringBuilder("");
		if (names.length > 0) {
			for (int i = 0; i < names.length; i++) {
				tableNames.append(names[i].substring(0, 1).toUpperCase()).append(names[i].substring(1));
			}
		}
		return tableNames.toString();
	}

	private String parseFieldName(String name) {
		String[] names = name.split("_");
		StringBuilder fieldName = new StringBuilder("");
		if (names.length > 0) {
			for (int i = 0; i < names.length; i++) {
				if (i == 0) {
					fieldName.append(names[i].substring(0, 1).toLowerCase()).append(names[i].substring(1));
				} else {
					fieldName.append(names[i].substring(0, 1).toUpperCase()).append(names[i].substring(1));
				}

			}
		}
		return fieldName.toString();
	}

	private void parseColumn(Table table, Entity entity, Mapper mapper) {
		List<Column> columns = table.getColumns();
		List<Field> fields = new ArrayList<Field>();
		List<MapperField> mapperFields = new ArrayList<MapperField>();
		// boolean hasDate = false;
		List<String> imports = new ArrayList<String>();
		for (Column c : columns) {
			String fieldName = parseFieldName(c.getColName());

			if (isIncludeField(fieldName)) {
				Field f = new Field();
				f.setName(fieldName);
				String fieldType = createFieldType(c.getColType());
				f.setType(fieldType);
				f.setComment(c.getComment());
				f.setSetMethodName("set" + parseName(c.getColName()));
				f.setGetMethodName("get" + parseName(c.getColName()));
				fields.add(f);
				if ("Date".equals(f.getType()) || "Timestamp".equals(f.getType())) {
					imports.add("java.util.Date".intern());
				}
				if ("BigDecimal".equals(f.getType())) {
					imports.add("java.math.BigDecimal".intern());
				}
			}
			if (!"id".equalsIgnoreCase(fieldName) && !"autoIncId".equalsIgnoreCase(fieldName)) {
				MapperField mf = new MapperField();
				mf.setCol(c.getColName());
				mf.setProperty(fieldName);
				mf.setJdbcType(this.createJdbcType(c.getColType()));
				mapperFields.add(mf);
			}
		}
		entity.setFields(fields);
		if (!imports.isEmpty()) {
			entity.setImports(imports);
		}
		mapper.setFields(mapperFields);
	}

	public String createFieldType(String type) {
		if (type == null || "".equals(type.trim())) {
			return "";
		}

		if (type.equals("varchar")) {
			return "String";
		}

		if (type.equals("bigint")) {
			return "Long";
		}

		if (type.equals("int")) {
			return "Integer";
		}
		
		if (type.equals("bit")) {
			return "Integer";
		}

		if (type.equals("tinyint")) {
			return "Integer";
		}

		if (type.equals("datetime")) {
			return "Date";
		}

		if (type.equals("double")) {
			return "Double";
		}

		if (type.equals("timestamp")) {
			return "Date";
		}
		if (type.equals("date")) {
			return "Date";
		}
		if (type.equals("char")) {
			return "String";
		}
		if (type.equals("decimal")) {
			return "BigDecimal";
		}
		return "";
	}

	public String createJdbcType(String type) {
		if (type == null || "".equals(type.trim())) {
			return "";
		}

		if (type.equals("varchar")) {
			return "VARCHAR";
		}

		if (type.equals("bigint")) {
			return "BIGINT";
		}

		if (type.equals("int")) {
			return "INTEGER";
		}
		if (type.equals("tinyint")) {
			return "TINYINT";
		}
		if (type.equals("bit")) {
			return "INTEGER";
		}
		if (type.equals("datetime")) {
			return "DATE";
		}
		if (type.equals("double")) {
			return "DOUBLE";
		}
		if (type.equals("timestamp")) {
			return "TIMESTAMP";
		}
		if (type.equals("char")) {
			return "CHAR";
		}
		if (type.equals("date")) {
			return "DATE";
		}
		if (type.equals("decimal")) {
			return "DECIMAL";
		}
		return "";
	}

	private boolean isIncludeField(String filedName) {
		/*
		 * || "createId".equalsIgnoreCase(filedName) ||
		 */
		if ("id".equalsIgnoreCase(filedName)) {
			return false;
		}
		return true;
	}
}
