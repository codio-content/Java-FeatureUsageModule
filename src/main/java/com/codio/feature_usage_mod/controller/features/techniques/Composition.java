package com.codio.feature_usage_mod.controller.features.techniques;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;

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

  //TODO: Only case I can think of is having the user input the object/class to see if we have a "has-a" relationship, i.e. no inheritance

  //General case - Look for an object that is not a standard datatype

  public Composition(){}

  public String processSpecificCase(CompilationUnit cu, String superClass, String subClass) {

    /*
    Step 1 - We have to find the super class - either in same package or in other package.
    If it doesnt exist, no composition
    if it exists, create a String with the path ???

    In the sub class, find the Object declared as a variable
    and then find the occurence of that object being created and a method being referenced using that object
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

    List<VariableDeclarationExpr> listOfVariables = cu.findAll(VariableDeclarationExpr.class);

    //System.out.println(listOfVariables.toString());

    System.out.println(listOfClasses().toString());


    return "";
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

      for (String element: result) {

        listofClasses.add(element.replaceAll("[a-zA-z]*\\\\", ""));

      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return listofClasses;
  }
}
