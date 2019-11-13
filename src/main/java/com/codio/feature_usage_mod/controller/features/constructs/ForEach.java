package com.codio.feature_usage_mod.controller.features.constructs;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.stmt.ForEachStmt;

import java.util.List;

public class ForEach {

  public ForEach() {
  }

  public String process(CompilationUnit cu) {
    List<ForEachStmt> forEachStmts = cu.findAll(ForEachStmt.class);
    int count = forEachStmts.size();
    return generateMessage(count);
  }

  private String generateMessage(int count) {
    if (count == 0) {
      return "No For Each Loops in Student Code.\n";
    } else if (count == 1) {
      return "1 For Each Loop in Student Code.\n";
    } else {
      return count + " For Each Loops in Student Code.\n";
    }
  }
}
