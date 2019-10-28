package com.codio.feature_usage_mod.controller.features.techniques;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.WhileStmt;

import java.util.List;

public class InfiniteLoops {

//TODO: While and doWhile get the same output from the last thread in the function - that a do while loop is present. Change needed.

  //TODO: For Loop Infinite Loop Case

  public InfiniteLoops() {
  }

  public String process(CompilationUnit cu) {

    StringBuilder sb = new StringBuilder();
    List<DoStmt> doWhileLoops = cu.findAll(DoStmt.class);
    List<WhileStmt> whileLoops = cu.findAll(WhileStmt.class);
    List<ForStmt> forLoops = cu.findAll(ForStmt.class);


    if (doWhileLoops.size() == 0) {
      sb.append("No Do While Loops in Code.\n");
    } else {
      for (DoStmt loop : doWhileLoops) {
        sb.append(isInfiniteLoop("Do While", loop));
      }
    }
    if (whileLoops.size() == 0) {
      sb.append("No While Loops in Code.\n");
    } else {
      for (WhileStmt loop : whileLoops) {
        sb.append(isInfiniteLoop("While", loop));
      }
    }
    if (forLoops.size() == 0) {
      sb.append("No For Loops in Code.\n");
    } else {
      for (ForStmt loop : forLoops) {
        sb.append(isInfiniteLoop("For", loop));
      }
    }

    return sb.toString();
  }

  private String isInfiniteLoop(String loopType, Statement loop) {

    String condition;
    String operator;
    String iteratorOp;
    String[] loopBody = loop.toString().split("\n");

    if (loopType.matches("Do While")) {
      condition = loop.asDoStmt().getCondition().toString();
    }
    else if (loopType.matches("While")) {
      condition = loop.asWhileStmt().getCondition().toString();
    }
    else {
      ForStmt forLoop = loop.asForStmt();
      condition = forLoop.getCompare().toString().replace("Optional", "");
      String initialization = forLoop.getInitialization().toString();
      String iterator = forLoop.getUpdate().toString();
      operator = checkOperatorInCondition(condition);
      iteratorOp = setCheckerForIteratorUpdate(operator);
      return checkForInfiniteLoops(initialization, condition, iterator, operator, iteratorOp);
    }

    operator = checkOperatorInCondition(condition);
    iteratorOp = setCheckerForIteratorUpdate(operator);
    return checkForInfiniteLoops(loopType, loopBody, condition, operator, iteratorOp);
  }

  private String checkOperatorInCondition(String condition) {
    if (condition.contains(">")) {
      return ">";
    } else if (condition.contains(">=")) {
      return ">=";
    } else if (condition.contains("<")) {
      return "<";
    } else if (condition.contains("<=")) {
      return  "<=";
    } else {
      return "==";
    }
  }

  private String setCheckerForIteratorUpdate(String operator) {

    String op;

    if (operator.contains(">")) {
      op = "-";
      return op;
    } else if (operator.contains("<")) {
      op = "+";
      return op;
    } else {
      op = "=";
      return op;
    }
  }

  private String checkForInfiniteLoops(String loopType, String[] loopBody, String condition,
                                       String operator, String op) {

    boolean flag = false;
    String iterator;
    StringBuilder sb = new StringBuilder();
    iterator = condition.replaceAll(operator + "[ 0-9]*", "").trim();
    for (String element : loopBody) {
      element = element.replace("\r", "");
      if (element.contains(iterator + op + op)
              || element.contains(op + op + iterator)
              || element.matches(iterator + "[ ]*" + op + "[= 0-9]*")
              || element.matches("[ ]*" + iterator + "[ ]*" + "=" + "[ ]*"
              + iterator + "[ ]*" + op + "[ ]*" + "[0-9]*" + "[ ]*" + ";")) {
        sb.append(loopType).append(" Loop present, No infinite loops\n");
        flag = true;
        break;
      }
    }
    if (!flag) {
      sb.append("Infinite Loop Possible\n");
    }
    return sb.toString();
  }

  private String checkForInfiniteLoops(String initialization, String condition, String iterator, String operator, String iteratorOp) {

    int initialValue = Integer.parseInt(initialization.replaceAll("[a-z a-zA-z]*"
            + "[ = ]*", ""));
    int conditionValue = Integer.parseInt(condition.replaceAll("[a-zA-z ]*" + "[<>= ]*", ""));

    String conditionOperator = condition.replaceAll("[\\[a-zA-z ]* +[^<>=]*" + "[ 0-9\\]]*","");

    return "";
  }
}
