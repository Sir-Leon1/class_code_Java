import java.io.*;

public class NameGame {
    public static void main(String args[]){
        String firstName = "";
        String lastName = "";
        String fullName;
        String initials;
        int numLetters;

        BufferedReader reader;

        reader = new BufferedReader(new InputStreamReader(System.in));
        try{
            firstName = reader.readline();
        }
        catch(IOException ioe){
            System.out.println("Input output exception");
        }

        System.out.println("Hello" + firstName + "!");
        System.out.println("I'll shout it" + firstName.toUpperCase() + "!");
        System.out.println("Ok whats your last name ?");

        try{
            lastName = reader.readline();
        }
        catch(IOException ioe){
            System.out.println("I/o Exception");
        }

        fullName = firstName;
        fullName = fullName.concat(" ").concat(lastName);
        System.out.println("Your full name is " + fullName + ".");
        initials = firstName.charAt[0] + "." + lastName.charAt[0] + ".";
        System.out.println("Your initials are " + initials);
        numLetters = firstName.length() + lastName.length();
        System.out.println(" Did you know you have " + numLetters + "letters in your name");
        System.out.println("Bye");
    }
}
