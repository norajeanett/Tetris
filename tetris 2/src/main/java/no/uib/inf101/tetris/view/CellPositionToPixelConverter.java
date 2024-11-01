package no.uib.inf101.tetris.view;

import java.awt.geom.Rectangle2D;

import no.uib.inf101.grid.CellPosition;
import no.uib.inf101.grid.GridDimension;


public class CellPositionToPixelConverter {

    Rectangle2D box;
    GridDimension gd;
    double margin;

    public CellPositionToPixelConverter (Rectangle2D box, GridDimension gd, double margin){
        this.box = box;
        this.gd = gd;
        this.margin = margin;
    }
    
    public Rectangle2D getBoundsForCell(CellPosition cp){

        double cellWidth = (box.getWidth() - ((gd.cols() + 1) * margin)) / gd.cols();
        double xcell = box.getX() + (cp.col() + 1) * margin + cellWidth * cp.col();
        double cellHeight = (box.getHeight() - (gd.rows() + 1) * margin) / gd.rows();
        double ycell = box.getY() + (cp.row() +1)*margin + cellHeight * cp.row();

        return new Rectangle2D.Double(xcell, ycell, cellWidth, cellHeight) ;

    }
}





