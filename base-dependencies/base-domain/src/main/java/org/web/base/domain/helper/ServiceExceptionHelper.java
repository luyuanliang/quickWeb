package org.web.base.domain.helper;

import org.apache.commons.lang3.StringUtils;
import org.web.base.domain.exception.ServiceException;
import org.web.base.domain.exception.ResultMessageEnum;
import org.web.base.domain.view.ViewResultDO;

import java.util.Map;

/**
 * 类ServiceExceptionHelper.java的实现描述：
 *
 * @author Administrator 2016年10月24日 上午11:19:35
 */
@SuppressWarnings("rawtypes")
public class ServiceExceptionHelper {

    public static ServiceException buildServiceException(ResultMessageEnum resultMessageEnum, String message, Map params) {
        return new ServiceException(resultMessageEnum.getCode(), resultMessageEnum.getMessage(), params);
    }

    public static ServiceException buildServiceException(ResultMessageEnum resultMessageEnum, String message) {
        return buildServiceException(resultMessageEnum, message, null);
    }

    public static ServiceException buildServiceException(ResultMessageEnum resultMessageEnum, Map params) {
        return new ServiceException(resultMessageEnum.getCode(), resultMessageEnum.getMessage(), params);
    }

    public static ServiceException buildServiceException(ResultMessageEnum resultMessageEnum) {
        return buildServiceException(resultMessageEnum, null, null);
    }


    public static ViewResultDO buildViewResultByServiceException(Exception e, String message) {
        if (e instanceof ServiceException) {
            return buildViewResultByServiceException((ServiceException) e, message);
        }
        return buildViewResultByServiceException(buildServiceException(ResultMessageEnum.SYSTEM_ERROR), message);
    }

    public static ViewResultDO buildViewResultByServiceException(ServiceException e) {
        return buildViewResultByServiceException(e, null);
    }

    public static ViewResultDO buildViewResultByServiceException(ServiceException e, String msg) {
        return buildViewResultByServiceException(e, msg, StringUtils.isEmpty(msg) ? msg : e.getMessage());
    }

    public static ViewResultDO buildViewResultByServiceException(ServiceException e, String msg, String title) {
        ViewResultDO view = new ViewResultDO();
        view.setType(ViewResultDO.ViewType.error.name());
        view.setResult(false);
        if (StringUtils.isNotBlank(title)) {
            view.setTitle(title);
        } else {
            view.setTitle("操作失败");
        }

        if (StringUtils.isNotBlank(msg)) {
            view.setMsg(msg);
        } else if (StringUtils.isNotBlank(e.getMessage())) {
            view.setMsg(e.getMessage());
        } else if (StringUtils.isNotBlank(e.getDescription())) {
            view.setMsg(e.getDescription());
        }
        return view;
    }


    public static String getExceptionInfo(Exception e) {
        StringBuilder buffer = new StringBuilder();
        if (e != null) {
            buffer.append(e.toString()).append("\n\t");
            StackTraceElement[] messages = e.getStackTrace();
            int length = messages.length;
            for (StackTraceElement message : messages) {
                buffer.append("at ").append(message.toString()).append("\n\t");
            }
        }
        return buffer.toString();
    }
}
