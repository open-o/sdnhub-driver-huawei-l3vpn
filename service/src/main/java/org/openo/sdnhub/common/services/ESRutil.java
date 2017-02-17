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

import java.util.Map;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.baseservice.roa.util.restclient.RestfulParametes;
import org.openo.baseservice.roa.util.restclient.RestfulResponse;
import org.openo.sdno.framework.container.resthelper.RestfulProxy;
import org.openo.sdno.framework.container.util.JsonUtil;

/**
 * Utility class to access External system register(ESR).
 * <br/>
 * <p>
 * </p>
 *
 * @author
 * @version SDNO 0.5 September 15, 2016
 */
public class ESRutil {

    /**
     * Queries ESR for controller details and returns contoller's properties as map.
     * <br/>
     *
     * @param ctrlUuid controller uuid
     * @return the map of controller's properties
     * @throws ServiceException
     * @since SDNHUB 0.5
     */

    private ESRutil(){

    }

    public static Map getControllerDetails(String ctrlUuid) throws ServiceException {
        String esrurl = "/openoapi/extsys/v1/sdncontrollers/" + ctrlUuid;
        final RestfulParametes restfulParametes = new RestfulParametes();
        RestfulResponse response = RestfulProxy.get(esrurl, restfulParametes);

        return JsonUtil.fromJson(response.getResponseContent(), Map.class);

    }

}
