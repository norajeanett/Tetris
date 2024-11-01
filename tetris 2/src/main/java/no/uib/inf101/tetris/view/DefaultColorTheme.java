package no.uib.inf101.tetris.view;

import java.awt.Color;

public class DefaultColorTheme implements ColorTheme {
    
    @Override
    public Color getCellColor(char c){
        switch(c) {
            case 'I':
                return Color.RED;
            case '-':
                return Color.BLACK;
            case 'O':
                return Color.ORANGE;
            case 'T':
                return Color.YELLOW;
            case 'J':
                return Color.BLUE;
            case 'L':
                return Color.CYAN;
            case 'Z':
                return Color.MAGENTA;
            case 'S':
                return Color.GREEN;
            default:
                throw new IllegalArgumentException("No available color for "+ c);
            
        }
    }

    

    @Override
    public Color getFrameColor(){

        return new Color(0,0,0,0);
    }

    @Override
    public Color getBackgroundColor(){
        return null;
    }

    @Override
    public Color getGameOverColor(){
        return new Color(0, 0, 0, 128);
    }


    
}
