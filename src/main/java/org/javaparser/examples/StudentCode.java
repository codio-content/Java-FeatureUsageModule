package org.javaparser.examples;

import java.util.Hashtable;

public class StudentCode {

  public StudentCode(){}

  StudentCode(String arg){}

  private StudentCode(double arg) {}

  public static void main(String args) {
    //String cute = "";
    int x = 10;
    String[] c = new String[5];
    System.out.println(x);

    for(int i = 0; i < 10; i++) {
      x = x + 10;
    }

    Hashtable<String, String> web = new Hashtable<>();
    web.put("Alpha", "Beta");
//    do {
//      x = x - 1;
//    }while(x > 5);

//    if(x > 5) {
//      System.out.println("a");
//    }
//    else if(x == 5){
//      System.out.println("b");
//    }
//    else {
//      System.out.println("c");
//    }
  }

}
