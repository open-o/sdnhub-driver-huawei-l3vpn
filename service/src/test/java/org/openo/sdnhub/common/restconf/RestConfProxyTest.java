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
import org.openo.sdnhub.common.services.ESRutil;
import org.openo.sdno.util.http.HTTPReturnMessage;

import java.util.HashMap;
import java.util.Map;

public class RestConfProxyTest {

    @Test
    public void getTestNormalJson() throws ServiceException {
        new MockUp<ESRutil>() {
            @Mock
            public  Map getControllerDetails(String ctrlUuid) throws ServiceException {
                Map<String,String> returnMap = new HashMap<>();
                returnMap.put("userName", "test");
                returnMap.put("password", "test123");
                returnMap.put("url", "http://127.0.0.1:8080");
                return returnMap;
            }
        };
        HTTPReturnMessage message = RestConfProxy.get(ContentType.JSON, "http://127.0.0.1", "1992012");
        assertEquals(message.getStatus(),200);
    }

    @Test
    public void getTestNormalXml() throws ServiceException {
        new MockUp<ESRutil>() {
            @Mock
            public  Map getControllerDetails(String ctrlUuid) throws ServiceException {
                Map<String,String> returnMap = new HashMap<>();
                returnMap.put("userName", "test");
                returnMap.put("password", "test123");
                returnMap.put("url", "http://127.0.0.1:8080");
                return returnMap;
            }
        };
        HTTPReturnMessage message = RestConfProxy.get(ContentType.XML, "http://127.0.0.1", "1992012");
        assertEquals(message.getStatus(),200);
    }

    @Test
    public void getTestNormalInvalidPort() throws ServiceException {
        new MockUp<ESRutil>() {
            @Mock
            public  Map getControllerDetails(String ctrlUuid) throws ServiceException {
                Map<String,String> returnMap = new HashMap<>();
                returnMap.put("userName", "test");
                returnMap.put("password", "test123");
                returnMap.put("url", "http://127.0.0.1:0");
                return returnMap;
            }
        };
        HTTPReturnMessage message = RestConfProxy.get(ContentType.XML, "http://127.0.0.1", "1992012");
        assertEquals(message.getStatus(),200);
    }
}
