// Programmer : Jake Coto
// Date: 10/19/2021-11/8/2021-12/13/2021
// Class: CS141
// Lab: MyCalendar

  import java.io.*;
  import java.util.*;
   // I spent a total of 18 hours on this program.
   // This program will display a menu for a calendar.
   // It is an interactive calendar program with each menu option having a different effect.
   
   public class MyCalendar {

      public static final int CELLS_IN_ROW = 7; // Sets number of cells in a row 
   
      public static final int NUMBER_OF_DAYS = 31; // Sets the number of days in a month
   
      public static final int YEAR_START_CELL = 5; // The day that this year started
   
      public static final int SIZE = 6; // Sets the sizing scale for calendar
      
      public static String [][] calendarEvents = new String[12][];
      

      public static void main(String[] args) throws FileNotFoundException {
         Scanner ev = new Scanner (new File("calendarEvents.txt"));
         Scanner input = new Scanner (System.in);
         Scanner nEvent = new Scanner (System.in);
   
            for (int i = 0; i < 12; i++) {
             calendarEvents[i] = new String[daysInMonth(i+1)];
}

         while (ev.hasNextLine()) {
            String line = ev.nextLine();
            Scanner lineScan = new Scanner(line);
            String day = lineScan.next();
            String name = lineScan.next();
            int d = dayFromDate(day);
            int m = monthFromDate(day);
            calendarEvents[m - 1][d - 1] = name;
}
         System.out.println("Thanks for using my Calendar program!");
         String command = printMenu(input);

            int month = -1;
            int day = -1;
            String name = "";
            String date = "";
         
      // Menu commands
      while(!command.equals("q")) { // Exit program
         if(command.equals("e")) { // Enter desired date
            date = getDate(input);
            month = monthFromDate(date);
            day = dayFromDate(date);
            drawMonth(month, day);
            displayDate(month, day);
            
       } else if(command.equals("fp")) {
            System.out.println("Enter a name for the newly created file: ");
            String fileName = input.next(); // Assigns next input as file name
            PrintStream out = new PrintStream(new File(fileName));
            System.setOut(out);
            System.out.println();
            return;
       
       } else if(command.equals("ev")) {
            System.out.println("Enter the event date: ");
            String eName = nEvent.nextLine();
            System.out.println("Enter the event name: ");
            String line = nEvent.nextLine();
            Scanner lineScan = new Scanner(line);
            int d = dayFromDate(eName);
            int m = monthFromDate(eName);
            calendarEvents[m - 1][d - 1] = eName;
     
       } else if(command.equals("t")) { // Today's date
            date = getToday();
            month = monthFromDate(date);
            day = dayFromDate(date);
            drawMonth(month, day);
            displayDate(month, day);
       } else if(command.equals("n")) { // Next month
            if(month == -1) {
            System.out.println("Select a command to display desired calendar.");
         
       } else {
            if(month == 12) {
            month = 1;
       } else {
            month++;
}
            drawMonth(month, day);
            displayDate(month, day);
}
       } else if(command.equals("p")) { // Previous month
            if(month == -1) {
            System.out.println("Select a command to display desired calendar.");
       } else {
            if(month == 1) {
            month = 12;
       } else {
            month--;
}
            drawMonth(month, day);
            displayDate(month, day);
}
       } else { // Invalid command
            System.out.println("Invalid input. Try again. (e, t, n, p, fp, ev or q)");
}
            command = printMenu(input);
   }
}
      
      // Scanner to read the users input to invoke an action from the menu
      public static String printMenu(Scanner input) {
         System.out.println("Please type a command");
         System.out.println("    \"e\" to display a date of your choosing. Use month/day format.");
         System.out.println("    \"t\" to get todays date.");
         System.out.println("    \"n\" to display the next month");
         System.out.println("    \"p\" to display the previous month");
         System.out.println("    \"q\" to quit the program");
         System.out.println("    \"fp\" to save calendar to a text file.");
         System.out.println("    \"ev\" to save a new event to calendar.");
      return input.next();
     }
      // To view a specific date that the user inputs (command e)
      public static String getDate(Scanner input) {
         System.out.print("Enter a date: ");
         String date = input.next();
         return date;
     }
      // To view todays date (command t)
      public static String getToday() {
         String date = "";
         Calendar today = Calendar.getInstance();
         date += today.get(Calendar.MONTH) + 1;
         date += "/" + today.get(Calendar.DATE);
         return date;
     }
      // Turns the month value into an int
      public static int monthFromDate(String date) {
         int index = date.indexOf("/");
            String month = date.substring(0, index);
            return Integer.parseInt(month);
     }
      // Turns the day value into an int
      public static int dayFromDate(String date) {
         int index = date.indexOf("/");
            String day = date.substring(index + 1);
            return Integer.parseInt(day);
     }
      // Prints the month and day of selected date
      public static void displayDate(int month, int day) {
         System.out.println("Month: " + month);
         System.out.println("Day: " + day);
     }
      // Assigns value to the month and day as an integer
      public static void drawMonth(int month, int day) {
         for(int i = 0; i < (SIZE * CELLS_IN_ROW) / 2 - 1; i++) {
            System.out.print(" ");
}
         System.out.println(month);
            
      for(int i = 0; i < CELLS_IN_ROW; i++) {
         for(int j = 0; j < SIZE / 2 - 1; j++) {
            System.out.print(" ");
}
            if(i == 0) {
               System.out.print(" S ");
          } else if (i == 1) {
               System.out.print(" M ");
          } else if (i == 2) {
               System.out.print(" T ");
          } else if (i == 3) {
               System.out.print(" W ");
          } else if (i == 4) {
               System.out.print(" T ");
          } else if (i == 5) {
               System.out.print(" F ");
          }  else {
               System.out.print(" S ");
}
         for(int j = 0; j < SIZE / 2 - 2; j++) {
            System.out.print(" ");
   }
}
            System.out.println();;
         for(int i = 0; i < SIZE * CELLS_IN_ROW + 1; i++) {
            System.out.print("=");
}
            System.out.println();
         int startDay = startDay(month);
         int days = daysInMonth(month);
         int row = 0;
         while(row * CELLS_IN_ROW - startDay + 2 <= days) {
            drawRow(row, startDay, days, day, month);
            row++;
   }
}
      // Displays the start day for the month, number of days in the month, and todays date with **
      public static void drawRow(int row, int startDay, int days, int day, int month) {
            drawNumberRow(row, startDay, days, day, month);
         for(int i = 0; i < SIZE / 2 - 1; i++) {
            System.out.print("|");
         for (int j = 0; j < CELLS_IN_ROW; j++) {
         for (int k = 0; k < SIZE - 1; k++) {
            System.out.print(" ");
}
            System.out.print("|");
}
            System.out.println();
}
         for(int j = 0; j < SIZE * CELLS_IN_ROW + 1; j++) {
            System.out.print("=");
}
            System.out.println();
}
      public static void drawNumberRow(int row, int startDay, int days, int day, int month) {
            System.out.print("|");
         if(row == 0) { // Creates blank number of cells depending on startDay of week
         int rowStart = 1;
         for(int i = 0; i < CELLS_IN_ROW - (CELLS_IN_ROW - startDay + 1); 
            i++) {
         for (int k = 0; k < SIZE - 1; k++) {
            System.out.print(" ");
}
            System.out.print("|");
}
         for (int j = 0; j < CELLS_IN_ROW - startDay + 1; j++) {
            System.out.print(" " + rowStart + " ");
            if(calendarEvents[month - 1][rowStart - 1] != null){
            System.out.print(calendarEvents[month - 1][rowStart - 1]);
            }
         int numLength = (rowStart + "").length();
         for (int k = 0; k < SIZE - numLength - 3; k++) {
         if(rowStart == day) {
            System.out.print("*"); 
          } else {
            System.out.print(" ");
   }
}
            System.out.print("|");
            rowStart++;
}
            System.out.println();
       } else {
         int rowStart = row * CELLS_IN_ROW - startDay + 2;
         for (int j = 0; j < CELLS_IN_ROW; j++) {
         int numLength = 0;
         if(rowStart <= days) {
            System.out.print(" " + rowStart + " ");
            if(calendarEvents[month - 1][rowStart - 1] != null){
            System.out.print(calendarEvents[month - 1][rowStart - 1]);
}

            numLength = (rowStart + "").length();
          } else {
            numLength = -2;
}
         for (int k = 0; k < SIZE - numLength - 3; k++) {
            if(rowStart == day && rowStart <= days) {
               System.out.print("*"); // Marks today on calendar
           } else {
               System.out.print(" ");
   }
}
               System.out.print("|");
               rowStart++;
}
               System.out.println();
   }
}
      // Decides number of days in month based off of its integer
      public static int daysInMonth(int month) {
         if(month == 4 || month == 6 || month == 9 || month == 11) {
      return 30;
       } else if (month == 2) {
      return 28;
       } else {
      return 31;
   }
}
      // Decides which day of week the month starts
      public static int startDay(int month) {
         int day = YEAR_START_CELL + 1;
            for(int i = 1; i < month; i++) {
            day  = (day + daysInMonth(i) % 7) % 7;
}
            if(day == 0) {
            day = 7;
}
         return day;
   }
}




      

      
            
      

      
            
   