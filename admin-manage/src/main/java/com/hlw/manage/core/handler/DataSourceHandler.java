package com.hlw.manage.core.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mqz
 * @description  动态数据源上下文
 * @since 2020/4/27
 */
public class DataSourceHandler {

    private static Logger logger = LoggerFactory.getLogger(DataSourceHandler.class);

    /** 数据源标识管理 */
    public static List<String> dataSourceIds = new ArrayList<String>();


    /** 本地线程维护当前变量 */
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static String getDataSourceRouterKey () {
        return contextHolder.get();
    }

    public static void setDataSourceRouterKey (String dataSourceRouterKey) {
        logger.info("切换至{}数据源", dataSourceRouterKey);
        contextHolder.set(dataSourceRouterKey);
    }

    /**设置数据源之前一定要先移除*/
    public static void removeDataSourceRouterKey () {
        contextHolder.remove();
    }

    /**判断指定DataSource当前是否存在*/
    public static boolean containsDataSource(String dataSourceId){
        return dataSourceIds.contains(dataSourceId);
    }

}
