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
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.openo.sdnhub.model.networkmodel.NetModel;

/**
 * <br>
 * <p>
 * </p>
 * 
 * @author
 * @version SDNO 0.5 August 17, 2016
 */
@XmlRootElement(name = "auto-select")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"loadBalanceNumber", "selectTunnels"})
@JsonRootName(value = "auto-select")
@JsonSerialize(include = Inclusion.NON_NULL)
@JsonPropertyOrder(value = {"loadBalanceNumber", "selectTunnels"})
public class AutoSelect implements NetModel {

    @XmlElement(name = "load-balance-number")
    @JsonProperty("load-balance-number")
    private Integer loadBalanceNumber;

    @XmlElementWrapper(name = "select-tunnels")
    @XmlElement(name = "select-tunnel")
    @JsonProperty("select-tunnels")
    private List<SelectTunnel> selectTunnels;

    public Integer getLoadBalanceNumber() {
        return loadBalanceNumber;
    }

    public void setLoadBalanceNumber(Integer loadBalanceNumber) {
        this.loadBalanceNumber = loadBalanceNumber;
    }

    public List<SelectTunnel> getSelectTunnels() {
        return selectTunnels;
    }

    public void setSelectTunnels(List<SelectTunnel> selectTunnels) {
        this.selectTunnels = selectTunnels;
    }

}
