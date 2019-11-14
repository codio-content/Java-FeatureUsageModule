package com.codio.feature_usage_mod.controller.features.constructs;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;

import java.util.List;

public class IfConditionals {

  public IfConditionals() {
  }

  public String process(Node node) {
    String message = "";

    List<IfStmt> allNodes = node.findAll(IfStmt.class);
    if (allNodes.size() == 0) {
      message = "There is no if statement in the code";
    } else {
      IfStmt ifStmt = allNodes.get(0);
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
    return message;
  }
}
