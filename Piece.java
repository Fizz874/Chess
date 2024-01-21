import java.awt.Graphics;
import java.util.ArrayList;

abstract public class Piece {
    protected int x;
    protected int y;
    protected String color;
    protected Square place;
    private boolean taken = false;
    private boolean moved = false;
    public abstract void paint(Graphics g);
    public abstract ArrayList<Square> possibleMove(Square[][] t, boolean c, Game g);

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public boolean isTaken(){
        return taken;
    }

    public boolean isMoved(){
        return moved;
    }

    public void setPlace(Square s){
        place = s;
        x = s.getBoardX()/s.getLen();
        y = s.getBoardY()/s.getLen();
    }

    public void setTaken(){
        taken = true;
    }

    public void setUntaken(){
        taken = false;
    }

    public void setMoved(){
        moved = true;
    }

}
