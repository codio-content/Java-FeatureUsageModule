package com.codio.feature_usage_mod.controller.features.constructs;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.stmt.ForStmt;

import java.util.List;

public class For {

  public For() {
  }

  public String process(CompilationUnit cu) {
    List<ForStmt> forStmts = cu.findAll(ForStmt.class);
    int count = forStmts.size();
    return generateMessage(count);
  }

  private String generateMessage(int count) {
    if (count == 0) {
      return "No For Loops in Student Code";
    } else if (count == 1) {
      return "1 For Loop in Student Code.\n";
    } else {
      return count + " For Loops in Student Code.\n";
    }
  }

}
