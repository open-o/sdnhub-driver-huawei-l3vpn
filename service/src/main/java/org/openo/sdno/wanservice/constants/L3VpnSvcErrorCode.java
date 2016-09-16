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

package org.openo.sdno.wanservice.constants;


/**
 * The constants of L3VPN service error code.<br>
 * 
 * @author
 * @version SDNO 0.5 Aug 2, 2016
 */
public class L3VpnSvcErrorCode {

    public static final String APP_NAME = "l3vpnsvc";

    public static final String L3VPN_GET_CONTROLLER_FAIL =
            ErrorCodeUtils.getErrorCode(APP_NAME, "l3vpn", "get_vpn_controller_fail");

    public static final String L3VPN_CREATE_CONTROLLER_FAIL =
            ErrorCodeUtils.getErrorCode(APP_NAME, "l3vpn", "create_vpn_controller_fail");

    public static final String L3VPN_DELETE_CONTROLLER_FAIL =
            ErrorCodeUtils.getErrorCode(APP_NAME, "l3vpn", "delete_vpn_controller_fail");

    public static final String L3VPN_UPDATE_STATUS_CONTROLLER_FAIL =
            ErrorCodeUtils.getErrorCode(APP_NAME, "l3vpn", "update_vpn_status_controller_fail");

    public static final String UPDATEDESC_CONTROLLER_FAIL = null;

    public static final String L3VPN_GET_STATUS_CONTROLLER_FAIL = null;

    private L3VpnSvcErrorCode() {
    }
}
