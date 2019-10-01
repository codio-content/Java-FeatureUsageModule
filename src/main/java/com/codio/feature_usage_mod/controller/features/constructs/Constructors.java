package com.codio.feature_usage_mod.controller.features.constructs;

import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.visitor.GenericVisitorAdapter;

import java.util.ArrayList;
import java.util.List;

public class Constructors extends GenericVisitorAdapter<String, Void> {

  public Constructors() {
  }
  @Override
  public String visit(ConstructorDeclaration cd, Void arg) {
    List<String> constructorNames = new ArrayList<>();
    StringBuilder returnMessage = new StringBuilder();
    super.visit(cd, arg);
    constructorNames.add(cd.getNameAsString());
    constructorNames.forEach(n -> returnMessage.append("Constructor Name Collected: ")
            .append(n)
            .append("\n"));

    return returnMessage.toString();
  }
}
