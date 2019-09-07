package com.leo.boot.ws.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.leo.boot.ws.domain.User;

/**
 * Cxf默认所有webservice发布在/services/*下，
 * http://localhost:8080/services查看所有已发布的webservice
 * 
 * @author Leo
 * @date 2019/07/18
 */
@Configuration
public class CxfConfig {

	/**
	 * EndpointImpl默认ThreadDefaultBus无法生效，注入的是{@link SpringBus}
	 * 
	 * @param bus
	 * @param user
	 * @return
	 */
	@Bean
	public Endpoint endpoint(Bus bus, User user) {
		Endpoint endpoint = new EndpointImpl(bus, user);
		endpoint.publish("/user");
		return endpoint;
	}

	/**
	 * 客户端调用代理对象
	 * @return
	 */
	@Bean("userClient")
	public User user() {
		JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
		jaxWsProxyFactoryBean.setAddress("http://localhost:8080/services/user");
		jaxWsProxyFactoryBean.setServiceClass(User.class);
		return (User) jaxWsProxyFactoryBean.create();
	}
	
}
