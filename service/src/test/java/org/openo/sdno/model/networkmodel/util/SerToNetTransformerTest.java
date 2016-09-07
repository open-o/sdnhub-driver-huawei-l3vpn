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

package org.openo.sdno.model.networkmodel.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.acwanservice.transformer.SerToNetTransformer;
import org.openo.sdno.model.uniformsbi.base.AutoSelectPolicy;
import org.openo.sdno.model.uniformsbi.base.AutoSelectTunnel;
import org.openo.sdno.model.uniformsbi.base.AutoSelectTunnels;
import org.openo.sdno.model.uniformsbi.base.MplsTePolicy;
import org.openo.sdno.model.uniformsbi.base.PathConstraint;
import org.openo.sdno.model.uniformsbi.base.PathProtectPolicy;
import org.openo.sdno.model.uniformsbi.base.TunnelService;
import org.openo.sdno.model.uniformsbi.comnontypes.enums.AdminStatus;
import org.openo.sdno.model.uniformsbi.l3vpn.BgpRoute;
import org.openo.sdno.model.uniformsbi.l3vpn.BgpRoutes;
import org.openo.sdno.model.uniformsbi.l3vpn.DiffServ;
import org.openo.sdno.model.uniformsbi.l3vpn.HubGroup;
import org.openo.sdno.model.uniformsbi.l3vpn.HubGroups;
import org.openo.sdno.model.uniformsbi.l3vpn.IsisRoute;
import org.openo.sdno.model.uniformsbi.l3vpn.L2Access;
import org.openo.sdno.model.uniformsbi.l3vpn.L3Ac;
import org.openo.sdno.model.uniformsbi.l3vpn.L3Access;
import org.openo.sdno.model.uniformsbi.l3vpn.L3Acs;
import org.openo.sdno.model.uniformsbi.l3vpn.L3Vpn;
import org.openo.sdno.model.uniformsbi.l3vpn.ProtectGroup;
import org.openo.sdno.model.uniformsbi.l3vpn.Route;
import org.openo.sdno.model.uniformsbi.l3vpn.Routes;
import org.openo.sdno.model.uniformsbi.l3vpn.SpokeAcs;
import org.openo.sdno.model.uniformsbi.l3vpn.SpokeGroup;
import org.openo.sdno.model.uniformsbi.l3vpn.StaticRoute;
import org.openo.sdno.model.uniformsbi.l3vpn.StaticRoutes;
import org.openo.sdno.model.uniformsbi.l3vpn.TopologyService;
import org.openo.sdno.model.uniformsbi.l3vpn.Vrrp;

import mockit.Mock;
import mockit.MockUp;

public class SerToNetTransformerTest {

    public BgpRoute createBgpRoute() {
        BgpRoute bgpRoute = new BgpRoute();
        bgpRoute.setAdvertiseCommunity(true);
        bgpRoute.setAdvertiseExtCommunity(true);
        bgpRoute.setHoldTime(1);
        bgpRoute.setKeepaliveTime(1);
        bgpRoute.setLocalAs("localAs");
        bgpRoute.setLocalIp("localIp");
        bgpRoute.setPassword("pwd");
        bgpRoute.setPeerIp("peerIp");
        bgpRoute.setRemoteAs("remoteIp");
        return bgpRoute;
    }

    @Test(expected = Exception.class)
    public void testTransformModel() throws ServiceException {
        new MockUp<IOUtils>() {

            @Mock
            public String toString(InputStream input) throws IOException {
                return "temp";
            }
        };

        L3Vpn l3Vpn = new L3Vpn();
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
        TunnelService tunnelService = new TunnelService();
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
        PathProtectPolicy pathProtectPolicy = new PathProtectPolicy();
        pathProtectPolicy.setBandwidthMode("auto");
        pathProtectPolicy.setRevertive(true);
        pathProtectPolicy.setWtr(1);
        mplsTe.setPathProtectPolicy(pathProtectPolicy);
        PathConstraint pathConstraint = new PathConstraint();
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

        L3Acs l3acs = new L3Acs();
        L3Ac l3Ac = new L3Ac();
        l3Ac.setUuid("uuid");
        L2Access l2Access = new L2Access();
        l2Access.setL2AccessType("L2AccessType");
        l3Ac.setL2Access(l2Access);
        l3Ac.setNeId("neId");
        L3Access l3Access = new L3Access();
        l3Access.setIpv4Address("ipv4Address");
        Routes routes = new Routes();
        List<Route> routeList = new ArrayList<>();
        Route route = new Route();
        route.setRouteType("type");
        StaticRoutes staticRoutes = new StaticRoutes();
        List<StaticRoute> staticRouteList = new ArrayList<>();
        StaticRoute staticRoute = new StaticRoute();
        staticRoute.setIpPrefix("preFix");
        staticRoute.setNextHop("nextHop");
        staticRouteList.add(staticRoute);
        staticRoutes.setStaticRoute(staticRouteList);
        route.setStaticRoutes(staticRoutes);
        BgpRoutes bgpRoutes = new BgpRoutes();
        List<BgpRoute> bgpRouteList = new ArrayList<>();
        BgpRoute bgpRoute = createBgpRoute();
        bgpRouteList.add(bgpRoute);
        bgpRoutes.setBgpRoute(bgpRouteList);
        route.setBgpRoutes(bgpRoutes);
        IsisRoute isisRoute = new IsisRoute();
        isisRoute.setNetworkEntity("networkEntity");
        route.setIsisRoute(isisRoute);
        routeList.add(route);
        routes.setRoute(routeList);
        l3Access.setRoutes(routes);
        l3Ac.setL3Access(l3Access);

        List<L3Ac> lsitL3Ac = new ArrayList<>();
        lsitL3Ac.add(l3Ac);
        l3acs.setL3Ac(lsitL3Ac);
        l3Vpn.setAcs(l3acs);

        DiffServ diffServ = new DiffServ();
        diffServ.setServiceClass("serViceType");
        diffServ.setColor("color");
        ;
        diffServ.setMode("mode");
        l3Vpn.setDiffServ(diffServ);
        SerToNetTransformer.transformModel(l3Vpn);
    }

    @Test
    public void testTransformModelBranchL2L3Access() throws ServiceException {
        new MockUp<IOUtils>() {

            @Mock
            public String toString(InputStream input) throws IOException {
                return "temp";
            }
        };
        L3Vpn l3Vpn = new L3Vpn();
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
        hubGroups.setHubGroup(null);
        topologyService.setHubGroups(null);

        SpokeGroup spokeGroup = new SpokeGroup();
        spokeGroup.setLocalBridge(true);
        List<SpokeAcs> listSpokeAcs = new ArrayList<>();
        SpokeAcs spokeAcs = new SpokeAcs();
        spokeAcs.setAcId("acID");
        listSpokeAcs.add(spokeAcs);
        spokeGroup.setSpokeAcs(null);
        topologyService.setSpokeGroup(null);
        l3Vpn.setTopologyService(topologyService);
        TunnelService tunnelService = new TunnelService();
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
        PathProtectPolicy pathProtectPolicy = new PathProtectPolicy();
        pathProtectPolicy.setBandwidthMode("auto");
        pathProtectPolicy.setRevertive(true);
        pathProtectPolicy.setWtr(1);
        mplsTe.setPathProtectPolicy(null);
        PathConstraint pathConstraint = new PathConstraint();
        pathConstraint.setHoldupPriority(1);
        pathConstraint.setSetupPriority(1);
        pathConstraint.setLatency(1);
        mplsTe.setPathConstraint(null);

        tunnelService.setMplsTe(mplsTe);
        l3Vpn.setTunnelService(tunnelService);
        l3Vpn.setFrr(true);
        ProtectGroup protectGroup = new ProtectGroup();
        protectGroup.setBackupAc("backUpAc");
        protectGroup.setMasterAc("masterAc");
        Vrrp vrrp = new Vrrp();
        vrrp.setVirtualIp("virtualIp");
        protectGroup.setVrrp(null);
        l3Vpn.setProtectGroup(protectGroup);

        L3Acs l3acs = new L3Acs();
        L3Ac l3Ac = new L3Ac();
        l3Ac.setUuid("uuid");
        L2Access l2Access = new L2Access();
        l2Access.setL2AccessType("L2AccessType");
        l3Ac.setL2Access(null);
        l3Ac.setNeId("neId");
        L3Access l3Access = new L3Access();
        l3Access.setIpv4Address("ipv4Address");
        l3Access.setRoutes(new Routes());
        l3Ac.setL3Access(null);
        List<L3Ac> lsitL3Ac = new ArrayList<>();
        lsitL3Ac.add(l3Ac);
        l3acs.setL3Ac(lsitL3Ac);
        l3Vpn.setAcs(l3acs);
        DiffServ diffServ = new DiffServ();
        diffServ.setServiceClass("serViceType");
        diffServ.setColor("color");
        diffServ.setMode("mode");
        l3Vpn.setDiffServ(diffServ);
        SerToNetTransformer.transformModel(l3Vpn);
    }

    @Test(expected = Exception.class)
    public void testTransformModelBrachRemaining1() throws ServiceException {
        new MockUp<IOUtils>() {

            @Mock
            public String toString(InputStream input) throws IOException {
                return "temp";
            }
        };
        L3Vpn l3Vpn = new L3Vpn();
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
        spokeGroup.setSpokeAcs(null);
        topologyService.setSpokeGroup(spokeGroup);
        l3Vpn.setTopologyService(topologyService);
        TunnelService tunnelService = new TunnelService();
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
        tunnelService.setAutoSelect(null);
        MplsTePolicy mplsTe = new MplsTePolicy();
        mplsTe.setBandwidth(123);
        mplsTe.setManageProtocol("mangeProtocol");
        mplsTe.setSignalType("strongSignal");
        PathProtectPolicy pathProtectPolicy = new PathProtectPolicy();
        pathProtectPolicy.setBandwidthMode("auto");
        pathProtectPolicy.setRevertive(true);
        pathProtectPolicy.setWtr(1);
        mplsTe.setPathProtectPolicy(pathProtectPolicy);
        PathConstraint pathConstraint = new PathConstraint();
        pathConstraint.setHoldupPriority(1);
        pathConstraint.setSetupPriority(1);
        pathConstraint.setLatency(1);
        mplsTe.setPathConstraint(pathConstraint);

        tunnelService.setMplsTe(null);
        l3Vpn.setTunnelService(tunnelService);
        l3Vpn.setFrr(true);
        ProtectGroup protectGroup = new ProtectGroup();
        protectGroup.setBackupAc("backUpAc");
        protectGroup.setMasterAc("masterAc");
        Vrrp vrrp = new Vrrp();
        vrrp.setVirtualIp("virtualIp");
        protectGroup.setVrrp(vrrp);
        l3Vpn.setProtectGroup(protectGroup);

        L3Acs l3acs = new L3Acs();
        L3Ac l3Ac = new L3Ac();
        l3Ac.setUuid("uuid");
        L2Access l2Access = new L2Access();
        l2Access.setL2AccessType("L2AccessType");
        l2Access.setPushVlanId("pushVlanID");
        l3Ac.setL2Access(l2Access);
        l3Ac.setNeId("neId");
        L3Access l3Access = new L3Access();
        l3Access.setIpv4Address("ipv4Address");
        l3Access.setRoutes(null);
        l3Ac.setL3Access(l3Access);
        List<L3Ac> lsitL3Ac = new ArrayList<>();
        lsitL3Ac.add(l3Ac);
        l3acs.setL3Ac(lsitL3Ac);
        l3Vpn.setAcs(l3acs);
        DiffServ diffServ = new DiffServ();
        diffServ.setServiceClass(null);
        diffServ.setColor(null);
        diffServ.setMode(null);
        l3Vpn.setDiffServ(diffServ);
        SerToNetTransformer.transformModel(l3Vpn);
    }

    @Test
    public void testTransformModelBrachRemaining() throws ServiceException {
        new MockUp<IOUtils>() {

            @Mock
            public String toString(InputStream input) throws IOException {
                return "temp";
            }
        };
        L3Vpn l3Vpn = new L3Vpn();
        AdminStatus adminStatus = AdminStatus.ADMIN_DOWN;
        // adminStatus.setName("name");
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
        spokeGroup.setSpokeAcs(null);
        topologyService.setSpokeGroup(spokeGroup);
        l3Vpn.setTopologyService(topologyService);
        TunnelService tunnelService = new TunnelService();
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
        tunnelService.setAutoSelect(null);
        MplsTePolicy mplsTe = new MplsTePolicy();
        mplsTe.setBandwidth(123);
        mplsTe.setManageProtocol("mangeProtocol");
        mplsTe.setSignalType("strongSignal");
        PathProtectPolicy pathProtectPolicy = new PathProtectPolicy();
        pathProtectPolicy.setBandwidthMode("auto");
        pathProtectPolicy.setRevertive(true);
        pathProtectPolicy.setWtr(1);
        mplsTe.setPathProtectPolicy(pathProtectPolicy);
        PathConstraint pathConstraint = new PathConstraint();
        pathConstraint.setHoldupPriority(1);
        pathConstraint.setSetupPriority(1);
        pathConstraint.setLatency(1);
        mplsTe.setPathConstraint(pathConstraint);

        tunnelService.setMplsTe(mplsTe);
        l3Vpn.setTunnelService(null);
        l3Vpn.setFrr(true);
        ProtectGroup protectGroup = new ProtectGroup();
        protectGroup.setBackupAc("backUpAc");
        protectGroup.setMasterAc("masterAc");
        Vrrp vrrp = new Vrrp();
        vrrp.setVirtualIp("virtualIp");
        protectGroup.setVrrp(vrrp);
        l3Vpn.setProtectGroup(protectGroup);

        L3Acs l3acs = new L3Acs();
        L3Ac l3Ac = new L3Ac();
        l3Ac.setUuid("uuid");
        L2Access l2Access = new L2Access();
        l2Access.setL2AccessType("L2AccessType");
        l3Ac.setL2Access(l2Access);
        l3Ac.setNeId("neId");
        L3Access l3Access = new L3Access();
        l3Access.setIpv4Address("ipv4Address");
        l3Access.setRoutes(null);
        l3Ac.setL3Access(l3Access);
        List<L3Ac> lsitL3Ac = new ArrayList<>();
        lsitL3Ac.add(l3Ac);
        l3acs.setL3Ac(lsitL3Ac);
        l3Vpn.setAcs(l3acs);
        DiffServ diffServ = new DiffServ();
        diffServ.setServiceClass(null);
        diffServ.setColor(null);
        diffServ.setMode(null);
        l3Vpn.setDiffServ(diffServ);
        SerToNetTransformer.transformModel(l3Vpn);
    }

    @Test
    public void testTransformModelBranch4Routes() throws ServiceException {
        new MockUp<IOUtils>() {

            @Mock
            public String toString(InputStream input) throws IOException {
                return "temp";
            }
        };
        L3Vpn l3Vpn = new L3Vpn();
        AdminStatus adminStatus = AdminStatus.ADMIN_DOWN;
        l3Vpn.setAdminStatus(adminStatus);
        l3Vpn.setUuid("uuid");
        l3Vpn.setName("name");

        l3Vpn.setTopologyService(null);

        TunnelService tunnelService = new TunnelService();
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
        autoSelectPolicy.setAutoSelectTunnels(null);
        tunnelService.setAutoSelect(null);
        MplsTePolicy mplsTe = new MplsTePolicy();
        mplsTe.setBandwidth(123);
        mplsTe.setManageProtocol("mangeProtocol");
        mplsTe.setSignalType("strongSignal");
        PathProtectPolicy pathProtectPolicy = new PathProtectPolicy();
        pathProtectPolicy.setBandwidthMode("auto");
        pathProtectPolicy.setRevertive(true);
        pathProtectPolicy.setWtr(1);
        mplsTe.setPathProtectPolicy(null);
        PathConstraint pathConstraint = new PathConstraint();
        pathConstraint.setHoldupPriority(1);
        pathConstraint.setSetupPriority(1);
        pathConstraint.setLatency(1);
        mplsTe.setPathConstraint(null);

        tunnelService.setMplsTe(null);
        l3Vpn.setTunnelService(tunnelService);

        l3Vpn.setFrr(null);
        l3Vpn.setProtectGroup(null);

        L3Acs l3acs = new L3Acs();
        L3Ac l3Ac = new L3Ac();
        l3Ac.setUuid("uuid");
        L2Access l2Access = new L2Access();
        l2Access.setL2AccessType("L2AccessType");
        l3Ac.setL2Access(l2Access);
        l3Ac.setNeId("neId");
        L3Access l3Access = new L3Access();
        l3Access.setIpv4Address("ipv4Address");
        Routes routes = new Routes();
        List<Route> routeList = new ArrayList<>();
        Route route = new Route();
        route.setRouteType("type");
        StaticRoutes staticRoutes = new StaticRoutes();
        List<StaticRoute> staticRouteList = new ArrayList<>();
        StaticRoute staticRoute = new StaticRoute();
        staticRoute.setIpPrefix("preFix");
        staticRoute.setNextHop("nextHop");
        staticRouteList.add(staticRoute);
        staticRoutes.setStaticRoute(staticRouteList);
        route.setStaticRoutes(null);
        BgpRoutes bgpRoutes = new BgpRoutes();
        List<BgpRoute> bgpRouteList = new ArrayList<>();
        BgpRoute bgpRoute = createBgpRoute();
        bgpRouteList.add(bgpRoute);
        bgpRoutes.setBgpRoute(bgpRouteList);
        route.setBgpRoutes(bgpRoutes);
        IsisRoute isisRoute = new IsisRoute();
        isisRoute.setNetworkEntity("networkEntity");
        route.setIsisRoute(isisRoute);
        routes.setRoute(routeList);
        l3Access.setRoutes(routes);
        l3Ac.setL3Access(l3Access);

        List<L3Ac> lsitL3Ac = new ArrayList<>();
        lsitL3Ac.add(l3Ac);
        l3acs.setL3Ac(lsitL3Ac);
        l3Vpn.setAcs(l3acs);

        l3Vpn.setDiffServ(null);
        SerToNetTransformer.transformModel(l3Vpn);
    }

    @Test
    public void testTransformModelBranch5StaticRoutes() throws ServiceException {
        new MockUp<IOUtils>() {

            @Mock
            public String toString(InputStream input) throws IOException {
                return "temp";
            }
        };
        L3Vpn l3Vpn = new L3Vpn();
        AdminStatus adminStatus = AdminStatus.ADMIN_DOWN;
        l3Vpn.setAdminStatus(adminStatus);
        l3Vpn.setUuid("uuid");
        l3Vpn.setName("name");

        l3Vpn.setTopologyService(null);
        TunnelService tunnelService = new TunnelService();
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
        autoSelectPolicy.setAutoSelectTunnels(null);
        tunnelService.setAutoSelect(autoSelectPolicy);
        MplsTePolicy mplsTe = new MplsTePolicy();
        mplsTe.setBandwidth(123);
        mplsTe.setManageProtocol("mangeProtocol");
        mplsTe.setSignalType("strongSignal");
        PathProtectPolicy pathProtectPolicy = new PathProtectPolicy();
        pathProtectPolicy.setBandwidthMode("auto");
        pathProtectPolicy.setRevertive(true);
        pathProtectPolicy.setWtr(1);
        mplsTe.setPathProtectPolicy(null);
        PathConstraint pathConstraint = new PathConstraint();
        pathConstraint.setHoldupPriority(1);
        pathConstraint.setSetupPriority(1);
        pathConstraint.setLatency(1);
        mplsTe.setPathConstraint(null);

        tunnelService.setMplsTe(mplsTe);
        l3Vpn.setTunnelService(tunnelService);
        l3Vpn.setFrr(null);

        l3Vpn.setProtectGroup(null);

        L3Acs l3acs = new L3Acs();
        L3Ac l3Ac = new L3Ac();
        l3Ac.setUuid("uuid");
        L2Access l2Access = new L2Access();
        l2Access.setL2AccessType("L2AccessType");
        l3Ac.setL2Access(l2Access);
        l3Ac.setNeId("neId");
        L3Access l3Access = new L3Access();
        l3Access.setIpv4Address("ipv4Address");
        Routes routes = new Routes();
        List<Route> routeList = new ArrayList<>();
        Route route = new Route();
        route.setRouteType("type");
        StaticRoutes staticRoutes = new StaticRoutes();
        List<StaticRoute> staticRouteList = new ArrayList<>();
        StaticRoute staticRoute = new StaticRoute();
        staticRoute.setIpPrefix("preFix");
        staticRoute.setNextHop("nextHop");
        staticRouteList.add(staticRoute);
        staticRoutes.setStaticRoute(staticRouteList);
        route.setStaticRoutes(null);
        BgpRoutes bgpRoutes = new BgpRoutes();
        List<BgpRoute> bgpRouteList = new ArrayList<>();
        BgpRoute bgpRoute = createBgpRoute();
        bgpRouteList.add(bgpRoute);
        bgpRoutes.setBgpRoute(bgpRouteList);
        route.setBgpRoutes(null);
        IsisRoute isisRoute = new IsisRoute();
        isisRoute.setNetworkEntity("networkEntity");
        route.setIsisRoute(isisRoute);
        routeList.add(route);
        routes.setRoute(routeList);
        l3Access.setRoutes(routes);
        l3Ac.setL3Access(l3Access);
        List<L3Ac> lsitL3Ac = new ArrayList<>();
        lsitL3Ac.add(l3Ac);
        l3acs.setL3Ac(lsitL3Ac);
        l3Vpn.setAcs(l3acs);
        l3Vpn.setDiffServ(null);
        SerToNetTransformer.transformModel(l3Vpn);
    }

    @Test
    public void testTransformModelBranch6StaticRoutes() throws ServiceException {
        new MockUp<IOUtils>() {

            @Mock
            public String toString(InputStream input) throws IOException {
                return "temp";
            }
        };
        L3Vpn l3Vpn = new L3Vpn();
        AdminStatus adminStatus = AdminStatus.ADMIN_DOWN;
        l3Vpn.setAdminStatus(null);
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

        TunnelService tunnelService = new TunnelService();
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
        PathProtectPolicy pathProtectPolicy = new PathProtectPolicy();
        pathProtectPolicy.setBandwidthMode("auto");
        pathProtectPolicy.setRevertive(true);
        pathProtectPolicy.setWtr(1);
        mplsTe.setPathProtectPolicy(pathProtectPolicy);
        PathConstraint pathConstraint = new PathConstraint();
        pathConstraint.setHoldupPriority(1);
        pathConstraint.setSetupPriority(1);
        pathConstraint.setLatency(1);
        mplsTe.setPathConstraint(pathConstraint);
        tunnelService.setMplsTe(mplsTe);
        l3Vpn.setTunnelService(tunnelService);

        l3Vpn.setFrr(null);

        l3Vpn.setProtectGroup(null);

        L3Acs l3acs = new L3Acs();
        L3Ac l3Ac = new L3Ac();
        l3Ac.setUuid("uuid");
        L2Access l2Access = new L2Access();
        l2Access.setL2AccessType("L2AccessType");
        l3Ac.setL2Access(l2Access);
        l3Ac.setNeId("neId");
        L3Access l3Access = new L3Access();
        l3Access.setIpv4Address("ipv4Address");
        Routes routes = new Routes();
        List<Route> routeList = new ArrayList<>();
        Route route = new Route();
        route.setRouteType("type");
        StaticRoutes staticRoutes = new StaticRoutes();
        List<StaticRoute> staticRouteList = new ArrayList<>();
        StaticRoute staticRoute = new StaticRoute();
        staticRoute.setIpPrefix("preFix");
        staticRoute.setNextHop("nextHop");
        // staticRouteList.add(staticRoute);
        staticRoutes.setStaticRoute(staticRouteList);
        route.setStaticRoutes(staticRoutes);
        BgpRoutes bgpRoutes = new BgpRoutes();
        List<BgpRoute> bgpRouteList = new ArrayList<>();
        BgpRoute bgpRoute = createBgpRoute();
        bgpRouteList.add(bgpRoute);
        bgpRoutes.setBgpRoute(bgpRouteList);
        route.setBgpRoutes(bgpRoutes);
        IsisRoute isisRoute = new IsisRoute();
        isisRoute.setNetworkEntity("networkEntity");
        route.setIsisRoute(null);
        routeList.add(route);
        routes.setRoute(routeList);
        l3Access.setRoutes(routes);
        l3Ac.setL3Access(l3Access);

        List<L3Ac> lsitL3Ac = new ArrayList<>();
        lsitL3Ac.add(l3Ac);
        l3acs.setL3Ac(lsitL3Ac);
        l3Vpn.setAcs(l3acs);

        l3Vpn.setDiffServ(null);
        SerToNetTransformer.transformModel(l3Vpn);
    }

    @Test
    public void testTransformModelBranch7BgpRoute() throws ServiceException {
        new MockUp<IOUtils>() {

            @Mock
            public String toString(InputStream input) throws IOException {
                return "temp";
            }
        };
        L3Vpn l3Vpn = new L3Vpn();
        AdminStatus adminStatus = AdminStatus.ADMIN_DOWN;
        l3Vpn.setAdminStatus(null);
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
        spokeGroup.setSpokeAcs(listSpokeAcs);
        topologyService.setSpokeGroup(spokeGroup);
        l3Vpn.setTopologyService(topologyService);

        l3Vpn.setTunnelService(null);

        l3Vpn.setFrr(null);

        l3Vpn.setProtectGroup(null);

        L3Acs l3acs = new L3Acs();
        L3Ac l3Ac = new L3Ac();
        l3Ac.setUuid("uuid");
        L2Access l2Access = new L2Access();
        l2Access.setL2AccessType("L2AccessType");
        l3Ac.setL2Access(l2Access);
        l3Ac.setNeId("neId");
        L3Access l3Access = new L3Access();
        l3Access.setIpv4Address("ipv4Address");
        Routes routes = new Routes();
        List<Route> routeList = new ArrayList<>();
        Route route = new Route();
        route.setRouteType("type");
        StaticRoutes staticRoutes = new StaticRoutes();
        List<StaticRoute> staticRouteList = new ArrayList<>();
        StaticRoute staticRoute = new StaticRoute();
        staticRoute.setIpPrefix("preFix");
        staticRoute.setNextHop("nextHop");
        staticRoutes.setStaticRoute(staticRouteList);
        route.setStaticRoutes(staticRoutes);
        BgpRoutes bgpRoutes = new BgpRoutes();
        List<BgpRoute> bgpRouteList = new ArrayList<>();
        BgpRoute bgpRoute = createBgpRoute();
        bgpRouteList.add(bgpRoute);
        bgpRoutes.setBgpRoute(bgpRouteList);
        route.setBgpRoutes(bgpRoutes);
        IsisRoute isisRoute = new IsisRoute();
        isisRoute.setNetworkEntity("networkEntity");
        route.setIsisRoute(null);
        routeList.add(route);
        routes.setRoute(routeList);
        l3Access.setRoutes(routes);
        l3Ac.setL3Access(l3Access);

        List<L3Ac> lsitL3Ac = new ArrayList<>();
        lsitL3Ac.add(l3Ac);
        l3acs.setL3Ac(lsitL3Ac);
        l3Vpn.setAcs(l3acs);

        l3Vpn.setDiffServ(null);
        SerToNetTransformer.transformModel(l3Vpn);
    }

    @Test
    public void testTransformModelBranch8() throws ServiceException {
        new MockUp<IOUtils>() {

            @Mock
            public String toString(InputStream input) throws IOException {
                return "temp";
            }
        };
        L3Vpn l3Vpn = new L3Vpn();
        AdminStatus adminStatus = AdminStatus.ADMIN_DOWN;
        l3Vpn.setAdminStatus(null);
        l3Vpn.setUuid("uuid");
        l3Vpn.setName("name");

        l3Vpn.setTopologyService(null);

        l3Vpn.setTunnelService(null);

        l3Vpn.setFrr(null);

        l3Vpn.setProtectGroup(null);

        l3Vpn.setAcs(null);

        l3Vpn.setDiffServ(null);
        SerToNetTransformer.transformModel(l3Vpn);
    }
}
