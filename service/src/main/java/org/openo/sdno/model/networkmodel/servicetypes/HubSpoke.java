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
 * HubSpoke class<br>
 * <p>
 * </p>
 * 
 * @author
 * @version SDNO 0.5 August 17, 2016
 */
@XmlRootElement(name = "hub-spoke")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"hubGroup", "spokeGroup"})
@JsonRootName(value = "hub-spoke")
@JsonSerialize(include = Inclusion.NON_NULL)
@JsonPropertyOrder(value = {"hubGroup", "spokeGroup"})
public class HubSpoke {

    @XmlElementWrapper(name = "hub-group")
    @XmlElement(name = "hub-ac")
    @JsonProperty("hub-group")
    private List<HubAc> hubGroup;

    @XmlElement(name = "spoke-group")
    @JsonProperty("spoke-group")
    private SpokeGroup spokeGroup;

    public List<HubAc> getHubGroup() {
        return hubGroup;
    }

    public void setHubGroup(List<HubAc> hubGroup) {
        this.hubGroup = hubGroup;
    }

    public SpokeGroup getSpokeGroup() {
        return spokeGroup;
    }

    public void setSpokeGroup(SpokeGroup spokeGroup) {
        this.spokeGroup = spokeGroup;
    }

}
