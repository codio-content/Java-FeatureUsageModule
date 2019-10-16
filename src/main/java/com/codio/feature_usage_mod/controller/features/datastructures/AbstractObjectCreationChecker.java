package com.codio.feature_usage_mod.controller.features.datastructures;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.ObjectCreationExpr;

import java.util.List;

abstract class AbstractObjectCreationChecker {


  public String process(CompilationUnit cu, String dataStructure) {
    List<ObjectCreationExpr> objectCreationExpr = cu.findAll(ObjectCreationExpr.class);
    if (objectCreationExpr.size() == 0) {
      return "No " + dataStructure + " (objects) in code";
    }

    for (ObjectCreationExpr obj : objectCreationExpr) {
      if (obj.toString().contains("new " + dataStructure)) {
        return dataStructure + " present in code";
      }
    }
    return "No " + dataStructure + "in code";

  }
}
