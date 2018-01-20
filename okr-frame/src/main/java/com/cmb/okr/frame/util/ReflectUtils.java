package com.cmb.okr.frame.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.util.ReflectionUtils;

import com.cmb.okr.frame.exception.AppException;

public class ReflectUtils extends ReflectionUtils {

	private static final List<String> PRIMITIVE_TYPE = Arrays.asList("Boolean", "Character", "Byte", "Short", "Integer",
			"Long", "Float", "Double", "String", "BigDecimal");

	private static final Map<Class<?>, Field[]> fieldsCache = new ConcurrentHashMap<Class<?>, Field[]>(256);
	private static final Map<Class<?>, Field[]> declaredFieldsCache = new ConcurrentHashMap<Class<?>, Field[]>(256);
	private static final Field[] NO_FIELDS = {};

	public static Field[] getDeclaredFields(Class<?> clazz) {
		Field[] result = declaredFieldsCache.get(clazz);
		if (result == null) {
			result = clazz.getDeclaredFields();
			declaredFieldsCache.put(clazz, (result.length == 0 ? NO_FIELDS : result));
		}
		return result;
	}

	/**
	 * 获取当前类及其父类中的所有属性
	 * 
	 * @param clazz
	 * @return
	 */
	public static Field[] getFields(Class<?> clazz) {
		Field[] result = fieldsCache.get(clazz);
		if (result == null) {
			List<Field> flist = new ArrayList<>();
			for (Class<?> klass = clazz; klass != Object.class; klass = klass.getSuperclass()) {
				flist.addAll(Arrays.asList(klass.getDeclaredFields()));
			}
			result = new Field[flist.size()];
			result = flist.toArray(result);
			fieldsCache.put(clazz, (result.length == 0 ? NO_FIELDS : result));
		}
		return result;
	}
	
	public static boolean isPrimitiveTypes(Class<?> clazz) {
		String className = clazz.getSimpleName();
		return PRIMITIVE_TYPE.contains(className);
	}

	public static void setField(Object target, String fieldName, Object val) {
		try {
			Field f = ReflectionUtils.findField(target.getClass(), fieldName);
			if (f == null) {
				return;
			}
			f.setAccessible(true);
			f.set(target, val);
		} catch (Exception e) {
			throw new AppException("反射赋值出错", e);
		}
	}
}
