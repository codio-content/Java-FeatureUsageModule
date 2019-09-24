package com.codio.feature_usage_mod;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;


import java.io.File;

/**
 * This class contains the main method to run the program
 */
public class FeatureUsageChecker {

  private static final String FILE_PATH = "src/main/java/org/javaparser/examples/StudentCode.java";

  public static void main(String args[]) throws Exception{
    // initialize Model
    // initialize Controller
    // initialize View
    // Pass Control to the Controller by calling the GO Method

    CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH));
    VoidVisitor<?> classNameVisitor = new FeatureUsageChecker.ClassNamePrinter();
    classNameVisitor.visit(cu, null);
  }

  private static class ClassNamePrinter extends VoidVisitorAdapter<Void> {

    @Override
    public void visit(ClassOrInterfaceDeclaration cd, Void arg) {
      super.visit(cd, arg);
      System.out.println("Class Name Printed: " + cd.getName());
    }
  }
}
