import java.util.*;
/**
 * Hangman3
 * Hangman with single or two player mode. You can play more than one game. It also keeps the score.
 * @author (Caique Trivelato)
 * @version (4/3/2024)
 */
public class Hangman3
{
    //class variablees defined as private
    private static String secretWord;
    private static String disguisedWord;
    private static String lettersRemaining;
    private static int guessesMade;
    private static int incorrectGuesses;
    private static int amountOfWords;
    private static int playersAmount;
    private static String player;
    private static int player1Score;
    private static int player2Score;
    private static int randomNumber;
    private static boolean notDone;
    private static String [] onePlayerGame = new String [amountOfWords];
    private static String [] player1Choice = new String [amountOfWords];
    private static String [] player2Choice = new String [amountOfWords];
    
    //this method is void but expects a string to be sent in and does basic setup
    public static void initialize(String word){
        secretWord = word.toLowerCase().trim();
        lettersRemaining = secretWord;
        disguisedWord = createDisguisedWord(secretWord);
        guessesMade = 0;
        incorrectGuesses = 0;
    }
    
    //This method returns a hidden word and takes in a string that is changed to said hidden word.
    public static String createDisguisedWord(String word){
        word.toLowerCase();
        
        word = word.replace('a', '?');
        word = word.replace('b', '?');
        word = word.replace('c', '?');
        word = word.replace('d', '?');
        word = word.replace('e', '?');
        word = word.replace('f', '?');
        word = word.replace('g', '?');
        word = word.replace('h', '?');
        word = word.replace('i', '?');
        word = word.replace('j', '?');
        word = word.replace('k', '?');
        word = word.replace('l', '?');
        word = word.replace('m', '?');
        word = word.replace('n', '?');
        word = word.replace('o', '?');
        word = word.replace('p', '?');
        word = word.replace('q', '?');
        word = word.replace('r', '?');
        word = word.replace('s', '?');
        word = word.replace('t', '?');
        word = word.replace('u', '?');
        word = word.replace('v', '?');
        word = word.replace('w', '?');
        word = word.replace('x', '?');
        word = word.replace('y', '?');
        word = word.replace('z', '?');
        
        return word;
    }
    
    //This method is void and takes in a character this is the letter guess and checking to see if correct.
    public static void makeGuess(Character c){
        if (Character.isLetter(c)){
            String guess = "" + c;
            guess = guess.toLowerCase();
            int letterPosition = lettersRemaining.indexOf(guess);
            boolean goodGuess = letterPosition > -1;
            while(letterPosition > -1){
                String before = lettersRemaining.substring(0, letterPosition);
                String after = lettersRemaining.substring(letterPosition+1);
                lettersRemaining = before + "#" + after;
                
                before = disguisedWord.substring(0, letterPosition);
                after = disguisedWord.substring(letterPosition+1);
                disguisedWord = before + guess + after;
                
                letterPosition = lettersRemaining.indexOf(guess);
            }
            
            guessesMade++;
            
            if(!goodGuess)
                incorrectGuesses++;
        }else
            System.out.println("Sorry, your guess must be an alphabet character from a to z");
                
        if(incorrectGuesses >=5)
        {
            System.out.println("You lost this round.");
            int calculation = guessesMade * incorrectGuesses;
            System.out.println("Your score in this round is " + calculation + " points.");
            scoreKeeping(player, calculation);
            //there is a call of a method here
        }
    }
    
    //accessor method for the hidden word
    public static String getDisguisedWord(){
        return disguisedWord;
    }
    
    //accessor method for the secret word
    public static String getSecretWord(){
        return secretWord;
    }
    
    //accessor method for the guess
    public static int getGuessCount(){
        return guessesMade;
    }
    
    //this method returns a boolean based on the hidden word being correct
    public static boolean isFound(){
        return secretWord.equals(disguisedWord);
    }
    
    //This is a void method that starts the game
    public static void playGame(){
        if(!isFound()){
            System.out.println("This is player's " + player + " turn.");
            while(!isFound() && incorrectGuesses < 5){
                System.out.println("\nThe disguised word is <" + disguisedWord + ">");
                System.out.println("Guess a letter");
                Scanner reader = new Scanner(System.in);
                String guess = reader.next();
                if(guess.length()!=1)
                    System.out.println("Sorry, bad guess. Need a single letter.");
                else{
                    makeGuess(new Character(guess.charAt(0)));
                }
                System.out.println("Guess made " + guessesMade + " with " + incorrectGuesses + " wrong");
            }
            
            if(isFound())
            {
                System.out.println();
                System.out.println("Congratulations, you found the secret word: " + secretWord);
                int calculation = guessesMade * incorrectGuesses;
                System.out.println("Your score in this round is " + calculation + " points.");
                scoreKeeping(player, calculation);
            }
        }
    }
    

    // start of my work
    // This method starts the program and prompts the user for 1 or 2 players
    public static void main(String[] args)
    {
        System.out.println("This is the Hangman Game. We are going to keep score. Your total mistakes per round is 5. Whoever finishes with the lowest points, wins it.");
        System.out.println("Would you like to play a single player or a two player mode (use '1' or '2', anything else to exit the program): ");
        System.out.println("or a two player mode (use '1' or '2' as): ");
        Scanner keyboard = new Scanner(System.in);
        playersAmount = keyboard.nextInt();
        
        if(playersAmount == 1)
            onePlayerMode();
        else if(playersAmount == 2)
            twoPlayerMode();
        else
        {    
            System.out.println("Exiting the program...");
            System.exit(9);
        }
    }
    
    // This is the single player method to throw the list of words to a round of the game.
    public static void onePlayerMode()
    {
        Scanner keyboard = new Scanner(System.in);
        amountOfWords = 5;
        player = "1";
        
        System.out.println("You chose to play with one player only.");
        System.out.println("So, we are using an specific list of words to start the game.");
        
        String [] onePlayerGame = {"chair", "percent", "ocean", "church", "hammer"};
        
        player = "1";
        notDone = true;
        do
        {
            everyTurn(onePlayerGame, false);
        } while(notDone() == true);
    }
    
    // This is the two player method to collect the amount of words played and the words 
    // to throw the arrays to a round of the game.
    public static void twoPlayerMode()
    {
        System.out.println("You chose to play with two players.");
        System.out.println("Choose how many words would each of you would like to guess?");
        
        Scanner keyboard = new Scanner(System.in);
        amountOfWords = keyboard.nextInt();
        
        String [] player1Choice = new String [amountOfWords];
        String [] player2Choice = new String [amountOfWords];
        
        System.out.println("\f");
        System.out.println("Now, we are going to collect words from the first player.");
        for(int i = 0; i < amountOfWords; i++)
        {
            player1Choice [i] = getWord("1", i+1);
            System.out.println();
        }
        
        System.out.println("\f");
        System.out.println("Now, we are going to collect words from the second player.");
        for(int i = 0; i < amountOfWords; i++)
        {
            player2Choice [i] = getWord("2", i+1);
            System.out.println();
        }
        
        player = "1";
        notDone = true;
        do
        {
            everyTurn(player2Choice, true);
            everyTurn(player1Choice, false);
        } while(notDone() == true);
    }
    
    // This is the method for one turn of the hangman game. 
    public static void everyTurn(String [] array, boolean twoPlayer)
    {
        boolean stop = false;
        
        do    
        {
            System.out.println("\f");
            Scanner keyboard = new Scanner(System.in);
            int count = 0;
            
            randomNumber = getRandom();
    
            while(array[randomNumber].equals(" ") && count<amountOfWords)
            {
                randomNumber = getRandom();
                count++;
                if(count == amountOfWords)
                {
                    System.out.println("You ran out of words.");
                    winner();
                    System.out.println("Thank you for playing. See you soon.");
                    System.exit(0);
                }
            }
            
            initialize(array[randomNumber]);
            playGame();
            
            if(twoPlayer==true)
            {    
                array[randomNumber] = " ";
                player = "2";
                break;
            } else
            {
                stop = true;
                array[randomNumber] = " ";
            }
        } while(stop == false);
    }
    
    // This is the method that checks if the user wants a second round or not.
    public static boolean notDone()
    {   
        System.out.println("Would you like to play another round? (again, write 'y' for yes and anything else to exit the game)");
        
        Scanner keyboard = new Scanner(System.in);
        String keepPlaying = keyboard.next();
        
        if(keepPlaying.equals("y"))
            System.out.println("Perfect. Let's play one more round then!");   
        else
        {
            System.out.println("You decided to stop the game.");
            winner();
            System.out.println("Thank you for playing. See you soon.");
            notDone = false;
        }
        player = "1";
        return notDone;
    }    
    
    // This method get a word from an input of the user and return it 
    // to a location in a list of words created for the two player game mode.
    public static String getWord(String player, int count)
    {
        System.out.println("Player " + player + ", choose your word number " + count + ": ");
        Scanner keyboard = new Scanner (System.in);
        String word = keyboard.nextLine();
        return word;
    }
    
    // This is a method to get a random number and return it to choose a random word 
    // from one of the lists generated for both single and two player game mode.
    public static int getRandom()
    {
        double random = Math.random()*amountOfWords;
        int randomNumber = (int) random;
        return randomNumber;
    }
    
    // This method updates and displays the score of the game and each player.
    public static void scoreKeeping(String player, int points)
    {
        if(player.equals("1"))
        {
            player1Score = player1Score + points;
            System.out.println("Your total score is " + player1Score + ", player 1.");
        } else
        {
            player2Score = player2Score + points;
            System.out.println("Your total score is " + player2Score + ", player 2.");
        }
        System.out.println();
        System.out.println("The current scoreboard looks like this: ");
        System.out.println("Player 1 ----- " + player1Score);
        if(playersAmount == 2)
            System.out.println("Player 2 ----- " + player2Score);
    }
    
    // This method reproduce the final result of the game.
    public static void winner()
    {
        if(playersAmount > 1)
        {
            if(player1Score > player2Score)
                System.out.println("The winner of the game is player 2 by a difference of " + (player1Score - player2Score) + " points.");
            else if(player1Score < player2Score)
                System.out.println("The winner of the game is player 1 by a difference of " + (player2Score - player1Score) + " points.");
            else
                System.out.println("There was a tie. Both players scored " + player1Score + " points.");
        } else
            System.out.println("Your score was " + player1Score + " points.");
    }
}
