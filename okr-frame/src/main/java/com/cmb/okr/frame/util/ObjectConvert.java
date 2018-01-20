package com.cmb.okr.frame.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cmb.okr.frame.exception.AppException;

public class ObjectConvert {
	private static Logger logger = LoggerFactory.getLogger(ObjectConvert.class);

	/**
	 * 对象转换
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	public static <T> T convert(Object source, Class<T> target) {
		if (source == null || target == null) {
			return null;
		}
		boolean isSourceMap = Map.class.isAssignableFrom(source.getClass());
		boolean isTargetMap = Map.class.isAssignableFrom(target);
		// 都是map
		if (isSourceMap && isTargetMap) {
			Map<String, Object> map = new HashMap<>();
			map.putAll((Map<String, Object>) source);
			return (T) map;
		}
		// source是map target不是map
		if (isSourceMap && !isTargetMap) {
			return (T) convert((Map<String, Object>) source, target);
		}
		// source不是map target是map
		if (!isSourceMap && isTargetMap) {
			Map<String, Object> result = new HashMap<>();
			convert(source, result, false, null);
			return (T) result;
		}
		// 两个都不是map
		try {
			Object result = target.newInstance();
			convert(source, result);
			return (T) result;
		} catch (InstantiationException | IllegalAccessException e) {
			logger.error(String.format("convert Object : %s failed", target));
			throw new AppException("对象转换失败");
		}

	}

	/**
	 * list数据转换
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> convertList(List<?> source, Class<T> target) {
		try {
			if (source == null || source.isEmpty() || target == null) {
				return null;
			}
			boolean isSourceMap = Map.class.isAssignableFrom(source.get(0).getClass());
			if (isSourceMap) {
				return convert((List<Map<String, Object>>) source, target);
			}
			List<T> result = new ArrayList<>();
			for (Object os : source) {
				T t = target.newInstance();
				convert(os, t);
				result.add(t);
			}
			return result;
		} catch (InstantiationException | IllegalAccessException e) {
			logger.error(String.format("convert Object failed"));
			throw new AppException("对象转换失败");
		}
	}

	/**
	 * map数组转换
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public static <T> List<T> convert(List<Map<String, Object>> source, Class<T> target) {
		List<T> result = new ArrayList<>();
		for (Map<String, Object> map : source) {
			result.add(convert(map, target));
		}
		return result;
	}

	/**
	 * 将source中属性赋值到target中（包括父类中的所有属性）
	 * 
	 * @param source
	 * @param target
	 */
	public static void convert(Object source, Object target) {
		convert(source, target, true);
	}

	/**
	 * 
	 * @param source
	 * @param target
	 * @param containsSuper
	 *            是否包含父类中的属性
	 */
	public static void convert(Object source, Object target, boolean containsSuper) {
		try {
			if (source == null || target == null) {
				return;
			}
			Field[] sourceFields = containsSuper ? ReflectUtils.getFields(source.getClass())
					: ReflectUtils.getDeclaredFields(source.getClass());
			Field[] targetFields = containsSuper ? ReflectUtils.getFields(target.getClass())
					: ReflectUtils.getDeclaredFields(target.getClass());

			for (Field sfield : sourceFields) {
				if (sfield.getName().equals("serialVersionUID")) {
					continue;
				}
				for (Field tfield : targetFields) {
					if (sfield.getName().equals(tfield.getName())) {
						if (!sfield.isAccessible()) {
							sfield.setAccessible(true);
						}
						if (!tfield.isAccessible()) {
							tfield.setAccessible(true);
						}
						tfield.set(target, sfield.get(source));
					}
				}
			}
		} catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
			logger.error(String.format("convert Object failed"), e);
			throw new AppException("对象转换失败");
		}
	}

	@SuppressWarnings("unchecked")
	public static void convert(Object source, Object target, boolean skilNullValue, List<String> ignoreProperties) {
		if (source == null || target == null) {
			throw new AppException("对象转换失败");
		}
		boolean isSourceMap = Map.class.isAssignableFrom(source.getClass());
		boolean isTargetMap = Map.class.isAssignableFrom(target.getClass());
		// source 和 target都不是map
		if (!isSourceMap && !isTargetMap) {
			try {
				if (source == null || target == null) {
					return;
				}
				// 获取source和target中的属性
				Field[] sourceFields = ReflectUtils.getFields(source.getClass());
				Field[] targetFields = ReflectUtils.getFields(target.getClass());

				for (Field sfield : sourceFields) {
					// 判断当前属性值是否忽略
					if (ignoreProperties != null && ignoreProperties.contains(sfield.getName())) {
						continue;
					}
					if (sfield.getName().equals("serialVersionUID")) {
						continue;
					}
					// 属性为空则判断是否跳过空
					if (!sfield.isAccessible()) {
						sfield.setAccessible(true);
					}
					Object sVal = sfield.get(source);
					if (sVal == null && skilNullValue) {
						continue;
					}
					for (Field tfield : targetFields) {
						if (sfield.getName().equals(tfield.getName())) {
							if (!tfield.isAccessible()) {
								tfield.setAccessible(true);
							}
							tfield.set(target, sVal);
						}
					}
				}
			} catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
				logger.error(String.format("convert Object failed"), e);
				throw new AppException("对象转换失败");
			}
		} else if (isSourceMap && !isTargetMap) {
			convert((Map<String, Object>) source, target, skilNullValue, ignoreProperties);
		} else if (!isSourceMap && isTargetMap) {
			convert(source, (Map<String, Object>) target, skilNullValue, ignoreProperties);
		} else if (isSourceMap && isTargetMap) {
			convert((Map<String, Object>) source, (Map<String, Object>) target, skilNullValue, ignoreProperties);
		}

	}
}
