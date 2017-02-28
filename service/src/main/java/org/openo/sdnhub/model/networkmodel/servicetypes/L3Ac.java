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
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonRootName;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.openo.sdno.model.servicemodel.tp.Tp;

/**
 * <br>
 * <p>
 * </p>
 *
 * @author
 * @version SDNO 0.5 August 17, 2016
 */
@XmlRootElement(name = "ac")
@XmlAccessorType(XmlAccessType.FIELD)
/*
 * @XmlType(propOrder = { "id", "name", "neId", "ltpId", "adminStatus", "operateStatus", "role",
 * "l2Access", "l3Access",
 * "vxlanAccess", "qosPolicy" })
 */
@JsonRootName(value = "ac")
@JsonSerialize(include = Inclusion.NON_NULL)
/*
 * @JsonPropertyOrder(value = { "id", "name", "neId", "ltpId", "adminStatus", "operateStatus",
 * "role", "l2Access",
 * "l3Access", "vxlanAccess", "qosPolicy" })
 */
public class L3Ac extends Ac {

    /**
     * inject ID/NeId of termination point.<br>
     *
     * @param tp
     * @since SDNHUB 0.5
     */
    @Override
    public void inject(Tp tp) {
        // Need to set Ltp Id
        setId(tp.getId());
        setNeId(tp.getNeId());
    }
}
