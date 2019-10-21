package com.codio.feature_usage_mod.controller.features.constructs;

import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.visitor.GenericVisitorAdapter;

public class Objects extends GenericVisitorAdapter<String, Void> {

  public Objects() {
  }

  @Override
  public String visit(ObjectCreationExpr oce, Void arg) {
    super.visit(oce, arg);
    if (oce.isObjectCreationExpr()) {
      return "Object creation expression found";
    } else {
      return "Object creation expression not found";
    }
  }

  //Pass by value, pass by reference

}
