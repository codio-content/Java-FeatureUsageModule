package com.codio.feature_usage_mod.controller.features.techniques;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.WhileStmt;

import java.util.List;

public class InfiniteLoops {

//TODO: WHile and doWhile get the same output from the last thread in the function - that a do while loop is present. Change needed.
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
        String[] loopBody = loop.toString().split("\n");
        String condition = loop.getCondition().toString();

        sb.append(checkOperatorInWhileCondition(loopBody, condition));
      }
    }
    if (whileLoops.size() == 0) {
      sb.append("No While Loops in Code.\n");
    } else {
      for (WhileStmt loop : whileLoops) {
        String[] loopBody = loop.toString().split("\n");
        String condition = loop.getCondition().toString();

        sb.append(checkOperatorInWhileCondition(loopBody, condition));
      }
    }
    if (forLoops.size() == 0) {
      sb.append("No For Loops in Code.\n");
    } else {
//      System.out.println(forLoops);
      for (ForStmt loop : forLoops) {
        String initialization = loop.getInitialization().toString();
        String condition = loop.getCompare().toString().replace("Optional", "");
        String update = loop.getUpdate().toString();
        System.out.println(initialization +"\n"+condition+"\n"+update);

        int initialValue = Integer.parseInt(initialization.replaceAll("[a-z a-zA-z]*"
                + "[ = ]*", ""));
        int conditionValue = Integer.parseInt(condition.replaceAll("[a-zA-z ]*" + "[<>= ]*", ""));

        String conditionOperator = condition.replaceAll("[\\[a-zA-z ]* +[^<>=]*" + "[ 0-9\\]]*","");
        //sb.append(checkOperatorInWhileCondition(loopBody, condition));
      }
    }

    return sb.toString();
  }

  private String checkOperatorInWhileCondition(String[] loopBody, String condition) {
    if (condition.contains(">")) {
      return setCheckerForIteratorUpdate(loopBody, condition, ">");
    } else if (condition.contains(">=")) {
      return setCheckerForIteratorUpdate(loopBody, condition, ">=");
    } else if (condition.contains("<")) {
      return setCheckerForIteratorUpdate(loopBody, condition, "<");
//      System.out.println(sb.toString());
    } else if (condition.contains("<=")) {
      return setCheckerForIteratorUpdate(loopBody, condition, "<=");
    } else {
      return setCheckerForIteratorUpdate(loopBody, condition, "==");
    }


  }

  private String setCheckerForIteratorUpdate(String[] loopBody, String condition, String operator) {

    String op;

    if (operator.contains(">")) {
      op = "-";
      return checkForInfiniteLoops(loopBody, condition, operator, op);
    } else if (operator.contains("<")) {
      op = "+";
      return checkForInfiniteLoops(loopBody, condition, operator, op);
    } else {
      op = "-";
      String message = checkForInfiniteLoops(loopBody, condition, operator, op);
      if (message.contains("No infinite loops")) {
        return message;
      } else {
        op = "+";
        return checkForInfiniteLoops(loopBody, condition, operator, op);
      }
    }


  }

  private String checkForInfiniteLoops(String[] loopBody, String condition, String operator,
                                       String op) {

    boolean flag = false;
    String iterator = "";
    StringBuilder sb = new StringBuilder();
    iterator = condition.replaceAll(operator + "[ 0-9]*", "").trim();
    for (String element : loopBody) {
      element = element.replace("\r", "");
      if (element.contains(iterator + op + op)
              || element.contains(op + op + iterator)
              || element.matches(iterator + "[ ]*" + op + "[= 0-9]*")
              || element.matches("[ ]*" + iterator + "[ ]*" + "=" + "[ ]*"
              + iterator + "[ ]*" + op + "[ ]*" + "[0-9]*" + "[ ]*" + ";")) {
        sb.append("Do While Loop present, No infinite loops\n");
        flag = true;
        break;
      }
    }
    if (!flag) {
      sb.append("Infinite Loop Possible\n");
    }


    return sb.toString();


  }
}
