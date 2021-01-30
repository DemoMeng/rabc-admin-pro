package com.hlw.manage.core.message;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.github.pagehelper.StringUtil;
import com.hlw.common.constants.CommonConstants;
import com.hlw.common.model.vo.system.ForLog;
import com.hlw.common.utils.WebCommonUtils;
import com.hlw.manage.core.CurrentTools;
import com.hlw.manage.core.handler.DataSourceHandler;
import com.hlw.manage.core.redis.RedisService;
import com.hlw.manage.system.entity.HlwManageLog;
import com.hlw.manage.system.entity.HlwManageUser;
import com.hlw.manage.system.service.IHlwManageLogService;
import com.hlw.manage.system.service.IHlwManageUserRoleService;
import com.hlw.manage.system.service.IHlwManageUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author mqz
 * @description 消息消费者
 * @since 2020/5/11
 */

@Component
public class MessageConsumer implements MessageListener {

    private final static Logger log = LoggerFactory.getLogger(MessageConsumer.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private IHlwManageLogService logService;

    @Autowired
    private IHlwManageUserService userService;

    @Autowired
    private IHlwManageUserRoleService userRoleService;

    @Override
    public void onMessage(Message message, byte[] bytes) {

        log.info("当前数据源为："+DataSourceHandler.getDataSourceRouterKey());
        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
        String msg = serializer.deserialize(message.getBody());
        if(!StringUtils.isEmpty(msg)){
            String[] result = trimFL(msg,'"').split(",");
            HlwManageUser user = userService.getById(Long.valueOf(result[0]));
            if(user != null){
                HlwManageLog hlwManageLog = new HlwManageLog()
                        .setIp(result[3])
                        //.setRequestMethod(request.getMethod())
                        .setCreateDate(new Date())
                        //.setRequestUri(request.getRequestURL().toString())
                        .setOperator(user.getId())
                        .setOperatorName(user.getName())
                        //.setRequestParams(WebCommonUtils.getRequestParams())
                        .setOperateAccount(user.getLoginName())
                        .setOperatorRole(userRoleService.getByUserId(user.getId()))
                        .setDescription(result[1])
                        .setResult(result[2]);
                logService.save(hlwManageLog);
                log.info("*****************消息保存成功："+hlwManageLog.getId()+" ***************");
            }
        }

//        RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
//        String msg = serializer.deserialize(message.getBody());
//        if(!StringUtils.isEmpty(msg)){
//            System.out.println(msg);
//            msg = trimFL(msg,'"').replace("\"","'");
//            System.out.println(msg);
//            JSON json = JSONUtil.parse(msg);
//            ForLog log = JSONUtil.toBean(json.toString(),ForLog.class);
//            if (log != null){
//                HlwManageUser current = userService.getById(log.getCurrentId());
//                String name = userRoleService.getByUserId(current.getId());
//                System.out.println(current);
//                System.out.println(name);
//            }
//        }
    }
    /**
     * 去除首尾字符
     * @param source
     * @param element
     * @return
     */
    public String trimFL(String source,char element){
        boolean beginIndexFlag = true;
        boolean endIndexFlag = true;
        do{
            int beginIndex = source.indexOf(element) == 0 ? 1 : 0;
            int endIndex = source.lastIndexOf(element) + 1 == source.length() ? source.lastIndexOf(element) : source.length();
            source = source.substring(beginIndex, endIndex);
            beginIndexFlag = (source.indexOf(element) == 0);
            endIndexFlag = (source.lastIndexOf(element) + 1 == source.length());
        } while (beginIndexFlag || endIndexFlag);
        return source;
    }
}
