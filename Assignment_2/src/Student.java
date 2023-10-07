//Imported classes to allow program to function properly
import java.text.DecimalFormat;
import java.util.List;

//Student class to allow to call student objects when necessary
public class Student {

    //Variables used within the Student class
    String name;
    String rollNum;
    double grade;
    List<Integer> grades;

    //Ensures that a double can be formatted to two decimal places
    private static final DecimalFormat df = new DecimalFormat("0.00");

    //Student class constructor
    public Student(String num, String name, List<Integer> grades){
        this.name = name;
        this.rollNum = num;
        this.grades = grades;
        grade = Double.parseDouble(df.format(averageGrade(grades)));
    }

    //Getters and setter for the student class
    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNum(){
        return rollNum;
    }

    public void setRollNum(String rollNum) {
        this.rollNum = rollNum;
    }

    public double getGrade(){
        return grade;
    }

    public List<Integer> getGrades() {
        return grades;
    }

    public void setGrades(List<Integer> grades) {
        this.grades = grades;
    }

    //ToString method allows the Student class information to be called easily and accurately simply calling System.out.println();
    public String toString() {
        return "Roll Number: " + rollNum + "\n" +
                "Name: " + name + "\n" +
                "Grade: " + grade + "\n";
    }

    //takes in all the Students grades as a list and calculates the average
    public double averageGrade(List<Integer> grades){
        int amount = grades.size();     //Obtains the amount of grades the student has
        double sum = 0;                 //Accumulates all Student grades

        //Iterates through grades to get the overall total
        for (Integer i: grades){
            sum += i;
        }

        //Returns the average of the Student grades
        return sum/amount;
    }

}
