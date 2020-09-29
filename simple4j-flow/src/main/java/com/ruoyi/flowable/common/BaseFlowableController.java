package com.ruoyi.flowable.common;




import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.filter.SqlFilter;
import com.ruoyi.common.utils.ObjectUtils;
import com.ruoyi.common.utils.SpringContextUtils;
import com.ruoyi.flowable.service.FlowableTaskService;
import com.ruoyi.flowable.service.PermissionService;
import com.ruoyi.flowable.wapper.IListWrapper;
import org.flowable.bpmn.model.ValuedDataObject;
import org.flowable.common.engine.api.FlowableIllegalArgumentException;
import org.flowable.common.engine.api.query.Query;
import org.flowable.common.engine.api.query.QueryProperty;
import org.flowable.engine.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author kubilewang
 * @date 2020年3月24日
 */
@SuppressWarnings({"rawtypes"})
public abstract class BaseFlowableController {
    @Autowired
    protected ResponseFactory responseFactory;
    @Autowired
    protected RepositoryService repositoryService;
    @Autowired
    protected ManagementService managementService;
    @Autowired
    protected RuntimeService runtimeService;
    @Autowired
    protected FormService formService;
    @Autowired
    protected HistoryService historyService;
    @Autowired
    protected PermissionService permissionService;
    @Autowired
    protected FlowableTaskService flowableTaskService;
    @Autowired
    protected TaskService taskService;

    protected FlowablePage getFlowablePage(Map<String, String> requestParams) {
        int current = -1;
        if (ObjectUtils.isNotEmpty(requestParams.get(Constants.CURRENT))) {
            current = ObjectUtils.convertToInteger(requestParams.get(Constants.CURRENT), 1);
        }
        int size = 10;
        if (ObjectUtils.isNotEmpty(requestParams.containsKey(Constants.SIZE))) {
            size = ObjectUtils.convertToInteger(requestParams.get(Constants.SIZE), 10);
        }
        if (current < 0) {
            return null;
        }
        List<FlowablePage.Order> orders = null;
        if (ObjectUtils.isNotEmpty(requestParams.get(Constants.ORDER_RULE))) {
            // orderRule=column1|asc,column2|desc
            orders = new ArrayList<>();
            String orderRule = requestParams.get(Constants.ORDER_RULE);
            // 处理排序
            if (orderRule != null && orderRule.length() > 0) {
                String[] orderColumnRules = orderRule.split(",");
                for (String orderColumnRule : orderColumnRules) {
                    if (orderColumnRule.length() == 0) {
                        continue;
                    }
                    String[] rule = orderColumnRule.split("\\|");
                    String orderColumn = rule[0];
                    SqlFilter.sqlInject(orderColumn);
                    FlowablePage.Order orderTmp = null;
                    if (rule.length == 2 && "DESC".equals(rule[1].toUpperCase())) {
                        orderTmp = new FlowablePage.Order(orderColumn, FlowablePage.Direction.DESC);
                    } else {
                        orderTmp = new FlowablePage.Order(orderColumn, FlowablePage.Direction.ASC);
                    }
                    orders.add(orderTmp);
                }
            }
        }
        if (orders == null) {
            return FlowablePage.of(current - 1, size);
        } else {
            return FlowablePage.of(current - 1, size, orders);
        }
    }

    protected FlowablePage pageList(Map<String, String> requestParams, Query query,
                                    Class<? extends IListWrapper> listWrapperClass,
                                    Map<String, QueryProperty> allowedSortProperties) {
        return pageList(getFlowablePage(requestParams), query, listWrapperClass, allowedSortProperties);
    }

    protected FlowablePage pageList(Map<String, String> requestParams, Query query,
                                    Class<? extends IListWrapper> listWrapperClass,
                                    Map<String, QueryProperty> allowedSortProperties,
                                    QueryProperty defaultDescSortProperty) {
        return pageList(getFlowablePage(requestParams), query, listWrapperClass, allowedSortProperties,
                defaultDescSortProperty);
    }

    protected FlowablePage pageList(FlowablePage flowablePage, Query query,
                                    Class<? extends IListWrapper> listWrapperClass,
                                    Map<String, QueryProperty> allowedSortProperties) {
        return pageList(flowablePage, query, listWrapperClass, allowedSortProperties, null);
    }

    protected FlowablePage pageList(FlowablePage flowablePage, Query query,
                                    Class<? extends IListWrapper> listWrapperClass,
                                    Map<String, QueryProperty> allowedSortProperties,
                                    QueryProperty defaultDescSortProperty) {
        List list = null;
        if (flowablePage == null) {
            list = query.list();
            flowablePage = FlowablePage.of(0, 0);
        } else {
            setQueryOrder(flowablePage.getOrders(), query, allowedSortProperties, defaultDescSortProperty);
            list = query.listPage((int) flowablePage.getOffset(), flowablePage.getSize());
        }
        if (listWrapperClass != null) {
            IListWrapper listWrapper = SpringContextUtils.getBean(listWrapperClass);
            list = listWrapper.execute(list);
        }

        flowablePage.setRecords(list);
        flowablePage.setTotal(query.count());
        return flowablePage;
    }

    protected List listWrapper(Class<? extends IListWrapper> listWrapperClass, List list) {
        IListWrapper listWrapper = SpringContextUtils.getBean(listWrapperClass);
        List result = listWrapper.execute(list);
        return result;
    }

    protected void setQueryOrder(List<FlowablePage.Order> orders, Query query, Map<String, QueryProperty> properties,
                                 QueryProperty defaultDescSortProperty) {
        boolean orderByDefaultDescSortProperty =
                (orders == null || orders.size() == 0 || properties.isEmpty()) && defaultDescSortProperty != null;
        if (orderByDefaultDescSortProperty) {
            query.orderBy(defaultDescSortProperty).desc();
        } else {
            if (orders != null && orders.size() > 0) {
                for (FlowablePage.Order order : orders) {
                    QueryProperty qp = properties.get(order.getProperty());
                    if (qp == null) {
                        throw new FlowableIllegalArgumentException("Value for param 'orders' is not valid, '" + order.getProperty() + "' is not a valid property");
                    }
                    query.orderBy(qp);
                    if (order.getDirection() == FlowablePage.Direction.ASC) {
                        query.asc();
                    } else {
                        query.desc();
                    }
                }
            }
        }
    }

    /**
     * 只接收字符串
     *
     * @param message
     * @param arguments
     * @return
     */
    protected String messageFormat(String message, String... arguments) {
        return MessageFormat.format(message, (Object[]) arguments);
    }

    protected boolean isShowBusinessKey(String processDefinitionId) {
        List<ValuedDataObject> dataObjects =
                repositoryService.getBpmnModel(processDefinitionId).getMainProcess().getDataObjects();
        if (dataObjects != null && dataObjects.size() > 0) {
            for (ValuedDataObject valuedDataObject : dataObjects) {
                if ("showBusinessKey".equals(valuedDataObject.getId())) {
                    if (valuedDataObject.getValue() instanceof String) {
                        return Boolean.valueOf((String) valuedDataObject.getValue());
                    } else if (valuedDataObject.getValue() instanceof Boolean) {
                        return (Boolean) valuedDataObject.getValue();
                    } else {
                        return false;
                    }
                }
            }
        }
        return false;
    }
}
