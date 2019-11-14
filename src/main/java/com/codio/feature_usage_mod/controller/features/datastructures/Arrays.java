package com.codio.feature_usage_mod.controller.features.datastructures;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.ArrayCreationExpr;

import java.util.List;

public class Arrays {

  public Arrays() {

    // Static, size, typecasting, dimension, sorted-unsorted (ALL OF THE WAYS), mean, median, mode, sum, min, max
  }

  public String process(CompilationUnit cu) {
    List<ArrayCreationExpr> arrays = cu.findAll(ArrayCreationExpr.class);
    if (arrays.size() == 0) {
      return "No Arrays in code";
    }
    if (arrays.get(0).isArrayCreationExpr()) {
      return "Array Creation expression found";
    } else {
      return "No Arrays in code";
    }
  }
}
