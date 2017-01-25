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

package org.openo.sdnhub.common.restconf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonRootName;

/**
 * The user authentication information.<br>
 *
 * @author
 * @version SDNHUB 0.5 2016-6-2
 */
@XmlRootElement(name = "passwordDto")
@JsonRootName(value = "passwordDto")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserAuthInfo {

    @XmlElement(required = true)
    private String userName;

    @XmlElement(required = true)
    private String password;

    /**
     * Get the user name.<br>
     *
     * @return the user name to be authenticated
     * @since SDNHUB 0.5
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set the user name.<br>
     *
     * @param userName is the user name to be authenticated
     * @since SDNHUB 0.5
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Get the password.<br>
     *
     * @return the password in string representation
     * @since SDNHUB 0.5
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password.<br>
     *
     * @param password is a password in string representation
     * @since SDNHUB 0.5
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
