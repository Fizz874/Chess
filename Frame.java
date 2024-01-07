

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Frame extends JFrame{

    Game game;

    Frame(){
        game = new Game();

        JMenuBar mb=new JMenuBar();  
        JMenu menu=new JMenu("Menu");   
        JMenuItem i1;
        i1=new JMenuItem("Restart");  
        i1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                game.restart();
            }
        });
        menu.add(i1);
        mb.add(menu);  
        this.setJMenuBar(mb); 

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        this.add(game, c);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);


    }

}
