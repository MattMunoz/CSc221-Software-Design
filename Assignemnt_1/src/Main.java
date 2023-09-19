import java.util.Scanner;   //Scanner class to allow us to input data
import java.util.Random;    //Random scanner used to test a class with various inputs

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);    //Object of the scanner class to allow for user input

        //Separate functions for each task in the assignment
        System.out.println("-----Task 1-----");
        Task1();
        System.out.println("\n-----Task 2-----");
        Task2(in);
        System.out.println("\n-----Task 3-----");
        Task3();
        System.out.println("\n-----Task 4-----");
        Task4();
        System.out.println("\n-----Task 5-----");
        Task5();
        System.out.println("\n-----Bonus-----");
        Bonus(in);
    }

    //Function for the first task of the assignment
    private static void Task1(){
        //Initializing an integer, double, and a string
        int i = 5;
        double d = .005;
        String name = "Matthew";

        //Output the integer, double and string initialized above
        System.out.println("Integer: " + i);
        System.out.println("Double: " + d);
        System.out.println("String: " + name);
    }

    //Function for the second task of the assignment
    private static void Task2(Scanner in){
        System.out.print("How old are you: ");      //Print out a question for the user to answer
        int age = in.nextInt();                     //Take the user input using the scanner class and placing into variable age

        //Conditional statement that goes one way or another depending on user input
        if (age >= 18){
            System.out.println("You are an adult.");
        } else {
            System.out.println("You are a minor.");
        }
    }

    //Function for the third task of the assignment
    private static void Task3(){
        System.out.print("Even numbers: ");    //A string output simply used to organize the information

        //Loop to display the even numbers between 1 and 20(inclusive)
        for(int i = 1; i <= 20; i++){
            if (i % 2 == 0){                    //Determines if the value is even
                System.out.print(i + " ");      //If number is even it is output
            }
        }

        //Loop to add up all odd numbers between 1 and 50
        int sum = 0;                    //Accumulator
        for(int i = 1; i <= 50; i++){
            if (i % 2 != 0){            //Determines if the number is odd
                sum += i;               //Adds the odd number to the ongoing accumulator
            }
        }

        System.out.println("\nSum: " + sum);    //Displays the sum determined in the loop
    }

    //Function for the fourth task of the assignment
    private static void Task4() {
        Random rand = new Random();         //Used to obtain random numbers for testing

        //Loop runs 5 times to allow for 5 tests
        for (int i = 0; i < 5; i++) {
            int len = rand.nextInt(10);         //Obtain a random integer smaller than 10 for the length
            int width = rand.nextInt(10);       //Obtain a random integer smaller than 10 for the width

            //Displays the random length and width and calls the calculateArea function to display the area of the rectangle
            System.out.println("Length: " + len + " Width: " + width +" Area: " + calculateArea(len, width));
        }
    }

    //Function taking in two parameters
    private static int calculateArea(int len, int width) {
        //The parameters are multiplied and returned
        return len * width;
    }

    //Function for the fifth task of the assignment
    private static void Task5() {
        //Loop to test the numbers from 0 to 9
        for (int i = 0; i < 10; i++) {
            System.out.println(i + "! = " + factorial(i));  //Call the factorial with the given input and displays the returned value
        }
    }

    //Function to calculate the factorial of a parameter
    private static int factorial(int n) {

        //Initialize the fact variable to use as an accumulator
        int fact = 1;

        if (n == 1 || n == 0) {             //First to values of factorial
            return 1;                       //Return 1 if input is 0 or 1
        } else {
            for (int i = 1; i <= n; i++) {  //Loop to get the factorial
                fact *= i;                  //multiply the ongoing values
            }
        }

        //Return the value calculated in the loop
        return fact;
    }

    //Function for the Bonus task of the assignment
    private static void Bonus(Scanner in) {

        //Ask user for the first number to be used in the calculation
        System.out.print("Enter first number: ");
        int first = in.nextInt();

        //Do while to ensure the user selects a valid operator
        String operator;    //Defined outside the loop to allow for use in the do-while loop
        do {
            System.out.print("Enter an operator (+, -, /, *): ");   //Print question to ask user for operator
            operator = in.next();
        } while (!(operator.equals("+") || operator.equals("-") ||  //If the user inputs an invalid operator run the question again
                operator.equals("*") || operator.equals("/")));

        //Ask user for the second number to be used in the calculation
        System.out.print("Enter second number: ");  //
        int second = in.nextInt();

        //Switch statement allow to more clearly and quickly select the proper operation the user selected
        switch (operator) {
            case "+" -> System.out.println(first + " + " + second + " = " + (first + second));
            case "-" -> System.out.println(first + " - " + second + " = " + (first - second));
            case "*" -> System.out.println(first + " * " + second + " = " + (first * second));
            case "/" -> {

                //Check if the user is doing a division by zero and get out of the function before computing
                if (second == 0) {
                    System.out.println("Can't divide by zero");
                    return;
                }
                System.out.print(first + " / " + second + " = " + (first / (double) second));
            }
        }

    }


}