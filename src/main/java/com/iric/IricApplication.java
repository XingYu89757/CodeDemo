package com.iric;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IricApplication {
    public static void main(String[] args) {
        SpringApplication.run(IricApplication.class, args);
    }

//    private static void startWebSocketServer(ConfigurableApplicationContext context, int port) {
//        WebSocketHandler handler = new MyWebSocketHandler(); // 自定义 WebSocket 处理器
//        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
//        mapping.setUrlMap(Collections.singletonMap("/ws", handler));
//        mapping.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        WebSocketHandlerAdapter adapter = new WebSocketHandlerAdapter();
//        context.getBean(WebSocketHandlerAdapter.class);
//        try {
//            WebSocketServerFactoryBean factory = new WebSocketServerFactoryBean();
//            factory.setPort(port);
//            factory.setHandlerMappings(Collections.singletonList(mapping));
//            factory.setWebSocketHandshakeHandler(new DefaultHandshakeHandler());
//            factory.setWebSocketEnabled(true);
//            factory.afterPropertiesSet();
//            ServerSocket serverSocket = factory.getObject();
//            serverSocket.start();
//            System.out.println("WebSocket Server started on port " + port);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
