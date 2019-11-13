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

      for (int i = 0; i < 10; i ++) {
        for (int j = 0; j < 100; j ++) {
          System.out.println("oooooooo");
        }
        System.out.println("yaaaaaaaas");
      }
    }
  }


}