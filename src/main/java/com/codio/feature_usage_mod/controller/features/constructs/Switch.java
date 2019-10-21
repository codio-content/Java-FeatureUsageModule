package com.codio.feature_usage_mod.controller.features.constructs;

import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.visitor.GenericVisitorAdapter;

public class Switch extends GenericVisitorAdapter<String, Void> {

  public Switch() {
  }

  @Override
  public String visit(SwitchStmt ss, Void arg) {
    super.visit(ss, arg);
    return String.valueOf(ss.isSwitchStmt());
  }

}
