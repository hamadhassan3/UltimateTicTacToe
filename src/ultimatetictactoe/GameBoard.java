/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe;

/**
 *
 * @author hamad
 */
public class GameBoard {
    //This class will be singleton
    
    private Player[] players;

    private int currentPlayer;
    
    
    private SmallBoard[][] boards;
    
    
    int state = -1;

    
    public GameBoard(Player human, Player computer){
        
        players = new Player[2];
        
        players[0] = human;
        players[1] = computer;
        currentPlayer = 0;
        
        boards = new SmallBoard[3][3];
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                boards[i][j] = new SmallBoard();
            }
        }
        
        human.setGameBoard(this);
        computer.setGameBoard(this);
    }
    
    
    public String getCurrentMove(){
    
        String symb = players[currentPlayer].getSymbol();
        
        if(currentPlayer == 0)
            currentPlayer = 1;
        else if(currentPlayer == 1)
            currentPlayer = 0;
        
        
        return symb;
        
    }
    
    public Player getCurrentPlayer(){
        return players[currentPlayer];
    }
    
    public Player getOpponent(){
        if(currentPlayer == 0)
            return players[1];
        else
            return players[0];
    }
    
    
    public SmallBoard getBoard(int i, int j){
        return boards[i][j];
    }
    
    public boolean canMakeMove(int currBoardRow, int currBoardCol, int prevMoveRow, int prevMoveCol){
        if(boards[prevMoveRow][prevMoveCol].getState() == -1 && currBoardRow == prevMoveRow && currBoardCol == prevMoveCol){
            return true;
        }
        else if(boards[prevMoveRow][prevMoveCol].getState() == -1){
            return false;
        }
        else if(boards[currBoardRow][currBoardCol].getState() == -1){ 
            return true;
        }
        else{
            return false;
        }
    }
    
    public HoldMove getCompMove(SmallBoard sb, Player comp){
        return sb.getMove(comp);
    }
    
    
    public int checkGameWin(){
        int win = -1;
        //Checking for 1
        if(boards[0][0].getState() == 1 && boards[0][1].getState() == 1 && boards[0][2].getState() == 1){
            win = 1;
        }
        else if(boards[1][0].getState() == 1 && boards[1][1].getState() == 1 && boards[1][2].getState() == 1){
            win = 1;
        }
        else if(boards[2][0].getState() == 1 && boards[2][1].getState() == 1 && boards[2][2].getState() == 1){
            win = 1;
        }
        else if(boards[0][0].getState() == 1 && boards[1][0].getState() == 1 && boards[2][0].getState() == 1){
            win = 1;
        }
        else if(boards[0][1].getState() == 1 && boards[1][1].getState() == 1 && boards[2][1].getState() == 1){
            win = 1;
        }
        else if(boards[0][2].getState() == 1 && boards[1][2].getState() == 1 && boards[2][2].getState() == 1){
            win = 1;
        }
        else if(boards[0][0].getState() == 1 && boards[1][1].getState() == 1 && boards[2][2].getState() == 1){
            win = 1;
        }
        else if(boards[0][2].getState() == 1 && boards[1][1].getState() == 1 && boards[2][0].getState() == 1){
            win = 1;
        }
        //Checking for 2
        else if(boards[0][0].getState() == 2 && boards[0][1].getState() == 2 && boards[0][2].getState() == 2){
            win = 2;
        }
        else if(boards[1][0].getState() == 2 && boards[1][1].getState() == 2 && boards[1][2].getState() == 2){
            win = 2;
        }
        else if(boards[2][0].getState() == 2 && boards[2][1].getState() == 2 && boards[2][2].getState() == 2){
            win = 2;
        }
        else if(boards[0][0].getState() == 2 && boards[1][0].getState() == 2 && boards[2][0].getState() == 2){
            win = 2;
        }
        else if(boards[0][1].getState() == 2 && boards[1][1].getState() == 2 && boards[2][1].getState() == 2){
            win = 2;
        }
        else if(boards[0][2].getState() == 2 && boards[1][2].getState() == 2 && boards[2][2].getState() == 2){
            win = 2;
        }
        else if(boards[0][0].getState() == 2 && boards[1][1].getState() == 2 && boards[2][2].getState() == 2){
            win = 2;
        }
        else if(boards[0][2].getState() == 2 && boards[1][1].getState() == 2 && boards[2][0].getState() == 2){
            win = 2;
        }
        
        boolean draw = true;
        for(int i = 0; i<3; i++)
            for(int j = 0; j<3;j++)
                if(boards[i][j].getState() == -1){
                    draw = false;
                    break;
                }
        if(draw)
            win = 3;
        return win;
    }
    
    public String getWinMessage(){
        if(currentPlayer == 0)
            return players[1].winMessage();
        else
            return players[0].winMessage();

    }
    
    
    public int setMove(int i, int j, int k, int l){
        return boards[i][j].setMove(k, l, this.players[this.currentPlayer]);
    }
    
    
}
