package com.codio.feature_usage_mod.controller.features.constructs;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ConstructorDeclaration;

import java.util.List;

/**
 * Java class to find Constructors in Student Code.
 */

public class Constructors {

  public Constructors() {
  }

  /**
   * Method that finds all instances of Constructors in Student Code and maintains their count.
   *
   * @param cu AST object generated from the Student Code file.
   * @return returns a message to the controller about instances of constructors, as String.
   */

  public String process(CompilationUnit cu) {
    List<ConstructorDeclaration> constructors = cu.findAll(ConstructorDeclaration.class);
    int count = constructors.size();
    return generateMessage(count, constructors);
  }

  /**
   * Private method that generates the message to be returned to the controller.
   *
   * @param count number of instances of Constructors found in Student Code.
   * @param constructors List of Constructor declarations.
   * @return returns the message to be passed on to the controller, as String.
   */

  private String generateMessage(int count, List<ConstructorDeclaration> constructors) {
    if (count == 0) {
      return "No constructors in Student Code";
    } else if (count == 1) {
      return "1 constructor in Student Code.\nConstructor name: "
              + getConstructorNames(constructors);
    } else {
      return count + " constructors in Student Code.\nConstructor names:\n"
              + getConstructorNames(constructors);
    }
  }

  /**
   * Private method to get the names of the Constructors found as String.
   *
   * @param constructors List of Constructor declarations.
   * @return returns the names of the Constructors found, as String.
   */

  private String getConstructorNames(List<ConstructorDeclaration> constructors) {
    StringBuilder sb = new StringBuilder();
    for (ConstructorDeclaration constructorDec : constructors) {
      sb.append(constructorDec.getDeclarationAsString().trim()).append("\n");
    }
    return sb.toString();
  }
}
