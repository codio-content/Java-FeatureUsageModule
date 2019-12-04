package com.codio.feature_usage_mod.controller.features.datastructures;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;

import java.util.List;

/**
 * Abstract class that processes the presence of Data Structures
 */

abstract class AbstractPolymorphismChecker extends AbstractObjectCreationChecker {

  public String process(CompilationUnit cu, String dataStructure, String polymorphism) {
    if (polymorphism.equals("N")) {
      return super.process(cu, dataStructure);
    } else {
      List<VariableDeclarationExpr> varDecExprs = cu.findAll(VariableDeclarationExpr.class);
      if (varDecExprs.size() == 0) {
        return "No " + dataStructure + " (variables) in code";
      }
      for (VariableDeclarationExpr obj : varDecExprs) {
        if (obj.toString().startsWith(dataStructure)) {
          return dataStructure + " present in code. \n NO POLYMORPHISM ! ";
        } else if (obj.toString().contains(dataStructure)) {
          return dataStructure + " present in code. \n POLYMORPHISM DETECTED! ";
        }
      }
      return "No " + dataStructure + "in code";
    }
  }

}
