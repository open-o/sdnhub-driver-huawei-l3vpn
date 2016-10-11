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

package org.openo.sdno.wanservice.inf;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.frame.ServiceParasInfo;
import org.openo.sdno.framework.container.service.IService;
import org.openo.sdno.model.networkmodel.servicetypes.L3Vpn;
import org.openo.sdno.model.networkmodel.servicetypes.VpnOperStatus;
import org.openo.sdno.result.Result;

/**
 * L3Vpn adapter service interface.<br>
 *
 * @author
 * @version SDNO 0.5 2016-6-22
 */
public interface L3VpnService extends IService {

    /**
     * Update the L3vpn information.<br>
     *
     * @param req request
     * @param vpdId VPN ID
     * @param ctrlUuid controller uuid
     * @return the status
     * @throws ServiceException
     * @since SDNO 0.5
     */
    Result<String> l3vpnStatusUpdate(String req, String vpdId, String ctrlUuid) throws ServiceException;

    /**
     * Create L3vpn.<br>
     *
     * @param req the service parameters information
     * @param ctrlUuid controller uuid
     * @return the result of creating L3vpn
     * @throws ServiceException
     * @since SDNO 0.5
     */
    Result<String> l3vpnCreate(String req, String ctrlUuid) throws ServiceException;

    /**
     * Update the description of L3vpn.<br>
     *
     * @param spi the service parameters information
     * @return the result of update
     * @throws ServiceException
     * @since SDNO 0.5
     */
    Result<L3Vpn> l3vpnUpdateDecs(ServiceParasInfo spi) throws ServiceException;

    /**
     * Get the operate status of L3vpn.<br>
     *
     * @param spi the service parameters information
     * @return the result of getting
     * @throws ServiceException
     * @since SDNO 0.5
     */
    Result<VpnOperStatus> l3vpnOperStatusGet(ServiceParasInfo spi) throws ServiceException;

    /**
     * Get the information of L3vpn.<br>
     *
     * @param ctrlId controller uuid
     * @param vpnId VPN ID
     * @return the result of getting L3vpn information
     * @throws ServiceException
     * @since SDNO 0.5
     */
    Result<String> l3vpnGet(String ctrlId, String vpnId) throws ServiceException;

    /**
     * Delete the L3vpn.<br>
     *
     * @param ctrlId controller uuid
     * @param vpnId VPN ID
     * @return the result of deleting
     * @throws ServiceException
     * @since SDNO 0.5
     */
    Result<String> l3vpnDelete(String ctrlId, String vpnId) throws ServiceException;
}
