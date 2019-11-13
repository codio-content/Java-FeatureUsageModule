package com.codio.feature_usage_mod.controller.features.constructs;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.stmt.WhileStmt;

import java.util.List;

public class While {

  public While() {
  }

  public String process(CompilationUnit cu) {
    List<WhileStmt> whileStmts = cu.findAll(WhileStmt.class);
    int count = whileStmts.size();
    return generateMessage(count);
  }

  private String generateMessage(int count) {
    if (count == 0) {
      return "No While Loops in Student Code";
    } else if (count == 1) {
      return "1 While Loop in Student Code.\n";
    } else {
      return count + " While Loops in Student Code.\n";
    }
  }
}
