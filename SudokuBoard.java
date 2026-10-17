import java.util.*;
import java.io.*;


//Sudoku Board class
public class SudokuBoard {
   //fields
   protected String[][] board;
   
   //create a set of all the allowed strings within the board for refrence later.
   Set<String> allowedCharacters = new HashSet<String>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "-", "_", "0"));
   
   //create a set of the Strings representing zero.
   Set<String> zeroCharacters = new HashSet<String>(Arrays.asList(".", "-", "_", "0"));
   
   

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
               if(!allowedCharacters.contains(board[r][c])){
                   return false;
               }
               
               //check the rows for duplicate values.
               if(checkerRow.contains(board[r][c]) && !zeroCharacters.contains(board[r][c])){
                   duplicateRow.add(board[r][c]);
               }
               if (checkerCol.contains(board[c][r]) && !zeroCharacters.contains(board[c][r])){
                   duplicateCol.add(board[c][r]);
               } 
               
               checkerRow.add(board[r][c]);
               checkerCol.add(board[c][r]);
               }
           if(!duplicateRow.isEmpty() || !duplicateCol.isEmpty()) {
               return false;
           }
           
       //miniSquare checker.    
       for(int i = 1; i < 10; i++){
           Set<String> checkerMini = new HashSet<String>();
           Set<String> duplicateMini = new HashSet<String>();
           String[][] currentMini = miniSquare(i);
           
           for(int row = 0; row < currentMini.length; row++){
               for(int col = 0; col < currentMini[row].length; col++){
                   if (checkerMini.contains(currentMini[row][col]) && !zeroCharacters.contains(currentMini[row][col])){
                       duplicateMini.add(currentMini[row][col]);
                   } 
                   
                   checkerMini.add(currentMini[row][col]);
               }
           }
           if(!duplicateMini.isEmpty()) {
               return false;
           }
       }
       } 
       
       return true;
     }
     
    private String[][] miniSquare(int spot) {
        String[][] mini = new String[3][3];
        for(int r = 0; r < 3; r++) {
           for(int c = 0; c < 3; c++) {
              // whoa - wild! This took me a solid hour to figure out (at least)
              // This translates between the "spot" in the 9x9 Sudoku board
              // and a new mini square of 3x3
              mini[r][c] = board[(spot - 1) / 3 * 3 + r][(spot - 1) % 3 * 3 + c];
           }
        }
        return mini;
   }
   //toString
   public String toString() {
      return printBoard();  

   }
}
