import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Square extends JPanel{

    public boolean highlighted = false;
    public boolean lastMove = false;
    public boolean target = false;
    public boolean option = false;
    public boolean enPassant = false;    
    public boolean castling = false;
    public boolean checked = false;

    private int boardX;
    private int boardY;
    private int len;
    Piece placedPiece = null;

    JButton b;
    Square(int a, int b, int edgeLen){
        len = edgeLen;
        boardX = len*a;
        boardY = len*b;
    }

    public void paintTarget(Graphics g){

        Graphics2D g2d = (Graphics2D)g;
        float thickness = 4;
        int half= (int)thickness/2;
        Stroke oldStroke = g2d.getStroke();
        g2d.setStroke(new BasicStroke(thickness));

        Color seaGreen = new Color(46,139,113);
        g2d.setColor(seaGreen);
        g2d.drawRect(boardX+half,boardY+half,(len-(int)thickness),(len-(int)thickness));
        g2d.setStroke(oldStroke);

    }

    public void paintOption(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        Color seaGreen = new Color(46,139,113);

        g2d.setPaint(seaGreen);
        g2d.fillOval(boardX+len/3,boardY+len/3,len/3,len/3);
    }
    
    public void paintChecked(Graphics g){

        Graphics2D g2d = (Graphics2D)g.create();
        Color orange = new Color(255,0,0);
        g2d.setPaint(orange);
        g2d.setComposite(
            AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g2d.fillRect(boardX, boardY, len, len);

        g2d.dispose();
    }


    public int getBoardX(){
        return boardX;
    }
    public int getBoardY(){
        return boardY;
    }
    public int getLen(){
        return len;
    }

    public static ImageIcon getScaledImage(ImageIcon srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg.getImage(), 0, 0, w, h, null );
        g2.dispose();

        return (new ImageIcon(resizedImg));
    }

}
