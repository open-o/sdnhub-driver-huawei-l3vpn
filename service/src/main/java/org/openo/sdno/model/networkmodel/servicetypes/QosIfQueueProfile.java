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
 * QOS If Queue Profile class.<br>
 * <p>
 * </p>
 * 
 * @author
 * @version SDNO 0.5 August 17, 2016
 */
@XmlRootElement(name = "queue-profile")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"direction", "queueProfileId"})
@JsonRootName(value = "queue-profile")
@JsonSerialize(include = Inclusion.NON_NULL)
@JsonPropertyOrder(value = {"direction", "queueProfileId"})
public class QosIfQueueProfile {

    @XmlElement(name = "direction")
    @JsonProperty("direction")
    private String direction;

    @XmlElement(name = "queue-profile-id")
    @JsonProperty("queue-profile-id")
    private String queueProfileId;

    public QosIfQueueProfile() {
        // Default constructor .. to allow normal initialization.
    }

    /**
     * QOS If Queue Profile Constructor<br>
     * 
     * @param direction
     * @param queueProfileId
     * @since SDNO 0.5
     */
    public QosIfQueueProfile(String direction, String queueProfileId) {
        this.direction = direction;
        this.queueProfileId = queueProfileId;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getQueueProfileId() {
        return queueProfileId;
    }

    public void setQueueProfileId(String queueProfileId) {
        this.queueProfileId = queueProfileId;
    }

}
