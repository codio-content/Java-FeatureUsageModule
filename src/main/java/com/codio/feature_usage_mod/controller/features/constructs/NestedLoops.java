package com.codio.feature_usage_mod.controller.features.constructs;

import com.codio.feature_usage_mod.controller.features.IConstructs;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.WhileStmt;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;

/**
 * Java class to find Nested Loops in Student Code.
 */

/*
TODO: NestedLoops - Help needed from Dev Team
Logic for this class
1. Make a list of all loops and add them to a hashtable with hashcode as key
2. Make a list of 'outer loops' i.e. loops at the level of depth = 1 from all methods
3. For each outerloop, find its child nodes and see if a loop is present
4. If loop found, record the type of loop and then in this loop, find nested loops as above
5. Final output of the method 'process' should be a the number of nested loops found, the depth
    of the nests and the structure as recorded
 */


public class NestedLoops implements IConstructs {

  List<List<String>> nestedLoops = new ArrayList<>();
  private Hashtable<Integer, Statement> loopsToVisit = new Hashtable<>();

  public NestedLoops() {
  }


  public String process(CompilationUnit cu) {

    List<List> listsOfLoops = findAllLoops(cu);
    addLoopsToHashTable(listsOfLoops);

    List<Statement> outerLoops = new ArrayList<>();

    List<MethodDeclaration> methods = cu.findAll(MethodDeclaration.class);
    for (MethodDeclaration method: methods) {
      outerLoops.addAll(findOuterLoops(method));
    }

    for (Statement loop: outerLoops) {
      List<String> nestedLoop = new ArrayList<>();
      String nest = getLoopTypeAsString(loop);
      nestedLoop.add(nest);
      findNestedLoops(loop, nestedLoop);
    }

    return "";

  }

  private String getLoopTypeAsString(Statement loop) {
    return loop.getClass().getSimpleName().replace("Stmt", "Loop");
  }

  private List<Statement> findOuterLoops(MethodDeclaration method) {

    List<Statement> outerLoops = new ArrayList<>();
    Optional<BlockStmt> methodBody = method.getBody();
    if (methodBody.isPresent()) {

      NodeList<Statement> childNodes = methodBody.get().getStatements();
      for (Statement child: childNodes) {

        if (statementIsALoop(child)){
          outerLoops.add(child);
        }
      }
    }
    return outerLoops;
  }

  private void findNestedLoops(Statement loop, List<String> nestedLoop) {

    BlockStmt loopBody = getLoopBody(loop);
    NodeList<Statement> childNodes = loopBody.getStatements();
    for (Statement child: childNodes) {

      if (statementIsALoop(child)){

        if (isLoopNotVisited(child)) {

          markLoopAsVisited(child);
          findNestedLoops(child, nestedLoop);
          nestedLoops.add(nestedLoop);
        }

      }
    }

  }

  private BlockStmt getLoopBody(Statement loop){
    Statement loopBody = null;
    try {
        LoopType loopType = LoopType.valueOf(loop.getClass().getSimpleName());
        switch (loopType) {
          case ForStmt:
            ForStmt forLoop = (ForStmt) loop;
            loopBody = forLoop.getBody();
            break;
          case DoStmt:
            DoStmt doWhileLoop = (DoStmt) loop;
            loopBody = doWhileLoop.getBody();
            break;
          case WhileStmt:
            WhileStmt whileLoop = (WhileStmt) loop;
            loopBody = whileLoop.getBody();
            break;
          case ForEach:
            ForEachStmt forEachLoop = (ForEachStmt) loop;
            loopBody = forEachLoop.getBody();
          default:
            break;
        }
      } catch (IllegalArgumentException e) {
        //Intentionally left blank as we want to skip all cases where statements are not loops.
      }
      return (BlockStmt) loopBody;
  }

  private void markLoopAsVisited(Statement loop) {
    loopsToVisit.remove(loop.hashCode());
  }

  private boolean isLoopNotVisited(Statement loop) {
    int uniqueHashcode = loop.hashCode();
    return loopsToVisit.containsKey(uniqueHashcode);
  }

  private boolean statementIsALoop(Statement child) {
    return child.isForStmt() || child.isForEachStmt() || child.isWhileStmt() || child.isDoStmt();
  }

  private List<List> findAllLoops(CompilationUnit cu) {

    List<List> listOfLists = new ArrayList<>();

    listOfLists.add(cu.findAll(ForStmt.class));
    listOfLists.add(cu.findAll(WhileStmt.class));
    listOfLists.add(cu.findAll(DoStmt.class));

    return listOfLists;
  }

  private void addLoopsToHashTable(List<List> listsOfLoops) {

    for (List<Statement> list: listsOfLoops) {
      for (Statement loop: list) {
        int uniqueHashcode = loop.hashCode();
        loopsToVisit.put(uniqueHashcode, loop);
      }
    }
  }
//  private void findNestedLoops(Statement loopBody, StringBuilder nestedLoop) {
//
//    tempData.put(depth, nestedLoop.toString());
//    List<Node> children = loopBody.getChildNodes();
//    boolean flag = false;
//    for (Node child : children) {
//      try {
//        LoopType loopType = LoopType.valueOf(child.getClass().getSimpleName());
//        switch (loopType) {
//          case ForStmt:
//            depth++;
//            flag = true;
//            ForStmt forLoop = (ForStmt) child;
//            nestedLoop.append("For Loop -> ");
//            findNestedLoops(forLoop.getBody(), nestedLoop);
//            break;
//          case DoStmt:
//            depth++;
//            flag = true;
//            DoStmt doWhileLoop = (DoStmt) child;
//            nestedLoop.append("Do While Loop -> ");
//            findNestedLoops(doWhileLoop.getBody(), nestedLoop);
//            break;
//          case WhileStmt:
//            depth++;
//            flag = true;
//            WhileStmt whileLoop = (WhileStmt) child;
//            nestedLoop.append("While Loop -> ");
//            findNestedLoops(whileLoop.getBody(), nestedLoop);
//            break;
//          default:
//            break;
//        }
//      } catch (IllegalArgumentException e) {
//        //Intentionally left blank as we want to skip all cases where statements are not loops.
//      }
//    }
//    if (!flag) {
//      invokeVariableInitializer(depth, nestedLoop.toString());
//    }
//  }
//
//  private void invokeVariableInitializer(int depth, String nestedLoop) {
//    if (tableOfEverything.containsKey(depth)) {
//      List<String> newListOfLoops = tableOfEverything.get(depth);
//      newListOfLoops.add(nestedLoop);
//      tableOfEverything.replace(depth, newListOfLoops);
//    } else {
//      List<String> listOfLoops = new ArrayList<>();
//      listOfLoops.add(nestedLoop);
//      tableOfEverything.put(depth, listOfLoops);
//    }
//
//    tempData.remove(depth, nestedLoop);
//    this.depth--;
//  }
}

enum LoopType {
  ForStmt, DoStmt, WhileStmt, ForEach
}


