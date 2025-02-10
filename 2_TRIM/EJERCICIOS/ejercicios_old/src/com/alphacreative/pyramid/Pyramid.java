package com.alphacreative.pyramid;

import java.util.Random;

public class Pyramid {

    public static void main(String[] args) {
        for (int j = 1; j < 6; j++) {
            for (int i = 3; i < 24; i++) {
                GeneratePyramid(i,3);
                sleep(16);
                clear();
            }
            for (int i = 24; i > 3; i--) {
                GeneratePyramid(i,3);
                sleep(16);
                clear();
            }
        }
    }

    public static void GeneratePyramid(int n, int branchLength) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n-i; j++) {
                System.out.print(" ");
            }
            for (int k = 1; k <= (2*i-1); k++) {
                if(RandomBoolean(0.1f))
                {
                    System.out.print("*");
                }
                else
                {
                    System.out.print("^");
                }
                
            }
            System.out.println();
        }
        for(int i = 1; i <= branchLength; i++) {
            for(int j = 1; j <= n-1; j++) {
                System.out.print(" ");
            }
            System.out.print("|");
            System.out.println();
        }

    }

    public static void sleep(int time) {
        try {
            Thread.sleep(time);
            } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void clear() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }
    public static boolean RandomBoolean(float probability) {
        if (probability <= 0) return false;
        if (probability >= 1) return true;
        return Math.random() < probability;
    }

}
