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

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpsURLConnectionImpl extends HttpURLConnection {

    public HttpsURLConnectionImpl(URL u) {
        super(u);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void disconnect() {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean usingProxy() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void connect() throws IOException {
        // TODO Auto-generated method stub
    }

}
