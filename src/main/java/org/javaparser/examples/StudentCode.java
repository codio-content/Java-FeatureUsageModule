package org.javaparser.examples;


import com.codio.feature_usage_mod.support.Job;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Scanner;

public class StudentCode extends Office {

  //  int number = 99999;
//  String sentence = "whoa so cool";
//  float fPointNumber = 5.0f;
//  double decimal = 8.0;
//  boolean bool = true;
//  long bigNumber = 1000000000;
  short smallNumber = 10000;
  byte idk = 7;
  char character = 'j';
  static String message = "";

  private Job job;
  private Office office;

  private Scanner scanner;

  public StudentCode() {
  }

  StudentCode(String arg) {
  }

  private StudentCode(double arg) {
  }

  void check() {
    job.setRole("CEO");
  }

  public String checkWebsite(int arg, int num) {
    //return super.checkWebsite(arg, num);
    scanner = new Scanner(System.in);

    return "";
  }

  String dontCheck() {
    return "";
  }

  protected int dayumSon() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    br.readLine();
    return 666;
  }

  private void checkForRecursion(String message, int count) {
  }

  private void checkForRecursion(String message) {
    int x = 1;
    int y = 200;
//    for(int i = 0; i < 100; i ++) {
//      x = x + 1;
//      y = y - 1;
//    }
    if (x < y) {
      message = message + "i";
      checkForRecursion(message);
    }
  }

  public static void main(String args) {
    String cute = "";
    int x = 10;
//    String[] c = new String[5];
//    System.out.println(x);
//
    for (int i = 0; i < 10; i++) {
      x = x + 10;
    }

//    List<Integer> list1 = new ArrayList<>();
//    ArrayList<Integer> list2 = new ArrayList<>();
//
//    Hashtable<String, String> web = new Hashtable<>();
//    web.put("Alpha", "Beta");
//
//    LinkedList<Integer> linkedList = new LinkedList<>();
//    linkedList.add(5);
//
//    List<Double> list = new ArrayList<>();
//    list.add(7.0);
//
//    Map<Integer, Integer> map = new HashMap<>();
//
//    Map<String, Integer> maps = new TreeMap<>();
//
//    Queue<Integer> queue = new LinkedList<>();


    LinkedList<Integer> ll = new LinkedList<>();


//    for (;true;) {
//      System.out.println("Woot woot");
//    }

//    do {
//      x = x + 1;
//    }while(x <5 );


//     i <= 10; i++
// if(x > 5) {
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
