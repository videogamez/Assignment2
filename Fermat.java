import java.util.Scanner;

public class Fermat {

    static int upperLimitK = 30;

    public static void main(String[] args) {
        PrintDescription();
        GetInput();
    }

    static void PrintDescription() {
        //Describe the problem and purpose of the program, along with what it does
        String problem = "Describle the problem";
        System.out.println(problem);
    }

    static void GetInput() {
        Scanner sc = new Scanner(System.in);
        int k, n;

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
}