package com.joy;

import com.linkedin.r2.RemoteInvocationException;
import com.linkedin.restli.client.CreateRequest;
import com.linkedin.restli.client.Response;
import com.linkedin.restli.client.ResponseFuture;
import com.linkedin.restli.client.RestClient;
import com.linkedin.restli.common.HttpStatus;
import org.apache.commons.lang.math.NumberUtils;

/**
 * Created by gaojiechen on 2014/11/6.
 */
public class RestLiClientUtil {
    public static RestClient restClient = RestLiClientFactory.getRestLiClient();

    public static long createReturnId(CreateRequest createRequest) {
        try {
            ResponseFuture rf = restClient.sendRequest(createRequest);
            if (checkResponse(rf.getResponse())) {
                return NumberUtils.toLong(rf.getResponse().getHeader("X-LinkedIn-Id"), 0);
            }
        } catch (RemoteInvocationException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private static boolean checkResponse(Response response) {
        if (response.getStatus() == HttpStatus.S_200_OK.getCode()) {
            return true;
        }
        return false;
    }
}
