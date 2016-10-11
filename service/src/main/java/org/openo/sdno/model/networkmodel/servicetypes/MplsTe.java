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
 * MplsTe class.<br>
 * 
 * @author
 * @version SDNO 0.5 August 22, 2016
 */
@XmlRootElement(name = "mpls-te")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"signalType", "manageProtocol", "sharing", "bestEffort", "bandwidth", "coRoute", "bfdEnable",
                "pathConstraint", "pathProtectPolicy"})
@JsonRootName(value = "mpls-te")
@JsonSerialize(include = Inclusion.NON_NULL)
@JsonPropertyOrder(value = {"signalType", "manageProtocol", "sharing", "bestEffort", "bandwidth", "coRoute",
                "bfdEnable", "pathConstraint", "pathProtectPolicy"})
public class MplsTe implements NetModel {

    @XmlElement(name = "signal-type")
    @JsonProperty("signal-type")
    private String signalType;

    @XmlElement(name = "manage-protocol")
    @JsonProperty("manage-protocol")
    private String manageProtocol;

    @XmlElement(name = "sharing")
    @JsonProperty("sharing")
    private Boolean sharing;

    @XmlElement(name = "besteffort")
    @JsonProperty("besteffort")
    private Boolean bestEffort;

    @XmlElement(name = "bandwidth")
    @JsonProperty("bandwidth")
    private Integer bandwidth;

    @XmlElement(name = "co-route")
    @JsonProperty("co-route")
    private Boolean coRoute;

    @XmlElement(name = "bfd-enable")
    @JsonProperty("bfd-enable")
    private Boolean bfdEnable;

    @XmlElement(name = "path-constraint")
    @JsonProperty("path-constraint")
    private PathConstraint pathConstraint;

    @XmlElement(name = "path-protect-policy")
    @JsonProperty("path-protect-policy")
    private PathProtectPolicy pathProtectPolicy;

    public String getSignalType() {
        return signalType;
    }

    public void setSignalType(String signalType) {
        this.signalType = signalType;
    }

    public String getManageProtocol() {
        return manageProtocol;
    }

    public void setManageProtocol(String manageProtocol) {
        this.manageProtocol = manageProtocol;
    }

    public Boolean getSharing() {
        return sharing;
    }

    public void setSharing(Boolean sharing) {
        this.sharing = sharing;
    }

    public PathConstraint getPathConstraint() {
        return pathConstraint;
    }

    public void setPathConstraint(PathConstraint pathConstraint) {
        this.pathConstraint = pathConstraint;
    }

    public PathProtectPolicy getPathProtectPolicy() {
        return pathProtectPolicy;
    }

    public void setPathProtectPolicy(PathProtectPolicy pathProtectPolicy) {
        this.pathProtectPolicy = pathProtectPolicy;
    }

    public Boolean getBestEffort() {
        return bestEffort;
    }

    public void setBestEffort(Boolean bestEffort) {
        this.bestEffort = bestEffort;
    }

    public Integer getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(Integer bandwidth) {
        this.bandwidth = bandwidth;
    }

    public Boolean getCoRoute() {
        return coRoute;
    }

    public void setCoRoute(Boolean coRoute) {
        this.coRoute = coRoute;
    }

    public Boolean getBfdEnable() {
        return bfdEnable;
    }

    public void setBfdEnable(Boolean bfdEnable) {
        this.bfdEnable = bfdEnable;
    }
}
