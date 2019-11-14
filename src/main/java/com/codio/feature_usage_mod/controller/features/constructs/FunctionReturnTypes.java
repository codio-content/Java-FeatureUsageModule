package com.codio.feature_usage_mod.controller.features.constructs;

import com.github.javaparser.ast.CompilationUnit;

import java.util.ArrayList;
import java.util.List;

public class FunctionReturnTypes {


  // TODO: Uses Method().visit method. Abstraction required for less cohesion

  public FunctionReturnTypes() {
  }

  public String processGeneralCase(CompilationUnit cu) {

    List<String> methods = process(cu);
    if (methods.size() == 0) {
      return "There are no methods in code";
    }
    int count = 0;
    for (String method : methods) {
      if (method.contains("void") && !method.contains("main")) {
        count++;
      }
    }
    return count + " methods have their return type as void excluding the main method";
  }

  public String processSpecificCase(CompilationUnit cu, String returnType, String functionName) {
    List<String> methods = process(cu);
    if (methods.size() == 0) {
      return "There are no methods in code";
    }

    for (String method : methods) {
      if (method.contains(returnType) && method.contains(functionName)) {
        return "Function with specified return type present";
      }
    }
    return "Function with specified return type NOT FOUND";
  }

  private List<String> process(CompilationUnit cu) {
    List<String> methods = new ArrayList<>();
    new Methods().visit(cu, methods);
    return methods;
  }
}
