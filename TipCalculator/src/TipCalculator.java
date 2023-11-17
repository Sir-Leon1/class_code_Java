import java.io.*;

public class TipCalculator {
    public static void main(String args[]){
        String cost;
        double meal, total, tip;
        BufferedReader reader;

        reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("\nHow much is the meal ");

        try{
            cost = reader.readLine();
            meal = Double.parseDouble(cost);
            tip = meal * 0.15;

            System.out.println("The meal cost is " + meal);
            System.out.println("The 15% tip is " + tip);
            System.out.println("The total cost is " + (meal + tip));
            reader.close();
        }

        catch(IOException ioe){
            System.out.println("Caught an IOException");
        }
    }
}