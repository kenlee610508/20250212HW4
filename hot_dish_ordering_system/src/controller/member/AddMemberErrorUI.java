package controller.member;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.porder.PorderMainUI;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddMemberErrorUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddMemberErrorUI frame = new AddMemberErrorUI();
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
    public AddMemberErrorUI() {
        setTitle("註冊失敗");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 350, 200);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 102, 102));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblMessage = new JLabel("註冊失敗，帳號已存在！");
        lblMessage.setFont(new Font("微軟正黑體", Font.BOLD, 18));
        lblMessage.setBounds(60, 50, 250, 30);
        contentPane.add(lblMessage);

        JButton btnClose = new JButton("返回註冊");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	AddMemberUI addMemberUI = new AddMemberUI();
            	addMemberUI.setVisible(true);
               
                dispose(); 
            }
        });
        btnClose.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        btnClose.setBounds(120, 100, 100, 30);
        contentPane.add(btnClose);
    }
}
