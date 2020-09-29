package com.ruoyi.flowable.wapper;

import java.util.List;

/**
 * @author kubilewang
 * @date 2020年3月24日
 */
public interface IListWrapper {
    /**
     * 执行List转换封装
     *
     * @param list
     * @return
     */
    @SuppressWarnings("rawtypes")
    public List execute(List list);
}