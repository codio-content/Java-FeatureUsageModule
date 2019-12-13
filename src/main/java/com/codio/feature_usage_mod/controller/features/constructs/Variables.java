package com.codio.feature_usage_mod.controller.features.constructs;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;

import java.util.ArrayList;
import java.util.List;

public class Variables implements IConstructs {

  public Variables() {
  }

  //TODO: Lists of all types of variable names

  //TODO: User input of specific type of variable in the right context

  public String process(CompilationUnit cu) {

    StringBuilder sb = new StringBuilder();
    List<MethodDeclaration> methods = cu.findAll(MethodDeclaration.class);

    List<String> localVariables = new ArrayList<>();
    List<String> instanceVariables = new ArrayList<>();
    List<String> globalVariables = new ArrayList<>();
    List<String> staticClassVariables = new ArrayList<>();

    ClassOrInterfaceDeclaration classBody = cu.findAll(ClassOrInterfaceDeclaration.class).get(0);

//    List<Node> childNodes = classBody.getChildNodes();
//    System.out.println(childNodes.toString());


    if (methods.size() == 0) {
      sb.append("No Local Variables in the code");
    } else {
      for (MethodDeclaration method : methods) {

        List<VariableDeclarationExpr> locVarExpr = method.findAll(VariableDeclarationExpr.class);

      }
    }

    return "";
  }
}
