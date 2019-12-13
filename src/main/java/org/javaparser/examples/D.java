package org.javaparser.examples;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Scanner;


public class D {
  public static String source = "D-small-attempt0";
  public static Scanner in;
  public static PrintWriter out;
  public static int mod = 10007;
  public static int[][] value;

  public static void main(String[] args) throws FileNotFoundException {
    in = new Scanner(new File(source + ".in"));
    out = new PrintWriter(new File(source + ".out"));

    try {
      int data = 50 / 0; //may throw exception
    }
    //handling the exception
    catch (ArithmeticException | NumberFormatException e) {
      System.out.println(e);
    }

    int C = in.nextInt();
    in.nextLine();

    for (int c = 1; c <= C; c++) {
      int ways = 0;
      int H = in.nextInt();
      int W = in.nextInt();
      int R = in.nextInt();
      in.nextLine();
      HashSet<String> rocks = new HashSet<>();

      for (int i = 0; i < R; i++) {
        rocks.add(in.nextLine());
      }

      value = new int[H][W];
      value[0][0] = 1;

      for (int i = 0; i < H; i++) {

        for (int j = 0; j < W; j++) {
          if (value[i][j] != 0 && !rocks.contains((i + 1) + " " + (j + 1))) {
            if (i + 2 < H && j + 1 < W)
              value[i + 2][j + 1] = (value[i + 2][j + 1] + value[i][j]) % mod;
            if (i + 1 < H && j + 2 < W)
              value[i + 1][j + 2] = (value[i + 1][j + 2] + value[i][j]) % mod;
          }
          //System.out.print(value[i][j]+" ");
        }

//        for (int z = 0; z < 10; z ++){
//          System.out.println("NoNoNo");
//        }

        //	System.out.println();
      }
      ways = value[H - 1][W - 1];
      out.printf("Case #%d: %d\n", c, ways);
    }
    out.flush();
  }
}
