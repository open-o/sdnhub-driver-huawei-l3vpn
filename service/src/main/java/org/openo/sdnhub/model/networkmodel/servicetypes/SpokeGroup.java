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
@XmlRootElement(name = "spoke-group")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"localBridge", "spokeAcs"})
@JsonRootName(value = "spoke-group")
@JsonSerialize(include = Inclusion.NON_NULL)
@JsonPropertyOrder(value = {"localBridge", "spokeAcs"})
public class SpokeGroup {

    @XmlElement(name = "local-bridge")
    @JsonProperty("local-bridge")
    private Boolean localBridge;

    @XmlElement(name = "spoke-ac")
    @JsonProperty("spoke-ac")
    List<SpokeAc> spokeAcs;

    public List<SpokeAc> getSpokeAcs() {
        return spokeAcs;
    }

    public void setSpokeAcs(List<SpokeAc> spokeAcs) {
        this.spokeAcs = spokeAcs;
    }

    public Boolean getLocalBridge() {
        return localBridge;
    }

    public void setLocalBridge(Boolean localBridge) {
        this.localBridge = localBridge;
    }

}
