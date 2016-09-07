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
 * Ospf class.<br/>
 * 
 * @author
 * @version SDNO 0.5 Aug 22, 2016
 */
@XmlRootElement(name = "ospf")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"area", "importRoutes"})
@JsonRootName(value = "ospf")
@JsonSerialize(include = Inclusion.NON_NULL)
@JsonPropertyOrder(value = {"area", "importRoutes"})
public class Ospf {

    @XmlElement(name = "area")
    @JsonProperty("area")
    private List<OspfArea> area;

    @XmlElement(name = "import-routes")
    @JsonProperty("import-routes")
    private List<ImportRoute> importRoutes;

    public List<OspfArea> getArea() {
        return area;
    }

    public void setArea(List<OspfArea> area) {
        this.area = area;
    }

    public List<ImportRoute> getImportRoutes() {
        return importRoutes;
    }

    public void setImportRoutes(List<ImportRoute> importRoutes) {
        this.importRoutes = importRoutes;
    }
}
