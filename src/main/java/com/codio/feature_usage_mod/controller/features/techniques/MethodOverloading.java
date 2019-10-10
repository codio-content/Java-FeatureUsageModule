package com.codio.feature_usage_mod.controller.features.techniques;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;
import java.util.Stack;

public class MethodOverloading extends VoidVisitorAdapter<Stack<String>> {

  public MethodOverloading(){}

  @Override
  public void visit(MethodDeclaration md, Stack<String> methodCalls) {
    super.visit(md, methodCalls);
    methodCalls.push(md.getName().asString());
  }

  public String process(Stack<String> methodCalls) {
    if (methodCalls.size() == 0) {
      return "No methods found in Student Code";
    }
    else {
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
    }
    else {
      return overloadingDetector(methodCalls);
    }
  }
}
