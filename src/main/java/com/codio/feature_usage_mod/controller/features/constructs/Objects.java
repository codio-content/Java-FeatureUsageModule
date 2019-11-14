package com.codio.feature_usage_mod.controller.features.constructs;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.ObjectCreationExpr;

import java.util.List;

public class Objects {

  public Objects() {
  }

  public String process(CompilationUnit cu) {
    List<ObjectCreationExpr> objects = cu.findAll(ObjectCreationExpr.class);
    int count = objects.size();
    return generateMessage(count, objects);
  }

  private String generateMessage(int count, List<ObjectCreationExpr> objects) {
    if (count == 0) {
      return "No Objects in Student Code";
    } else if (count == 1) {
      return "1 Object in Student Code.\nObject type: " + getObjectNames(objects);
    } else {
      return count + " Objects in Student Code.\nObject types:\n" + getObjectNames(objects);
    }
  }

  private String getObjectNames(List<ObjectCreationExpr> objects) {
    StringBuilder sb = new StringBuilder();
    for (ObjectCreationExpr object: objects) {
      sb.append(object.getTypeAsString()).append("\n");
    }
    return sb.toString();
  }
  //Pass by value, pass by reference

}
