package com.codio.feature_usage_mod.controller.features.constructs;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.StringLiteralExpr;

import java.util.List;

public class Strings {

  public Strings() {
  }

  public String process(CompilationUnit cu) {
    List<StringLiteralExpr> strings = cu.findAll(StringLiteralExpr.class);
    int count = strings.size();
    return generateMessage(count);
  }

  private String generateMessage(int count) {
    if (count == 0) {
      return "No String literals in Student Code";
    } else if (count == 1) {
      return "1 String literal in Student Code.\n";
    } else {
      return count + " String literals in Student Code.\n";
    }
  }

}
