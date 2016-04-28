package com.caozj.framework.chat.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.caozj.framework.chat.handler.ChatHandler;
import com.caozj.framework.chat.interceptor.ChatInterceptor;

/**
 * websocket配置类(因为代码生成器直接使用ClassPathXmlApplicationContext获取bean,如果@EnableWebMvc打开就会报错
 * ，所以在单独使用代码生成器的时候，注释掉这里；但是直接使用服务器部署运行时ok的,所以当需要使用websocket的时候，把注释去掉)
 * 
 * @author caozj
 * 
 */
@Configuration
// @EnableWebMvc
@EnableWebSocket
public class ChatConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

	private static final Log logger = LogFactory.getLog(ChatConfig.class);

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(chatHandler(), "/websck").addInterceptors(new ChatInterceptor());
		registry.addHandler(chatHandler(), "/sockjs/websck").addInterceptors(new ChatInterceptor()).withSockJS();
		logger.info("web socket registry success");
	}

	@Bean
	public ChatHandler chatHandler() {
		return new ChatHandler();
	}

}
