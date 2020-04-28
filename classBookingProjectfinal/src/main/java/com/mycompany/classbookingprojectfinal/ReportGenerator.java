import java.util.ArrayList;
import java.util.Scanner;

public class ReportGenerator {
    public static void monthlyReport() {
        String id;
        while (true) {
            System.out.println("Id \t\t\tName\n" +
                    "2\t\t\tFeb\n" +
                    "3\t\t\tMar\n" +
                    "4\t\t\tApril\n");


            Scanner scan = new Scanner(System.in);
            System.out.print("Enter the month id for monthly report:");
            id = scan.nextLine();
            if (id.length() == 1) {
                id = "0" + id;
            }
            try {
                if (Integer.parseInt(id) < 2 || Integer.parseInt(id) > 5) {
                    System.out.println("Invalid input");
                    continue;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }


            System.out.println("ID\t\t\tDate \t\tTime\t\t\tClass Name \t\tTotal Student\t\tAverage Rating");
            int found = 0;
            for (int i = 0; i < 300; i++) {
                for (int j = 0; Main.monthlyTimeTableArray[i][0] != null && j < 3; j++) {
                    for (int k = 0; k < 3; k++) {

                        //month matched
                        if (Main.monthlyTimeTableArray[i][j][k].date.substring(3, 5).equals(id)) {//18/04/2020

                            found = 1;
                            //total students
                            int students = 0;
                            for (int l = 0; Main.monthlyTimeTableArray[i][0] != null && l < Main.monthlyTimeTableArray[i][j][k].bookedUserId.length; l++) {
                                if (Main.monthlyTimeTableArray[i][j][k].bookedUserId[l] != 0) {
                                    students++;
                                }
                            }

                            
                            

                            System.out.println(Integer.toString(i) + Integer.toString(j) + Integer.toString(k) + "      " + Main.monthlyTimeTableArray[i][j][k].date + "      " + Main.monthlyTimeTableArray[i][j][k].time + "      " + Main.monthlyTimeTableArray[i][j][k].name + "         " + students + "                  " );//Date \t\tClass Name \t\tTotal Student\t\tAverage Rating");
                        }
                    }
                }
            }
            if (found == 0) {
                System.out.println("Given Month Not Matched");
            }
            break;
        }

    }

    
}
