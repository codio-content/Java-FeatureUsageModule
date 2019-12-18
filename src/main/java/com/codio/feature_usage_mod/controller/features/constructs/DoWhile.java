package com.codio.feature_usage_mod.controller.features.constructs;

import com.codio.feature_usage_mod.controller.features.IConstructs;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.stmt.DoStmt;

import java.util.List;

/**
 * Java class to find Do While Loops in Student Code.
 */

public class DoWhile implements IConstructs {

  public DoWhile() {
  }

  /**
   * Method that finds all instances of Do While Loops in Student Code and maintains their count.
   *
   * @param cu AST object generated from the Student Code file.
   * @return returns a message to the controller about instances of Do While loops, as String.
   */

  public String process(CompilationUnit cu) {
    List<DoStmt> doStmts = cu.findAll(DoStmt.class);
    int count = doStmts.size();
    return generateMessage(count);
  }

  /**
   * Private method that generates the message to be returned to the controller.
   *
   * @param count number of instances of Do While Loops found in Student Code.
   * @return returns the message to be passed on to the controller, as String.
   */

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
