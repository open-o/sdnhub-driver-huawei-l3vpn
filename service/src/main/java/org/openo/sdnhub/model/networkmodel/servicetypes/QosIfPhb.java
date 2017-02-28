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

/**
 * QOS If PHB class<br>
 * <p>
 * </p>
 *
 * @author
 * @version SDNO 0.5 August 17, 2016
 */
@XmlRootElement(name = "qos-if-phb")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"direction", "trust8021p", "trustUpstream", "phbMap"})
@JsonRootName(value = "qos-if-phb")
@JsonSerialize(include = Inclusion.NON_NULL)
@JsonPropertyOrder(value = {"direction", "trust8021p", "trustUpstream", "phbMap"})
public class QosIfPhb {

    @XmlElement(name = "direction")
    @JsonProperty("direction")
    private String direction;

    @XmlElement(name = "trust-8021p")
    @JsonProperty("trust-8021p")
    private Boolean trust8021p;

    @XmlElement(name = "trust-upstream")
    @JsonProperty("trust-upstream")
    private Boolean trustUpstream;

    @XmlElement(name = "phb-map")
    @JsonProperty("phb-map")
    private Boolean phbMap;

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Boolean getTrust8021p() {
        return trust8021p;
    }

    public void setTrust8021p(Boolean trust8021p) {
        this.trust8021p = trust8021p;
    }

    public Boolean getTrustUpstream() {
        return trustUpstream;
    }

    public void setTrustUpstream(Boolean trustUpstream) {
        this.trustUpstream = trustUpstream;
    }

    public Boolean getPhbMap() {
        return phbMap;
    }

    public void setPhbMap(Boolean phbMap) {
        this.phbMap = phbMap;
    }
}
