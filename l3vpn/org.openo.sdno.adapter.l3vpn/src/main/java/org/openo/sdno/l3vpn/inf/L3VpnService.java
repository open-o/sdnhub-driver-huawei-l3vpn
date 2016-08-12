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

package org.openo.sdno.l3vpn.inf;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.frame.ServiceParasInfo;
import org.openo.sdno.framework.container.service.IService;
import org.openo.sdno.model.networkmodel.servicel3vpn.L3Vpn;
import org.openo.sdno.model.networkmodel.servicetypes.Ac;
import org.openo.sdno.model.networkmodel.servicetypes.VpnOperStatus;
import org.openo.sdno.result.Result;

/**
 * L3Vpn adapter service interface.<br/>
 * 
 * @author
 * @version SDNO 0.5 2016-6-22
 */
public interface L3VpnService extends IService {

    /**
     * Update the L3vpn information.<br/>
     * 
     * @param spi The service parameters information
     * @since SDNO 0.5
     */
    public void adminStatusUpdate(final ServiceParasInfo spi) throws ServiceException;

    /**
     * if the adminStatus is activated,then change to deactivated,otherwise change to activated.<br/>
     * 
     * @param spi The service parameters information
     * @since SDNO 0.5
     */
    public void l3vpnTpStatusUpdate(final ServiceParasInfo spi) throws ServiceException;

    /**
     * Create L3vpn.<br/>
     * 
     * @param spi The service parameters information
     * @return The result of creating L3vpn
     * @since SDNO 0.5
     */
    public Result<L3Vpn> l3vpnCreate(final ServiceParasInfo spi) throws ServiceException;

    /**
     * Delete the TP information of vpn.<br/>
     * 
     * @param spi The service parameters information
     * @return The result of deleting
     * @since SDNO 0.5
     */
    public Result<String> l3vpnBindTpDelete(final ServiceParasInfo spi) throws ServiceException;

    /**
     * Delete the L3vpn.<br/>
     * 
     * @param spi The service parameters information
     * @return The result of deleting
     * @since SDNO 0.5
     */
    public Result<String> l3vpnDelete(final ServiceParasInfo spi) throws ServiceException;

    /**
     * Update the description of L3vpn.<br/>
     * 
     * @param spi The service parameters information
     * @return The result of updating
     * @since SDNO 0.5
     */
    public Result<L3Vpn> l3vpnUpdateDecs(final ServiceParasInfo spi) throws ServiceException;

    /**
     * Get the operate status of L3vpn.<br/>
     * 
     * @param spi The service parameters information
     * @return The result of getting
     * @since SDNO 0.5
     */
    public Result<VpnOperStatus> l3vpnOperStatusGet(final ServiceParasInfo spi) throws ServiceException;

    /**
     * Get the information of L3vpn.<br/>
     * 
     * @param spi The service parameters information
     * @return The result of getting L3vpn information
     * @since SDNO 0.5
     */
    public Result<L3Vpn> l3vpnGet(final ServiceParasInfo spi) throws ServiceException;

    /**
     * Create the bound TP of L3vpn.<br/>
     * 
     * @param spi The service parameters information
     * @return The result of creating TP
     * @since SDNO 0.5
     */
    public Result<Ac> l3vpnBindTpCreate(final ServiceParasInfo spi) throws ServiceException;
}
