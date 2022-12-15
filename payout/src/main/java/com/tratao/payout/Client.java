package com.tratao.payout;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.tratao.payout.models.*;
import com.tratao.xcore.BaseClient;
import com.tratao.xcore.Config;
import com.tratao.xcore.request.RequestMethod;
import com.tratao.xcore.request.RequestResult;
import com.tratao.xcore.sign.RSASign;

import java.util.HashMap;
import java.util.Map;

public class Client {
    private Config config;
    private BaseClient baseClient;
    private HashMap<String, String> headers;
    private String host = "https://api.xcurerncy.com/payout";
    private boolean sandbox;
    private HashMap<String, Integer> retries;

    public void setSandbox(boolean sandbox) {
        this.sandbox = sandbox;
        if (sandbox) {
            host = "https://api-sandbox.xcurrency.com/payout";
        } else {
            host = "https://api.xcurrency.com/payout";
        }
    }

    public Client(Config config) {
        this.config = config;
        baseClient = BaseClient.getInstance();
        headers = Maps.newHashMap(ImmutableMap.of("appKey", config.getAppKey()));
        retries = new HashMap<>();
    }

    /**
     * To get token and auto set headers of token
     * @return response
     */
    public RequestResponse<String> getToken() {
        String uri = host + "/oauth/token";
        GetTokenRequest request = new GetTokenRequest();
        request.setSecretKey(config.getSecretKey());
        request.setAppKey(config.getAppKey());

        String body = JSON.toJSONString(request);
        headers.put("sign", RSASign.sign(body, config.getPrivateKey()));

        try {
            RequestResult result = baseClient.makeRequest(uri, RequestMethod.POST, null, null, body);
            if (result.getStatusCode() == 200) {
                RequestResponse<String> response = JSON.parseObject(result.getContent(), new TypeReference<RequestResponse<String>>(){});

                // set header token
                headers.put("token", response.getData());

                return response;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RequestResponse<>("-1", "request error");
    }

    public RequestResponse<GetRateResponseData> getRate(GetRateRequest request) {
        String uri = host + "/payment/query/price";
        getToken();

        String body = JSON.toJSONString(request);
        headers.put("sign", RSASign.sign(body, config.getPrivateKey()));

        try {
            RequestResult result = baseClient.makeRequest(uri, RequestMethod.POST, null, headers, body);

            if (result.getStatusCode() == 200) {
                RequestResponse<GetRateResponseData> response = JSON.parseObject(result.getContent(), new TypeReference<RequestResponse<GetRateResponseData>>(){});

                return response;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new RequestResponse<>("-1", "request error");
    }

    public RequestResponse<CreateTransferResponseData> createTransfer(CreateTransferRequest request) {


        return new RequestResponse<>();
    }

}
