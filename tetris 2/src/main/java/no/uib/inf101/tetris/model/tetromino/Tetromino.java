package no.uib.inf101.tetris.model.tetromino;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.grid.GridCell;


public final class Tetromino implements Iterable<GridCell<Character>> {
    char symbol;
    boolean[][] shape;
    public CellPosition position;

    private Tetromino(char symbol, boolean[][] shape, CellPosition position) {
        this.symbol = symbol;
        this.shape = shape;
        this.position = position;
    }

    // Pakke-privat statisk metode for å opprette tetrominoer basert på symbol
    public static Tetromino newTetromino(char symbol) {
        switch (symbol) {
            case 'I':
                return new Tetromino(symbol, new boolean[][]{
                    { false, false, false, false },
                    {  true,  true,  true, true },
                    { false,  false, false, false },
                    { false,  false, false, false }}, new CellPosition(0, 0));
            case 'O':
                return new Tetromino(symbol, new boolean[][]{
                    { false, false, false, false },
                    { false, true,  true, false },
                    { false,  true, true, false },
                    { false,  false, false, false }}, new CellPosition(0, 0));
            case 'T':
                return new Tetromino(symbol, new boolean[][]{
                    { false, false, false },
                    {  true,  true,  true },
                    { false,  true, false }}, new CellPosition(0, 0));
            case 'S':
                return new Tetromino(symbol, new boolean[][]{
                   { false, false, false },
                    {  false,  true,  true },
                    { true,  true, false }}, new CellPosition(0, 0));
            case 'Z':
                return new Tetromino(symbol, new boolean[][]{
                    { false, false, false },
                    {  true,  true,  false },
                    { false,  true, true }}, new CellPosition(0, 0));
            case 'J':
                return new Tetromino(symbol, new boolean[][]{
                    { false, false, false },
                    {  true,  true,  true },
                    { false,  false, true }}, new CellPosition(0, 0));
            case 'L':
                return new Tetromino(symbol, new boolean[][]{
                   { false, false, false },
                    {  true,  true,  true },
                    { true,  false, false }}, new CellPosition(0, 0));
            default:
                throw new IllegalArgumentException("Ukjent symbol: " + symbol);
        }
    }

    public Tetromino shiftedBy(int deltaRow, int deltaCol) {
        CellPosition newPosition = new CellPosition(this.position.row() + deltaRow, this.position.col() + deltaCol);
        return new Tetromino(this.symbol, this.shape, newPosition);
    }

    

    public Tetromino shiftedToTopCenterOf(GridDimension grid) {
        int center = ((grid.cols()/2) - (this.shape[0].length) / 2); 
        return new Tetromino(this.symbol, this.shape, new CellPosition(-1, center));
    }

    /**
     * 
     * @return an iterator
     */
    @Override
    public Iterator<GridCell<Character>> iterator() {
        ArrayList<GridCell<Character>> myList = new ArrayList<>();
        for (int row = 0; row < this.shape.length; row++) {
            for (int col = 0; col < this.shape[row].length; col++) {
                if (this.shape[row][col]) {
                    myList.add(new GridCell<Character>(new CellPosition(this.position.row() + row, this.position.col() + col), this.symbol));
                }
            }
        }
        return myList.iterator();
    }


    public int hashCode(){
        return Objects.hash(this.symbol, Arrays.deepHashCode(shape), this.position);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tetromino tetromino = (Tetromino) o;
        return symbol == tetromino.symbol &&
               Arrays.deepEquals(shape, tetromino.shape) &&
               Objects.equals(position, tetromino.position);
    }



    /**
     * 
     * @return a rotated copy of the piece. 
     */
    public Tetromino rotate() {
        int originalRows = this.shape.length;
        int originalCols = this.shape[0].length;
    
        boolean[][] newPiece = new boolean[originalCols][originalRows];
    
        // Transpose and flip rows in one pass
        for (int row = 0; row < originalRows; row++) {
            for (int col = 0; col < originalCols; col++) {
                newPiece[col][originalRows - 1 - row] = this.shape[row][col];
            }
        }
    
        return new Tetromino(this.symbol, newPiece, this.position);
    }
   
}

