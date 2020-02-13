package com.codio.feature_usage_mod.controller.features.techniques;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Composition {

  //TODO: Abstraction for ClassDec to List

  //TODO: Only case I can think of is having the user input the object/class to see if we have a
  // "has-a" relationship, i.e. no inheritance

  //General case - Look for an object that is not a standard data type

  //TODO: Regex for path of files for same package needs modification for special characters

  public Composition() {
  }

  public String processSpecificCase(CompilationUnit cu, String superClass, String subClass) {

    /*
    Step 1 - We have to find the super class - either in same package or in other package.
    If it doesn't exist, no composition
    if it exists, create a String with the path ???

    In the sub class, find the Object declared as a variable
    and then find the occurrence of that object being created and a method being referenced using that object
    if found, yayayayy else NO composition

     */


    //Step 2 -


    return "";
  }

  public String processGeneralCase(CompilationUnit cu) {

    /*
    In the sub class, find the Object declared as a variable

    Then check if the student has defined a class by that name from the object type
    if not, then no composition
    if yes,
    then find the occurence of that object being created and a method being referenced using that object
    if found, yayayayy else NO composition

     */

    List<String> classBody = convertClassDecToList(cu);
    List<String> classesInSamePackage = listOfClasses();

    List<ImportDeclaration> importList = cu.findAll(ImportDeclaration.class);
    List<String> relevantImports = new ArrayList<>();

    List<FieldDeclaration> fieldDecs = cu.findAll(FieldDeclaration.class);
    List<String> relevantFields = new ArrayList<>();

    for (ImportDeclaration importStatement : importList) {
      String importStmt = importStatement.toString();
      if (!(importStmt.startsWith("import java."))) {
        importStmt = importStmt.replaceAll("[ a-zA-z0-9._]*\\.", "")
                .replaceAll("[;\r\n]*", "");
        relevantImports.add(importStmt);
      }
    }

    for (FieldDeclaration fieldDec : fieldDecs) {
      String field = fieldDec.toString();
      if (!(field.contains("int") || field.contains("String") || field.contains("float")
              || field.contains("double") || field.contains("boolean") || field.contains("long")
              || field.contains("short") || field.contains("byte") || field.contains("char"))) {
        relevantFields.add(field);
      }
    }

    for (String field : relevantFields) {

      //check if the field is related to relevantImport
      Boolean flag = checkIfFieldInRelevantImport(field, relevantImports);
      if (flag) {
        //Field is relevant to Import

        return checkIfCompositionIsPresent(cu, field);
      } else {
        //if NOT - check if field is related to classInSamePackage
        flag = checkIfFieldInSamePackage(field, classesInSamePackage);
        if (flag) {
          //Field is relevant to Class in same package
          return checkIfCompositionIsPresent(cu, field);
        }
      }
      //if NOT - NO COMPOSITION
    }

    return "Composition NOT FOUND";
  }

  private String checkIfCompositionIsPresent(CompilationUnit cu, String fieldName) {

    System.out.println(fieldName);
    fieldName = fieldName.replaceAll("[a-zA-z]* ", "").replace(";", "");
    System.out.println(fieldName);
    List<MethodCallExpr> methodCalls = cu.findAll(MethodCallExpr.class);
    for (MethodCallExpr methodCallExpr : methodCalls) {
      String methodCall = methodCallExpr.toString();
      if (methodCall.contains(fieldName + ".")) {
        //Return Composition FOUND
        return "Composition present";
      }
    }
    //return Composition NOT FOUND
    return "Composition NOT FOUND";
  }

  private Boolean checkIfFieldInSamePackage(String field, List<String> classesInSamePackage) {

    for (String className : classesInSamePackage) {
      if (field.contains(className)) {
        return true;
      }
    }
    return false;
  }

  private boolean checkIfFieldInRelevantImport(String field, List<String> relevantImports) {

    for (String importedClass : relevantImports) {

      if (field.contains(importedClass)) {
        return true;
      }
    }
    return false;
  }

  private List<String> convertClassDecToList(CompilationUnit cu) {
    ClassOrInterfaceDeclaration classDec = cu.findAll(ClassOrInterfaceDeclaration.class).get(0);
    return Arrays.asList(classDec.toString().split("\n"));

  }

  private List<String> listOfClasses() {

    List<String> listofClasses = new ArrayList<>();

    try (Stream<Path> walk = Files.walk(Paths.get("src/main/java/org/javaparser/examples"))) {

      List<String> result = walk.map(Path::toString)
              .filter(f -> f.endsWith(".java"))
              .collect(Collectors.toList());

      for (String element : result) {

        listofClasses.add(element.replaceAll("[a-zA-z]*\\\\", "")
                .replaceAll("\\.java", ""));

      }
    } catch (IOException e) {
      List<String> exception = new ArrayList<>();
      exception.add(e.getMessage());
      return exception;
    }

    return listofClasses;
  }
}
