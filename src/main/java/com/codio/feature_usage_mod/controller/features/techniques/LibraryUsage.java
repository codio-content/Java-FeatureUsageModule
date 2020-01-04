package com.codio.feature_usage_mod.controller.features.techniques;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;

import java.util.ArrayList;
import java.util.List;

public class LibraryUsage {

  public LibraryUsage() {
  }

  public String process(CompilationUnit cu, String libraryName) {

    List<ImportDeclaration> allImports = cu.findAll(ImportDeclaration.class);
    List<ImportDeclaration> asteriskImports = new ArrayList<>();

    boolean flag = false;
    if (allImports.size() == 0) {
      return "No Import Statements in Code";
    }

    for (ImportDeclaration impDec : allImports) {
      if (impDec.isAsterisk()) {
        asteriskImports.add(impDec);
      }
      else {
        if (impDec.toString().contains(libraryName)) {
          flag = true;
        }
      }
    }
    if (flag) {
      return "Required Library used in Student Code";
    }
    else {
      for (ImportDeclaration asteriskImport: asteriskImports) {
        String importStatement = asteriskImport.getNameAsString();
        if (libraryName.contains(importStatement)) {
          return "Required Library used in the form of Asterisk Import Statement";
        }
      }
      return "Required Library Not Found";
    }
  }
}
