package com.codio.feature_usage_mod.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.codio.feature_usage_mod.controller.features.constructs.Classes;
import com.codio.feature_usage_mod.controller.features.constructs.Constructors;
import com.codio.feature_usage_mod.controller.features.constructs.DoWhile;
import com.codio.feature_usage_mod.controller.features.constructs.For;
import com.codio.feature_usage_mod.controller.features.constructs.ForEach;
import com.codio.feature_usage_mod.controller.features.constructs.IfConditionals;
import com.codio.feature_usage_mod.controller.features.constructs.Objects;
import com.codio.feature_usage_mod.controller.features.constructs.Strings;
import com.codio.feature_usage_mod.controller.features.constructs.Switch;
import com.codio.feature_usage_mod.controller.features.constructs.While;
import com.codio.feature_usage_mod.view.IView;
import com.github.javaparser.ast.CompilationUnit;


public class FeatureUsageController implements IController {

  /*TODO: After making the Feature set, Make a Commands directory with all these Features as an independent
          Java file with corresponding code to be executed in the switch case*/

  private IView view;
  private CompilationUnit cu;


  public FeatureUsageController( IView view, CompilationUnit cu) {
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
            + "4. exit\n");
    appendToAppendableAndDisplay(sb);

    String category = view.getNextInput();

    switch (category) {
      case "constructs":
        constructsSwitchCase();
        break;
      case "data structures":
        datastructuresSwitchCase();
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
        if(constructorList.size() == 0) {
          message = "No constructors in code";
        }
        else {
          constructorList.forEach(n -> buffer.append("Constructor name: ").append(n).append("\n"));
          message = buffer.toString();
        }
        break;

      case "datatypes":
        //primitive, etc. (int, char, double, ...)
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
        //check for return type of a particular method
        break;

      case "ifconditionals":
        message = new IfConditionals().process(cu);
        break;

      case "methods":
        List<String> methodsList = new ArrayList<>();
        buffer = new StringBuffer();
        new Constructors().visit(cu, methodsList);
        if(methodsList.size() == 0) {
          message = "No methods in code";
        }
        else {
          methodsList.forEach(n -> buffer.append("Method name: ").append(n).append("\n"));
          message = buffer.toString();
        }
        break;

      case "objects":
        message = new Objects().visit(cu, null);
        if(message == null){
          message = "No Object creation expressions in code in code";
        }
        break;

      case "strings":
        message = new Strings().visit(cu, null);
        if(message == null){
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


  private void techniquesSwitchCase() {

  }

  private void datastructuresSwitchCase() {

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
    }catch (NullPointerException ne) {
      message = "No";
    }
    return message;
  }
}
