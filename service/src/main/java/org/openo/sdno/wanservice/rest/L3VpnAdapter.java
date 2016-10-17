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
import org.openo.sdno.framework.container.service.IResource;
import org.openo.sdno.result.Result;
import org.openo.sdno.wanservice.inf.L3VpnService;

/**
 * Restful interface class of L3 VPN adapter resource.<br>
 *
 * @author
 * @version SDNO 0.5 2016-5-30
 */
@Path("/sbi-l3vpn/v1")
public class L3VpnAdapter extends IResource<L3VpnService> {

    private L3VpnService service;

    public L3VpnService getService() {
        return service;
    }

    @Override
    public String getResUri() {
        return "/openoapi/sbi-l3vpn/v1";
    }

    /**
     * Set the L3VPN service.<br>
     *
     * @param service is l3vpn service information.
     * @since SDNO 0.5
     */
    @Override
    public void setService(L3VpnService service) {
        this.service = service;
    }

    /**
     * Create a L3VPN service.<br>
     *
     * @param request Http servlet request with the service parameters information..
     * @param ctrlUuidParam ctrl UUID parameter in header
     * @return response with L3VPN created object that contains the UUID generated.
     * @throws WebApplicationException throws exception if the operation fails.
     * @since SDNO 0.5
     */
    @POST
    @Path("/l3vpns")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Result<String> l3vpnCreate(@Context final HttpServletRequest request,
            @HeaderParam("X-Driver-Parameter") String ctrlUuidParam) throws WebApplicationException {
        String req = RestUtils.getRequestBody(request);
        String ctrlUuid = ctrlUuidParam.substring(ctrlUuidParam.indexOf('=') + 1);
        try {
            return service.l3vpnCreate(req, ctrlUuid);
        } catch(ServiceException e) {
            throw new WebApplicationException(e.getId(), e.getHttpCode());
        }
    }

    /**
     * Delete L3VPN service. <br>
     *
     * @param ctrlUuidParam ctrl UUID parameter in header
     * @param vpnId vpn ID
     * @return response of the delete operation.
     * @throws WebApplicationException throws exception if the operation fails.
     * @since SDNO 0.5
     */
    @DELETE
    @Path("/l3vpns/{id}")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Result<String> l3vpnDelete(@HeaderParam("X-Driver-Parameter") String ctrlUuidParam,
            @PathParam("id") String vpnId) throws WebApplicationException {
        String ctrlUuid = ctrlUuidParam.substring(ctrlUuidParam.indexOf('=') + 1);
        try {
            return service.l3vpnDelete(ctrlUuid, vpnId);
        } catch(ServiceException e) {
            throw new WebApplicationException(e.getId(), e.getHttpCode());
        }
    }

    /**
     * Update the administration state of the L3Vpn service. <br>
     *
     * @param request Http servlet request with the service parameters information..
     * @param ctrlUuidParam ctrl UUID parameter in header
     * @param vpdId vpn ID
     * @return response of the update operation.
     * @throws WebApplicationException throws exception if the operation fails.
     * @since SDNO 0.5
     */
    @PUT
    @Path("/l3vpns/{id}")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Result<String> l3vpnStatusUpdate(@Context final HttpServletRequest request,
            @HeaderParam("X-Driver-Parameter") String ctrlUuidParam, @PathParam("id") String vpdId)
            throws WebApplicationException {
        String req = RestUtils.getRequestBody(request);
        String ctrlUuid = ctrlUuidParam.substring(ctrlUuidParam.indexOf('=') + 1);

        try {
            return service.l3vpnStatusUpdate(req, vpdId, ctrlUuid);
        } catch(ServiceException e) {
            throw new WebApplicationException(e.getId(), e.getHttpCode());
        }
    }

    /**
     * Get the information of L3vpn.<br>
     *
     * @param request Http servlet request with the service parameters information.
     * @param vpnId vpn ID
     * @param ctrlUuidParam ctrl UUID parameter in header
     * @return required L3VPN object.
     * @throws WebApplicationException throws exception if the operation fails.
     * @since SDNO 0.5
     */
    @GET
    @Path("/l3vpns/{id}")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Result<String> l3vpnGet(@Context final HttpServletRequest request, @PathParam("id") String vpnId,
            @HeaderParam("X-Driver-Parameter") String ctrlUuidParam) throws WebApplicationException {
        String ctrlUuid = ctrlUuidParam.substring(ctrlUuidParam.indexOf('=') + 1);

        try {
            return service.l3vpnGet(ctrlUuid, vpnId);
        } catch(ServiceException e) {
            throw new WebApplicationException(e.getId(), e.getHttpCode());
        }

    }
}
