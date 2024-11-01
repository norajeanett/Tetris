package no.uib.inf101.tetris.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.tetris.model.TestTetromino.PatternedTetrominoFactory;
//import no.uib.inf101.tetris.model.tetromino.Tetromino;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;
import no.uib.inf101.tetris.view.ViewableTetrisModel;


public class TestTetrisModel {

    private TetrisBoard board;
    private TetrominoFactory factory;
    private ViewableTetrisModel model;


@Test
public void initialPositionOfO() {
  TetrisBoard board = new TetrisBoard(20, 10);
  TetrominoFactory factory = new PatternedTetrominoFactory("O");
  ViewableTetrisModel model = new TetrisModel(board, factory);

  List<GridCell<Character>> tetroCells = new ArrayList<>();
  for (GridCell<Character> gc : model.getFallingPiece()) {
    tetroCells.add(gc);
  }

  assertEquals(4, tetroCells.size());
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 4), 'O')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 5), 'O')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 4), 'O')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(1, 5), 'O')));
}



@Test
public void initialPositionOfI() {
  TetrisBoard board = new TetrisBoard(20, 10);
  TetrominoFactory factory = new PatternedTetrominoFactory("I");
  ViewableTetrisModel model = new TetrisModel(board, factory);

  List<GridCell<Character>> tetroCells = new ArrayList<>();
  for (GridCell<Character> gc : model.getFallingPiece()) {
    tetroCells.add(gc);
  }

  assertEquals(4, tetroCells.size());
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 3), 'I')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 4), 'I')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 5), 'I')));
  assertTrue(tetroCells.contains(new GridCell<>(new CellPosition(0, 6), 'I')));
}


@BeforeEach
    public void setUp() {
        board = new TetrisBoard(20, 10); 
        factory = new PatternedTetrominoFactory("I"); 
        model = new TetrisModel(board, factory); 
    }


@Test
public void moveTetrominoSuccess (){
    boolean result = model.moveTetromino(1, 0); 
    assertTrue(result);
}

@Test 
public void notOutOfBoard(){
    boolean result = model.moveTetromino(100, 0); 
    assertFalse(result); 
}


 
@Test 
public void notOnCurrentPlace(){
    boolean result = model.moveTetromino(2, 0); 
    assertFalse(result);
}

//du mangler å teste: Vellykket flytting endrer hva man får om man iterererer gjennom den fallende brikkent
 


@Test
public void rotateTetrominoWithinBoardBounds(){
    boolean result = model.rotateTetromino(); 
    assertTrue(result);
}

@Test
public void dropTetrominoAttachesItToBoardAndFetchesNewOne() {
    model.dropTetromino();
     // Utfør handlingen
    // Verifiser at tetrominoen er festet til brettet. Dette kan kreve at du sjekker brettets tilstand.
    // Verifiser at en ny tetromino er satt som den fallende brikken.
}

@Test
public void testRemoveFullRows() {
  // Tester at fulle rader fjernes i brettet:
  // -T
  // TT
  // LT
  // L-
  // LL

  TetrisBoard board = new TetrisBoard(5, 2);
  board.set(new CellPosition(0, 1), 'T');
  board.set(new CellPosition(1, 0), 'T');
  board.set(new CellPosition(1, 1), 'T');
  board.set(new CellPosition(2, 1), 'T');
  board.set(new CellPosition(4, 0), 'L');
  board.set(new CellPosition(4, 1), 'L');
  board.set(new CellPosition(3, 0), 'L');
  board.set(new CellPosition(2, 0), 'L');

  assertEquals(3, board.clearFullRows());

  String expected = String.join("\n", new String[] {
    "--",
    "--",
    "--",
    "-T",
    "L-"
  });
  assertEquals(expected, board.prettyString());
}


@Test
public void bottomRowRemains() {
    TetrisBoard board = new TetrisBoard(5, 2);
    board.set(new CellPosition(4, 0), 'L');
    board.set(new CellPosition(4, 1), '-');

    assertEquals(0, board.clearFullRows());

    String expected = String.join("\n", new String[] {
        "--",
        "--",
        "--",
        "--",
        "L-"
    });
    assertEquals(expected, board.prettyString());
}


@Test
public void topRowRemoved() {
    TetrisBoard board = new TetrisBoard(5, 2);
    board.set(new CellPosition(0, 0), 'T');
    board.set(new CellPosition(0, 1), 'T');

    assertEquals(1, board.clearFullRows());

    String expected = String.join("\n", new String[] {
        "--",
        "--",
        "--",
        "--",
        "--"
    });
    assertEquals(expected, board.prettyString());
}

@Test
public void boardWidth() {
    TetrisBoard board = new TetrisBoard(5, 4); // Et bredere brett
    board.set(new CellPosition(4, 0), 'L');
    board.set(new CellPosition(4, 1), 'L');
    board.set(new CellPosition(4, 2), 'L');
    board.set(new CellPosition(4, 3), 'L');

    assertEquals(1, board.clearFullRows());

    String expected = String.join("\n", new String[] {
        "----",
        "----",
        "----",
        "----",
        "----"
    });
    assertEquals(expected, board.prettyString());
}

@Test
    public void testClockTick() {
        // Anta at den fallende brikken starter i en posisjon hvor den kan flytte seg ned minst en gang
        model.clockTick();
    }

    
    
}
