package com.codio.feature_usage_mod.controller.features.constructs;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.stmt.ForEachStmt;

import java.util.List;

/**
 * Java class to find For-Each loops in Student Code.
 */

public class ForEach implements IConstructs{

  public ForEach() {
  }

  /**
   * Method that finds all instances of For-Each Loops in Student Code and maintains their count.
   *
   * @param cu AST object generated from the Student Code file.
   * @return returns a message to the controller about instances of For-Each Loops, as String.
   */

  public String process(CompilationUnit cu) {
    List<ForEachStmt> forEachStmts = cu.findAll(ForEachStmt.class);
    int count = forEachStmts.size();
    return generateMessage(count);
  }

  /**
   * Private method that generates the message to be returned to the controller.
   *
   * @param count number of instances of For-Each Loops found in Student Code.
   * @return returns the message to be passed on to the controller, as String.
   */

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
