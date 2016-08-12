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

package org.openo.sdno.resource;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.baseservice.util.RestUtils;
import org.openo.sdno.cbb.wanvpn.util.checker.ScopeChecker;
import org.openo.sdno.cbb.wanvpn.util.rest.ROAInputStreamParser;
import org.openo.sdno.frame.ServiceParasInfo;
import org.openo.sdno.framework.container.service.IResource;
import org.openo.sdno.l3vpn.inf.L3VpnService;
import org.openo.sdno.model.networkmodel.servicel3vpn.L3Vpn;
import org.openo.sdno.model.networkmodel.servicetypes.Ac;
import org.openo.sdno.model.networkmodel.servicetypes.VpnOperStatus;
import org.openo.sdno.result.Result;

/**
 * Restful interface class of L3 VPN adapter resource.<br/>
 * 
 * @author
 * @version SDNO 0.5 2016-5-30
 */
@Path("/svc/l3vpn/v1/adapter")
public class L3VpnAdapter extends IResource<L3VpnService> {

    @Override
    public String getResUri() {
        return "/svc/l3vpn/v1/adapter";
    }

    private L3VpnService service;

    
    public L3VpnService getService() {
        return service;
    }

    
    public void setService(L3VpnService service) {
        this.service = service;
    }

    @PUT
    @Path("/adminstatusupdate")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public void adminStatusUpdate(@Context final HttpServletRequest request) throws ServiceException {
        ServiceParasInfo spi = ROAInputStreamParser.fromJson(RestUtils.getRequestBody(request), ServiceParasInfo.class);
        service.adminStatusUpdate(spi);
    }

    @PUT
    @Path("/l3vpntpstatus")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public void l3vpnTpStatusUpdate(@Context final HttpServletRequest request) throws ServiceException {
        ServiceParasInfo spi = ROAInputStreamParser.fromJson(RestUtils.getRequestBody(request), ServiceParasInfo.class);
        service.l3vpnTpStatusUpdate(spi);
    }

    @POST
    @Path("/l3vpncreate")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Result<L3Vpn> l3vpnCreate(@Context final HttpServletRequest request) throws ServiceException {
        ServiceParasInfo spi = ROAInputStreamParser.fromJson(RestUtils.getRequestBody(request), ServiceParasInfo.class);
        Result<L3Vpn> rst = service.l3vpnCreate(spi);
        return rst;
    }

    @DELETE
    @Path("/l3vpnbindtpdelete")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Result<String> l3vpnBindTpDelete(@Context final HttpServletRequest request) throws ServiceException {
        ServiceParasInfo spi = ROAInputStreamParser.fromJson(RestUtils.getRequestBody(request), ServiceParasInfo.class);
        Result<String> rst = service.l3vpnBindTpDelete(spi);
        return rst;
    }

    @DELETE
    @Path("/l3vpndelete")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Result<String> l3vpnDelete(@Context final HttpServletRequest request) throws ServiceException {
        ServiceParasInfo spi = ROAInputStreamParser.fromJson(RestUtils.getRequestBody(request), ServiceParasInfo.class);
        Result<String> rst = service.l3vpnDelete(spi);
        return rst;
    }

    @PUT
    @Path("/l3vpnupdatedecs")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Result<L3Vpn> l3vpnUpdateDecs(@Context final HttpServletRequest request) throws ServiceException {
        ServiceParasInfo spi = ROAInputStreamParser.fromJson(RestUtils.getRequestBody(request), ServiceParasInfo.class);
        Result<L3Vpn> rst = service.l3vpnUpdateDecs(spi);
        return rst;
    }

    @GET
    @Path("/l3vpnoperstatusget")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Result<VpnOperStatus> l3vpnOperStatusGet(@Context final HttpServletRequest request) throws ServiceException {
        ServiceParasInfo spi = ROAInputStreamParser.fromJson(RestUtils.getRequestBody(request), ServiceParasInfo.class);
        Result<VpnOperStatus> rst = service.l3vpnOperStatusGet(spi);
        return rst;
    }

    @GET
    @Path("/l3vpnget")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Result<L3Vpn> l3vpnGet(@Context final HttpServletRequest request) throws ServiceException {
        ServiceParasInfo spi = ROAInputStreamParser.fromJson(RestUtils.getRequestBody(request), ServiceParasInfo.class);
        Result<L3Vpn> rst = service.l3vpnGet(spi);
        return rst;
    }

    @POST
    @Path("/l3vpnbindtpcreate")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Result<Ac> l3vpnBindTpCreate(@Context final HttpServletRequest request) throws ServiceException {
        ServiceParasInfo spi = ROAInputStreamParser.fromJson(RestUtils.getRequestBody(request), ServiceParasInfo.class);
        Result<Ac> rst = service.l3vpnBindTpCreate(spi);
        return rst;
    }
}
