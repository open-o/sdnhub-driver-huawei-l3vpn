package org.openo.sdno.acwanservice;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.acwanservice.moco.AcwanDriverFailureServer;
import org.openo.sdno.testframework.checker.IChecker;
import org.openo.sdno.testframework.http.model.HttpModelUtils;
import org.openo.sdno.testframework.http.model.HttpRequest;
import org.openo.sdno.testframework.http.model.HttpResponse;
import org.openo.sdno.testframework.http.model.HttpRquestResponse;
import org.openo.sdno.testframework.testmanager.TestManager;
import org.openo.sdno.testframework.util.file.FileUtils;

public class ITCreateFailTest  extends TestManager{

	 private AcwanDriverFailureServer acwanServer = new AcwanDriverFailureServer();


	    @Before
	    public void setup() throws ServiceException {
	        acwanServer.start();
	    }

	    @After
	    public void tearDown() {
	        acwanServer.stop();
	    }

	    @Test
	    public void test() throws ServiceException {
	        File createFile = new File("src/integration-test/resources/AcWanDriverSvc/createfail.json");
	        HttpRquestResponse createHttpObject =
	                HttpModelUtils.praseHttpRquestResponse(FileUtils.readFromJson(createFile));
	        HttpRequest createRequest = createHttpObject.getRequest();
	        execTestCase(createRequest, new FailChecker());
	    }
	    
	    @Test
        public void testDeleteFail() throws ServiceException {
            File createFile = new File("src/integration-test/resources/testcase/l3vpn-delete.json");
            HttpRquestResponse createHttpObject =
                    HttpModelUtils.praseHttpRquestResponse(FileUtils.readFromJson(createFile));
            HttpRequest createRequest = createHttpObject.getRequest();
            execTestCase(createRequest, new FailChecker());
        }
	    
	    @Test
        public void testUpdateFail() throws ServiceException {
            File createFile = new File("src/integration-test/resources/AcWanDriverSvc/l3VpnUpdate.json");
            HttpRquestResponse createHttpObject =
                    HttpModelUtils.praseHttpRquestResponse(FileUtils.readFromJson(createFile));
            HttpRequest createRequest = createHttpObject.getRequest();
            execTestCase(createRequest, new FailChecker());
        }
	    
	    @Test
        public void testGetFail() throws ServiceException {
            File createFile = new File("src/integration-test/resources/AcWanDriverSvc/l3vpnGet.json");
            HttpRquestResponse createHttpObject =
                    HttpModelUtils.praseHttpRquestResponse(FileUtils.readFromJson(createFile));
            HttpRequest createRequest = createHttpObject.getRequest();
            execTestCase(createRequest, new FailChecker());
        }
	    
	    private class FailChecker implements IChecker {

	        @Override
	        public boolean check(HttpResponse response) {
	            if (response.getStatus() >= 200 && response.getStatus() <= 204 ) {
	                return false;
	            }
	            return true;
	        }
	    }
}
