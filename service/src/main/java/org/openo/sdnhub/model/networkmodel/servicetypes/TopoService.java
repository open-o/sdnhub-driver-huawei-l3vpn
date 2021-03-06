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
import org.openo.sdnhub.model.networkmodel.NetModel;

/**
 * TopoService class.<br>
 * <p>
 * </p>
 *
 * @author
 * @version SDNO 0.5 August 17, 2016
 */
@XmlRootElement(name = "topo-service")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"hubSpoke"})
@JsonRootName(value = "topo-service")
@JsonSerialize(include = Inclusion.NON_NULL)
@JsonPropertyOrder(value = {"hubSpoke"})
public class TopoService implements NetModel {

    @XmlElement(name = "hub-spoke")
    @JsonProperty("hub-spoke")
    private HubSpoke hubSpoke;

    public HubSpoke getHubSpoke() {
        return hubSpoke;
    }

    public void setHubSpoke(HubSpoke hubSpoke) {
        this.hubSpoke = hubSpoke;
    }

}
