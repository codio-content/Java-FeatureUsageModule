package com.codio.feature_usage_mod.controller;

//import statements

import java.util.Scanner;
import java.util.function.Consumer;

import com.codio.feature_usage_mod.controller.features.datastructures.Arrays;
import com.codio.feature_usage_mod.model.ICommandDesignModel;
import com.codio.feature_usage_mod.view.IView;

public class FeatureUsageController implements IController, Consumer<String> {

  /*TODO: After making the Feature set, Make a Commands directory with all these Features as an independent
          Java file with corresponding code to be executed in the switch case*/

  private ICommandDesignModel model;
  private IView view;

  public FeatureUsageController(ICommandDesignModel model, IView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public String processCommand(String command) {
    Scanner s = new Scanner(command);
    IFeatures cmd = null;

    while (s.hasNext()) {
      String in = s.next();

      switch (in) {
        case "arrays":
          cmd = new Arrays();
          break;
        case "classes":
          break;
        case "composition":
          break;
        case "constructors":
          break;
        case "datatypes":
          break;
        case "dowhile":
          break;
        case "fileio":
          break;
        case "for":
          break;
        case "foreach":
          break;
        case "functions":
          break;
        case "hastables":
          break;
        case "if":
          break;
        case "infiniteloops":
          break;
        case "inheritance":
          break;
        case "libraryusage":
          break;
        case "lists":
          break;
        case "maps":
          break;
        case "methods":
          break;
        case "methodoverloading":
          break;
        case "methodoverriding":
          break;
        case "objects":
          break;
        case "recursion":
          break;
        case "stacks":
          break;
        case "standardio":
          break;
        case "streamreaders":
          break;
        case "switch":
          break;
        case "trees":
          break;
        case "variables":
          break;
        case "vectors":
          break;
        case "while":
          break;


        //TODO: Write the cases possible for the feature set

      }
    }

    return null;
  }


  @Override
  public void go() {

  }

  @Override
  public void accept(String s) {
    String command = s;
    String status = "";

    try {
      status = processCommand(command);
    } catch (Exception ex) {
      //TODO: View function to throw error message on console
    }
  }
}
