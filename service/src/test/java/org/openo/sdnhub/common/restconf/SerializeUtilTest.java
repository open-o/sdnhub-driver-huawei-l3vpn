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

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openo.baseservice.remoteservice.exception.ServiceException;

import java.util.HashMap;
import java.util.Map;

public class SerializeUtilTest {

    @Test
    public void toJsonTestNormal() throws ServiceException {
        Map<String,String> body = new HashMap<>();
        body.put("user", "test");
        String data = SerializeUtil.toJson(body);
        assertEquals(data,"{\"user\":\"test\"}");
    }

    @Test
    public void toJsonTestNullBody() throws ServiceException {
        String data = SerializeUtil.toJson(null);
        assertEquals(data,null);
    }

    @Test
    public void fromJsonTestNull() throws ServiceException {
        String jsonStr = "{\"user\":\"test\"}";
        Map<String, String> objMap = SerializeUtil.fromJson(jsonStr, Map.class);
        assertEquals(objMap, null);
    }

    @Test(expected = Exception.class)
    public void fromJsonTestExceptionCases() throws ServiceException {
        SerializeUtil.fromJson("", Map.class);
    }

    @Test
    public void serializeTestAbnormal() throws ServiceException {
        Map<String,String> body = new HashMap<>();
        body.put("user", "test");
        String result = SerializeUtil.serialize(ContentType.JSON, body);
        assertEquals(result,"{\"user\":\"test\"}");
    }

    @Test
    public void deSerializeAbnormalTest() throws ServiceException {
        String jsonStr = "{\"user\":\"test\"}";
        Map<String , String> result = SerializeUtil.deSerialize(ContentType.JSON, jsonStr, Map.class);
        assertEquals(result, null);
    }

    @Test(expected = ServiceException.class)
    public void fromXmlTestXmlException() throws ServiceException {
        Map<String,String>  result = SerializeUtil.fromXml("<name>tester</name>", Map.class);
        System.out.println(result);
    }

    @Test(expected = ServiceException.class)
    public void fromXmlTestParseException() throws ServiceException {
        Map<String,String>  result = SerializeUtil.fromXml("\"tester\"", Map.class);
        System.out.println(result);
    }
}
