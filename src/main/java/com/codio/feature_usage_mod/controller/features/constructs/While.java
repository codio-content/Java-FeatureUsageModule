package com.codio.feature_usage_mod.controller.features.constructs;

import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.visitor.GenericVisitorAdapter;

public class While extends GenericVisitorAdapter<String, Void> {

  public While() {
  }

  @Override
  public String visit(WhileStmt ws, Void arg) {
    super.visit(ws, arg);
    return String.valueOf(ws.isWhileStmt());
  }
}
