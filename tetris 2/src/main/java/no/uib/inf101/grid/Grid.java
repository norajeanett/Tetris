package no.uib.inf101.grid;

import java.lang.reflect.InaccessibleObjectException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Grid<E> implements IGrid<E> {
    private int row;
    private int col;

    List<List<E>> grid;

    public Grid(int row, int col, E defaultValue) {
        this.row = row;
        this.col = col;
        grid = new ArrayList<>(row);

        
        for (int r = 0; r < this.row; r++) {
            List<E> rowlist = new ArrayList<>();
            for (int i = 0; i < this.col; i++) {
                rowlist.add(defaultValue);
            }
            grid.add(rowlist);
        }
    }

    public Grid(int row, int col) {
        this(row, col, null);
    }

    @Override
    public void set(CellPosition pos, E value) {
        if (!positionIsOnGrid(pos)) {
            throw new InaccessibleObjectException("Position is not on the grid");
        }
        grid.get(pos.row()).set(pos.col(), value);
    }

    @Override
    public E get(CellPosition pos) {
        if (!positionIsOnGrid(pos)) {
            throw new IndexOutOfBoundsException("Position is not on grid");
        }
        return grid.get(pos.row()).get(pos.col());
    }

    @Override
    public boolean positionIsOnGrid(CellPosition pos) {
        boolean isInRow = pos.row() >= 0 && pos.row() < this.row;
        boolean isOnCol = pos.col() >= 0 && pos.col() < this.col;
        return isInRow && isOnCol;
    }

    //denne er jeg ekstremt usikker på, trenger hjelp til det som er under der med gridcell
    @Override
    public Iterator<GridCell<E>> iterator() {
        List<GridCell<E>> cells = new ArrayList<>();
        for (int row = 0; row < this.row; row++) {
            for (int col = 0; col < this.col; col++) {
                CellPosition pos = new CellPosition(row, col);
                GridCell<E> gridItem = new GridCell<> (pos, get(pos)); 
                cells.add(gridItem);
            }
        }
        return cells.iterator();
    }

    @Override
    public int rows() {
        return this.row;
    }

    @Override
    public int cols() {
        return this.col;
    }
}