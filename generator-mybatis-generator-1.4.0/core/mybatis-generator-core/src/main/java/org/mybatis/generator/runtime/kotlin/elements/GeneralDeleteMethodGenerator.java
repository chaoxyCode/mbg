/**
 * Copyright 2006-2019 the original author or authors.
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
package org.mybatis.generator.runtime.kotlin.elements;

import org.mybatis.generator.api.dom.kotlin.KotlinArg;
import org.mybatis.generator.api.dom.kotlin.KotlinFile;
import org.mybatis.generator.api.dom.kotlin.KotlinFunction;

public class GeneralDeleteMethodGenerator extends AbstractKotlinFunctionGenerator {

    private String mapperName;

    private GeneralDeleteMethodGenerator(Builder builder) {
        super(builder);
        mapperName = builder.mapperName;
    }

    @Override
    public KotlinFunctionAndImports generateMethodAndImports() {
        KotlinFunctionAndImports functionAndImports =
                KotlinFunctionAndImports.withFunction(
                                KotlinFunction.newOneLineFunction(
                                                mapperName + ".delete") // $NON-NLS-1$
                                        .withArgument(
                                                KotlinArg.newArg("completer") // $NON-NLS-1$
                                                        .withDataType(
                                                                "DeleteCompleter") //$NON-NLS-1$
                                                        .build())
                                        .withCodeLine(
                                                "deleteFrom(this::delete, "
                                                        + tableFieldName
                                                        + ", completer)") //$NON-NLS-1$
                                                                          // //$NON-NLS-2$
                                        .build())
                        .withImport("org.mybatis.dynamic.sql.util.kotlin.*") // $NON-NLS-1$
                        .withImport("org.mybatis.dynamic.sql.util.kotlin.mybatis3.*") // $NON-NLS-1$
                        .build();

        addFunctionComment(functionAndImports);
        return functionAndImports;
    }

    @Override
    public boolean callPlugins(KotlinFunction kotlinFunction, KotlinFile kotlinFile) {
        return context.getPlugins()
                .clientGeneralDeleteMethodGenerated(kotlinFunction, kotlinFile, introspectedTable);
    }

    public static class Builder extends BaseBuilder<Builder, GeneralDeleteMethodGenerator> {
        private String mapperName;

        public Builder withMapperName(String mapperName) {
            this.mapperName = mapperName;
            return this;
        }

        @Override
        public Builder getThis() {
            return this;
        }

        @Override
        public GeneralDeleteMethodGenerator build() {
            return new GeneralDeleteMethodGenerator(this);
        }
    }
}