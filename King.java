import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class King extends Piece{
    
    King(String side, Square square){
        this.setPlace(square);
        this.color = side;
    }

    @Override
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        ImageIcon i;
        if(color == "white"){
            i = Square.getScaledImage(new ImageIcon("PieceImages\\white-king.png"), place.getLen(),place.getLen());
        } else {
            i = Square.getScaledImage(new ImageIcon("PieceImages\\black-king.png"), place.getLen(),place.getLen());
        }
        g2d.drawImage(i.getImage(), x*place.getLen(),y*place.getLen(),null);
    }

    @Override
    public ArrayList<Square> possibleMove(Square[][] squareTable, boolean visible, Game game ) {
        ArrayList<Square> tab = new ArrayList<Square>();
        Square sqr;

            ArrayList<Square> moves = new ArrayList<Square>();
            if(y != 7){
                moves.add(squareTable[x][y+1]);
                if(x != 7) moves.add(squareTable[x+1][y+1]);
                if(x != 0) moves.add(squareTable[x-1][y+1]);
            }
            if(y != 0){
                moves.add(squareTable[x][y-1]);
                if(x != 7) moves.add(squareTable[x+1][y-1]);
                if(x != 0) moves.add(squareTable[x-1][y-1]);
            }
            if(x != 7) moves.add(squareTable[x+1][y]);
            if(x != 0) moves.add(squareTable[x-1][y]);

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

