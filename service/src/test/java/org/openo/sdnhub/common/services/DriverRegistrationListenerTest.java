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

import org.junit.Test;
import org.openo.baseservice.roa.util.restclient.RestfulParametes;
import org.openo.baseservice.roa.util.restclient.RestfulResponse;
import org.openo.sdno.framework.container.resthelper.RestfulProxy;

import mockit.Mock;
import mockit.MockUp;

public class DriverRegistrationListenerTest {

    DriverRegistrationListener listener = new DriverRegistrationListener();

    @Test
    public void contextDestroyedTestNormal(){

        new MockUp<RestfulProxy>() {
            @Mock
            public RestfulResponse delete(String url, RestfulParametes restParametes){
                RestfulResponse resp = new RestfulResponse();
                resp.setStatus(200);
                return resp;
            }
        };
        listener.contextDestroyed(null);
    }

    @Test
    public void contextDestroyedTestAbnormal(){

        new MockUp<RestfulProxy>() {
            @Mock
            public RestfulResponse delete(String url, RestfulParametes restParametes){
                RestfulResponse resp = new RestfulResponse();
                resp.setStatus(500);
                return resp;
            }
        };
        listener.contextDestroyed(null);
    }

    @Test
    public void contextInitializedTestNormal(){
        listener.contextInitialized(null);
    }
}
