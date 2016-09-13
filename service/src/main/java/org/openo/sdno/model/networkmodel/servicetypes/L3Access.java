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
 * L3Access class.<br>
 * <p>
 * </p>
 * 
 * @author
 * @version SDNO 0.5 Aug 17, 2016
 */
@XmlRootElement(name = "l3-access")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"address", "staticRoutes", "protocols"})
@JsonRootName(value = "l3-access")
@JsonSerialize(include = Inclusion.NON_NULL)
@JsonPropertyOrder(value = {"address", "staticRoutes", "protocols"})
public class L3Access {

    @XmlElement(name = "address")
    @JsonProperty("address")
    private String address;

    @XmlElementWrapper(name = "static-routes")
    @XmlElement(name = "static-route")
    @JsonProperty("static-routes")
    private List<StaticRoute> staticRoutes;

    @XmlElementWrapper(name = "protocols")
    @XmlElement(name = "protocol")
    @JsonProperty("protocols")
    private List<Protocol> protocols;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<StaticRoute> getStaticRoutes() {
        return staticRoutes;
    }

    public void setStaticRoutes(List<StaticRoute> staticRoutes) {
        this.staticRoutes = staticRoutes;
    }

    public List<Protocol> getProtocols() {
        return protocols;
    }

    public void setProtocols(List<Protocol> protocols) {
        this.protocols = protocols;
    }

}
