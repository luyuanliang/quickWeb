package org.web.base.domain.helper;

import lombok.NonNull;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.web.base.domain.ResultDO;
import org.web.base.domain.exception.ResultMessageEnum;
import org.web.base.domain.exception.ServiceException;

import java.util.Map;

@SuppressWarnings("ALL")
public class ResultHelper {

    public static ResultDO buildResultDOByResultMessageEnum(@NonNull ResultMessageEnum resultMessageEnum, String message, Map extendsDomain) {
        ResultDO resultDO = new ResultDO(false);
        if (ResultMessageEnum.SUCCESS == resultMessageEnum) {
            resultDO.setSuccess(true);
        }
        if (StringUtils.isNotBlank(message)) {
            resultDO.setMessage(message);
        } else {
            resultDO.setMessage(resultMessageEnum.getMessage());
        }
        resultDO.setCode(resultMessageEnum.getCode());
        resultDO.setExtendsDomain(extendsDomain);
        return resultDO;
    }

    public static ResultDO buildResultDOByResultMessageEnum(ResultMessageEnum resultMessageEnum, String message) {
        return buildResultDOByResultMessageEnum(resultMessageEnum, message, null);
    }

    public static ResultDO buildResultDOByResultMessageEnum(ResultMessageEnum resultMessageEnum, Map extendsDomain) {
        return buildResultDOByResultMessageEnum(resultMessageEnum, null, extendsDomain);
    }

    public static ResultDO buildResultDOByResultMessageEnum(ResultMessageEnum resultMessageEnum) {
        return buildResultDOByResultMessageEnum(resultMessageEnum, null, null);
    }

    /***
     * Payattention there are some same key name, between serviceException.getParams() and input param.
     * */
    public static ResultDO buildResultDOByException(Exception e, Map param) {
        ResultDO resultDO = new ResultDO(false);
        if (param != null) {
            resultDO.setExtendsDomain(param);
        }
        if (e instanceof ServiceException) {
            ServiceException serviceException = (ServiceException) e;
            resultDO.setCode(serviceException.getErrorCode());
            resultDO.setMessage(serviceException.getMessage());
            if (ObjectUtils.isEmpty(serviceException.getParams())) {
                resultDO.getExtendsDomain().putAll(serviceException.getParams());
            }
        } else {
            resultDO.setCode(ResultMessageEnum.SYSTEM_ERROR.getCode());
            resultDO.setMessage(ServiceExceptionHelper.getExceptionInfo(e));
        }
        return resultDO;
    }

    public static ResultDO buildResultDOByException(Exception e) {
        return buildResultDOByException(e, null);
    }

}
