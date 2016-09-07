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
 * L3 ACs model class.<br/>
 *
 * @author
 * @version SDNO 0.5 Jul 22, 2016
 */
@XmlRootElement(name = "acs")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"l3Ac"})
@JsonRootName(value = "acs")
@JsonSerialize(include = Inclusion.NON_NULL)
@JsonPropertyOrder(value = {"l3Ac"})
public class L3Acs {

    @XmlElement(name = "ac")
    @JsonProperty("ac")
    private List<L3Ac> l3Ac = null;

    public List<L3Ac> getL3Ac() {
        return l3Ac;
    }

    public void setL3Ac(List<L3Ac> l3Ac) {
        this.l3Ac = l3Ac;
    }

}
