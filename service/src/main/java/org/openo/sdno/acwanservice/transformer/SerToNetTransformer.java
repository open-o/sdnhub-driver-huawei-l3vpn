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

package org.openo.sdno.acwanservice.transformer;

import java.util.ArrayList;
import java.util.List;

import org.openo.sdno.acwanservice.config.ConfigKeyConst;
import org.openo.sdno.acwanservice.config.Configuration;
import org.openo.sdno.model.networkmodel.servicetypes.AcProtectGroup;
import org.openo.sdno.model.networkmodel.servicetypes.AcProtectGroups;
import org.openo.sdno.model.networkmodel.servicetypes.AutoSelect;
import org.openo.sdno.model.networkmodel.servicetypes.BgpPeer;
import org.openo.sdno.model.networkmodel.servicetypes.DiffServ;
import org.openo.sdno.model.networkmodel.servicetypes.Dot1q;
import org.openo.sdno.model.networkmodel.servicetypes.HubAc;
import org.openo.sdno.model.networkmodel.servicetypes.HubSpoke;
import org.openo.sdno.model.networkmodel.servicetypes.ISIS;
import org.openo.sdno.model.networkmodel.servicetypes.L2Access;
import org.openo.sdno.model.networkmodel.servicetypes.L3Ac;
import org.openo.sdno.model.networkmodel.servicetypes.L3Access;
import org.openo.sdno.model.networkmodel.servicetypes.L3Acs;
import org.openo.sdno.model.networkmodel.servicetypes.L3Vpn;
import org.openo.sdno.model.networkmodel.servicetypes.L3VpnConfig;
import org.openo.sdno.model.networkmodel.servicetypes.L3VpnInstances;
import org.openo.sdno.model.networkmodel.servicetypes.MplsTe;
import org.openo.sdno.model.networkmodel.servicetypes.Ne;
import org.openo.sdno.model.networkmodel.servicetypes.Nes;
import org.openo.sdno.model.networkmodel.servicetypes.PathConstraint;
import org.openo.sdno.model.networkmodel.servicetypes.PathProtectPolicy;
import org.openo.sdno.model.networkmodel.servicetypes.Port;
import org.openo.sdno.model.networkmodel.servicetypes.Protocol;
import org.openo.sdno.model.networkmodel.servicetypes.SelectTunnel;
import org.openo.sdno.model.networkmodel.servicetypes.SpokeAc;
import org.openo.sdno.model.networkmodel.servicetypes.SpokeGroup;
import org.openo.sdno.model.networkmodel.servicetypes.StaticRoute;
import org.openo.sdno.model.networkmodel.servicetypes.TopoService;
import org.openo.sdno.model.networkmodel.servicetypes.TunnelService;
import org.openo.sdno.model.networkmodel.servicetypes.Vrrp;
import org.openo.sdno.model.networkmodel.servicetypes.Vxlan;
import org.openo.sdno.model.networkmodel.servicetypes.enumeration.RouteType;
import org.openo.sdno.model.uniformsbi.base.AutoSelectTunnel;
import org.openo.sdno.model.uniformsbi.l3vpn.BgpRoute;
import org.openo.sdno.model.uniformsbi.l3vpn.BgpRoutes;
import org.openo.sdno.model.uniformsbi.l3vpn.HubGroup;
import org.openo.sdno.model.uniformsbi.l3vpn.IsisRoute;
import org.openo.sdno.model.uniformsbi.l3vpn.ProtectGroup;
import org.openo.sdno.model.uniformsbi.l3vpn.TopologyService;

/**
 * class for transforming service to network model<br>
 * 
 * @author
 * @version SDNO 0.5 Aug 22, 2016
 */
public class SerToNetTransformer {

    private SerToNetTransformer() {
    }

    /**
     * transform service to network
     * <br>
     * 
     * @param l3Vpn
     * @return network instance
     * @since SDNO 0.5
     */
    public static L3VpnConfig transformModel(org.openo.sdno.model.uniformsbi.l3vpn.L3Vpn l3Vpn) {
        L3VpnConfig l3VpnConfig = new L3VpnConfig();
        L3VpnInstances l3VpnInstances = new L3VpnInstances();
        List<L3Vpn> instances = new ArrayList<L3Vpn>();

        L3Vpn ctrlrl3vpn = new L3Vpn();

        ctrlrl3vpn.setId(l3Vpn.getUuid());
        ctrlrl3vpn.setName(l3Vpn.getName());
        ctrlrl3vpn.setUserLabel(Configuration.getValues(ConfigKeyConst.USER_LABEL));
        if(l3Vpn.getAdminStatus() != null) {
            ctrlrl3vpn.setAdminStatus(l3Vpn.getAdminStatus().getName());
        }
        ctrlrl3vpn.setIpMtu(l3Vpn.getIpMtu());
        ctrlrl3vpn.setLabelApplyMode(Configuration.getValues(ConfigKeyConst.LABEL_APPLY_MODE));
        if(l3Vpn.getFrr() != null) {
            ctrlrl3vpn.setFrr(l3Vpn.getFrr().toString());
        }
        
        //
        List<Ne> nesList = new ArrayList<Ne>();
        Ne ne = new Ne();
        ne.setId(Configuration.getValues(ConfigKeyConst.NE_ID_1));
        nesList.add(ne);
        
        ne = new Ne();
        ne.setId(Configuration.getValues(ConfigKeyConst.NE_ID_2));
        nesList.add(ne);
        
        Nes nes = new Nes();
        nes.setNes(nesList);
        ctrlrl3vpn.setNes(nes);

        if(l3Vpn.getAcs() != null) {
            ctrlrl3vpn.setAcs(transformAcs(l3Vpn.getAcs()));
        }
        if(l3Vpn.getTopologyService() != null) {
            ctrlrl3vpn.setTopoService(transformTopoService(l3Vpn.getTopologyService()));
        }
        if(l3Vpn.getProtectGroup() != null) {
            ctrlrl3vpn.setAcProtectGroups(transformProtectGroup(l3Vpn.getProtectGroup()));
        }
        if(l3Vpn.getDiffServ() != null) {
            ctrlrl3vpn.setDiffServ(transformDiffServ(l3Vpn.getDiffServ()));
        }
        if(l3Vpn.getTunnelService() != null) {
            ctrlrl3vpn.setTunnelService(transformTunnelService(l3Vpn.getTunnelService()));
        }

        // TODO: set mode From Config file

        instances.add(ctrlrl3vpn);
        l3VpnInstances.setInstances(instances);
        l3VpnConfig.setInstances(l3VpnInstances);

        return l3VpnConfig;
    }

    private static L3Acs transformAcs(org.openo.sdno.model.uniformsbi.l3vpn.L3Acs acs) {

        L3Acs l3Acs = new L3Acs();
        List<L3Ac> l3AcList = new ArrayList<>();

        for(org.openo.sdno.model.uniformsbi.l3vpn.L3Ac l3ac : acs.getL3Ac()) {
            L3Ac ctrlrl3ac = new L3Ac();

            ctrlrl3ac.setId(l3ac.getUuid());
            ctrlrl3ac.setNeId(l3ac.getNeId());
            ctrlrl3ac.setUserLabel(Configuration.getValues(ConfigKeyConst.USER_LABEL));

            if(l3ac.getL2Access() != null) {
                L2Access l2access = new L2Access();
                l2access.setAccessType(l3ac.getL2Access().getL2AccessType());
                Dot1q dot1q = new Dot1q();
                dot1q.setVlanId(l3ac.getL2Access().getDot1qVlanBitmap());
                dot1q.setLtpId(l3ac.getLtpId());
                l2access.setDot1q(dot1q);

                Port port = new Port();
                port.setLtpId(l3ac.getLtpId());
                l2access.setPort(port);

                Vxlan vxlan = new Vxlan();
                if(l3ac.getL2Access().getPushVlanId() != null) {
                    vxlan.setVniId(Integer.valueOf(l3ac.getL2Access().getPushVlanId()));
                    l2access.setVxlan(vxlan);
                }
                ctrlrl3ac.setL2Access(l2access);
            }

            // TODO: set Qos From Config file

            if(l3ac.getL3Access() != null) {

                L3Access l3Access = new L3Access();
                l3Access.setAddress(l3ac.getL3Access().getIpv4Address());

                if(l3ac.getL3Access().getRoutes() != null) {
                    List<Protocol> protocols = new ArrayList<Protocol>();

                    List<StaticRoute> staticRoutes = new ArrayList<>();
                    for(org.openo.sdno.model.uniformsbi.l3vpn.Route route : l3ac.getL3Access().getRoutes().getRoute()) {
                        if(route.getStaticRoutes() != null) {
                            for(org.openo.sdno.model.uniformsbi.l3vpn.StaticRoute sr : route.getStaticRoutes()
                                    .getStaticRoute()) {
                                StaticRoute staticRoute = new StaticRoute();
                                staticRoute.setIpPrefix(sr.getIpPrefix());
                                staticRoute.setNextHop(sr.getNextHop());
                                staticRoute.setPreference(
                                        Integer.valueOf(Configuration.getValues(ConfigKeyConst.SR_PREFERENCE)));
                                staticRoute.setDescription(Configuration.getValues(ConfigKeyConst.SR_DESCRIPTION));
                                staticRoute
                                        .setTrackBfdEnable(Configuration.getValues(ConfigKeyConst.SR_TRACK_BFD_ENABLE));
                                staticRoutes.add(staticRoute);
                                Protocol protocol = new Protocol();
                                protocol.setType(RouteType.STATIC.getName());

                            }
                        }
                        l3Access.setStaticRoutes(staticRoutes);

                        // Populate Network model protocols of type BGP AND ISIS to the Network
                        // model protocol list
                        if(route.getBgpRoutes() != null) {
                            BgpRoutes bgpRoutes = route.getBgpRoutes();
                            Protocol protocol = new Protocol();
                            List<BgpPeer> bgpPeers = new ArrayList<BgpPeer>();
                            for(BgpRoute bgpRoute : bgpRoutes.getBgpRoute()) {
                                BgpPeer bgpPeer = new BgpPeer();
                                bgpPeer.setAdvertiseCommunity(bgpRoute.isAdvertiseCommunity());
                                bgpPeer.setAdvertiseExtCommunity(bgpRoute.isAdvertiseExtCommunity());
                                bgpPeer.setHoldTime(bgpRoute.getHoldTime());
                                bgpPeer.setKeepAliveTime(bgpRoute.getKeepaliveTime());
                                bgpPeer.setPassword(bgpRoute.getPassword());
                                bgpPeer.setPeerIp(bgpRoute.getPeerIp());
                                bgpPeer.setRemoteAs(bgpRoute.getRemoteAs());
                                bgpPeers.add(bgpPeer);
                            }
                            protocol.setBgp(bgpPeers);
                            protocol.setType(RouteType.BGP.getName());
                            protocols.add(protocol);

                        }

                        if(route.getIsisRoute() != null) {
                            IsisRoute isis = route.getIsisRoute();
                            Protocol protocol = new Protocol();
                            ISIS ctlIsis = new ISIS();
                            ctlIsis.setNetworkEntity(isis.getNetworkEntity());
                            protocol.setIsis(ctlIsis);
                            protocol.setType(RouteType.BGP.getName());
                            protocols.add(protocol);
                        }
                        l3Access.setProtocols(protocols);
                    }

                }

                // TODO: SBI does not consists OSPF today.

                ctrlrl3ac.setL3Access(l3Access);
            }

            // vxlan-access is not present in the JSON input. Needs to be taken from the Conf files.
            // TODO: set vxlanAccess from config file

            l3AcList.add(ctrlrl3ac);
        }
        // TODO : Add from Conf file Particular-constraint.
        l3Acs.setL3Ac(l3AcList);
        return l3Acs;
    }

    private static AcProtectGroups transformProtectGroup(ProtectGroup protectGroup) {
        AcProtectGroups apgs = new AcProtectGroups();

        AcProtectGroup apg = new AcProtectGroup();
        apg.setBackAcId(protectGroup.getBackupAc());
        apg.setMasterAcId(protectGroup.getMasterAc());

        if(protectGroup.getVrrp() != null) {
            Vrrp vrrp = new Vrrp();
            vrrp.setVirtualIp(protectGroup.getVrrp().getVirtualIp());
            apg.setVrrp(vrrp);
        }
        apgs.setAcprotectGroup(apg);
        return apgs;
    }

    private static TunnelService
            transformTunnelService(org.openo.sdno.model.uniformsbi.base.TunnelService tunnelService) {
        TunnelService ts = new TunnelService();

        ts.setType(tunnelService.getType());

        if(tunnelService.getAutoSelect() != null) {
            AutoSelect autoSelect = new AutoSelect();

            autoSelect.setLoadBalanceNumber(tunnelService.getAutoSelect().getLoadBalanceNumber());

            List<SelectTunnel> ctrlrselectTunnels = new ArrayList<SelectTunnel>();

            if(tunnelService.getAutoSelect().getAutoSelectTunnels() != null) {
                List<AutoSelectTunnel> selectTunnels =
                        tunnelService.getAutoSelect().getAutoSelectTunnels().getAutoSelectTunnel();

                for(AutoSelectTunnel autoSelectTunnel : selectTunnels) {
                    SelectTunnel st = new SelectTunnel();
                    st.setPriority(autoSelectTunnel.getPriority());
                    st.setType(autoSelectTunnel.getType());
                    ctrlrselectTunnels.add(st);
                }
                autoSelect.setSelectTunnels(ctrlrselectTunnels);
                ts.setAutoSelect(autoSelect);
            }
        }

        if(tunnelService.getMplsTe() != null) {
            ts.setMplsTe(transformMplsTe(tunnelService.getMplsTe()));
        }

        return ts;
    }

    private static MplsTe transformMplsTe(org.openo.sdno.model.uniformsbi.base.MplsTePolicy mplsTe) {

        MplsTe mplsObj = new MplsTe();
        mplsObj.setBandwidth(mplsTe.getBandwidth());
        mplsObj.setManageProtocol(mplsTe.getManageProtocol());
        mplsObj.setSignalType(mplsTe.getSignalType());
        mplsObj.setSharing(mplsTe.isSharing());
        mplsObj.setBestEffort(mplsTe.isBesteffort());
        mplsObj.setBfdEnable(mplsTe.isBfdEnable());
        mplsObj.setCoRoute(mplsTe.isCoRoute());

        if(mplsTe.getPathProtectPolicy() != null) {
            PathProtectPolicy ppp = new PathProtectPolicy();
            ppp.setBandwidthMode(mplsTe.getPathProtectPolicy().getBandwidthMode());
            ppp.setHotStandby(mplsTe.getPathProtectPolicy().getHotStandbyEnable());
            ppp.setRevertive(mplsTe.getPathProtectPolicy().getRevertive());
            ppp.setWtr(mplsTe.getPathProtectPolicy().getWtr());
            mplsObj.setPathProtectPolicy(ppp);
        }
        if(mplsTe.getPathConstraint() != null) {
            PathConstraint pathConstraint = new PathConstraint();
            pathConstraint.setHoldupPriority(mplsTe.getPathConstraint().getHoldupPriority());
            pathConstraint.setLatency(mplsTe.getPathConstraint().getLatency());
            pathConstraint.setSetupPriority(mplsTe.getPathConstraint().getSetupPriority());
            mplsObj.setPathConstraint(pathConstraint);
        }

        return mplsObj;
    }

    private static DiffServ transformDiffServ(org.openo.sdno.model.uniformsbi.l3vpn.DiffServ diffServ) {
        DiffServ diffServValue = new DiffServ();
        if(diffServ.getServiceClass() != null) {
            diffServValue.setServiceClass(diffServ.getServiceClass());
        }
        if(diffServ.getColor() != null) {
            diffServValue.setColor(diffServ.getColor());
        }
        if(diffServ.getMode() != null) {
            diffServValue.setMode(diffServ.getMode());
        }

        return diffServValue;
    }

    private static TopoService transformTopoService(TopologyService topologyService) {
        TopoService topoService = new TopoService();
        HubSpoke hubSpoke = new HubSpoke();

        if(topologyService.getHubGroups() != null) {
            hubSpoke.setHubGroup(transformHubGroup(topologyService.getHubGroups().getHubGroup()));
        }

        if(topologyService.getSpokeGroup() != null) {
            hubSpoke.setSpokeGroup(transformSpokeGroup(topologyService.getSpokeGroup()));
        }

        topoService.setHubSpoke(hubSpoke);

        return topoService;
    }

    private static SpokeGroup transformSpokeGroup(org.openo.sdno.model.uniformsbi.l3vpn.SpokeGroup spokeGroup) {
        SpokeGroup sg = new SpokeGroup();
        sg.setLocalBridge(spokeGroup.isLocalBridge());
        List<SpokeAc> ctrlSpokeAcs = new ArrayList<>();

        List<org.openo.sdno.model.uniformsbi.l3vpn.SpokeAcs> spokeAcs = spokeGroup.getSpokeAcs();
        if(spokeAcs != null) {
            for(org.openo.sdno.model.uniformsbi.l3vpn.SpokeAcs spokeAc : spokeAcs) {
                SpokeAc sa = new SpokeAc();
                sa.setAcId(spokeAc.getAcId());
                ctrlSpokeAcs.add(sa);
            }

            sg.setSpokeAcs(ctrlSpokeAcs);
        }

        return sg;
    }

    private static List<HubAc> transformHubGroup(List<HubGroup> hubGroup) {
        List<HubAc> hubAcs = new ArrayList<HubAc>();

        for(HubGroup hubgrp : hubGroup) {
            HubAc hubac = new HubAc();
            hubac.setAcId(hubgrp.getAcId());
            hubac.setHubDirection(hubgrp.getHubDirection());
            hubAcs.add(hubac);
        }

        return hubAcs;
    }
}
