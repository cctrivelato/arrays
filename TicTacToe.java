import java.util.*;
/**
 * TicTacToe
 * This is the class that displays tic tac toe game with a 2D board created with an array
 * @author (Caique Trivelato)
 * @version (4/3/2024)
 */
public class TicTacToe
{
    // Use a 3 X 3 (two-dimensional) array for the game board.
    
    private static char[] [] board = new char [3][3];
    private static char turn;
    private static int row;              // Loop controls to 
    private static int col;              // display the board
    private static int turnRow;          // User input to
    private static int turnCol;          // select move
    private static boolean entryError;
    private static boolean anotherGame = true;
    private static char repeat;          // User input: y or Y to repeat
    private static int entryCount = 0;   // Game ends when board is full
                                         // (when entryCount = 9);
    public static void main(String [] args)
    {
        Scanner keyboard = new Scanner(System.in);
        
        while(anotherGame)
        {
            newGame();
            
            while(!winner())
            {
                writeBoard();
                getMove();
            }
            
            System.out.println("Another game? Enter Y or y for yes.");
            repeat = keyboard.next().charAt(0);
            if(repeat == 'y' || repeat == 'Y')
                anotherGame = true;
            else
                anotherGame = false;
        }
    }
    
    public static void writeBoard()
    {
        System.out.println("-------------------");
        System.out.println("|R\\C| 1 | 2 | 3 |");
        System.out.println("-------------------");
        for(row = 0; row < 3; ++row)
        {
            System.out.println("| " + (row + 1)
                            + " | " + board[row][0]
                            + " | " + board[row][1]
                            + " | " + board[row][2]
                            + " | ");
            System.out.println("-------------------");
        }
    }
    
    public static void getMove()
    {
        entryError = true;  // Will change to false if valid row
                            // and column numbers are entered.
        Scanner keyboard = new Scanner(System.in);
        
        while(entryError)
        {
            System.out.println();
            System.out.println(turn + "'s turn.");
            System.out.println("Where do what your " + turn + " placed?");
            System.out.println("Please enter row number and column number"
                                      + " separated by a space.");
            System.out.println();                          
            
            turnRow = keyboard.nextInt();
            turnCol = keyboard.nextInt();
            System.out.println("You have entered row #" + turnRow);
            System.out.println("          and column #" + turnCol);
            
            // Check for proper range (1, 2, or 3)
            
            if (turnRow < 1 || turnRow > 3 || turnCol < 1 || turnCol > 3)
            {
                System.out.println("Invalid entry: try again.");
                System.out.println("Row & columns numbers must be either 1, 2, or 3.");
            }
            
            // Check to see if it is already occupied
            // Adjust turnRow and turnCol for 0-numbering in array
            
            else if (board[turnRow - 1][turnCol - 1] == 'X' || board[turnRow - 1][turnCol -1] == 'O')
            {
                System.out.println("That cell is already taken.");
                System.out.println("Please make another selection.");
            }
            
            else // Valid entry
            {
                entryError = false;
                System.out.println("Thank you for your selection.");
                board[turnRow - 1][turnCol - 1] = turn;
                ++entryCount;
            }
        }
    }
    
    public static boolean winner()
    {
        // Row checks
        
        for(row = 0; row < 3; ++row)
        {
            if(board[row][0] == turn)
            {
                if(board[row][1] == turn)
                {
                    if(board[row][2] == turn)
                    {
                        System.out.println();
                        System.out.println(turn + " IS THE WINNER!!!");
                        writeBoard();
                        return true;
                    }
                }
            }
        }
        
        // Column checks
        for(col = 0; col < 3; ++col)
        {
            if(board[0][col] == turn)
            {
                if(board[1][col] == turn)
                {
                    if(board[2][col] == turn)
                    {
                        System.out.println();
                        System.out.println(turn + " IS THE WINNER!!!");
                        writeBoard();
                        return true;
                    }
                }
            }
        }
        
        //Diagonal checks
        
        if(board[0][0] == turn)
            {
                if(board[1][1] == turn)
                {
                    if(board[2][2] == turn)
                    {
                        System.out.println();
                        System.out.println(turn + " IS THE WINNER!!!");
                        writeBoard();
                        return true;
                    }
                }
            }
        
        if(board[0][2] == turn)
        {
            if(board[1][1] == turn)
            {
                if(board[2][0] == turn)
                {
                    System.out.println();
                    System.out.println(turn + " IS THE WINNER!!!");
                    writeBoard();
                    return true;
                }
            }
        }
        
        //These lines execute only if there is no winner.
        
        // End game if board is full
        
        if(entryCount >= 9)
        {
            System.out.println();
            System.out.println("Draw: no winner and board is full.");
            writeBoard();
            return true;
        }
        
        else    // Next player's turn
        {
            if(turn == 'X')
                turn = 'O';
            else
                turn = 'X';
            return false;
        }
    }
    
    public static void newGame()
    {
        System.out.println();
        System.out.println("New Game: X goes first.");
        turn = 'O';  // Turn will change to X when winner() is called
        
        // Clear the board
        
        for(row = 0; row < 3; ++row)
        {
            for(col = 0; col < 3; ++col)
                board[row][col] = ' ';
        }
        entryCount = 0;
        System.out.println();
    }
}
