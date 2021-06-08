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
public class Player {
    private String symbol;
    private String name;
    
    public int score = 0;
    
    private GameBoard gameboard;
    
    public Player(String symbol){
        this.symbol = symbol;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getSymbol(){
        return symbol;
    }
    
    
    public void setGameBoard(GameBoard gameboard){
        this.gameboard = gameboard;
    }
    
    public GameBoard getBoard(){
        return gameboard;
    }
    
    public String winMessage(){
        return name + " has won!";
    }
    public String getName(){
        return name;
    }
}
