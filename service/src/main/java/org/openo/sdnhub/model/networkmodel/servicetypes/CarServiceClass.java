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
 * <br>
 * <p>
 * </p>
 *
 * @author
 * @version SDNO 0.5 August 17, 2016
 */
@XmlRootElement(name = "car-service-class")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"flowColor", "passOrDiscard", "serviceClass", "color"})
@JsonRootName(value = "car-service-class")
@JsonSerialize(include = Inclusion.NON_NULL)
@JsonPropertyOrder(value = {"flowColor", "passOrDiscard", "serviceClass", "color"})
public class CarServiceClass {

    @XmlElement(name = "flow-color")
    @JsonProperty("flow-color")
    private String flowColor;

    @XmlElement(name = "pass-or-discard")
    @JsonProperty("pass-or-discard")
    private String passOrDiscard;

    @XmlElement(name = "service-class")
    @JsonProperty("service-class")
    private String serviceClass;

    @XmlElement(name = "color")
    @JsonProperty("color")
    private String color;

    public String getFlowColor() {
        return flowColor;
    }

    public void setFlowColor(String flowColor) {
        this.flowColor = flowColor;
    }

    public String getPassOrDiscard() {
        return passOrDiscard;
    }

    public void setPassOrDiscard(String passOrDiscard) {
        this.passOrDiscard = passOrDiscard;
    }

    public String getServiceClass() {
        return serviceClass;
    }

    public void setServiceClass(String serviceClass) {
        this.serviceClass = serviceClass;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
