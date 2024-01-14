import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class Game extends JPanel implements MouseListener{
    Board b;
    Piece[] blackPieces = new Piece[16];
    Piece[] whitePieces = new Piece[16];

    public ArrayList<Piece> takenPieces = new ArrayList<Piece>();
    Square[] lastTurn = new Square[2];
    Square checkMark;

    int pressX;
    int pressY;

    int len;

    boolean check = false;
    boolean moving = false;
    String turn = "white";
    Square lastSqr = null;

    Game(){
        len = 80;
        b = new Board(this);
        setPieces();
        this.setPreferredSize(new Dimension(8*len,8*len));
        addMouseListener(this);
    }

    public void paint(Graphics g){
        b.paint(g);

    }

    private void placePieces(){
        for (int i =0; i < 16; i++){
            if(whitePieces[i] != null){
                whitePieces[i].place.placedPiece = whitePieces[i];
            }
            if(blackPieces[i] != null){
                blackPieces[i].place.placedPiece = blackPieces[i];                                
            }
        }
    }

    private void setPieces(){

        whitePieces[0] = new King("white", b.squareTable[4][7] );
        whitePieces[1] = new Queen("white", b.squareTable[3][7] );

        whitePieces[6] = new Rook("white",b.squareTable[0][7]);
        whitePieces[7] = new Rook("white",b.squareTable[7][7]);        

        blackPieces[0] = new King("black", b.squareTable[4][0]);
        blackPieces[1] = new Queen("black", b.squareTable[3][0]);

        blackPieces[6] = new Rook("black",b.squareTable[0][0]);
        blackPieces[7] = new Rook("black",b.squareTable[7][0]);    

        for(int i = 8; i < 16; i++){
                    whitePieces[i] = new Pawn("white",b.squareTable[i-8][6]);
                    blackPieces[i] = new Pawn("black",b.squareTable[i-8][1]);
        }
        placePieces();

    }

    public void mousePressed(MouseEvent e) {
        Point p = e.getPoint();
        pressX = (int)Math.floor(p.getX())/len;
        pressY = (int)Math.floor(p.getY())/len;
    }
    public void mouseReleased(MouseEvent e) {
            Point p = e.getPoint();
            int releaseX =  (int)Math.floor(p.getX())/len;
            int releaseY =  (int)Math.floor(p.getY())/len;
            if(releaseX == pressX && releaseY == pressY){
                if(moving){
                move(b.squareTable[releaseX][releaseY],lastSqr);
            } else {
                
                lastSqr = b.squareTable[releaseX][releaseY];
                chose(lastSqr);
            }
            e.getComponent().repaint();
            }

    }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    public void mouseClicked(MouseEvent e) {}
    
    private void move(Square newSqr, Square oldSqr){
        //druga faza ruchu, będzie obsługiwać przesunięcie

        if (newSqr.option || newSqr.target){

            if(newSqr.target && !newSqr.castling){
                take(newSqr);
            }

            if(newSqr.castling) {
                oldSqr.placedPiece.possibleMove(b.squareTable,false, this);
                int y = (turn == "white")? 7:0;

                if(newSqr.getBoardX()/len > 4){
                    newSqr = b.squareTable[6][y];
                    b.squareTable[5][y].placedPiece = b.squareTable[7][y].placedPiece;
                    b.squareTable[7][y].placedPiece = null;
                    b.squareTable[5][y].placedPiece.setPlace(b.squareTable[5][y]);
                    b.squareTable[5][y].placedPiece.setMoved();

                } else {

                    newSqr = b.squareTable[2][y];
                    b.squareTable[3][y].placedPiece = b.squareTable[0][y].placedPiece;
                    b.squareTable[0][y].placedPiece = null;
                    b.squareTable[3][y].placedPiece.setPlace(b.squareTable[3][y]);
                    b.squareTable[3][y].placedPiece.setMoved();
                }
                
        
            //enpassant
            } else if (newSqr.enPassant) { 
                oldSqr.placedPiece.possibleMove(b.squareTable,false, this);
                if(turn == "white"){
                    take(b.squareTable[newSqr.getBoardX()/len][(newSqr.getBoardY()/len) +1]);
                } else {
                    take(b.squareTable[newSqr.getBoardX()/len][(newSqr.getBoardY()/len )-1]);
                }

            } else {
                //kasowanie znaku ruchu ze wszystkich pól
                oldSqr.placedPiece.possibleMove(b.squareTable,false, this);
            }

            //kasowanie zaznaczenia pola
            if(checkMark != null) checkMark.checked = false;
            oldSqr.highlighted = false;
            //zaznaczenie innym kolorem jako miejsce ostatniego ruchu
            if(lastTurn[0] != null){
                lastTurn[0].lastMove = false;
                lastTurn[1].lastMove = false;
            }
            oldSqr.lastMove = true;
            newSqr.lastMove = true;
            lastTurn[0] = oldSqr;
            lastTurn[1] = newSqr;

            //usunięcie figury ze starego pola i starego pola z figury
            //dodanie do nowego
            newSqr.placedPiece = oldSqr.placedPiece;
            oldSqr.placedPiece = null;
            newSqr.placedPiece.setPlace(newSqr);
            if(!newSqr.placedPiece.isMoved()) newSqr.placedPiece.setMoved();
        
            moving = false;


            //zmiana tury
            if(turn == "white") {
                turn = "black";
                //sprawdzanie czy jest szach
                if(isCheck(blackPieces[0].place)){                    
                    //zaznaczamy, że szach
                    blackPieces[0].place.checked = true;
                    checkMark = blackPieces[0].place;
                    //sprawdzamy czy nie mat
                    if(isMate()) gameOver(false);
                } else if(isMate()){
                    gameOver(true);
                }


            } else {
                turn = "white";
                if(isCheck(whitePieces[0].place)){
                    whitePieces[0].place.checked = true;
                    checkMark = whitePieces[0].place;
                    if(isMate())gameOver(false);
                } else if(isMate()){
                    gameOver(true);
                }
            }

        } else if (newSqr == oldSqr){
            oldSqr.placedPiece.possibleMove(b.squareTable,false, this);
            oldSqr.highlighted = false;
            moving = false;

        } else if(newSqr.placedPiece != null && newSqr.placedPiece.color == oldSqr.placedPiece.color){
            oldSqr.placedPiece.possibleMove(b.squareTable,false, this);
            oldSqr.highlighted = false;
            moving = false;
            lastSqr = newSqr;
            chose(newSqr);
        }


    }

    private void chose(Square chosenSqr){
        //pierwsza faza ruchu - obsługuje zaznaczenie figury, wyświetlenie możliwych pól

        if(chosenSqr.placedPiece != null){
            if(chosenSqr.placedPiece.color == turn){
                chosenSqr.highlighted = true;
                ArrayList<Square> tab = findLegal(chosenSqr.placedPiece);
                Square sqr;
                for(int i =0; i< tab.size();i++){
                    sqr = tab.get(i);
                    if(sqr != null){
                        if(sqr.placedPiece != null){
                            sqr.target = true;
                        } else {
                            sqr.option = true;
                        }
                    }
                }
                moving = true;
            }
        }

    }

    private void take(Square newSqr){

        if(newSqr.placedPiece != null){
            Piece attacked = newSqr.placedPiece;
            attacked.setTaken();
            takenPieces.add(attacked);

            newSqr.placedPiece = null;
            newSqr.target = false;

        }

    }

    private ArrayList<Square> findLegal(Piece p){

        ArrayList<Square> tab = p.possibleMove(b.squareTable,false, this);
        Square pieceSqr = p.place;
        pieceSqr.placedPiece = null;
        Square sqr;
        Piece tempPiece;

        for(int i =0; i< tab.size();i++){
                    sqr = tab.get(i);
                    if(!(sqr.placedPiece != null&&sqr.placedPiece.color == turn)){
                        tempPiece = sqr.placedPiece;
                        if(tempPiece != null) tempPiece.setTaken();
                        p.setPlace(sqr);

                        sqr.placedPiece = p;
                        if(turn == "white"){
                            if(isCheck(whitePieces[0].place)){

                                tab.set(i,null);
                            }
                        } else {
                            if(isCheck(blackPieces[0].place)){
                                tab.set(i,null);
                            }
                        }
                        if(tempPiece != null) tempPiece.setUntaken();
                        sqr.placedPiece = tempPiece;                        
                    }
        }
       pieceSqr.placedPiece = p;
       p.setPlace(pieceSqr);

        return tab;

    }


    public boolean isCheck(Square s){
        Piece[] pieces; 

        Piece tempPiece = s.placedPiece;
        if(tempPiece != null) tempPiece.setTaken();

        if(turn == "white"){
            pieces = blackPieces;
            s.placedPiece = new Pawn("white",s);
        } else {
            pieces = whitePieces;
            s.placedPiece = new Pawn("black",s);
        }

        for(int i = 0; i < 16; i++){
            if(pieces[i] != null){
                if(!pieces[i].isTaken()){
                    pieces[i].possibleMove(b.squareTable,true, this);
                    if(s.target){
                        b.clearMarks();
                        if(tempPiece != null) tempPiece.setUntaken();
                        s.placedPiece = tempPiece;
                        return true;
                    }
                }
            }
        }
        b.clearMarks();
        if(tempPiece != null) tempPiece.setUntaken();
        s.placedPiece = tempPiece;
        return false;
    }

    private boolean isMate(){ //or draw
        Piece[] pieces; 
        ArrayList<Square> tab;
        if(turn == "white"){
            pieces = whitePieces;
        } else {
            pieces = blackPieces;
        }

        for(int i =0; i < 16; i++){
            if(pieces[i] != null){
                if(!pieces[i].isTaken()){
                    tab = findLegal(pieces[i]);

                    if(pieces[i] instanceof King) {
                        int y = (pieces[i].color == "white")? 7:0;
                        b.squareTable[0][y].castling = false;
                        b.squareTable[7][y].castling = false;
                        b.squareTable[2][y].castling = false;
                        b.squareTable[6][y].castling = false;
                    }

                    for(int j = 0; j < tab.size(); j++){
                        if(tab.get(j) != null) return false;
                    }
                }
            }
        }
        return true;
    }

    public void restart(){
        b = new Board(this);
        setPieces();
        takenPieces.clear();
        moving = false;
        turn = "white";
        lastSqr =null;
        lastTurn = new Square[2];
        this.repaint();

    }

    private void gameOver(boolean isDraw){
        this.repaint();
        String[] options = {"Play again"};
        int choice;
        if (!isDraw){
            if(turn == "white"){
                //System.out.println("\nColor black won!!");
                choice = JOptionPane.showOptionDialog(this, "Black won","Game over",0,JOptionPane.PLAIN_MESSAGE,null, options, null);
            } else {
                //System.out.println("\nColor white won!!");
                choice = JOptionPane.showOptionDialog(this, "White won","Game over",0,JOptionPane.PLAIN_MESSAGE,null, options, null);            
            }
        } else {
                //System.out.println("\nDraw!!");
                choice = JOptionPane.showOptionDialog(this, "It's a draw","Game over",0,JOptionPane.PLAIN_MESSAGE,null, options, null);                        
        }

        if(choice == 0){
            restart();
        }

        


    }

}
