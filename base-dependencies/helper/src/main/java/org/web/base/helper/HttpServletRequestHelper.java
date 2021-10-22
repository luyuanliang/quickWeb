package org.web.base.helper;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.web.base.domain.exception.ServiceException;
import org.web.base.domain.exception.ResultMessageEnum;
import org.web.base.domain.helper.ServiceExceptionHelper;

@SuppressWarnings({"rawtypes","unchecked"})
public class HttpServletRequestHelper {

	private static final Logger logger = Logger.getLogger(HttpServletRequestHelper.class);

	public static final String DATA_FORMAT_ = "DATA_FORMAT_";
	public static final String TIME_FORMAT_ = "TIME_FORMAT_";

	public static <T> T buildBeanByRequest(HttpServletRequest request, Class<T> clazz) throws ServiceException {
		logger.info("URI is " + request.getRequestURI());
		return buildBeanByMap(buildMapByRequest(request), clazz);
	}

	public static <T> T buildBeanByMap(Map map, Class<T> clazz) throws ServiceException {

		try {
			T obj = clazz.newInstance();
			BeanUtils.populate(obj, map);
			return obj;
		} catch (Exception e) {
			logger.error(ServiceExceptionHelper.getExceptionInfo(e));
			throw ServiceExceptionHelper.buildServiceException(ResultMessageEnum.PARAM_FORMAT_INVALID);
		}
	}

	public static Map<String, Object> buildMapByRequest(HttpServletRequest request) throws ServiceException{
		return buildMapByRequest(request,"全部");
	}

	public static Map<String, Object> buildMapByRequest(HttpServletRequest request,String setNullStr) throws ServiceException {
		logger.info("URI is " + request.getRequestURI());
		try {
			Enumeration<String> e = request.getParameterNames();
			Map<String, Object> map = new HashMap<>();
			while (e.hasMoreElements()) {
				final String name = e.nextElement().toString();
				String value = request.getParameter(name);
				logger.info(name + "=" + request.getParameter(name));
				if (name.startsWith(DATA_FORMAT_)) {
					map.put(name.replaceFirst(DATA_FORMAT_, ""), DateHelper.fromStrGetDate(request.getParameter(name),DateHelper.FORMAT_YYYY_MM_DD));
				} else if (name.startsWith(TIME_FORMAT_)) {
					map.put(name.replaceFirst(TIME_FORMAT_, ""), DateHelper.fromStrGetDate(request.getParameter(name),DateHelper.FORMAT_YYYY_MM_DD_HH_MM_SS));
				} else if (setNullStr.equals(value)){
					map.put(name, null);
				} else if (!StringUtils.isEmpty(value)){
					map.put(name, request.getParameter(name));
				}

			}
			return map;
		} catch (Exception e) {
			logger.error(ServiceExceptionHelper.getExceptionInfo(e));
			throw ServiceExceptionHelper.buildServiceException(ResultMessageEnum.PARAM_FORMAT_INVALID);
		}
	}

}
