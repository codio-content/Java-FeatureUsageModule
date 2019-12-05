package com.codio.feature_usage_mod.controller.features.constructs;


import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.stmt.CatchClause;
import com.github.javaparser.ast.stmt.ThrowStmt;
import com.github.javaparser.ast.stmt.TryStmt;

import java.util.List;
import java.util.Optional;

public class TryCatch {

  public TryCatch() {}

  public String process(CompilationUnit cu) {

    List<TryStmt> tryBlocks = cu.findAll(TryStmt.class);
    int count = tryBlocks.size();
    return generateMessage(tryBlocks, count);
  }

  private String generateMessage(List<TryStmt> tryBlocks, int count) {

    StringBuilder sb = new StringBuilder();
    int outOfScopeTryBlocks = 0;
    int inScopeTryBlocks = 0;
    sb.append(count).append(" Try Blocks Found in Student Code!\n\n");
    for (TryStmt tryBlock : tryBlocks) {
      CallableDeclaration methodOrConstructor = locateTryBlock(tryBlock);
      if (methodOrConstructor == null) {
        outOfScopeTryBlocks++;
      } else {
        inScopeTryBlocks++;
        sb.append("Try Block ").append(inScopeTryBlocks).append(":\n");
        sb.append(getMethodOrConstructorName(methodOrConstructor));
        System.out.println(sb.toString());
        sb.append(checkForCatchBlocks(tryBlock));

        System.out.println(sb.toString());
      }
    }
    sb.append(outOfScopeTryBlocks).append(" Try Blocks are out of scope!\n");
    return sb.toString();
  }

  private String getMethodOrConstructorName(CallableDeclaration methodOrConstructor) {

    StringBuilder sb = new StringBuilder();
    String name = methodOrConstructor.getDeclarationAsString(true, false);
    if (methodOrConstructor.isConstructorDeclaration()) {
      sb.append("Try block found in a Constructor.\nConstructor Name: ")
              .append(name).append("\n");
    } else {
      sb.append("Try block found in a Method.\nMethod Name: ")
              .append(name).append("\n");
    }
    return sb.toString();
  }

  private String checkForCatchBlocks(TryStmt tryBlock) {

    StringBuilder sb = new StringBuilder();

    NodeList<CatchClause> catchBlocks = tryBlock.getCatchClauses();
    int count = catchBlocks.size();
    if (count == 1) {
      sb.append("1 Catch Block found for the corresponding Try Block.\n");
    } else {
      sb.append(count).append(" Catch Blocks found for the corresponding Try Block.\n");
    }
    sb.append(checkForExceptionHandlingInCatchBlocks(catchBlocks));

    return sb.toString();
  }

  private String checkForExceptionHandlingInCatchBlocks(NodeList<CatchClause> catchBlocks) {

    StringBuilder sb = new StringBuilder();

    int catchBlockIndex = 0;
    for (CatchClause catchBlock : catchBlocks) {
      catchBlockIndex++;
      sb.append("Catch Block ").append(catchBlockIndex).append(":\n");
      sb.append(checkForCaughtExceptions(catchBlock));
      sb.append(checkForThrowStatements(catchBlock));
    }
    return sb.toString();
  }

  private String checkForThrowStatements(CatchClause catchBlock) {
    List<ThrowStmt> throwStmts = catchBlock.findAll(ThrowStmt.class);
    int count = throwStmts.size();

    if (count == 0) {
      return "No Throw Statements Found!\n";
    } else if (count == 1) {
      return count + " Throw Statement Found!\n" + getThrowStatementData(throwStmts, count);
    } else {
      return count + " Throw Statements Found!\n" + getThrowStatementData(throwStmts, count);
    }
  }

  private String checkForCaughtExceptions(CatchClause catchBlock) {
    StringBuilder sb = new StringBuilder();
    sb.append("Exceptions Caught:\n");
    String[] exceptions = catchBlock.getParameter().toString().split("\\| *");
    int lastElement = exceptions.length - 1;
    exceptions[lastElement] = exceptions[lastElement].replaceAll(" [a-z0-9]*", "");
    for (String exception: exceptions) {
      sb.append(exception).append("\n");
    }
    return sb.toString();
  }

  private String getThrowStatementData(List<ThrowStmt> throwStmts, int count) {
    StringBuilder sb = new StringBuilder();
    if (count == 1) {
      sb.append("Exception thrown in the Catch Block:\n");
    } else {
      sb.append("Exceptions thrown in the Catch Block:\n");
    }
    for (ThrowStmt throwStmt : throwStmts) {
      sb.append(throwStmt.getExpression().toString()).append("\n");
    }
    return sb.toString();
  }

  private CallableDeclaration locateTryBlock(TryStmt tryBlock) {
    Optional<Node> parent = tryBlock.getParentNode();
    return parent.map(this::getParentMethodOrConstructor).orElse(null);
  }

  private CallableDeclaration getParentMethodOrConstructor(Node node) {
    if (node instanceof CallableDeclaration) {
      return ((CallableDeclaration) node);
    } else {
      return node.getParentNode().map(this::getParentMethodOrConstructor).orElse(null);
    }
  }
}
