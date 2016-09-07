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

package org.openo.sdno.acwanservice.impl;

import java.text.MessageFormat;
import java.util.Map;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.wanservice.constants.L3VpnSvcErrorCode;
import org.openo.sdno.wanservice.inf.Il3VpnService;
import org.openo.sdno.acwanservice.transformer.NetToSerTransformer;
import org.openo.sdno.acwanservice.transformer.SerToNetTransformer;
import org.openo.sdno.common.restconf.ContentType;
import org.openo.sdno.common.restconf.RestConfProxy;
import org.openo.sdno.common.restconf.SerializeUtil;
import org.openo.sdno.common.restconf.ServiceExceptionUtil;
import org.openo.sdno.exception.ErrorCode;
import org.openo.sdno.exception.HttpCode;
import org.openo.sdno.frame.ServiceParasInfo;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.model.networkmodel.servicetypes.L3Vpn;
import org.openo.sdno.model.networkmodel.servicetypes.L3VpnConfig;
import org.openo.sdno.model.networkmodel.servicetypes.VpnOperStatus;
import org.openo.sdno.result.Result;
import org.openo.sdno.util.http.HTTPReturnMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The service class of L3vpn.<br/>
 *
 * @author
 * @version SDNO 0.5 2016-6-2
 */
public class L3vpnAcService implements Il3VpnService {

    private static final Logger LOGGER = LoggerFactory.getLogger(L3vpnAcService.class);

    private static final ContentType CONTENT_TYPE = ContentType.XML;

    private static final String ACTIVE_STATUS = "active";

    private static final String INACTIVE_STATUS = "inactive";

    /**
     * Update the L3vpn information.<br/>
     *
     * @param request request
     * @param vpdId vpn ID
     * @param ctrlUuid ctrl UUID
     * @return
     * @since SDNO 0.5
     */
    public Result<String> l3vpnStatusUpdate(String request, String vpdId, String ctrlUuid) throws ServiceException {

        final org.openo.sdno.model.uniformsbi.l3vpn.L3Vpn l3Vpn =
                JsonUtil.fromJson(request, org.openo.sdno.model.uniformsbi.l3vpn.L3Vpn.class);

        L3VpnConfig ctrlrl3vpn = SerToNetTransformer.transformModel(l3Vpn);
        final String l3vpnMsg = SerializeUtil.serialize(L3vpnAcService.CONTENT_TYPE, ctrlrl3vpn);
        L3vpnAcService.LOGGER.info(l3vpnMsg);

        final String url =
                MessageFormat.format("/restconf/config/huawei-ac-net-l3vpn:l3vpn-cfg/instances/instance/{0}", vpdId);
        final HTTPReturnMessage msg = RestConfProxy.put(L3vpnAcService.CONTENT_TYPE, url, ctrlUuid, l3vpnMsg);
        if(HttpCode.isSucess(msg.getStatus())) {
            return new Result<>(ErrorCode.OPERATION_SUCCESS, msg.getBody());
        }

        L3vpnAcService.LOGGER.error("adminStatusUpdate failed:" + msg.getBody());
        throw ServiceExceptionUtil.getServiceException(L3VpnSvcErrorCode.L3VPN_UPDATE_STATUS_CONTROLLER_FAIL,
                msg.getStatus(), msg.getBody(), ctrlUuid);
    }

    /**
     * Create L3vpn.<br/>
     *
     * @param spi The service parameters information
     * @param ctrlUuid ctrl UUID
     * @return The result of creating L3vpn
     * @since SDNO 0.5
     */
    public Result<String> l3vpnCreate(final String spi, String ctrlUuid) throws ServiceException {
        final String url = "/restconf/config/huawei-ac-net-l3vpn:l3vpn-cfg/instances";

        final org.openo.sdno.model.uniformsbi.l3vpn.L3Vpn l3Vpn =
                JsonUtil.fromJson(spi, org.openo.sdno.model.uniformsbi.l3vpn.L3Vpn.class);

        L3VpnConfig ctrlrl3vpn = SerToNetTransformer.transformModel(l3Vpn);
        final String l3vpnMsg = SerializeUtil.serialize(L3vpnAcService.CONTENT_TYPE, ctrlrl3vpn);
        L3vpnAcService.LOGGER.info(l3vpnMsg);
        final HTTPReturnMessage msg = RestConfProxy.post(L3vpnAcService.CONTENT_TYPE, url, ctrlUuid, l3vpnMsg);
        if(HttpCode.isSucess(msg.getStatus())) {
            return new Result<>(ErrorCode.OPERATION_SUCCESS, msg.getBody());
        } else {
            L3vpnAcService.LOGGER.error("l3vpnCreate failed:" + msg.getBody());
            throw ServiceExceptionUtil.getServiceException(L3VpnSvcErrorCode.L3VPN_CREATE_CONTROLLER_FAIL,
                    msg.getStatus(), msg.getBody(), ctrlUuid);
        }
    }

    /**
     * Delete the L3vpn.<br/>
     *
     * @param ctrlId ctrl ID
     * @param vpnId vpn ID
     * @return The result of deleting
     * @since SDNO 0.5
     */
    public Result<String> l3vpnDelete(String ctrlId, String vpnId) throws ServiceException {
        final String url = "/restconf/config/huawei-ac-net-l3vpn:l3vpn-cfg/instances/instance/" + vpnId;
        final HTTPReturnMessage msg = RestConfProxy.del(L3vpnAcService.CONTENT_TYPE, url, ctrlId);
        if(HttpCode.isSucess(msg.getStatus())) {
            return new Result<>(ErrorCode.OPERATION_SUCCESS, msg.getBody());
        } else {
            L3vpnAcService.LOGGER.error("l3vpnDelete failed:" + msg.getBody());
            throw ServiceExceptionUtil.getServiceException(L3VpnSvcErrorCode.L3VPN_DELETE_CONTROLLER_FAIL,
                    msg.getStatus(), msg.getBody(), ctrlId);
        }
    }

    /**
     * Update the description of L3vpn.<br/>
     *
     * @param spi The service parameters information
     * @return The result of updating
     * @since SDNO 0.5
     */
    public Result<L3Vpn> l3vpnUpdateDecs(final ServiceParasInfo spi) throws ServiceException {
        final L3Vpn l3Vpn = JsonUtil.fromJson(spi.getServiceBody(), L3Vpn.class);
        L3vpnAcService.LOGGER.info("l3vpn update :" + spi.getServiceBody());
        final String url = "/restconf/config/huawei-ac-net-l3vpn:l3vpn-cfg/instances/instance/" + l3Vpn.getId();
        final HTTPReturnMessage msg = RestConfProxy.put(L3vpnAcService.CONTENT_TYPE, url, spi.getCltuuid(),
                SerializeUtil.serialize(L3vpnAcService.CONTENT_TYPE, l3Vpn));
        if(HttpCode.isSucess(msg.getStatus())) {
            return new Result<>(ErrorCode.OPERATION_SUCCESS, l3Vpn);
        } else {
            L3vpnAcService.LOGGER.error("l3vpnUpdate failed:" + msg.getBody());
            throw ServiceExceptionUtil.getServiceException(L3VpnSvcErrorCode.UPDATEDESC_CONTROLLER_FAIL,
                    msg.getStatus(), msg.getBody(), spi.getCltuuid());
        }
    }

    /**
     * Get the operate status of L3vpn.<br/>
     *
     * @param spi The service parameters information
     * @return The result of getting
     * @since SDNO 0.5
     */
    public Result<VpnOperStatus> l3vpnOperStatusGet(final ServiceParasInfo spi) throws ServiceException {
        final Map<String, String[]> obj = spi.getQueryMap();
        final String uuid = obj.get("uuid")[0];
        final String url = "/restconf/operational/huawei-ac-net-l3vpn:l3vpn-oper/instances/instance/" + uuid;
        final HTTPReturnMessage msg = RestConfProxy.get(L3vpnAcService.CONTENT_TYPE, url, spi.getCltuuid());
        if(HttpCode.isSucess(msg.getStatus())) {
            final VpnOperStatus operStatus =
                    SerializeUtil.deSerialize(L3vpnAcService.CONTENT_TYPE, msg.getBody(), VpnOperStatus.class);
            L3vpnAcService.LOGGER.info("doGetL3vpnCfg success:" + msg.getBody());
            return new Result<>(ErrorCode.OPERATION_SUCCESS, operStatus == null ? null : operStatus);
        } else {
            L3vpnAcService.LOGGER.error("doGetL3vpn failed:" + msg.getBody());
            throw ServiceExceptionUtil.getServiceException(L3VpnSvcErrorCode.L3VPN_GET_STATUS_CONTROLLER_FAIL,
                    msg.getStatus(), msg.getBody(), spi.getCltuuid());
        }
    }

    /**
     * Get the information of L3vpn.<br/>
     *
     * @param vpnId vpn ID
     * @param ctrlUuid ctrl UUID
     * @return The result of getting L3vpn information
     * @since SDNO 0.5
     */
    public Result<String> l3vpnGet(String ctrlUuid, String vpnId) throws ServiceException {

        final String url = "/restconf/config/huawei-ac-net-l3vpn:l3vpn-cfg/instances/instance/" + vpnId;
        final HTTPReturnMessage msg = RestConfProxy.get(L3vpnAcService.CONTENT_TYPE, url, ctrlUuid);
        if(HttpCode.isSucess(msg.getStatus())) {
            String str = msg.getBody().replaceAll("\\\\", "");
            final L3VpnConfig l3Vpn = SerializeUtil.deSerialize(L3vpnAcService.CONTENT_TYPE, str, L3VpnConfig.class);
            org.openo.sdno.model.uniformsbi.l3vpn.L3Vpn nbiL3vpn = NetToSerTransformer.transformModel(l3Vpn);
            L3vpnAcService.LOGGER.info("doGetL3vpnOperStatus success:" + msg.getBody());
            return new Result<>(ErrorCode.OPERATION_SUCCESS, JsonUtil.toJson(nbiL3vpn));
        } else {
            L3vpnAcService.LOGGER.error("doGetL3vpn failed:" + msg.getBody());
            throw ServiceExceptionUtil.getServiceException(L3VpnSvcErrorCode.L3VPN_GET_CONTROLLER_FAIL, msg.getStatus(),
                    msg.getBody(), ctrlUuid);
        }
    }
}
