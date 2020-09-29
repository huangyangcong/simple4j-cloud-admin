package com.ruoyi.flowable.wapper;

import com.ruoyi.flowable.common.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author kubilewang
 * @date 2020年3月22日
 */
@Component
public class ProcDefListWrapper implements IListWrapper {

    @Autowired
    private ResponseFactory responseFactory;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public List execute(List list) {
        return responseFactory.createProcessDefinitionResponseList(list);
    }
}
