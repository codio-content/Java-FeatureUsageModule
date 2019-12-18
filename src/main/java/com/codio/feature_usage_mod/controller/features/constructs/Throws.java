package com.codio.feature_usage_mod.controller.features.constructs;

import com.codio.feature_usage_mod.controller.features.IConstructs;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.CallableDeclaration;

import java.util.ArrayList;
import java.util.List;

/**
 * Java Class to find Throws statements in Student Code.
 */

public class Throws implements IConstructs {

  public Throws() {
  }

  public String process(CompilationUnit cu) {

    List<CallableDeclaration> listOfMethodsAndConstructors = cu.findAll(CallableDeclaration.class);
    int numberOfMethodsOrConstructors = listOfMethodsAndConstructors.size();

    return checkForThrows(numberOfMethodsOrConstructors, listOfMethodsAndConstructors);
  }

  private String checkForThrows(int numberOfMethodsOrConstructors,
                                List<CallableDeclaration> listOfMethodsAndConstructors) {

    int numberOfThrows = 0;
    List<String> listOfMessages = new ArrayList<>();

    for (CallableDeclaration methodOrConstructor : listOfMethodsAndConstructors) {

      NodeList thrownExceptions = methodOrConstructor.getThrownExceptions();
      int numberOfExceptions = thrownExceptions.size();

      if (numberOfExceptions != 0) {
        numberOfThrows++;
        String message = generateExceptionData(methodOrConstructor, numberOfExceptions, thrownExceptions);
        listOfMessages.add(message);
      }
    }

    if (numberOfThrows == 0) {
      return "No 'Throws' statements found in Student Code.\n";
    } else if (numberOfThrows == 1) {
      return "1 'Throws' statement found in Student Code.\n" + printExceptionData(listOfMessages);
    } else {
      return numberOfThrows + "'Throws' statements found in Student Code.\n"
              + printExceptionData(listOfMessages);
    }
  }

  private String printExceptionData(List<String> listOfMessages) {
    StringBuilder sb = new StringBuilder();
    int count = 0;
    for (String message : listOfMessages) {
      count++;
      sb.append(count).append(") ").append(message).append("\n");
    }
    return sb.toString();
  }

  private String generateExceptionData(CallableDeclaration methodOrConstructor, int numberOfExceptions,
                                       NodeList thrownExceptions) {

    String name = methodOrConstructor.getNameAsString();
    StringBuilder sb = new StringBuilder();

    if (methodOrConstructor.isConstructorDeclaration()) {
      sb.append("Constructor Name: ").append(name).append("\n");
    } else {
      sb.append("Method Name: ").append(name).append("\n");
    }

    if (numberOfExceptions == 1) {
      sb.append("Exception after 'Throws' - ").append(getExceptionNames(thrownExceptions));
    } else {
      sb.append("Exceptions after 'Throws' - ").append(getExceptionNames(thrownExceptions));
    }

    return sb.toString();
  }

  private String getExceptionNames(NodeList list) {
    StringBuilder sb = new StringBuilder();
    for (Object exception : list) {
      sb.append(exception.toString()).append("\n");
    }
    return sb.toString();
  }

}

