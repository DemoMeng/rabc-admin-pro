package com.hlw.common.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mqz
 * @description
 * @since 2020/4/24
 */
public class CommonConstants {

    public static final String KEY_FOR_SAVE_LOG = "ADMIN_SAVE_LOG_KEY";

    public static final String KEY_FOR_MESSAGE_LIST = "ADMIN_MESSAGE_LIST";

    /**配置的key*/
    public class Config{

        /**1:第一级  2：第二级  3：第三级*/
        public static final int LEVEL_BELONG_1 = 1;
        public static final int LEVEL_BELONG_2 = 2;
        public static final int LEVEL_BELONG_3 = 3;

        /**API*/
        public static final String KEY_API_CONFIG_1 = "API_CONFIG_1";
        public static final String KEY_API_CONFIG_2 = "API_CONFIG_2";
        public static final String KEY_API_CONFIG_3 = "API_CONFIG_3";


        /**CONTRACT*/
        public static final String KEY_CONTRACT_CONFIG_1 = "CONTRACT_CONFIG_1";
        public static final String KEY_CONTRACT_CONFIG_2 = "CONTRACT_CONFIG_2";
        public static final String KEY_CONTRACT_CONFIG_3 = "CONTRACT_CONFIG_3";

        /**类型*/
        public class Type{
            public static final String API = "API";
            public static final String CONTRACT = "CONTRACT";
        }

        /**启用状态 （ 0 ：启用  1：未启用）*/
        public class Status{
            public static final int ON_0 = 0;
            public static final int OFF_ = 1;
        }

    }


    public class Current{
        /**当前用户角色*/
        public static final String CURRENT_ROLE = "ADMIN_CURRENT_ROLE_";
        /**当前用户角色*/
        public static final String CURRENT_ROLE_ID_LIST = "ADMIN_CURRENT_ROLE_ID_LIST_";
        /**当前用户key*/
        public static final String CURRENT_USER = "ADMIN_CURRENT_USER_";
        /**当前用户id对应的token*/
        public static final String CURRENT_USER_T = "ADMIN_CURRENT_USER_T_";
        /**角色id对应的菜单缓存*/
        public static final String ROLE_MENU_LIST = "ADMIN_ROLE_MENU_LIST_";
        /**角色关联编辑回显*/
        public static final String ROLE_MENU_SAVE_LIST = "ADMIN_ROLE_MENU_SAVE_LIST";
        /**角色关联编辑回显*/
        public static final String ROLE_MENU_EDIT_LIST = "ADMIN_ROLE_MENU_EDIT_LIST_";
        /**角色关联编辑回显*/
        public static final String MENU_CHILD_LIST = "ADMIN_MENU_CHILD_LIST_";
    }

    /**删除标识-删除*/
    public static final int DELETE_FLAG_DELETED = 1;
    /**删除标识-正常*/
    public static final int DELETE_FLAG_NORMAL = 0;

    /**新增*/
    public static final String NEW = "新增";
    /**编辑*/
    public static final String UPDATE = "编辑";

    public class HlwMangeMenu{

        /**父级id为0  （代表目录 否则就是菜单）*/
        public static final String PARENT_ID_ZERO = "0";
        /**是否显示 ：显示*/
        public static final int SHOW_FLAG_YES = 0;
        /**是否显示 ：隐藏*/
        public static final int SHOW_FLAG_NO =1;

        /*********归属类别*********/
        /**1：一级菜单*/
        public static final int BELONG_TYPE_1 = 1;
        /**2：二级菜单*/
        public static final int BELONG_TYPE_2 = 2;
        /**3：三级菜单*/
        public static final int BELONG_TYPE_3 = 3;
        /*********归属类别*********/
    }


    public class HlwManageRole{
        /**超级角色-标识*/
        public static final String SUPER_ADMIN = "SA";
        /**普通角色-标识*/
        public static final String COMMON_ROLE = "CR";

        /**是否使用中-是*/
        public static final int USABLE_YES_0 = 0;
        /**是否使用中-否*/
        public static final int USABLE_NO_1 = 1;
    }





    public class HlwManageUser{

        /**创建用户初始化密码*/
        public static final String INIT_PWD = "123456";

        /**启用禁用标识*/
        public class EnableFlag{
            /**禁用*/
            public static final String OFF = "off";
            /**启用*/
            public static final String ON = "on";
            /**禁用-database*/
            public static final int OFF_NO = 1;
            /**启用*/
            public static final int ON_YES = 0;
        }

        /**
         * 删除状态
         */
        public class DeleteFlag{
            /**删除*/
            public static final int DELETED = 1;
            /**正常*/
            public static final int NORMAL = 0;
        }

        /**
         * 登录许可
         */
        public class LoginFlag{
            /**允许*/
            public static final int ALLOW = 0;
            /**不允许*/
            public static final int NOT_ALLOW = 1;
        }


    }

    public class Sms{

        /**短信登录*/
        public static final String LOGIN_TYPE_SMS = "sms";
        /**密码登录*/
        public static final String LOGIN_TYPE_PWD = "pwd";
        /**过期时间 秒*/
        public static final long EXPIRED_TIME = 300;
        /**redis的key 登录*/
        public static final String KEY_LOGIN = "ADMIN_EXPIRED_LOGIN_";
        /**redis的key 忘记密码*/
        public static final String KEY_RE_PASSWORD = "ADMIN_KEY_RE_PASSWORD_";
        /**api地址*/
        public static final String API = "XXXXX";
        /**登录验证码消息体前*/
        public static final String LOGIN_START = "XXXX";
        /**登录验证码消息体后*/
        public static final String LOGIN_END = "XXXX";
        /**重置密码验证码消息体前*/
        public static final String RE_PASSWORD_START = "XXXX";
        /**重置密码验证码消息体后*/
        public static final String RE_PASSWORD_END = "XXXX";
        /**创建用户消息*/
        public static final String INIT_ACCOUNT_SAAS_START = "XXXX";
        /**创建用户消息*/
        public static final String INIT_ACCOUNT_SAAS_MIDDLE = "XXX";
        /**创建用户消息*/
        public static final String INIT_ACCOUNT_SAAS_END = "XXXX";
        /**创建用户消息*/
        public static final String INIT_ACCOUNT_OPEN_START = "XXXX";
        /**创建用户消息*/
        public static final String INIT_ACCOUNT_OPEN_MIDDLE = "XXXX";
        /**创建用户消息*/
        public static final String INIT_ACCOUNT_OPEN_END = "XXXX";
    }



}
