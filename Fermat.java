import java.util.Scanner;

public class Fermat {

    static int upperLimitK = 30;
    static int k, n; //equation variables

    static float smallestRelativeMiss = 0f;
    static int smallestMiss = 0;

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
                float closeness = 0;  //relative "closeness" of x^n + y^n and z^n
                int z = x+y/2;

                if(z > k) {
                    z = k;
                    System.out.println(x + ", " + y + ", " + z + ", " + NearMiss(x, y, z, n));
                    continue;   //continue to next y value
                }


                //initiate z at a value near x and y
                while (z <= k) {
                    float near = RelativeMiss(x, y, z, n);   //get the "closeness" of x y z

                    if(near < closeness) { //if closeness is less than last z value, break out of loop
                        closeness = near;
                        break;
                    }
                    closeness = near; //closeness is increasing, continue loop
                    z++;
                }
                z--;

                fName(x, y, z, n, closeness);
            }
        }
    }

    static void fName(int x, int y, int z, int n, float closeness) {
        float relMiss = Math.abs(1f - closeness); //abs value of distance from 1

        if(smallestRelativeMiss > relMiss) {   //relMiss is closer to 1 than smallest relative miss, so new smallest rel miss
            smallestRelativeMiss = relMiss;
            System.out.println("New closest miss.");
            System.out.println("x: " + x + " y: " + y + " z: " + z);
            System.out.println("Relative Miss (ratio): " + closeness);
            System.out.println("Actual Miss: " + ActualMiss(x, y, z, n));
        }
    }

    public static float RelativeMiss(int x, int y, int z, int n) {
        float zVal = z^n;
        float xyVal = x^n + y^n;
        return xyVal/zVal;
    }

    public static int ActualMiss(int x, int y, int z, int n) {
        float zVal = z^n;
        float xyVal = x^n + y^n;
        return xyVal-zVal;
    }

}