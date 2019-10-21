package com.codio.feature_usage_mod.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.codio.feature_usage_mod.controller.features.constructs.Classes;
import com.codio.feature_usage_mod.controller.features.constructs.Constructors;
import com.codio.feature_usage_mod.controller.features.constructs.DataTypes;
import com.codio.feature_usage_mod.controller.features.constructs.DoWhile;
import com.codio.feature_usage_mod.controller.features.constructs.For;
import com.codio.feature_usage_mod.controller.features.constructs.ForEach;
import com.codio.feature_usage_mod.controller.features.constructs.FunctionReturnTypes;
import com.codio.feature_usage_mod.controller.features.constructs.IfConditionals;
import com.codio.feature_usage_mod.controller.features.constructs.Methods;
import com.codio.feature_usage_mod.controller.features.constructs.Objects;
import com.codio.feature_usage_mod.controller.features.constructs.Strings;
import com.codio.feature_usage_mod.controller.features.constructs.Switch;
import com.codio.feature_usage_mod.controller.features.constructs.While;
import com.codio.feature_usage_mod.controller.features.datastructures.ArrayDeques;
import com.codio.feature_usage_mod.controller.features.datastructures.Arrays;
import com.codio.feature_usage_mod.controller.features.datastructures.HashMaps;
import com.codio.feature_usage_mod.controller.features.datastructures.HashSets;
import com.codio.feature_usage_mod.controller.features.datastructures.HashTables;
import com.codio.feature_usage_mod.controller.features.datastructures.LinkedHashMaps;
import com.codio.feature_usage_mod.controller.features.datastructures.LinkedHashSets;
import com.codio.feature_usage_mod.controller.features.datastructures.LinkedLists;
import com.codio.feature_usage_mod.controller.features.datastructures.ArrayLists;
import com.codio.feature_usage_mod.controller.features.datastructures.PriorityQueues;
import com.codio.feature_usage_mod.controller.features.datastructures.Stacks;
import com.codio.feature_usage_mod.controller.features.datastructures.TreeMaps;
import com.codio.feature_usage_mod.controller.features.datastructures.TreeSets;
import com.codio.feature_usage_mod.controller.features.datastructures.Vectors;
import com.codio.feature_usage_mod.controller.features.techniques.InfiniteLoops;
import com.codio.feature_usage_mod.controller.features.techniques.Inheritance;
import com.codio.feature_usage_mod.controller.features.techniques.LibraryUsage;
import com.codio.feature_usage_mod.controller.features.techniques.MethodOverloading;
import com.codio.feature_usage_mod.controller.features.techniques.MethodOverriding;
import com.codio.feature_usage_mod.controller.features.techniques.Recursion;
import com.codio.feature_usage_mod.controller.features.techniques.StandardInputOutput;
import com.codio.feature_usage_mod.view.IView;
import com.github.javaparser.ast.CompilationUnit;


public class FeatureUsageController implements IController {

  private IView view;
  private CompilationUnit cu;

  public FeatureUsageController(IView view, CompilationUnit cu) {
    this.view = view;
    this.cu = cu;
  }

  @Override
  public void start() {

    StringBuffer sb = new StringBuffer();
    sb.append("Welcome to Symonn's Feature Usage Module.\n"
            + "What do you want to check in your students' code ?\n"
            + "1. constructs\n"
            + "2. data structures\n"
            + "3. techniques\n"
            + "4. full report\n"
            + "5. exit\n");
    appendToAppendableAndDisplay(sb);

    String category = view.getNextInput();

    switch (category) {
      case "constructs":
        constructsSwitchCase();
        break;
      case "ds":
        dataStructuresSwitchCase();
        break;
      case "techniques":
        techniquesSwitchCase();
        break;
      case "exit":
        return;
      default:
        sb.append("Incorrect option. Please choose again.").append("\n");
        appendToAppendableAndDisplay(sb);
    }
    start();
  }

  private void constructsSwitchCase() {
    String message = "";
    String choice = "";
    StringBuffer buffer;
    StringBuffer sb = new StringBuffer();
    sb.append("Please enter one of the following options in lowercase:\n"
            + "1. Classes\n"
            + "2. Constructors\n"
            + "3. DataTypes\n"
            + "4. DoWhile\n"
            + "5. For\n"
            + "6. ForEach\n"
            + "7. FunctionReturnTypes\n"
            + "8. IfConditionals\n"
            + "9. Methods\n"
            + "10. Objects\n"
            + "11. Strings\n"
            + "12. Switch\n"
            + "13. Variables\n"
            + "14. While\n");
    appendToAppendableAndDisplay(sb);

    String option = view.getNextInput();
    if (option == null) {
      return;
    }
    switch (option) {
      case "classes":
        message = new Classes().visit(cu, null) + "\n";
        break;

      case "constructors":
        List<String> constructorList = new ArrayList<>();
        buffer = new StringBuffer();
        new Constructors().visit(cu, constructorList);
        if (constructorList.size() == 0) {
          message = "No constructors in code";
        } else {
          constructorList.forEach(n -> buffer.append("Constructor name: ").append(n).append("\n"));
          message = buffer.toString();
        }
        break;

      case "datatypes":

        sb = new StringBuffer();
        sb.append("Do you want to check for a specific datatype and variable ?\n"
                + "Y/N?");
        appendToAppendableAndDisplay(sb);

        choice = view.getNextInput();
        if (choice == null) {
          return;
        }

        switch (choice) {
          case "Y":
            sb = new StringBuffer();
            sb.append("Enter the data type and variable name you want to check \n");
            appendToAppendableAndDisplay(sb);
            String dataType = view.getNextInput();
            String variableName = view.getNextInput();
            message = new DataTypes().processSpecificCase(cu, dataType, variableName);
            break;
          case "N":
            message = new DataTypes().processGeneralCase(cu);
            break;
        }
        break;

      case "dowhile":
        message = new DoWhile().visit(cu, null);
        message = checkForNullPointerException(message);
        break;

      case "for":
        message = new For().visit(cu, null);
        message = checkForNullPointerException(message);

        break;

      case "foreach":
        message = new ForEach().visit(cu, null);
        message = checkForNullPointerException(message);

        break;

      case "functionreturntypes":
        sb = new StringBuffer();
        sb.append("Do you want to check for a specific function and return type ?\n"
                + "Y/N?");
        appendToAppendableAndDisplay(sb);

        choice = view.getNextInput();
        if (choice == null) {
          return;
        }

        switch (choice) {
          case "Y":
            sb = new StringBuffer();
            sb.append("Enter the return type and function name you want to check \n");
            appendToAppendableAndDisplay(sb);
            String returnType = view.getNextInput();
            String functionName = view.getNextInput();
            message = new DataTypes().processSpecificCase(cu, returnType, functionName);
            break;
          case "N":
            message = new FunctionReturnTypes().processGeneralCase(cu);
            break;
        }
        break;

      case "ifconditionals":
        message = new IfConditionals().process(cu);
        break;

      case "methods":
        List<String> methodsList = new ArrayList<>();
        buffer = new StringBuffer();
        new Methods().visit(cu, methodsList);
        if (methodsList.size() == 0) {
          message = "No methods in code";
        } else {
          methodsList.forEach(n -> buffer.append("Method name: ").append(n).append("\n"));
          message = buffer.toString();
        }
        break;

      case "objects":
        message = new Objects().visit(cu, null);
        if (message == null) {
          message = "No Object creation expressions in code in code";
        }
        break;

      case "strings":
        message = new Strings().visit(cu, null);
        if (message == null) {
          message = "No String literals in code";
        }
        break;

      case "switch":
        message = new Switch().visit(cu, null);
        message = checkForNullPointerException(message);
        break;

      case "variables":
        //local, global, private, public, instance
        break;

      case "while":
        message = new While().visit(cu, null);
        message = checkForNullPointerException(message);
        break;
    }
    appendToAppendableAndDisplay(new StringBuffer().append(message));

  }

  private void dataStructuresSwitchCase() {

    String message = "";
    StringBuffer sb;
    sb = new StringBuffer();
    sb.append("Please enter one of the following options in lowercase:\n"
            + "1. ArrayDeques\n"
            + "2. ArrayLists\n"
            + "3. Arrays\n"
            + "4. Graphs\n"
            + "5. HashMaps\n"
            + "6. HashSets\n"
            + "7. HashTables\n"
            + "8. LinkedHashMaps\n"
            + "9. LinkedHashSets\n"
            + "10. LinkedLists\n"
            + "11. PriorityQueues\n"
            + "12. Stacks\n"
            + "13. TreeMaps\n"
            + "14. Trees\n"
            + "15. TreeSets\n"
            + "16. Vectors\n");
    appendToAppendableAndDisplay(sb);

    String option = view.getNextInput();
    if (option == null) {
      return;
    }

    sb = new StringBuffer();
    sb.append("Do you want to check for polymorphism?\n"
            + "Y/N?");
    appendToAppendableAndDisplay(sb);

    String choice = view.getNextInput();

    if (choice == null) {
      return;
    }
    if (!(choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("N"))) {
      return;
    }

    switch (option) {
      case "arraydeque":
        message = new ArrayDeques().process(cu, "ArrayDeque", choice);
        break;
      case "arraylists":
        message = new ArrayLists().process(cu, "ArrayList", choice);
        break;
      case "arrays":
        message = new Arrays().process(cu);
        break;
      case "graphs":
        //TODO: Think about it as Java doesn't have a Graph Class
        break;
      case "hashmaps":
        message = new HashMaps().process(cu, "HashMap", choice);
        break;
      case "hashset":
        message = new HashSets().process(cu, "HashSet", choice);
        break;
      case "hashtables":
        message = new HashTables().process(cu, "Hashtable");
        break;
      case "linkedhashmap":
        message = new LinkedHashMaps().process(cu, "LinkedHashMap", choice);
        break;
      case "linkedhashset":
        message = new LinkedHashSets().process(cu, "LinkedHashSet", choice);
        break;
      case "linkedlists":
        message = new LinkedLists().process(cu, "LinkedList", choice);
        break;
      case "priorityqueues":
        message = new PriorityQueues().process(cu, "PriorityQueue");
        break;
      case "stacks":
        message = new Stacks().process(cu, "Stack", choice);
        break;
      case "treemaps":
        message = new TreeMaps().process(cu, "TreeMap");
        break;
      case "trees":
        //TODO: Think about it as Java Library doesn't have a Tree Class
        break;
      case "treeset":
        message = new TreeSets().process(cu, "TreeSet", choice);
        break;
      case "vectors":
        message = new Vectors().process(cu, "Vector", choice);
        break;

    }
    appendToAppendableAndDisplay(new StringBuffer().append(message).append("\n"));

  }

  private void techniquesSwitchCase() {
    String message = "";
    StringBuffer sb;
    sb = new StringBuffer();
    sb.append("Please enter one of the following options in lowercase:\n"
            + "1. Composition\n"
            + "2. FileIO\n"
            + "3. InfiniteLoops\n"
            + "4. Inheritance\n"
            + "5. LibraryUsage\n"
            + "6. MethodOverloading\n"
            + "7. MethodOverriding\n"
            + "8. Recursion\n"
            + "9. StdIO\n"
            + "10. StreamReaders\n");
    appendToAppendableAndDisplay(sb);

    String option = view.getNextInput();
    if (option == null) {
      return;
    }

    switch (option) {

      case "composition":
        break;
      case "fileio":
        //TODO: Friday
        break;
      case "infiniteloops":
        message = new InfiniteLoops().process(cu);
        break;
      case "inheritance":
        message = new Inheritance().process(cu);
        break;
      case "libraryusage":
        sb = new StringBuffer();
        sb.append("Enter the required library (import statement) to be verified in Student Code");
        appendToAppendableAndDisplay(sb);
        String libraryName = view.getNextInput();
        message = new LibraryUsage().process(cu, libraryName);
        break;

      case "methodoverloading":
        Stack<String> methodCalls = new Stack<>();
        MethodOverloading mo = new MethodOverloading();
        mo.visit(cu, methodCalls);
        message = mo.process(methodCalls);
        break;

      case "methodoverriding":
        message = new MethodOverriding().process(cu);
        break;
      case "recursion":
        message = new Recursion().process(cu);
        break;
      case "stdio":
        message = new StandardInputOutput().process(cu);
        break;
      case "streamreaders":
        //TODO: Friday
        break;
      default:


    }
    appendToAppendableAndDisplay(new StringBuffer().append(message).append("\n"));

  }


  /**
   * Appends output buffer to appendable and displays to user.
   *
   * @param message message to be displayed
   */
  private void appendToAppendableAndDisplay(StringBuffer message) {
    Appendable out = new StringBuffer();
    try {
      out.append(message);
    } catch (IOException e) {
      e.printStackTrace();
    }
    view.show(out);
  }

  private String checkForNullPointerException(String message) {

    try {
      if (message.equals("true")) {
        message = "Yes";
      }
    } catch (NullPointerException ne) {
      message = "No";
    }
    return message;
  }
}
