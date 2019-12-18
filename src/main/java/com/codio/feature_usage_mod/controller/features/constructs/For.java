package com.codio.feature_usage_mod.controller.features.constructs;

import com.codio.feature_usage_mod.controller.features.IConstructs;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.stmt.ForStmt;

import java.util.List;

/**
 * Java class to find For loops in Student Code.
 */

public class For implements IConstructs {

  public For() {
  }

  /**
   * Method that finds all instances of For Loops in Student Code and maintains their count.
   *
   * @param cu AST object generated from the Student Code file.
   * @return returns a message to the controller about instances of For Loops, as String.
   */

  public String process(CompilationUnit cu) {
    List<ForStmt> forStmts = cu.findAll(ForStmt.class);
    int count = forStmts.size();
    return generateMessage(count);
  }

  /**
   * Private method that generates the message to be returned to the controller.
   *
   * @param count number of instances of For Loops found in Student Code.
   * @return returns the message to be passed on to the controller, as String.
   */

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
