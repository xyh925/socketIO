package com.xys.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

//@Component
@Component
@PropertySource(value = {"classpath:application.properties"})
public class ConfigUtils {
	
	@Value("${jdbc.url}")
	private String url;
	
	@Value("${socketio.host}")
	private String host;
	
	@Value("${socketio.port}")
	private String port;
	

	public String getUrl() {
		return url;
	}
	

	public String getHost() {
		return host;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(String port) {
		this.port = port;
	}


	public String getPort() {
		return port;
	}
	
 
}
