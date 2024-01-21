import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Knight extends Piece{

    Knight(String side, Square square){
        this.setPlace(square);
        this.color = side;
    }

    @Override
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        ImageIcon i;
        if(color == "white"){
            i = Square.getScaledImage(new ImageIcon("PieceImages\\white-knight.png"), place.getLen(),place.getLen());
        } else {
            i = Square.getScaledImage(new ImageIcon("PieceImages\\black-knight.png"), place.getLen(),place.getLen());
        }
        g2d.drawImage(i.getImage(), x*place.getLen(),y*place.getLen(),null);
    }

    @Override
    public ArrayList<Square> possibleMove(Square[][] squareTable, boolean visible, Game game ) {
        ArrayList<Square> tab = new ArrayList<Square>();
        Square sqr;
        ArrayList<Square> moves = new ArrayList<Square>();
        if(x != 7){
            if(y < 6) moves.add(squareTable[x+1][y+2]);
            if(y > 1) moves.add(squareTable[x+1][y-2]);
        }
        if(x < 6){
            if(y != 7) moves.add(squareTable[x+2][y+1]);
            if(y != 0) moves.add(squareTable[x+2][y-1]);
        }
        if(x != 0){
            if(y < 6) moves.add(squareTable[x-1][y+2]);
            if(y > 1) moves.add(squareTable[x-1][y-2]);
        }
        if(x > 1){
            if(y != 7) moves.add(squareTable[x-2][y+1]);
            if(y != 0) moves.add(squareTable[x-2][y-1]);
        }

            for(int i = 0; i < moves.size(); i++){
                sqr = moves.get(i);
                if( sqr != null){
                    if(sqr.placedPiece == null){
                        sqr.option = visible;
                        tab.add(sqr);
                    } else if( sqr.placedPiece.color != color){
                        sqr.target = visible;
                        tab.add(sqr);
                    }
                }
            }

        return tab;
    }
}
