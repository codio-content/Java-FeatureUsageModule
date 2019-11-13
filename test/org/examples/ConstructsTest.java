package org.examples;

// Unit testing code file

/*
Successfully tested for:
Classes
Constructors
 */


public class ConstructsTest {

  ConstructsTest(String arg) {
  }

  private ConstructsTest() {
    int x = 0;

    do {
      x = x + 10;
    } while (x < 100);

  }

  public static class ConstructsSubClass {

    ConstructsSubClass(int offset) {
    }
  }


}