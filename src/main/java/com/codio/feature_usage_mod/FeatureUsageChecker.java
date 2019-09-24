package com.codio.feature_usage_mod;

import com.codio.feature_usage_mod.controller.FeatureUsageController;
import com.codio.feature_usage_mod.controller.IController;
import com.codio.feature_usage_mod.model.FeatureModel;
import com.codio.feature_usage_mod.model.IModel;
import com.codio.feature_usage_mod.view.FeatureUsageView;
import com.codio.feature_usage_mod.view.IView;

import java.io.InputStreamReader;

/**
 * This class contains the main method to run the program
 */
public class FeatureUsageChecker {

  //private static final String FILE_PATH = "src/main/java/org/javaparser/examples/StudentCode.java";

  public static void main(String args[]) throws Exception{
    // initialize Model
    // initialize Controller
    // initialize View
    // Pass Control to the Controller by calling the GO Method
    Readable in = new InputStreamReader(System.in);
    IModel model = new FeatureModel();
    IView view = new FeatureUsageView(in);
    String file_path = "src/main/java/org/javaparser/examples/StudentCode.java";
    IController controller = new FeatureUsageController(model, view, file_path);
    controller.start();

  }


}

