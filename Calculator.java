import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Font;

public class Calculator extends JFrame implements KeyListener{

    JPanel contentPane;

    JPanel pnDisplay;

    JPanel pnInput;

    JPanel pnHistory;

    JTextField textField;

    JButton btn7;

    JButton btn8;
    JButton btn9;

    JButton btnAdd;

    JButton btnClearAll;

    JButton btn4;

    JButton btn5;
    JButton btnright;
    JButton btnleft;

    JButton btn6;

    JButton btnMinus;

    JButton btnClearText;

    JButton btn1;

    JButton btn2;

    JButton btn3;

    JButton btnMultiply;


    JButton btn0;

    JButton btnDot;

    JButton btnEqual;

    JButton btnDivide;


    DefaultListModel<String> listData;
    JList<String> list;

    private static boolean dot=false;
    private String exp="";




    /**
     * Launch the application.
     */

    void updateTextDigit(String s){
        exp+=s;
        textField.setText(exp);
        listData.addElement(s);
    }
    void deleteTextDigit(){
        exp = exp.substring(0,exp.length()-1);
        listData.remove(listData.size()-1);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_0:              //가상 키 코드
            case KeyEvent.VK_NUMPAD0:
                btn0.doClick();   // 리스트 index 번호를 가져옴
                break;

            case KeyEvent.VK_NUMPAD1:
            case KeyEvent.VK_1:
                btn1.doClick();
                break;

            case KeyEvent.VK_2:
            case KeyEvent.VK_NUMPAD2:
                btn2.doClick();
                break;

            case KeyEvent.VK_3:
            case KeyEvent.VK_NUMPAD3:
                btn3.doClick();
                break;

            case KeyEvent.VK_4:
            case KeyEvent.VK_NUMPAD4:
                btn4.doClick();
                break;

            case KeyEvent.VK_5:
            case KeyEvent.VK_NUMPAD5:
                btn5.doClick();
                break;

            case KeyEvent.VK_6:
            case KeyEvent.VK_NUMPAD6:
                btn6.doClick();
                break;

            case KeyEvent.VK_7:
            case KeyEvent.VK_NUMPAD7:
                btn7.doClick();
                break;

            case KeyEvent.VK_8:
            case KeyEvent.VK_NUMPAD8:
                btn8.doClick();
                break;

            case KeyEvent.VK_9:
            case KeyEvent.VK_NUMPAD9:
                btn9.doClick();
                break;

            case KeyEvent.VK_PERIOD :
            case KeyEvent.VK_DECIMAL :
                btnDot.doClick();
                break;

            case KeyEvent.VK_ENTER :
                btnEqual.doClick();
                break;

            case KeyEvent.VK_MINUS :
            case KeyEvent.VK_SUBTRACT :
                btnMinus.doClick();
                break;

            case KeyEvent.VK_ESCAPE :
                btnClearText.doClick();
                break;

            case KeyEvent.VK_ADD :
                btnAdd.doClick();
                break;

            case KeyEvent.VK_MULTIPLY :
                btnMultiply.doClick();
                break;

            case KeyEvent.VK_DIVIDE :
            case KeyEvent.VK_SLASH :
                btnDivide.doClick();
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }



            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('x')) x *= parseFactor();
                    else if (eat('/')) x /= parseFactor();
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();

                double x;
                int startPos = this.pos;
                if (eat('(')) {
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') {
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);

                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }


                return x;
            }
        }.parse();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Calculator frame = new Calculator();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Calculator() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);


        pnDisplay = new JPanel();

        contentPane.add(pnDisplay, BorderLayout.NORTH);

        pnDisplay.setLayout(new GridLayout(0, 1, 0, 0));


        textField = new JTextField();

        textField.setHorizontalAlignment(SwingConstants.RIGHT);

        textField.setFont(new Font("Courier New", Font.PLAIN, 28));

        pnDisplay.add(textField);

        textField.setColumns(10);


        pnInput = new JPanel();

        contentPane.add(pnInput, BorderLayout.CENTER);

        pnInput.setLayout(new GridLayout(4, 5, 5, 5));


        btn7 = new JButton("7");
        btn7.addKeyListener(this);
        btn7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                updateTextDigit("7");
            }
        });

        btn7.setFont(new Font("굴림", Font.BOLD, 14));



        pnInput.add(btn7);


        btn8 = new JButton("8");
        btn8.addKeyListener(this);
        btn8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                updateTextDigit("8");
            }
        });

        btn8.setFont(new Font("굴림", Font.BOLD, 14));



        pnInput.add(btn8);


        btn9 = new JButton("9");
        btn9.addKeyListener(this);
        btn9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                updateTextDigit("9");
            }
        });

        btn9.setFont(new Font("굴림", Font.BOLD, 14));



        pnInput.add(btn9);


        btnAdd = new JButton("+");
        btnAdd.addKeyListener(this);
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int len=exp.length();
                len=len-1;
                String c="";
                int f=0;
                try {
                    c = Character.toString(exp.charAt(len));
                }catch (Exception e){
                    if(!c.equals("."))
                        f=1;
                }
                if(f!=1){
                    if(c.equals("+")||c.equals("-")||c.equals("x")||c.equals("/")){
                        deleteTextDigit();
                    }
                    else if(c.equals(".")){
                        dot = false;
                        updateTextDigit("0");
                    }
                    else{
                        dot = false;
                    }
                    updateTextDigit("+");
                }
            }
        });

        btnAdd.setFont(new Font("굴림", Font.BOLD, 14));



        pnInput.add(btnAdd);


        btnClearAll = new JButton("C");
        btnClearAll.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                textField.setText("");
                exp="";
            }

        });

        btnClearAll.setFont(new Font("굴림", Font.BOLD, 14));

        pnInput.add(btnClearAll);


        btn4 = new JButton("4");
        btn4.addKeyListener(this);
        btn4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                updateTextDigit("4");            }
        });

        btn4.setFont(new Font("굴림", Font.BOLD, 14));



        pnInput.add(btn4);


        btn5 = new JButton("5");
        btn5.addKeyListener(this);
        btn5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                updateTextDigit("5");
            }
        });

        btn5.setFont(new Font("굴림", Font.BOLD, 14));



        pnInput.add(btn5);


        btn6 = new JButton("6");
        btn6.addKeyListener(this);
        btn6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                updateTextDigit("6");
            }
        });

        btn6.setFont(new Font("굴림", Font.BOLD, 14));



        pnInput.add(btn6);


        btnMinus = new JButton("-");
        btnMinus.addKeyListener(this);
        btnMinus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int len=exp.length();
                len=len-1;
                String c="";
                int f=0;
                try {
                    c = Character.toString(exp.charAt(len));
                }catch (Exception e){
                    if(!c.equals("."))
                        f=1;

                }
                if(f!=1){
                    if(c.equals("+")||c.equals("-")||c.equals("x")||c.equals("/")){
                        deleteTextDigit();
                    }
                    else if(c.equals(".")){
                        dot = false;
                        updateTextDigit("0");
                    }
                    else{
                        dot = false;
                    }
                    updateTextDigit("-");
                }
            }
        });

        btnMinus.setFont(new Font("굴림", Font.BOLD, 14));



        pnInput.add(btnMinus);


        btnClearText = new JButton("CE");
        btnClearText.addKeyListener(this);
        btnClearText.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                textField.setText("");
                exp="";
                listData.clear();
            }

        });

        btnClearText.setFont(new Font("굴림", Font.BOLD, 14));

        pnInput.add(btnClearText);


        btn1 = new JButton("1");
        btn1.addKeyListener(this);
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                updateTextDigit("1");
            }
        });

        btn1.setFont(new Font("굴림", Font.BOLD, 14));



        pnInput.add(btn1);


        btn2 = new JButton("2");
        btn2.addKeyListener(this);
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                updateTextDigit("2");
            }
        });

        btn2.setFont(new Font("굴림", Font.BOLD, 14));



        pnInput.add(btn2);


        btn3 = new JButton("3");
        btn3.addKeyListener(this);
        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                updateTextDigit("3");
            }
        });

        btn3.setFont(new Font("굴림", Font.BOLD, 14));



        pnInput.add(btn3);


        btnMultiply = new JButton("x");
        btnMultiply.addKeyListener(this);
        btnMultiply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int len=exp.length();
                len=len-1;
                String c="";
                int f=0;
                try {
                    c = Character.toString(exp.charAt(len));
                }catch (Exception e){
                    f=1;

                }
                if(f!=1){
                    if(c.equals("+")||c.equals("-")||c.equals("x")||c.equals("/")){
                        deleteTextDigit();
                    }
                    else if(c.equals(".")){
                        dot = false;
                        updateTextDigit("0");
                    }
                    else{
                        dot = false;
                    }
                    updateTextDigit("x");
                }
            }
        });

        btnMultiply.setFont(new Font("굴림", Font.BOLD, 14));



        pnInput.add(btnMultiply);


        btnright = new JButton("(");
        btnright.addKeyListener(this);
        btnright.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                updateTextDigit("(");
            }
        });
        btnright.setFont(new Font("굴림", Font.BOLD, 14));

        pnInput.add(btnright);


        btn0 = new JButton("0");
        btn0.addKeyListener(this);
        btn0.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                updateTextDigit("0");
            }
        });

        btn0.setFont(new Font("굴림", Font.BOLD, 14));



        pnInput.add(btn0);


        btnDot = new JButton(".");
        btnDot.addKeyListener(this);
        btnDot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int len=exp.length();
                len=len-1;
                String c="";
                int f=0;
                try {
                    c = Character.toString(exp.charAt(len));
                }catch (Exception e){
                    f=1;

                }
                if(f!=1){
                    if(c.equals("+")||c.equals("-")||c.equals("*")||c.equals("/")||c.equals(".")){
                    }
                    else{
                        if(!dot)
                            updateTextDigit(".");
                        dot = true;
                    }}
            }
        });

        btnDot.setFont(new Font("굴림", Font.BOLD, 14));

        pnInput.add(btnDot);


        btnEqual = new JButton("=");
        btnEqual.addKeyListener(this);
        btnEqual.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(!exp.equals("")) {
                    try {
                        double evalu = eval(exp);
                        if(evalu == Double.POSITIVE_INFINITY || evalu == Double.NEGATIVE_INFINITY){
                            exp = "Cannot divide by zero";
                        }
                        else if(evalu%1<0.000001){
                            exp = Integer.toString((int)evalu);
                        }
                        else exp = Double.toString(evalu);

                        textField.setText(exp);
                        listData.addElement("=");
                        listData.addElement(exp);

                    } catch (Exception e) {
                        exp = "Error";
                        textField.setText(exp);
                        listData.addElement("=");
                        listData.addElement(exp);
                        exp="";
                    }

                }
            }
        });

        btnEqual.setFont(new Font("굴림", Font.BOLD, 14));



        pnInput.add(btnEqual);


        btnDivide = new JButton("/");
        btnDivide.addKeyListener(this);
        btnDivide.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int len=exp.length();
                len=len-1;
                String c="";
                int f=0;
                try {
                    c = Character.toString(exp.charAt(len));
                }catch (Exception e){
                    if(!c.equals("."))
                        f=1;

                }
                if(f!=1){
                    if(c.equals("+")||c.equals("-")||c.equals("x")||c.equals("/")){
                        deleteTextDigit();
                    }
                    else if(c.equals(".")){
                        dot = false;
                        updateTextDigit("0");
                    }
                    else{
                        dot = false;
                    }
                    updateTextDigit("/");
                }
            }
        });

        btnDivide.setFont(new Font("굴림", Font.BOLD, 14));



        pnInput.add(btnDivide);


        btnleft = new JButton(")");
        btnleft.addKeyListener(this);
        btnleft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                updateTextDigit(")");
            }
        });
        btnleft.setFont(new Font("굴림", Font.BOLD, 14));

        pnInput.add(btnleft);


        pnHistory = new JPanel();


        contentPane.add(pnHistory, BorderLayout.EAST);

        pnHistory.setPreferredSize(new Dimension(120, 200));




        pnHistory.setLayout(new BorderLayout(5, 5));

        listData =new DefaultListModel();
        list = new JList(listData);
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                exp="";
                updateTextDigit((String)list.getSelectedValue());

            }
        });

        pnHistory.add(list);
        pnHistory.add(new JScrollPane(list,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));

        /*

         * End of GUI code

         */



    }



}