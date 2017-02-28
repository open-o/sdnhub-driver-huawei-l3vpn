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

import static org.junit.Assert.assertEquals;

import mockit.Mock;
import mockit.MockUp;

import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.common.restconf.HttpProxy;
import org.openo.sdno.util.http.HTTPRequestMessage;
import org.openo.sdno.util.http.HTTPReturnMessage;
import org.openo.sdno.util.http.HTTPSender;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Map;

public class HttpProxyTest {

    HttpProxy httpProxy = new HttpProxy();

    @Test
    public void testSetHttpContentType() {
        httpProxy.setHttpContentType(null);
    }

    @Test
    public void testRestInvoke() {

        new MockUp<HTTPSender>() {

            @Mock
            protected HttpURLConnection sendMsg(HTTPRequestMessage requst, Map<String, String> authInfo,
                    HTTPReturnMessage response, boolean isAuth)
                    throws IOException, NoSuchProviderException, NoSuchAlgorithmException, ServiceException {
                response.setStatus(200);
                return null;
            }
        };
        HTTPRequestMessage authReq = new HTTPRequestMessage();
        HTTPRequestMessage request = new HTTPRequestMessage();
        HTTPReturnMessage response = httpProxy.restInvoke(authReq, request);
        assertEquals(response.getStatus(), 200);
    }

    @Test
    public void testRestInvokeHttpError() {
        new MockUp<HTTPSender>() {

            @Mock
            protected HttpURLConnection sendMsg(HTTPRequestMessage requst, Map<String, String> authInfo,
                    HTTPReturnMessage response, boolean isAuth)
                    throws IOException, NoSuchProviderException, NoSuchAlgorithmException, ServiceException {
                response.setStatus(500);
                return null;
            }
        };
        HTTPRequestMessage authReq = new HTTPRequestMessage();
        HTTPRequestMessage request = new HTTPRequestMessage();
        HTTPReturnMessage response = httpProxy.restInvoke(authReq, request);
        assertEquals(response.getStatus(), 500);
    }

    @Test
    public void testRestInvokeException() {
        new MockUp<HTTPSender>() {

            @Mock
            protected HttpURLConnection sendMsg(HTTPRequestMessage requst, Map<String, String> authInfo,
                    HTTPReturnMessage response, boolean isAuth)
                    throws IOException, NoSuchProviderException, NoSuchAlgorithmException, ServiceException {
                response.setStatus(200);
                return null;
            }
        };

        HTTPRequestMessage authReq = new HTTPRequestMessage();
        HTTPRequestMessage request = new HTTPRequestMessage();
        HTTPReturnMessage response = httpProxy.restInvoke(authReq, request);
        assertEquals(response.getStatus(), 200);
    }
}
