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

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * Protocol class.<br>
 * <p>
 * </p>
 *
 * @author
 * @version SDNO 0.5 August 17, 2016
 */
@XmlRootElement(name = "protocol")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"type", "bgp", "isis", "ospf"})
@JsonRootName(value = "protocol")
@JsonSerialize(include = Inclusion.NON_NULL)
@JsonPropertyOrder(value = {"type", "bgp", "isis", "ospf"})
public class Protocol {

    // bgp isis ospf
    @XmlElement(name = "type")
    @JsonProperty("type")
    private String type;

    @XmlElementWrapper(name = "bgp")
    @XmlElement(name = "bgp-peer")
    @JsonProperty("bgp")
    private List<BgpPeer> bgp;

    @XmlElement(name = "isis")
    @JsonProperty("isis")
    private ISIS isis;

    @XmlElement(name = "area")
    @JsonProperty("ospf")
    private Ospf ospf;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<BgpPeer> getBgp() {
        return bgp;
    }

    public void setBgp(List<BgpPeer> bgp) {
        this.bgp = bgp;
    }

    public ISIS getIsis() {
        return isis;
    }

    public void setIsis(ISIS isis) {
        this.isis = isis;
    }

    public Ospf getOspf() {
        return ospf;
    }

    public void setOspf(Ospf ospf) {
        this.ospf = ospf;
    }

}
