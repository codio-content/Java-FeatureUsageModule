package com.codio.feature_usage_mod.controller.features.constructs;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.util.List;

public class MethodReturnTypes {

  public MethodReturnTypes() {
  }

  public String processGeneralCase(CompilationUnit cu) {

    List<MethodDeclaration> methods = cu.findAll(MethodDeclaration.class);
    int count = methods.size();
    if (count == 0) {
      return "No Methods in Student Code";
    }
    int voidMethods = 0;
    int otherMethods = 0;
    boolean mainMethod = false;

    for (MethodDeclaration method : methods) {
      String methodDec = method.getDeclarationAsString();
      if (methodDec.contains("void")) {
        if (methodDec.contains("main")) {
          mainMethod = true;
        }
        voidMethods++;
      }
      else {
        otherMethods++;
      }
    }
    return generateMessageForGeneralCase(voidMethods, otherMethods, mainMethod);
  }

  public String processSpecificCase(CompilationUnit cu, String returnType, String functionName) {
    List<MethodDeclaration> methods = cu.findAll(MethodDeclaration.class);
    if (methods.size() == 0) {
      return "There are no methods in code";
    }

    for (MethodDeclaration method : methods) {
      String methodDec = method.getDeclarationAsString();
      if (methodDec.contains(returnType) && methodDec.contains(functionName)) {
        return "Function with specified return type present";
      }
    }
    return "Function with specified return type NOT FOUND";
  }

  private String generateMessageForGeneralCase(int voidMethods, int otherMethods, boolean mainMethod) {
    StringBuilder sb = new StringBuilder();
    if (mainMethod) {
      sb.append("The main method is present in Student Code.\n")
              .append(voidMethods).append(" method(s) with return type 'void' (except main).\n")
              .append(otherMethods).append(" method(s) with other return types.\n");
      return sb.toString();
    }
    else {
      sb.append("No main method in Student Code.\n")
              .append(voidMethods).append(" method(s) with return type 'void'.\n")
              .append(otherMethods).append(" method(s) with other return types.\n");
      return sb.toString();
    }
  }

}
