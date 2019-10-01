package com.codio.feature_usage_mod.controller.features.constructs;

import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.visitor.GenericVisitorAdapter;

public class For extends GenericVisitorAdapter<String, Void> {

  public For(){}

  @Override
  public String visit(ForStmt fs, Void arg) {
    super.visit(fs, arg);
    return String.valueOf(fs.isForStmt());
  }

}
