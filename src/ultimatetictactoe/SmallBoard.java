/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author hamad
 */
public class SmallBoard {
    private int[][] arr;
    private int state = -1;
    public SmallBoard(){
    
        //Initializing with zeroes
        arr = new int[3][3];
        for(int i = 0; i<3; i++)
            for(int j = 0; j<3; j++)
                arr[i][j] = 0;
    
    }
    
    public void copyArr(int[][] arr2){
        for(int i = 0; i<3; i++)
            for(int j =0; j<3; j++)
                arr[i][j] = arr2[i][j];
    }
    
    public int setMove(int i, int j, Player p){
    
        if(arr[i][j] == 0){
            if(p.getSymbol().equals("O")){

                arr[i][j] = 1;
                if(checkWin() == 1){
                    return 10;  //increment in score
                }
            }
            else{
                arr[i][j] = 2;
                if(checkWin() == 2){
                    return 10;  //increment in score
                }
            }
        }
        return 0;
    }
    
    public HoldMove getMove(Player comp){
        int compInt = -1;
        int playerInt = -1;
        if(comp.getSymbol().equals("O")){
            compInt = 1;
            playerInt = 2;
        }
        else{
            compInt = 2;
            playerInt = 1;
        }
        

        

        

        
        List<HoldMove> lsMoves = new ArrayList<HoldMove>();
        
        for(int i = 0; i < 3; i++){
            for(int j =0; j<3; j++){
                if(arr[i][j] == 0){
                    lsMoves.add(new HoldMove(i, j));
                }
            }
        }
        
        //Checking if the computer can win with any of the moves
        for(HoldMove hm: lsMoves){
            SmallBoard sb = new SmallBoard();
            sb.copyArr(arr);
            sb.makeMove(hm.i, hm.j, compInt);
            
            if(sb.checkWin() == compInt){
                return hm;
            }
            
        }

        //Checking if the opponent can win with a move
        for(HoldMove hm: lsMoves){
            SmallBoard sb = new SmallBoard();
            sb.copyArr(arr);
            sb.makeMove(hm.i, hm.j, playerInt);
            
            if(sb.checkWin() == playerInt){
                return hm;
            }   
        }
        Random rand = new Random(); 
  

        int randNum = rand.nextInt(lsMoves.size() -1); 
        if(randNum < 0 ){
            randNum = randNum *-1;
        }
        
        return lsMoves.get(randNum);
        
    }
    
    private int checkWin(){
        int win = -1;
        //Checking for 1
        if(arr[0][0] == 1 && arr[0][1] == 1 && arr[0][2] == 1){
            win = 1;
        }
        else if(arr[1][0] == 1 && arr[1][1] == 1 && arr[1][2] == 1){
            win = 1;
        }
        else if(arr[2][0] == 1 && arr[2][1] == 1 && arr[2][2] == 1){
            win = 1;
        }
        else if(arr[0][0] == 1 && arr[1][0] == 1 && arr[2][0] == 1){
            win = 1;
        }
        else if(arr[0][1] == 1 && arr[1][1] == 1 && arr[2][1] == 1){
            win = 1;
        }
        else if(arr[0][2] == 1 && arr[1][2] == 1 && arr[2][2] == 1){
            win = 1;
        }
        else if(arr[0][0] == 1 && arr[1][1] == 1 && arr[2][2] == 1){
            win = 1;
        }
        else if(arr[0][2] == 1 && arr[1][1] == 1 && arr[2][0] == 1){
            win = 1;
        }
        //Checking for 2
        else if(arr[0][0] == 2 && arr[0][1] == 2 && arr[0][2] == 2){
            win = 2;
        }
        else if(arr[1][0] == 2 && arr[1][1] == 2 && arr[1][2] == 2){
            win = 2;
        }
        else if(arr[2][0] == 2 && arr[2][1] == 2 && arr[2][2] == 2){
            win = 2;
        }
        else if(arr[0][0] == 2 && arr[1][0] == 2 && arr[2][0] == 2){
            win = 2;
        }
        else if(arr[0][1] == 2 && arr[1][1] == 2 && arr[2][1] == 2){
            win = 2;
        }
        else if(arr[0][2] == 2 && arr[1][2] == 2 && arr[2][2] == 2){
            win = 2;
        }
        else if(arr[0][0] == 2 && arr[1][1] == 2 && arr[2][2] == 2){
            win = 2;
        }
        else if(arr[0][2] == 2 && arr[1][1] == 2 && arr[2][0] == 2){
            win = 2;
        }
        
        boolean draw = true;
        for(int i = 0; i<3; i++)
            for(int j = 0; j<3;j++)
                if(arr[i][j] == 0){
                    draw = false;
                    break;
                }
        if(draw)
            this.state = 3;
        else
            this.state = win;
        return state;
    }
    
    
    public int getState(){
        
        if(state == -1){
            checkWin();
        }
        
        return state;
    }

    private void makeMove(int i, int j, int val) {
        
        this.arr[i][j] = val;
        
    }
    


    
}
