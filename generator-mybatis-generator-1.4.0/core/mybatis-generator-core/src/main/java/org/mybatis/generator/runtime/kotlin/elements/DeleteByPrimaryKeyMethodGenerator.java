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

import org.mybatis.generator.api.dom.kotlin.KotlinFile;
import org.mybatis.generator.api.dom.kotlin.KotlinFunction;
import org.mybatis.generator.runtime.dynamic.sql.elements.v2.Utils;

import java.util.Objects;

public class DeleteByPrimaryKeyMethodGenerator extends AbstractKotlinFunctionGenerator {

    private KotlinFragmentGenerator fragmentGenerator;
    private String mapperName;

    private DeleteByPrimaryKeyMethodGenerator(Builder builder) {
        super(builder);
        fragmentGenerator = builder.fragmentGenerator;
        mapperName = Objects.requireNonNull(builder.mapperName);
    }

    @Override
    public KotlinFunctionAndImports generateMethodAndImports() {
        if (!Utils.generateDeleteByPrimaryKey(introspectedTable)) {
            return null;
        }

        KotlinFunctionAndImports functionAndImports =
                KotlinFunctionAndImports.withFunction(
                                KotlinFunction.newOneLineFunction(
                                                mapperName + ".deleteByPrimaryKey") // $NON-NLS-1$
                                        .withCodeLine("delete {") // $NON-NLS-1$
                                        .build())
                        .withImport("org.mybatis.dynamic.sql.SqlBuilder.isEqualTo") // $NON-NLS-1$
                        .build();

        addFunctionComment(functionAndImports);

        KotlinFunctionParts functionParts =
                fragmentGenerator.getPrimaryKeyWhereClauseAndParameters();
        acceptParts(functionAndImports, functionParts);

        return functionAndImports;
    }

    @Override
    public boolean callPlugins(KotlinFunction kotlinFunction, KotlinFile kotlinFile) {
        return context.getPlugins()
                .clientDeleteByPrimaryKeyMethodGenerated(
                        kotlinFunction, kotlinFile, introspectedTable);
    }

    public static class Builder extends BaseBuilder<Builder, DeleteByPrimaryKeyMethodGenerator> {

        private KotlinFragmentGenerator fragmentGenerator;
        private String mapperName;

        public Builder withFragmentGenerator(KotlinFragmentGenerator fragmentGenerator) {
            this.fragmentGenerator = fragmentGenerator;
            return this;
        }

        public Builder withMapperName(String mapperName) {
            this.mapperName = mapperName;
            return this;
        }

        @Override
        public Builder getThis() {
            return this;
        }

        @Override
        public DeleteByPrimaryKeyMethodGenerator build() {
            return new DeleteByPrimaryKeyMethodGenerator(this);
        }
    }
}
