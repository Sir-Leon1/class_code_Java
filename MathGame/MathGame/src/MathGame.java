/*
 * A simple math program to help
 * you remember arithmetic operations in java
 * 
 */

import java.io.*;

public class MathGame {
    public static void main(String args[]){
        int num1, num2;
        BufferedReader reader;

        reader = new BufferedReader(new InputStreamReader(System.in));

        try
        {
            System.out.print("\nFirst number: ");
            num1 = Integer.parseInt(reader.readLine());
            System.out.print("\nSecond number: ");
            num2 = Integer.parseInt(reader.readLine());
            reader.close();
        }

        catch (IOException ioe)
        {
            System.out.println("I/O Exception error. Using 1...");
            num1 = num2 = 1;
        }
        catch (NumberFormatException nfe){
            System.out.println("Number format is wrong. Using 1...");
            num1 = num2 = 1;
        }

        /*
         * Avoid the error 1 + 1 = 11 below
         * System.out.println(num1 + "+" + num2 + "=" + num1 + num2)
         */

         System.out.println(num1 + "+" + num2 + "=" + (num1 + num2));
         System.out.println(num1 + "-" + num2 + "=" + (num1 - num2));
         System.out.println(num1 + "*" + num2 + "=" + (num1 * num2));
         System.out.println(num1 + "/" + num2 + "=" + (num1 / num2));
         System.out.println(num1 + "%" + num2 + "=" + (num1 % num2));
    }
}