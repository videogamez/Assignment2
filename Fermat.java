/* 
 This program was written
 by Devin Dougherty, Lukas Shiley, and Patrick Diem
 on 03/07/2021
 for the COSC 445W-01 class.
 Title: "Fermat's Theorem Near Misses"
 Folder: ""
 Dependencies: None
 Emails: doughertyd2@duq.edu shileyl@duq.edu
 "This program attempts to locate near misses from Fermat's Theorem utilizing the form x^n + y^n <> z^n"
*/

import java.util.Scanner;

public class Fermat {

    static int upperLimitK = 30;
    static int k, n; //equation variables

    static float smallestRelativeMiss = Float.MAX_VALUE;  //smallest relative miss
    static int smallestMiss = Integer.MAX_VALUE;  //smallest actual miss

    public static void main(String[] args) {
        PrintDescription();
        GetInput();
        TestValues();
        System.out.println(RelativeMiss(13, 18, 23, 3));
    }

    static void PrintDescription() {
        //Describe the problem and purpose of the program, along with what it does
        String problem = "Describe the problem";
        System.out.println(problem);
    }

    static void GetInput() {
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Enter the value of k for the equation");  //tell the user to input a value for k
            k = sc.nextInt();  //set k to the value the user chooses
        } 
        while(!IsValid(k, 10, upperLimitK));  //if the value is not in a valid range, reprompt the user for another value

        //same thing for value of n
        do {
            System.out.println("Enter the value of n for the equation");
            n = sc.nextInt();
        } 
        while(!IsValid(n, 3, 11));

        sc.close();
    }

    static boolean IsValid(int val, int min, int max) {
        if(val < min || val > max) {
            //tell the user their input was not valid
            System.out.println("Value must be greater than " + (min-1) + " and less than " + (max+1) +"\n");
            return false;
        }
        return true;
    }

    static void TestValues() {
        for(int x = 10; x <= k; x++) {
            for(int y = x; y <= k; y++) { 
                float closeness = 0;  //relative "closeness" of x^n + y^n and z^n
                int z = x+y/2;

                if(z > k) {
                    z = k;
                    PrintResults(x, y, z, n, RelativeMiss(x, y, z, n));
                    continue;   //continue to next y value
                }


                //initiate z at a value near x and y
                while (z <= k) {
                    float near = RelativeMiss(x, y, z, n);   //get the "closeness" of x y z
                    System.out.println(near);

                    if(near < closeness) { //if closeness is less than last z value, break out of loop
                        closeness = near;
                        break;
                    }
                    closeness = near; //closeness is increasing, continue loop
                    System.out.println(closeness);
                    z++;
                }
                z--;
                System.out.println(closeness);

                PrintResults(x, y, z, n, closeness);
            }
        }
    }

    static void PrintResults(int x, int y, int z, int n, float closeness) {
        float relMiss = Math.abs(1f - closeness); //abs value of distance from 1
        System.out.println(x + " " + y + " " + z + " " + relMiss);

        if(smallestRelativeMiss > relMiss) {   //relMiss is closer to 1 than smallest relative miss, so new smallest rel miss
            smallestRelativeMiss = relMiss;
            System.out.println("\nNew closest miss.");
            System.out.println("x: " + x + " y: " + y + " z: " + z);
            System.out.println("Relative Miss (ratio): " + closeness);
            System.out.println("Actual Miss: " + ActualMiss(x, y, z, n));
        }
    }

    public static float RelativeMiss(int x, int y, int z, int n) { // get the relative miss from x,y,z,n
        float zVal = z^n;
        float xyVal = x^n + y^n;
        return xyVal/zVal;
    }

    public static int ActualMiss(int x, int y, int z, int n) { // get the actual miss from x,y,z,n
        int zVal = z^n;
        int xyVal = x^n + y^n;
        return xyVal-zVal;
    }

}