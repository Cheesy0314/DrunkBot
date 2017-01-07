package com.drunkbot.discord.connectors;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
//import org.apache.commons
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dylan on 1/3/2017.
 */
public class WebRequest {

    public static String expander (String address) throws IOException{
            URL url = new URL(address);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection(Proxy.NO_PROXY); //using proxy may increase latency
            connection.setInstanceFollowRedirects(false);
            connection.connect();
            String expandedURL = connection.getHeaderField("Location");
            connection.getInputStream().close();
            return expandedURL;

    }
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

//        }
        conn.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;

        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }

        reader.close();

        return builder.toString();
    }

    public String doTwitchRequest (String user) throws Exception{
        StringBuilder builder = new StringBuilder();
        URL url = new URL("https://api.twitch.tv/kraken/streams/" + user);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Accept","application/vnd.twitchtv.v5+json");
        conn.setRequestProperty("Client-ID","xeqnbvxqztl3xihqnmfc4qkzjcmydc");
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
