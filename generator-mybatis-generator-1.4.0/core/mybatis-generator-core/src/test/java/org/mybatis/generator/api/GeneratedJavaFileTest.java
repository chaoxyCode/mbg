/**
 * Copyright 2006-2018 the original author or authors.
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
package org.mybatis.generator.api;

import org.junit.jupiter.api.Test;
import org.mybatis.generator.api.dom.DefaultJavaFormatter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;

import static org.assertj.core.api.Assertions.assertThat;

public class GeneratedJavaFileTest {

    @Test
    public void testReqularInterface() {
        FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType("org.mybatis.test.TestInterface");
        Interface ifc = new Interface(fqjt);
        JavaFormatter jf = new DefaultJavaFormatter();
        GeneratedJavaFile gjf = new GeneratedJavaFile(ifc, "src", jf);

        assertThat(gjf.getFileName()).isEqualTo("TestInterface.java");
        assertThat(gjf.getTargetPackage()).isEqualTo("org.mybatis.test");
    }

    @Test
    public void testGenericInterface() {
        FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType("org.mybatis.test.TestInterface");
        fqjt.addTypeArgument(new FullyQualifiedJavaType("T"));
        Interface ifc = new Interface(fqjt);
        JavaFormatter jf = new DefaultJavaFormatter();
        GeneratedJavaFile gjf = new GeneratedJavaFile(ifc, "src", jf);

        assertThat(gjf.getFileName()).isEqualTo("TestInterface.java");
        assertThat(gjf.getTargetPackage()).isEqualTo("org.mybatis.test");
    }
}
