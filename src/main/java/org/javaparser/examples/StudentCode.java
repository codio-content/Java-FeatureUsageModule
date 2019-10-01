package org.javaparser.examples;

public class StudentCode {

  public StudentCode(){}

  StudentCode(String arg){}

  private StudentCode(double arg) {}

  public static void main(String args) {
    int x;
    x = 10;
    System.out.println(x);

//    for(int i = 0; i < 10; i++) {
//      x = x + 10;
//    }

    do {
      x = x - 1;
    }while(x > 5);

  }

}
