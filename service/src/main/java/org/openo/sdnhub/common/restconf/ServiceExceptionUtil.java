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

import org.openo.baseservice.remoteservice.exception.ExceptionArgs;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.model.servicemodel.brs.Device;

/**
 * The tool class of service exception.<br>
 *
 * @author
 * @version SDNHUB 0.5 2016-6-2
 */
public class ServiceExceptionUtil {

    private ServiceExceptionUtil() {
    }

    /**
     * Get the ServiceException.<br>
     *
     * @param errCode the error code
     * @param httpCode the HTTP status code
     * @param detail the detail description message
     * @param controllerID the controller uuid
     * @return the ServiceException
     * @throws ServiceException
     * @since SDNHUB 0.5
     */
    public static ServiceException getServiceException(final String errCode, final int httpCode, final String detail,
            final String controllerID) throws ServiceException {
        final Device dev = RestConfProxy.getControllerDevice(controllerID);
        final String strErrorID = String.valueOf(errCode);
        final ServiceException ex = new ServiceException(strErrorID, detail);
        ex.setHttpCode(httpCode);
        final String[] reasonArgs = new String[] {dev.getIp()};
        final String[] details = new String[] {String.valueOf(httpCode), detail};
        ex.setExceptionArgs(new ExceptionArgs(new String[] {}, reasonArgs, details, new String[] {}));
        return ex;
    }

}
