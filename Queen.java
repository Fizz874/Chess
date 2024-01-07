import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Queen extends Piece{

    Queen(String side, Square square){
        this.setPlace(square);
        this.color = side;
    }

    @Override
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        ImageIcon i;
        if(color == "white"){
            i = Square.getScaledImage(new ImageIcon("PieceImages\\white-queen.png"), place.getLen(),place.getLen());
        } else {
            i = Square.getScaledImage(new ImageIcon("PieceImages\\black-queen.png"), place.getLen(),place.getLen());
        }
        g2d.drawImage(i.getImage(), x*place.getLen(),y*place.getLen(),null);
    }

    @Override
    public ArrayList<Square> possibleMove(Square[][] squareTable, boolean visible, Game game ) {
        ArrayList<Square> tab = new ArrayList<Square>();
        Square sqr;
        boolean[] directions = new boolean[8];
        for(int i =0; i < 8; i++) directions[i] = true;
        Square[] sqrTab = new Square[8];

        for(int i = 1; i < 8; i++){

            sqrTab[0] = (x+i > 7) ? null : squareTable[x+i][y];            
            sqrTab[1] = (x-i < 0) ? null : squareTable[x-i][y];
            sqrTab[2] = (y+i > 7) ? null : squareTable[x][y+i];
            sqrTab[3] = (y-i < 0) ? null : squareTable[x][y-i];
            sqrTab[4] = (y-i < 0 || x-i < 0) ? null : squareTable[x-i][y-i];
            sqrTab[5] = (y-i < 0 || x+i > 7) ? null : squareTable[x+i][y-i];
            sqrTab[6] = (y+i > 7 || x+i > 7) ? null : squareTable[x+i][y+i];
            sqrTab[7] = (y+i > 7 || x-i < 0) ? null : squareTable[x-i][y+i];

            for(int j =0; j <8; j++){
                if (directions[j] == true){
                    if(sqrTab[j] == null) {
                        directions[j] = false;
                    } else {
                        sqr = sqrTab[j];
                        if( sqr != null){
                            if(sqr.placedPiece == null){
                                sqr.option = visible;
                                tab.add(sqr);
                            } else {
                                if( sqr.placedPiece.color != color) {
                                    sqr.target = visible;
                                    tab.add(sqr);
                                } 
                                directions[j] = false;
                            }
                        }
                    }
                }

            }

        
        }

        return tab;
    }
}
