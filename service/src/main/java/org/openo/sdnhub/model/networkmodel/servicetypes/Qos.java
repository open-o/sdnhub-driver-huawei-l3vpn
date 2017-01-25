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
 * QOS class.<br>
 * <p>
 * </p>
 * 
 * @author
 * @version SDNO 0.5 August 17, 2016
 */
@XmlRootElement(name = "qos")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"qosIfCars", "qosIfPhbs", "qosIfTrafficPolicys", "qosIfQueueProfiles"})
@JsonRootName(value = "qos")
@JsonSerialize(include = Inclusion.NON_NULL)
@JsonPropertyOrder(value = {"qosIfCars", "qosIfPhbs", "qosIfTrafficPolicys", "qosIfQueueProfiles"})
public class Qos {

    @XmlElementWrapper(name = "qos-if-cars")
    @XmlElement(name = "qos-if-car")
    @JsonProperty("qos-if-cars")
    private List<QosIfCar> qosIfCars;

    @XmlElementWrapper(name = "qos-if-phbs")
    @XmlElement(name = "qos-if-phb")
    @JsonProperty("qos-if-phbs")
    private List<QosIfPhb> qosIfPhbs;

    @XmlElementWrapper(name = "qos-if-traffic-policys")
    @XmlElement(name = "traffic-policy")
    @JsonProperty("qos-if-traffic-policys")
    private List<QosIfTrafficPolicy> qosIfTrafficPolicys;

    @XmlElementWrapper(name = "qos-if-queue-profiles")
    @XmlElement(name = "queue-profile")
    @JsonProperty("qos-if-queue-profiles")
    private List<QosIfQueueProfile> qosIfQueueProfiles;

    public List<QosIfPhb> getQosIfPhbs() {
        return qosIfPhbs;
    }

    public void setQosIfPhbs(List<QosIfPhb> qosIfPhbs) {
        this.qosIfPhbs = qosIfPhbs;
    }

    public List<QosIfTrafficPolicy> getQosIfTrafficPolicys() {
        return qosIfTrafficPolicys;
    }

    public void setQosIfTrafficPolicys(List<QosIfTrafficPolicy> qosIfTrafficPolicys) {
        this.qosIfTrafficPolicys = qosIfTrafficPolicys;
    }

    public List<QosIfQueueProfile> getQosIfQueueProfiles() {
        return qosIfQueueProfiles;
    }

    public void setQosIfQueueProfiles(List<QosIfQueueProfile> qosIfQueueProfiles) {
        this.qosIfQueueProfiles = qosIfQueueProfiles;
    }

    public List<QosIfCar> getQosIfCars() {
        return qosIfCars;
    }

    public void setQosIfCars(List<QosIfCar> qosIfCars) {
        this.qosIfCars = qosIfCars;
    }

}
