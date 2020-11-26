/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tic_tac_toe;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet; 
import java.util.logging.Level;
import java.util.logging.Logger;
public class TTT extends javax.swing.JFrame
{
    private boolean flag =false;
    private String userid="";
    static class Move
    { int row, col; };
    static char player = 'X', opponent = 'O';
    private int xCount = 0;
    private int oCount = 0;
    private int draw =0;
    private int level = 9;
    char board[][]={{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
    void space()
    {
        jButton7 .setText(" ");
        jButton8 .setText(" ");
        jButton9 .setText(" ");
        jButton10.setText(" ");
        jButton11.setText(" ");
        jButton12.setText(" ");
        jButton13.setText(" ");
        jButton14.setText(" ");
        jButton15.setText(" ");
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                board[i][j]=' ';
    }
    static Boolean isMovesLeft(char board[][])
    {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ')
                    return true;
        return false;
    }
    static int evaluate(char b[][])
    {
        for (int row = 0; row < 3; row++)
        {
            if (b[row][0] == b[row][1] &&b[row][1] == b[row][2])
            {
                if (b[row][0] == player)
                    return -10;
                else if (b[row][0] == opponent)
                    return +10;
            }
        }
        for (int col = 0; col < 3; col++)
        {
            if (b[0][col] == b[1][col] &&b[1][col] == b[2][col])
            {
                if (b[0][col] == player)
                    return -10;

                else if (b[0][col] == opponent)
                    return +10;
            }
        }
        if (b[0][0] == b[1][1] && b[1][1] == b[2][2])
        {
            if (b[0][0] == player)
                return -10;
            else if (b[0][0] == opponent)
                return +10;
        }
        if (b[0][2] == b[1][1] && b[1][1] == b[2][0])
        {
            if (b[0][2] == player)
                return -10;
            else if (b[0][2] == opponent)
                return +10;
        }
        return 0;
    }
    static int minimax(char board[][],int depth, Boolean isMax)
    {
        int score = evaluate(board);
        if (score == 10)
            return score;
        if (score == -10)
            return score;
        if (isMovesLeft(board) == false)
            return 0;
        if (isMax)
        {
            int best = -1000;
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    if (board[i][j]==' ')
                    {
                        board[i][j] = opponent;
                        best = Math.max(best, minimax(board,depth + 1, !isMax));
                        board[i][j] = ' ';
                    }
                }
            }
            return best;
        }
        else
        {
            int best = 1000;
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    if (board[i][j] == ' ')
                    {
                        board[i][j] = player;
                        best = Math.min(best, minimax(board,depth + 1, !isMax));
                        board[i][j] = ' ';
                    }
                }
            }
            return best;
        }
    }
    static Move bot(char board[][])
    {
        int bestVal = -1000;
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (board[i][j] == ' ')
                {
                    board[i][j] = opponent;
                    int moveVal = minimax(board, 0, false);
                    board[i][j] = ' ';
                    if (moveVal > bestVal)
                    {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        return bestMove;
    }
    void moveMaker(Move botsmove)
    {
        if(botsmove.row==0)
        {
            switch (botsmove.col) {
                case 0:
                    jButton7.setText(Character.toString(opponent));
                    board[botsmove.row][botsmove.col]=opponent;
                    break;
                case 1:
                    jButton8.setText(Character.toString(opponent));
                    board[botsmove.row][botsmove.col]=opponent;
                    break;
                case 2:
                    jButton9.setText(Character.toString(opponent));
                    board[botsmove.row][botsmove.col]=opponent;
                    break;
                default:
                    break;
            }
        }
        if(botsmove.row==1)
        {
            switch (botsmove.col) {
                case 0:
                    jButton10.setText(Character.toString(opponent));
                    board[botsmove.row][botsmove.col]=opponent;
                    break;
                case 1:
                    jButton11.setText(Character.toString(opponent));
                    board[botsmove.row][botsmove.col]=opponent;
                    break;
                case 2:
                    jButton12.setText(Character.toString(opponent));
                    board[botsmove.row][botsmove.col]=opponent;
                    break;
                default:
                    break;
            }
        }
        if(botsmove.row==2)
        {
            switch (botsmove.col) {
                case 0:
                    jButton13.setText(Character.toString(opponent));
                    board[botsmove.row][botsmove.col]=opponent;
                    break;
                case 1:
                    jButton14.setText(Character.toString(opponent));
                    board[botsmove.row][botsmove.col]=opponent;
                    break;
                case 2:
                    jButton15.setText(Character.toString(opponent));
                    board[botsmove.row][botsmove.col]=opponent;
                    break;
                default:
                    break;
            }
        }
    }
    Move allBots(char board[][],int level)
    {
        int random;
        random = ((int)((Math.random())*10))%level;
        if (level==10)
            return bot(board);
        else if (random==0)
        {
            Move eb = new Move();
            int random1=(int)(((Math.random())*10)%3),random2=(int)(((Math.random())*10)%3);
            while(board[random1][random2]!=' ')
            {
                random1= (int)(((Math.random())*10)%3);
                random2= (int)(((Math.random())*10)%3);
            }
            eb.row=random1;
            eb.col=random2;
            return eb;
        }
        else
            return bot(board);
    }
    void scoreMonitor(int count,boolean drawcount)
    {
        if(count==-10)
        {
            
            if(player =='X')
            {
                xCount++;
                jLabel11.setText(Integer.toString(xCount));
            }
            else
            {
                oCount++;
                jLabel12.setText(Integer.toString(oCount));
            }
        }
        else if (count==10)
        {
            if(opponent =='X')
            {
                xCount++;
                jLabel11.setText(Integer.toString(xCount));
            }
            else
            {
                oCount++;
                jLabel12.setText(Integer.toString(oCount));
            }
        }
        else if(drawcount)
        {    
            draw++;
            jLabel50.setText(Integer.toString(draw));
        }
    }
    boolean checker()
    {
        int count;
        boolean drawcount=(!isMovesLeft(board));
        count = evaluate(board);
        scoreMonitor(count,drawcount);
        if(0!=count)
        {
            space();
            if(opponent == 'X')
                moveMaker(allBots(board,level));
            return false;
        } 
        else if(!isMovesLeft(board))
        {
            space();            
            if(opponent == 'X')
                moveMaker(allBots(board,level));
            return false;
        }
        else
            return true;
    }
    private void hidden(boolean flag)
    {
        jButton19.setVisible(flag);
        jLabel13.setVisible(flag);
        jLabel14.setVisible(flag);
    }
    public boolean verifyFields(String name,String userid,String password,String confirmpassword)
    {
        if(name.trim().equals("") || userid.trim().equals("") || password.trim().equals("") || confirmpassword.trim().equals(""))
        {
            JOptionPane.showMessageDialog(null, "All fields are required ! ","Empty Fields",2);
            return false;
        }
        else if(!password.equals(confirmpassword))
        {
            JOptionPane.showMessageDialog(null, "Password doesn't match ! ","Confirm Password",2);
            return false;
        }
        else
        {
            return true;
        }
    }
    public boolean checkUsername(String userid) throws ClassNotFoundException
    {
        PreparedStatement ps;
        ResultSet rs;
        boolean username_exist = false;
        Connection conn;
        String query = "SELECT * FROM data WHERE Username=?";
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdatabase?" + "user=root");
            ps= conn.prepareStatement(query);
            ps.setString(1,userid);
            rs= ps.executeQuery();
            if(rs.next())
            {
                username_exist = true;
                JOptionPane.showMessageDialog(null,"This username is already taken","Username Failed",2);
            }
        } 
        catch (SQLException | ClassNotFoundException ex) 
        {
            Logger.getLogger(TTT.class.getName()).log(Level.SEVERE, null, ex);
        }
        return username_exist;
    }
    public TTT()
    {
        initComponents();
        setSize(1280,720);
        setLocationRelativeTo(null);
        jRadioButton1.setSelected(true);
        jRadioButton4.setSelected(true);
        hidden(flag);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JFrame();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        register = new javax.swing.JFrame();
        jTextField3 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jPasswordField2 = new javax.swing.JPasswordField();
        jPasswordField3 = new javax.swing.JPasswordField();
        jLabel56 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        game = new javax.swing.JFrame();
        jButton16 = new javax.swing.JButton();
        jLabel49 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jLabel52 = new javax.swing.JLabel();
        stats = new javax.swing.JFrame();
        jLabel21 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jButton20 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton19 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();

        login.setSize(new java.awt.Dimension(1280, 720));
        login.getContentPane().setLayout(null);

        jLabel4.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("UserID");
        login.getContentPane().add(jLabel4);
        jLabel4.setBounds(410, 150, 114, 50);

        jTextField2.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        login.getContentPane().add(jTextField2);
        jTextField2.setBounds(570, 145, 253, 50);

        jPasswordField1.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });
        login.getContentPane().add(jPasswordField1);
        jPasswordField1.setBounds(570, 235, 253, 50);

        jLabel6.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Password");
        login.getContentPane().add(jLabel6);
        jLabel6.setBounds(390, 225, 163, 50);

        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Login");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        login.getContentPane().add(jButton2);
        jButton2.setBounds(590, 340, 125, 59);

        jButton17.setBackground(new java.awt.Color(0, 0, 0));
        jButton17.setFont(new java.awt.Font("Open Sans", 0, 24)); // NOI18N
        jButton17.setForeground(new java.awt.Color(255, 255, 255));
        jButton17.setText("Back");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        login.getContentPane().add(jButton17);
        jButton17.setBounds(10, 620, 85, 41);

        jButton18.setBackground(new java.awt.Color(0, 0, 0));
        jButton18.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jButton18.setForeground(new java.awt.Color(255, 255, 255));
        jButton18.setText("Register");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        login.getContentPane().add(jButton18);
        jButton18.setBounds(1020, 610, 169, 59);

        jLabel5.setFont(new java.awt.Font("Open Sans", 0, 75)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Login");
        login.getContentPane().add(jLabel5);
        jLabel5.setBounds(520, 0, 190, 103);

        jLabel57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tic_tac_toe/972105c5a775f38cf33d3924aea053f1.jpg"))); // NOI18N
        jLabel57.setText("jLabel57");
        login.getContentPane().add(jLabel57);
        jLabel57.setBounds(410, 20, 100, 90);

        jLabel55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tic_tac_toe/ezgif.com-gif-maker (1).gif"))); // NOI18N
        login.getContentPane().add(jLabel55);
        jLabel55.setBounds(0, 0, 1200, 680);

        register.setSize(new java.awt.Dimension(1280, 720));
        register.getContentPane().setLayout(null);

        jTextField3.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        register.getContentPane().add(jTextField3);
        jTextField3.setBounds(560, 180, 340, 56);

        jLabel2.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("UserId");
        register.getContentPane().add(jLabel2);
        jLabel2.setBounds(410, 180, 110, 50);

        jLabel7.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Password");
        register.getContentPane().add(jLabel7);
        jLabel7.setBounds(370, 330, 163, 50);

        jLabel8.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Name");
        register.getContentPane().add(jLabel8);
        jLabel8.setBounds(420, 250, 100, 50);

        jTextField4.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        register.getContentPane().add(jTextField4);
        jTextField4.setBounds(560, 250, 340, 56);

        jButton6.setBackground(new java.awt.Color(0, 0, 0));
        jButton6.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Submit");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        register.getContentPane().add(jButton6);
        jButton6.setBounds(540, 500, 240, 110);

        jButton21.setBackground(new java.awt.Color(0, 0, 0));
        jButton21.setFont(new java.awt.Font("Open Sans", 0, 24)); // NOI18N
        jButton21.setForeground(new java.awt.Color(255, 255, 255));
        jButton21.setText("Back");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        register.getContentPane().add(jButton21);
        jButton21.setBounds(20, 620, 85, 41);

        jLabel1.setFont(new java.awt.Font("Open Sans", 0, 75)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Register");
        register.getContentPane().add(jLabel1);
        jLabel1.setBounds(510, 30, 340, 140);

        jLabel54.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setText("Confrim Password ");
        register.getContentPane().add(jLabel54);
        jLabel54.setBounds(240, 400, 317, 50);

        jPasswordField2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jPasswordField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField2ActionPerformed(evt);
            }
        });
        register.getContentPane().add(jPasswordField2);
        jPasswordField2.setBounds(560, 330, 336, 50);

        jPasswordField3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jPasswordField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField3ActionPerformed(evt);
            }
        });
        register.getContentPane().add(jPasswordField3);
        jPasswordField3.setBounds(560, 400, 330, 50);

        jLabel56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tic_tac_toe/images.png"))); // NOI18N
        jLabel56.setText("jLabel56");
        register.getContentPane().add(jLabel56);
        jLabel56.setBounds(400, 30, 120, 119);

        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tic_tac_toe/ezgif.com-gif-maker (1).gif"))); // NOI18N
        register.getContentPane().add(jLabel53);
        jLabel53.setBounds(0, 0, 1210, 670);

        game.setSize(new java.awt.Dimension(1280, 720));
        game.getContentPane().setLayout(null);

        jButton16.setBackground(new java.awt.Color(0, 0, 0));
        jButton16.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jButton16.setForeground(new java.awt.Color(255, 255, 255));
        jButton16.setText("Back");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        game.getContentPane().add(jButton16);
        jButton16.setBounds(880, 520, 111, 59);

        jLabel49.setFont(new java.awt.Font("Open Sans", 0, 24)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("Draw :");
        game.getContentPane().add(jLabel49);
        jLabel49.setBounds(900, 160, 72, 33);

        jLabel10.setFont(new java.awt.Font("Open Sans", 0, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Player O:");
        game.getContentPane().add(jLabel10);
        jLabel10.setBounds(870, 120, 99, 33);

        jLabel9.setFont(new java.awt.Font("Open Sans", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Player X:");
        game.getContentPane().add(jLabel9);
        jLabel9.setBounds(870, 80, 94, 33);

        jLabel11.setFont(new java.awt.Font("Open Sans", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("0  ");
        game.getContentPane().add(jLabel11);
        jLabel11.setBounds(990, 80, 30, 30);

        jLabel12.setFont(new java.awt.Font("Open Sans", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("0");
        game.getContentPane().add(jLabel12);
        jLabel12.setBounds(990, 120, 20, 33);

        jLabel50.setFont(new java.awt.Font("Open Sans", 0, 24)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("0");
        game.getContentPane().add(jLabel50);
        jLabel50.setBounds(990, 160, 20, 33);

        jButton11.setBackground(new java.awt.Color(0, 0, 0));
        jButton11.setFont(new java.awt.Font("Open Sans", 0, 96)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText(" ");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        game.getContentPane().add(jButton11);
        jButton11.setBounds(270, 230, 250, 212);

        jButton12.setBackground(new java.awt.Color(0, 0, 0));
        jButton12.setFont(new java.awt.Font("Open Sans", 0, 96)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText(" ");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        game.getContentPane().add(jButton12);
        jButton12.setBounds(530, 230, 250, 212);

        jButton14.setBackground(new java.awt.Color(0, 0, 0));
        jButton14.setFont(new java.awt.Font("Open Sans", 0, 96)); // NOI18N
        jButton14.setForeground(new java.awt.Color(255, 255, 255));
        jButton14.setText(" ");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        game.getContentPane().add(jButton14);
        jButton14.setBounds(270, 450, 250, 212);

        jButton15.setBackground(new java.awt.Color(0, 0, 0));
        jButton15.setFont(new java.awt.Font("Open Sans", 0, 96)); // NOI18N
        jButton15.setForeground(new java.awt.Color(255, 255, 255));
        jButton15.setText(" ");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        game.getContentPane().add(jButton15);
        jButton15.setBounds(530, 450, 250, 212);

        jButton8.setBackground(new java.awt.Color(0, 0, 0));
        jButton8.setFont(new java.awt.Font("Open Sans", 0, 96)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText(" ");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        game.getContentPane().add(jButton8);
        jButton8.setBounds(270, 10, 250, 212);

        jButton7.setBackground(new java.awt.Color(0, 0, 0));
        jButton7.setFont(new java.awt.Font("Open Sans", 0, 96)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText(" ");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        game.getContentPane().add(jButton7);
        jButton7.setBounds(10, 10, 250, 212);

        jButton9.setBackground(new java.awt.Color(0, 0, 0));
        jButton9.setFont(new java.awt.Font("Open Sans", 0, 96)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText(" ");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        game.getContentPane().add(jButton9);
        jButton9.setBounds(530, 10, 250, 212);

        jButton10.setBackground(new java.awt.Color(0, 0, 0));
        jButton10.setFont(new java.awt.Font("Open Sans", 0, 96)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText(" ");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        game.getContentPane().add(jButton10);
        jButton10.setBounds(10, 230, 250, 212);

        jButton13.setBackground(new java.awt.Color(0, 0, 0));
        jButton13.setFont(new java.awt.Font("Open Sans", 0, 96)); // NOI18N
        jButton13.setForeground(new java.awt.Color(255, 255, 255));
        jButton13.setText(" ");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        game.getContentPane().add(jButton13);
        jButton13.setBounds(10, 450, 250, 212);

        jLabel52.setBackground(new java.awt.Color(0, 0, 0));
        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tic_tac_toe/ezgif.com-gif-maker (1).gif"))); // NOI18N
        jLabel52.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        game.getContentPane().add(jLabel52);
        jLabel52.setBounds(0, 0, 1200, 680);

        stats.setSize(new java.awt.Dimension(1280, 720));
        stats.getContentPane().setLayout(null);

        jLabel21.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("0");
        stats.getContentPane().add(jLabel21);
        jLabel21.setBounds(1030, 150, 110, 50);

        jLabel26.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Total Wins :");
        stats.getContentPane().add(jLabel26);
        jLabel26.setBounds(820, 230, 200, 50);

        jLabel42.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("Total Draws :");
        stats.getContentPane().add(jLabel42);
        jLabel42.setBounds(790, 190, 220, 50);

        jLabel43.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("0");
        stats.getContentPane().add(jLabel43);
        jLabel43.setBounds(1030, 190, 120, 50);

        jLabel44.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("0");
        stats.getContentPane().add(jLabel44);
        jLabel44.setBounds(1030, 230, 120, 50);

        jLabel45.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("0");
        stats.getContentPane().add(jLabel45);
        jLabel45.setBounds(1030, 270, 130, 50);

        jLabel46.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("Total Loss :");
        stats.getContentPane().add(jLabel46);
        jLabel46.setBounds(830, 270, 187, 50);

        jButton20.setBackground(new java.awt.Color(0, 0, 0));
        jButton20.setFont(new java.awt.Font("Open Sans", 0, 24)); // NOI18N
        jButton20.setForeground(new java.awt.Color(255, 255, 255));
        jButton20.setText("Back");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        stats.getContentPane().add(jButton20);
        jButton20.setBounds(20, 620, 85, 41);

        jLabel22.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("iMPOSSIBLE Bot");
        stats.getContentPane().add(jLabel22);
        jLabel22.setBounds(270, 340, 271, 50);

        jLabel23.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Hard Bot");
        stats.getContentPane().add(jLabel23);
        jLabel23.setBounds(550, 340, 160, 50);

        jLabel24.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Medium Bot");
        stats.getContentPane().add(jLabel24);
        jLabel24.setBounds(720, 340, 206, 50);

        jLabel25.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Easy Bot");
        stats.getContentPane().add(jLabel25);
        jLabel25.setBounds(950, 340, 142, 50);

        jLabel37.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Won");
        stats.getContentPane().add(jLabel37);
        jLabel37.setBounds(140, 410, 100, 50);

        jLabel31.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Draw");
        stats.getContentPane().add(jLabel31);
        jLabel31.setBounds(150, 460, 89, 50);

        jLabel36.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("Lost");
        stats.getContentPane().add(jLabel36);
        jLabel36.setBounds(150, 510, 71, 50);

        jLabel27.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("0");
        stats.getContentPane().add(jLabel27);
        jLabel27.setBounds(340, 410, 95, 40);

        jLabel28.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("0");
        stats.getContentPane().add(jLabel28);
        jLabel28.setBounds(570, 410, 95, 40);

        jLabel29.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("0");
        stats.getContentPane().add(jLabel29);
        jLabel29.setBounds(770, 410, 95, 40);

        jLabel30.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("0");
        stats.getContentPane().add(jLabel30);
        jLabel30.setBounds(970, 410, 95, 40);

        jLabel32.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("0");
        stats.getContentPane().add(jLabel32);
        jLabel32.setBounds(340, 460, 95, 40);

        jLabel33.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("0");
        stats.getContentPane().add(jLabel33);
        jLabel33.setBounds(570, 460, 95, 40);

        jLabel34.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("0");
        stats.getContentPane().add(jLabel34);
        jLabel34.setBounds(770, 460, 95, 40);

        jLabel35.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("0");
        stats.getContentPane().add(jLabel35);
        jLabel35.setBounds(970, 460, 95, 40);

        jLabel38.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("0");
        stats.getContentPane().add(jLabel38);
        jLabel38.setBounds(340, 510, 95, 40);

        jLabel39.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("0");
        stats.getContentPane().add(jLabel39);
        jLabel39.setBounds(570, 510, 95, 40);

        jLabel40.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("0");
        stats.getContentPane().add(jLabel40);
        jLabel40.setBounds(770, 510, 95, 40);

        jLabel41.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("0");
        stats.getContentPane().add(jLabel41);
        jLabel41.setBounds(970, 510, 95, 40);

        jLabel17.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Name :");
        stats.getContentPane().add(jLabel17);
        jLabel17.setBounds(130, 160, 119, 50);

        jLabel18.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("     ");
        stats.getContentPane().add(jLabel18);
        jLabel18.setBounds(260, 160, 370, 50);

        jLabel15.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("UserID :");
        stats.getContentPane().add(jLabel15);
        jLabel15.setBounds(120, 220, 133, 50);

        jLabel16.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("     ");
        stats.getContentPane().add(jLabel16);
        jLabel16.setBounds(260, 220, 400, 50);

        jLabel19.setFont(new java.awt.Font("Open Sans", 0, 36)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Total Game :");
        stats.getContentPane().add(jLabel19);
        jLabel19.setBounds(800, 150, 211, 50);

        jLabel20.setFont(new java.awt.Font("Open Sans Extrabold", 0, 50)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Statistics");
        stats.getContentPane().add(jLabel20);
        jLabel20.setBounds(450, 40, 250, 60);

        jLabel58.setForeground(new java.awt.Color(255, 255, 255));
        jLabel58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tic_tac_toe/ezgif.com-gif-maker (1).gif"))); // NOI18N
        jLabel58.setText("jLabel58");
        stats.getContentPane().add(jLabel58);
        jLabel58.setBounds(0, 0, 1200, 680);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tic Tac Toe");
        setSize(new java.awt.Dimension(1280, 720));
        getContentPane().setLayout(null);

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setFont(new java.awt.Font("Open Sans", 0, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Exit ");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(10, 630, 79, 41);

        jButton3.setBackground(new java.awt.Color(0, 0, 0));
        jButton3.setFont(new java.awt.Font("Open Sans Extrabold", 0, 36)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Start");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(520, 450, 181, 111);

        jButton4.setBackground(new java.awt.Color(0, 0, 0));
        jButton4.setFont(new java.awt.Font("Open Sans", 0, 24)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Login ");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(1090, 10, 99, 41);

        jLabel3.setFont(new java.awt.Font("Copperplate Gothic Bold", 1, 100)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("TIC TAC TOE");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(256, 124, 760, 80);

        jButton5.setBackground(new java.awt.Color(0, 0, 0));
        jButton5.setFont(new java.awt.Font("Open Sans", 0, 24)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Logout");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5);
        jButton5.setBounds(970, 10, 111, 41);

        jLabel13.setFont(new java.awt.Font("Open Sans", 0, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Username :");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(10, 10, 126, 33);

        jLabel14.setFont(new java.awt.Font("Open Sans", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("            ");
        getContentPane().add(jLabel14);
        jLabel14.setBounds(140, 10, 72, 33);

        jButton19.setBackground(new java.awt.Color(0, 0, 0));
        jButton19.setFont(new java.awt.Font("Open Sans", 0, 24)); // NOI18N
        jButton19.setForeground(new java.awt.Color(255, 255, 255));
        jButton19.setText("Static");
        jButton19.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton19);
        jButton19.setBounds(1100, 630, 91, 41);

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        jRadioButton1.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton1.setText("X");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton1);
        jRadioButton1.setBounds(350, 291, 35, 35);

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        jRadioButton2.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton2.setText("O");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton2);
        jRadioButton2.setBounds(350, 326, 39, 35);

        buttonGroup2.add(jRadioButton3);
        jRadioButton3.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        jRadioButton3.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton3.setText("iMPOSSIBLE");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton3);
        jRadioButton3.setBounds(889, 291, 127, 35);

        buttonGroup2.add(jRadioButton4);
        jRadioButton4.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        jRadioButton4.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton4.setText("Hard");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton4);
        jRadioButton4.setBounds(889, 326, 67, 35);

        buttonGroup2.add(jRadioButton5);
        jRadioButton5.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        jRadioButton5.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton5.setText("Medium");
        jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton5);
        jRadioButton5.setBounds(889, 361, 95, 35);

        buttonGroup2.add(jRadioButton6);
        jRadioButton6.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        jRadioButton6.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton6.setText("Easy");
        jRadioButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton6);
        jRadioButton6.setBounds(889, 396, 63, 35);

        jLabel47.setFont(new java.awt.Font("Open Sans Semibold", 0, 24)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setText("Bots");
        getContentPane().add(jLabel47);
        jLabel47.setBounds(889, 242, 127, 42);

        jLabel48.setFont(new java.awt.Font("Open Sans Semibold", 0, 24)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setText("Chance");
        getContentPane().add(jLabel48);
        jLabel48.setBounds(332, 251, 85, 33);

        jLabel51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tic_tac_toe/main.gif"))); // NOI18N
        jLabel51.setText("jLabel51");
        getContentPane().add(jLabel51);
        jLabel51.setBounds(0, 0, 1200, 680);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private JFrame frame;
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        frame = new JFrame("Exit");
        if(JOptionPane.showConfirmDialog(frame,"Confirm if you wannt to exit","Tic Tac Toe",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION);
        {
            System.exit(0);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(flag)
        {
            game.setVisible(true);
            game.setLocationRelativeTo(null);
            this.setVisible(false);
            if(opponent == 'X')
                moveMaker(allBots(board,level));
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        PreparedStatement ps;
        ResultSet rs;
        String userid = jTextField2.getText();
        String password = String.valueOf(jPasswordField1.getPassword());
        String query = "SELECT * FROM `data` WHERE `Username` =? AND `Password` =?";
        try 
        {
            ps = MyConnection.getConnection().prepareStatement(query); 
            ps.setString(1, userid);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if(rs.next())
            {
                this.setVisible(true);
                login.setVisible(false);
                this.userid=userid;
                jLabel14.setText(userid);
                flag=true;
                hidden(flag);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Incorrect Username Or Password", "Login Failed", 2);
            }    
        }
        catch (SQLException ex)
        {
            Logger.getLogger(TTT.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        login.setVisible(true);
        login.setLocationRelativeTo(null);
        hidden(flag);
        this.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        flag =false;
        hidden(flag);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        if(checker()&&((jButton8.getText()).charAt(0)==' '))
        {
            jButton8.setText(Character.toString(player));
            board[0][1]=player;
            if(isMovesLeft(board)&&(evaluate(board)==0))
                moveMaker(allBots(board,level));
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if(checker()&&((jButton7.getText()).charAt(0)==' '))
        {
            jButton7.setText(Character.toString(player));
            board[0][0]=player;
            if(isMovesLeft(board)&&(evaluate(board)==0))
                moveMaker(allBots(board,level));
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        game.setVisible(false);
        space();
        PreparedStatement ps;
        Connection conn = null;
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdatabase?" + "user=root");
            switch(level)
            {
                case 1:
                    ps = conn.prepareStatement("UPDATE data SET EBW=EBW+"+Integer.toString(xCount)+",EBD=EBD+"+Integer.toString(draw)+",EBL=EBL+"+Integer.toString(oCount)+" WHERE Username='"+userid+"'");
                    ps.executeUpdate();
                    break;
                case 4:
                    ps = conn.prepareStatement("UPDATE data SET MBW=MBW+"+Integer.toString(xCount)+",MBD=MBD+"+Integer.toString(draw)+",MBL=MBL+"+Integer.toString(oCount)+" WHERE Username='"+userid+"'");
                    ps.executeUpdate();
                    break;
                case 9:
                    ps = conn.prepareStatement("UPDATE data SET HBW=HBW+"+Integer.toString(xCount)+",HBD=HBD+"+Integer.toString(draw)+",HBL=HBL+"+Integer.toString(oCount)+" WHERE Username='"+userid+"'");
                    ps.executeUpdate();
                    break;
                case 10:
                    ps = conn.prepareStatement("UPDATE data SET IBW=IBW+"+Integer.toString(xCount)+",IBD=IBD+"+Integer.toString(draw)+",IBL=IBL+"+Integer.toString(oCount)+" WHERE Username='"+userid+"'");
                    ps.executeUpdate();
                    break;
            }
        }
        catch (ClassNotFoundException | SQLException ex) 
        {
            Logger.getLogger(TTT.class.getName()).log(Level.SEVERE, null, ex);
        }
        xCount = 0;
        oCount = 0;
        draw =0;
        this.setVisible(true);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        if(checker()&&((jButton15.getText()).charAt(0)==' '))
        {
            jButton15.setText(Character.toString(player));
            board[2][2]=player;
            
            if((isMovesLeft(board)&&(evaluate(board)==0)))
                moveMaker(allBots(board,level));
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        if(checker()&&((jButton9.getText()).charAt(0)==' '))
        {
            jButton9.setText(Character.toString(player));
            board[0][2]=player;
            if(isMovesLeft(board)&&(evaluate(board)==0))
                moveMaker(allBots(board,level));
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        if(checker()&&((jButton10.getText()).charAt(0)==' '))
        {
            jButton10.setText(Character.toString(player));
            board[1][0]=player;
            if(isMovesLeft(board)&&(evaluate(board)==0))
                moveMaker(allBots(board,level));
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        if(checker()&&((jButton11.getText()).charAt(0)==' '))
        {
            jButton11.setText(Character.toString(player));
            board[1][1]=player;
            if(isMovesLeft(board)&&(evaluate(board)==0))
                moveMaker(allBots(board,level));
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        if(checker()&&((jButton12.getText()).charAt(0)==' '))
        {
            jButton12.setText(Character.toString(player));
            board[1][2]=player;
            if(isMovesLeft(board)&&(evaluate(board)==0))
                moveMaker(allBots(board,level));
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        if(checker()&&((jButton13.getText()).charAt(0)==' '))
        {
            jButton13.setText(Character.toString(player));
            board[2][0]=player;
            if(isMovesLeft(board)&&(evaluate(board)==0))
                moveMaker(allBots(board,level));
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
       if(checker()&&((jButton14.getText()).charAt(0)==' '))
        {
            jButton14.setText(Character.toString(player));
            board[2][1]=player;
            if(isMovesLeft(board)&&(evaluate(board)==0))
                moveMaker(allBots(board,level));
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        this.setVisible(true);
        login.setVisible(false);
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        login.setVisible(false);
        register.setLocationRelativeTo(null);
        register.setVisible(true);
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        level =9;
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jRadioButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton6ActionPerformed
        level=1;
    }//GEN-LAST:event_jRadioButton6ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        player = 'X';
        opponent = 'O';
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        player = 'O';
        opponent = 'X';
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        level=10;
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton5ActionPerformed
        level=4;
    }//GEN-LAST:event_jRadioButton5ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        this.setVisible(true);
        stats.setVisible(false);
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        this.setVisible(false);
        stats.setLocationRelativeTo(null);
        Connection conn = null;
        ResultSet rs;
        Statement stmt;
        String arr[][] = new String[3][4];
        int totalwon,totalloss,totaldraw,totalgame;
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdatabase?" + "user=root");
            String query = "select * from data WHERE Username = '" + userid +"'";
            stmt = conn.createStatement();
            rs =stmt.executeQuery(query);
            if (rs.next()) 
            { 
                jLabel16.setText(rs.getString(1));
                jLabel18.setText(rs.getString(2));
                arr[0][0]=rs.getString(4);
                arr[0][1]=rs.getString(5);
                arr[0][2]=rs.getString(6);
                arr[0][3]=rs.getString(7);

                arr[1][0]=rs.getString(8);
                arr[1][1]=rs.getString(9);
                arr[1][2]=rs.getString(10);
                arr[1][3]=rs.getString(11);

                arr[2][0]=rs.getString(12);
                arr[2][1]=rs.getString(13);
                arr[2][2]=rs.getString(14);
                arr[2][3]=rs.getString(15);

                jLabel27.setText(arr[0][0]);
                jLabel28.setText(arr[0][1]);
                jLabel29.setText(arr[0][2]);
                jLabel30.setText(arr[0][3]);

                jLabel32.setText(arr[1][0]);
                jLabel33.setText(arr[1][1]);
                jLabel34.setText(arr[1][2]);
                jLabel35.setText(arr[1][3]);

                jLabel38.setText(arr[2][0]);
                jLabel39.setText(arr[2][1]);
                jLabel40.setText(arr[2][2]);
                jLabel41.setText(arr[2][3]);

                totalwon=Integer.parseInt(arr[0][0])+Integer.parseInt(arr[0][1])+Integer.parseInt(arr[0][2])+Integer.parseInt(arr[0][3]);
                totaldraw=Integer.parseInt(arr[1][0])+Integer.parseInt(arr[1][1])+Integer.parseInt(arr[1][2])+Integer.parseInt(arr[1][3]);
                totalloss=Integer.parseInt(arr[2][0])+Integer.parseInt(arr[2][1])+Integer.parseInt(arr[2][2])+Integer.parseInt(arr[2][3]);

                totalgame=totalwon+totalloss+totaldraw;

                jLabel21.setText(Integer.toString(totalgame));
                jLabel45.setText(Integer.toString(totalwon));
                jLabel43.setText(Integer.toString(totaldraw));
                jLabel44.setText(Integer.toString(totalloss));

            }
        }
        catch (ClassNotFoundException | SQLException ex) 
        {
            Logger.getLogger(TTT.class.getName()).log(Level.SEVERE, null, ex);
        }
        stats.setVisible(true);   // TODO add your handling code here:
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        login.setVisible(true);
        register.setVisible(false);
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        String name= jTextField4.getText();
        String userid= jTextField3.getText();
        String password= String.valueOf(jPasswordField2.getPassword());
        String confirmpassword= String.valueOf(jPasswordField3.getPassword());
        PreparedStatement ps;
        Connection conn = null;
        if(verifyFields(name,userid,password,confirmpassword))
        {
            try 
            {
                if(!checkUsername(userid))
                {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdatabase?" + "user=root");
                        //ps = conn.prepareStatement("SELECT * from data where Username=?");
                        ps = conn.prepareStatement("INSERT INTO data VALUES (?,?,?,0,0,0,0,0,0,0,0,0,0,0,0)");
                        ps.setString(1,userid);
                        ps.setString(2,name);
                        ps.setString(3,password);
                        if(ps.executeUpdate()>0)
                        {
                            JOptionPane.showMessageDialog(null,"Successfully Registered");
                            jTextField4.setText("");
                            jTextField3.setText("");
                            jPasswordField2.setText("");
                            jPasswordField3.setText("");
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"Error! Check your Information");
                        }
                    }   
                } 
                catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(TTT.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jPasswordField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jPasswordField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField2ActionPerformed
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TTT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new TTT().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JFrame game;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JPasswordField jPasswordField3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JFrame login;
    private javax.swing.JFrame register;
    private javax.swing.JFrame stats;
    // End of variables declaration//GEN-END:variables
}