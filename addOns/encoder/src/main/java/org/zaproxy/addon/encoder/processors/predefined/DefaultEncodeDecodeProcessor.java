/*
 * Zed Attack Proxy (ZAP) and its related class files.
 *
 * ZAP is an HTTP/HTTPS proxy for assessing web application security.
 *
 * Copyright 2018 The ZAP Development Team
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
package org.zaproxy.addon.encoder.processors.predefined;

import org.parosproxy.paros.extension.encoder.Encoder;
import org.zaproxy.addon.encoder.processors.EncodeDecodeProcessor;
import org.zaproxy.addon.encoder.processors.EncodeDecodeResult;

public abstract class DefaultEncodeDecodeProcessor implements EncodeDecodeProcessor {

    private Encoder encoder;

    protected Encoder getEncoder() {
        if (encoder == null) {
            encoder = new Encoder();
        }
        return encoder;
    }

    @Override
    public EncodeDecodeResult process(String value) {
        try {
            String result = processInternal(value);
            return new EncodeDecodeResult(result);
        } catch (Exception e) {
            return EncodeDecodeResult.withError(
                    e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }

    protected abstract String processInternal(String value) throws Exception;
}