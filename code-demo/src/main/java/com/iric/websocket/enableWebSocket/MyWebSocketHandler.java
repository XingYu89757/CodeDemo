package com.iric.websocket.enableWebSocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

@Component
@Slf4j
public class MyWebSocketHandler implements WebSocketHandler {
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String path = session.getUri().getPath();
        log.info("path{}", path);
        log.info("SpringSocketHandle, 收到新的连接: " + session.getId());
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String msg = "SpringSocketHandle, 连接：" + session.getId() + "，已收到消息。";
        session.sendMessage(new TextMessage(msg));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.info("WS 连接发生错误");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        log.info("WS 关闭连接");
    }

    // 支持分片消息
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
