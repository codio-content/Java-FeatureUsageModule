package com.codio.feature_usage_mod.controller.features.constructs;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ConstructorDeclaration;

import java.util.List;

public class Constructors {

  public Constructors() {
  }

  public String process(CompilationUnit cu) {
    List<ConstructorDeclaration> constructors = cu.findAll(ConstructorDeclaration.class);
    int count = constructors.size();
    return generateMessage(count, constructors);
  }

  private String generateMessage(int count, List<ConstructorDeclaration> constructors) {
    if (count == 0) {
      return "No constructors in Student Code";
    }
    else if (count == 1) {
      return  "1 constructor in Student Code.\nConstructor name: "
              + getConstructorNames(constructors);
    }
    else {
      return count + " constructors in Student Code.\nConstructor names:\n"
              + getConstructorNames(constructors);
    }
  }

  private String getConstructorNames(List<ConstructorDeclaration> constructors) {
    StringBuilder sb = new StringBuilder();
    for (ConstructorDeclaration constructorDec: constructors) {
      sb.append(constructorDec.getDeclarationAsString().trim()).append("\n");
    }
    return sb.toString();
  }
}
