package org.examples;

public class ConstructsTest{

  ConstructsTest(String arg) {
  }

  private ConstructsTest() {
  }

  public static class ConstructsSubClass{

    ConstructsSubClass(int offset) {
    }
  }
}