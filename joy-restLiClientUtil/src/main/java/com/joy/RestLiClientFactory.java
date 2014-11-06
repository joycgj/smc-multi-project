package com.joy;

import com.linkedin.common.callback.FutureCallback;
import com.linkedin.common.util.None;
import com.linkedin.d2.balancer.D2Client;
import com.linkedin.d2.balancer.D2ClientBuilder;
import com.linkedin.r2.transport.common.Client;
import com.linkedin.r2.transport.common.bridge.client.TransportClient;
import com.linkedin.r2.transport.common.bridge.client.TransportClientAdapter;
import com.linkedin.r2.transport.http.client.HttpClientFactory;
import com.linkedin.restli.client.RestClient;
import org.apache.commons.lang.StringUtils;

import java.util.Collections;

/**
 * Created by gaojiechen on 2014/11/5.
 */
public class RestLiClientFactory {

    private static RestClient restClient = null;

    public static RestClient getRestLiClient() {
        if (restClient != null) {
            return restClient;
        }

        RestClient restClient = null;
        if (RestLiClientConfig.restLiClientUseD2) {
            final D2Client d2Client = new D2ClientBuilder()
                    .setZkHosts(RestLiClientConfig.restLiClientD2ZkAdd)
                    .setBasePath(RestLiClientConfig.restLiClientD2ZkPath)
                    .build();
            d2Client.start(new FutureCallback<None>());
            restClient = new RestClient(d2Client, "d2://");
        } else {
            String host = RestLiClientConfig.restLiServerHost;
            int port = RestLiClientConfig.restLiServerPort;
            if (StringUtils.isNotBlank(host) && port > 0) {
                HttpClientFactory http = new HttpClientFactory();
                TransportClient transportClient = http.getClient(Collections.<String, String>emptyMap());
                Client r2Client = new TransportClientAdapter(transportClient);
                restClient = new RestClient(r2Client, "http://" + host + ":" + port + "/");
            } else {
                return null;
            }
        }

        return restClient;
    }
}
