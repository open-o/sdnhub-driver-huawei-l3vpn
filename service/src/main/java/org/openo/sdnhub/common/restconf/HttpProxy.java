/*
 * Copyright 2016-2017 Huawei Technologies Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openo.sdnhub.common.restconf;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.io.IOUtils;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.exception.HttpCode;
import org.openo.sdno.util.http.HTTPRequestMessage;
import org.openo.sdno.util.http.HTTPReturnMessage;
import org.openo.sdno.util.http.HTTPSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HttpProxy class.<br>
 *
 * @author
 * @version SDNHUB 0.5 2016-6-2
 */
public class HttpProxy extends HTTPSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpProxy.class);

    private String contentType;

    /**
     * Authenticate the HTTP request .<br>
     *
     * @param authReq HTTP authentication request message
     * @param request HTTP request message
     * @return the HTTP response message
     * @since SDNHUB 0.5
     */
    @Override
    public HTTPReturnMessage restInvoke(HTTPRequestMessage authReq, HTTPRequestMessage request) {
        HTTPReturnMessage authResponse = new HTTPReturnMessage();
        HTTPReturnMessage response = new HTTPReturnMessage();
        BufferedReader br = null;
        try {
            // Parse out the token_id, and put into the response
            super.setHttpContentType(MediaType.APPLICATION_JSON);
            super.setHttpAccept(MediaType.APPLICATION_JSON);
            sendMsg(authReq, null, authResponse, true);
            if(HttpCode.isSucess(authResponse.getStatus())) {
                Map<String, String> tokenMap = new HashMap<>();
                tokenMap.put(ACCESS_TOKEN, authResponse.getToken());

                super.setHttpContentType(contentType);
                super.setHttpAccept(contentType);
                HttpURLConnection conn = sendMsg(request, tokenMap, response, false);
                LOGGER.info("HttpSender::restInvoke controller status:" + response.getStatus());
                if(HttpCode.isSucess(response.getStatus())) {
                    br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                } else {
                    br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                    LOGGER.warn("HttpSender::restInvoke controller send failed!");
                }
                processReturnMsg(response, br);
            } else {
                LOGGER.error("HttpSender::restInvoke controller auth failed!");
                throw new ServiceException("restInvoke controller auth failed! error code is :" + response.getStatus());
            }
        } catch(Exception e) {
            if(response.getStatus() == 0) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            }
            response.setBody("\"HttpSender::restInvoke controller error! \"");
            LOGGER.warn("HttpSender::restInvoke controller error! ", e);
        } finally {
            IOUtils.closeQuietly(br);
        }
        return response;
    }

    /**
     * Set the HTTP content type.<br>
     *
     * @param httpContentType is type of HTTP content
     * @since SDNHUB 0.5
     */
    @Override
    public void setHttpContentType(String httpContentType) {
        super.setHttpContentType(httpContentType);
        this.contentType = httpContentType;
    }
}
