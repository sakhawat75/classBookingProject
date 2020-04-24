/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg7com1025c_work;

/**
 *
 * @author sakhawat

 */
public class Main{
	public static void mainMenu() {
	        boolean exits = false;

	        while (!exits) {
	            Scanner scan = new Scanner(System.in);
	            System.out.println(
	                    "\n\n1.\tBook group exercise \n" +
	                            "2.\tAttend your boked class\n" +
	                            "3.\tChange Previous booking\n" +
	                            "4.\tMonthly class report\n" +
	                            "5.\tMonthly champion class report\n" +
	                            "6.\tExit\n");

	            System.out.println("Enter your choice::");
	            int choice = -1;
	            try {
	                choice = scan.nextInt();
	            } catch (Exception e) {
	                System.out.println("Invalid Input");
	                continue;
	            }


	            switch (choice) {
	                case 1://Book group excercise
	                    bookGroupExcerciseMenu(false);
	                    break;
	                case 2://attend your booked class/review
	                    attendBookedClass();
	                    break;
	                case 3://change previous booking
	                    changeBooking();
	                    break;
	                case 4://monthly report
	                    monthlyReport();
	                    break;
	                case 5:
	                    monthlyChampionReport();
	                    break;
	                case 6:
	                    exits = true;
	                    break;

	                default:
	                    System.out.println("Incorrect input!!! Please re-enter choice from our menu");
	            }

	            if (exits)
	                break;
	        }
	        System.out.println("Program ended .. .");
	        System.exit(1);


	    }
}