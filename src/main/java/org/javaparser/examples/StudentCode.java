package org.javaparser.examples;


import com.codio.feature_usage_mod.support.Office;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class StudentCode extends Office {

  public StudentCode(){}

  StudentCode(String arg){}

  private StudentCode(double arg) {}

  void check(){

  }

  public String checkWebsite(int arg, int num) {
    //return super.checkWebsite(arg, num);
    return "";
  }

  String dontCheck() {
    return "";
  }
  protected int dayumSon(){
    return 666;
  }
  private void checkForRecursion(String message, int count) {
  }

  private void checkForRecursion(String message){
    int x = 1;
    int y = 200;
    for(int i = 0; i < 100; i ++) {
      x = x + 1;
      y = y - 1;
    }
    if (x<y) {
      message = message + "i";
      checkForRecursion(message);
    }
  }

  public static void main(String args) {
    //String cute = "";
    int x = 10;
//    String[] c = new String[5];
//    System.out.println(x);
//
//    for(int i = 0; i < 10; i++) {
//      x = x + 10;
//    }

    List<Integer> list1 = new ArrayList<>();
    ArrayList<Integer> list2 = new ArrayList<>();

    Hashtable<String, String> web = new Hashtable<>();
    web.put("Alpha", "Beta");

    LinkedList<Integer> linkedList = new LinkedList<>();
    linkedList.add(5);

    List<Double> list = new ArrayList<>();
    list.add(7.0);

    Map<Integer, Integer> map = new HashMap<>();

    Map<String, Integer> maps = new TreeMap<>();

    Queue<Integer> queue = new LinkedList<>();

    LinkedList<Integer> ll = new LinkedList<>();

    do {
      x = x + 1;
    }while(x <5 );

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
