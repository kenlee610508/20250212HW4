package controller.member;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import model.Member;
import controller.porder.PorderMainUI;
import java.awt.Color;
import java.awt.Font;

public class LoginSuccessUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblWelcome;
    private Member member;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginSuccessUI frame = new LoginSuccessUI(new Member("測試用戶", "test", "1234", "0912345678", "台北市", "02-1234567", null));
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    
    public LoginSuccessUI(Member member) {
        this.member = member; 
        setTitle("登入成功");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 350, 200);
        contentPane = new JPanel();
        contentPane.setForeground(Color.WHITE);
        contentPane.setBackground(new Color(206, 0, 103));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblWelcome = new JLabel("登入成功");
        lblWelcome.setForeground(Color.WHITE);
        lblWelcome.setFont(new Font("微軟正黑體", Font.BOLD, 28));
        lblWelcome.setBounds(86, 44, 206, 40);
        contentPane.add(lblWelcome);

        if (member != null) {
            lblWelcome.setText(member.getName() + " 歡迎您!");
        } else {
            lblWelcome.setText("歡迎使用系統!");
        }

        JButton btnEnterSystem = new JButton("進入主頁");
        btnEnterSystem.setBounds(100, 120, 120, 30);
        btnEnterSystem.addActionListener(e -> {
            
            PorderMainUI porderMain = new PorderMainUI();
            porderMain.setVisible(true);
            dispose(); 
        });
        contentPane.add(btnEnterSystem);
    }
}
