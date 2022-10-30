package com.arquitectura.client.serviceregistrationanddiscoveryclient.soap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.List;


@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
    //Message Dispatcher Servlet
    //Application Contexy
    //Url -> /ws/*

    @Bean
    ServletRegistrationBean messageDispatcherServlet(ApplicationContext context) {
        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setApplicationContext(context);
        messageDispatcherServlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(messageDispatcherServlet, "/ws/*");
    }

    //ws/client.wsdl
        //Namespaces - http://arquitectura.com/users
        // PortType - UsersPort
    // client-details.xsd
    @Bean(name="users")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema clientSchema){
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName("UserPort");
        definition.setTargetNamespace("http://arquitectura.com/users");
        definition.setLocationUri("/ws");
        definition.setSchema(clientSchema());
        return definition;
    }


    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        CustomValidationInterceptor validatingInterceptor = new CustomValidationInterceptor();
        validatingInterceptor.setValidateRequest(true);
        validatingInterceptor.setValidateResponse(true);
        validatingInterceptor.setXsdSchema(clientSchema());
        interceptors.add(validatingInterceptor);
    }

    @Bean
    public XsdSchema clientSchema(){
        return  new SimpleXsdSchema(new ClassPathResource("client-details.xsd"));
    }

}
