package com.hlw.common.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author mqz
 * @description
 * @since 2020/5/30
 */
@Data
@Accessors(chain =true)
public class MessageBean {

    private Long userId;
    private String description;
    private String result;
    private String ip;
    private Date createDate;

}
