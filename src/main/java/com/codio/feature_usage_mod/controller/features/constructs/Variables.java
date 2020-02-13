package com.codio.feature_usage_mod.controller.features.constructs;

import com.codio.feature_usage_mod.controller.features.IConstructs;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Variables implements IConstructs {

  public Variables() {
  }

  // TODO: User input of specific type of variable in the right context

  private Hashtable<String, String> classVarTable = new Hashtable<>();
  private Hashtable<String, String> localVarTable = new Hashtable<>();

  public String process(CompilationUnit cu) {

    StringBuilder sb = new StringBuilder();
    List<FieldDeclaration> classVariables = cu.findAll(FieldDeclaration.class);

    if (classVariables.size() == 0) {
      sb.append("No Class/Global Variables found in Student Code.\n\n");
    }
    else {
      addToGlobalHashTable(classVariables);
      sb.append(printHashTables(classVarTable, "global"));
    }

    List<VariableDeclarationExpr> localVariables = cu.findAll(VariableDeclarationExpr.class);
    if (localVariables.size() == 0) {
      sb.append("No Local Variables found in Student Code.\n\n");
    }
    else {
    addToLocalHashTable(localVariables);
    sb.append(printHashTables(localVarTable, "local"));
  }
    System.out.println(sb.toString());
    return sb.toString();
  }

  private void addToGlobalHashTable(List<FieldDeclaration> classVariables) {
    for (FieldDeclaration classVariable: classVariables) {
      ClassOrInterfaceDeclaration parentClass = getParentClass(classVariable);
      String className = parentClass.getNameAsString();
      classVarTable.put(classVariable.toString(), className);
    }
  }

  private void addToLocalHashTable(List<VariableDeclarationExpr> localVariables) {
    for (VariableDeclarationExpr localVariable: localVariables) {
      CallableDeclaration methodOrConstructor = getParentMethodOrConstructor(localVariable);
      String methodOrConstructorName = getMethodOrConstructorName(methodOrConstructor);
      localVarTable.put(localVariable.toString(), methodOrConstructorName);
    }
  }

  private String printHashTables(Hashtable<String, String> hashtable, String scope) {
    StringBuilder sb = new StringBuilder();
    if (scope.equalsIgnoreCase("global")) {
      sb.append("List of Global/Class Variables with Scope Details:\n\n")
              .append("Global/Class Variable\t\t\t\t\t\tDetails\n");
    }
    else {
      sb.append("List of Local Variables with Scope Details:\n\n")
              .append("Local Variable\t\t\t\t\t\tDetails\n");
    }

    Set<String> keySet = hashtable.keySet();
    for (String key: keySet) {
      sb.append(key).append("\t\t\t\t\t\t").append(hashtable.get(key)).append("\n");
    }
    return sb.toString();
  }

  private CallableDeclaration getParentMethodOrConstructor(Node node) {
    if (node instanceof CallableDeclaration) {
      return ((CallableDeclaration) node);
    } else {
      return node.getParentNode().map(this::getParentMethodOrConstructor).orElse(null);
    }
  }

  private String getMethodOrConstructorName(CallableDeclaration methodOrConstructor) {

    StringBuilder sb = new StringBuilder();
    String name = methodOrConstructor.getDeclarationAsString(true, false);
    if (methodOrConstructor.isConstructorDeclaration()) {
      sb.append("Constructor Name: ").append(name).append("\n");
    } else {
      sb.append("Method Name: ").append(name).append("\n");
    }
    return sb.toString();
  }

  private ClassOrInterfaceDeclaration getParentClass(Node node) {
    if (node instanceof ClassOrInterfaceDeclaration) {
      return ((ClassOrInterfaceDeclaration) node);
    } else {
      return node.getParentNode().map(this::getParentClass).orElse(null);
    }
  }
}
