package com.xys.call;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;

@Configuration
public class NettySocketioConfig {
	
	 	@Value("${socketio.host}")
	    private String host;
	    @Value("${socketio.port}")
	    private Integer port;
	 
	    //netty-socketio服务器
	    @Bean
	    public SocketIOServer socketIOServer() {
	        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
	        config.setPort(port);
	        config.setPingTimeout(1000);
	        config.setUpgradeTimeout(1000); 
	        config.setPingInterval(1000);
	        SocketIOServer server = new SocketIOServer(config);
	        return server;
	    }
	 
	    //用于扫描netty-socketio的注解，比如 @OnConnect、@OnEvent
	    @Bean
	    public SpringAnnotationScanner springAnnotationScanner() {
	        return new SpringAnnotationScanner(socketIOServer());
	    }

}
