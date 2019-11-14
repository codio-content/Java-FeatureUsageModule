package com.codio.feature_usage_mod.controller.features.constructs;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.util.List;

public class Methods {

  public Methods() {
  }

  public String process(CompilationUnit cu) {
    List<MethodDeclaration> methods = cu.findAll(MethodDeclaration.class);
    int count = methods.size();
    return generateMessage(count, methods);
  }

  private String generateMessage(int count, List<MethodDeclaration> methods) {
    if (count == 0) {
      return "No Methods in Student Code";
    } else if (count == 1) {
      return "1 Method in Student Code.\nMethod name: " + getMethodNames(methods);
    } else {
      return count + " Methods in Student Code.\nMethod names:\n" + getMethodNames(methods);
    }

  }

  private String getMethodNames(List<MethodDeclaration> methods) {

    StringBuilder sb = new StringBuilder();
    for (MethodDeclaration method: methods) {
      sb.append(method.getDeclarationAsString().trim()).append("\n");
    }
    return sb.toString();
  }
}
