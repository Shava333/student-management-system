import java.util.Scanner;
public class Student {
    public void studentdetails(){
        Scanner input = new Scanner(System.in);

        if (Main.studentCount >= Main.maxAmount){
            System.out.println("There are no slots to register");
            return;
        }
        while(true){
            System.out.println("Enter the student ID!(w1234567)");
            String StudentId = input.next().toLowerCase();
            Main.studentid[Main.studentCount]=StudentId;
            if (Main.duplicateid(StudentId)) {
                System.out.println("This ID has been registered already please enter a new ID!!");
                continue;
            }


            if (StudentId.length() == 8) {
                if (StudentId.charAt(0) == 'w') {
                    break;

                } else {
                    System.out.println("Please enter in the correct format(w1234567) ");
                }
            } else {
                System.out.println("The length is invalid please input correct id ");
            }
        }
        while(true){
            System.out.println("Enter The Student Name:");
            String StudentName = input.next().toLowerCase();
            Main.studentname[Main.studentCount]=StudentName;

            if (Main.duplicatename(StudentName)) {
                System.out.println("There is a student registered in this name please check again ");
                continue;

            }
            Main.studentCount++;

            System.out.println("The Student is registered successfully");
            break;
        }


    }


}