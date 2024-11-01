package no.uib.inf101.tetris.view;

//import java.util.ArrayList;
//import java.util.List;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.model.GameState;
//import no.uib.inf101.grid.Grid;
import no.uib.inf101.grid.GridCell;
//import no.uib.inf101.tetris.model.TetrisBoard;



public interface ViewableTetrisModel {

    public GridDimension getDimension(); 

    public Iterable<GridCell<Character>> getTilesOnBoard();

    public Iterable<GridCell<Character>> getFallingPiece();

    public boolean moveTetromino(int deltaRow, int deltaCol);

    public boolean rotateTetromino();

    public void dropTetromino();

    GameState getGameState();
    
    void clockTick();
    
    public int getScore();



    
}

       
  

         

        

 
    
