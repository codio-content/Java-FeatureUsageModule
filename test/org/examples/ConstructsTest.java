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

  void ifConditionals() {
    double x = Math.random();
    if (x > 0) {
      if (x > 10) {
        if (x > 100) {
          if (x == 1000) {
            System.out.println("1000");
          } else {
            System.out.println("Not 1000");
          }
        } else {
          System.out.println("< 100");
        }
      }
      else if (x == 9) {
        System.out.println("9");
      }
      else if (x == 7) {
        System.out.println("7");
      }
      else if (x < 7) {
        if (x < 5) {
          if (x < 3) {
            if (x < 1) {
              System.out.println("0-1");
            } else {
              System.out.println("1-3");
            }
          } else {
            System.out.println("3-5");
          }
        }
        else {
          System.out.println("5-7");
        }
      }
      else {
        System.out.println("oops");
      }
    } else if (x < 0) {
      System.out.println("negative");
    } else if (x == 0) {
      System.out.println("0");
    } else {
      System.out.println("infinity");
    }


  }

  public static class ConstructsSubClass {

    ConstructsSubClass(int offset) {

      for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 100; j++) {
          System.out.println("oooooooo");
        }
        System.out.println("yaaaaaaaas");
      }
    }
  }


}