package com.codio.feature_usage_mod.controller.features.constructs;

import com.codio.feature_usage_mod.controller.features.IConstructs;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;

import java.util.ArrayList;
import java.util.List;

public class Variables implements IConstructs {

  public Variables() {
  }

  // TODO: User input of specific type of variable in the right context

  // Scope, Local/Global, Details
  // Field Declarator for Class variables
  // Variable Declaration Expression for All Local Variables

  public String process(CompilationUnit cu) {

    StringBuilder sb = new StringBuilder();
    List<MethodDeclaration> methods = cu.findAll(MethodDeclaration.class);

    int count_localVariables = 0;
    int count_globalVariables = 0;
    int count_staticClassVariables = 0;

    if (methods.size() == 0) {
      sb.append("No Local Variables in the code");
    } else {
      for (MethodDeclaration method : methods) {

        List<VariableDeclarationExpr> locVarExpr = method.findAll(VariableDeclarationExpr.class);

      }
    }

    return "";
  }
}
