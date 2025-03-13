// import the packages
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//creating the main arrays
public class Main {
    static final int maxAmount = 100;

    static String[] studentid = new String[maxAmount];
    static String[] studentname = new String[maxAmount];
    static int studentCount = 0;
    static double [] averagesorting = new double [maxAmount];

    //crate the main method
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);


        while (true) {// infinte loop

            menu();
            if (input.hasNextInt()) {
                int UserChoice = input.nextInt();// getting the input for user choice
                switch (UserChoice) {

                    case 1:// creating options
                        availabelSeats();// calling the methods
                        break;// stop the itiration
                    case 2:
                        RegisterStudents(input);
                        break;
                    case 3:
                        DeleteStudent(input);
                        break;
                    case 4:
                        FindStudent(input);
                        break;
                    case 5:
                        StoreStudentDetailsIntoTheFile();
                        break;
                    case 6:
                        loadStudentDetailsFromTheFileIntoTheSystem();
                        break;
                    case 7:
                        ViewTheListOfStudents();
                        break;
                    case 8:
                        Addinontalopotions(input);
                        break;
                    case 9:
                        input.close();
                        System.exit(0);// exiting the program
                }

            } else {
                System.out.println("Invalid Input Enter ");
                input.nextLine();
            }

        }
    }

    public static void menu() {
        //display the options for user to input
        System.out.println("***Welcome to the student***");
        System.out.println("1.check Available seats.");
        System.out.println("2.Register student.");
        System.out.println("3.Delete Student.");
        System.out.println("4.Find student.");
        System.out.println("5.Store students details.");
        System.out.println("6.Load students details from to the file.");
        System.out.println("7.View the list of students based on there names.");
        System.out.println("8.Additional options.");
        System.out.println("9.Exit the program");
        System.out.println("Enter the choice: ");
    }

    public static void availabelSeats() {
        int availabel_seats = maxAmount - studentCount;// calculate the number of available seats
        System.out.println("The available number of slots is " + availabel_seats);

    }

    public static void RegisterStudents(Scanner input) {
        Student studentinformation = new Student();
        studentinformation.studentdetails();//call student details method from the student class

    }

    static boolean duplicateid(String Student_ID) {

        for (int i = 0; i < studentCount; i++) {
            if (studentid[i].equals(Student_ID)) {//check whether the student id is taken
                return true;
            }

        }
        return false;
    }

    static boolean duplicatename(String Student_name) {
        for (int i = 0; i < studentCount; i++) {
            if (studentname[i].equals(Student_name)) {//check whether the student name is taken
                return true;
            }
        }
        return false;
    }
    // create a method for delete the students
    public static void DeleteStudent(Scanner input) {
        if (studentCount == 0) {// check whether there are any student registered
            System.out.println("\nThere are no students registered!");
            return;
        }else {

            System.out.println("Enter the ID which need to delete :");
            String dltstudent = input.next();

            boolean idfound = false;//id validation

            for (int i = 0; i < studentCount; i++) {//loop the student count
                if (studentid[i].equals(dltstudent)) {
                    idfound = true;
                    for (int j = i; j < studentCount - 1; j++) {
                        studentid[j] = studentid[j + 1];
                        studentname[j] = studentname[j + 1];
                    }
                    studentid[studentCount - 1] = null;
                    studentname[studentCount - 1] = null;
                    studentCount--;
                }
            }
            if (idfound) {// check whether the student id is found
                System.out.println(dltstudent + "ID Deleted successfully!!");

            } else {
                System.out.println("Invalid Input! Enter a valid ID.");

            }
        }

    }

    // create the method to find the students
    public static void FindStudent(Scanner input) {
        if (studentCount == 0) {// check that any student is registered
            System.out.println("\nThere are no students registered!");
            return;
        }else {
            System.out.println("Enter the student ID");
            String FindStudent = input.next();
            boolean found = false; //input validation

            for (int i = 0; i < studentCount; i++) {//loop the student count
                if (studentid[i].equals(FindStudent)) {// check that any id is matching in the loop
                    System.out.println("The student ID is " + studentname[i]);//print the name according to the given student id
                    found = true;
                    break;
                }
            }
            if (!found) {//if the id is not found
                System.out.println("The student ID not found");//print a message that telling id is not found
            }
        }

    }

    //create a method for store student details into the file
    public static void StoreStudentDetailsIntoTheFile() {
        if (studentCount == 0) {// check if there are any student registered
            System.out.println("\nThere are no students registered!");//print a message for user
            return;
        }else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("Studentdetails.txt"))) {//error handling
                for (int i = 0; i < studentCount; i++) {//loop the student count
                    writer.write(studentid[i] + "," + studentname[i]);// write student id and name in the text file

                    writer.newLine();// getting a new line
                }
                System.out.println("Student Details Stored Successfully!!");//print a message that student details are stored successfully
            } catch (IOException e) {//error handling if the file not found
                System.out.println("Error writing to file: " + e.getMessage());//print the message that file not found
            }
        }
    }
    // create a method for load student details from the text file
    public static void loadStudentDetailsFromTheFileIntoTheSystem() {
        studentCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("Studentdetails.txt"))) {
            String line;
            while ((line = reader.readLine()) != null && studentCount < maxAmount) {
                String[] data = line.split(",");
                if (data.length >= 2) {//check that there are two parts
                    String id = data[0].trim();
                    String name = data[1].trim();
                    if (!duplicateid(id)) {
                        studentid[studentCount] = id;
                        studentname[studentCount] = name;
                        studentCount++;
                    }
                }
            }
            System.out.println("Student details loaded successfully");
            System.out.println("Total students loaded: " + studentCount);

            // Display loaded students
            System.out.println("\nLoaded Student Details:");
            for (int i = 0; i < studentCount; i++) {
                System.out.println("ID: " + studentid[i] + ", Name: " + studentname[i]);
            }
          //
        } catch (FileNotFoundException e) {
            System.out.println("Student details file not found!");
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }








    public static void ViewTheListOfStudents() {
        if (studentCount == 0) {
            System.out.println("There are no students Please Register Students first.....");
        }

        for (int i = 0; i < studentCount - 1; i++) {
            for (int j = 0; j < studentCount - i; j++) {
                if (studentname[j] == null || (studentname[j + 1]) == null) {
                    continue;
                }
                if ((studentname[j].compareTo(studentname[j + 1]) > 0)) {
                    String TemporaryString = studentname[j];
                    studentname[j] = studentname[j + 1];
                    studentname[j] = studentname[j + 1];
                    studentname[j + 1] = TemporaryString;

                }
            }
        }
        System.out.println("Students are sorted according to the alphebetical order");
        for (int i = 0; i < studentCount; i++) {
            System.out.println(studentid[i] + "-" + studentname[i]);

        }

    }

    public static void AddModuleToStudent(Scanner input) {
        if (studentCount == 0) {
            System.out.println("\nThere are no students registered!");
            return;
        }
        System.out.println("\nEnter the ID of the student to get a grade");
        String studentId = input.next();

        boolean foundstudent = false;
        for (int i = 0; i < studentCount; i++) {
            if (studentid[i].equals(studentId)) {
                foundstudent = true;
                System.out.println("\nThe student ID is " + studentid[i] + "and name " + studentname[i]);

                Module ModuleClass = new Module();
                ModuleClass.inputmoduldata(input);

                averagesorting[i] = ModuleClass.getAverage();

                try (PrintWriter writer = new PrintWriter(new FileWriter("Studentdetails.txt", true))) {
                    writer.print(studentid[i] + "," + studentname[i]);
                    for (int j = 0; j < ModuleClass.getModulenames().size(); j++) {
                        writer.print("," + " " + ModuleClass.getModulenames().get(j) + ":" + " " + ModuleClass.getModulemarks().get(j));
                    }
                    writer.println(" , Average " + ModuleClass.getAverage());
                    System.out.println("\nMarks Added Successfully!");


                    System.out.println("\nGrade " + ModuleClass.getAverage());
                    break;

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
            if (!foundstudent) {
                System.out.println("Invalid student ID!");
            }

        }
    }

    static void StudentsSummary() {
        System.out.println("\nSummary of the students details");
        System.out.println("\nTotal student registrations " + studentCount);


    }
    //create a method to summary
    public static void DetailedSummary() {
        System.out.println("\nDetailed Summary of Student Records");

        System.out.println("Total student registrations: " + studentCount);

        int module1Above40 = 0;//assign variables
        int module2Above40 = 0;
        int module3Above40 = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("Studentdetails.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 5) { // Ensure that module data
                    for (int i = 2; i <= 4; i++) { // Module data starts from index 2
                        String[] moduleData = data[i].trim().split(":");
                        if (moduleData.length == 2) {
                            double mark = Double.parseDouble(moduleData[1].trim());
                            if (mark > 40) {
                                switch (i) {
                                    case 2: module1Above40++; break;
                                    case 3: module2Above40++; break;
                                    case 4: module3Above40++; break;
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading student details file: " + e.getMessage());
        }
        //display the number of students who pass the modules
        System.out.println("Number of students have more than 40 in Module 1: " + module1Above40);
        System.out.println("Number of students have more than 40 in Module 2: " + module2Above40);
        System.out.println("Number of students have more than 40 in Module 3: " + module3Above40);
    }
    //create a method for student report
    public static void StudentReport() {
        System.out.println("\nComplete Student Report (Sorted by Average)");

        if (studentCount == 0) {//check if there are any student is registered
            System.out.println("No students registered.");
            return;
        }

        // Create a list to hold student data
        List<StudentData> studentDataList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("Studentdetails.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                //split the line based on commas
                String[] data = line.split(",");
                //check if the line has five elements
                if (data.length >= 5) {
                    //extract and trim the student details
                    String id = data[0].trim();
                    String name = data[1].trim();
                    double module1 = Double.parseDouble(data[2].split(":")[1].trim());// extract and parse the module
                    double module2 = Double.parseDouble(data[3].split(":")[1].trim());
                    double module3 = Double.parseDouble(data[4].split(":")[1].trim());
                    double average = (module1 + module2 + module3) / 3;

                    studentDataList.add(new StudentData(id, name, module1, module2, module3, average));
                }
            }
        } catch (IOException e) {//error handling if the file is not found
            System.out.println("Error reading student details file: " + e.getMessage());
            return;
        }

        // Bubble sort
        for (int i = 0; i < studentDataList.size() - 1; i++) {
            for (int j = 0; j < studentDataList.size() - i - 1; j++) {
                //compare the average marks of the students
                if (studentDataList.get(j).average > studentDataList.get(j + 1).average) {
                    // Swap the student details if the avergae is grater
                    StudentData temp = studentDataList.get(j);//temporaly store the data
                    studentDataList.set(j, studentDataList.get(j + 1));//set the curent postion
                    studentDataList.set(j + 1, temp);//place the temporaly stored student in the next position
                }
            }
        }

        // Display sorted data
        for (StudentData student : studentDataList) {//loop to itirate student data from student data list
            System.out.println("\nStudent Information:");
            System.out.println("ID: " + student.id);
            System.out.println("Name: " + student.name);
            System.out.println("Module 1: " + student.module1);
            System.out.println("Module 2: " + student.module2);
            System.out.println("Module 3: " + student.module3);
            System.out.println("Average: " + String.format("%.2f", student.average));
            System.out.println("Grade: " + calculateGrade(student.average));

        }
    }


    private static class StudentData {
        String id, name;
        double module1, module2, module3, average;
        // Constructor for the StudentData class
        //initilaize the fields with values
        StudentData(String id, String name, double module1, double module2, double module3, double average) {
            this.id = id;//set the student ID
            this.name = name;
            this.module1 = module1;
            this.module2 = module2;
            this.module3 = module3;
            this.average = average;
        }
    }
    // check the grade of the student
    private static String calculateGrade(double average) {
        if (average >= 80) return "Distinction";
        else if (average >= 70) return "Merit";
        else if (average >= 60) return "Pass";
        else return "Fail";
    }

    //create a method for addinontal options
    public static void Addinontalopotions(Scanner input) {
        //printing the options
        System.out.println("a.Add student name");
        System.out.println("b.Module marks and the rank");
        System.out.println("c.Summary of the student");
        System.out.println("d.Student report");
        System.out.println("e.Exit from additional options");
        String Choice = input.next();// take the input from the user

        switch (Choice) {
            case "a"://creating options
                RegisterStudents(input);// calling the methods
                break;
            case "b":
                AddModuleToStudent(input);
                break;
            case "c":
                DetailedSummary();
                break;
            case "d":
                StudentReport();
                break;
            case "e":
                input.close();
                System.exit(0);// exit the additional options
        }
    }
}

