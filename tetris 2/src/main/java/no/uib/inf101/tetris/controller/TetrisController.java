package no.uib.inf101.tetris.controller;


import no.uib.inf101.tetris.model.GameState;
import no.uib.inf101.tetris.view.TetrisView;


import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import java.awt.event.ActionEvent;




public class TetrisController implements KeyListener {
    
    private final ControllableTetrisModel conModel;
    private final TetrisView view;
    private Timer timer;
    

    public TetrisController (ControllableTetrisModel conModel, TetrisView view){
        this.conModel = conModel;
        this.view = view;
       
        
        view.addKeyListener(this);
        this.view.setFocusable(true);
        this.view.requestFocusInWindow();

        this.timer = new Timer(getTimerDelay(), this::clockTick);
        this.timer.start();
        
    }


    private int getTimerDelay() {
        return conModel.timeBetweenTicks();
    }

    private void refreshTimer() {
        int newDelay = getTimerDelay();
        this.timer.setDelay(newDelay);
        this.timer.setInitialDelay(newDelay);
    }


    private void clockTick(ActionEvent e) {
        if (conModel.getGameState() != GameState.GAME_OVER) {
            conModel.clockTick();
            refreshTimer(); // Kan være unødvendig uten flere nivåer, men god praksis
            view.repaint();
        }
    }

    
    @Override
    public void keyPressed(KeyEvent e) {
        boolean moved = false;
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            // Left arrow was pressed
            conModel.moveTetromino(0, -1);
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            // Right arrow was pressed
            conModel.moveTetromino(0, 1);
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            // Down arrow was pressed
            conModel.moveTetromino(1, 0);
            
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            // Up arrow was pressed
            conModel.rotateTetromino();
        }
        else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            // Spacebar was pressed
            conModel.dropTetromino();
            //this.timer.restart();

            }

        if(moved){
            view.repaint();
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                this.timer.restart(); 
            } 
        }
        
            
        }

        
    

    @Override
    public void keyReleased(KeyEvent e) {
        // Implementeres om nødvendig
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Implementeres om nødvendig
        
    }
}
