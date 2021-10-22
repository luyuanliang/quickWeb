package org.web.base.domain.helper;

import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.web.base.domain.ResultDO;
import org.web.base.domain.exception.ResultMessageEnum;

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

}
