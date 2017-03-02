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

package org.openo.sdnhub.common.services;

import mockit.Mock;
import mockit.MockUp;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.openo.baseservice.roa.util.restclient.RestfulParametes;
import org.openo.baseservice.roa.util.restclient.RestfulResponse;
import org.openo.sdno.framework.container.resthelper.RestfulProxy;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class DriverRegistrationListenerTest {

    DriverRegistrationListener listener = new DriverRegistrationListener();

    @Test
    public void contextDestroyedTestNormal() {

        new MockUp<RestfulProxy>() {

            @Mock
            public RestfulResponse delete(String url, RestfulParametes restParametes) {
                RestfulResponse resp = new RestfulResponse();
                resp.setStatus(200);
                return resp;
            }
        };
        listener.contextDestroyed(null);
    }

    @Test
    public void contextDestroyedTestAbnormal() {

        new MockUp<RestfulProxy>() {

            @Mock
            public RestfulResponse delete(String url, RestfulParametes restParametes) {
                RestfulResponse resp = new RestfulResponse();
                resp.setStatus(500);
                return resp;
            }
        };
        listener.contextDestroyed(null);
    }

    @Test
    public void contextInitializedTestNormal() {
        new MockUp<File>() {

            @Mock
            public boolean exists() {
                return false;
            }
        };
        listener.contextInitialized(null);
    }

    @Test
    public void contextInitializedTestNormalCaseIpNull() {

        new MockUp<RestfulProxy>() {

            @Mock
            public RestfulResponse post(String url, RestfulParametes restParametes) {
                RestfulResponse resp = new RestfulResponse();
                resp.setStatus(200);
                return resp;
            }
        };

        new MockUp<File>() {

            @Mock
            public boolean exists() {
                return true;
            }
        };

        new MockUp<Files>() {

            @Mock
            public byte[] readAllBytes(Path path) {
                byte[] myvar = "Value".getBytes();
                return myvar;

            }
        };

        new MockUp<ObjectMapper>() {

            @SuppressWarnings("unchecked")
            @Mock
            public <T> T readValue(byte[] src, Class<T> valueType) {

                Map<String, Map<String, String>> dmRegistrationBodyMap = new HashMap<String, Map<String, String>>();
                Map<String, String> driverInfoMap = new HashMap<String, String>();
                driverInfoMap.put("instanceID", "usb12345");
                dmRegistrationBodyMap.put("driverInfo", driverInfoMap);
                return (T)dmRegistrationBodyMap;
            }
        };

        listener.contextInitialized(null);
    }

    @Test
    public void contextInitializedTestNormalCaseIpNotNull() {

        new MockUp<RestfulProxy>() {

            @Mock
            public RestfulResponse post(String url, RestfulParametes restParametes) {
                RestfulResponse resp = new RestfulResponse();
                resp.setStatus(200);
                return resp;
            }
        };

        new MockUp<File>() {

            @Mock
            public boolean exists() {
                return true;
            }
        };

        new MockUp<Files>() {

            @Mock
            public byte[] readAllBytes(Path path) {

                byte[] myvar = "Value".getBytes();
                return myvar;
            }
        };

        new MockUp<ObjectMapper>() {

            @SuppressWarnings("unchecked")
            @Mock
            public <T> T readValue(byte[] src, Class<T> valueType) {

                Map<String, Map<String, String>> dmRegistrationBodyMap = new HashMap<String, Map<String, String>>();
                Map<String, String> driverInfoMap = new HashMap<String, String>();
                driverInfoMap.put("instanceID", "usb12345");
                driverInfoMap.put("ip", "10.172.13.12");
                dmRegistrationBodyMap.put("driverInfo", driverInfoMap);
                return (T)dmRegistrationBodyMap;
            }
        };

        listener.contextInitialized(null);
    }
}
