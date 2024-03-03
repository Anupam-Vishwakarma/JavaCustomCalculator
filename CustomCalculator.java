/*
--> A Java program for a customized calculator
--> This calculator is made to demonstrate error handling techniques
--> It cannot take any input larger than 100000
--> It cannot perform multiplication when both values are larger than 7000
--> It throws an error when trying to divide by zero(0)
--> It will not add 8 with 9
--> Only integer input will be taken
*/
package krishna;

// Created by the krishnaanupam6@gmail.com (author)

import java.util.InputMismatchException;
import java.util.Scanner;

// Custom exception for invalid or restricted input
class InvalidInput extends Exception
{
    @Override
    public String toString()
    {
        return "Error: Invalid input. The input values are not allowed.";
    }
}

// Custom exception for division by zero
class CanNotDivideByZero extends Exception
{
    @Override
    public String toString()
    {
        return "Error: Cannot divide by zero.";
    }
}

// Custom exception for exceeding maximum input size
class MaxInputSize extends Exception
{
    @Override
    public String toString()
    {
        return "Error: Input size exceeds the maximum limit (100000).";
    }
}

// Custom exception for exceeding maximum multiplication input size
class MaxMullInputSize extends Exception
{
    @Override
    public String toString()
    {
        return "Error: For multiplication, input values must be less than 7000.";
    }
}

// Class containing all the mathematical methods/operations
class MyCalculator
{
    // Addition method
    int add(int a, int b) throws InvalidInput, MaxInputSize
    {
        if (a == 8 && b == 9)
        {
            throw new InvalidInput();
        }
        if (a > 100000 || b > 100000)
        {
            throw new MaxInputSize();
        }
        return a + b;
    }

    // Subtraction method
    int sub(int a, int b) throws MaxInputSize
    {
        if (a > 100000 || b > 100000)
        {
            throw new MaxInputSize();
        }
        return a - b;
    }

    // Multiplication method
    int mull(int a, int b) throws MaxMullInputSize
    {
        if (a > 7000 && b > 7000)
        {
            throw new MaxMullInputSize();
        }
        return a * b;
    }

    // Division method
    float divide(int a, int b) throws MaxInputSize, CanNotDivideByZero
    {
        if (a > 100000 || b > 100000)
        {
            throw new MaxInputSize();
        }
        if (b == 0)
        {
            throw new CanNotDivideByZero();
        }
        return (float) a / b;
    }
}

public class CustomCalculator
{
    // Encapsulated method for getting user input
    private static int getUserInput(Scanner scanner)
    {
        int choice = 0; //variable to store the input value
        boolean isValidInput = false; //to check weather the input is correct is not

        do
        {
            System.out.print("Enter a number: ");
            try
            {
                choice = scanner.nextInt();
                isValidInput = true; //if input is correct make true
            }
            //if input is incorrect then
            catch (InputMismatchException e)
            {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume the invalid input
            }
        } while (!isValidInput);
        //loop will terminate only when isValidInput is true means only at correct input
        return choice;
    }

    public static void main(String[] args)
    {
        MyCalculator calculator = new MyCalculator();//new Calculator is created named as calculator
        Scanner scanner = new Scanner(System.in);

        int choice = 0;
        //all the operations are added into a do while loop
        //so that user can run this code once and then use till he/she wants
        //this program will be terminated when the user want
        do
        {
            try
            {
                System.out.println("Select an operation:");
                System.out.println("1. Addition");
                System.out.println("2. Subtraction");
                System.out.println("3. Multiplication");
                System.out.println("4. Division");
                System.out.println("0. Exit");
                System.out.println("Enter your choice : ");
                choice = getUserInput(scanner);

                int num1, num2;//two values on which the operation will be performed.

                switch (choice)
                {
                    case 1:
                        // Addition
                        System.out.println("Enter two values to perform addition:");
                        num1 = getUserInput(scanner);
                        num2 = getUserInput(scanner);
                        System.out.println("Addition: " + calculator.add(num1, num2));
                        break;

                    case 2:
                        // Subtraction
                        System.out.println("Enter two values to perform subtraction:");
                        num1 = getUserInput(scanner);
                        num2 = getUserInput(scanner);
                        System.out.println("Subtraction: " + calculator.sub(num1, num2));
                        break;

                    case 3:
                        // Multiplication
                        System.out.println("Enter two values to perform multiplication:");
                        num1 = getUserInput(scanner);
                        num2 = getUserInput(scanner);
                        System.out.println("Multiplication: " + calculator.mull(num1, num2));
                        break;

                    case 4:
                        // Division
                        System.out.println("Enter two values to perform division:");
                        num1 = getUserInput(scanner);
                        num2 = getUserInput(scanner);
                        System.out.println("Division: " + calculator.divide(num1, num2));
                        break;

                    case 0:
                        System.out.println("Exiting the program. Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                        break;
                }
            }

            catch (InvalidInput | MaxInputSize | MaxMullInputSize | CanNotDivideByZero e)
            {
                System.out.println(e.toString());
                scanner.nextLine(); // Consume the invalid input
            }
        } while (choice != 0);

        scanner.close();
    }
}
