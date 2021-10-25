package org.web.base.domain.exception;


public enum ResultMessageEnum {

    SUCCESS("SUCCESS", "成功"),
    SYSTEM_EXCEPTION("SYSTEM_EXCEPTION", "系统异常"),
    SYSTEM_ERROR("SYSTEM_ERROR", "系统异常"),

    // data exception.
    DATA_RECORD_EXIST("DATA_RECORD_EXIST", "记录已存在"),
    DATA_NO_PRIVILEGE("DATA_NO_PRIVILEGE", "没有访问权限"),
    DATA_FORBIDDEN_UPDATE("DATA_FORBIDDEN_UPDATE", "记录不允许修改"),
    DATA_LOSE_EFFICACY("DATA_LOSE_EFFICACY", "数据失效"),
    DATA_NOT_EXIST("DATA_NOT_EXIST", "信息不存在"),

    // 外部系统异常
    OUTER_SYSTEM_EXCEPTION("OUTER_SYSTEM_EXCEPTION", "外部系统异常"),
    OUTER_ACCESS_SYSTEM_ERROR("OUTER_ACCESS_SYSTEM_ERROR", "访问外部系统出错"),

    // 参数相关	PARAM_INVALID("PARAM_INVALID", "参数不合法"),
    PARAM_FORMAT_INVALID("PARAM_FORMAT_INVALID", "格式不合法"),
    PARAM_EMPTY("PARAM_EMPTY", "参数为空"),
    PARAM_INVALID("PARAM_INVALID", "参数无效"),
    PARAM_DATE_COMPARE_TIME_INVALID("PARAM_DATE_COMPARE_TIME_INVALID", "时间日期不合法,结束时间不能早于开始时间"),
    PARAM_DATE_MORE_THAN_CURRENT("PARAM_DATE_MORE_THAN_CURRENT", "时间日期不合法,设定日期早于当前时间"),
    // 服务初始化错误
    INIT_PARAM_NOT_SETTED("INIT_PARAM_NOT_SETTED","参数没有设置");

    private String code;
    private String message;

    ResultMessageEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessageByCode(String code) {
        for (ResultMessageEnum c : ResultMessageEnum.values()) {
            if (c.getCode().equals(code)) {
                return c.getMessage();
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    }
