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
import org.openo.sdno.model.networkmodel.NetModel;

/**
 * AcProtectGroup class.<br>
 * <p>
 * </p>
 *
 * @author
 * @version SDNO 0.5 August 17, 2016
 */
@XmlRootElement(name = "ac-protect-groups")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"masterAcId", "backAcId", "vrrp"})
@JsonRootName(value = "ac-protect-groups")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder(value = {"masterAcId", "backAcId", "vrrp"})
public class AcProtectGroup implements NetModel {

    @XmlElement(name = "master-ac-id")
    @JsonProperty("master-ac-id")
    private String masterAcId;

    @XmlElement(name = "backup-ac-id")
    @JsonProperty("backup-ac-id")
    private String backAcId;

    @XmlElement(name = "vrrp")
    @JsonProperty("vrrp")
    private Vrrp vrrp;

    public Vrrp getVrrp() {
        return vrrp;
    }

    public void setVrrp(final Vrrp vrrp) {
        this.vrrp = vrrp;
    }

    public String getBackAcId() {
        return backAcId;
    }

    public void setBackAcId(final String backAcId) {
        this.backAcId = backAcId;
    }

    public String getMasterAcId() {
        return masterAcId;
    }

    public void setMasterAcId(final String masterAcId) {
        this.masterAcId = masterAcId;
    }
}
