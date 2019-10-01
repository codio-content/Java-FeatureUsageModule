package org.javaparser.examples;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VoidVisitorStarter {

  private static final String FILE_PATH = "src/main/java/org/javaparser/examples/" +
          "ReversePolishNotation.java";

  public static void main(String[] args) throws Exception {

    CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH));

    //Visitor without List Object as Parameter Type
    VoidVisitor<?> methodNameVisitor = new MethodNamePrinter();
    methodNameVisitor.visit(cu, null);
    System.out.println("\n-------------\n");
    //Visitor with Lost Object as Parameter Type

    List<String> methodNames = new ArrayList<>();
    VoidVisitor<List<String>> methodNameCollector = new MethodNameCollector();
    methodNameCollector.visit(cu, methodNames);
    methodNames.forEach(n -> System.out.println("Method Name Collected: " + n));

    System.out.println("\n-------------\n");
    List<String> constructorNames = new ArrayList<>();
    VoidVisitor<List<String>> constructorNameCollector = new ConstructorNameCollector();
    constructorNameCollector.visit(cu, constructorNames);
    constructorNames.forEach(n -> System.out.println("Constructor Name Collected " + n));
  }


  private static class MethodNamePrinter extends VoidVisitorAdapter<Void> {

    @Override
    public void visit(MethodDeclaration md, Void arg) {
      super.visit(md, arg);
      System.out.println("Method Name Printed: " + md.getName());
    }
  }

  private static class MethodNameCollector extends VoidVisitorAdapter<List<String>> {
    @Override
    public void visit(MethodDeclaration md, List<String> collector) {
      super.visit(md, collector);
      collector.add(md.getNameAsString());
    }
  }

  private static class ConstructorNameCollector extends VoidVisitorAdapter<List<String>> {
    @Override
    public void visit(ConstructorDeclaration cd, List<String> collector) {
      super.visit(cd, collector);
      collector.add(cd.getDeclarationAsString());
    }
  }

}
