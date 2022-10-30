package com.arquitectura.client.serviceregistrationanddiscoveryclient.soap;

import org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor;

import javax.xml.namespace.QName;

public class CustomValidationInterceptor extends PayloadValidatingInterceptor {
    @Override
    public QName getDetailElementName() {
        return new QName("http://arquitectura.com", "error", "my");
    }
}
