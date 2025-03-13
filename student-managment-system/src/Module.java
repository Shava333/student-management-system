import java.util.*;
public class Module {
    private static final int ModuleCount =3;
    private List<String> modulenames = new ArrayList<>();
    private List<Double> modulemarks = new ArrayList<>();
    private double average;

    public void inputmoduldata(Scanner input){
        double total =0;
        for(int j=1; j<4; j++){
            System.out.println("Enter the mark of module:"+j);
            double ModuleMark;
            String ModuleName = ("Module"+j);

            if(input.hasNextDouble()) {
                ModuleMark = input.nextDouble();
                input.nextLine();// To get a new line

                while (true) {
                    if (ModuleMark >= 0 && ModuleMark <= 100) {//check the marks are in the range between 0 to 100
                        break;
                    } else {
                        System.out.println("Enter a valid Mark:");
                        ModuleMark = input.nextDouble();//To take input for module mark
                        input.nextLine();
                    }
                }

                modulenames.add(ModuleName);//call module name method
                modulemarks.add(ModuleMark);//call module mark method
                total += ModuleMark;


            } else{
                System.out.println("Invalid input");
            }



        }
        average = total / ModuleCount;//get the average
        getrank();


    }
    public void getrank(){ //check the grade
        if (average>=80){
            System.out.println("Pass with a Distinction Pass");
        } else if (average>=70){
            System.out.println("Pass with a Merit Pass");
        } else if (average>=60){
            System.out.println("Pass the Module");
        } else{
            System.out.println("Fail the module");
        }

    }
    public List<Double> getModulemarks() {
        return modulemarks;
    }
    public List<String> getModulenames() {
        return modulenames;
    }

    public double getAverage(){

        return average;
    }



}
