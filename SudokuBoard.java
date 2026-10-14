import java.util.*;
import java.io.*;


//Sudoku Board class
public class SudokuBoard {
   //fields
   protected String[][] board;
   

   //constructor
   public SudokuBoard(String FileName) {
      board = new String[9][9];
      
      try {
         Scanner input = new Scanner(new File(FileName));
         int row = 0;    
         while (input.hasNextLine() && row < board.length) {
            String line = input.nextLine();
            
            if (line.length() != board.length) {
               System.out.println(FileName + " format doesnt match requirements X_x \n");
               return;
            }
            //adding file values to board
            for (int col = 0; col < board[row].length; col++) {
               board[row][col] = "" + line.charAt(col);
            }
            row++;
         }
      } catch (FileNotFoundException e) {
         System.out.println("Can't load " + FileName + " :(");
      }
   }
   
   
   //helper for toString
   public String printBoard() {
      String sBoard = "+-----------------------+\n";
      for (int r = 0; r < board.length; r++) {
         for (int c = 0; c < 9; c++) {
            if (c == 0 || c == 3 || c == 6) {
               sBoard += "| ";
            }
            sBoard += board[r][c] + " ";
         }
         sBoard += "|\n";    
         if (r == 2 || r == 5) {
               sBoard += "|-------|-------|-------|\n";
         }
      }
      sBoard += "+-----------------------+\n";
      return sBoard;
   }
   
   public boolean isValid() {       
     // row checks
     for(int r = 0; r < board.length; r++){
         Set<String> checkerRow = new HashSet<String>();
         Set<String> duplicateRow = new HashSet<String>();

         for( int c = 0; c < board.length; c++){
             if(checkerRow.contains(board[r][c])){
                 duplicateRow.add(board[r][c]);
             } else{
                 checkerRow.add(board[r][c]);
             }
         }
         if(!duplicateRow.isEmpty()) {
            return false;
         }
     } 
     return true;
   }
   
   //toString
   public String toString() {
      return printBoard();  

   }
}
