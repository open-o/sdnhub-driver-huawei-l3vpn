/*
 * Copyright (c) 2016, Huawei Technologies Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
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
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.openo.sdno.model.networkmodel.NetModel;

/**
 * <br/>
 * <p>
 * </p>
 * 
 * @author
 * @version SDNO 0.5 Aug 17, 2016
 */
@XmlRootElement(name = "vrrp")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "virtualIp" })
@JsonRootName(value = "vrrp")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Vrrp implements NetModel {

    @XmlElement(name = "virtual-ip")
    @JsonProperty("virtual-ip")
    private String virtualIp;

    public String getVirtualIp() {
        return virtualIp;
    }

    public void setVirtualIp(final String virtualIp) {
        this.virtualIp = virtualIp;
    }
}