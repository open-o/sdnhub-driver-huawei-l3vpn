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

package org.openo.sdnhub.wanservice.rest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.baseservice.util.RestUtils;
import org.openo.sdnhub.wanservice.inf.L3VpnService;
import org.openo.sdno.framework.container.service.IResource;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.model.uniformsbi.l3vpn.L3Vpn;
import org.openo.sdno.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Restful interface class of L3 VPN adapter resource.<br>
 *
 * @author
 * @version SDNO 0.5 2016-5-30
 */
@Path("/sbi-l3vpn/v1")
public class L3VpnAdapter extends IResource<L3VpnService> {

    private static final Logger LOGGER = LoggerFactory.getLogger(L3VpnAdapter.class);

    private static final String l3vpn = "l3vpn";

    private L3VpnService vpnService;

    public L3VpnService getService() {
        return vpnService;
    }

    @Override
    public String getResUri() {
        return "/openoapi/sbi-l3vpn/v1";
    }

    /**
     * Set the L3VPN service.<br>
     *
     * @param service is l3vpn service information.
     * @since SDNHUB 0.5
     */
    @Override
    public void setService(L3VpnService service) {
        this.vpnService = service;
    }

    /**
     * Create a L3VPN service.<br>
     *
     * @param request HTTP servlet request with the service parameters information..
     * @param ctrlUuidParam controller UUID parameter in header
     * @return response with L3VPN created object that contains the UUID generated.
     * @throws ServiceException throws exception if the operation fails.
     * @since SDNHUB 0.5
     */
    @POST
    @Path("/l3vpns")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Result<String> l3vpnCreate(@Context final HttpServletRequest request,
            @HeaderParam("X-Driver-Parameter") String ctrlUuidParam) throws ServiceException {

        String req = RestUtils.getRequestBody(request);
        LOGGER.error("Create L3VPN request body: " + req);
        Map<String, L3Vpn> l3vpnCreateReq = (JsonUtil.fromJson(req, (new HashMap<String, L3Vpn>()).getClass()));

        String ctrlUuid = ctrlUuidParam.substring(ctrlUuidParam.indexOf('=') + 1);
        return vpnService.l3vpnCreate(JsonUtil.toJson(l3vpnCreateReq.get(l3vpn)), ctrlUuid);
    }

    /**
     * Delete L3VPN service. <br>
     *
     * @param ctrlUuidParam controller UUID parameter in header
     * @param vpnId VPN ID
     * @return response of the delete operation.
     * @throws ServiceException throws exception if the operation fails.
     * @since SDNHUB 0.5
     */
    @DELETE
    @Path("/l3vpns/{id}")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Result<String> l3vpnDelete(@HeaderParam("X-Driver-Parameter") String ctrlUuidParam,
            @PathParam("id") String vpnId) throws ServiceException {
        String ctrlUuid = ctrlUuidParam.substring(ctrlUuidParam.indexOf('=') + 1);
        return vpnService.l3vpnDelete(ctrlUuid, vpnId);
    }

    /**
     * Update the administration state of the L3Vpn service. <br>
     *
     * @param request HTTP servlet request with the service parameters information..
     * @param ctrlUuidParam controller UUID parameter in header
     * @param vpdId VPN ID
     * @return response of the update operation.
     * @throws ServiceException throws exception if the operation fails.
     * @since SDNHUB 0.5
     */
    @PUT
    @Path("/l3vpns/{id}")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Result<String> l3vpnStatusUpdate(@Context final HttpServletRequest request,
            @HeaderParam("X-Driver-Parameter") String ctrlUuidParam, @PathParam("id") String vpdId)
            throws ServiceException {
        String req = RestUtils.getRequestBody(request);
        String ctrlUuid = ctrlUuidParam.substring(ctrlUuidParam.indexOf('=') + 1);

        return vpnService.l3vpnStatusUpdate(req, vpdId, ctrlUuid);
    }

    /**
     * Get the information of L3vpn.<br>
     *
     * @param vpnId VPN ID
     * @param ctrlUuidParam controller UUID parameter in header
     * @return required L3VPN object.
     * @throws ServiceException throws exception if the operation fails.
     * @since SDNHUB 0.5
     */
    @GET
    @Path("/l3vpns/{id}")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Result<String> l3vpnGet(@PathParam("id") String vpnId,
            @HeaderParam("X-Driver-Parameter") String ctrlUuidParam) throws ServiceException {
        String ctrlUuid = ctrlUuidParam.substring(ctrlUuidParam.indexOf('=') + 1);

        return vpnService.l3vpnGet(ctrlUuid, vpnId);
    }
}
