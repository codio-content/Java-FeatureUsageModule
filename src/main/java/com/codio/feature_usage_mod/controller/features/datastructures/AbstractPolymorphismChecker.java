package com.codio.feature_usage_mod.controller.features.datastructures;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;

import java.util.List;

abstract class AbstractPolymorphismChecker {

  public String processVar(CompilationUnit cu, String dataStructure) {
    List<VariableDeclarationExpr> varDecExprs = cu.findAll(VariableDeclarationExpr.class);
    if (varDecExprs.size() == 0) {
      return "No " + dataStructure +" (variables) in code";
    }

    for (VariableDeclarationExpr obj: varDecExprs) {
      if (obj.toString().contains(dataStructure)) {
        return dataStructure + " present in code";
      }
    }
    return "No " + dataStructure + "in code";

  }

}
