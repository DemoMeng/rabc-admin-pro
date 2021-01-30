package com.hlw.manage.core.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author mqz
 * @description
 * @since 2020/4/27
 */
public class RoutingDataSource extends AbstractRoutingDataSource {

    /**匹配以定位数据源，配置文件*/
    @Override
    protected Object determineCurrentLookupKey() {
        /**String dataSourceName = DataSourceHandler.getDataSourceRouterKey();*/
        /**logger.info("当前数据源是：{}", dataSourceName);*/
        return DataSourceHandler.getDataSourceRouterKey();
    }
}
