/*  Copyright 2011 ThoughtWorks Ltd
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.thoughtworks.inproctester.htmlunit;

import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponseData;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import com.thoughtworks.inproctester.core.InProcRequest;
import com.thoughtworks.inproctester.core.InProcResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HttpTesterAdaptor {
    static WebResponseData adaptResponse(InProcResponse inProcResponse) throws IOException {
        final List<NameValuePair> headers = new ArrayList<>();
        Collection<String> headerNames = inProcResponse.getHeaderNames();
        for (String headerName : headerNames) {
            String headerValue = inProcResponse.getHeader(headerName);
            headers.add(new NameValuePair(headerName, headerValue));
        }
        return new WebResponseData(inProcResponse.getContentBytes(), inProcResponse.getStatus(), inProcResponse.getReason(), headers);
    }

    static InProcRequest adaptRequest(WebRequest request) {
        return new HtmlUnitInProcRequest(request);
    }

}
