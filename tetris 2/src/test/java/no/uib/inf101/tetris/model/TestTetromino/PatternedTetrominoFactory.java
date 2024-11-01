package no.uib.inf101.tetris.model.TestTetromino;

import no.uib.inf101.tetris.model.tetromino.Tetromino;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;

public class PatternedTetrominoFactory implements TetrominoFactory {

    private final String string;
    private int currentIndex = 0;

    public PatternedTetrominoFactory(String string) {
        if (string == null || string.isEmpty()) {
            throw new IllegalArgumentException("Pattern must not be null or empty");
        }
        this.string = string;
    }

    @Override
    public Tetromino getNext() {
        if (currentIndex >= string.length()) {
            currentIndex = 0; 
        }

        char tetrominoType = string.charAt(currentIndex++);
        return Tetromino.newTetromino(tetrominoType);
    }
    
}

