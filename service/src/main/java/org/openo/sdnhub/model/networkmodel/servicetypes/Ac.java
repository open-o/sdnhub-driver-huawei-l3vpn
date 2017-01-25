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
import org.openo.sdnhub.model.networkmodel.NetModel;
import org.openo.sdno.model.servicemodel.tp.Tp;

/**
 * AC class<br>
 * 
 * @author
 * @version SDNO 0.5 August 22, 2016
 */
@XmlRootElement(name = "ac")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "neId", "userLabel", "l2Access", "l3Access", "vxlanAccess", "qos"})
@JsonRootName(value = "ac")
@JsonSerialize(include = Inclusion.NON_NULL)
@JsonPropertyOrder(value = {"id", "neId", "userLabel", "l2Access", "l3Access", "vxlanAccess", "qos"})
public class Ac implements NetModel {

    @XmlElement(name = "id")
    @JsonProperty("id")
    private String id;

    @XmlElement(name = "ne-id")
    @JsonProperty("ne-id")
    private String neId;

    @XmlElement(name = "user-label")
    @JsonProperty("user-label")
    private String userLabel;

    @XmlElement(name = "l2-access")
    @JsonProperty("l2-access")
    private L2Access l2Access;

    @XmlElement(name = "l3-access")
    @JsonProperty("l3-access")
    private L3Access l3Access;

    @XmlElement(name = "vxlan-access")
    @JsonProperty("vxlan-access")
    private VxlanAccess vxlanAccess;

    @XmlElement(name = "qos")
    @JsonProperty("qos")
    private Qos qos;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNeId() {
        return neId;
    }

    public void setNeId(String neId) {
        this.neId = neId;
    }

    public String getUserLabel() {
        return userLabel;
    }

    public void setUserLabel(String userLabel) {
        this.userLabel = userLabel;
    }

    public L2Access getL2Access() {
        return l2Access;
    }

    public void setL2Access(L2Access l2Access) {
        this.l2Access = l2Access;
    }

    public L3Access getL3Access() {
        return l3Access;
    }

    public void setL3Access(L3Access l3Access) {
        this.l3Access = l3Access;
    }

    public Qos getQos() {
        return qos;
    }

    public void setQos(Qos qos) {
        this.qos = qos;
    }

    /**
     * inject ID/NeId of termination point.<br>
     * 
     * @param tp
     * @since SDNHUB 0.5
     */
    public void inject(Tp tp) {
        setId(tp.getId());
        setNeId(tp.getNeId());
    }

}
