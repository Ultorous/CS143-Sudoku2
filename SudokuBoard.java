import java.util.*;
import java.io.*;


//Sudoku Board class
public class SudokuBoard {
   //fields
   protected String[][] board;
   
   //create a set of all the allowed strings within the board for refrence later.
   Set<String> allowedCharacters = new HashSet<String>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "-", "_", "0"));
   
   

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
           Set<String> checkerCol = new HashSet<String>();
           Set<String> duplicateCol = new HashSet<String>();

           for( int c = 0; c < board.length; c++){
               //check for characters which are not allowed in the board.
               if(!allowedCharacters.contains(board[c][r])){
                   return false;
               }
               
               //check the rows for duplicate values.
               if(checkerRow.contains(board[c][r])){
                   duplicateRow.add(board[c][r]);
               } else if (checkerCol.contains(board[r][c])){
                   duplicateCol.add(board[r][c]);
               } else {
                   checkerRow.add(board[c][r]);
                   checkerCol.add(board[r][c]);
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
