package com.zzq.webserver.core.context.holder;

import com.zzq.webserver.core.filter.Filter;
import lombok.Data;

/**
 * @author ZZQ
 * @date 2020/2/1
 */
@Data
public class FilterHolder {
    private Filter filter;
    private String filterClass;

    public FilterHolder(String filterClass) {
        this.filterClass = filterClass;
    }
}
