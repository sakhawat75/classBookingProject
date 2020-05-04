
import java.util.Arrays;

import static org.junit.Assert.*;

import org.junit.*;
import java.io.*;
import java.util.*;


public class Tester {
    @Before
    public void run() {
        Main.loadUsersFromList();
        Main.loadExercisesFromFile();

    }

    @Test
    public void testUserExist() {//test1
        System.out.println("\n\nTesting User Exit: ");
        String testUserName = "sakhawat";
        boolean found = false;
        for (int i = 0; i < Main.userList.size(); i++) {
            if (Main.userList.get(i).equals(testUserName)) {
                found = true;
                System.out.println("User found");
                System.out.println("UserExist Test PASSED");
                break;
            }
        }

        assertEquals(true, found);

    }

    @Test
    public void testDataFileExit() {
        System.out.println("\n\nTesting data.txt Exit: ");
        String testFileName = "data.txt";

        boolean found = false;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("data.txt"));
            found = true;
        } catch (Exception e) {
            found = false;
            System.out.println("data.txt file FOUND");
            System.out.println("File Test PASSED");

        }

        assertEquals(true, found);
    }


    //-test programming is fetching data correct from txt file
    @Test
    public void testClassBooked() {

        String id = "011";
        boolean found = false;

        int i = 0, j = 0, k = 0;
        try {
            if (id.length() == 4) {
                i = Integer.parseInt(id.substring(0, 2));
                j = Integer.parseInt(id.substring(2, 3));
                k = Integer.parseInt(id.substring(3, 4));
            }
            if (id.length() == 3) {
                i = Integer.parseInt(id.substring(0, 1));
                j = Integer.parseInt(id.substring(1, 2));
                k = Integer.parseInt(id.substring(2, 3));

            }
        } catch (Exception e) {
            System.out.println("Invalid input");


        }


        for (int l = 0; l < Main.monthlyTimeTableArray[i][j][k].bookedUserId.length; l++) {
            if (Main.monthlyTimeTableArray[i][j][k].bookedUserId[l] == Main.userId) {
                found = true;
                System.out.println("Your booking found");
                System.out.println("Booking Test PASSED");

                break;
            }

        }
        assertEquals(true, found);
    }


    //-test attended class  is removed from booked class after reviewing
    @Test
    public void testAttendedClassRemoved() {
        int i = 0, j = 0, k = 0;
        boolean found = false;
        String in = "011";
        String msg = "masf";
        int rat = 2;
        if (in.length() == 4) {
            i = Integer.parseInt(in.substring(0, 2));
            j = Integer.parseInt(in.substring(2, 3));
            k = Integer.parseInt(in.substring(3, 4));
        }
        if (in.length() == 3) {
            i = Integer.parseInt(in.substring(0, 1));
            j = Integer.parseInt(in.substring(1, 2));
            k = Integer.parseInt(in.substring(2, 3));

        }

        Main.monthlyTimeTableArray[i][j][k].rating[rat - 1]++;

        for (int l = 0; Main.monthlyTimeTableArray[i][0] != null && l < Main.monthlyTimeTableArray[i][j][k].bookedUserId.length; l++) {
            if (Main.monthlyTimeTableArray[i][j][k].bookedUserId[l] == Main.userId) {
                Main.monthlyTimeTableArray[i][j][k].bookedUserId[l] = -1;

                System.out.println("AttendedClass Removed from booked classes found");
                System.out.println("AttendedClassRemoved Test PASSED");
                found = true;

                break;

            }
        }


        assertEquals(true, found);

    }

    //-test monthly champion report calculating correctly
    @Test
    public void testRemoved() {
        String id;


        id = "2";
        if (id.length() == 1) {
            id = "0" + id;
        }


        ArrayList<String> countList = new ArrayList<>();

        double totalIncome = 0;

        for (int i = 0; i < 300; i++) {
            for (int j = 0; Main.monthlyTimeTableArray[i][0] != null && j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    //month matched
                    if (!countList.contains(Main.monthlyTimeTableArray[i][j][k].name) && Main.monthlyTimeTableArray[i][j][k].date.substring(3, 5).equals(id)) {//18/04/2020
                        totalIncome += Main.getClassMonthlyIncome(id, Main.monthlyTimeTableArray[i][j][k].name);

                        countList.add(Main.monthlyTimeTableArray[i][j][k].name);
                    }
                }
            }
        }
        double expected =840;
        assertEquals(expected,totalIncome,0.001);


    }


}
