package com.codio.feature_usage_mod.controller.features.datastructures;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.ObjectCreationExpr;

import java.util.List;

public class HashTables {

  //min, max, avg, chain length, hashing algorithm, keys, values,

  public HashTables(){}

  public String process(CompilationUnit cu) {
    List<ObjectCreationExpr> objectCreationExpr = cu.findAll(ObjectCreationExpr.class);
    if (objectCreationExpr.size() == 0) {
      return "No HashTable (objects) in code";
    }

    for (ObjectCreationExpr obj: objectCreationExpr) {
      System.out.println(obj.toString());
      if (obj.toString().contains("new Hashtable")) {
        return "HashTable present in code";
      }
    }
    return "No HashTable (objects) in code";

  }
}
