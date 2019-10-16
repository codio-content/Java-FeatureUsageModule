package com.codio.feature_usage_mod.controller.features.constructs;

import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.visitor.GenericVisitorAdapter;

public class ForEach extends GenericVisitorAdapter<String, Void> {

  public ForEach() {
  }

  @Override
  public String visit(ForEachStmt fes, Void arg) {
    super.visit(fes, arg);
    return String.valueOf(fes.isForEachStmt());
  }

}
