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
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * PathConstraint class.<br/>
 * 
 * @author
 * @version SDNO 0.5 Aug 22, 2016
 */
@XmlRootElement(name = "path-constraint")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"setupPriority", "holdupPriority", "latency"})
@JsonRootName(value = "path-constraint")
@JsonSerialize(include = Inclusion.NON_NULL)
@JsonPropertyOrder(value = {"setupPriority", "holdupPriority", "latency"})
public class PathConstraint {

    @XmlElement(name = "setup-priority")
    @JsonProperty("setup-priority")
    private Integer setupPriority;

    @XmlElement(name = "holdup-priority")
    @JsonProperty("holdup-priority")
    private Integer holdupPriority;

    @XmlElement(name = "latency")
    @JsonProperty("latency")
    private Integer latency;

    public Integer getSetupPriority() {
        return setupPriority;
    }

    public void setLatency(Integer latency) {
        this.latency = latency;
    }

    public void setSetupPriority(Integer setupPriority) {
        this.setupPriority = setupPriority;
    }

    public Integer getLatency() {
        return latency;
    }

    public void setHoldupPriority(Integer holdupPriority) {
        this.holdupPriority = holdupPriority;
    }

    public Integer getHoldupPriority() {
        return holdupPriority;
    }

}
