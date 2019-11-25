package com.codio.feature_usage_mod.controller.features.constructs;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import java.util.List;

/**
 * Java class to find Classes/Interfaces in Student Code.
 */

public class Classes {

  public Classes() {
  }

  /**
   * Method to find all instances of Classes/Interfaces in Student Code and maintains their count.
   *
   * @param cu AST object generated from the Student Code file.
   * @return returns a message to the controller about instances of classes/interfaces, as String.
   */

  public String process(CompilationUnit cu) {
    List<ClassOrInterfaceDeclaration> classes = cu.findAll(ClassOrInterfaceDeclaration.class);
    int count = classes.size();
    return generateMessage(count, classes);
  }

  /**
   * Private method that generates the message to be returned to the controller.
   *
   * @param count number of instances of Classes/Interfaces found in Student Code.
   * @param classes List of Class/Interface declarations.
   * @return returns the message to be passed on to the controller, as String.
   */

  private String generateMessage(int count, List<ClassOrInterfaceDeclaration> classes) {
    if (count == 0) {
      return "No classes in Student Code";
    } else if (count == 1) {
      return "1 class in Student Code.\nClass name: " + getClassNames(classes);
    } else {
      return count + " classes in Student Code.\nClass names:\n" + getClassNames(classes);
    }
  }

  /**
   * Private method to get the names of the Classes/Interfaces found as String.
   *
   * @param classes List of Class/Interface declarations.
   * @return returns the names of the Classes/Interfaces found, as String.
   */

  private String getClassNames(List<ClassOrInterfaceDeclaration> classes) {
    StringBuilder sb = new StringBuilder();
    for (ClassOrInterfaceDeclaration classDec : classes) {
      sb.append(classDec.getNameAsString()).append("\n");
    }
    return sb.toString();
  }

}