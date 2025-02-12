package controller.member;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import service.impl.MemberServiceImpl;
import model.Member;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddMemberUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField nameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField phoneField;
    private JTextField addressField;
    private JTextField telField;
    private JTextField birthdayField;
    private MemberServiceImpl memberService = new MemberServiceImpl();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddMemberUI frame = new AddMemberUI();
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
    public AddMemberUI() {
        setTitle("會員註冊");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(255, 255, 128));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("會員註冊");
        lblTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
        lblTitle.setBounds(230, 20, 150, 30);
        contentPane.add(lblTitle);

        JLabel lblName = new JLabel("姓名:");
        lblName.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        lblName.setBounds(100, 70, 80, 30);
        contentPane.add(lblName);

        nameField = new JTextField();
        nameField.setBounds(180, 70, 220, 30);
        contentPane.add(nameField);

        JLabel lblUsername = new JLabel("帳號:");
        lblUsername.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        lblUsername.setBounds(100, 110, 80, 30);
        contentPane.add(lblUsername);

        usernameField = new JTextField();
        usernameField.setBounds(180, 110, 220, 30);
        contentPane.add(usernameField);

        JLabel lblPassword = new JLabel("密碼:");
        lblPassword.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        lblPassword.setBounds(100, 150, 80, 30);
        contentPane.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(180, 150, 220, 30);
        contentPane.add(passwordField);

        JLabel lblPhone = new JLabel("手機:");
        lblPhone.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        lblPhone.setBounds(100, 190, 80, 30);
        contentPane.add(lblPhone);

        phoneField = new JTextField();
        phoneField.setBounds(180, 190, 220, 30);
        contentPane.add(phoneField);

        JLabel lblPhoneHint = new JLabel("格式: 09xx-xxx-xxx");
        lblPhoneHint.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        lblPhoneHint.setForeground(Color.RED);
        lblPhoneHint.setBounds(410, 190, 150, 30);
        contentPane.add(lblPhoneHint);

        JLabel lblAddress = new JLabel("住址:");
        lblAddress.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        lblAddress.setBounds(100, 230, 80, 30);
        contentPane.add(lblAddress);

        addressField = new JTextField();
        addressField.setBounds(180, 230, 220, 30);
        contentPane.add(addressField);

        JLabel lblTel = new JLabel("電話:");
        lblTel.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        lblTel.setBounds(100, 270, 80, 30);
        contentPane.add(lblTel);

        telField = new JTextField();
        telField.setBounds(180, 270, 220, 30);
        contentPane.add(telField);

        JLabel lblTelHint = new JLabel("格式: 0x-xxxx-xxxx");
        lblTelHint.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        lblTelHint.setForeground(Color.RED);
        lblTelHint.setBounds(410, 270, 150, 30);
        contentPane.add(lblTelHint);

        JLabel lblBirthday = new JLabel("生日:");
        lblBirthday.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        lblBirthday.setBounds(100, 310, 80, 30);
        contentPane.add(lblBirthday);

        birthdayField = new JTextField();
        birthdayField.setBounds(180, 310, 220, 30);
        contentPane.add(birthdayField);

        JLabel lblBirthdayHint = new JLabel("格式: yyyy-MM-dd");
        lblBirthdayHint.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
        lblBirthdayHint.setForeground(Color.RED);
        lblBirthdayHint.setBounds(410, 310, 150, 30);
        contentPane.add(lblBirthdayHint);

        JButton btnRegister = new JButton("註冊");
        btnRegister.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        btnRegister.setBounds(200, 370, 120, 40);
        btnRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                String name = nameField.getText();
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String phone = phoneField.getText();
                String address = addressField.getText();
                String tel = telField.getText();
                String birthdayStr = birthdayField.getText();

                try {
                    LocalDate birthday = LocalDate.parse(birthdayStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    Member newMember = new Member(name, username, password, phone, address, tel, birthday);

                    if (memberService.registerMember(newMember)) {
                        new AddMemberSuccessUI().setVisible(true);
                        dispose();
                    } else {
                        new AddMemberErrorUI().setVisible(true);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "生日格式錯誤，請輸入 yyyy-MM-dd", "輸入錯誤", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        contentPane.add(btnRegister);
    }
}
