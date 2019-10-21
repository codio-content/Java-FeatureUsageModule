package com.codio.feature_usage_mod.controller.features.constructs;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.List;

public class FunctionReturnTypes {


  // TODO: User input for specific function and return type

  public FunctionReturnTypes(){}

  public String process(CompilationUnit cu) {
    List<String> methods = new ArrayList<>();

    new Methods().visit(cu, methods);
//    System.out.println(methods.toString());
    if (methods.size() == 0) {
      return "There are no methods in code";
    }
    int count = 0;
    for (String method: methods) {
      if (method.contains("void") && !method.contains("main")) {
        count++;
      }
    }
    return count + " methods have their return type as void excluding the main method";
  }
}
