package com.codio.feature_usage_mod.controller.features.constructs;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.stmt.DoStmt;

import java.util.List;

public class DoWhile {

  public DoWhile() {
  }

  public String process(CompilationUnit cu) {
    List<DoStmt> doStmts = cu.findAll(DoStmt.class);
    int count = doStmts.size();
    return generateMessage(count);
  }

  private String generateMessage(int count) {
    if (count == 0) {
      return "No Do While Loops in Student Code";
    } else if (count == 1) {
      return "1 Do While Loop in Student Code.\n";
    } else {
      return count + " Do While Loops in Student Code.\n";
    }
  }

}
