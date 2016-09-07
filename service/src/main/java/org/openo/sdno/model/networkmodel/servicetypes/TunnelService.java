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
import org.openo.sdno.model.networkmodel.NetModel;

/**
 * <br/>
 * <p>
 * </p>
 * 
 * @author
 * @version SDNO 0.5 Aug 17, 2016
 */
@XmlRootElement(name = "tunnel-service")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"type", "autoSelect", "mplsTe", "particularConstraints"})
@JsonRootName(value = "tunnel-service")
@JsonSerialize(include = Inclusion.NON_NULL)
@JsonPropertyOrder(value = {"type", "autoSelect", "mplsTe", "particularConstraints"})
public class TunnelService implements NetModel {

    @XmlElement(name = "type")
    @JsonProperty("type")
    private String type;

    @XmlElement(name = "auto-select")
    @JsonProperty("auto-select")
    private AutoSelect autoSelect;

    @XmlElement(name = "mpls-te")
    @JsonProperty("mpls-te")
    private MplsTe mplsTe;

    @JsonProperty("particular-constraints")
    private List<ParticularConstraintStatement> particularConstraints;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AutoSelect getAutoSelect() {
        return autoSelect;
    }

    public void setAutoSelect(AutoSelect autoSelect) {
        this.autoSelect = autoSelect;
    }

    public MplsTe getMplsTe() {
        return mplsTe;
    }

    public void setMplsTe(MplsTe mplsTe) {
        this.mplsTe = mplsTe;
    }

    public List<ParticularConstraintStatement> getParticularConstraints() {
        return particularConstraints;
    }

    public void setParticularConstraints(List<ParticularConstraintStatement> particularConstraints) {
        this.particularConstraints = particularConstraints;
    }
}
