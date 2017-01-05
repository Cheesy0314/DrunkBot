package com.drunkbot.discord;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
//import org.apache.commons
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dylan on 1/3/2017.
 */
public class WebRequest {
    private Map<String,String> headers;

    public void setHeaders (Map input) {
        headers = input;
    }

    public void setHeader(String key, String value) {
        if (headers == null) {
            headers = new HashMap<String, String>();
        }

        headers.put(key,value);
    }
    public String doRequest (String target) throws Exception {
        StringBuilder builder = new StringBuilder();
        URL url = new URL(target);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        for (String key : headers.keySet()) {
            conn.setRequestProperty(key,headers.get(key));
        }
        conn.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;

        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }

        reader.close();

        return builder.toString();
    }
}
