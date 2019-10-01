package com.codio.feature_usage_mod.controller.features.constructs;

import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

public class Constructors extends VoidVisitorAdapter<List<String>> {

  public Constructors() {
  }
  @Override
  public void visit(ConstructorDeclaration cd, List<String> collector) {
    super.visit(cd, collector);
    collector.add(cd.getDeclarationAsString());
  }
}
