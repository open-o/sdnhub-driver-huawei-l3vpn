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

package org.openo.sdnhub.acwanservice.transformer;

import java.util.ArrayList;
import java.util.List;

import org.openo.sdnhub.model.networkmodel.servicetypes.BgpPeer;
import org.openo.sdnhub.model.networkmodel.servicetypes.HubAc;
import org.openo.sdnhub.model.networkmodel.servicetypes.L3Ac;
import org.openo.sdnhub.model.networkmodel.servicetypes.L3Acs;
import org.openo.sdnhub.model.networkmodel.servicetypes.L3Vpn;
import org.openo.sdnhub.model.networkmodel.servicetypes.MplsTe;
import org.openo.sdnhub.model.networkmodel.servicetypes.Protocol;
import org.openo.sdnhub.model.networkmodel.servicetypes.StaticRoute;
import org.openo.sdnhub.model.networkmodel.servicetypes.TopoService;
import org.openo.sdnhub.model.networkmodel.servicetypes.TunnelService;
import org.openo.sdno.model.uniformsbi.comnontypes.enums.RouteType;
import org.openo.sdno.model.uniformsbi.l3vpn.BgpRoute;
import org.openo.sdno.model.uniformsbi.l3vpn.BgpRoutes;
import org.openo.sdno.model.uniformsbi.l3vpn.HubGroup;
import org.openo.sdno.model.uniformsbi.l3vpn.IsisRoute;
import org.openo.sdno.model.uniformsbi.l3vpn.Route;
import org.openo.sdno.model.uniformsbi.l3vpn.Routes;
import org.openo.sdno.model.uniformsbi.l3vpn.SpokeAc;
import org.openo.sdno.model.uniformsbi.l3vpn.SpokeGroup;
import org.openo.sdno.model.uniformsbi.l3vpn.StaticRoutes;

/**
 * class for transforming network to service model<br>
 *
 * @author
 * @version SDNO 0.5 August 22, 2016
 */
public class NetToSerTransformer {

    private static String topology = "fullMesh";

    private NetToSerTransformer() {

    }

    /**
     * Transform model from network to service
     * <br>
     *
     * @param l3VpnConfig is a network model VPN configuration
     * @return service instance of uniform SBI
     * @since SDNHUB 0.5
     */
    public static org.openo.sdno.model.uniformsbi.l3vpn.L3Vpn transformModel(L3Vpn l3Vpn) {

        org.openo.sdno.model.uniformsbi.l3vpn.L3Vpn nbil3Vpn = new org.openo.sdno.model.uniformsbi.l3vpn.L3Vpn();

        nbil3Vpn.setUuid(l3Vpn.getId());
        nbil3Vpn.setName(l3Vpn.getName());
        nbil3Vpn.setAdminStatusFromString(l3Vpn.getAdminStatus());

        // Topology set to hubSpoke as controller model does not support topology
        nbil3Vpn.setDescription(l3Vpn.getName());

        if(l3Vpn.getFrr() != null) {
            nbil3Vpn.setFrr(Boolean.valueOf(l3Vpn.getFrr()));
        }

        org.openo.sdno.model.uniformsbi.l3vpn.DiffServ diffServ = new org.openo.sdno.model.uniformsbi.l3vpn.DiffServ();
        if(l3Vpn.getDiffServ() != null) {
            diffServ.setColor(l3Vpn.getDiffServ().getColor());
            diffServ.setMode(l3Vpn.getDiffServ().getMode());
            diffServ.setServiceClass(l3Vpn.getDiffServ().getServiceClass());
        }
        nbil3Vpn.setDiffServ(diffServ);

        org.openo.sdno.model.uniformsbi.l3vpn.TopologyService topologyService =
                transformTopoService(l3Vpn.getTopoService());
        nbil3Vpn.setTopologyService(topologyService);
        nbil3Vpn.setTopology(topology);

        org.openo.sdno.model.uniformsbi.l3vpn.L3LoopbackIfs l3Loopbackifs =
                new org.openo.sdno.model.uniformsbi.l3vpn.L3LoopbackIfs();
        nbil3Vpn.setL3Loopbackifs(l3Loopbackifs);

        org.openo.sdno.model.uniformsbi.l3vpn.L3Acs l3Acs = transformAcs(l3Vpn.getAcs());
        nbil3Vpn.setAcs(l3Acs);
        nbil3Vpn.setIpMtu(l3Vpn.getIpMtu());
        nbil3Vpn.setTunnelService(transformTunnelService(l3Vpn.getTunnelService()));

        return nbil3Vpn;
    }

    /**
     * Transform attachment circuits associated with the VPN from network to service
     * <br>
     *
     * @param acs is a network attachment circuits
     * @return service instance of attachment circuits
     * @since SDNHUB 0.5
     */
    private static org.openo.sdno.model.uniformsbi.l3vpn.L3Acs transformAcs(L3Acs acs) {

        org.openo.sdno.model.uniformsbi.l3vpn.L3Acs l3Acs = new org.openo.sdno.model.uniformsbi.l3vpn.L3Acs();
        List<org.openo.sdno.model.uniformsbi.l3vpn.L3Ac> listL3Ac = new ArrayList<>();

        for(L3Ac l3ac : acs.getL3Ac()) {
            org.openo.sdno.model.uniformsbi.l3vpn.L3Ac ctrlrl3ac = new org.openo.sdno.model.uniformsbi.l3vpn.L3Ac();
            ctrlrl3ac.setUuid(l3ac.getId());
            ctrlrl3ac.setNeId(l3ac.getNeId());

            if(l3ac.getL2Access() != null) {
                org.openo.sdno.model.uniformsbi.l3vpn.L2Access l2access =
                        new org.openo.sdno.model.uniformsbi.l3vpn.L2Access();
                l2access.setL2AccessType(l3ac.getL2Access().getAccessType());
                l2access.setDot1qVlanBitmap(l3ac.getL2Access().getDot1q().getVlanId());
                l2access.setPushVlanId(String.valueOf(l3ac.getL2Access().getDot1q().getVlanId()));

                ctrlrl3ac.setL2Access(l2access);
            }

            if(l3ac.getL3Access() != null) {

                org.openo.sdno.model.uniformsbi.l3vpn.L3Access l3Access =
                        new org.openo.sdno.model.uniformsbi.l3vpn.L3Access();
                l3Access.setIpv4Address(l3ac.getL3Access().getAddress());

                Routes routes = new Routes();
                List<Route> routeList = new ArrayList<>();

                procStaticRoutesTransformAcs(l3ac, routeList);
                procProtocolsTransformAcs(l3ac, routeList);
                routes.setRoute(routeList);

                l3Access.setRoutes(routes);
                ctrlrl3ac.setL3Access(l3Access);
            }
            listL3Ac.add(ctrlrl3ac);
        }
        l3Acs.setL3Ac(listL3Ac);
        return l3Acs;
    }

    /**
     * Transform attachment circuits associated with the VPN from network to service on Static Routes.
     * <br>
     *
     * @param l3ac is a network attachment circuits
     * @param routeList collection of routing information
     * @since SDNHUB 0.5
     */
    private static void procStaticRoutesTransformAcs(L3Ac l3ac, List<Route> routeList) {
        if(l3ac.getL3Access().getStaticRoutes() != null) {
            Route route = new Route();
            StaticRoutes sroutes = new StaticRoutes();
            List<org.openo.sdno.model.uniformsbi.l3vpn.StaticRoute> staticRouteList = new ArrayList<>();

            for(StaticRoute staticRoute : l3ac.getL3Access().getStaticRoutes()) {
                org.openo.sdno.model.uniformsbi.l3vpn.Route nbiRoute =
                        new org.openo.sdno.model.uniformsbi.l3vpn.Route();
                nbiRoute.setRouteType(RouteType.STATIC.getAlias());

                org.openo.sdno.model.uniformsbi.l3vpn.StaticRoute sr =
                        new org.openo.sdno.model.uniformsbi.l3vpn.StaticRoute();
                sr.setIpPrefix(staticRoute.getIpPrefix());
                sr.setNextHop(staticRoute.getNextHop());
                staticRouteList.add(sr);
            }
            sroutes.setStaticRoute(staticRouteList);

            route.setStaticRoutes(sroutes);
            route.setRouteType("static");
            routeList.add(route);
        }
    }

    /**
     * Transform attachment circuits associated with the VPN from network to service on protocols.
     * <br>
     *
     * @param l3ac is a network attachment circuits
     * @param routeList collection of routing information
     * @since SDNHUB 0.5
     */
    private static void procProtocolsTransformAcs(L3Ac l3ac, List<Route> routeList) {
        if(l3ac.getL3Access().getProtocols() != null) {
            for(Protocol protocol : l3ac.getL3Access().getProtocols()) {
                if(protocol.getBgp() != null) {
                    Route route = new Route();
                    BgpRoutes bgps = new BgpRoutes();
                    List<BgpRoute> bgpRouteList = new ArrayList<>();
                    for(BgpPeer bgp : protocol.getBgp()) {
                        BgpRoute bgproute = new BgpRoute();
                        bgproute.setAdvertiseCommunity(bgp.getAdvertiseCommunity());
                        bgproute.setAdvertiseExtCommunity(bgp.getAdvertiseExtCommunity());
                        bgproute.setHoldTime(bgp.getHoldTime());
                        bgproute.setKeepaliveTime(bgp.getKeepAliveTime());
                        bgproute.setPassword(bgp.getPassword());
                        bgproute.setPeerIp(bgp.getPeerIp());
                        bgproute.setRemoteAs(bgp.getRemoteAs());
                        bgpRouteList.add(bgproute);
                    }
                    bgps.setBgpRoute(bgpRouteList);
                    route.setBgpRoutes(bgps);
                    routeList.add(route);
                }

                if(protocol.getIsis() != null) {
                    Route route = new Route();
                    IsisRoute isis = new IsisRoute();
                    isis.setNetworkEntity(protocol.getIsis().getNetworkEntity());
                    route.setIsisRoute(isis);
                    routeList.add(route);
                }

                if(protocol.getOspf() != null) {
                    // OSPF related
                }
            }
        }
    }

    /**
     * Transform tunnel service of l3vpn from network to service model
     * <br>
     *
     * @param tunnelService is a network tunnel service
     * @return service instance of uniform sbi's tunnel service
     * @since SDNHUB 0.5
     */
    private static org.openo.sdno.model.uniformsbi.base.TunnelService
            transformTunnelService(TunnelService tunnelService) {
        org.openo.sdno.model.uniformsbi.base.TunnelService ts =
                new org.openo.sdno.model.uniformsbi.base.TunnelService();

        ts.setType(tunnelService.getType());

        if(tunnelService.getAutoSelect() != null) {
            org.openo.sdno.model.uniformsbi.base.AutoSelectPolicy autoSelectPolicy =
                    new org.openo.sdno.model.uniformsbi.base.AutoSelectPolicy();
            autoSelectPolicy.setLoadBalanceNumber(tunnelService.getAutoSelect().getLoadBalanceNumber());
            ts.setAutoSelect(autoSelectPolicy);
        }

        if(tunnelService.getMplsTe() != null) {
            ts.setMplsTe(transformMplsTe(tunnelService.getMplsTe()));
        }

        return ts;
    }

    /**
     * Transform MPLS traffic engineering data from network to service model
     * <br>
     *
     * @param mplsTe is a network MPLS traffic engineering data
     * @return service instance of uniform sbi's MPLS traffic engineering data
     * @since SDNHUB 0.5
     */
    private static org.openo.sdno.model.uniformsbi.base.MplsTePolicy transformMplsTe(MplsTe mplsTe) {

        org.openo.sdno.model.uniformsbi.base.MplsTePolicy mplsObj =
                new org.openo.sdno.model.uniformsbi.base.MplsTePolicy();
        mplsObj.setBandwidth(mplsTe.getBandwidth());
        mplsObj.setManageProtocol(mplsTe.getManageProtocol());
        mplsObj.setSignalType(mplsTe.getSignalType());
        mplsObj.setSharing(mplsTe.getSharing());
        mplsObj.setBesteffort(mplsTe.getBestEffort());
        mplsObj.setBfdEnable(mplsTe.getBfdEnable());
        mplsObj.setCoRoute(mplsTe.getCoRoute());

        if(mplsTe.getPathProtectPolicy() != null) {
            org.openo.sdno.model.uniformsbi.base.PathProtectPolicy ppp =
                    new org.openo.sdno.model.uniformsbi.base.PathProtectPolicy();
            ppp.setBandwidthMode(mplsTe.getPathProtectPolicy().getBandwidthMode());
            ppp.setHotStandbyEnable(mplsTe.getPathProtectPolicy().getHotStandby());
            ppp.setRevertive(mplsTe.getPathProtectPolicy().getRevertive());
            ppp.setWtr(mplsTe.getPathProtectPolicy().getWtr());
            mplsObj.setPathProtectPolicy(ppp);
        }
        if(mplsTe.getPathConstraint() != null) {
            org.openo.sdno.model.uniformsbi.base.PathConstraint pathConstraint =
                    new org.openo.sdno.model.uniformsbi.base.PathConstraint();
            pathConstraint.setHoldupPriority(mplsTe.getPathConstraint().getHoldupPriority());
            pathConstraint.setLatency(mplsTe.getPathConstraint().getLatency());
            pathConstraint.setSetupPriority(mplsTe.getPathConstraint().getSetupPriority());
            mplsObj.setPathConstraint(pathConstraint);
        }

        return mplsObj;
    }

    /**
     * Transform topology service from network to service model
     * <br>
     *
     * @param topologyService is a network topology service
     * @return service instance of uniform sbi's topology service
     * @since SDNHUB 0.5
     */
    private static org.openo.sdno.model.uniformsbi.l3vpn.TopologyService
            transformTopoService(TopoService topologyService) {

        if(topologyService == null) {
            return null;
        }

        org.openo.sdno.model.uniformsbi.l3vpn.TopologyService topoService =
                new org.openo.sdno.model.uniformsbi.l3vpn.TopologyService();
        org.openo.sdno.model.uniformsbi.l3vpn.HubGroups hubGroup =
                new org.openo.sdno.model.uniformsbi.l3vpn.HubGroups();
        SpokeGroup spokeGroup = new SpokeGroup();

        if(topologyService.getHubSpoke() != null) {
            topology = "hubSpoke";
            if(topologyService.getHubSpoke().getHubGroup() != null) {
                hubGroup.setHubGroup(transformHubGroup(topologyService.getHubSpoke().getHubGroup()));
            }

            if(topologyService.getHubSpoke().getSpokeGroup() != null) {
                spokeGroup.setLocalBridge(topologyService.getHubSpoke().getSpokeGroup().getLocalBridge());
                spokeGroup
                        .setSpokeAcs(transformSpokeGroup(topologyService.getHubSpoke().getSpokeGroup().getSpokeAcs()));
            }
            topoService.setSpokeGroup(spokeGroup);
            topoService.setHubGroups(hubGroup);
        }

        return topoService;
    }

    /**
     * Transform spoke attachment circuits from network to service model
     * <br>
     *
     * @param spokeAcs is a network spoke attachment circuits
     * @return service instance of uniform sbi's spoke attachment circuits
     * @since SDNHUB 0.5
     */
    private static List<SpokeAc>
            transformSpokeGroup(List<org.openo.sdnhub.model.networkmodel.servicetypes.SpokeAc> spokeAcs) {

        List<SpokeAc> nbispokeAcs = new ArrayList<>();
        for(org.openo.sdnhub.model.networkmodel.servicetypes.SpokeAc spokeAc : spokeAcs) {
            SpokeAc acs = new SpokeAc();
            acs.setAcId(spokeAc.getAcId());
            nbispokeAcs.add(acs);
        }

        return nbispokeAcs;
    }

    /**
     * Transform hub attachment circuits from network to service model
     * <br>
     *
     * @param hubGroup is a network hub attachment circuits
     * @return service instance of uniform sbi's hub attachment circuits
     * @since SDNHUB 0.5
     */
    private static List<HubGroup> transformHubGroup(List<HubAc> hubGroup) {
        List<HubGroup> hubAcs = new ArrayList<>();

        for(HubAc hubgrp : hubGroup) {
            HubGroup hubac = new HubGroup();
            hubac.setAcId(hubgrp.getAcId());
            hubac.setHubDirection(hubgrp.getHubDirection());
            hubAcs.add(hubac);
        }

        return hubAcs;
    }
}
