import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Pawn extends Piece {

    Pawn(String side, Square square){
        this.setPlace(square);
        color = side;

    }
    @Override
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        ImageIcon i;
        if(color == "white"){

            i = Square.getScaledImage(new ImageIcon("PieceImages\\white-pawn.png"), place.getLen(),place.getLen());
        } else {
            i = Square.getScaledImage(new ImageIcon("PieceImages\\black-pawn.png"), place.getLen(),place.getLen());
        }

        g2d.drawImage(i.getImage(), x*place.getLen(),y*place.getLen(),null);
    }

    @Override
    public ArrayList<Square> possibleMove(Square[][] squareTable, boolean visible, Game game ) {
        ArrayList<Square> tab = new ArrayList<Square>();
        Square sqr;
        if(color == "white"){
            //pole z przodu puste
            if(y != 0) {
                sqr = squareTable[x][y-1];
                if(sqr.placedPiece == null){
                    sqr.option = visible;
                    tab.add(sqr);
                    //dwa pola do przodu
                    if(y == 6){
                        sqr = squareTable[x][y-2];
                        if(sqr.placedPiece == null){
                            sqr.option = visible;
                            tab.add(sqr);
                        }
                    }
                    
                }
            //pola na ukos
                if(x != 0 ){
                    sqr = squareTable[x-1][y-1];
                    if(sqr.placedPiece != null && sqr.placedPiece.color == "black"){
                        sqr.target = visible;
                        tab.add(sqr);
                    }
                }
                if(x!= 7){
                    sqr = squareTable[x+1][y-1];
                    if(sqr.placedPiece != null && sqr.placedPiece.color == "black"){
                        sqr.target = visible;
                        tab.add(sqr);
                    }
                }
            }

            //enpassant
            if(game.lastTurn[0] != null && game.turn == color ){
                if(game.lastTurn[1].placedPiece instanceof Pawn && (Math.abs(game.lastTurn[0].getBoardY() - game.lastTurn[1].getBoardY()) == 2*game.len)){
                    if( y == game.lastTurn[1].getBoardY()/game.len && (Math.abs(x - game.lastTurn[1].getBoardX()/game.len) == 1)) {
                        squareTable[game.lastTurn[1].getBoardX()/game.len][2].option = visible;
                        squareTable[game.lastTurn[1].getBoardX()/game.len][2].enPassant = !squareTable[game.lastTurn[1].getBoardX()/game.len][2].enPassant;
                        tab.add(squareTable[game.lastTurn[1].getBoardX()/game.len][2]);
                    }
                }
            }

        } else {
            if(y != 7){
                sqr = squareTable[x][y+1];
                if(sqr.placedPiece == null){
                    sqr.option = visible;
                    tab.add(sqr);
                    //dwa pola do przodu
                    if(y == 1){
                        sqr = squareTable[x][y+2];
                        if(sqr.placedPiece == null){
                            sqr.option = visible;
                            tab.add(sqr);
                        }
                    }
                    
                }
                //pola na ukos
                if(x != 0){
                    sqr = squareTable[x-1][y+1];
                    if(sqr.placedPiece != null && sqr.placedPiece.color == "white"){
                        sqr.target = visible;
                        tab.add(sqr);
                    }
                }
                if(x != 7){
                    sqr = squareTable[x+1][y+1];
                    if(sqr.placedPiece != null && sqr.placedPiece.color == "white"){
                        sqr.target = visible;
                        tab.add(sqr);
                    }
                }
            }

            //enpassant
            if(game.lastTurn[0] != null && game.turn == color){
                if(game.lastTurn[1].placedPiece instanceof Pawn && (Math.abs(game.lastTurn[0].getBoardY() - game.lastTurn[1].getBoardY()) == 2*game.len)){
                    if( y == game.lastTurn[1].getBoardY()/game.len && (Math.abs(x - game.lastTurn[1].getBoardX()/game.len) == 1)) {
                        squareTable[game.lastTurn[1].getBoardX()/game.len][5].option = visible;
                        squareTable[game.lastTurn[1].getBoardX()/game.len][5].enPassant = !squareTable[game.lastTurn[1].getBoardX()/game.len][5].enPassant;
                        tab.add(squareTable[game.lastTurn[1].getBoardX()/game.len][5]);                        
                    }
                }
            }
        }
        return tab;
    }
}

