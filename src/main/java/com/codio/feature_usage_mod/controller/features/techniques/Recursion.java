package com.codio.feature_usage_mod.controller.features.techniques;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.util.List;

public class Recursion {

  public Recursion(){}

  public String process(CompilationUnit cu) {

    List<MethodDeclaration> methodCalls = cu.findAll(MethodDeclaration.class);
    if (methodCalls.size() == 0) {
      return "No methods used in Student Code";
    }

    for (MethodDeclaration methodCall: methodCalls) {
      String methodBody = methodCall.getBody().toString();
      if (methodBody.contains(methodCall.getName().asString())) {
        return "Recursion Used in Code";
      }
    }
    return "Recursion NOT FOUND";
  }
}
