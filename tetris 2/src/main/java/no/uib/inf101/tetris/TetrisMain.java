package no.uib.inf101.tetris;

import javax.swing.JFrame;

//import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.tetris.controller.TetrisController;
import no.uib.inf101.tetris.model.TetrisBoard;
import no.uib.inf101.tetris.model.TetrisModel;
import no.uib.inf101.tetris.model.tetromino.RandomTetrominoFactory;
import no.uib.inf101.tetris.model.tetromino.TetrominoFactory;
//import no.uib.inf101.tetris.view.SampleView;
//import no.uib.inf101.tetris.view.ViewableTetrisModel;
import no.uib.inf101.tetris.view.TetrisView;


public class TetrisMain {
  public static final String WINDOW_TITLE = "INF101 Tetris";
  
  public static void main(String[] args) {

    //SampleView view = new SampleView();
    TetrisBoard board = new TetrisBoard(15,10);
    
    //board.set(new CellPosition(0, 0), 'I');
    //board.set(new CellPosition(0, 9), 'O');
    //board.set(new CellPosition(14, 0), 'S');
    //board.set(new CellPosition(14, 9), 'T');

    TetrominoFactory factory = new RandomTetrominoFactory();
    //ViewableTetrisModel model = new TetrisModel(board, factory);
    TetrisModel model = new TetrisModel(board, factory);
    TetrisView view = new TetrisView(model);
    new TetrisController(model, view);
    

    // The JFrame is the "root" application window.
    // We here set som properties of the main window, 
    // and tell it to display our tetrisView
    JFrame frame = new JFrame(WINDOW_TITLE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // Here we set which component to view in our window
    frame.setContentPane(view);
    
    // Call these methods to actually display the window
    frame.pack();
    frame.setVisible(true);
  }
  
}
