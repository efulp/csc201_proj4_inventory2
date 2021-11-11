import java.io.*;
import java.util.Scanner;
import java.lang.Math;  
import java.text.DecimalFormat;

public class Inventory2 {  
    public static void main(String[] args) {  
        int updates = 0;  
        SetTree<String> set = null;  

        if(!argsOK(args))
            System.exit(1);

        int tableSize = Integer.parseInt(args[0]);
        set = new SetTree<String>(tableSize);  

        final long startTime = System.currentTimeMillis();
        updates = readInventoryFile(args[1], set);
        final long endTime = System.currentTimeMillis();
        displayInventoryInfo(set, updates);

        long difference = endTime - startTime;
        System.out.println("Time to process file: " + difference + " msec");
    }  


    private static int readInventoryFile(String inFile, SetTree<String> set) {
        int numUpdates = 0;  

        try {
            Scanner input = new Scanner(new File(inFile));
            while(input.hasNext()) {
                String line = input.nextLine();
                numUpdates += processInventoryLine(line, set);
            }
            input.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println("Error in opening inventory file.");
            e.printStackTrace();
            System.exit(1);
        }

        return numUpdates;
    }


    private static void displayInventoryInfo(SetTree<String> set, int updates) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        System.out.println("Number of inventory udpates: " + updates + ", number of values: " + set.size());  
        System.out.print("Hash table load factor: " + set.loadFactor() + " (" + df.format(set.loadStd()) + ")");  
        System.out.println(", contents: ");
        System.out.println(set);  
    }


    private static int processInventoryLine(String line, SetTree<String> set) {
        int update = 0;
        String[] token = line.split("[ ]+");  
        if(token.length == 2) {
           char action = token[0].charAt(0);
           if(action == 'a' || action == 'A') {
              if(!set.contains(token[1])) update = 1;  
              set.add(token[1]);  
           }  
           else if(action == 'd' || action == 'D') {
              if(set.contains(token[1])) update = 1;  
              set.remove(token[1]);  
           }  
           else
               System.out.println("Bad action: " + line);  
        }  
        return update;
    }


   /**
     * Checks the command line args are correct (1 string)
     *
     * @param args String[] first is inventory file
     * @return true if everyting is OK, false otherwise
     */
    private static boolean argsOK(String[] args) {
        if(args.length < 2) {
            System.out.println("Usage: java program table_size input_name_file ");
            System.out.println("  table_size        hash table size (integer) ");  
            System.out.println("  input_name_file   file containing inventory ");
            return false;
        }

        try {
            Integer.parseInt(args[0]);
        } 
        catch (NumberFormatException e) {
            System.out.println("Usage: java program table_size input_name_file ");
            System.out.println("  table_size MUST be an integer ");  
            return false;  
        }

        return true;
    }

}  


