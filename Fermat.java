/* 
 This program was written
 by Devin Dougherty, Lukas Shiley, and Patrick Diem
 on 03/07/2021
 for the COSC 445W-01 class.
 Title: ""
 Folder: ""
 Dependencies: None
 Emails: doughertyd2@duq.edu shileyl@duq.edu
 "Description"
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
                float closeness = Float.MAX_VALUE;  //relative "closeness" of x^n + y^n and z^n
                int z = (x+y)/2;

                if(z > k) {
                    z = k;
                    PrintResults(x, y, z, RelativeMiss(x, y, z));
                    continue;   //continue to next y value
                }


                //initiate z at a value near x and y
                while (z <= k) {
                    float near = RelativeMiss(x, y, z);   //get the "closeness" of x y z
                    //System.out.println("X: " + x + " Y: " + y + " Z: " + z + " N: " + near);
                    if(near > closeness)
                        break;
                    
                    closeness = near; //closeness is increasing, continue loop
                    z++;
                }
                z--;

                PrintResults(x, y, z, closeness);
            }
        }
    }

    static void PrintResults(int x, int y, int z, float closeness) {
        float relMiss = closeness; //abs value of distance from 1

        if(smallestRelativeMiss > relMiss) {   //relMiss is closer to 1 than smallest relative miss, so new smallest rel miss
            smallestRelativeMiss = relMiss;
            System.out.println("\nNew closest miss.");
            System.out.println("x: " + x + " y: " + y + " z: " + z);
            System.out.println("Relative Miss (ratio): " + (1+closeness));
            System.out.println("Actual Miss: " + ActualMiss(x, y, z));
        }
    }

    public static float RelativeMiss(int x, int y, int z) { // get the relative miss from x,y,z,n
        double zVal = Math.pow(z, n);
        double xyVal = Math.pow(x, n) + Math.pow(y, n);
        //System.out.println(xyVal);
        //System.out.println(zVal);
        //System.out.println(Math.abs(1f - xyVal/zVal));
        return (float) Math.abs(1.0 - xyVal/zVal);
    }

    public static int ActualMiss(int x, int y, int z) { // get the actual miss from x,y,z,n
        double zVal = Math.pow(z, n);
        double xyVal = Math.pow(x, n) + Math.pow(y, n);
        return (int) (xyVal-zVal);
    }

}