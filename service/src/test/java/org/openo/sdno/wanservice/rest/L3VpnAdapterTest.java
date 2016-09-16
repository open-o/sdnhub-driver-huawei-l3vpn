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

package org.openo.sdno.wanservice.rest;

import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;

public class L3VpnAdapterTest {

    L3VpnAdapter l3VpnAdapter = new L3VpnAdapter();

    @Test
    public void testAdminStatusUpdate() throws ServiceException {

       /* new MockUp<ROAInputStreamParser>() {

            @Mock
            public ServiceParasInfo fromJson(String str, Class<ServiceParasInfo> clazz) throws ServiceException {
                return new ServiceParasInfo("test", "test", "test", null, "test");
            }
        };*/

        l3VpnAdapter.getResUri();
        l3VpnAdapter.getService();
        l3VpnAdapter.setService(null);
        // l3VpnAdapter.adminStatusUpdate(new HttpServletRequest());
    }

}
