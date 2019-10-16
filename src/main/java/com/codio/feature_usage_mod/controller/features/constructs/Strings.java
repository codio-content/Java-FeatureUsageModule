package com.codio.feature_usage_mod.controller.features.constructs;

import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.visitor.GenericVisitorAdapter;

public class Strings extends GenericVisitorAdapter<String, Void> {

  public Strings() {
  }

  @Override
  public String visit(StringLiteralExpr sle, Void arg) {
    super.visit(sle, arg);
    if (sle.isStringLiteralExpr()) {
      return "There is a String literal in the code";
    } else {
      return "No String literals in code";
    }
  }
}
