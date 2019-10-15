package com.codio.feature_usage_mod.controller.features.techniques;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MethodOverriding {

  //TODO: 3. Where does super.methodName() fit in this picture ? or is that inheritance ?

  public MethodOverriding(){}


  private String overriddenMethod = "";

  public String process(CompilationUnit cu) {

    String inheritance = new Inheritance().process(cu);
    if (!(inheritance.contains("Inheritance FOUND"))) {
      return "No Method Overriding, since there's no Inheritance";
    }

    ClassOrInterfaceDeclaration classDec = cu.findAll(ClassOrInterfaceDeclaration.class).get(0);
    List<String> classBody = Arrays.asList(classDec.toString().split("\n"));

    List<AnnotationExpr> annotations = cu.findAll(AnnotationExpr.class);

    for (String element: classBody) {
      if (element.contains("@Override")) {
        overriddenMethod = classBody.get(classBody.indexOf(element) + 1)
                .replace("{\r", "").trim();
        break;
      }
    }

    if (annotations.size() == 0 || overriddenMethod.equals("")) {
      try {
        return checkForMethodOverriding(cu, classDec, classBody);
      }
      catch (FileNotFoundException ffe) {
        return "Super Class File Not Found";
      }

    }
    return "No Method Overriding";
  }

  private String checkForMethodOverriding(CompilationUnit cu,
                                          ClassOrInterfaceDeclaration classDec,
                                          List<String> classBody) throws FileNotFoundException {

    ClassOrInterfaceType classOrInterface = classDec.getExtendedTypes().get(0);
    String superClassName = classOrInterface.getNameAsString();
    StringBuilder sb = new StringBuilder().append("src/main/java/");
    List<ImportDeclaration> libraryList = cu.findAll(ImportDeclaration.class);

    for (ImportDeclaration importDec: libraryList) {
      if (importDec.toString().contains(superClassName)) {
        sb.append(importDec.getNameAsString().replaceAll("\\.", "/"))
                .append(".java");
        break;
      }
    }

    final String superClassPath = sb.toString();

    CompilationUnit superCu = StaticJavaParser.parse(new File(superClassPath));

    //Methods from subclass
    //List<MethodDeclaration> subClassMethods = cu.findAll(MethodDeclaration.class);

    List<String> subClassMethods = new ArrayList<>();

    //Methods from Superclass
    //List<MethodDeclaration> superClassMethods = superCu.findAll(MethodDeclaration.class);

    List<String> superClassMethods = new ArrayList<>();

//    for (MethodDeclaration method: superClassMethods) {
//      System.out.println(method.getDeclarationAsString().replaceAll(" [a-zA-z0-9]*,", ",").replaceAll(" [a-zA-z0-9]*\\)", ")"));
//    }

    for (MethodDeclaration subClassMethod: cu.findAll(MethodDeclaration.class)) {
      subClassMethods.add(subClassMethod.getDeclarationAsString()
              .replaceAll(" [a-zA-z0-9]*,", ",")
              .replaceAll(" [a-zA-z0-9]*\\)", ")"));
    }

    for (MethodDeclaration superClassMethod: superCu.findAll(MethodDeclaration.class)) {
      superClassMethods.add(superClassMethod.getDeclarationAsString()
              .replaceAll(" [a-zA-z0-9]*,", ",")
              .replaceAll(" [a-zA-z0-9]*\\)", ")"));
    }

    List<String> methodOverriding = new ArrayList<>(subClassMethods);

    if (methodOverriding.retainAll(superClassMethods)) {
      if (methodOverriding.size() == 0) {
        return "Method Overriding NOT FOUND";
      }
      return "Method Overriding Present";
    }

    return "Method Overriding NOT FOUND";
  }
}
