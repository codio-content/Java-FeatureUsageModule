package com.codio.feature_usage_mod.controller.features.constructs;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.stmt.WhileStmt;

import java.util.List;

/**
 * Java class to find While Loops in Student Code.
 */

public class While implements IConstructs {

  public While() {
  }

  /**
   * Method that finds all instances of While Loops in Student Code and maintains their count.
   *
   * @param cu AST object generated from the Student Code file.
   * @return message to the controller, as String.
   */

  public String process(CompilationUnit cu) {
    List<WhileStmt> whileStmts = cu.findAll(WhileStmt.class);
    int count = whileStmts.size();
    return generateMessage(count);
  }

  /**
   * Private method that generates the message to be returned to the controller.
   *
   * @param count number of instances of While Loops found in Student Code.
   * @return message to be passed on to the controller, as String.
   */

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
