package com.codio.feature_usage_mod.controller.features.constructs;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.GenericVisitorAdapter;

public class Classes extends GenericVisitorAdapter<String, Void> {

  public Classes() {
  }

  @Override
  public String visit(ClassOrInterfaceDeclaration cd, Void arg) {
    super.visit(cd, arg);
    return "Class Name used: " + cd.getName();
  }

}
