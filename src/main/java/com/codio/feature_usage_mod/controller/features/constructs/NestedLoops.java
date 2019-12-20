package com.codio.feature_usage_mod.controller.features.constructs;

import com.codio.feature_usage_mod.controller.features.IConstructs;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.WhileStmt;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Java class to find Nested Loops in Student Code.
 */

//TODO: How to keep track of depth and String message for multiple nests in one loop

//TODO: new approach - Don't pass StringBuilder in recursion. Maintain depth and loop info in temp variables


public class NestedLoops implements IConstructs {

  private int depth;
  private int visitedCounter = 0;
  private Hashtable<Integer, String> tempData = new Hashtable<>();
  private Hashtable<Integer, List<String>> tableOfEverything = new Hashtable<>();
  private Hashtable<Integer, Statement> visited = new Hashtable<>();

  public NestedLoops() {
  }

  @SuppressWarnings({"unchecked"})
  public String process(CompilationUnit cu) {

    List<List> lists = new ArrayList<>();

    lists.add(cu.findAll(ForStmt.class));
    lists.add(cu.findAll(WhileStmt.class));
    lists.add(cu.findAll(DoStmt.class));

    List<Statement> allLoops = new ArrayList<>();

    for (List list : lists) {
      allLoops.addAll(list);
    }

    StringBuilder nestedLoop;
    for (Statement loop : allLoops) {

      if (visited.contains(loop)) {
        break;
      } else {
        visitedCounter++;
        visited.put(visitedCounter, loop);
        depth = 1;
        LoopType loopType = LoopType.valueOf(loop.getClass().getSimpleName());

        switch (loopType) {
          case ForStmt:
            nestedLoop = new StringBuilder();
            ForStmt forLoop = (ForStmt) loop;
            nestedLoop.append("For Loop -> \n\t");
            findNestedLoops(forLoop.getBody(), nestedLoop);
            break;
          case WhileStmt:
            nestedLoop = new StringBuilder();
            WhileStmt whileLoop = (WhileStmt) loop;
            nestedLoop.append("While Loop -> \n\t");
            findNestedLoops(whileLoop.getBody(), nestedLoop);
            break;
          case DoStmt:
            nestedLoop = new StringBuilder();
            DoStmt doWhileLoop = (DoStmt) loop;
            nestedLoop.append("Do While Loop -> \n\t");
            findNestedLoops(doWhileLoop.getBody(), nestedLoop);
            break;
        }
      }

    }
    return "";
  }

  private void findNestedLoops(Statement loopBody, StringBuilder nestedLoop) {

    tempData.put(depth, nestedLoop.toString());
    List<Node> children = loopBody.getChildNodes();
    boolean flag = false;
    for (Node child : children) {
      try {
        LoopType loopType = LoopType.valueOf(child.getClass().getSimpleName());
        switch (loopType) {
          case ForStmt:
            depth++;
            flag = true;
            ForStmt forLoop = (ForStmt) child;
            nestedLoop.append("For Loop -> ");
            findNestedLoops(forLoop.getBody(), nestedLoop);
            break;
          case DoStmt:
            depth++;
            flag = true;
            DoStmt doWhileLoop = (DoStmt) child;
            nestedLoop.append("Do While Loop -> ");
            findNestedLoops(doWhileLoop.getBody(), nestedLoop);
            break;
          case WhileStmt:
            depth++;
            flag = true;
            WhileStmt whileLoop = (WhileStmt) child;
            nestedLoop.append("While Loop -> ");
            findNestedLoops(whileLoop.getBody(), nestedLoop);
            break;
          default:
            break;
        }
      } catch (IllegalArgumentException e) {
        //Intentionally left blank as we want to skip all cases where statements are not loops.
      }
    }
    if (!flag) {
      invokeVariableInitializer(depth, nestedLoop.toString());
    }
  }

  private void invokeVariableInitializer(int depth, String nestedLoop) {
    if (tableOfEverything.containsKey(depth)) {
      List<String> newListOfLoops = tableOfEverything.get(depth);
      newListOfLoops.add(nestedLoop);
      tableOfEverything.replace(depth, newListOfLoops);
    } else {
      List<String> listOfLoops = new ArrayList<>();
      listOfLoops.add(nestedLoop);
      tableOfEverything.put(depth, listOfLoops);
    }

    tempData.remove(depth, nestedLoop);
    this.depth--;
  }
}

enum LoopType {
  ForStmt, DoStmt, WhileStmt
}

