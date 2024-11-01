package no.uib.inf101.tetris.model;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.Grid;

public class TetrisBoard extends Grid<Character> {

    private static final Character DEFAULT_TILE = '-';
    private int score;
    private int state;

    public TetrisBoard(int rows, int cols) {
        super(rows, cols, DEFAULT_TILE);
        this.score = 0;
        this.state = 1;
    }

    private boolean isRowFull(int row) {
        for (int col = 0; col < this.cols(); col++) {
            if (this.get(new CellPosition(row, col)).equals(DEFAULT_TILE)) {
                return false;
            }
        }
        return true;
    }


    public String prettyString(){
        String prettyString = "";
        for (int row = 0; row < this.rows(); row++){
            for (int col = 0; col < this.cols(); col++){
                CellPosition pos = new CellPosition(row, col);
                char value = this.get(pos);
                prettyString += value;
            }
            prettyString += "\n";
        }
        return prettyString.substring(0, prettyString.length()-1);
    }

    private void fillRowWithTile(int row, Character tile) {
        for (int col = 0; col < this.cols(); col++) {
            this.set(new CellPosition(row, col), tile);
        }
    }

    private void copyRowToRow(int fromRow, int toRow) {
        for (int col = 0; col < this.cols(); col++) {
            this.set(new CellPosition(toRow, col), this.get(new CellPosition(fromRow, col)));
        }
    }

    public int clearFullRows() {
        int rowsCleared = 0;
    
        // Start fra bunnen av brettet og jobb deg oppover
        int targetRow = this.rows() - 1; // "person a" starter ved nederste rad
    
        for (int checkRow = this.rows() - 1; checkRow >= 0; checkRow--) { // "person b" sjekker hver rad
            if (!isRowFull(checkRow)) { // Hvis raden som sjekkes ikke er full, kopieres den ned til 'targetRow'
                if (targetRow != checkRow) { // Sjekker for å unngå å kopiere en rad til seg selv
                    copyRowToRow(checkRow, targetRow);
                }
                targetRow--; // "person a" flytter seg opp for å være klar til å fylle neste rad
            } else {
                rowsCleared++; // Teller opp rader som er fulle og dermed "forkastes"
            }
        }
    
        // Etter å ha flyttet alle ikke-fulle rader ned, fyll resterende rader på toppen med DEFAULT_TILE
        while (targetRow >= 0) {
            fillRowWithTile(targetRow, DEFAULT_TILE);
            targetRow--;
        }
    
        return rowsCleared; // Returnerer antall rader som ble fjernet
    }

   


    public int getScore() {
        return this.score;
    }

    public int getLevel(){
        return this.state;
    }

    // Assuming CellPosition class exists and works as intended.
    // Assuming the Grid<Character> class properly implements get() and set() methods.

    
}

