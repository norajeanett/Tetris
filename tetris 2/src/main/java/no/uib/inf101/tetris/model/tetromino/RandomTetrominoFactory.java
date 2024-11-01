package no.uib.inf101.tetris.model.tetromino;

import java.util.Random;


public class RandomTetrominoFactory implements TetrominoFactory{
    private static final char[] tetrominoShape = {'I', 'O', 'T', 'S', 'Z', 'J', 'L'};
    private final Random random = new Random();

    /**
     * @return returns a random new Tetromino object from one of the shapes.
     */
    @Override
    public Tetromino getNext() {
        int i = random.nextInt(tetrominoShape.length);
        char shape = tetrominoShape[i];
        return Tetromino.newTetromino(shape);
    }
}



