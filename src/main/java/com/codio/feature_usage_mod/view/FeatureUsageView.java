package com.codio.feature_usage_mod.view;

import java.util.Scanner;

public class FeatureUsageView implements IView {

  private Scanner scanner;

  /**
   * Constructor for portfolio manager view.
   *
   * @param in Readable input
   */
  public FeatureUsageView(Readable in) {
    this.scanner = new Scanner(in);
  }

  /**
   * Displays output to console.
   *
   * @param out output
   */
  public void show(Appendable out) {
    System.out.println(out.toString());
  }

  /**
   * Reads in next input.
   * @return next input
   */
  public String getNextInput() {
    if (scanner.hasNext()) {
      return scanner.next();
    }
    else {
      throw new IllegalArgumentException("Input cannot be null");
    }
  }
}
