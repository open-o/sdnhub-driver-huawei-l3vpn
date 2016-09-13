/*
 * Copyright 2016 Huawei Technologies Co., Ltd.
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

package org.openo.sdno.common.restconf;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.baseservice.roa.util.restclient.RestfulParametes;
import org.openo.baseservice.roa.util.restclient.RestfulResponse;
import org.openo.sdno.framework.container.resthelper.RestfulProxy;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.util.http.HTTPRequestMessage;

import mockit.Mock;
import mockit.MockUp;

public class HttpProxyTest {

    HttpProxy httpProxy = new HttpProxy();

    @Test
    public void testSetHttpContentType() {
        httpProxy.setHttpContentType(null);
    }

    @Test
    public void testRestInvoke() {
        new MockUp<JsonUtil>() {

            @Mock
            public Map<String, String> fromJson(String arg0, Class<T> arg1) {
                Map<String, String> map = new HashMap<>();
                map.put("key", "value");
                return map;
            }
        };

        new MockUp<RestfulProxy>() {

            @Mock
            RestfulResponse post(String uri, RestfulParametes restParametes) throws ServiceException {
                RestfulResponse restful = new RestfulResponse();
                restful.setStatus(200);
                return restful;
            }
        };
        HTTPRequestMessage authReq = new HTTPRequestMessage();
        HTTPRequestMessage request = new HTTPRequestMessage();
        httpProxy.restInvoke(authReq, request);
    }

    @Test
    public void testRestInvokeException() {
        new MockUp<JsonUtil>() {

            @Mock
            public Map<String, String> fromJson(String arg0, Class<T> arg1) {
                Map<String, String> map = new HashMap<>();
                map.put("key", "value");
                return map;
            }
        };

        new MockUp<RestfulProxy>() {

            @Mock
            RestfulResponse post(String uri, RestfulParametes restParametes) throws ServiceException {
                RestfulResponse restful = new RestfulResponse();
                restful.setStatus(504);
                return restful;
            }
        };
        HTTPRequestMessage authReq = new HTTPRequestMessage();
        HTTPRequestMessage request = new HTTPRequestMessage();
        httpProxy.restInvoke(authReq, request);
    }
}
