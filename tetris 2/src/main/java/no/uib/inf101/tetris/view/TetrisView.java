package no.uib.inf101.tetris.view;

import javax.swing.JPanel;

import no.uib.inf101.grid.GridCell;
//import no.uib.inf101.tetris.model.GameState;
import no.uib.inf101.tetris.model.GameState;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Color; 
import java.awt.Font;
import java.awt.FontMetrics;



public class TetrisView extends JPanel {

    ViewableTetrisModel model;
    ColorTheme colorTheme = new DefaultColorTheme();
    

    public TetrisView(ViewableTetrisModel model) {
        this.model = model;
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(300, 400));
      }
      
      // The paintComponent method is called by the Java Swing framework every time
      // either the window opens or resizes, or we call .repaint() on this object. 
      // Note: NEVER call paintComponent directly yourself

      @Override
      public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawGame(g2);
      }


      private void drawGame(Graphics2D g2) {
        // Draw frame
        int margin = 2;

        Rectangle2D rectangle = new Rectangle2D.Double(20, 20, this.getWidth() - 40, this.getHeight() - 40);
        g2.setColor(colorTheme.getFrameColor()); // Assuming getFrameColor() exists in ColorTheme
        g2.fill(rectangle);

        // Create a CellPositionToPixelConverter object
        CellPositionToPixelConverter converter = new CellPositionToPixelConverter(rectangle, model.getDimension(), margin); // Assuming constructor takes Rectangle and margin

        // Draw cells
        
        drawCells(g2, model.getTilesOnBoard(), converter, colorTheme);
        drawCells(g2, model.getFallingPiece(), converter, colorTheme); // Assuming model.getCells() returns Iterable<GridCell<Character>>
        
        if (model.getGameState() == GameState.GAME_OVER) {
        drawGameOver(g2);
    }
    
      }



      private void drawCells(Graphics2D g2, Iterable<GridCell<Character>> cells, CellPositionToPixelConverter converter, ColorTheme colorTheme) {
          for (GridCell<Character> cell : cells) {
              
              Rectangle2D cellRect = converter.getBoundsForCell(cell.pos());
              
              //Color cellColor = colorTheme.getCellColor(cell.value());
              
              g2.setColor(colorTheme.getCellColor(cell.value())); // Assuming getCellColor(Character) exists in ColorTheme
              g2.fill(cellRect);
        }
    }




  



    private void drawGameOver(Graphics2D g2) {
      // Tegner en gjennomsiktig overlay over hele spillet
      Color overlayColor = new Color(0, 0, 0, 128); // Gjennomsiktig svart
      g2.setColor(overlayColor);
      g2.fill(new Rectangle2D.Double(0, 0, this.getWidth(), this.getHeight()));
  
      // Tegner "Game Over"-teksten
      String gameOverText = "Game Over";
      Font font = new Font("Arial", Font.BOLD, 30);
      g2.setFont(font);
      g2.setColor(Color.WHITE); // Tekstfarge
      
      FontMetrics metrics = g2.getFontMetrics(font);
      int x = (this.getWidth() - metrics.stringWidth(gameOverText)) / 2;
      int y = ((this.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
      
      g2.drawString(gameOverText, x, y);

      String scoreText = "Score: " + model.getScore();
      Font scoreFont = new Font("Arial", Font.PLAIN, 20);
      g2.setFont(scoreFont);
    
      FontMetrics scoreMetrics = g2.getFontMetrics(scoreFont);
      int scoreX = (this.getWidth() - scoreMetrics.stringWidth(scoreText)) / 2;
      // Plasserer poengsummen litt under "Game Over"-teksten
      int scoreY = y + metrics.getHeight() + 20; // Du kan justere avstanden etter ønske
    
      g2.drawString(scoreText, scoreX, scoreY);
}}




       
      
  
  

