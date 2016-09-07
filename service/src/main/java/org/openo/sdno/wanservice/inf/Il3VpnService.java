/*
 * Copyright (c) 2016, Huawei Technologies Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
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
 * L3Vpn adapter service interface.<br/>
 * 
 * @author
 * @version SDNO 0.5 2016-6-22
 */
public interface Il3VpnService extends IService {

    /**
     * Update the L3vpn information.<br/>
     *
     * @param req request
     * @param vpdId vpn ID
     * @param ctrlUuid ctrl ID
     * @return
     * @since SDNO 0.5
     */
    Result<String> l3vpnStatusUpdate(String req, String vpdId, String ctrlUuid) throws ServiceException;

    /**
     * Create L3vpn.<br/>
     *
     * @param req The service parameters information
     * @param ctrlUuid
     * @return The result of creating L3vpn
     * @since SDNO 0.5
     */
    Result<String> l3vpnCreate(String req, String ctrlUuid) throws ServiceException;

    /**
     * Delete the TP information of vpn.<br/>
     *
     * @param spi The service parameters information
     * @return The result of deleting
     * @since SDNO 0.5
     */

    /**
     * Update the description of L3vpn.<br/>
     *
     * @param spi The service parameters information
     * @return The result of updating
     * @since SDNO 0.5
     */
    Result<L3Vpn> l3vpnUpdateDecs(ServiceParasInfo spi) throws ServiceException;

    /**
     * Get the operate status of L3vpn.<br/>
     *
     * @param spi The service parameters information
     * @return The result of getting
     * @since SDNO 0.5
     */
    Result<VpnOperStatus> l3vpnOperStatusGet(ServiceParasInfo spi) throws ServiceException;

    /**
     * Get the information of L3vpn.<br/>
     *
     * @param ctrlId ctrl ID
     * @param vpnId vpn ID
     * @return The result of getting L3vpn information
     * @since SDNO 0.5
     */
    Result<String> l3vpnGet(String ctrlId, String vpnId) throws ServiceException;

    /**
     * Delete the L3vpn.<br/>
     *
     * @param ctrlId ctrl ID
     * @param vpnId vpn ID
     * @return The result of deleting
     * @since SDNO 0.5
     */
    Result<String> l3vpnDelete(String ctrlId, String vpnId) throws ServiceException;
}
