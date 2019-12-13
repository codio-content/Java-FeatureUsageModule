package com.codio.feature_usage_mod.controller.features.constructs;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.util.List;

/**
 * Java Class to find methods in Student Code.
 */

public class Methods implements IConstructs{

  public Methods() {
  }

  /**
   * Method that finds all instances of 'Methods' in Student Code and maintains their count.
   *
   * @param cu AST object generated from Student Code file.
   * @return message to the controller, as String.
   */

  public String process(CompilationUnit cu) {
    List<MethodDeclaration> methods = cu.findAll(MethodDeclaration.class);
    int count = methods.size();
    return generateMessage(count, methods);
  }

  /**
   * Private message that generates the message to be returned to the controller.
   *
   * @param count   number of instances of Methods found in Student Code.
   * @param methods List of Method declarations
   * @return message to be passed on to the controller, as String.
   */

  private String generateMessage(int count, List<MethodDeclaration> methods) {
    if (count == 0) {
      return "No Methods in Student Code";
    } else if (count == 1) {
      return "1 Method in Student Code.\nMethod name: " + getMethodNames(methods);
    } else {
      return count + " Methods in Student Code.\nMethod names:\n" + getMethodNames(methods);
    }

  }

  /**
   * Private method to get the names of the 'methods' found, as String.
   *
   * @param methods List of Method declarations.
   * @return names of the Methods found, as String.
   */

  private String getMethodNames(List<MethodDeclaration> methods) {

    StringBuilder sb = new StringBuilder();
    for (MethodDeclaration method : methods) {
      sb.append(method.getDeclarationAsString().trim()).append("\n");
    }
    return sb.toString();
  }
}
