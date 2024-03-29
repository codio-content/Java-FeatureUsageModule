//package com.codio.feature_usage_mod.support;
//
//import com.github.javaparser.JavaParser;
//import com.github.javaparser.ParseException;
//import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
//import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
//import com.google.common.base.Strings;
//import java.io.File;
//import java.io.IOException;
//
//public class ListClassesExample {
//
//  public static void listClasses(File projectDir) {
//    new DirectoryExplorer((level, path, file) -> path.endsWith(".java"), (level, path, file) -> {
//      System.out.println(path);
//      System.out.println(Strings.repeat("=", path.length()));
//      try {
//        new VoidVisitorAdapter<Object>() {
//          @Override
//          public void visit(ClassOrInterfaceDeclaration n, Object arg) {
//            super.visit(n, arg);
//            System.out.println(" * " + n.getName());
//          }
//        }.visit(JavaParser.parse(file), null);
//        System.out.println(); // empty line
//      } catch (ParseException | IOException e) {
//        new RuntimeException(e);
//      }
//    }).explore(projectDir);
//  }
//  public static void main(String[] args) {
//    File projectDir = new File("source_to_parse/junit-master");
//    listClasses(projectDir);
//  }
//}