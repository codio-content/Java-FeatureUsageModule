package com.codio.feature_usage_mod.controller.features.constructs;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;

import java.util.List;

//TODO: Revamp the whole logic for getting counts for all possible statements and cases

public class IfConditionals {

  public IfConditionals() {
  }

  public String process(CompilationUnit cu) {
    String message = "";

    List<IfStmt> ifStmts = cu.findAll(IfStmt.class);

    int total_If = ifStmts.size();
    int total_Else_If = 0;
    int total_If_Else = 0;
    int total_Nested_If_Else = 0;
    int max_Nest_Depth = 0;

    if (ifStmts.size() == 0) {
      message = "There is no if statement in the code";
    } else {

      for (IfStmt ifStmt : ifStmts) {
        List<Node> nodes = ifStmt.getChildNodes();
        for (Node node : nodes) {
          if (node instanceof BlockStmt) {
            List<Node> childNodes = node.getChildNodes();

          }
        }

        if (ifStmt.getElseStmt().isPresent()) {
          Statement elseStmt = ifStmt.getElseStmt().get();
          if (elseStmt instanceof IfStmt) {
            message = "There is a Cascading if statement (nested if-else block) in the code";
          } else {
            message = "If-Else block present. No cascading If Statement";
          }
        } else {
          message = "There is only an if statement with no else block";
        }


      }


    }
    return message;
  }
}
