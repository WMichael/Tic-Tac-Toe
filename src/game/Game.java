package game;

// Holds game state
public class Game {
	private Integer xWins = 0; 
	private Integer oWins = 0; 
	private Integer draws = 0;
	private Integer noOfGamesPlayed = 0; 
	private Integer noOfTilesUsed = 0; // if this reaches 6 then there is a draw. 
	private boolean isWon = false; 
	private boolean currentTurn = true;
	private char[][] board = {{'_','_','_'},{'_','_','_'},{'_','_','_'}};
	
	// True = X and False = O
	public void win(boolean player) {
		if(player == false) {
			this.xWins++; 
		}
		else {
			this.oWins++;
		}
	}
	
	public Integer getNoOfGames() {
		return this.noOfGamesPlayed;
	}
	public Integer getNoOfXWins() {
		return xWins;
	}
	public Integer getNoOfOWins() {
		return oWins;
	}
	public Integer getNoOfDraws() {
		return draws;
	}
	public boolean getCurrentTurn() {
		return this.currentTurn;
	}
	
	public void displayBoard() {
		System.out.println(" ");
		for (int i=0; i< board.length; i++) {
			System.out.println(board[i][0] + " " + board[i][1] + " " + board[i][2]);
		}
		System.out.println(this.noOfTilesUsed + "/9 tiles used.");
	}
	
	// Changes current player, updates board array and checks if game is won. If won, result is given in a string.
	public String turnPlayed(int col, int row, boolean currentPlayer) {
		if (this.currentTurn == true) {
			board[col][row] = 'X';
			this.currentTurn = false;
		}
		else {
			board[col][row] = 'O';
			this.currentTurn = true;
		}
		
		this.noOfTilesUsed++;
		displayBoard(); // Used for debugging.
		
		//Check if current turn wins the game. 
		int n = 3;
		//Check Column 
        for(int i = 0; i < n; i++){
            if(board[col][i] != board[col][row])
                break;
            if(i == n-1){ //If it gets to the last element in the column then it means all elements are the same. 
                System.out.println("Win");
                isWon = true;
            }
        }
        //Check Row
        for(int i = 0; i < n; i++){
            if(board[i][row] != board[col][row])
                break;
            if(i == n-1){
                System.out.println("Win");
                isWon = true;
            }
        }
        //Check tLeft>bRight Diag 
        if(col == row) {
        	for(int i = 0; i < n; i++) {
	        	if(board[i][i] != board[col][row])
	        		break;
		        if(i == n-1) {
		        	System.out.println("Win");
		        	isWon = true;
		        }
        	
        	}
        }
        //Check bLeft>tRight Diag 
        if(col + row == n - 1){
        	for(int i = 0;i<n;i++){
        		if(board[i][(n-1)-i] != board[col][row])
        			break;
                if(i == n-1){
                	System.out.println("Win");
                	isWon = true;
                }
            }
        }   
        
        if(this.noOfTilesUsed == 9) {
        	this.isWon = true;
        }
        
        // If the game is won
        if(isWon == true) { 
        	char[][] newBoard = {{'_','_','_'},{'_','_','_'},{'_','_','_'}};
    		board = newBoard;
    		this.currentTurn = true;
    		String result = "";

    		//Update scores
    		if(this.noOfTilesUsed == 9) {
    			this.draws++;
    			result = "draw";
    			System.out.println("Draw has happened.");
    		}
    		else {
	    		if (currentPlayer == true) {
	    			this.xWins++;
	    			result = "xWin";
	    		System.out.println("X win has happened.");
	    		}
	    		else {
	    			this.oWins++;
	    			result = "oWin";
	    		System.out.println("O Win has happened.");
	    		}
	    		
    		}
    		this.noOfTilesUsed = 0;
    		this.isWon = false;
    		displayBoard();
        	return result;
        	
        }
        else {
        	return "";
        }
	}
	
	public void reset() {
		this.xWins = 0;
		this.oWins = 0;
		this.draws = 0; 
		this.noOfTilesUsed = 0;
		this.noOfGamesPlayed = 0;
		this.currentTurn = true;
		char[][] newBoard = {{'_','_','_'},{'_','_','_'},{'_','_','_'}};
		board = newBoard; 
		displayBoard();
	}
}
