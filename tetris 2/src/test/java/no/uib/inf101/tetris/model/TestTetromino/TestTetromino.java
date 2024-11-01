package no.uib.inf101.tetris.model.TestTetromino;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridCell;
import no.uib.inf101.tetris.model.TetrisBoard;
//import no.uib.inf101.tetris.model.TetrisBoard;
//import no.uib.inf101.tetris.model.TetrisBoard;
import no.uib.inf101.tetris.model.tetromino.Tetromino;


public class TestTetromino {

  @Test
  public void testHashCodeAndEquals() {
    Tetromino t1 = Tetromino.newTetromino('T');
    Tetromino t2 = Tetromino.newTetromino('T');
    Tetromino t3 = Tetromino.newTetromino('T').shiftedBy(1, 0);
    Tetromino s1 = Tetromino.newTetromino('S');
    Tetromino s2 = Tetromino.newTetromino('S').shiftedBy(0, 0);

    assertEquals(t1, t2);
    assertEquals(s1, s2);
    assertEquals(t1.hashCode(), t2.hashCode());
    assertEquals(s1.hashCode(), s2.hashCode());
    assertNotEquals(t1, t3);
    assertNotEquals(t1, s1);
  }

  @Test
  public void tetrominoIterationOfT() {
    // Create a standard 'T' tetromino placed at (10, 100) to test
    Tetromino tetro = Tetromino.newTetromino('T');
    tetro = tetro.shiftedBy(10, 100);

    // Collect which objects are iterated through
    List<GridCell<Character>> objs = new ArrayList<>();
    for (GridCell<Character> gc : tetro) {
      objs.add(gc);
    }

    // Check that we got the expected GridCell objects
    assertEquals(4, objs.size());
    assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 100), 'T')));
    assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 101), 'T')));
    assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 102), 'T')));
    assertTrue(objs.contains(new GridCell<>(new CellPosition(12, 101), 'T')));
  }

  @Test
  public void tetrominoIterationOfS() {
    // Create a standard 'S' tetromino placed at (10, 100) to test
    Tetromino tetro = Tetromino.newTetromino('S');
    tetro = tetro.shiftedBy(10, 100);

    // Collect which objects are iterated through
    List<GridCell<Character>> objs = new ArrayList<>();
    for (GridCell<Character> gc : tetro) {
      objs.add(gc);
    }
    assertEquals(4, objs.size());
    assertTrue(objs.contains(new GridCell<>(new CellPosition(12, 100), 'S')));
    assertTrue(objs.contains(new GridCell<>(new CellPosition(12, 101), 'S')));
    assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 101), 'S')));
    assertTrue(objs.contains(new GridCell<>(new CellPosition(11, 102), 'S')));
  }

  @Test
  public void doubleShift(){
    Tetromino check = Tetromino.newTetromino('S');
    check = check.shiftedBy(10, 100);
    check = check.shiftedBy(10, 100);

    List<GridCell<Character>> objs = new ArrayList<>();
    for (GridCell<Character> gc : check) {
        objs.add(gc);
    }

    assertEquals(4, objs.size());
    assertTrue(objs.contains(new GridCell<>(new CellPosition(22, 200), 'S')));
    assertTrue(objs.contains(new GridCell<>(new CellPosition(22, 201), 'S')));
    assertTrue(objs.contains(new GridCell<>(new CellPosition(21, 201), 'S')));
    assertTrue(objs.contains(new GridCell<>(new CellPosition(21, 202), 'S')));
  }

  @Test
  public void checkShiftedToTopCenterOfO() {
    Tetromino check = Tetromino.newTetromino('O');

    check = check.shiftedToTopCenterOf(new TetrisBoard(5, 9));

    ArrayList<GridCell<Character>> objs = new ArrayList<>();
    for (GridCell<Character> gc : check) {
        objs.add(gc);
    }

    assertEquals(4, objs.size());
    System.out.println("Row: " + check.position.row());
    System.out.println("Column: " + check.position.col());

    assertTrue(objs.contains(new GridCell<>(new CellPosition(0, 3), 'O')),
               "Expected cell at position (0, 3) is missing");
    assertTrue(objs.contains(new GridCell<>(new CellPosition(0, 4), 'O')),
               "Expected cell at position (0, 4) is missing");
    assertTrue(objs.contains(new GridCell<>(new CellPosition(1, 3), 'O')),
               "Expected cell at position (1, 3) is missing");
    assertTrue(objs.contains(new GridCell<>(new CellPosition(1, 4), 'O')),
               "Expected cell at position (1, 4) is missing");

    }
}
