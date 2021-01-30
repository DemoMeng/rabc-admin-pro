package com.hlw.manage.core.async;

import com.hlw.common.constants.CommonConstants;
import com.hlw.common.model.MessageBean;
import com.hlw.manage.core.redis.RedisService;
import com.hlw.manage.system.entity.HlwManageLog;
import com.hlw.manage.system.entity.HlwManageUser;
import com.hlw.manage.system.service.IHlwManageLogService;
import com.hlw.manage.system.service.IHlwManageUserRoleService;
import com.hlw.manage.system.service.IHlwManageUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author mqz
 * @description
 * @since 2020/5/30
 */
@Component
@EnableScheduling
@EnableAsync
public class ScheduleTask {

    private static final Logger log = LoggerFactory.getLogger(ScheduleTask.class);

    @Autowired
    private IHlwManageUserService userService;

    @Autowired
    private IHlwManageUserRoleService userRoleService;

    @Autowired
    private IHlwManageLogService logService;

    @Async
    @Scheduled(cron = "0/60 * *  * * ? ") // 每60秒执行
    public void autoLog2(){
        List<MessageBean> mbList = (List<MessageBean>) RedisService.get(CommonConstants.KEY_FOR_MESSAGE_LIST);
        List<MessageBean> left = new ArrayList<>();
        if(mbList != null && mbList.size() >0) {
            log.info("【信息】执行定时任务..."+mbList.size());
            for (MessageBean model : mbList) {
                HlwManageUser user = userService.getById(model.getUserId());
                if (user != null) {
                    HlwManageLog hlwManageLog = new HlwManageLog()
                            .setIp(model.getIp())
                            //.setRequestMethod(request.getMethod())
                            .setCreateDate(model.getCreateDate())
                            //.setRequestUri(request.getRequestURL().toString())
                            .setOperator(user.getId())
                            .setOperatorName(user.getName())
                            //.setRequestParams(WebCommonUtils.getRequestParams())
                            .setOperateAccount(user.getLoginName())
                            .setOperatorRole(userRoleService.getByUserId(user.getId()))
                            .setDescription(model.getDescription())
                            .setResult(model.getResult());
                    if(logService.save(hlwManageLog)) {
                        log.info("*****************消息保存成功：" + hlwManageLog.getId() + " ***************");
                        continue;
                    }
                    /**redis消息持久化失败，重新把消息加入队列*/
                    log.info("*****************消息保存未能成功：" + model.getUserId()+model.getDescription() + " ***************");
                    left.add(model);
                }
            }
            RedisService.del(CommonConstants.KEY_FOR_MESSAGE_LIST);
            RedisService.set(CommonConstants.KEY_FOR_MESSAGE_LIST,left);
         }
    }
}

