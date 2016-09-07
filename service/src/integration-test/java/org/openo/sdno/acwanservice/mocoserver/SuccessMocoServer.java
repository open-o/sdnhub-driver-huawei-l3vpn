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

package org.openo.sdno.acwanservice.mocoserver;

import org.openo.sdno.testframework.http.model.HttpRequest;
import org.openo.sdno.testframework.http.model.HttpResponse;
import org.openo.sdno.testframework.http.model.HttpRquestResponse;
import org.openo.sdno.testframework.moco.MocoHttpServer;
import org.openo.sdno.testframework.moco.responsehandler.MocoResponseHandler;

public class SuccessMocoServer extends MocoHttpServer {

    private static final String GET_CONTROLER_DEVICE_FILE =
            "src/integration-test/resources/adapter/controllerdevice.json";

    private static final String GET_CONTROLER_MO_FILE = "src/integration-test/resources/adapter/controllermo.json";

    private static final String DELETE_L3VPN_SUCCESS_FILE = "src/integration-test/resources/adapter/deletesuccess.json";

    private static final String AUTH_L3VPN_SUCCESS_FILE = "src/integration-test/resources/adapter/authentication.json";

    public SuccessMocoServer(String configFile) {
        // super(configFile);
    }

    public SuccessMocoServer() {
        super();
    }

    @Override
    public void addRequestResponsePairs() {
        this.addRequestResponsePair(GET_CONTROLER_DEVICE_FILE, new CreateVpcSuccessInResponseHandler());
        this.addRequestResponsePair(AUTH_L3VPN_SUCCESS_FILE, new CreateVpcSuccessInResponseHandler());

        this.addRequestResponsePair(GET_CONTROLER_MO_FILE, new CreateVpcSuccessInResponseHandler());
        this.addRequestResponsePair(DELETE_L3VPN_SUCCESS_FILE, new CreateVpcSuccessInResponseHandler());

    }

    private class CreateVpcSuccessInResponseHandler extends MocoResponseHandler {

        @Override
        public void processRequestandResponse(HttpRquestResponse httpObject) {
            HttpRequest req = httpObject.getRequest();
            HttpResponse res = httpObject.getResponse();
        }
    }
}
