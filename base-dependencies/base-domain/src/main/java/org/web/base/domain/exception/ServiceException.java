package org.web.base.domain.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;


/**
 * 类ServiceException.java的实现描述：封装业务异常
 *
 * @author Luyl 2016年10月24日 上午10:23:00
 */
@SuppressWarnings("ALL")
@Setter
@Getter
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    // 错误码
    private String errorCode;
    // 结果描述，用于排查问题
    private String description;
    // 参数
    private Map params;


    public ServiceException(String errorCode, String description) {
        this.errorCode = errorCode;
        this.description = description;
    }

    public ServiceException(String errorCode, String description, Map params) {
        this.errorCode = errorCode;
        this.params = params;
        this.description = description;
    }

}
