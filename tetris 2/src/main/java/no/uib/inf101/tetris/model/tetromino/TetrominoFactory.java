package no.uib.inf101.tetris.model.tetromino;

public interface TetrominoFactory {
    /**
     * 
     * @return a random new Tetromino object
     */
   Tetromino getNext(); 
}