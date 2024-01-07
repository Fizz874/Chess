import java.awt.*;
import javax.swing.*;


public class Board extends JPanel{

    Game parentGame;
    Square [][] squareTable = new Square[8][8];
    Square lastSqr = null;
    public int edgeLen;  

    Board(Game g) {
        parentGame = g;
        edgeLen = parentGame.len;
        for(int i =0; i <8; i++){
            for(int j =0; j <8; j++){
                squareTable[i][j] = new Square(i, j, edgeLen);
            }
        }
    }

    public void paint(Graphics g) {

        Graphics2D g2D = (Graphics2D) g;

        //rysowanie planszy
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                int x = i*edgeLen;
                int y = j*edgeLen;
                if(squareTable[i][j].highlighted){
                    Color seaGreen = new Color(46,139,113);
                    g2D.setColor(seaGreen);
                } else if(squareTable[i][j].lastMove){
                    Color lightGreen = new Color(154,205,50);
                    g2D.setColor(lightGreen);
                } else {
                    if((i+j)%2 == 0){
                        g2D.setColor(Color.lightGray);
                    } else {
                        g2D.setColor(Color.white);
                    }
                }
                g2D.fillRect(x, y, edgeLen, edgeLen);
                if(squareTable[i][j].checked){
                    squareTable[i][j].paintChecked(g);
                }
                if (squareTable[i][j].placedPiece != null){
                    squareTable[i][j].placedPiece.paint(g);
                }
                if(squareTable[i][j].target){
                    squareTable[i][j].paintTarget(g);
                }
                if(squareTable[i][j].option){
                    squareTable[i][j].paintOption(g);
                }
                
            }
        }


    }

    public void clearMarks(){
        for(int i =0; i < 8;i++){
            for(int j =0; j < 8; j++){
                squareTable[i][j].target = false;
                squareTable[i][j].option = false;
            }
        }
    }
}
