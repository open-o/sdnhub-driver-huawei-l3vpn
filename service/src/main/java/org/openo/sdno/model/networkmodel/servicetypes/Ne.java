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
import org.openo.sdno.model.networkmodel.NetModel;
import org.openo.sdno.model.servicemodel.tp.Tp;

/**
 * NE class<br>
 * 
 * @author
 * @version SDNO 0.5 August 22, 2016
 */
@XmlRootElement(name = "ne")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id"})
@JsonRootName(value = "ac")
@JsonSerialize(include = Inclusion.NON_NULL)
@JsonPropertyOrder(value = {"id"})
public class Ne implements NetModel {

    @XmlElement(name = "id")
    @JsonProperty("id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * inject ID/NeId of termination point.<br>
     * 
     * @param tp
     * @since SDNO 0.5
     */
    public void inject(Tp tp) {
        setId(tp.getNeId());
    }

}
