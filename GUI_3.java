import java.awt.*;

import javax.swing.*;
import java.awt.event.*;

public class GUI_3 {    
public static void main(String[] args) {
 
        JFrame frm = new JFrame();
        
        frm.setTitle("MouseEvent");
 
        frm.setSize(350, 300);
 
        frm.setLocationRelativeTo(null);
 
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        frm.getContentPane().setLayout(null);
 
      
        JLabel lbl = new JLabel();
        lbl.setBounds(50, 50, 50, 50);
        lbl.setText("HELLO");
        frm.getContentPane().add(lbl);
 
        frm.setVisible(true);

        
        class MyMouseListener implements MouseListener {
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                lbl.setLocation(x, y);
            }
     
    	   public void mouseClicked(MouseEvent e) {
    	   
       }
    	   public void mouseEntered(MouseEvent e) {
    		   
    	   }
    	   public void mouseExited(MouseEvent e) {
    		   
    	   }
    	   public void mouseReleased(MouseEvent e) {
    		   
    	   }

}
       frm.getContentPane().addMouseListener(new MyMouseListener());

}
}




