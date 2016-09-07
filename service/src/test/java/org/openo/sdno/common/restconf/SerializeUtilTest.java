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

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.validation.constraints.AssertTrue;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.poi.ss.formula.functions.T;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.model.servicemodel.brs.Device;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import mockit.Mock;
import mockit.MockUp;

public class SerializeUtilTest {

    /*
     * @Test
     * public void testSerialize() throws ServiceException {
     * new MockUp<ObjectMapper>() {
     * @Mock
     * public String writeValueAsString(Object value) {
     * return "Test";
     * }
     * };
     * //Object.class.getAnnotationsByType(annotationClass) obj = new Object();
     * Object.class.getAnnotationsByType(JsonRootName.class);
     * String result = SerializeUtil.serialize(ContentType.JSON, "Test");
     * assertTrue(result == "Test");
     * }
     * @Test
     * public void testSerializeObjectNull() throws ServiceException {
     * Object obj = SerializeUtil.serialize(ContentType.JSON, null);
     * assertTrue(obj == null);
     * }
     * @Test
     * public void testSerializeToXML() throws ServiceException {
     * new MockUp<Marshaller>() {
     * @Mock
     * public void marshal( Object jaxbElement, java.io.Writer writer )
     * throws JAXBException {
     * //writer.append("a");
     * System.out.println("test");
     * return;
     * }
     * };
     * //Object.class.getAnnotationsByType(annotationClass) obj = new Object();
     * SerializeUtil.serialize(ContentType.XML, "Test");
     * }
     * @Test
     * public void testDeserializeFromXML() throws ServiceException {
     * new MockUp<JAXBContext>() {
     * @Mock
     * public JAXBContext newInstance(Class... classesToBeBound ) throws JAXBException{
     * return (JAXBContext)new Object();
     * }
     * };
     * SerializeUtil.deSerialize(ContentType.XML, "", JsonRootName.class);
     * }
     * @Test
     * public void testDeserializeFromJSON() throws ServiceException {
     * Class<JsonRootName> clazz = JsonRootName.class;
     * new MockUp<ObjectMapper>() {
     * @Mock
     * public <T> String readValue(String content, Class<T> valueType) throws IOException,
     * JsonParseException, JsonMappingException{
     * return "test";
     * }
     * };
     * SerializeUtil.deSerialize(ContentType.JSON, "test", JsonRootName.class);
     * SerializeUtil.deSerialize(ContentType.JSON, null, clazz.getClass());
     * //SerializeUtil.deSerialize(ContentType.JSON, "test", null);
     * }
     */
}
