package no.uib.inf101.tetris.model;


import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.grid.GridDimension;
import no.uib.inf101.tetris.controller.ControllableTetrisModel;
import no.uib.inf101.tetris.model.tetromino.Tetromino;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.ViewableTetrisModel;





public class TetrisModel implements ViewableTetrisModel, ControllableTetrisModel {
    
    TetrisBoard board;
    TetrominoFactory tetrominoFactory;
    Tetromino fallingTetromino;
    private GameState gameState;
    private int score2;

    public TetrisModel(TetrisBoard board, TetrominoFactory tetrominoFactory ){
        this.board = board;
        this.score2 = 0;
        this.tetrominoFactory = tetrominoFactory;
        this.fallingTetromino = tetrominoFactory.getNext().shiftedToTopCenterOf(board);
        this.gameState = GameState.ACTIVE_GAME;
        
    }

    /**
     * @return a GridDimension in this case the board
     */
    @Override
    public GridDimension getDimension(){
        return board;
    }


    /**
     * @return a Iterable<GridCell<Character>> in this case the board
     */
    @Override
    public Iterable<GridCell<Character>> getTilesOnBoard(){
        return board;
    }


    /**
     * @return a Iterable<GridCell<Character>> in this case tetromino
     */
    @Override
    public Iterable<GridCell<Character>> getFallingPiece() {
        return fallingTetromino;
    }

    @Override
    public boolean moveTetromino(int deltaRow, int deltaCol){
         Tetromino movedTetromino = fallingTetromino.shiftedBy(deltaRow, deltaCol);
        if (isPositionLegal(movedTetromino)) {
            fallingTetromino = movedTetromino;
            return true;
        }
        return false;
    }

    private boolean isPositionLegal(Tetromino tetromino) {
        for (GridCell<Character> Cell : tetromino) { //fallingTetromino
            CellPosition position = Cell.pos(); //må finnne ut hvordan jeg får hentet posisjonen riktig 
            if (!board.positionIsOnGrid(position) || !board.get(position).equals('-')) {
                return false;
            }
        }
        return true;

        
    }

    @Override
    public boolean rotateTetromino(){
        Tetromino rotated = fallingTetromino.rotate();
        if (isPositionLegal(rotated)) {
            fallingTetromino = rotated;
            return true;
        }
        return false;
    }


    public void placeTetrominoAndClearRows() {
        attachTetrominoToBoard();
        int rowsCleared = board.clearFullRows();
        updateScore(rowsCleared);

        if (gameState != GameState.GAME_OVER) {
            fetchNewFallingTetromino();
        }
    }

    private void fetchNewFallingTetromino() {
        // Hent ut en ny brikke fra tetromino-fabrikken
        //Tetromino newTetromino = tetrominoFactory.getNext();
        fallingTetromino = tetrominoFactory.getNext().shiftedToTopCenterOf(board);
            if (!isPositionLegal(fallingTetromino)) {
                gameState = GameState.GAME_OVER; // Setter spillets tilstand til GAME_OVER hvis den nye brikken ikke kan plasseres lovlig
            }
          
        
        }
       
    
    private void attachTetrominoToBoard() {
        for (GridCell<Character> cell : fallingTetromino) {
            // Hent cellens posisjon og verdi (karakteren til tetrominoen)
            CellPosition position = cell.pos();
            Character value = cell.value();
    
            // Oppdater brettets tilstand med tetrominoens karakter på cellens posisjon
            board.set(position, value);
        }

        int rowsCleared = board.clearFullRows();
        updateScore(rowsCleared);
        // Hent en ny tetromino som skal falle
        fetchNewFallingTetromino();
    }


    

    @Override
    public void dropTetromino(){
        if (gameState == GameState.GAME_OVER) {
            // Spillet er over, ikke tillat dropping av nye brikker.
            return;
        }
        boolean moved = true;
        while (moved) {
            Tetromino movedTetromino = fallingTetromino.shiftedBy(1, 0); 
            if (isPositionLegal(movedTetromino)) {
                fallingTetromino = movedTetromino;
            } else {
                attachTetrominoToBoard();
                moved = false;
                
            }
            
        }
        
        
    }


    private void updateScore(int rowsCleared) {
        // Du kan justere poengsystemet etter ønske, her er et enkelt eksempel
        switch (rowsCleared) {
            case 1: 
                score2 += 100; 
                break;
            case 2: 
                score2 += 300; 
                break;
            case 3: 
                score2 += 500; 
                break;
            case 4: 
                score2 += 800; 
                break;
            // Hvis du vil håndtere en situasjon for 0 rader fjernet eller andre spesifikke tilfeller, kan du legge til flere case-blokker
            default: 
                // Ingen ekstra poeng for 0 rader fjernet eller mer enn 4
                break;
        } // Ingen ekstra poeng for 0 rader fjernet eller mer enn 4
    }



    @Override
    public GameState getGameState() {
        return gameState; // Returner spillets tilstand
    }

    public boolean isGameOver() {
        // Anta at vi henter en ny tetromino og prøver å plassere den øverst i midten av brettet
        Tetromino newTetromino = tetrominoFactory.getNext().shiftedToTopCenterOf(board);

        // Returnerer true (spillet er over) hvis den nye tetrominoen ikke kan plasseres lovlig
        return !isPositionLegal(newTetromino);
    }


    @Override
    public int timeBetweenTicks() {
        int speed = 1000;
        if(board.getLevel()== 1){
            speed = 1000;
        }
        if(board.getLevel()== 2){
            speed = 800;
        }
        if(board.getLevel()== 4){
            speed = 600;
        }
        if(board.getLevel()>= 5){
            speed = 400;
        }
        return speed;
        
    }
    @Override
    public void clockTick() {
        if (moveTetromino(1,0) == false) {
            attachTetrominoToBoard();
        }
                 
    }

    @Override
    public int getScore() {
        return score2;
    }  




 
    
    
}
