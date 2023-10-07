// Classes imported to allow certain things to function within program
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);      //Scanner class called to allow for user input

        menu(scan);     //Call to menu method

    }

    public static void menu(Scanner in) throws IOException {
        Document doc = new Document();      //Creates a Document object

        String fileName = doc.fileSelect(in);   //Calls the file select method within the Document class

        System.out.println("-------------------------------------");

        String input;       //Variable used to carry the user input for the menu selection

        //Do while loop to allow the program to run at least once and up until the user is finished
        do {

            //Displayed menu for user to choose from
            System.out.println("""
                    1. Get all student records
                    2. Get a single student record
                    3. Update a student grade
                    4. Add a student record
                    5. Delete a student record
                    6. Quit
                    Selection:""");
            input = in.next();

            //Switch statement to take menu option and process what the user wants accordingly
            switch (input) {
                //Case 1 allows the user to view the record for all students
                case "1" -> {
                    System.out.println("\n---Getting all records---\n");
                    doc.allRecords(fileName);
                    in.next();
                }
                //Case 2 allows the user to view the record for a single student
                case "2" -> {
                    System.out.println("\n---Getting a record---");
                    doc.oneRecord(in, fileName);
                    in.next();
                }
                //Case 3 allows the user to update a record by deleting the original and imputing a new one in its place
                case "3" -> {
                    System.out.println("---Update an existing record---");
                    doc.modify(in,fileName);
                    in.next();
                }
                //Case 4 allows the user to add a new record
                case "4" -> {
                    System.out.println("\n---Adding a new record---");
                    doc.addRecord(in, fileName, false);
                    in.next();
                }
                //Case 5 allows the user to delete a record if it exists
                case "5" -> {
                    System.out.println("\n---Deleting a record---");
                    doc.deleteRecord(in, fileName, false, " ");
                    in.next();
                }
                //Case 6 allows the user to exit the program
                case "6" -> System.out.println("\nExiting system, have a nice day!");
                //An invalid input outputs a message to the user and allows them to select again
                default -> {
                    try{
                        throw new InvalidInputException("The input is invalid, please try again!");
                    } catch (InvalidInputException ex) {;
                        System.out.println(ex.getMessage());
                    }
                }
            }

            System.out.println("-------------------------------------");
        } while (!input.equals("6"));
    }

}

//Custom exception class, used to ensure proper selection of menu options
class InvalidInputException extends Exception
{
    public InvalidInputException (String s) {
        super(s);
    }
}