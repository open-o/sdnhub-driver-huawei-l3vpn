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

package org.openo.sdno.acwanservice.config;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Configuration class<br>
 * 
 * @author
 * @version SDNO 0.5 Aug 22, 2016
 */
public class Configuration {

    private static final Logger LOGGER = LoggerFactory.getLogger(Configuration.class);

    private static final String CF_KEY = "cfgkey";

    private static final String CF_VALUE = "cfgvalue";

    private static final String DOMAIN = "acWanConf";

    private Configuration() {

    }

    /**
     * get label values.<br>
     * 
     * @param label
     * @return
     * @since SDNO 0.5
     */
    public static String getValues(String label) {
        List<Map<String, String>> values = null;
        try {
            values = getJsonFileData(DOMAIN);
        } catch(ServiceException e) {
            // TODO Auto-generated catch block
            LOGGER.warn("ServiceException generated" + e);
        }
        return getValue(values, label);

    }

    private static String getValue(List<Map<String, String>> values, String key) {
        String returnResult = null;
        for(Map<String, String> value : values) {
            String keyValue = value.get(CF_KEY);
            if((keyValue != null) && keyValue.equals(key)) {
                returnResult = value.get(CF_VALUE);
                break;
            }
        }

        return returnResult;
    }

    private static List<Map<String, String>> getJsonFileData(String domain) throws ServiceException {
        try {
            String content = IOUtils.toString(
                    Configuration.class.getClassLoader().getResourceAsStream("/generalconfig/acWanConf.json"));
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(content.getBytes(), List.class);
        } catch(IOException e) {
            LOGGER.warn("Get json file failed!", e);
        }
        return Collections.EMPTY_LIST;
    }
}
