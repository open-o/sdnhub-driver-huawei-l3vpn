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
import org.openo.sdno.model.paradesc.NoPrintField;

/**
 * <br>
 * <p>
 * </p>
 * 
 * @author
 * @version SDNO 0.5 August 17, 2016
 */
@XmlRootElement(name = "bgp-peer")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"remoteAs", "peerIp", "keepAliveTime", "holdTime", "password", "advertiseCommunity",
                "advertiseExtCommunity"})
@JsonRootName(value = "bgp-peer")
@JsonSerialize(include = Inclusion.NON_NULL)
@JsonPropertyOrder(value = {"remoteAs", "peerIp", "keepAliveTime", "holdTime", "password", "advertiseCommunity",
                "advertiseExtCommunity"})
public class BgpPeer {

    @XmlElement(name = "remote-as")
    @JsonProperty("remote-as")
    private String remoteAs;

    @XmlElement(name = "peer-ip")
    @JsonProperty("peer-ip")
    private String peerIp;

    @XmlElement(name = "keepalive-time")
    @JsonProperty("keepalive-time")
    private Integer keepAliveTime;

    @XmlElement(name = "hold-time")
    @JsonProperty("hold-time")
    private Integer holdTime;

    @XmlElement(name = "password")
    @JsonProperty("password")
    @NoPrintField
    private String password;

    @XmlElement(name = "advertise-community")
    @JsonProperty("advertise-community")
    private Boolean advertiseCommunity;

    @XmlElement(name = "advertise-ext-community")
    @JsonProperty("advertise-ext-community")
    private Boolean advertiseExtCommunity;

    public String getRemoteAs() {
        return remoteAs;
    }

    public void setRemoteAs(String remoteAs) {
        this.remoteAs = remoteAs;
    }

    public String getPeerIp() {
        return peerIp;
    }

    public void setPeerIp(String peerIp) {
        this.peerIp = peerIp;
    }

    public Integer getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(Integer keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public Integer getHoldTime() {
        return holdTime;
    }

    public void setHoldTime(Integer holdTime) {
        this.holdTime = holdTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdvertiseCommunity() {
        return advertiseCommunity;
    }

    public void setAdvertiseCommunity(Boolean advertiseCommunity) {
        this.advertiseCommunity = advertiseCommunity;
    }

    public Boolean getAdvertiseExtCommunity() {
        return advertiseExtCommunity;
    }

    public void setAdvertiseExtCommunity(Boolean advertiseExtCommunity) {
        this.advertiseExtCommunity = advertiseExtCommunity;
    }
}
