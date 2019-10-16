package com.codio.feature_usage_mod.controller.features.constructs;

import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.visitor.GenericVisitorAdapter;

public class DoWhile extends GenericVisitorAdapter<String, Void> {

  public DoWhile() {
  }

  @Override
  public String visit(DoStmt ds, Void arg) {
    super.visit(ds, arg);
    return String.valueOf(ds.isDoStmt());
  }

}
