package com.codio.feature_usage_mod.controller.features.techniques;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.util.List;
import java.util.Stack;

public class MethodOverloading {

  public MethodOverloading() {
  }

  public String process(CompilationUnit cu) {
    List<MethodDeclaration> methodDecs = cu.findAll(MethodDeclaration.class);
    Stack<String> methodCalls = new Stack<>();
    for (MethodDeclaration methodDec: methodDecs) {
      methodCalls.add(methodDec.getName().asString());
    }

    if (methodCalls.size() == 0) {
      return "No methods found in Student Code";
    } else {
      return overloadingDetector(methodCalls);
    }
  }

  private String overloadingDetector(Stack<String> methodCalls) {
    String method = methodCalls.pop();
    if (methodCalls.empty()) {
      return "Method Overloading NOT FOUND";
    }
    if (methodCalls.contains(method)) {
      return "Method Overloading present in Student Code";
    } else {
      return overloadingDetector(methodCalls);
    }
  }
}
