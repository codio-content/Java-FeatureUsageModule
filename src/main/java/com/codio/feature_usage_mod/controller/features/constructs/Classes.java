package com.codio.feature_usage_mod.controller.features.constructs;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class Classes {

  public static class ClassNamePrinter extends VoidVisitorAdapter<Void> {

    @Override
    public void visit(ClassOrInterfaceDeclaration cd, Void arg) {
      super.visit(cd, arg);
      System.out.println("Class Name Printed: " + cd.getName());
    }
  }
}
