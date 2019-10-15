package com.codio.feature_usage_mod.controller.features.techniques;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.WhileStmt;

import java.util.Arrays;
import java.util.List;

public class InfiniteLoops {


  public InfiniteLoops (){}

  public String process(CompilationUnit cu) {

    StringBuilder sb = new StringBuilder();
    List<DoStmt> doWhileLoops = cu.findAll(DoStmt.class);
    List<WhileStmt> whileLoops = cu.findAll(WhileStmt.class);
    List<ForStmt> forLoops = cu.findAll(ForStmt.class);


    if (doWhileLoops.size() == 0) {
      sb.append("No Do While Loops in Code.\n");
    }
    else {
      for (DoStmt loop: doWhileLoops) {
        String[] loopBody = loop.toString().split("\n");

        String condition = loop.getCondition().toString();
        String iterator = "";
        boolean dwFlag = false;
        if (condition.contains(">") || condition.contains(">=")) {


          iterator = condition.replaceAll("[>= 0-9]*","").trim();
          for (String element: loopBody) {
            element = element.replace("\r", "");
            if (element.contains(iterator + "--")
                    || element.contains("--" + iterator)
                    || element.matches(iterator + "[ -= 0-9]*")
                    || element.matches("[ ]*" + iterator + "[ ]*" + "=" + "[ ]*"
                        + iterator + "[ ]*" + "-" + "[ ]*" + "[0-9]*" + "[ ]*" + ";")) {
                sb.append("Do While Loop present, No infinite loops");
                dwFlag = true;
                break;
            }
          }
          if (!dwFlag) {
            sb.append("Infinite Loop Possible");
          }

        }
        else if (condition.contains("<") || condition.contains("<=")) {

          iterator = condition.replaceAll("[<= 0-9]*","").trim();
          for (String element: loopBody) {
            element = element.replace("\r", "");
            if (element.contains(iterator + "++")
                    || element.contains("++" + iterator)
                    || element.matches(iterator + "[ += 0-9]*")
                    || element.matches("[ ]*" + iterator + "[ ]*" + "=" + "[ ]*"
                    + iterator + "[ ]*" + "+" + "[ ]*" + "[0-9]*" + "[ ]*" + ";")) {
              sb.append("Do While Loop present, No infinite loops");
              dwFlag = true;
              break;
            }
          }
          if (!dwFlag) {
            sb.append("Infinite Loop Possible");
          }
        }

        else {
        }

      }
    }
    if (whileLoops.size() == 0) {
      sb.append("No While Loops in Code.\n");
    }
    else {
    }
    if (forLoops.size() == 0) {
      sb.append("No For Loops in Code.\n");
    }
    else {
    }

    return sb.toString();
  }
}
