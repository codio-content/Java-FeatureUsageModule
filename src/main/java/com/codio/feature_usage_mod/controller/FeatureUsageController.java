package com.codio.feature_usage_mod.controller;

import com.codio.feature_usage_mod.controller.features.constructs.Classes;
import com.codio.feature_usage_mod.controller.features.constructs.Constructors;
import com.codio.feature_usage_mod.controller.features.constructs.DataTypes;
import com.codio.feature_usage_mod.controller.features.constructs.DoWhile;
import com.codio.feature_usage_mod.controller.features.constructs.For;
import com.codio.feature_usage_mod.controller.features.constructs.ForEach;
import com.codio.feature_usage_mod.controller.features.constructs.IfConditionals;
import com.codio.feature_usage_mod.controller.features.constructs.MethodReturnTypes;
import com.codio.feature_usage_mod.controller.features.constructs.Methods;
import com.codio.feature_usage_mod.controller.features.constructs.NestedLoops;
import com.codio.feature_usage_mod.controller.features.constructs.Objects;
import com.codio.feature_usage_mod.controller.features.constructs.Strings;
import com.codio.feature_usage_mod.controller.features.constructs.Switch;
import com.codio.feature_usage_mod.controller.features.constructs.Throws;
import com.codio.feature_usage_mod.controller.features.constructs.TryCatch;
import com.codio.feature_usage_mod.controller.features.constructs.Variables;
import com.codio.feature_usage_mod.controller.features.constructs.While;
import com.codio.feature_usage_mod.controller.features.datastructures.ArrayDeques;
import com.codio.feature_usage_mod.controller.features.datastructures.ArrayLists;
import com.codio.feature_usage_mod.controller.features.datastructures.Arrays;
import com.codio.feature_usage_mod.controller.features.datastructures.HashMaps;
import com.codio.feature_usage_mod.controller.features.datastructures.HashSets;
import com.codio.feature_usage_mod.controller.features.datastructures.HashTables;
import com.codio.feature_usage_mod.controller.features.datastructures.LinkedHashMaps;
import com.codio.feature_usage_mod.controller.features.datastructures.LinkedHashSets;
import com.codio.feature_usage_mod.controller.features.datastructures.LinkedLists;
import com.codio.feature_usage_mod.controller.features.datastructures.PriorityQueues;
import com.codio.feature_usage_mod.controller.features.datastructures.Stacks;
import com.codio.feature_usage_mod.controller.features.datastructures.TreeMaps;
import com.codio.feature_usage_mod.controller.features.datastructures.TreeSets;
import com.codio.feature_usage_mod.controller.features.datastructures.Vectors;
import com.codio.feature_usage_mod.controller.features.techniques.Composition;
import com.codio.feature_usage_mod.controller.features.techniques.InfiniteLoops;
import com.codio.feature_usage_mod.controller.features.techniques.Inheritance;
import com.codio.feature_usage_mod.controller.features.techniques.LibraryUsage;
import com.codio.feature_usage_mod.controller.features.techniques.MethodOverloading;
import com.codio.feature_usage_mod.controller.features.techniques.MethodOverriding;
import com.codio.feature_usage_mod.controller.features.techniques.Recursion;
import com.codio.feature_usage_mod.controller.features.techniques.StandardInputOutput;
import com.codio.feature_usage_mod.view.IView;
import com.github.javaparser.ast.CompilationUnit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

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
    sb.append(getCategories());
    appendToAppendableAndDisplay(sb);

    String category = view.getNextInput();

    switch (category) {
      case "1":
        constructsSwitchCase();
        break;
      case "2":
        dataStructuresSwitchCase();
        break;
      case "3":
        techniquesSwitchCase();
        break;
      case "4":
        //full report
      case "5":
        return;
      default:
        sb.append("Incorrect option. Please choose again.").append("\n");
        appendToAppendableAndDisplay(sb);
    }
    start();
  }

  private String getCategories() {

    List<Path> featureCategories =
            getFilesFromDirectory("src/main/java/com/codio/feature_usage_mod/controller/features",
                    "directory");

    StringBuilder message = new StringBuilder();
    message.append("Welcome to Symonn's Feature Usage Module.\n"
            + "What do you want to check in your students' code ?\n");
    int index = 1;
    for (Path featureCategory : featureCategories) {
      message.append(index).append(". ").append(featureCategory.toString()).append("\n");
      index++;
    }
    message.append(index).append(". exit");
    return message.toString();
  }

  private void constructsSwitchCase() {
    String message = "";
    String choice;
    StringBuffer sb = new StringBuffer();
    sb.append(getConstructs());
    appendToAppendableAndDisplay(sb);

    String option = view.getNextInput();
    if (option == null) {
      return;
    }
    switch (option) {
      case "classes":
        message = new Classes().process(cu);
        break;

      case "constructors":
        message = new Constructors().process(cu);
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
        message = new DoWhile().process(cu);
        break;

      case "for":
        message = new For().process(cu);
        break;

      case "foreach":
        message = new ForEach().process(cu);
        break;

      case "methodreturntypes":
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
            message = new MethodReturnTypes().processSpecificCase(cu, returnType, functionName);
            break;
          case "N":
            message = new MethodReturnTypes().processGeneralCase(cu);
            break;
        }
        break;

      case "ifconditionals":
        message = new IfConditionals().process(cu);
        break;

      case "methods":
        message = new Methods().process(cu);
        break;

      case "nestedloops":
        message = new NestedLoops().process(cu);
        break;

      case "objects":
        message = new Objects().process(cu);
        break;

      case "strings":
        message = new Strings().process(cu);
        break;

      case "switch":
        message = new Switch().process(cu);
        break;

      case "throws":
        message = new Throws().process(cu);
        break;

      case "trycatch":
        message = new TryCatch().process(cu);
        break;

      case "variables":
        message = new Variables().process(cu);
        break;

      case "while":
        message = new While().process(cu);
        break;
    }
    appendToAppendableAndDisplay(new StringBuffer().append(message));

  }

  private String getConstructs() {

    List<Path> constructs =
            getFilesFromDirectory("src/main/java/com/codio/feature_usage_mod/controller/features/constructs",
                    "file");

    StringBuilder message = new StringBuilder();
    message.append("Please choose one of the following options:\n");
    int index = 1;
    for (Path construct: constructs) {
      message.append(index).append(". ").append(construct.toString()).append("\n");
      index++;
    }

    return message.toString();
  }


  private List<Path> getFilesFromDirectory(String path, String type) {

    List<Path> files = new ArrayList<>();
    try (Stream<Path> paths = Files.list(Paths.get(path))) {
      if (type.equals("directory")) {
        paths
                .filter(Files::isDirectory)
                .map(Path::getFileName)
                .forEach(files::add);
      } else {
        paths
                .filter(Files::isRegularFile)
                .map(Path::getFileName)
                .forEach(files::add);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return files;
  }

  private void dataStructuresSwitchCase() {

    String message = "";
    StringBuffer sb;
    sb = new StringBuffer();
    sb.append("Please enter one of the following options in lowercase:\n"
            + "1. ArrayDeques\n"
            + "2. ArrayLists\n"
            + "3. Arrays\n"
            + "4. HashMaps\n"
            + "5. HashSets\n"
            + "6. HashTables\n"
            + "7. LinkedHashMaps\n"
            + "8. LinkedHashSets\n"
            + "9. LinkedLists\n"
            + "10. PriorityQueues\n"
            + "11. Stacks\n"
            + "12. TreeMaps\n"
            + "13. TreeSets\n"
            + "14. Vectors\n");
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
    String choice = "";
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
        sb = new StringBuffer();
        sb.append("Do you want to check for a specific case of composition ?\n"
                + "Y/N?");
        appendToAppendableAndDisplay(sb);

        choice = view.getNextInput();
        if (choice == null) {
          return;
        }

        switch (choice) {
          case "Y":
            sb = new StringBuffer();
            sb.append("Enter the composition relationship: Super class has - a sub-class \n"
                    + "Super Class: ");
            appendToAppendableAndDisplay(sb);
            String superClass = view.getNextInput();
            appendToAppendableAndDisplay(new StringBuffer("\nhas - a \nSub Class: "));
            String subClass = view.getNextInput();
            message = new Composition().processSpecificCase(cu, superClass, subClass);
            break;
          case "N":
            message = new Composition().processGeneralCase(cu);
            break;
        }


        break;
      case "fileio":
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
}
