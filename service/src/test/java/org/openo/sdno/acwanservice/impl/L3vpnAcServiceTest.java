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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.helpers.AbstractMarshallerImpl;
import javax.xml.bind.helpers.AbstractUnmarshallerImpl;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.common.restconf.HttpProxy;
import org.openo.sdno.frame.ServiceParasInfo;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.model.networkmodel.servicetypes.AcProtectGroup;
import org.openo.sdno.model.networkmodel.servicetypes.AcProtectGroups;
import org.openo.sdno.model.networkmodel.servicetypes.L3Acs;
import org.openo.sdno.model.networkmodel.servicetypes.L3Vpn;
import org.openo.sdno.model.networkmodel.servicetypes.L3VpnConfig;
import org.openo.sdno.model.networkmodel.servicetypes.VpnOperStatus;
import org.openo.sdno.model.servicemodel.brs.Device;
import org.openo.sdno.model.uniformsbi.base.AutoSelectPolicy;
import org.openo.sdno.model.uniformsbi.base.AutoSelectTunnel;
import org.openo.sdno.model.uniformsbi.base.AutoSelectTunnels;
import org.openo.sdno.model.uniformsbi.base.MplsTePolicy;
import org.openo.sdno.model.uniformsbi.comnontypes.enums.AdminStatus;
import org.openo.sdno.model.uniformsbi.l3vpn.HubGroup;
import org.openo.sdno.model.uniformsbi.l3vpn.HubGroups;
import org.openo.sdno.model.uniformsbi.l3vpn.L2Access;
import org.openo.sdno.model.uniformsbi.l3vpn.L3Access;
import org.openo.sdno.model.uniformsbi.l3vpn.ProtectGroup;
import org.openo.sdno.model.uniformsbi.l3vpn.Routes;
import org.openo.sdno.model.uniformsbi.l3vpn.SpokeAcs;
import org.openo.sdno.model.uniformsbi.l3vpn.SpokeGroup;
import org.openo.sdno.model.uniformsbi.l3vpn.TopologyService;
import org.openo.sdno.model.uniformsbi.l3vpn.Vrrp;
import org.openo.sdno.util.http.HTTPRequestMessage;
import org.openo.sdno.util.http.HTTPReturnMessage;
import org.openo.sdno.wanvpn.inventory.sdk.util.InventoryProxy;

import mockit.Mock;
import mockit.MockUp;

public class L3vpnAcServiceTest {

    L3vpnAcService l3vpnAcService = new L3vpnAcService();

    org.openo.sdno.model.uniformsbi.l3vpn.L3Vpn l3Vpn = new org.openo.sdno.model.uniformsbi.l3vpn.L3Vpn();

    Map<String, String[]> map = new HashMap<>();

    String[] string = {"1", "2"};

    @Before
    public void create() {

        AdminStatus adminStatus = AdminStatus.ADMIN_DOWN;
        l3Vpn.setAdminStatus(adminStatus);
        l3Vpn.setUuid("uuid");
        l3Vpn.setName("name");
        TopologyService topologyService = new TopologyService();
        HubGroups hubGroups = new HubGroups();
        List<HubGroup> listHubGroup = new ArrayList<>();
        HubGroup hubGroup = new HubGroup();
        hubGroup.setAcId("acID");
        hubGroup.setHubDirection("hubDirection");
        listHubGroup.add(hubGroup);
        hubGroups.setHubGroup(listHubGroup);
        topologyService.setHubGroups(hubGroups);

        SpokeGroup spokeGroup = new SpokeGroup();
        spokeGroup.setLocalBridge(true);
        List<SpokeAcs> listSpokeAcs = new ArrayList<>();
        SpokeAcs spokeAcs = new SpokeAcs();
        spokeAcs.setAcId("acID");
        listSpokeAcs.add(spokeAcs);
        spokeGroup.setSpokeAcs(listSpokeAcs);
        topologyService.setSpokeGroup(spokeGroup);
        l3Vpn.setTopologyService(topologyService);
        org.openo.sdno.model.uniformsbi.base.TunnelService tunnelService =
                new org.openo.sdno.model.uniformsbi.base.TunnelService();
        tunnelService.setType("type");
        AutoSelectPolicy autoSelectPolicy = new AutoSelectPolicy();
        autoSelectPolicy.setLoadBalanceNumber(1);
        AutoSelectTunnels autoSelectTunnels = new AutoSelectTunnels();
        AutoSelectTunnel autoSelectTunnel = new AutoSelectTunnel();
        autoSelectTunnel.setPriority(1);
        autoSelectTunnel.setType("type");
        List<AutoSelectTunnel> list = new ArrayList<>();
        list.add(autoSelectTunnel);
        autoSelectTunnels.setAutoSelectTunnel(list);
        autoSelectPolicy.setAutoSelectTunnels(autoSelectTunnels);
        tunnelService.setAutoSelect(autoSelectPolicy);
        MplsTePolicy mplsTe = new MplsTePolicy();
        mplsTe.setBandwidth(123);
        mplsTe.setManageProtocol("mangeProtocol");
        mplsTe.setSignalType("strongSignal");
        org.openo.sdno.model.uniformsbi.base.PathProtectPolicy pathProtectPolicy =
                new org.openo.sdno.model.uniformsbi.base.PathProtectPolicy();
        pathProtectPolicy.setBandwidthMode("auto");
        pathProtectPolicy.setRevertive(true);
        pathProtectPolicy.setWtr(1);
        mplsTe.setPathProtectPolicy(pathProtectPolicy);
        org.openo.sdno.model.uniformsbi.base.PathConstraint pathConstraint =
                new org.openo.sdno.model.uniformsbi.base.PathConstraint();
        pathConstraint.setHoldupPriority(1);
        pathConstraint.setSetupPriority(1);
        pathConstraint.setLatency(1);
        mplsTe.setPathConstraint(pathConstraint);

        tunnelService.setMplsTe(mplsTe);
        l3Vpn.setTunnelService(tunnelService);
        l3Vpn.setFrr(true);
        ProtectGroup protectGroup = new ProtectGroup();
        protectGroup.setBackupAc("backUpAc");
        protectGroup.setMasterAc("masterAc");
        Vrrp vrrp = new Vrrp();
        vrrp.setVirtualIp("virtualIp");
        protectGroup.setVrrp(vrrp);
        l3Vpn.setProtectGroup(protectGroup);

        org.openo.sdno.model.uniformsbi.l3vpn.L3Acs l3acs = new org.openo.sdno.model.uniformsbi.l3vpn.L3Acs();
        org.openo.sdno.model.uniformsbi.l3vpn.L3Ac l3Ac = new org.openo.sdno.model.uniformsbi.l3vpn.L3Ac();
        l3Ac.setUuid("uuid");
        L2Access l2Access = new L2Access();
        l2Access.setL2AccessType("L2AccessType");
        l3Ac.setL2Access(l2Access);
        l3Ac.setNeId("neId");
        L3Access l3Access = new L3Access();
        l3Access.setIpv4Address("ipv4Address");
        l3Access.setRoutes(new Routes());
        l3Ac.setL3Access(l3Access);
        List<org.openo.sdno.model.uniformsbi.l3vpn.L3Ac> lsitL3Ac = new ArrayList<>();
        lsitL3Ac.add(l3Ac);
        l3acs.setL3Ac(lsitL3Ac);
        l3Vpn.setAcs(l3acs);
        map.put("l3VpnId", string);
        map.put("tpId", string);
        map.put("uuid", string);
    }

    @Test
    public void testl3adminStatusUpdate() throws ServiceException {

        new MockUp<JsonUtil>() {

            @Mock
            public L3Vpn fromJson(String arg0, Class<L3Vpn> arg1) {
                L3Vpn l3Vpn = new L3Vpn();
                l3Vpn.setId("Id");
                l3Vpn.setMode("mode");
                l3Vpn.setName("name");
                List<AcProtectGroup> list = new ArrayList<>();
                list.add(new AcProtectGroup());
                l3Vpn.setAcProtectGroups(new AcProtectGroups());
                ;
                l3Vpn.setAcs(new L3Acs());
                return l3Vpn;

            }
        };

        new MockUp<InventoryProxy>() {

            @Mock
            public Device getControllerDevice(String arg0) throws ServiceException {
                Device device = new Device();
                device.setPort(123);
                return device;
            }
        };
        new MockUp<HttpProxy>() {

            @Mock
            public HTTPReturnMessage restInvoke(HTTPRequestMessage arg0, HTTPRequestMessage arg1) {
                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);
                return httpReturnMessage;
            }
        };
        new MockUp<AbstractMarshallerImpl>() {

            @Mock
            public void marshal(Object obj, java.io.Writer w) throws IOException {
                w.write(obj.toString());

            }
        };

        ServiceParasInfo spi = new ServiceParasInfo("uuid", "resource", "serviceBody", null, "classPath");
    }

    @Test
    public void testl3vpnTpStatusUpdate() throws ServiceException {

        new MockUp<JsonUtil>() {

            @Mock
            public org.openo.sdno.model.uniformsbi.l3vpn.L3Vpn fromJson(String arg0,
                    Class<org.openo.sdno.model.uniformsbi.l3vpn.L3Vpn> arg1) {
                org.openo.sdno.model.uniformsbi.l3vpn.L3Vpn l3Vpn = new org.openo.sdno.model.uniformsbi.l3vpn.L3Vpn();
                l3Vpn.setDescription("description");
                ;
                return l3Vpn;
            }
        };

        new MockUp<InventoryProxy>() {

            @Mock
            public Device getControllerDevice(String arg0) throws ServiceException {
                Device device = new Device();
                device.setPort(123);
                return device;
            }
        };
        new MockUp<HttpProxy>() {

            @Mock
            public HTTPReturnMessage restInvoke(HTTPRequestMessage arg0, HTTPRequestMessage arg1) {
                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);
                return httpReturnMessage;
            }
        };
        new MockUp<AbstractMarshallerImpl>() {

            @Mock
            public void marshal(Object obj, java.io.Writer w) throws IOException {
                w.write(obj.toString());

            }
        };
        new MockUp<IOUtils>() {

            @Mock
            public String toString(InputStream input) throws IOException {
                return "temp";
            }
        };

        ServiceParasInfo spi = new ServiceParasInfo("uuid", "resource", "serviceBody", null, "classPath");
        l3vpnAcService.l3vpnStatusUpdate("reqeset", "123", "123");
    }

    @Test
    public void testl3vpnTpStatusUpdateInActiveBranch() throws ServiceException {

        new MockUp<JsonUtil>() {

            @Mock
            public org.openo.sdno.model.uniformsbi.l3vpn.L3Vpn fromJson(String arg0,
                    Class<org.openo.sdno.model.uniformsbi.l3vpn.L3Vpn> arg1) {
                org.openo.sdno.model.uniformsbi.l3vpn.L3Vpn l3Vpn = new org.openo.sdno.model.uniformsbi.l3vpn.L3Vpn();
                l3Vpn.setDescription("description");
                ;
                return l3Vpn;
            }
        };

        new MockUp<InventoryProxy>() {

            @Mock
            public Device getControllerDevice(String arg0) throws ServiceException {
                Device device = new Device();
                device.setPort(123);
                return device;
            }
        };
        new MockUp<HttpProxy>() {

            @Mock
            public HTTPReturnMessage restInvoke(HTTPRequestMessage arg0, HTTPRequestMessage arg1) {
                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);
                return httpReturnMessage;
            }
        };
        new MockUp<AbstractMarshallerImpl>() {

            @Mock
            public void marshal(Object obj, java.io.Writer w) throws IOException {
                w.write(obj.toString());

            }
        };

        ServiceParasInfo spi = new ServiceParasInfo("uuid", "resource", "serviceBody", null, "classPath");
        l3vpnAcService.l3vpnStatusUpdate("reqeset", "123", "123");
    }

    @Test(expected = ServiceException.class)
    public void testl3vpnTpStatusUpdateException() throws ServiceException {
        new MockUp<IOUtils>() {

            @Mock
            public String toString(InputStream input) throws IOException {
                return "temp";
            }
        };
        new MockUp<JsonUtil>() {

            @Mock
            public org.openo.sdno.model.uniformsbi.l3vpn.L3Vpn fromJson(String arg0,
                    Class<org.openo.sdno.model.uniformsbi.l3vpn.L3Vpn> arg1) {
                org.openo.sdno.model.uniformsbi.l3vpn.L3Vpn l3Vpn = new org.openo.sdno.model.uniformsbi.l3vpn.L3Vpn();
                l3Vpn.setDescription("description");
                ;
                return l3Vpn;
            }
        };

        new MockUp<InventoryProxy>() {

            @Mock
            public Device getControllerDevice(String arg0) throws ServiceException {
                Device device = new Device();
                device.setPort(123);
                return device;
            }
        };
        new MockUp<HttpProxy>() {

            @Mock
            public HTTPReturnMessage restInvoke(HTTPRequestMessage arg0, HTTPRequestMessage arg1) {
                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(504);
                return httpReturnMessage;
            }
        };
        new MockUp<AbstractMarshallerImpl>() {

            @Mock
            public void marshal(Object obj, java.io.Writer w) throws IOException {
                w.write(obj.toString());

            }
        };

        ServiceParasInfo spi = new ServiceParasInfo("uuid", "resource", "serviceBody", null, "classPath");
        l3vpnAcService.l3vpnStatusUpdate("reqeset", "123", "123");
    }

    @Test
    public void testl3vpnCreate() throws ServiceException {
        new MockUp<IOUtils>() {

            @Mock
            public String toString(InputStream input) throws IOException {
                return "temp";
            }
        };
        new MockUp<JsonUtil>() {

            @Mock
            public org.openo.sdno.model.uniformsbi.l3vpn.L3Vpn fromJson(String arg0,
                    Class<org.openo.sdno.model.uniformsbi.l3vpn.L3Vpn> arg1) {
                org.openo.sdno.model.uniformsbi.l3vpn.L3Vpn l3Vpnl = new org.openo.sdno.model.uniformsbi.l3vpn.L3Vpn();
                // modifyTpStatusInput.setAdminStatus("inactive");
                return l3Vpnl;
            }
        };
        new MockUp<InventoryProxy>() {

            @Mock
            public Device getControllerDevice(String arg0) throws ServiceException {
                Device device = new Device();
                device.setPort(123);
                return device;
            }
        };
        new MockUp<HttpProxy>() {

            @Mock
            public HTTPReturnMessage restInvoke(HTTPRequestMessage arg0, HTTPRequestMessage arg1) {
                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);
                return httpReturnMessage;
            }
        };
        l3vpnAcService.l3vpnCreate("spi", "ctrluuid");
    }

    @Test(expected = ServiceException.class)
    public void testl3vpnCreateException() throws ServiceException {

        new MockUp<JsonUtil>() {

            @Mock
            public org.openo.sdno.model.uniformsbi.l3vpn.L3Vpn fromJson(String arg0,
                    Class<org.openo.sdno.model.uniformsbi.l3vpn.L3Vpn> arg1) {
                org.openo.sdno.model.uniformsbi.l3vpn.L3Vpn l3Vpnl = new org.openo.sdno.model.uniformsbi.l3vpn.L3Vpn();
                return l3Vpnl;
            }
        };
        new MockUp<InventoryProxy>() {

            @Mock
            public Device getControllerDevice(String arg0) throws ServiceException {
                Device device = new Device();
                device.setPort(123);
                return device;
            }
        };
        new MockUp<HttpProxy>() {

            @Mock
            public HTTPReturnMessage restInvoke(HTTPRequestMessage arg0, HTTPRequestMessage arg1) {
                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(504);
                return httpReturnMessage;
            }
        };
        new MockUp<IOUtils>() {

            @Mock
            public String toString(InputStream input) throws IOException {
                return "temp";
            }
        };
        l3vpnAcService.l3vpnCreate("spi", "ctrluuid");
    }

    @Test
    public void testl3vpnBindTpDelete() throws ServiceException {

        new MockUp<InventoryProxy>() {

            @Mock
            public Device getControllerDevice(String arg0) throws ServiceException {
                Device device = new Device();
                device.setPort(123);
                return device;
            }
        };
        new MockUp<HttpProxy>() {

            @Mock
            public HTTPReturnMessage restInvoke(HTTPRequestMessage arg0, HTTPRequestMessage arg1) {
                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);
                return httpReturnMessage;
            }
        };
    }

    @Test
    public void testl3vpnBindTpDeleteException() throws ServiceException {

        new MockUp<InventoryProxy>() {

            @Mock
            public Device getControllerDevice(String arg0) throws ServiceException {
                Device device = new Device();
                device.setPort(123);
                return device;
            }
        };
        new MockUp<HttpProxy>() {

            @Mock
            public HTTPReturnMessage restInvoke(HTTPRequestMessage arg0, HTTPRequestMessage arg1) {
                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(504);
                return httpReturnMessage;
            }
        };
        ServiceParasInfo spi = new ServiceParasInfo("uuid", "resource", "serviceBody", map, "classPath");
    }

    @Test
    public void testl3vpnDelete() throws ServiceException {

        new MockUp<InventoryProxy>() {

            @Mock
            public Device getControllerDevice(String arg0) throws ServiceException {
                Device device = new Device();
                device.setPort(123);
                return device;
            }
        };
        new MockUp<HttpProxy>() {

            @Mock
            public HTTPReturnMessage restInvoke(HTTPRequestMessage arg0, HTTPRequestMessage arg1) {
                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);
                return httpReturnMessage;
            }
        };
        ServiceParasInfo spi = new ServiceParasInfo("uuid", "resource", "serviceBody", map, "classPath");
        l3vpnAcService.l3vpnDelete("123", "121");
    }

    @Test(expected = ServiceException.class)
    public void testl3vpnDeleteException() throws ServiceException {

        new MockUp<InventoryProxy>() {

            @Mock
            public Device getControllerDevice(String arg0) throws ServiceException {
                Device device = new Device();
                device.setPort(123);
                return device;
            }
        };
        new MockUp<HttpProxy>() {

            @Mock
            public HTTPReturnMessage restInvoke(HTTPRequestMessage arg0, HTTPRequestMessage arg1) {
                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(504);
                return httpReturnMessage;
            }
        };
        ServiceParasInfo spi = new ServiceParasInfo("uuid", "resource", "serviceBody", map, "classPath");
        l3vpnAcService.l3vpnDelete("123", "121");
    }

    @Test
    public void testl3vpnUpdateDecs() throws ServiceException {
        new MockUp<JsonUtil>() {

            @Mock
            public L3Vpn fromJson(String arg0, Class<L3Vpn> arg1) {
                L3Vpn l3Vpn = new L3Vpn();
                l3Vpn.setAcs(new L3Acs());
                return l3Vpn;
            }
        };
        new MockUp<InventoryProxy>() {

            @Mock
            public Device getControllerDevice(String arg0) throws ServiceException {
                Device device = new Device();
                device.setPort(123);
                return device;
            }
        };
        new MockUp<HttpProxy>() {

            @Mock
            public HTTPReturnMessage restInvoke(HTTPRequestMessage arg0, HTTPRequestMessage arg1) {
                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);
                return httpReturnMessage;
            }
        };
        ServiceParasInfo spi = new ServiceParasInfo("uuid", "resource", "serviceBody", map, "classPath");
        l3vpnAcService.l3vpnUpdateDecs(spi);
    }

    @Test(expected = ServiceException.class)
    public void testl3vpnUpdateDecsException() throws ServiceException {
        new MockUp<JsonUtil>() {

            @Mock
            public L3Vpn fromJson(String arg0, Class<L3Vpn> arg1) {
                L3Vpn l3Vpn = new L3Vpn();
                l3Vpn.setAcs(new L3Acs());
                return l3Vpn;
            }
        };
        new MockUp<InventoryProxy>() {

            @Mock
            public Device getControllerDevice(String arg0) throws ServiceException {
                Device device = new Device();
                device.setPort(123);
                return device;
            }
        };
        new MockUp<HttpProxy>() {

            @Mock
            public HTTPReturnMessage restInvoke(HTTPRequestMessage arg0, HTTPRequestMessage arg1) {
                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(504);
                return httpReturnMessage;
            }
        };
        ServiceParasInfo spi = new ServiceParasInfo("uuid", "resource", "serviceBody", map, "classPath");
        l3vpnAcService.l3vpnUpdateDecs(spi);
    }

    @Test
    public void testl3vpnOperStatusGet() throws ServiceException {
        new MockUp<InventoryProxy>() {

            @Mock
            public Device getControllerDevice(String arg0) throws ServiceException {
                Device device = new Device();
                device.setPort(123);
                return device;
            }
        };
        new MockUp<HttpProxy>() {

            @Mock
            public HTTPReturnMessage restInvoke(HTTPRequestMessage arg0, HTTPRequestMessage arg1) {
                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);
                return httpReturnMessage;
            }
        };

        new MockUp<AbstractUnmarshallerImpl>() {

            @Mock
            public Object unmarshal(javax.xml.transform.Source source) {

                return new VpnOperStatus();
            }
        };
        ServiceParasInfo spi = new ServiceParasInfo("uuid", "resource", "serviceBody", map, "classPath");
        l3vpnAcService.l3vpnOperStatusGet(spi);
    }

    @Test
    public void testl3vpnOperStatusGetBranch() throws ServiceException {
        new MockUp<InventoryProxy>() {

            @Mock
            public Device getControllerDevice(String arg0) throws ServiceException {
                Device device = new Device();
                device.setPort(123);
                return device;
            }
        };
        new MockUp<HttpProxy>() {

            @Mock
            public HTTPReturnMessage restInvoke(HTTPRequestMessage arg0, HTTPRequestMessage arg1) {
                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);
                httpReturnMessage.setBody("body");
                return httpReturnMessage;
            }
        };

        ServiceParasInfo spi = new ServiceParasInfo("uuid", "resource", "serviceBody", map, "classPath");
        l3vpnAcService.l3vpnOperStatusGet(spi);
    }

    @Test(expected = ServiceException.class)
    public void testl3vpnOperStatusGetException() throws ServiceException {
        new MockUp<InventoryProxy>() {

            @Mock
            public Device getControllerDevice(String arg0) throws ServiceException {
                Device device = new Device();
                device.setPort(123);
                return device;
            }
        };
        new MockUp<HttpProxy>() {

            @Mock
            public HTTPReturnMessage restInvoke(HTTPRequestMessage arg0, HTTPRequestMessage arg1) {
                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(504);
                return httpReturnMessage;
            }
        };

        ServiceParasInfo spi = new ServiceParasInfo("uuid", "resource", "serviceBody", map, "classPath");
        l3vpnAcService.l3vpnOperStatusGet(spi);
    }

    @Test
    public void testl3vpnGet() throws ServiceException {
        new MockUp<InventoryProxy>() {

            @Mock
            public Device getControllerDevice(String arg0) throws ServiceException {
                Device device = new Device();
                device.setPort(123);
                return device;
            }
        };
        new MockUp<HttpProxy>() {

            @Mock
            public HTTPReturnMessage restInvoke(HTTPRequestMessage arg0, HTTPRequestMessage arg1) {
                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(200);
                httpReturnMessage.setBody("body");
                return httpReturnMessage;
            }
        };

        new MockUp<AbstractUnmarshallerImpl>() {

            @Mock
            public Object unmarshal(javax.xml.transform.Source source) {

                return new L3VpnConfig();
            }
        };

        ServiceParasInfo spi = new ServiceParasInfo("uuid", "resource", "serviceBody", map, "classPath");
        l3vpnAcService.l3vpnGet("vpcID", "ctrlID");
    }

    @Test(expected = ServiceException.class)
    public void testl3vpnGetException() throws ServiceException {
        new MockUp<InventoryProxy>() {

            @Mock
            public Device getControllerDevice(String arg0) throws ServiceException {
                Device device = new Device();
                device.setPort(123);
                return device;
            }
        };
        new MockUp<HttpProxy>() {

            @Mock
            public HTTPReturnMessage restInvoke(HTTPRequestMessage arg0, HTTPRequestMessage arg1) {
                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(504);
                return httpReturnMessage;
            }
        };

        new MockUp<AbstractUnmarshallerImpl>() {

            @Mock
            public Object unmarshal(javax.xml.transform.Source source) {

                return new L3Vpn();
            }
        };
        l3vpnAcService.l3vpnGet("vpcID", "ctrlID");
    }

    @Test
    public void testl3vpnBindTpCreate() throws ServiceException {
        new MockUp<JsonUtil>() {

            @Mock
            public L3Vpn fromJson(String arg0, Class<L3Vpn> arg1) {
                L3Vpn l3Vpn = new L3Vpn();
                l3Vpn.setAcs(new L3Acs());
                l3Vpn.setId("id");
                return l3Vpn;
            }
        };
        new MockUp<InventoryProxy>() {

            @Mock
            public Device getControllerDevice(String arg0) throws ServiceException {
                Device device = new Device();
                device.setPort(123);
                return device;
            }
        };
        new MockUp<HttpProxy>() {

            @Mock
            public HTTPReturnMessage restInvoke(HTTPRequestMessage arg0, HTTPRequestMessage arg1) {
                HTTPReturnMessage httpReturnMessage = new HTTPReturnMessage();
                httpReturnMessage.setStatus(504);
                return httpReturnMessage;
            }
        };

        ServiceParasInfo spi = new ServiceParasInfo("uuid", "resource", "serviceBody", map, "classPath");
    }
}
