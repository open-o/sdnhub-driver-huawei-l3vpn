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
 * Dot1q class.<br>
 * <p>
 * </p>
 * 
 * @author
 * @version SDNO 0.5 Aug 17, 2016
 */
@XmlRootElement(name = "dot1q")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"ltpId", "vlanId"})
@JsonRootName(value = "dot1q")
@JsonSerialize(include = Inclusion.NON_NULL)
@JsonPropertyOrder(value = {"ltpId", "vlanId"})
public class Dot1q {

    @XmlElement(name = "ltp-id")
    @JsonProperty("ltp-id")
    private String ltpId;

    @XmlElement(name = "vlan-id")
    @JsonProperty("vlan-id")
    private Integer vlanId;

    public Integer getVlanId() {
        return vlanId;
    }

    public void setVlanId(Integer vlanId) {
        this.vlanId = vlanId;
    }

    public String getLtpId() {
        return ltpId;
    }

    public void setLtpId(String ltpId) {
        this.ltpId = ltpId;
    }

}
