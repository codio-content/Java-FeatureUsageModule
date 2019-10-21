package com.codio.feature_usage_mod.controller.features.techniques;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class StandardInputOutput {

  //TODO: Check presence of Standard Input Library BufferedReader(InputStreamReader) )

  //TODO: Check presence of System.out.print() ?

  public StandardInputOutput(){}

  public String process(CompilationUnit cu) {

    StringBuilder sb = new StringBuilder();
    boolean importScanner = false;

    List<ImportDeclaration> importList = cu.findAll(ImportDeclaration.class);

    for (ImportDeclaration importStatement: importList) {
      if (importStatement.toString().contains("java.util.Scanner")) {
        sb.append("java.util.Scanner library has been imported in code. \n");
        importScanner = true;
        break;
      }
    }
    if (!importScanner) {
      sb.append("java.util.Scanner library NOT FOUND. \n");
      return sb.toString();
    }


    ClassOrInterfaceDeclaration classDec = cu.findAll(ClassOrInterfaceDeclaration.class).get(0);
    List<String> classAsList = Arrays.asList(classDec.toString().split("\n"));

    Stack<String> classAsStack = convertListToStack(classAsList);

    Stack<String> classAfterScannerDec = findScannerDeclaration(classAsStack);

    if (classAfterScannerDec.size() != 0) {
      sb.append("Scanner object has been declared in code. \n");
    }
    else {
      sb.append("Scanner object declaration NOT FOUND. \n");
      return sb.toString();
    }
    String scannerObject = getScannerObject(classAfterScannerDec);
    return findScannerFunction(classAfterScannerDec, scannerObject);
  }

  private String findScannerFunction(Stack<String> classAfterScannerDec, String scannerObject) {
    String line = classAfterScannerDec.pop();
    if (classAfterScannerDec.empty()) {
      return "Scanner function NOT FOUND";
    }
    if (line.contains(scannerObject + ".has") || line.contains(scannerObject + ".next")) {
      return "Scanner Function found in code";
    }
    else {
      return findScannerFunction(classAfterScannerDec, scannerObject);
    }
  }

  private String getScannerObject(Stack<String> classAfterScannerDec) {
    return classAfterScannerDec.pop().replaceAll("[ = new Scanner(System.in);]*", "");
  }

  private Stack<String> findScannerDeclaration(Stack<String> classAsStack) {

    String line = classAsStack.peek();
    if (line.contains("new Scanner(System.in);")) {
      return classAsStack;
    }

    classAsStack.pop();
    return findScannerDeclaration(classAsStack);

  }

  private Stack<String> convertListToStack(List<String> list) {
    Stack<String> stack = new Stack<>();
    stack.addAll(list);
    return stack;
  }


}
