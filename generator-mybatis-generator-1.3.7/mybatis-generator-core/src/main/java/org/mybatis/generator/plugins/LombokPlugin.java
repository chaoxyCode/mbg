/**
 *    Copyright 2006-2019 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.generator.plugins;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/** LombokPlugin 代办 */
public class LombokPlugin extends PluginAdapter {

  @Override
  public boolean validate(List<String> warnings) {
    return true;
  }

  @Override
  public boolean modelBaseRecordClassGenerated(
      TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    this.addLombokAnnotation(topLevelClass, introspectedTable);
    return true;
  }

  @Override
  public boolean modelExampleClassGenerated(
      TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    this.addLombokAnnotation(topLevelClass, introspectedTable);
    return true;
  }

  @Override
  public boolean modelPrimaryKeyClassGenerated(
      TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    this.addLombokAnnotation(topLevelClass, introspectedTable);
    return true;
  }

  @Override
  public boolean modelRecordWithBLOBsClassGenerated(
      TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    this.addLombokAnnotation(topLevelClass, introspectedTable);
    return true;
  }

  /*
  @Override
  public boolean clientGenerated(
      Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    interfaze.addJavaDocLine("/**");
    interfaze.addJavaDocLine("* Created by Mybatis Generator " + date2Str(new Date()));
    interfaze.addJavaDocLine("*\\/");
    return true;
  }
   */

  @Override
  public boolean modelSetterMethodGenerated(
      Method method,
      TopLevelClass topLevelClass,
      IntrospectedColumn introspectedColumn,
      IntrospectedTable introspectedTable,
      ModelClassType modelClassType) {
    return false;
  }

  @Override
  public boolean modelGetterMethodGenerated(
      Method method,
      TopLevelClass topLevelClass,
      IntrospectedColumn introspectedColumn,
      IntrospectedTable introspectedTable,
      ModelClassType modelClassType) {
    return false;
  }

  private String date2Str(Date date) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    return sdf.format(date);
  }

  /**
   * 添加 lombok 注解
   *
   * @param topLevelClass
   * @param introspectedTable
   */
  private void addLombokAnnotation(
      TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    topLevelClass.addImportedType("lombok.Getter");
    topLevelClass.addImportedType("lombok.Setter");
    topLevelClass.addImportedType("lombok.ToString");

    topLevelClass.addAnnotation("@Getter");
    topLevelClass.addAnnotation("@Setter");
    topLevelClass.addAnnotation("@ToString");
  }
}
