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
import org.openo.sdnhub.model.networkmodel.NetModel;

/**
 * Description: <br>
 * Copyright: Copyright (c) 2015-2055<br>
 * Company: Huawei Tech. Co., Ltd<br>
 *
 * @since: iManager NetMatrix V100R003C00,2016/6/23<br>
 * @author: h00220202
 * @version: 1.0
 */

@XmlRootElement(name = "ac-protect-groups")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"acprotectGroup"})
@JsonRootName(value = "ac-protect-groups")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder(value = {"acprotectGroup"})
public class AcProtectGroups implements NetModel {

    @XmlElement(name = "ac-protect-group")
    @JsonProperty("ac-protect-group")
    private AcProtectGroup acprotectGroup;

    public AcProtectGroup getAcprotectGroup() {
        return acprotectGroup;
    }

    public void setAcprotectGroup(AcProtectGroup acprotectGroup) {
        this.acprotectGroup = acprotectGroup;
    }

}
