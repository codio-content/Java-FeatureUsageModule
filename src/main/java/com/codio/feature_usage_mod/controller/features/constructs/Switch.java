package com.codio.feature_usage_mod.controller.features.constructs;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.stmt.SwitchStmt;

import java.util.List;

/**
 * Java class to find Switch statements in Student Code.
 */

public class Switch {

  public Switch() {
  }

  /**
   * Method that finds all instances of Switch statements in Student Code and maintains their
   * count.
   *
   * @param cu AST object generated from Student Code file.
   * @return message to the controller, as String.
   */

  public String process(CompilationUnit cu) {
    List<SwitchStmt> switchStmts = cu.findAll(SwitchStmt.class);
    int count = switchStmts.size();
    return generateMessage(count);
  }

  /**
   * Private method that generates the message to be returned to the controller.
   *
   * @param count number of instances of Switch statements found in Student code.
   * @return message to be passed on to the controller, as String.
   */

  private String generateMessage(int count) {
    if (count == 0) {
      return "No Switch Statements in Student Code";
    } else if (count == 1) {
      return "1 Switch Statement in Student Code.\n";
    } else {
      return count + " Switch Statements in Student Code.\n";
    }
  }
}
