package com.iric.websocket.serverEndPoint;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 使用javax做后端websocket服务
 *
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@ServerEndpoint("/notice/{userId}")
@Component
@Slf4j
public class NoticeWebsocket {

    //记录连接的客户端
    public static Map<String, Session> clients = new ConcurrentHashMap<>();

    /**
     * userId关联sid（解决同一用户id，在多个web端连接的问题）
     */
    public static Map<String, Set<String>> conns = new ConcurrentHashMap<>();
    /**
     * 保存当前yserId和当前对象信息
     */
    private static ConcurrentHashMap<String, NoticeWebsocket> websocketConcurrentHashMap = new ConcurrentHashMap<>();

    private Session session;
    private String sid = null;
    private String userId;

    /**
     * 连接
     *
     * @param session
     * @param userId
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        String tempSid = session.getId();
        this.sid = tempSid;
        this.userId = userId;
        this.session = session;
        clients.put(tempSid, session);
        session.setMaxIdleTimeout(20 * 1000);
        long maxIdleTimeout = session.getMaxIdleTimeout();
        log.info("MaxIdleTimeout:{}", maxIdleTimeout);
        Set<String> clientSet = conns.get(userId);
        if (clientSet == null) {
            clientSet = new HashSet<>();
            conns.put(userId, clientSet);
        }
        log.info(this.toString());
        clientSet.add(tempSid);
        websocketConcurrentHashMap.put(userId, this);
        log.info("DeviceWebsocket---onOpen===>id:{}--{}--连接数：{}--在线数：{}--当前设备连接数：{}", userId, tempSid, clients.size(), conns.size(), conns.get(userId).size());

    }

    public void close() throws IOException {
        this.session.close();
    }

    @OnClose
    public void onClose(Session session, @PathParam("userId") String userId) {
        String tempSid = session.getId();
        log.info(this.sid + "连接断开！");
        closeSession(tempSid, userId);
    }


    public void closeSession(String sid, String userId) {
        Session s = clients.remove(sid);
        if (s != null) {
            try {
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Set<String> clientSet = conns.get(userId);
        if (clientSet != null) {
            clientSet.remove(sid);
        }
        int currentConnectCount = clientSet.size();
        if (clientSet != null && clientSet.size() == 0) {
            conns.remove(userId);
            currentConnectCount = 0;
        }
        log.info("DeviceWebsocket---onClose===>id:--{}--{}--连接数：{}--在线数：{}--当前设备连接数：{}", userId, sid, clients.size(), conns.size(), currentConnectCount);
        //log.error("在线人数===="+clients.size());
    }

    @OnMessage
    public void sendMessage(Session session, String noticeType) throws IOException {
        log.info("接收到的message为：{}", noticeType);
        if ("ping".equalsIgnoreCase(noticeType)) {
            try {
                session.getBasicRemote().sendText("pong");
                // session.getBasicRemote().sendPong(ByteBuffer.wrap("Pong".getBytes(StandardCharsets.UTF_8)));
            } catch (IOException e) {
                // 处理异常
            }
        } else if ("close".equals(noticeType)) {
            onClose(session, userId);
        } else {
            NoticeWebsocketResp noticeWebsocketResp = new NoticeWebsocketResp();
            noticeWebsocketResp.setNoticeType(noticeType);
            sendMessage(noticeWebsocketResp);
            //    session.getBasicRemote().sendPing("ping");

        }
    }


    /**
     * 发送给所有用户
     *
     * @param noticeWebsocketResp
     */
    public static void sendMessage(NoticeWebsocketResp noticeWebsocketResp) {
        String message = JSONObject.toJSONString(noticeWebsocketResp);
        for (Session session1 : NoticeWebsocket.clients.values()) {
            try {
                session1.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据用户id发送给某一个用户
     **/
    public static void sendMessageByUserId(String userId, NoticeWebsocketResp noticeWebsocketResp) {
        if (!StringUtils.isEmpty(userId)) {
            String message = JSONObject.toJSONString(noticeWebsocketResp);
            Set<String> clientSet = conns.get(userId);
            if (clientSet != null) {
                Iterator<String> iterator = clientSet.iterator();
                while (iterator.hasNext()) {
                    String sid = iterator.next();
                    Session session = clients.get(sid);
                    if (session != null) {
                        try {
                            session.getBasicRemote().sendText(message);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        iterator.remove();
                        ;
                    }
                }
            }
        }
    }


    @OnError
    public void onError(Throwable error) {
        log.error("error");
        error.printStackTrace();
    }

    public static synchronized ConcurrentHashMap<String, NoticeWebsocket> getWebSocketMap() {
        return websocketConcurrentHashMap;
    }
}