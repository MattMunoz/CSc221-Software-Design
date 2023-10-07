//Imported classes used for program functionality
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Document class allows for the document needed for the students to be opened and manipulated as needed
public class Document {

    //fileSelect allows the user to select the file they'd like to work with
    public String fileSelect(Scanner in) throws IOException {
        String select;              //Stores user input when deciding to select a file
        String file;                //Stores the file name the user will manipulate
        File f;                     //Stores the actual file that will be manipulated

        //Do while loop to ensure the proper file gets selected and allows the user to retry if a mistake was made
        do {
            //User selects the file
            System.out.println("What file would you like to open: ");
            file = in.next();

            f = new File(file);     //Calls the file the user wants

            //Checks if the selected file exits
            if (!f.exists()) {
                //User validates if that's the file they want to work with
                System.out.println("\n" + f + " does not exist\n" +
                        "Would you like to create it? (Y/N)");
                select = in.next();

                //If the file doesn't exist create it
                if (!(select.equals("Y") || select.equals("y"))) {
                    continue;
                }

                //Inputs the header for the csv file when the file is created
                try {
                    FileWriter output = new FileWriter(f);
                    output.write("Roll Number, Name, Average Grade, All Grades");
                    output.close();
                }
                //Throws exception if the file is unable to be written to
                catch (Exception e) {
                    e.getStackTrace();
                }
            //If the file exists the user ensures that the file they want to open
            } else {
                System.out.println("\n" + f + " found. \nIs this the file you want to open? (Y/N)");
                select = in.next();
                System.out.println();
            }

        //If the user doesn't want that record to open the program allows them to select again
        } while (!(select.equals("Y") || select.equals("y")));

        //Returns the name of the file to be opened
        return file;

    }

    //allRecords outputs all the records stored within the file
    public void allRecords(String fileName) throws IOException {
        String currentLine;                                                     //Stores the current line being read from the file
        BufferedReader br = new BufferedReader(new FileReader(fileName));       //Allows lines to be read

        br.readLine();                                                          //Takes the header so it's not output later

        //try...catch ensures there is a line to be read
        try
        {
            //Takes the current line being read, turns it into an array to output it accordingly
            while ((currentLine = br.readLine()) != null){
                String[] student = currentLine.split(",");
                if (!currentLine.equals("")) {
                    System.out.println("Roll Number: " + student[0] + "\n" +
                            "Name: " + student[1] + "\n" +
                            "Grade: " + student[2] + "\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //closes the BufferReader to avoid later errors
        br.close();

        System.out.println("Press 0+Enter to continue");
    }

    //oneRecord outputs a single user selected record
    public void oneRecord(Scanner in, String fileName) throws IOException {
        System.out.println("What roll number would you like to see: ");
        String roll = in.next();                                                 //Takes the roll number of the student the user want to see
        String currentLine;                                                     //Stores the current line being observed

        BufferedReader br = new BufferedReader(new FileReader(fileName));       //Allows lines to be read

        //try...catch ensures there is a line to be read
        try
        {
            //Takes the current line being read, turns it into an array to output it accordingly
            while ((currentLine = br.readLine()) != null) {
                //Only processes to line that includes the roll number the user wants
                if (currentLine.contains(roll)) {
                    String[] student = currentLine.split(",");
                    System.out.println("\nRoll Number: " + student[0] + "\n" +
                            "Name: " + student[1] + "\n" +
                            "Grade: " + student[2] + "\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //closes the BufferReader to avoid later errors
        br.close();

        System.out.println("Press 0+Enter to continue");
    }

    //addRecord allows a record to be appended to the file
    public void addRecord(Scanner in, String fileName, boolean tf) throws IOException {
        File file = new File(fileName);                         //Opens the file the user specified at the start of the program
        FileWriter writer = new FileWriter(file, true); //Allows the user to append a record to the file

        //Entering the new students information
        System.out.println("Roll Number: ");
        String newRoll = in.next();             //Takes in the Roll Number for the new record

        //Validates the roll number is of 4 numbers, no letters
        while (!(newRoll.matches("[0-9]+") && newRoll.length() == 4)) {
            System.out.println("Invalid entry, Roll Number must be numeric with exactly 4 digits\n");
            System.out.println("Re-enter Roll Number: ");
            newRoll = in.next();
        }

        System.out.println("First Name: ");
        String firstName = in.next();          //Takes in the First Name for the new record

        //Validates the First Name is of only letters
        while (!firstName.matches("[a-zA-Z]+")) {
            System.out.println("Invalid entry, First Name can only include letters\n");
            System.out.println("Re-enter First Name: ");
            firstName = in.next();
        }

        System.out.println("Last Name: ");
        String lastName = in.next();           //Takes in the Last Name for the new record

        //Validates the Last Name is of only letters
        while (!lastName.matches("[a-zA-Z]+")) {
            System.out.println("Invalid entry, Last Name can only include letters\n");
            System.out.println("Re-enter Last Name: ");
            lastName = in.next();
        }

        String newName = firstName + " " + lastName;        //Combines the first and last name to a single name foe entry

        System.out.println("Grades, insert an grade below 0 or greater than 120 to continue");

        int i;      //Variable takes in the grade one at a time
        List<Integer> newGrades = new ArrayList<>();        //Stores all the students grades

        //Allows the user to input each grade separately
        while (true) {

            //Ensures the values being entered are only integers
            try {
                i = in.nextInt();
            } catch (Exception e) {
                in.next();
                continue;
            }

            //Allows the user to exit if the value is outside the bounds
            if (i < 0 || i > 120){
                break;
            }
            newGrades.add(i);       //Adds each grade to the list one at time
        }

        //Converts the Array of integers to a String of numbers seperated by spaces
        List<String> strings = newGrades.stream().map(Object::toString)
                .toList();
        String grades = String.join(" ", strings);

        //Crates a student object, mostly to calculate the average grade
        Student stu = new Student(newRoll, newName, newGrades);

        //Creates a string with all the input information
        String insert = newRoll + "," + newName + "," + stu.grade + "," + grades;

        //Writes the newly enters record at the end of the file
        writer.write(System.getProperty( "line.separator" ));
        writer.write(insert);

        //Checks if the file is adding a new record or updating one
        if (!tf) System.out.println("\n---Added---\n" + stu);
        else System.out.println("\n---Updated---\n" + stu);

        //Closes the writer to prevent errors later on
        writer.close();

        System.out.println("Press 0+Enter to continue");
    }

    //modify allows the user to update a record by deleting the original one and creating a new one
    public void modify(Scanner in, String fileName) throws IOException{
        System.out.println("What roll number would you like to modify: ");
        String roll = in.next();        //Stores the record to be modified

        deleteRecord(in, fileName, true, roll);     //Deletes the original record
        addRecord(in, fileName, true);              //Add a new record in its place
    }

    //deleteRecord allows the user to remove a record base on Roll Number
    public void deleteRecord(Scanner in, String fileName, boolean tf, String roll) throws IOException {

        //Checks if the record will be deleted permanently or just updated
        if (!tf) {
            System.out.println("What roll number would you like to delete: ");
            roll = in.next();
        }


        File inputFile = new File(fileName);                    //Opens the original file
        File tempFile = new File("myTempFile.txt");    //Creates temporary file that will store only the wanted records

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));  //Allows the original file to be read
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));   //Allows the new, temp file to be written to

        String currentLine;     //Stores the current line from the original file being read

        //Runs through each line in the original file
        while((currentLine = reader.readLine()) != null) {
            if(currentLine.contains(roll)) continue;        //If the line doesn't contain the Roll Number being deleted skip the copy
            writer.write(currentLine + System.getProperty("line.separator"));
        }

        //Closes the writer and reader to prevent errors later in the program
        writer.close();
        reader.close();

        boolean deleted = inputFile.delete();               //Deletes the original file including the deleted record
        boolean successful = tempFile.renameTo(inputFile);  //Renames the temp file, which doesn't include the deleted record to the original file name

        //If record was simply deleted, not an update, informes the user
        if (!tf) {
            if (deleted && successful) System.out.println("\nIf record existed it was deleted!\n");
            System.out.println("Press 0+Enter to continue");
        }
    }

}