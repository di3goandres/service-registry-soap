package com.arquitectura.client.serviceregistrationanddiscoveryclient.controller;


import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
class ServiceInstanceRestController {
    private RestTemplate restTemplate;

    @Autowired
    public ServiceInstanceRestController(RestTemplateBuilder builder, EurekaClient eurekaClient) {
        this.restTemplate = builder.build();
        this.eurekaClient = eurekaClient;
    }
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private final EurekaClient eurekaClient;

    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }
}
