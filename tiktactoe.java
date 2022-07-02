import java.util.*;
public class tiktactoe {
    private static  char [][] board = new char[3][3];
    private static int turn = 1;
    public static void main(String [] args){
        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to play Tic Tac Toe?");
        if(input.next().equals("yes"))
        {
            play();
        }
    }

    public static void play(){
        System.out.println();
        char player;
        if(turn % 2 == 0){
            player = 'O';
            System.out.println("It is Player two's turn" + "\n");
        }
        else{
            player = 'X';
            System.out.println("It is Player one's turn" + "\n");
        }

        showBoard();

        int row = inquireRow();
        int col = inquireCol();



        //Redirects player if input will cause an error
        Scanner input = new Scanner(System.in);
        boolean allowed = false;
        while(allowed != true) {
            if ((row <= 2 && row >= 0) && (col <= 2 && col >= 0))
                allowed = true;
            else if(col < 0 || col > 2){
                System.out.println("The selected column is not an available choice. Please try again.");
                col = input.nextInt() - 1;
            }
            else{
                System.out.println("The selected row is not an available choice. Please try again.");
                row = input.nextInt() - 1;
            }
        }

        if(board[row][col] != Character.MIN_VALUE)
        {
            System.out.println("\nUmmmm no you don't. You cheating brat. You imbecile you.\nRethink your actions and then pick a new position.\n");
            row = inquireRow();
            col = inquireCol();
        }

        updateBoard(player,row,col);

        if(check(player,row,col))
        {
            showBoard();
            if(turn % 2 == 0)
                System.out.println("Player 2 Wins!\n");
            else
                System.out.println("Player 1 Wins!\n");
            System.out.println("Would you like to play again?");
            if(input.next().equals("yes"))
            {
                System.out.println("\nWelcome to a new game!");
                for(int r = 0; r < board.length; r++)
                {
                    for(int c = 0; c < board[0].length; c++)
                    {
                        board[r][c] = Character.MIN_VALUE;
                    }
                }
                turn = 1;
                play();
            }
            else
            {
                System.out.println("\nVery well, thank you for playing my game :)");
            }
        }
        else
        {
            turn++;
            play();
        }
    }
    public static int inquireRow(){
        Scanner file = new Scanner(System.in);
        int toReturn = 0;
        try{
            System.out.println("Which row do you want to pick?");
            toReturn = file.nextInt();
        }catch(Exception E){
            System.out.print("Not a valid input. ");
            inquireRow();
        }
        return toReturn - 1;
    }

    public static int inquireCol(){
        Scanner file = new Scanner(System.in);
        int toReturn = 0;
        try{
            System.out.println("Which column do you want to pick?");
            toReturn = file.nextInt();
        }catch(Exception E){
            System.out.print("Not a valid input. ");
            inquireCol();
        }
        return toReturn - 1;
    }

    public static void updateBoard(char player, int row, int col){
        board[row][col] = player;
    }

    public static boolean check(char player, int row, int col){
        //Check Vertical
        if(board[0][col] == player && board[1][col] == player && board[2][col] == player)
            return true;
        //Check Horizontal
        if(board[row][0] == player && board[row][1] == player && board[row][2] == player)
            return true;
        //Check Diagonal going up
        if(board[2][0] == player && board[1][1] == player && board[0][2] == player)
            return true;
        //Check Diagonal going down
        if(board[0][0] == player && board[1][1] == player && board[2][2] == player)
            return true;

        return false;
    }

    public static void showBoard(){
        System.out.println("  1   2   3");
        for(int r = 0; r < board.length; r++)
        {
            for(int c = 0; c < board[0].length; c++)
            {
                if(c == 0)
                    System.out.print(r + 1 + " ");
                if(c != 2)
                    System.out.print(board[r][c] + " | ");
                else
                    System.out.println(board[r][c]);
            }
            System.out.println("  ---------");
        }
        System.out.println();
    }
}
