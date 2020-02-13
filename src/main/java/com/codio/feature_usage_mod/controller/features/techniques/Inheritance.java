package com.codio.feature_usage_mod.controller.features.techniques;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

import java.util.List;

//TODO:  Edge case alert. Only the presence of Extends doesn't imply inheritance


public class Inheritance {

  public Inheritance() {
  }

  public String process(CompilationUnit cu) {
    ClassOrInterfaceDeclaration classDec = cu.findAll(ClassOrInterfaceDeclaration.class).get(0);
    List<ClassOrInterfaceType> inheritanceList = classDec.getExtendedTypes();
    if (inheritanceList.size() == 0) {
      return "No classes extended or interfaces implemented.";
    }

    return "Inheritance FOUND";
  }
}
