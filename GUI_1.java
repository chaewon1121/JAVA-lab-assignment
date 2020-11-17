import java.awt.*;

import javax.swing.*;
import java.awt.event.*;

public class GUI_1 {    
public static void main(String[] args) {
 
        JFrame frm = new JFrame();
        
        frm.setTitle("상,하,좌,우");
 
        frm.setSize(350, 300);
 
        frm.setLocationRelativeTo(null);
 
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        frm.getContentPane().setLayout(null);
 
      
        JLabel lbl = new JLabel();
        lbl.setBounds(50, 50, 50, 50);
        lbl.setText("HELLO");
        frm.getContentPane().add(lbl);
 
        frm.setVisible(true);
        
        frm.addKeyListener(new KeyAdapter()
        { 
         public void keyPressed(KeyEvent e){
          switch(e.getKeyCode()){
           case KeyEvent.VK_UP:
            lbl.setLocation(lbl.getX(), lbl.getY()-10);
            break;
         
           case KeyEvent.VK_DOWN:
            lbl.setLocation(lbl.getX(), lbl.getY()+10);
            break;
           case KeyEvent.VK_LEFT:
            lbl.setLocation(lbl.getX()-10, lbl.getY());
            break;
           case KeyEvent.VK_RIGHT:
            lbl.setLocation(lbl.getX()+10, lbl.getY());
            break;
         

         }

        }
        });
       
 
    }
}





