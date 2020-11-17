import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class GUI_2 {    
public static void main(String[] args) {
	KeyExample k = new KeyExample();
	
}
}

class KeyExample extends JFrame{
	JLabel jl;
	Container c1 = getContentPane();
	public KeyExample() {
		c1.setLayout(new FlowLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("색상변경");
		setSize(330, 300);
		
		jl = new JLabel("<Enter>키로 배경색이 바뀝니다");
		c1.add(jl);
		c1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int a = (int) (Math.random() * 256); 
				int b = (int) (Math.random() * 256); 
				int c = (int) (Math.random() * 256); 
				
				if (e.getKeyChar() == '\n') {
					jl.setText("r=" +a + ", g=" + b+ ", b=" +c);
					c1.setBackground(new Color(a,b,c));
				}
				else if(e.getKeyChar() == 'q') {
					System.exit(0);
				}
			}
		});
		setVisible(true);
		c1.requestFocus();
		
	}


}


