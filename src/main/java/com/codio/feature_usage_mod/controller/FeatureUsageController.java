package com.codio.feature_usage_mod.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.codio.feature_usage_mod.controller.features.constructs.Classes;
import com.codio.feature_usage_mod.model.IModel;
import com.codio.feature_usage_mod.view.IView;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

public class FeatureUsageController implements IController {

  /*TODO: After making the Feature set, Make a Commands directory with all these Features as an independent
          Java file with corresponding code to be executed in the switch case*/

  private IModel model;
  private IView view;
  private CompilationUnit cu;


  public FeatureUsageController(IModel model, IView view, CompilationUnit cu)
          throws FileNotFoundException {
    this.model = model;
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
            + "3. techniques");
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
      default:
        sb.append("Incorrect option. Please choose again.").append("\n");
        appendToAppendableAndDisplay(sb);
    }
    start();
  }

  private void techniquesSwitchCase() {

  }

  private void datastructuresSwitchCase() {

  }

  private void constructsSwitchCase() {
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
      new Classes().visit(cu, null);
        break;
      case "constructors":
        break;
      case "datatypes":
        break;
      case "dowhile":
        break;
      case "for":
        break;
      case "foreach":
        break;
      case "functionreturntypes":
        break;
      case "ifconditionals":
        break;
      case "methods":
        break;
      case "objects":
        break;
      case "strings":
        break;
      case "switch":
        break;
      case "variables":
        break;
      case "while":
        break;
    }

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
