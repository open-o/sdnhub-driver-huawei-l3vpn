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

package org.openo.sdno.common.restconf;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 * The tool class of serialize.<br/>
 *
 * @author
 * @version SDNO 0.5 2016-6-2
 */
public class SerializeUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(SerializeUtil.class);

    private SerializeUtil() {

    }

    /**
     * Serialize the body.<br/>
     *
     * @param contentType The enum of contents type
     * @param body The body to be serialized
     * @return The serialized body
     * @since SDNO 0.5
     */
    public static String serialize(ContentType contentType, Object body) throws ServiceException {
        if(contentType == ContentType.JSON) {
            return toJson(body);
        } else {
            return toXml(body);
        }
    }

    /**
     * Deserialize the string object.<br/>
     *
     * @param contentType The enum of contents type
     * @param str The string object to be deserialized
     * @param clazz The class type that deserialize to
     * @return The deserialized object
     * @since SDNO 0.5
     */
    public static <T> T deSerialize(ContentType contentType, String str, Class<T> clazz) throws ServiceException {
        if(contentType == ContentType.JSON) {
            return fromJson(str, clazz);
        } else {
            return fromXml(str, clazz);
        }
    }

    /**
     * Change the body to json.<br/>
     *
     * @param body The body object to be changed
     * @return The json that body is changed to
     * @since SDNO 0.5
     */
    public static String toJson(Object body) throws ServiceException {
        if(body == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        if(body.getClass().getAnnotation(JsonRootName.class) != null) {
            mapper.enable(SerializationConfig.Feature.WRAP_ROOT_VALUE);
        }
        try {
            return mapper.writeValueAsString(body);
        } catch(IOException ex) {
            LOGGER.error("Parser to json error.", ex);
            throw new IllegalArgumentException(
                    (new StringBuilder("Parser obj to json error, obj = ")).append(body).toString(), ex);
        }
    }

    /**
     * Change the json object to the class type.<br/>
     *
     * @param json The json object to be changed
     * @param clazz The class type that the json is changed to
     * @return The object that the json is changed to
     * @since SDNO 0.5
     */
    public static <T> T fromJson(String json, Class<T> clazz) throws ServiceException {
        if(!StringUtils.isEmpty(json)) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        if(clazz.getAnnotation(JsonRootName.class) != null) {
            mapper.enable(DeserializationConfig.Feature.UNWRAP_ROOT_VALUE);
        }
        try {
            return mapper.readValue(json, clazz);
        } catch(IOException ex) {
            LOGGER.error("Parser to object error.", ex);
            throw new IllegalArgumentException((new StringBuilder("Parser json to object error, json = ")).append(json)
                    .append(", expect class = ").append(clazz).toString(), ex);
        }
    }

    /**
     * Change the object to xml.<br/>
     *
     * @param obj The object to be changed
     * @return The xml that object is changed to
     * @since SDNO 0.5
     */
    public static String toXml(Object obj) throws ServiceException {
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);
            return writer.toString();
        } catch(PropertyException e) {
            LOGGER.error("toXml failed.", e);
            throw new ServiceException("toXml failed.", e);
        } catch(JAXBException e) {
            LOGGER.error("toXml failed.", e);
            throw new ServiceException("toXml failed.", e);
        }
    }

    /**
     * Change the xml object to the class type.<br/>
     *
     * @param xml The xml object to be changed
     * @param clazz The class type that the xml is changed to
     * @return The object that the xml is changed to
     * @since SDNO 0.5
     */
    public static <T> T fromXml(String xml, Class<T> clazz) throws ServiceException {
        if(StringUtils.isEmpty(xml)) {
            return null;
        }
        try {
            String trimXml = trimBodyStr(xml);
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader reader = new StringReader(trimXml);
            SAXParserFactory sax = SAXParserFactory.newInstance();
            sax.setNamespaceAware(false);
            String feature = "http://apache.org/xml/features/disallow-doctype-decl";
            sax.setFeature(feature, true);
            feature = "http://xml.org/sax/features/external-general-entities";
            sax.setFeature(feature, false);
            feature = "http://xml.org/sax/features/external-parameter-entities";
            sax.setFeature(feature, false);
            // sax.setXIncludeAware(false);

            XMLReader xmlReader = sax.newSAXParser().getXMLReader();
            Source source = new SAXSource(xmlReader, new InputSource(reader));
            return (T)unmarshaller.unmarshal(source);
        } catch(JAXBException e) {
            LOGGER.error("formXml failed.", e);
            throw new ServiceException("formXml failed.", e);
        } catch(SAXException e) {
            LOGGER.error("formXml failed.", e);
            throw new ServiceException("formXml failed.", e);
        } catch(ParserConfigurationException e) {
            LOGGER.error("formXml failed.", e);
            throw new ServiceException("formXml failed.", e);
        }
    }

    private static String trimBodyStr(String body) {
        if(body.indexOf('"') == 0 && body.lastIndexOf('"') == body.length() - 1) {
            return body.substring(1, body.length() - 1);
        }
        return body;
    }
}
