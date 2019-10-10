package com.codio.feature_usage_mod.controller.features.techniques;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;

import java.util.List;

public class LibraryUsage {

  public LibraryUsage(){}

  public String process(CompilationUnit cu, String libraryName) {
    List<ImportDeclaration> libraryList = cu.findAll(ImportDeclaration.class);
    if (libraryList.size() == 0) {
      return "No Import Statements in Code";
    }

    for (ImportDeclaration impDec: libraryList) {
      if (impDec.toString().contains("libraryName")) {
        return "Required Library used in Student Code";
      }
    }
    return "Required Library NOT FOUND";
  }
}
