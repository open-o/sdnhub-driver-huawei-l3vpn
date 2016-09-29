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

package org.openo.sdno.model.networkmodel.servicetypes.enumeration;

/**
 * The enum class of route type.<br>
 *
 * @author
 * @version SDNO 0.5 2016-6-6
 */
public enum RouteType {
    STATIC(0, "static"), BGP(1, "bgp");

    private int value;

    private String name;

    /**
     * Constructor to initialise the class.<br>
     *
     * @param value is a value
     * @param name is a type of routing
     * @since SDNO 0.5
     */
    private RouteType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    /**
     * Get the route type value.<br>
     *
     * @return route type value
     * @since SDNO 0.5
     */
    public int getValue() {
        return value;
    }

    /**
     * Set the route type value.<br>
     *
     * @param value is a route type value
     * @since SDNO 0.5
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Get the route type.<br>
     *
     * @return the route type
     * @since SDNO 0.5
     */
    public String getName() {
        return name;
    }

    /**
     * Set the route type.<br>
     *
     * @param name is the route type
     * @since SDNO 0.5
     */
    public void setName(String name) {
        this.name = name;
    }
}
