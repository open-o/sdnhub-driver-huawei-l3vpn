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

package org.openo.sdno.model.networkmodel.servicetypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.openo.sdno.model.networkmodel.NetModel;

/**
 * L3vpn class.<br>
 * 
 * @author
 * @version SDNO 0.5 Aug 22, 2016
 */
@XmlRootElement(name = "instance")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "name", "userLabel", "adminStatus", "ipMtu", "labelApplyMode", "mode", "frr", "nes", "acs",
                "topoService", "acProtectGroups", "diffServ", "tunnelService"})
@JsonRootName(value = "instance")
@JsonSerialize(include = Inclusion.NON_NULL)
@JsonPropertyOrder(value = {"id", "name", "userLabel", "adminStatus", "ipMtu", "labelApplyMode", "mode", "frr", "nes",
                "acs", "topoService", "acProtectGroups", "diffServ", "tunnelService"})
public class L3Vpn implements NetModel {

    @XmlElement(name = "id")
    @JsonProperty("id")
    private String id;

    @XmlElement(name = "name")
    @JsonProperty("name")
    private String name;

    @XmlElement(name = "user-label")
    @JsonProperty("user-label")
    private String userLabel;

    @XmlElement(name = "admin-status")
    @JsonProperty("admin-status")
    private String adminStatus;

    @XmlElement(name = "mtu")
    @JsonProperty("mtu")
    private Integer ipMtu;

    @XmlElement(name = "label-apply-mode")
    @JsonProperty("label-apply-mode")
    private String labelApplyMode;

 // hub-spoke full-mesh
    @XmlElement(name = "mode")
    @JsonProperty("mode")
    private String mode; 

    @XmlElement(name = "frr")
    @JsonProperty("frr")
    private String frr;

    @XmlElement(name = "nes")
    @JsonProperty("nes")
    private Nes nes;

    @XmlElement(name = "acs")
    @JsonProperty("acs")
    private L3Acs acs;

    @XmlElement(name = "topo-service")
    @JsonProperty("topo-service")
    private TopoService topoService;

    @XmlElement(name = "ac-protect-groups")
    @JsonProperty("ac-protect-groups")
    private AcProtectGroups acProtectGroups;

    @XmlElement(name = "diff-serv")
    @JsonProperty("diff-serv")
    private DiffServ diffServ;

    @XmlElement(name = "tunnel-service")
    @JsonProperty("tunnel-service")
    private TunnelService tunnelService;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserLabel() {
        return userLabel;
    }

    public void setUserLabel(String userLabel) {
        this.userLabel = userLabel;
    }

    public String getAdminStatus() {
        return adminStatus;
    }

    public void setAdminStatus(String adminStatus) {
        this.adminStatus = adminStatus;
    }

    public Integer getIpMtu() {
        return ipMtu;
    }

    public void setIpMtu(Integer ipMtu) {
        this.ipMtu = ipMtu;
    }

    public String getLabelApplyMode() {
        return labelApplyMode;
    }

    public void setLabelApplyMode(String labelApplyMode) {
        this.labelApplyMode = labelApplyMode;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getFrr() {
        return frr;
    }

    public void setFrr(String frr) {
        this.frr = frr;
    }

    public Nes getNes() {
        return nes;
    }

    public void setNes(Nes nes) {
        this.nes = nes;
    }

    public L3Acs getAcs() {
        return acs;
    }

    public void setAcs(L3Acs acs) {
        this.acs = acs;
    }

    public TopoService getTopoService() {
        return topoService;
    }

    public void setTopoService(TopoService topoService) {
        this.topoService = topoService;
    }

    public AcProtectGroups getAcProtectGroups() {
        return acProtectGroups;
    }

    public void setAcProtectGroups(AcProtectGroups acProtectGroups) {
        this.acProtectGroups = acProtectGroups;
    }

    public DiffServ getDiffServ() {
        return diffServ;
    }

    public void setDiffServ(DiffServ diffServ) {
        this.diffServ = diffServ;
    }

    public TunnelService getTunnelService() {
        return tunnelService;
    }

    public void setTunnelService(TunnelService tunnelService) {
        this.tunnelService = tunnelService;
    }

}
