package org.web.base.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.web.base.domain.exception.ResultMessageEnum;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author luyl
 * 用于封装DUBBO接口,封装BIZ层的结果.
 */
@Setter
@Getter
public class ResultDO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    // 终端用户的提示信息，不推荐使用
    private String message;

    // code. @see ServiceException#code属性
    private String code;

    private boolean success = true; // 执行是否成功
    // 用于描述返回记录总数，如果返回单条记录，例如返回的module类型是一个对象，而非集合的场合，会自动设置1，详情见setModule方法。
    private long totalCount = 0;
    private T domain; // 实际的返回结果
    // 某些情况下，特殊 格外的参数返回
    private Map<String, Object> extendsDomain = null;

    public ResultDO() {
    }

    public boolean isFailed() {
        return !isSuccess();
    }

    public ResultDO(boolean success) {
        this.success = success;
        if (success) {
            this.message = "操作成功";
        }
    }

    public ResultDO(boolean success, String code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public void addExtendsDomain(String key, Object model) {
        getExtendsDomain().put(key, model);
    }


    public Map<String, Object> getExtendsDomain() {
        if (extendsDomain == null) {
            extendsDomain = new HashMap<>();
        }
        return extendsDomain;
    }

    @SuppressWarnings("rawtypes")
    public boolean isEmpty() {
        if (!this.success) {
            return true;
        }
        return ObjectUtils.isEmpty(this);
    }

    public boolean isNotEmpty() {
        return !isEmpty();
    }

    public void setDomain(T domain) {
        this.domain = domain;
        if ((!(domain instanceof CharSequence)) && (!(domain instanceof List)) && (!(domain instanceof Set)) && (!(domain instanceof Map))) {
            setTotalCount(1L);
        }
        if (StringUtils.isEmpty(code)) {
            setCode(ResultMessageEnum.SUCCESS.getCode());
            setMessage(ResultMessageEnum.SUCCESS.getMessage());
        }
    }

}
