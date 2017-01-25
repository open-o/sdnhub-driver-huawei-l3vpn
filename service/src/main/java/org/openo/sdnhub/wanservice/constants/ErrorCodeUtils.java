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

package org.openo.sdnhub.wanservice.constants;

import org.apache.commons.lang3.StringUtils;

/**
 * Error code utility class.<br>
 *
 * @author
 * @version SDNO 0.5 2016-6-1
 */
public class ErrorCodeUtils {

    private static final String POINT = ".";

    private ErrorCodeUtils() {
    }

    /**
     * Get error code.<br>
     *
     * @param appName APP name
     * @param source source
     * @param error error
     * @return error code
     * @since SDNHUB 0.5
     */
    public static final String getErrorCode(final String appName, final String source, final String error) {
        return StringUtils.join(appName, POINT, source, POINT, error);
    }
}
