package com.iric.websocket.serverEndPoint;

import lombok.Data;

/**
 * ws通知返回对象
 */
@Data
public class NoticeWebsocketResp<T> {

    /**
     * 通知类型
     */
    private String noticeType;
    /**
     * 通知内容
     */
    private T noticeInfo;

}