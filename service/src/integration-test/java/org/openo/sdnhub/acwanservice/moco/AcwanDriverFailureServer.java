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

package org.openo.sdnhub.acwanservice.moco;

import org.openo.sdno.testframework.http.model.HttpRquestResponse;
import org.openo.sdno.testframework.moco.MocoHttpServer;
import org.openo.sdno.testframework.moco.responsehandler.MocoResponseHandler;

public class AcwanDriverFailureServer extends MocoHttpServer {

    public AcwanDriverFailureServer() {
        super();
    }

    public AcwanDriverFailureServer(String configFile) {
        // super(configFile);
    }

    @Override
    public void addRequestResponsePairs() {

        this.addRequestResponsePair("src/integration-test/resources/AcWanDriverSvc/moco/getDevice.json",
                new AcWanSuccessResponseHandler());
        this.addRequestResponsePair("src/integration-test/resources/AcWanDriverSvc/moco/queryController.json",
                new AcWanSuccessResponseHandler());
        this.addRequestResponsePair("src/integration-test/resources/AcWanDriverSvc/moco/authentication.json",
                new AcWanSuccessResponseHandler());
        this.addRequestResponsePair("src/integration-test/resources/AcWanDriverSvc/moco/CreateFail.json",
                new AcWanSuccessResponseHandler());
        this.addRequestResponsePair("src/integration-test/resources/adapter/deletefail.json",
                new AcWanSuccessResponseHandler());
        this.addRequestResponsePair("src/integration-test/resources/adapter/l3VpnUpdateFail.json",
                new AcWanSuccessResponseHandler());
        this.addRequestResponsePair("src/integration-test/resources/adapter/getL3vpnFail.json",
                new AcWanSuccessResponseHandler());
    }

    private class AcWanSuccessResponseHandler extends MocoResponseHandler {

        @Override
        public void processRequestandResponse(HttpRquestResponse httpObject) {

        }
    }

}
