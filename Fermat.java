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

    static int upperLimitK = 50;
    static int k, n; //equation variables

    static float smallestRelativeMiss = Float.MAX_VALUE;  //smallest relative miss
    static long smallestMiss = Long.MAX_VALUE;  //smallest actual miss

    public static void main(String[] args) {
        PrintDescription();
        GetInput();
        TestValues();
    }

    static void PrintDescription() {
        //Describe the problem and purpose of the program, along with what it does
        String problem = 
        "Fermat’s last theorem states that there are no natural numbers x, y, and z such that x^n + y^n = z^n\n" +
        "This program will ask for an integer n to plug into the formula, and an integer k as the upper limit of x, y, and z.\n" +
        "It will then find x, y, and z values greater than 10 and less than k that nearly satisfy the equation.";
        System.out.println(problem);
    }

    static void GetInput() {
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("\nThe value k must be greater than 10 and less than " + upperLimitK + ".");
            System.out.println("Enter the value of k for the equation:");  //tell the user to input a value for k
            k = sc.nextInt();  //set k to the value the user chooses
        } 
        while(!IsValid(k, 10, upperLimitK));  //if the value is not in a valid range, reprompt the user for another value

        //same thing for value of n
        do {
            System.out.println("\nThe value n must be greater than 2 and less than 12.");
            System.out.println("Enter the value of n for the equation:");
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

    public static long ActualMiss(int x, int y, int z) { // get the actual miss from x,y,z,n
        double zVal = Math.pow(z, n);
        double xyVal = Math.pow(x, n) + Math.pow(y, n);
        return (long) (xyVal-zVal);
    }

}