package com.codio.feature_usage_mod.controller.features.datastructures;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.ArrayCreationExpr;
import com.github.javaparser.ast.visitor.GenericVisitorAdapter;

import java.util.List;

public class Arrays extends GenericVisitorAdapter<String, Void> {

  public Arrays() {

    // Static, size, typecasting, dimension, sorted-unsorted (ALL OF THE WAYS), mean, median, mode, sum, min, max
  }

  public String process(CompilationUnit cu) {
    List<ArrayCreationExpr> arrays = cu.findAll(ArrayCreationExpr.class);
    if(arrays.size() == 0) {
      return "No Arrays in code";
    }
    if (arrays.get(0).isArrayCreationExpr()) {
      return "Array Creation expression found";
    }
    else {
      return "No Arrays in code";
    }
  }

//  @Override
//  public String visit(ArrayCreationExpr arrayCreationExpr, Void arg) {
//    super.visit(arrayCreationExpr, arg);
//    List<ArrayCreationExpr> arrays = arrayCreationExpr.findAll(ArrayCreationExpr.class);
//    if(arrays.size() == 0) {
//      return "No Arrays in code";
//    }
//    if (arrays.get(0).isArrayCreationExpr()) {
//      return "Array Creation expression found";
//    }
//    else {
//      return "No Arrays in code";
//    }
//  }
}
