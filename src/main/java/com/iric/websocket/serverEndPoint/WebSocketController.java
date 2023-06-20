package com.iric.websocket.serverEndPoint;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/test")
public class WebSocketController {


    /**
     * 测试保持心跳之后断开连接是否重试
     *
     * @param userId
     * @throws IOException
     */
    @PostMapping("/testClose/{userId}")
    public void testClose(@PathVariable("userId") String userId) throws IOException {
        ConcurrentHashMap<String, NoticeWebsocket> webSocketMap = NoticeWebsocket.getWebSocketMap();
        NoticeWebsocket noticeWebsocket = webSocketMap.get(userId);
        noticeWebsocket.close();
    }
}