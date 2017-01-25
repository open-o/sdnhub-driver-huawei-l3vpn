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

import java.net.URL;
import java.util.Map;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdnhub.common.services.ESRutil;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.model.servicemodel.brs.Device;
import org.openo.sdno.util.http.HTTPRequestMessage;
import org.openo.sdno.util.http.HTTPReturnMessage;
import org.openo.sdno.util.http.HTTPSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Rest conf proxy class.<br>
 *
 * @author
 * @version SDNHUB 0.5 2016-6-2
 */
public class RestConfProxy {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestConfProxy.class);

    private static final String AUTH_URL = "/controller/v2/tokens";

    private static final String PROTOCOL = "https://";

    private static final int DEFAULT_CON_TIMEOUT = 5000;

    private static final int DEFAULT_READ_TIMEOUT = 120000;

    private RestConfProxy() {

    }

    /**
     * Call the get method through the URL.<br>
     *
     * @param contentType the enumeration of contents type
     * @param url the URL to be called
     * @param controller the uuid of controller
     * @return the HTTP response message from rest conf
     * @throws ServiceException
     * @since SDNHUB 0.5
     */
    public static HTTPReturnMessage get(final ContentType contentType, final String url, final String controller)
            throws ServiceException {
        LOGGER.info("url:" + url);
        final Device dev = getControllerDevice(controller);
        final HTTPSender httpSender = buildHTTPSender(contentType);
        return httpSender.restInvoke(buildAuthParam(contentType, dev), buildParam(contentType, url, dev, null, "GET"));
    }

    /**
     * Call the post method through the URL.<br>
     *
     * @param contentType the enumeration of contents type
     * @param url the URL to be called
     * @param controller the uuid of controller
     * @param body the request body
     * @return the HTTP response message from rest conf
     * @throws ServiceException
     * @since SDNHUB 0.5
     */
    public static HTTPReturnMessage post(final ContentType contentType, final String url, final String controller,
            final String body) throws ServiceException {
        LOGGER.info("url:" + url);
        LOGGER.info("body:" + body);
        final Device dev = getControllerDevice(controller);
        final HTTPSender httpSender = buildHTTPSender(contentType);
        return httpSender.restInvoke(buildAuthParam(contentType, dev), buildParam(contentType, url, dev, body, "POST"));
    }

    /**
     * Call the delete method through the URL.<br>
     *
     * @param contentType the enumeration of contents type
     * @param url the URL to be called
     * @param controller the uuid of controller
     * @return the HTTP response message from rest conf
     * @throws ServiceException
     * @since SDNHUB 0.5
     */
    public static HTTPReturnMessage del(final ContentType contentType, final String url, final String controller)
            throws ServiceException {
        LOGGER.info("url:" + url);
        final Device dev = getControllerDevice(controller);
        final HTTPSender httpSender = buildHTTPSender(contentType);
        return httpSender.restInvoke(buildAuthParam(contentType, dev),
                buildParam(contentType, url, dev, null, "DELETE"));
    }

    /**
     * Call the put method through the URL.<br>
     *
     * @param contentType the enumeration of contents type
     * @param url the URL to be called
     * @param controller the uuid of controller
     * @param body the request body
     * @return the HTTP response message from rest conf
     * @throws ServiceException
     * @since SDNHUB 0.5
     */
    public static HTTPReturnMessage put(final ContentType contentType, final String url, final String controller,
            final String body) throws ServiceException {
        LOGGER.info("url:" + url);
        LOGGER.info("body:" + body);
        final Device dev = getControllerDevice(controller);
        final HTTPSender httpSender = buildHTTPSender(contentType);
        return httpSender.restInvoke(buildAuthParam(contentType, dev), buildParam(contentType, url, dev, body, "PUT"));
    }

    /**
     * get the controller device.<br>
     *
     * @param controller is uuid of the controller
     * @return the device
     * @throws ServiceException
     * @since SDNHUB 0.5
     */
    public static Device getControllerDevice(String controller) throws ServiceException {
        Device device = new Device();
        try {
            Map contentMap = ESRutil.getControllerDetails(controller);
            device.setUser((String)contentMap.get("userName"));
            device.setPwd((String)contentMap.get("password"));

            URL url = new URL((String)contentMap.get("url"));
            device.setIp(url.getHost());
            device.setPort(url.getPort());
        } catch(Exception e) {
            LOGGER.error("Error in getting controller", e);
            throw new ServiceException("Error in getting controller", e);
        }

        return device;
    }

    /**
     * Builder function for HTTP.<br>
     *
     * @param contentType the enumeration of contents type
     * @return the HTTPSender
     * @throws ServiceException
     * @since SDNHUB 0.5
     */
    private static HTTPSender buildHTTPSender(final ContentType contentType) {
        final HTTPSender httpSender = new HttpProxy();
        httpSender.setConnectTimeout(DEFAULT_CON_TIMEOUT);
        httpSender.setReadTimeout(DEFAULT_READ_TIMEOUT);

        if(contentType == ContentType.XML) {
            httpSender.setHttpContentType("application/xml");
            httpSender.setHttpAccept("application/xml");
        } else {
            httpSender.setHttpContentType("application/json");
            httpSender.setHttpAccept("application/json");
        }
        return httpSender;
    }

    /**
     * Builder function for HTTP authentication parameters.<br>
     *
     * @param contentType the enumeration of contents type
     * @param dev is a device
     * @return the HTTPRequestMessage
     * @throws ServiceException
     * @since SDNHUB 0.5
     */
    private static HTTPRequestMessage buildAuthParam(final ContentType contentType, final Device dev)
            throws ServiceException {
        final HTTPRequestMessage message = new HTTPRequestMessage();
        final String urlPrefix = getUrlPrefix(dev);
        message.setUrl(urlPrefix + AUTH_URL);
        message.setAction("POST");
        message.setBody(buildAuthContext(contentType, dev));
        return message;
    }

    /**
     * Builder function for HTTP parameters.<br>
     *
     * @param contentType the enumeration of contents type
     * @param url is a URL string
     * @param dev is a device
     * @param body HTTP body
     * @param action type of action
     * @return the HTTPRequestMessage
     * @throws ServiceException
     * @since SDNHUB 0.5
     */
    private static HTTPRequestMessage buildParam(final ContentType contentType, final String url, final Device dev,
            final String body, final String action) throws ServiceException {
        final HTTPRequestMessage message = new HTTPRequestMessage();
        final String urlPrefix = getUrlPrefix(dev);
        message.setUrl(urlPrefix + url);
        message.setAction(action);
        message.setBody(body);
        return message;
    }

    /**
     * get the URL prefix.<br>
     *
     * @param dev is a device
     * @return the URL string
     * @since SDNHUB 0.5
     */
    private static String getUrlPrefix(final Device dev) {
        final StringBuilder sb = new StringBuilder(PROTOCOL);
        sb.append(dev.getIp());
        if(dev.getPort() > 0) {
            sb.append(':').append(dev.getPort());
        }
        return sb.toString();
    }

    /**
     * Builder function for authentication context.<br>
     *
     * @param contentType the enumeration of contents type
     * @param dev is a device
     * @return the authentication context information
     * @throws ServiceException
     * @since SDNHUB 0.5
     */
    private static String buildAuthContext(final ContentType contentType, final Device dev) throws ServiceException {
        final UserAuthInfo userAuth = new UserAuthInfo();
        userAuth.setUserName(dev.getUser());
        userAuth.setPassword(dev.getPwd());
        return JsonUtil.toJson(userAuth);

    }
}
