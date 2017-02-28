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
 * QOS If Traffic Policy class.<br>
 * <p>
 * </p>
 *
 * @author
 * @version SDNO 0.5 August 17, 2016
 */
@XmlRootElement(name = "traffic-policy")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"direction", "trafficPolicyId"})
@JsonRootName(value = "traffic-policy")
@JsonSerialize(include = Inclusion.NON_NULL)
@JsonPropertyOrder(value = {"direction", "trafficPolicyId"})
public class QosIfTrafficPolicy {

    @XmlElement(name = "direction")
    @JsonProperty("direction")
    private String direction;

    @XmlElement(name = "traffic-policy-id")
    @JsonProperty("traffic-policy-id")
    private String trafficPolicyId;

    public QosIfTrafficPolicy() {
        // Default constructor .. to allow normal initialization.
    }

    /**
     * QOS If Traffic Policy Constructor<br>
     *
     * @param direction
     * @param trafficPolicyId
     * @since SDNHUB 0.5
     */
    public QosIfTrafficPolicy(String direction, String trafficPolicyId) {
        this.direction = direction;
        this.trafficPolicyId = trafficPolicyId;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getTrafficPolicyId() {
        return trafficPolicyId;
    }

    public void setTrafficPolicyId(String trafficPolicyId) {
        this.trafficPolicyId = trafficPolicyId;
    }

}
