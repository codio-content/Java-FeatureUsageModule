package com.codio.feature_usage_mod.controller.features.constructs;

import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.visitor.GenericVisitorAdapter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

public class IfConditionals extends VoidVisitorAdapter<List<String>> {

  public IfConditionals(){}

  @Override
  public void visit(IfStmt ifs, List<String> collector) {
    super.visit(ifs, collector);
    collector.add(String.valueOf(ifs.isIfStmt()));
    collector.add(String.valueOf(ifs.hasElseBlock()));
    collector.add(String.valueOf(ifs.hasCascadingIfStmt()));
  }

}
