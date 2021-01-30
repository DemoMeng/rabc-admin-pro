package com.hlw.manage.core.message;

import com.hlw.common.constants.CommonConstants;
import com.hlw.common.model.MessageBean;
import com.hlw.manage.core.redis.RedisService;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mqz
 * @description
 * @since 2020/6/9
 */
@Component
public class MessageDealService {
    public static void deal(MessageBean messageBean){
        List<MessageBean> list = (List<MessageBean>) RedisService.get(CommonConstants.KEY_FOR_MESSAGE_LIST);
        if(CollectionUtils.isEmpty(list)){
            list = new ArrayList<>();
        }
        list.add(messageBean);
        RedisService.set(CommonConstants.KEY_FOR_MESSAGE_LIST,list);
    }
}
