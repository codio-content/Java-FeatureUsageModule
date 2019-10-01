package com.codio.feature_usage_mod.controller.features.constructs;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

public class Methods extends VoidVisitorAdapter<List<String>> {

  public Methods (){}

  @Override
  public void visit(MethodDeclaration md, List<String> collector) {
    super.visit(md, collector);
    collector.add(md.getDeclarationAsString());
  }
}
