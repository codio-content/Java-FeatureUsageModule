package com.codio.feature_usage_mod.controller.features.constructs;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.StringLiteralExpr;

import java.util.List;

/**
 * Java class to find String objects in Student Code.
 */

public class Strings {

  public Strings() {
  }

  /**
   * Method that finds all instances of String objects in Student Code and maintains their count.
   *
   * @param cu AST object generated from Student Code file.
   * @return message to the controller, as String.
   */

  public String process(CompilationUnit cu) {
    List<StringLiteralExpr> strings = cu.findAll(StringLiteralExpr.class);
    int count = strings.size();
    return generateMessage(count);
  }

  /**
   * Private method that generates the message to be returned to the controller.
   *
   * @param count number of instances of String objects found in Student Code.
   * @return message to be passed on to the controller, as String.
   */

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
