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

package org.openo.sdno.model.networkmodel.servicetypes;

import org.junit.Test;
import org.openo.sdno.model.networkmodel.NetModel;
import org.openo.sdno.model.servicemodel.tp.Tp;

public class NetworkModelServiceTypesTest {

    Vxlan vxlan = new Vxlan();

    VxlanAccess vxlanAccess = new VxlanAccess();

    @Test
    public void testAc() {

        Ac ac = new Ac();
        ac.getId();
        ac.setId("id");
        ac.getNeId();
        ac.setNeId("neID");
        ac.getUserLabel();
        ac.setUserLabel("userLable");
        ac.getL2Access();
        ac.setL2Access(null);
        ac.getL3Access();
        ac.setL3Access(null);
        ac.getQos();
        ac.setQos(null);
        ac.inject(new Tp());
    }

    @Test
    public void testAcOperStatus() {

        AcOperStatus acOperStatus = new AcOperStatus();
        acOperStatus.getId();
        acOperStatus.setId("id");
        acOperStatus.getName();
        acOperStatus.setName("neID");
        acOperStatus.getOperStatus();
        acOperStatus.setOperStatus("userLable");
        acOperStatus.getIsactive();
        acOperStatus.setIsactive(true);
    }

    @Test
    public void testAcProtectGroup() {

        AcProtectGroup obj = new AcProtectGroup();
        obj.getVrrp();
        obj.setVrrp(null);
        obj.getBackAcId();
        obj.setBackAcId("neID");
        obj.getMasterAcId();
        obj.setMasterAcId("userLable");
    }

    @Test
    public void testAutoSelect() {

        AutoSelect obj = new AutoSelect();
        obj.getLoadBalanceNumber();
        obj.setLoadBalanceNumber(null);
        obj.getSelectTunnels();
        obj.setSelectTunnels(null);
    }

    @Test
    public void testBgpPeer() {

        BgpPeer obj = new BgpPeer();
        obj.getRemoteAs();
        obj.setRemoteAs(null);
        obj.getPeerIp();
        obj.setPeerIp("neID");
        obj.getKeepAliveTime();
        obj.setKeepAliveTime(null);
        obj.getHoldTime();
        obj.setHoldTime(null);
        obj.getPassword();
        obj.setPassword("neID");
        obj.getAdvertiseCommunity();
        obj.setAdvertiseCommunity(true);
        obj.getAdvertiseExtCommunity();
        obj.setAdvertiseExtCommunity(true);
    }

    @Test
    public void testBindingTunnel() {

        BindingTunnel obj = new BindingTunnel();
        obj.getTunnelName();
        obj.setTunnelName(null);
    }

    @Test
    public void testCarServiceClass() {

        CarServiceClass obj = new CarServiceClass();
        obj.getFlowColor();
        obj.setFlowColor(null);
        obj.getPassOrDiscard();
        obj.setPassOrDiscard("neID");
        obj.getServiceClass();
        obj.setServiceClass(null);
        obj.getColor();
        obj.setColor(null);

    }

    @Test
    public void testDot1q() {

        Dot1q obj = new Dot1q();
        obj.getVlanId();
        obj.setVlanId(null);
        obj.getLtpId();
        obj.setLtpId("neID");
    }

    @Test
    public void testHubAc() {

        HubAc obj = new HubAc();
        obj.getAcId();
        obj.setAcId(null);
        obj.getHubDirection();
        obj.setHubDirection("neID");
    }

    @Test
    public void testHubSpoke() {

        HubSpoke obj = new HubSpoke();
        obj.getHubGroup();
        obj.setHubGroup(null);
        obj.getSpokeGroup();
        obj.setSpokeGroup(null);
    }

    @Test
    public void testISIS() {

        ISIS obj = new ISIS();
        obj.getNetworkEntity();
        obj.setNetworkEntity(null);
    }

    @Test
    public void testL2Access() {

        L2Access obj = new L2Access();
        obj.getAccessType();
        obj.setAccessType(null);
        obj.getPort();
        obj.setPort(null);
        obj.getDot1q();
        obj.setDot1q(null);
    }

    /*
     * @Test
     * public void testL3Ac() {
     * L3Ac obj = new L3Ac();
     * obj.getConnection();
     * obj.setConnection(null);
     * obj.inject(new Tp());
     * }
     */

    @Test
    public void testL3Access() {

        L3Access obj = new L3Access();
        obj.getAddress();
        obj.setAddress(null);
        obj.getStaticRoutes();
        obj.setStaticRoutes(null);
        obj.getProtocols();
        obj.setProtocols(null);
    }

    @Test
    public void testMplsTe() {

        MplsTe obj = new MplsTe();
        obj.getSignalType();
        obj.setSignalType(null);
        obj.getManageProtocol();
        obj.setManageProtocol(null);
        obj.getSharing();
        obj.setSharing(null);
        obj.getPathConstraint();
        obj.setPathConstraint(null);
        obj.getPathProtectPolicy();
        obj.setPathProtectPolicy(null);
        obj.getBestEffort();
        obj.setBestEffort(null);
        obj.getBandwidth();
        obj.setBandwidth(null);
        obj.getCoRoute();
        obj.setCoRoute(null);
        obj.getBfdEnable();
        obj.setBfdEnable(null);

    }

    @Test
    public void testNe() {

        Ne obj = new Ne();
        obj.getId();
        obj.setId(null);
        obj.inject(new Tp());
    }

    @Test
    public void testOspfArea() {

        OspfArea obj = new OspfArea();
        obj.getId();
        obj.setId(null);
        obj.getNetworks();
        obj.setNetworks(null);
    }

    @Test
    public void testOspfNetworkSbi() {

        OspfNetworkSbi obj = new OspfNetworkSbi();
        obj.getIpPrefix();
        obj.setIpPrefix(null);
    }

    @Test
    public void testParticularConstraintStatement() {

        ParticularConstraintStatement obj = new ParticularConstraintStatement();
        obj.getIngressNeId();
        obj.setIngressNeId(null);
        obj.getEgressNeId();
        obj.setEgressNeId(null);
        obj.getType();
        obj.setType(null);
        obj.getBindingTunnels();
        obj.setBindingTunnels(null);
        obj.getMplsTe();
        obj.setMplsTe(null);
    }

    @Test
    public void testPathConstraint() {

        PathConstraint obj = new PathConstraint();
        obj.getSetupPriority();
        obj.setSetupPriority(null);
        obj.getHoldupPriority();
        obj.setHoldupPriority(null);
        obj.getLatency();
        obj.setLatency(null);
    }

    @Test
    public void testPathProtectPolicy() {

        PathProtectPolicy obj = new PathProtectPolicy();
        // obj.getType();
        // obj.setType(null);
        obj.getHotStandby();
        obj.setHotStandby(null);
        obj.getWtr();
        obj.setWtr(null);
        obj.getBandwidthMode();
        obj.setBandwidthMode(null);
        // obj.getOverLap();
        // obj.setOverLap(null);
        // obj.getPathDisjointCalc();
        // obj.setPathDisjointCalc(null);
        obj.getRevertive();
        obj.setRevertive(true);
    }

    @Test
    public void testPort() {

        Port obj = new Port();
        obj.getLtpId();
        obj.setLtpId("test");
    }

    @Test
    public void testProtocol() {

        Protocol obj = new Protocol();
        obj.getType();
        obj.setType(null);
        obj.getBgp();
        obj.setBgp(null);
        obj.getIsis();
        obj.setIsis(null);
        obj.getOspf();
        obj.setOspf(null);
    }

    @Test
    public void testQosIfPhb() {

        QosIfPhb obj = new QosIfPhb();
        obj.getDirection();
        obj.setDirection(null);
        obj.getTrust8021p();
        obj.setTrust8021p(null);
        obj.getTrustUpstream();
        obj.setTrustUpstream(null);
        obj.getPhbMap();
        obj.setPhbMap(null);
    }

    @Test
    public void testQosIfQueueProfile() {

        QosIfQueueProfile obj = new QosIfQueueProfile();
        QosIfQueueProfile obj1 = new QosIfQueueProfile("test", "test");
        obj.getDirection();
        obj.setDirection(null);
        obj.getQueueProfileId();
        obj.setQueueProfileId(null);
    }

    @Test
    public void testQosIfTrafficPolicy() {

        QosIfTrafficPolicy obj = new QosIfTrafficPolicy();
        QosIfTrafficPolicy obj1 = new QosIfTrafficPolicy("test", "test");
        obj.getDirection();
        obj.setDirection(null);
        obj.getTrafficPolicyId();
        obj.setTrafficPolicyId(null);
    }

    @Test
    public void testQosIfCar() {

        QosIfCar obj = new QosIfCar();
        obj.getDirection();
        obj.setDirection(null);
        obj.getCir();
        obj.setCir(null);
        obj.getPir();
        obj.setPir(null);
        obj.getCbs();
        obj.setCbs(null);

        obj.getPbs();
        obj.setPbs(null);
        obj.getEnable();
        obj.setEnable(null);
        obj.getCarServiceClass();
        obj.setCarServiceClass(null);
    }

    @Test
    public void testQos() {

        Qos obj = new Qos();
        obj.getQosIfPhbs();
        obj.setQosIfPhbs(null);
        obj.getQosIfTrafficPolicys();
        obj.setQosIfTrafficPolicys(null);
        obj.getQosIfQueueProfiles();
        obj.setQosIfQueueProfiles(null);
        obj.getQosIfCars();
        obj.setQosIfCars(null);
    }

    @Test
    public void testSelectTunnel() {

        SelectTunnel obj = new SelectTunnel();
        obj.getType();
        obj.setType(null);
        obj.getPriority();
        obj.setPriority(null);
    }

    @Test
    public void testSpokeAc() {

        SpokeAc obj = new SpokeAc();
        obj.getAcId();
        obj.setAcId(null);
    }

    @Test
    public void testSpokeGroup() {

        SpokeGroup obj = new SpokeGroup();
        obj.getSpokeAcs();
        obj.setSpokeAcs(null);
        obj.getLocalBridge();
        obj.setLocalBridge(null);
    }

    @Test
    public void testStaticRoute() {

        StaticRoute obj = new StaticRoute();
        obj.getIpPrefix();
        obj.setIpPrefix(null);
        obj.getNextHop();
        obj.setNextHop(null);
        obj.getPreference();
        obj.setPreference(null);
    }

    @Test
    public void testTopoService() {

        TopoService obj = new TopoService();
        obj.getHubSpoke();
        obj.setHubSpoke(null);
    }

    @Test
    public void testTunnelService() {

        TunnelService obj = new TunnelService();
        obj.getType();
        obj.setType(null);
        obj.getAutoSelect();
        obj.setAutoSelect(null);
        obj.getMplsTe();
        obj.setMplsTe(null);
        // obj.getVxlan();
        // obj.setVxlan(null);
        obj.getParticularConstraints();
        obj.setParticularConstraints(null);
    }

    @Test
    public void testVpnOperStatus() {

        VpnOperStatus obj = new VpnOperStatus();
        obj.getId();
        obj.setId(null);
        obj.getOperStatus();
        obj.setOperStatus(null);
        obj.getAcOperStatusLst();
        obj.setAcOperStatusLst(null);
    }

    @Test
    public void testVrrp() {

        Vrrp obj = new Vrrp();
        obj.getVirtualIp();
        obj.setVirtualIp(null);
    }
}
