package com.codio.feature_usage_mod.controller.features.constructs;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.ObjectCreationExpr;

import java.util.List;

/**
 * Java class to find instances of 'Objects' in Student Code.
 */

public class Objects {

  public Objects() {
  }

  /**
   * Method that finds all instances of 'Objects' in Student Code and maintains their count.
   *
   * @param cu AST object generated from Student Code.
   * @return message to the Controller, as String.
   */

  public String process(CompilationUnit cu) {
    List<ObjectCreationExpr> objects = cu.findAll(ObjectCreationExpr.class);
    int count = objects.size();
    return generateMessage(count, objects);
  }

  /**
   * Private method that generates the message to be returned to the controller.
   *
   * @param count number of instances of 'Objects' found in Student Code.
   * @param objects List of Object Creation Expressions
   * @return message to be passed on to the controller, as String.
   */

  private String generateMessage(int count, List<ObjectCreationExpr> objects) {
    if (count == 0) {
      return "No Objects in Student Code";
    } else if (count == 1) {
      return "1 Object in Student Code.\nObject type: " + getObjectNames(objects);
    } else {
      return count + " Objects in Student Code.\nObject types:\n" + getObjectNames(objects);
    }
  }

  /**
   * Private method to get the names of the 'Objects' found, as String.
   *
   * @param objects List of Object Creation Expressions.
   * @return names of 'Objects' found, as String.
   */

  private String getObjectNames(List<ObjectCreationExpr> objects) {
    StringBuilder sb = new StringBuilder();
    for (ObjectCreationExpr object: objects) {
      sb.append(object.getTypeAsString()).append("\n");
    }
    return sb.toString();
  }
  //Pass by value, pass by reference

}
