package com.codio.feature_usage_mod.controller.features.constructs;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import java.util.List;

public class Classes {

  public Classes() {
  }

  public String process(CompilationUnit cu) {
    List<ClassOrInterfaceDeclaration> classes = cu.findAll(ClassOrInterfaceDeclaration.class);
    int count = classes.size();
    return generateMessage(count, classes);
  }

  private String generateMessage(int count, List<ClassOrInterfaceDeclaration> classes) {
    if (count == 0) {
      return "No classes in Student Code";
    }
    else if (count == 1) {
      return  "1 class in Student Code.\nClass name: " + getClassNames(classes);
    }
    else {
      return count + " classes in Student Code.\nClass names:\n" + getClassNames(classes);
    }
  }

  private String getClassNames(List<ClassOrInterfaceDeclaration> classes) {
    StringBuilder sb = new StringBuilder();
    for (ClassOrInterfaceDeclaration classDec: classes) {
      sb.append(classDec.getNameAsString()).append("\n");
    }
    return sb.toString();
  }

}