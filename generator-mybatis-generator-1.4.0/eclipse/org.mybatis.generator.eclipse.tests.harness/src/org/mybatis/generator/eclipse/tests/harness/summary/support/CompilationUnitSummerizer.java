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
package org.mybatis.generator.eclipse.tests.harness.summary.support;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ImportDeclaration;

import java.util.List;

public class CompilationUnitSummerizer extends AbstractSummarizer {

    private ImportGatheringVisitor visitor = new ImportGatheringVisitor();

    @SuppressWarnings("unchecked")
    private CompilationUnitSummerizer(CompilationUnit node) {
        super(node);
        visitImports(node.imports());
    }

    public static CompilationUnitSummerizer from(CompilationUnit node) {
        return new CompilationUnitSummerizer(node);
    }

    public List<String> getImports() {
        return visitor.getImports();
    }

    private void visitImports(List<ImportDeclaration> imports) {
        for (ImportDeclaration importDeclaration : imports) {
            importDeclaration.accept(visitor);
        }
    }
}
