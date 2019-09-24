package com.codio.feature_usage_mod.view;


/**
 * The interface for our view class.
 */

public interface IView {

  /**
   * Reads in next input.
   * @return next input
   */
  String getNextInput();

  /**
   * Displays output.
   */
  void show(Appendable out);

}
