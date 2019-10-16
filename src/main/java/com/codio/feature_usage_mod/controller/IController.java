package com.codio.feature_usage_mod.controller;

import java.io.FileNotFoundException;

/**
 * The controller interface for the FeatureChecker program. The functions here have been designed to
 * give control to the controller, and the primary operation for the controller to function (process
 * a feature command)
 */

public interface IController {

  /**
   * Start the program, i.e. give control to the controller
   */
  void start();

}
