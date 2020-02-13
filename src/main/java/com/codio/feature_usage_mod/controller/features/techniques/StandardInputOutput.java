package com.codio.feature_usage_mod.controller.features.techniques;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class StandardInputOutput {

  public StandardInputOutput() {
  }

  public String process(CompilationUnit cu) {

    List<ImportDeclaration> importList = cu.findAll(ImportDeclaration.class);
    List<String> classAsList = convertClassDecToList(cu);
    Stack<String> classAsStack = convertListToStack(classAsList);

    String scannerMessage = checkForScanner(importList, classAsStack);

    String bufferedReaderMessage = checkForBufferedReader(importList, classAsStack);

    String stdOutMessage = checkForStandardOutput(classAsStack, new StringBuilder());

    return scannerMessage + bufferedReaderMessage + stdOutMessage;
  }

  private String checkForStandardOutput(Stack<String> classAsStack, StringBuilder sb) {

    String line = classAsStack.pop();
    if (classAsStack.empty()) {
      sb.append("System.out.print function NOT FOUND. \n");
      return sb.toString();
    }
    if (line.contains("System.out.print")) {
      sb.append("System.out,print Function found in code. \n");
      return sb.toString();
    } else {
      return checkForStandardOutput(classAsStack, sb);
    }

  }

  private String checkForScanner(List<ImportDeclaration> importList, Stack<String> classAsStack) {

    StringBuilder sb = new StringBuilder();
    String scannerImportMessage = findScannerImportDec(importList);
    if (scannerImportMessage.contains("NOT FOUND")) {
      sb.append(scannerImportMessage);
      return scannerImportMessage;
    }
    sb.append(scannerImportMessage);

    Stack<String> classAfterScannerDec = findScannerDeclaration(classAsStack);

    if (classAfterScannerDec.size() != 0) {

      sb.append("Scanner object has been declared in code. \n");
      String scannerObject = getScannerObject(classAfterScannerDec);

      return findScannerFunction(classAfterScannerDec, scannerObject, sb);
    }

    sb.append("Scanner object declaration NOT FOUND. Ergo, No Scanner functions. \n");
    return sb.toString();

  }


  private String checkForBufferedReader(List<ImportDeclaration> importList, Stack<String> classAsStack) {
    StringBuilder sb = new StringBuilder();
    String bufferedReaderImportMessage = findBufferedReaderImportDec(importList);
    if (bufferedReaderImportMessage.contains("NOT FOUND")) {
      sb.append(bufferedReaderImportMessage);
      return bufferedReaderImportMessage;
    }
    sb.append(bufferedReaderImportMessage);

    Stack<String> classAfterBufferedReaderDec = findBufferedReaderDeclaration(classAsStack);

    if (classAfterBufferedReaderDec.size() != 0) {

      sb.append("Buffered Reader object has been declared in code. \n");
      String bufferedReaderObject = getBufferedReaderObject(classAfterBufferedReaderDec);

      return findBufferedReaderFunction(classAfterBufferedReaderDec, bufferedReaderObject, sb);
    }

    sb.append("Buffered Reader object declaration NOT FOUND. Ergo, No functions. \n");
    return sb.toString();

  }

  private String findBufferedReaderFunction(Stack<String> classAfterBufferedReaderDec,
                                            String bufferedReaderObject, StringBuilder sb) {

    String line = classAfterBufferedReaderDec.pop();
    if (classAfterBufferedReaderDec.empty()) {
      sb.append("BufferedReader function NOT FOUND. \n");
      return sb.toString();
    }
    if (line.contains(bufferedReaderObject + ".read")) {
      sb.append("BufferedReader function found in code. \n");
      return sb.toString();
    } else {
      return findBufferedReaderFunction(classAfterBufferedReaderDec, bufferedReaderObject, sb);
    }
  }

  private String getBufferedReaderObject(Stack<String> classAfterBufferedReaderDec) {
    return classAfterBufferedReaderDec.pop()
            .replaceAll("[ = new BufferedReader(new InputStreamReader(System.in));]*", "");
  }

  private Stack<String> findBufferedReaderDeclaration(Stack<String> classAsStack) {

    String line = classAsStack.peek();
    if (line.contains("new BufferedReader(new InputStreamReader(System.in))")) {
      return classAsStack;
    }
    classAsStack.pop();
    return findBufferedReaderDeclaration(classAsStack);
  }

  private String findBufferedReaderImportDec(List<ImportDeclaration> importList) {

    StringBuilder sb = new StringBuilder();
    for (ImportDeclaration importStatement : importList) {
      String importStmnt = importStatement.toString();
      if (importStmnt.contains("java.io.BufferedReader")) {
        sb.append("java.io.BufferedReader library has been imported in code. \n");
        if (importStmnt.contains("java.io.InputStreamReader")) {
          sb.append("java.io.InputStreamReader library for stdin has been imported in code. \n");
        }
        sb.append("java.io.InputStreamReader NOT FOUND. \n");
        return sb.toString();
      }
    }
    sb.append("java.io.BufferedReader and java.io.InputStreamReader NOT FOUND. \n");
    return sb.toString();
  }

  private String findScannerImportDec(List<ImportDeclaration> importList) {

    StringBuilder sb = new StringBuilder();
    for (ImportDeclaration importStatement : importList) {
      if (importStatement.toString().contains("java.util.Scanner")) {
        sb.append("java.util.Scanner library has been imported in code. \n");
        return sb.toString();
      }
    }
    sb.append("java.util.Scanner library NOT FOUND. \n");
    return sb.toString();
  }

  private String findScannerFunction(Stack<String> classAfterScannerDec,
                                     String scannerObject, StringBuilder sb) {
    String line = classAfterScannerDec.pop();
    if (classAfterScannerDec.empty()) {
      sb.append("Scanner function NOT FOUND. \n");
      return sb.toString();
    }
    if (line.contains(scannerObject + ".has") || line.contains(scannerObject + ".next")) {
      sb.append("Scanner Function found in code. \n");
      return sb.toString();
    } else {
      return findScannerFunction(classAfterScannerDec, scannerObject, sb);
    }
  }

  private String getScannerObject(Stack<String> classAfterScannerDec) {
    return classAfterScannerDec.pop().replaceAll("[ = new Scanner(System.in);]*", "");
  }

  private Stack<String> findScannerDeclaration(Stack<String> classAsStack) {

    String line = classAsStack.peek();
    if (line.contains("new Scanner(System.in)")) {
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

  private List<String> convertClassDecToList(CompilationUnit cu) {
    ClassOrInterfaceDeclaration classDec = cu.findAll(ClassOrInterfaceDeclaration.class).get(0);
    return Arrays.asList(classDec.toString().split("\n"));

  }

}
