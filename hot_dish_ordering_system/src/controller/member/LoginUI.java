package controller.member;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import model.Member;
import service.impl.MemberServiceImpl;
import util.Tool;

public class LoginUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField usernameField, captchaField;
    private JPasswordField passwordField;
    private JLabel lblTitle, captchaLabel, clockLabel;
    private MemberServiceImpl memberService = new MemberServiceImpl();
    private Timer colorTimer, clockTimer;
    private Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA};
    private int colorIndex = 0;
    private String generatedCaptcha;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LoginUI frame = new LoginUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LoginUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 420);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(128, 128, 255));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblTitle = new JLabel("熱炒店會員登入系統");
        lblTitle.setForeground(colors[colorIndex]);
        lblTitle.setFont(new Font("微軟正黑體", Font.BOLD, 24));
        lblTitle.setBounds(130, 20, 250, 30);
        contentPane.add(lblTitle);

        // **⭐ 設置標題顏色變化效果 ⭐**
        colorTimer = new Timer(800, e -> {
            colorIndex = (colorIndex + 1) % colors.length;
            lblTitle.setForeground(colors[colorIndex]);
        });
        colorTimer.start();

        // **⭐ 時鐘標籤 ⭐**
        clockLabel = new JLabel();
        clockLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 16));
        clockLabel.setBounds(150, 55, 250, 30);
        contentPane.add(clockLabel);
        updateClock(); // 初始化時間顯示

        // **⭐ 設置定時器，每秒更新時間 ⭐**
        clockTimer = new Timer(1000, e -> updateClock());
        clockTimer.start();

        JLabel lblUsername = new JLabel("帳號:");
        lblUsername.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        lblUsername.setBounds(100, 90, 80, 30);
        contentPane.add(lblUsername);

        usernameField = new JTextField();
        usernameField.setBounds(180, 90, 180, 30);
        contentPane.add(usernameField);
        usernameField.setColumns(10);

        JLabel lblPassword = new JLabel("密碼:");
        lblPassword.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        lblPassword.setBounds(100, 140, 80, 30);
        contentPane.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(180, 140, 180, 30);
        contentPane.add(passwordField);

        JLabel lblCaptcha = new JLabel("驗證碼:");
        lblCaptcha.setFont(new Font("微軟正黑體", Font.PLAIN, 18));
        lblCaptcha.setBounds(100, 190, 80, 30);
        contentPane.add(lblCaptcha);

        captchaLabel = new JLabel(generateCaptcha());
        captchaLabel.setFont(new Font("微軟正黑體", Font.BOLD, 22));
        captchaLabel.setBounds(180, 190, 100, 30);
        captchaLabel.setForeground(Color.BLACK);
        contentPane.add(captchaLabel);

        JButton btnRefreshCaptcha = new JButton("刷新");
        btnRefreshCaptcha.setBounds(290, 190, 70, 30);
        btnRefreshCaptcha.addActionListener(e -> captchaLabel.setText(generateCaptcha()));
        contentPane.add(btnRefreshCaptcha);

        captchaField = new JTextField();
        captchaField.setBounds(180, 230, 180, 30);
        contentPane.add(captchaField);

        JButton btnLogin = new JButton("登入");
        btnLogin.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        btnLogin.setBounds(100, 280, 120, 40);
        btnLogin.addActionListener(e -> loginAction());
        contentPane.add(btnLogin);

        JButton btnRegister = new JButton("註冊");
        btnRegister.setFont(new Font("微軟正黑體", Font.BOLD, 16));
        btnRegister.setBounds(240, 280, 120, 40);
        btnRegister.addActionListener(e -> {
            dispose();
            new AddMemberUI().setVisible(true);
        });
        contentPane.add(btnRegister);
    }

    /**
     * 產生隨機驗證碼（4 位數）
     */
    private String generateCaptcha() {
        Random rand = new Random();
        generatedCaptcha = String.valueOf(rand.nextInt(9000) + 1000);
        return generatedCaptcha;
    }

    /**
     * 更新時鐘標籤，每秒刷新時間
     */
    private void updateClock() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        clockLabel.setText(formatter.format(new Date()));
    }

    /**
     * 登入驗證
     */
    private void loginAction() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String inputCaptcha = captchaField.getText();

        if (!inputCaptcha.equals(generatedCaptcha)) {
            JOptionPane.showMessageDialog(this, "驗證碼錯誤！請重新輸入", "錯誤", JOptionPane.ERROR_MESSAGE);
            captchaLabel.setText(generateCaptcha());
            return;
        }

        Member member = memberService.login(username, password);
        if (member != null) {
            Tool.save(member, "member.txt");
            JOptionPane.showMessageDialog(this, "登入成功！", "成功", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new LoginSuccessUI(member).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "帳號或密碼錯誤！", "錯誤", JOptionPane.ERROR_MESSAGE);
        }
    }
}
