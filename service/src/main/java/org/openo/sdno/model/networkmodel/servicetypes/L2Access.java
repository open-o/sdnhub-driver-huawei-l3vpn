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

/**
 * L2Access class.<br>
 * <p>
 * </p>
 *
 * @author
 * @version SDNO 0.5 August 17, 2016
 */
@XmlRootElement(name = "l2-access")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"accessType", "port", "dot1q", "vxlan"})
@JsonRootName(value = "l2-access")
@JsonSerialize(include = Inclusion.NON_NULL)
@JsonPropertyOrder(value = {"accessType", "port", "dot1q", "vxlan"})
public class L2Access {

    @XmlElement(name = "access-type")
    @JsonProperty("access-type")
    private String accessType;

    @XmlElement(name = "port")
    @JsonProperty("port")
    private Port port;

    @XmlElement(name = "dot1q")
    @JsonProperty("dot1q")
    private Dot1q dot1q;

    @XmlElement(name = "vxlan")
    @JsonProperty("vxlan")
    private Vxlan vxlan;

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    public Port getPort() {
        return port;
    }

    public void setPort(Port port) {
        this.port = port;
    }

    public Dot1q getDot1q() {
        return dot1q;
    }

    public void setDot1q(Dot1q dot1q) {
        this.dot1q = dot1q;
    }

    public Vxlan getVxlan() {
        return vxlan;
    }

    public void setVxlan(Vxlan vxlan) {
        this.vxlan = vxlan;
    }

}
