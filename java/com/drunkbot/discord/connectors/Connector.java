package com.drunkbot.discord.connectors;

import java.util.*;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.client.methods.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class Connector {
    private String urlLink;
    private String requestData;
    final static Logger logger = LogManager.getLogger(Connector.class.getName());
    private Map<String, String> headers;
    private String method = "POST";


    public Connector(String url, String jsonData, Map<String, String> myHeaders) {
        urlLink = url;
        requestData = jsonData;
        headers = myHeaders;

    }

    public Connector(String url, String jsonData, Map<String, String> myHeaders, String method) {
        urlLink = url;
        requestData = jsonData;
        headers = myHeaders;
        this.setMethod(method);
    }

    public void setMethod (String newMethod) {
        this.method = newMethod;
    }

    public String connect() throws Exception {
        try {

            System.out.println("Connecting");
            HttpClient httpclient = HttpClients.createDefault();

            if (method.equalsIgnoreCase("GET")) {
                HttpGet request = new HttpGet(urlLink);
                for (String headerName : headers.keySet()) {
                    String headerValue = headers.get(headerName);
                    request.setHeader(headerName, headerValue);
                }

                HttpResponse response = httpclient.execute(request);
                HttpEntity entity = response.getEntity();

                return EntityUtils.toString(entity);
            } else if (method.equalsIgnoreCase("PUT")) {
                HttpPut request = new HttpPut(urlLink);
                request.setEntity(new StringEntity(requestData));

                for (String headerName : headers.keySet()) {
                    String headerValue = headers.get(headerName);
                    request.setHeader(headerName, headerValue);
                }

                HttpResponse response = httpclient.execute(request);
                HttpEntity entity = response.getEntity();

                return EntityUtils.toString(entity);
            } else if (method.equalsIgnoreCase("DELETE")) {
                HttpDelete request = new HttpDelete(urlLink);
                for (String headerName : headers.keySet()) {
                    String headerValue = headers.get(headerName);
                    request.setHeader(headerName, headerValue);
                }

                HttpResponse response = httpclient.execute(request);
                HttpEntity entity = response.getEntity();

                return EntityUtils.toString(entity);
            } else if (method.equals("PATCH")) {
                HttpPatch request = new HttpPatch(urlLink);
                request.setEntity(new StringEntity(requestData));

                for (String headerName : headers.keySet()) {
                    String headerValue = headers.get(headerName);
                    request.setHeader(headerName, headerValue);
                }

                HttpResponse response = httpclient.execute(request);
                HttpEntity entity = response.getEntity();

                return EntityUtils.toString(entity);
            } else {
                HttpPost request = new HttpPost(urlLink);
                request.setEntity(new StringEntity(requestData));

                for (String headerName : headers.keySet()) {
                    String headerValue = headers.get(headerName);
                    request.setHeader(headerName, headerValue);
                }

                HttpResponse response = httpclient.execute(request);
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity);
            }

        } catch (Exception e) {
            logger.trace(ExceptionUtils.getStackTrace(e));
            throw new Exception("Error in connection, could not complete transaction", e);
        }
    }
}
