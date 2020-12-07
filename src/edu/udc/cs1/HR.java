package edu.udc.cs1;
import edu.udc.cs1.Employee;
import edu.udc.cs1.CS1Utils;
import edu.udc.cs1.EmployeeException;
import java.util.Scanner;
/**
 *
 * @author samuel zewdu
 */
public class HR {
    private Employee[] allEmployees;

    public String[] getHighPaid() {

        String[] highPaid = new String[3];
        for (int i = 0; i < allEmployees.length - 1; i++) {
            // Find the minimum in the list[i..list.length−1]
            Employee currentMin = allEmployees[i];
            int currentMinIndex = i;

            for (int j = i + 1; j < allEmployees.length; j++) {
                if (currentMin.getSalary() < allEmployees[j].getSalary()) {
                    currentMin = allEmployees[j];
                    currentMinIndex = j;
                }
            }

            if (currentMinIndex != i) {
                allEmployees[currentMinIndex] = allEmployees[i];
                allEmployees[i] = currentMin;
            }
        }

        Employee emp = allEmployees[0];
        highPaid[0] = emp.getFirstName();
        highPaid[1] = emp.getLastName();
        highPaid[2] = emp.getEmail();


        return highPaid;
    }
    public void sortWorkers() {

        if (allEmployees == null)
            return;

        if (allEmployees.length > 0) {

            for (int i = 0; i < allEmployees.length - 1; i++) {
                Employee currentMin = allEmployees[i];
                int currentMinIndex = i;

                for (int j = i + 1; j < allEmployees.length; j++) {
                    if (currentMin.getEmail().compareTo(allEmployees[j].getEmail()) < 0) {
                        currentMin = allEmployees[j];
                        currentMinIndex = j;
                    }
                }


                if (currentMinIndex != i) {
                    allEmployees[currentMinIndex] = allEmployees[i];
                    allEmployees[i] = currentMin;
                }
            }

            for (int i = 0; i < 2; i++) {
                Employee emp = allEmployees[i];
                System.out.println(emp.getFirstName() + " " + emp.getLastName() + " " + emp.getEmail());
            }
            for (int i = allEmployees.length - 2; i < allEmployees.length; i++) {
                Employee emp = allEmployees[i];
                System.out.println(emp.getFirstName() + " " + emp.getLastName() + " " + emp.getEmail());
            }
        }
    }

//    public String tryAnswer(int num, String answer) {
//        return "";
//    }

    public int findEmp(String firstName, String lastName) {

        int index = -1;
        for (int i = 0; i < allEmployees.length; i++) {
            Employee emp = allEmployees[i];
            if (emp.getFirstName().toLowerCase().equals(firstName.toLowerCase()) && emp.getLastName().toLowerCase().equals(lastName.toLowerCase())) {
                index = i;
            }
        }
        if (index == -1) {
            throw (new EmployeeException());
        }
        return index;
    }
    public void processEmps() {
        CS1Utils cs1utils = new CS1Utils();
        String version = cs1utils.getStaticVersion();
        //        System.out.println(version);

        //Part 2 starts here
        Scanner sc = new Scanner(System.in);
        String questions = cs1utils.getQuestionStack();
        System.out.println("\t\t---------Questions---------\n" + questions);
   
        System.out.print("Question number: ");
        int qNum = sc.nextInt();
        sc.nextLine();
        System.out.print("Answer: ");
        String qAns = sc.nextLine();
        //Part 2 ends here
        
        // try:catch without if:else
        try {
            String tryAns = cs1utils.tryAnswer(qNum, qAns);
            System.out.println(tryAns);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Your question number is invalid.");
        } catch (EmployeeException e) {
            System.out.println("Your answer is incorrect.");
        }

        this.allEmployees = cs1utils.getAllEmployees();
        this.sortWorkers();

        System.out.println("Enter employee first name:");
        String firstName = sc.nextLine();
        System.out.println("Enter Employee last name: ");
        String lastName = sc.nextLine();

        //test getHighPaid()
        String[] hiVals = getHighPaid();
        System.out.println("Highest salaried person is " +
            hiVals[0] + " " +
            hiVals[1] +
            " email:" + hiVals[2]);
        //part 1
        try {
            int empIndex = findEmp(firstName, lastName);
            Employee emp = allEmployees[empIndex];
            System.out.println(emp.getFirstName() + " " + emp.getLastName() + " email:" + emp.getEmail());
        } catch (EmployeeException e) {
            System.out.println("Employee not found!");
        }


    }

    public static void main(String[] args) {
        HR hr = new HR();
        hr.processEmps();
    }

}