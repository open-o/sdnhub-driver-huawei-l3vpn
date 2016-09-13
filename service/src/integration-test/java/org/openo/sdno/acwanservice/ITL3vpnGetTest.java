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

package org.openo.sdno.acwanservice;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.acwanservice.moco.AcwanCreateHttpsServer;
import org.openo.sdno.acwanservice.moco.L3vpnGetForAcWanDriverServer;
import org.openo.sdno.testframework.checker.RegularExpChecker;
import org.openo.sdno.testframework.http.model.HttpModelUtils;
import org.openo.sdno.testframework.http.model.HttpResponse;
import org.openo.sdno.testframework.http.model.HttpRquestResponse;
import org.openo.sdno.testframework.testmanager.TestManager;
import org.openo.sdno.testframework.util.file.FileUtils;

/**
 * @author tWX380482
 */
public class ITL3vpnGetTest extends TestManager {

    private L3vpnGetForAcWanDriverServer l3vpnGetForAcWanDriverServer = new L3vpnGetForAcWanDriverServer();
    private AcwanCreateHttpsServer acwanHttpsServer = new AcwanCreateHttpsServer();

    /**
     * @throws InterruptedException
     * @throws java.lang.Exception
     */
    @Before
    public void setup() throws ServiceException, InterruptedException {
        l3vpnGetForAcWanDriverServer.start();
        acwanHttpsServer.start();
        // Thread.sleep(10000);
    }

    @After
    public void tearDown() {
        l3vpnGetForAcWanDriverServer.stop();
        acwanHttpsServer.stop();
    }

	@Test
	public void test() throws ServiceException {
		File l3vpnGetFile = new File("src/integration-test/resources/AcWanDriverSvc/l3vpnGet.json");
	    HttpRquestResponse createHttpObject = HttpModelUtils.praseHttpRquestResponse(FileUtils.readFromJson(l3vpnGetFile));
	    HttpResponse createResponse = execTestCase(l3vpnGetFile, new RegularExpChecker(createHttpObject.getResponse()));
	    
	}

}
