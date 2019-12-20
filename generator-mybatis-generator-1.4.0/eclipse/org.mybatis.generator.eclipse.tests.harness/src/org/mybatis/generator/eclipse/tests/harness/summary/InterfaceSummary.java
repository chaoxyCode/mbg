/**
 * Copyright 2006-2016 the original author or authors.
 *
 * <p>Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * <p>http://www.apache.org/licenses/LICENSE-2.0
 *
 * <p>Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.mybatis.generator.eclipse.tests.harness.summary;

import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.mybatis.generator.eclipse.tests.harness.summary.support.InterfaceSummarizer;

public class InterfaceSummary extends AbstractTypeOrEnumSummary {

    private InterfaceSummary() {
        super();
    }

    public static InterfaceSummary from(TypeDeclaration node) {
        InterfaceSummarizer summarizer = InterfaceSummarizer.from(node);

        return new Builder()
                .withName(summarizer.getName())
                .withSuperInterfaces(summarizer.getSuperInterfaces())
                .withFields(summarizer.getFieldSummaries())
                .withMethods(summarizer.getMethodSignatures())
                .withAnnotationSummaries(summarizer.getAnnotationSummaries())
                .withClassSummaries(summarizer.getClassSummaries())
                .withEnumSummaries(summarizer.getEnumSummaries())
                .withInterfaceSummaries(summarizer.getInterfaceSummaries())
                .summary();
    }

    private static class Builder extends AbstractTypeOrEnumSummaryBuilder<Builder> {
        private InterfaceSummary summary = new InterfaceSummary();

        @Override
        protected InterfaceSummary summary() {
            return summary;
        }

        @Override
        protected Builder getThis() {
            return this;
        }
    }
}
