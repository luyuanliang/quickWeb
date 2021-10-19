package org.web.base.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class ListHelper {
    public ListHelper() {
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <T> List<T> transStr2List(String str, String seperate, Class<T> clazz) {
        List list = null;
        if (StringUtils.isNotBlank(str)) {
            list = new ArrayList();
            String[] array = str.split(seperate);

            for (String s : array) {
                if (StringUtils.isNotBlank(s) && StringUtils.isNotBlank(s.trim())) {
                    if ("java.lang.Long".equals(clazz.toString())) {
                        list.add(Long.valueOf(s.trim()));
                    } else if ("java.lang.Integer".equals(clazz.toString())) {
                        list.add(Integer.valueOf(s.trim()));
                    } else {
                        list.add(s.trim());
                    }
                }
            }
        }

        return list != null && list.size() != 0 ? list : null;
    }

    public static boolean isEmpty(List list) {
        if (list == null || list.size() == 0) {
            return true;
        }
        return false;
    }
    public static boolean isNotEmpty(List list) {
        return !isEmpty(list);
    }

}
