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

import java.math.BigInteger;

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
 * Qos If Car class<br/>
 * <p>
 * </p>
 * 
 * @author
 * @version SDNO 0.5 Aug 17, 2016
 */
@XmlRootElement(name = "qos-if-car")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"direction", "enable", "cir", "pir", "cbs", "pbs", "carServiceClass"})
@JsonRootName(value = "qos-if-car")
@JsonSerialize(include = Inclusion.NON_NULL)
@JsonPropertyOrder(value = {"direction", "enable", "cir", "pir", "cbs", "pbs", "carServiceClass"})
public class QosIfCar {

    @XmlElement(name = "direction")
    @JsonProperty("direction")
    private String direction;

    @XmlElement(name = "enable")
    @JsonProperty("enable")
    private Boolean enable;

    @XmlElement(name = "cir")
    @JsonProperty("cir")
    private BigInteger cir;

    @XmlElement(name = "pir")
    @JsonProperty("pir")
    private BigInteger pir;

    @XmlElement(name = "cbs")
    @JsonProperty("cbs")
    private BigInteger cbs;

    @XmlElement(name = "pbs")
    @JsonProperty("pbs")
    private BigInteger pbs;

    @XmlElement(name = "car-service-class")
    @JsonProperty("car-service-class")
    private CarServiceClass carServiceClass;

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public BigInteger getCir() {
        return cir;
    }

    public void setCir(BigInteger cir) {
        this.cir = cir;
    }

    public BigInteger getPir() {
        return pir;
    }

    public void setPir(BigInteger pir) {
        this.pir = pir;
    }

    public BigInteger getCbs() {
        return cbs;
    }

    public void setCbs(BigInteger cbs) {
        this.cbs = cbs;
    }

    public BigInteger getPbs() {
        return pbs;
    }

    public void setPbs(BigInteger pbs) {
        this.pbs = pbs;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public CarServiceClass getCarServiceClass() {
        return carServiceClass;
    }

    public void setCarServiceClass(CarServiceClass carServiceClass) {
        this.carServiceClass = carServiceClass;
    }

}
