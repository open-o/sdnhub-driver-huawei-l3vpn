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

import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.model.servicemodel.brs.Device;
import org.openo.sdno.wanvpn.inventory.sdk.util.InventoryProxy;

import mockit.Mock;
import mockit.MockUp;

public class RestConfProxyTest {

    @Test
    public void testBasic() throws ServiceException {

        new MockUp<InventoryProxy>() {

            @Mock
            public Device getControllerDevice(String arg0) throws ServiceException {
                Device device = new Device();
                device.setPort(123);
                return device;
            }
        };
        RestConfProxy.get(null, null, null);
        RestConfProxy.del(null, null, null);
        RestConfProxy.post(null, null, null, null);
        RestConfProxy.put(null, null, null, null);
    }

    @Test
    public void testBasicBranch() throws ServiceException {

        new MockUp<InventoryProxy>() {

            @Mock
            public Device getControllerDevice(String arg0) throws ServiceException {
                return new Device();
            }
        };
        RestConfProxy.get(ContentType.XML, null, null);
        RestConfProxy.del(ContentType.XML, null, null);
        RestConfProxy.post(ContentType.XML, null, null, null);
        RestConfProxy.put(ContentType.XML, null, null, null);
    }
}
