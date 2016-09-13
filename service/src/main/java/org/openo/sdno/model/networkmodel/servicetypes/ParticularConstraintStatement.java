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
 * Particular Constraint Statement class.<br>
 * 
 * @author
 * @version SDNO 0.5 Aug 22, 2016
 */
@XmlRootElement(name = "particular-constraint")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"ingressNeId", "egressNeId", "type", "bindingTunnels", "mplsTe"})
@JsonRootName(value = "particular-constraint")
@JsonSerialize(include = Inclusion.NON_NULL)
@JsonPropertyOrder(value = {"ingressNeId", "egressNeId", "type", "bindingTunnels", "mplsTe"})
public class ParticularConstraintStatement {

    @XmlElement(name = "ingress-ne-id")
    @JsonProperty("ingress-ne-id")
    private String ingressNeId;

    @XmlElement(name = "egress-ne-id")
    @JsonProperty("egress-ne-id")
    private String egressNeId;

    @XmlElement(name = "type")
    @JsonProperty("type")
    private String type;

    @XmlElementWrapper(name = "binding-tunnels")
    @XmlElement(name = "tunnel")
    @JsonProperty("binding-tunnel")
    private List<BindingTunnel> bindingTunnels;

    @XmlElement(name = "mpls-te")
    @JsonProperty("mpls-te")
    private MplsTe mplsTe;

    public String getIngressNeId() {
        return ingressNeId;
    }

    public void setIngressNeId(String ingressNeId) {
        this.ingressNeId = ingressNeId;
    }

    public String getEgressNeId() {
        return egressNeId;
    }

    public void setEgressNeId(String egressNeId) {
        this.egressNeId = egressNeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<BindingTunnel> getBindingTunnels() {
        return bindingTunnels;
    }

    public void setBindingTunnels(List<BindingTunnel> bindingTunnels) {
        this.bindingTunnels = bindingTunnels;
    }

    public MplsTe getMplsTe() {
        return mplsTe;
    }

    public void setMplsTe(MplsTe mplsTe) {
        this.mplsTe = mplsTe;
    }
}
