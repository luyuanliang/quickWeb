package org.web.base.domain.view;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 封装返回给前端页面的对象，把该对象转成json输出给前端页面。
 * */
@Setter
@Getter
public class ViewResultDO<T> {

    public ViewResultDO(boolean result) {
        this.result = result;
        if (result) {
            setMsg("操作成功！");
        }
    }

    public ViewResultDO() {
    }

    private boolean result;
    private String msg;
    // error、question、info、warning。
    private String type;
    private String title;
    int total = 0;
    T data;
    T rows;

    public void setData(T data) {
        setData(data, false);
    }

    public void setData(T data, boolean setDataFlag) {
        if (setDataFlag) {
            this.data = data;
        } else if (data instanceof List) {
            setRows(data);
        } else {
            this.data = data;
        }
    }

    public enum ViewType {
        error, question, info, warning
    }
}