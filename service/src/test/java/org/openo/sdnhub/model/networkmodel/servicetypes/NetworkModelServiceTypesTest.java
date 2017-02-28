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

package org.openo.sdnhub.model.networkmodel.servicetypes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openo.sdnhub.model.networkmodel.servicetypes.Ac;
import org.openo.sdnhub.model.networkmodel.servicetypes.AcOperStatus;
import org.openo.sdnhub.model.networkmodel.servicetypes.AcProtectGroup;
import org.openo.sdnhub.model.networkmodel.servicetypes.AutoSelect;
import org.openo.sdnhub.model.networkmodel.servicetypes.BgpPeer;
import org.openo.sdnhub.model.networkmodel.servicetypes.BindingTunnel;
import org.openo.sdnhub.model.networkmodel.servicetypes.CarServiceClass;
import org.openo.sdnhub.model.networkmodel.servicetypes.Dot1q;
import org.openo.sdnhub.model.networkmodel.servicetypes.HubAc;
import org.openo.sdnhub.model.networkmodel.servicetypes.HubSpoke;
import org.openo.sdnhub.model.networkmodel.servicetypes.ISIS;
import org.openo.sdnhub.model.networkmodel.servicetypes.L2Access;
import org.openo.sdnhub.model.networkmodel.servicetypes.L3Access;
import org.openo.sdnhub.model.networkmodel.servicetypes.MplsTe;
import org.openo.sdnhub.model.networkmodel.servicetypes.Ne;
import org.openo.sdnhub.model.networkmodel.servicetypes.Ospf;
import org.openo.sdnhub.model.networkmodel.servicetypes.OspfArea;
import org.openo.sdnhub.model.networkmodel.servicetypes.OspfNetworkSbi;
import org.openo.sdnhub.model.networkmodel.servicetypes.ParticularConstraintStatement;
import org.openo.sdnhub.model.networkmodel.servicetypes.PathConstraint;
import org.openo.sdnhub.model.networkmodel.servicetypes.PathProtectPolicy;
import org.openo.sdnhub.model.networkmodel.servicetypes.Port;
import org.openo.sdnhub.model.networkmodel.servicetypes.Protocol;
import org.openo.sdnhub.model.networkmodel.servicetypes.Qos;
import org.openo.sdnhub.model.networkmodel.servicetypes.QosIfCar;
import org.openo.sdnhub.model.networkmodel.servicetypes.QosIfPhb;
import org.openo.sdnhub.model.networkmodel.servicetypes.QosIfQueueProfile;
import org.openo.sdnhub.model.networkmodel.servicetypes.QosIfTrafficPolicy;
import org.openo.sdnhub.model.networkmodel.servicetypes.SelectTunnel;
import org.openo.sdnhub.model.networkmodel.servicetypes.SpokeAc;
import org.openo.sdnhub.model.networkmodel.servicetypes.SpokeGroup;
import org.openo.sdnhub.model.networkmodel.servicetypes.StaticRoute;
import org.openo.sdnhub.model.networkmodel.servicetypes.TopoService;
import org.openo.sdnhub.model.networkmodel.servicetypes.TunnelService;
import org.openo.sdnhub.model.networkmodel.servicetypes.VpnOperStatus;
import org.openo.sdnhub.model.networkmodel.servicetypes.Vrrp;
import org.openo.sdnhub.model.networkmodel.servicetypes.Vxlan;
import org.openo.sdnhub.model.networkmodel.servicetypes.VxlanAccess;
import org.openo.sdno.model.servicemodel.tp.Tp;

import java.math.BigInteger;
import java.util.List;

public class NetworkModelServiceTypesTest {

    Vxlan vxlan = new Vxlan();

    VxlanAccess vxlanAccess = new VxlanAccess();

    @Test
    public void testAc() {
        Ac ac = new Ac();
        String id = "id";
        ac.setId(id);
        String neId = "neId";
        ac.setNeId(neId);
        String userLabel = "userLabel";
        ac.setUserLabel(userLabel);
        L2Access l2Access = null;
        ac.setL2Access(l2Access);
        L3Access l3Access = null;
        ac.setL3Access(l3Access);
        Qos qos = null;
        ac.setQos(qos);
        assertEquals(ac.getId(), id);
        assertEquals(ac.getNeId(), neId);
        assertEquals(ac.getUserLabel(), userLabel);
        assertEquals(ac.getL2Access(), l2Access);
        assertEquals(ac.getL3Access(), l3Access);
        assertEquals(ac.getQos(), qos);
        ac.inject(new Tp());
    }

    @Test
    public void testAcOperStatus() {
        AcOperStatus acOperStatus = new AcOperStatus();
        acOperStatus.setId("id");
        String name = "name";
        acOperStatus.setName(name);
        String operStatus = "operStatus";
        acOperStatus.setOperStatus(operStatus);
        acOperStatus.setIsactive(true);
        String id = "id";
        assertEquals(acOperStatus.getId(), id);
        assertEquals(acOperStatus.getName(), name);
        assertEquals(acOperStatus.getOperStatus(), operStatus);
        assertEquals(acOperStatus.getIsactive(), true);
    }

    @Test
    public void testAcProtectGroup() {
        AcProtectGroup obj = new AcProtectGroup();
        obj.getVrrp();
        Vrrp vrrp = null;
        obj.setVrrp(vrrp);
        String backAcId = "backAcId";
        obj.setBackAcId(backAcId);
        String userLable = "userLable";
        obj.setMasterAcId(userLable);
        assertEquals(obj.getVrrp(), vrrp);
        assertEquals(obj.getBackAcId(), backAcId);
        assertEquals(obj.getMasterAcId(), userLable);

    }

    @Test
    public void testAutoSelect() {
        AutoSelect obj = new AutoSelect();
        Integer loadBalanceNumber = 1;
        obj.setLoadBalanceNumber(loadBalanceNumber);
        List<SelectTunnel> seletTunnels = null;
        obj.setSelectTunnels(seletTunnels);
        assertEquals(obj.getLoadBalanceNumber(), loadBalanceNumber);
        assertEquals(obj.getSelectTunnels(), seletTunnels);
    }

    @Test
    public void testBgpPeer() {
        BgpPeer obj = new BgpPeer();
        String remote = "remote";
        obj.setRemoteAs(remote);
        obj.setPeerIp("neID");
        Integer keepAliveTime = 1;
        obj.setKeepAliveTime(keepAliveTime);
        Integer holdAliveTime = 1;
        obj.setHoldTime(holdAliveTime);
        String password = "pwd";
        obj.setPassword(password);
        obj.setAdvertiseCommunity(true);
        obj.setAdvertiseExtCommunity(true);
        assertEquals(obj.getRemoteAs(), remote);
        String peerId = "neID";
        assertEquals(obj.getPeerIp(), peerId);
        assertEquals(obj.getKeepAliveTime(), keepAliveTime);
        assertEquals(obj.getHoldTime(), holdAliveTime);
        assertEquals(obj.getPassword(), password);
        assertEquals(obj.getAdvertiseCommunity(), true);
        assertEquals(obj.getAdvertiseExtCommunity(), true);
    }

    @Test
    public void testBindingTunnel() {
        BindingTunnel obj = new BindingTunnel();
        String tunnelName = "tunnelName";
        obj.setTunnelName(tunnelName);
        assertEquals(obj.getTunnelName(), tunnelName);
    }

    @Test
    public void testCarServiceClass() {
        CarServiceClass obj = new CarServiceClass();
        String flowColor = "flowColor";
        obj.setFlowColor(flowColor);
        String passOrDiscard = "passOrDiscard";
        obj.setPassOrDiscard(passOrDiscard);
        String serviceClass = "serviceClass";
        obj.setServiceClass(serviceClass);
        String color = "color";
        obj.setColor(color);
        assertEquals(obj.getFlowColor(), flowColor);
        assertEquals(obj.getPassOrDiscard(), passOrDiscard);
        assertEquals(obj.getServiceClass(), serviceClass);
        assertEquals(obj.getColor(), color);
    }

    @Test
    public void testDot1q() {
        Dot1q obj = new Dot1q();
        Integer valnId = 123;
        obj.setVlanId(valnId);
        String ltpId = "ltpId";
        obj.setLtpId(ltpId);
        assertEquals(obj.getVlanId(), valnId);
        assertEquals(obj.getLtpId(), ltpId);
    }

    @Test
    public void testHubAc() {
        HubAc obj = new HubAc();
        String acId = "acId";
        obj.setAcId(acId);
        String hubDirection = "hubDirection";
        obj.setHubDirection(hubDirection);
        assertEquals(obj.getAcId(), acId);
        assertEquals(obj.getHubDirection(), hubDirection);
    }

    @Test
    public void testHubSpoke() {
        HubSpoke obj = new HubSpoke();
        List<HubAc> hubGroup = null;
        obj.setHubGroup(hubGroup);
        SpokeGroup spokeGroup = null;
        obj.setSpokeGroup(spokeGroup);
        assertEquals(obj.getHubGroup(), hubGroup);
        assertEquals(obj.getSpokeGroup(), spokeGroup);
    }

    @Test
    public void testIsis() {
        ISIS obj = new ISIS();
        obj.getNetworkEntity();
        String networkEntity = null;
        obj.setNetworkEntity(networkEntity);
        assertEquals(obj.getNetworkEntity(), networkEntity);
    }

    @Test
    public void testL2Access() {
        L2Access obj = new L2Access();
        String accessType = "accessType";
        obj.setAccessType(accessType);
        Port port = null;
        obj.setPort(port);
        Dot1q dot1q = null;
        obj.setDot1q(dot1q);
        assertEquals(obj.getAccessType(), accessType);
        assertEquals(obj.getPort(), port);
        assertEquals(obj.getDot1q(), dot1q);
    }

    @Test
    public void testL3Access() {
        L3Access obj = new L3Access();
        String address = "address";
        obj.setAddress(address);
        List<StaticRoute> staticRoute = null;
        obj.setStaticRoutes(staticRoute);
        List<Protocol> protocols = null;
        obj.setProtocols(protocols);
        assertEquals(obj.getAddress(), address);
        assertEquals(obj.getStaticRoutes(), staticRoute);
        assertEquals(obj.getProtocols(), protocols);
    }

    @Test
    public void testMplsTe() {
        MplsTe obj = new MplsTe();
        String signalType = "signalType";
        obj.setSignalType(signalType);
        String manageProtocol = "manageProtocol";
        obj.setManageProtocol(manageProtocol);
        Boolean sharing = true;
        obj.setSharing(sharing);
        PathConstraint pathConstraint = null;
        obj.setPathConstraint(pathConstraint);
        PathProtectPolicy pathProtectPolicy = null;
        obj.setPathProtectPolicy(pathProtectPolicy);
        Boolean bestEffort = true;
        obj.setBestEffort(bestEffort);
        Integer bandwidth = 123;
        obj.setBandwidth(bandwidth);
        Boolean coRoute = true;
        obj.setCoRoute(coRoute);
        Boolean bfdEnable = true;
        obj.setBfdEnable(bfdEnable);
        assertEquals(obj.getSignalType(), signalType);
        assertEquals(obj.getManageProtocol(), manageProtocol);
        assertEquals(obj.getSharing(), sharing);
        assertEquals(obj.getPathConstraint(), pathConstraint);
        assertEquals(obj.getPathProtectPolicy(), pathProtectPolicy);
        assertEquals(obj.getBestEffort(), bestEffort);
        assertEquals(obj.getBandwidth(), bandwidth);
        assertEquals(obj.getCoRoute(), coRoute);
        assertEquals(obj.getBfdEnable(), bfdEnable);
    }

    @Test
    public void testNe() {
        Ne obj = new Ne();
        String id = "id";
        obj.setId(id);
        assertEquals(obj.getId(), id);
        obj.inject(new Tp());
    }

    @Test
    public void testOspfArea() {
        OspfArea obj = new OspfArea();
        String id = "id";
        obj.setId(id);
        List<OspfNetworkSbi> network = null;
        obj.setNetworks(network);
        assertEquals(obj.getId(), id);
        assertEquals(obj.getNetworks(), network);
    }

    @Test
    public void testOspfNetworkSbi() {
        OspfNetworkSbi obj = new OspfNetworkSbi();
        String ipPrefix = "ipPrefix";
        obj.setIpPrefix(ipPrefix);
        assertEquals(obj.getIpPrefix(), ipPrefix);
    }

    @Test
    public void testParticularConstraintStatement() {
        ParticularConstraintStatement obj = new ParticularConstraintStatement();
        String ingressNeId = "ingressNeId";
        obj.setIngressNeId(ingressNeId);
        String egressNeId = "egressNeId";
        obj.setEgressNeId(egressNeId);
        String type = "type";
        obj.setType(type);
        List<BindingTunnel> bindingTunnels = null;
        obj.setBindingTunnels(bindingTunnels);
        MplsTe mplsTe = null;
        obj.setMplsTe(mplsTe);
        assertEquals(obj.getIngressNeId(), ingressNeId);
        assertEquals(obj.getEgressNeId(), egressNeId);
        assertEquals(obj.getType(), type);
        assertEquals(obj.getBindingTunnels(), bindingTunnels);
        assertEquals(obj.getMplsTe(), mplsTe);
    }

    @Test
    public void testPathConstraint() {
        PathConstraint obj = new PathConstraint();
        Integer setupPriority = 1;
        obj.setSetupPriority(setupPriority);
        Integer holdupPriority = 1;
        obj.setHoldupPriority(holdupPriority);
        Integer latency = 1;
        obj.setLatency(latency);
        assertEquals(obj.getSetupPriority(), setupPriority);
        assertEquals(obj.getHoldupPriority(), holdupPriority);
        assertEquals(obj.getLatency(), latency);
    }

    @Test
    public void testPathProtectPolicy() {
        PathProtectPolicy obj = new PathProtectPolicy();
        Boolean hostStandby = true;
        obj.setHotStandby(hostStandby);
        Integer wtr = 1;
        obj.setWtr(wtr);
        String bandwidthMode = "bandwidthMode";
        obj.setBandwidthMode(bandwidthMode);
        Boolean revertive = true;
        obj.setRevertive(revertive);
        assertEquals(obj.getHotStandby(), hostStandby);
        assertEquals(obj.getWtr(), wtr);
        assertEquals(obj.getBandwidthMode(), bandwidthMode);
        assertEquals(obj.getRevertive(), revertive);
    }

    @Test
    public void testPort() {
        Port obj = new Port();
        String ltpId = "ltpId";
        obj.setLtpId(ltpId);
        assertEquals(obj.getLtpId(), ltpId);
    }

    @Test
    public void testProtocol() {
        Protocol obj = new Protocol();
        String type = "type";
        obj.setType(type);
        List<BgpPeer> bgp = null;
        obj.setBgp(bgp);
        ISIS isis = null;
        obj.setIsis(isis);
        Ospf ospf = null;
        obj.setOspf(ospf);
        assertEquals(obj.getType(), type);
        assertEquals(obj.getBgp(), bgp);
        assertEquals(obj.getIsis(), isis);
        assertEquals(obj.getOspf(), ospf);
    }

    @Test
    public void testQosIfPhb() {
        QosIfPhb obj = new QosIfPhb();
        String direction = "direction";
        obj.setDirection(direction);
        Boolean trust8021p = true;
        obj.setTrust8021p(trust8021p);
        Boolean trustUpstream = true;
        obj.setTrustUpstream(trustUpstream);
        Boolean phbMap = null;
        obj.setPhbMap(phbMap);
        assertEquals(obj.getDirection(), direction);
        assertEquals(obj.getTrust8021p(), trust8021p);
        assertEquals(obj.getTrustUpstream(), trustUpstream);
        assertEquals(obj.getPhbMap(), phbMap);
    }

    @Test
    public void testQosIfQueueProfile() {
        QosIfQueueProfile obj = new QosIfQueueProfile();
        String direction = "direction";
        obj.setDirection(direction);
        String profileId = "profileId";
        obj.setQueueProfileId(profileId);
        assertEquals(obj.getDirection(), direction);
        assertEquals(obj.getQueueProfileId(), profileId);
    }

    @Test
    public void testQosIfTrafficPolicy() {
        QosIfTrafficPolicy obj = new QosIfTrafficPolicy();
        String direction = "direction";
        obj.setDirection(direction);
        String trafficPolicyId = "trafficPolicyId";
        obj.setTrafficPolicyId(trafficPolicyId);
        assertEquals(obj.getDirection(), direction);
        assertEquals(obj.getTrafficPolicyId(), trafficPolicyId);
    }

    @Test
    public void testQosIfCar() {
        QosIfCar obj = new QosIfCar();
        String direction = "direction";
        obj.setDirection(direction);
        BigInteger cir = null;
        obj.setCir(cir);
        BigInteger pir = null;
        obj.setPir(pir);
        BigInteger cbs = null;
        obj.setCbs(cbs);
        BigInteger pbs = null;
        obj.setPbs(pbs);
        Boolean enable = true;
        obj.setEnable(enable);
        CarServiceClass carserviceClass = null;
        obj.setCarServiceClass(carserviceClass);
        assertEquals(obj.getDirection(), direction);
        assertEquals(obj.getCir(), cir);
        assertEquals(obj.getPir(), pir);
        assertEquals(obj.getCbs(), cbs);
        assertEquals(obj.getPbs(), pbs);
        assertEquals(obj.getEnable(), enable);
        assertEquals(obj.getCarServiceClass(), carserviceClass);
    }

    @Test
    public void testQos() {
        Qos obj = new Qos();
        List<QosIfPhb> qosIfPhbs = null;
        obj.setQosIfPhbs(qosIfPhbs);
        List<QosIfTrafficPolicy> qosIfTrafficPolicys = null;
        obj.setQosIfTrafficPolicys(qosIfTrafficPolicys);
        List<QosIfQueueProfile> qosIfQueueProfiles = null;
        obj.setQosIfQueueProfiles(qosIfQueueProfiles);
        List<QosIfCar> qosIfCars = null;
        obj.setQosIfCars(qosIfCars);
        assertEquals(obj.getQosIfPhbs(), qosIfPhbs);
        assertEquals(obj.getQosIfTrafficPolicys(), qosIfTrafficPolicys);
        assertEquals(obj.getQosIfQueueProfiles(), qosIfQueueProfiles);
        assertEquals(obj.getQosIfCars(), qosIfCars);
    }

    @Test
    public void testSelectTunnel() {
        SelectTunnel obj = new SelectTunnel();
        String type = "type";
        obj.setType(type);
        Integer priority = 1;
        obj.setPriority(priority);
        assertEquals(obj.getType(), type);
        assertEquals(obj.getPriority(), priority);
    }

    @Test
    public void testSpokeAc() {
        SpokeAc obj = new SpokeAc();
        String acId = "acId";
        obj.setAcId(acId);
        assertEquals(obj.getAcId(), acId);
    }

    @Test
    public void testSpokeGroup() {
        SpokeGroup obj = new SpokeGroup();
        obj.getSpokeAcs();
        List<SpokeAc> spokeAcs = null;
        obj.setSpokeAcs(spokeAcs);
        Boolean localBridge = true;
        obj.setLocalBridge(localBridge);
        assertEquals(obj.getSpokeAcs(), spokeAcs);
        assertEquals(obj.getLocalBridge(), localBridge);
    }

    @Test
    public void testStaticRoute() {
        StaticRoute obj = new StaticRoute();
        String ipPrefix = "ipPrefix";
        obj.setIpPrefix(ipPrefix);
        String nextHop = "nextHop";
        obj.setNextHop(nextHop);
        Integer preference = 1;
        obj.setPreference(preference);
        assertEquals(obj.getIpPrefix(), ipPrefix);
        assertEquals(obj.getNextHop(), nextHop);
        assertEquals(obj.getPreference(), preference);
    }

    @Test
    public void testTopoService() {

        TopoService obj = new TopoService();
        HubSpoke hubSpoke = null;
        obj.setHubSpoke(hubSpoke);
        assertEquals(obj.getHubSpoke(), hubSpoke);
    }

    @Test
    public void testTunnelService() {
        TunnelService obj = new TunnelService();
        String type = "type";
        obj.setType(type);
        AutoSelect autoSelect = null;
        obj.setAutoSelect(autoSelect);
        obj.getMplsTe();
        MplsTe mplsTe = null;
        obj.setMplsTe(mplsTe);
        List<ParticularConstraintStatement> particularConstraints = null;
        obj.setParticularConstraints(particularConstraints);
        assertEquals(obj.getType(), type);
        assertEquals(obj.getAutoSelect(), autoSelect);
        assertEquals(obj.getParticularConstraints(), particularConstraints);
        assertEquals(obj.getMplsTe(), mplsTe);
    }

    @Test
    public void testVpnOperStatus() {
        VpnOperStatus obj = new VpnOperStatus();
        String id = "id";
        obj.setId(id);
        String operStatus = "operStatus";
        obj.setOperStatus(operStatus);
        List<AcOperStatus> acOperStatusList = null;
        obj.setAcOperStatusLst(acOperStatusList);
        assertEquals(obj.getId(), id);
        assertEquals(obj.getOperStatus(), operStatus);
        assertEquals(obj.getAcOperStatusLst(), acOperStatusList);
    }

    @Test
    public void testVrrp() {
        Vrrp obj = new Vrrp();
        String virtualIp = "virtualIp";
        obj.setVirtualIp(virtualIp);
        assertEquals(obj.getVirtualIp(), virtualIp);
    }
}
