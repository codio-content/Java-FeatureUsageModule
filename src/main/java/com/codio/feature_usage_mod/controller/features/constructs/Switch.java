package com.codio.feature_usage_mod.controller.features.constructs;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.stmt.SwitchStmt;

import java.util.List;

public class Switch {

  public Switch() {
  }

  public String process(CompilationUnit cu) {
    List<SwitchStmt> switchStmts = cu.findAll(SwitchStmt.class);
    int count = switchStmts.size();
    return generateMessage(count);
  }

  private String generateMessage(int count) {
    if (count == 0) {
      return "No Switch Statements in Student Code";
    } else if (count == 1) {
      return "1 Switch Statement in Student Code.\n";
    } else {
      return count + " Switch Statements in Student Code.\n";
    }
  }
}
