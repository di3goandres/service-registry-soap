package com.arquitectura.client.serviceregistrationanddiscoveryclient.soap;


import com.arquitectura.users.ClientDetails;
import com.arquitectura.users.IdentificationType;
import com.arquitectura.users.RegisterClientRequest;
import com.arquitectura.users.RegisterClientResponse;
import com.arquitectura.users.SuccessResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.math.BigInteger;
import java.util.UUID;


@Endpoint
public class ClientRegisterController {
    // input RegisterClientRequest
    // output RegisterClientResponse
    //http://arquitectura.com/users
    //RegisterClientRequest

    @PayloadRoot(namespace = "http://arquitectura.com/users", localPart = "RegisterClientRequest")
    @ResponsePayload
    public SuccessResponse registerClient(@RequestPayload RegisterClientRequest registerClientRequest) {
        SuccessResponse registerClientResponse = new SuccessResponse();
        registerClientResponse.setMessage("Message received");
        registerClientResponse.setUuid(UUID.randomUUID().toString());
        registerClientResponse.setResultCode(new BigInteger("200"));
        return registerClientResponse;
    }
}
