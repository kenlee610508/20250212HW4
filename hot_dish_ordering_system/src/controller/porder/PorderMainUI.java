package controller.porder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PorderMainUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                PorderMainUI frame = new PorderMainUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public PorderMainUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 255, 255));
        panel.setBounds(32, 10, 379, 60);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblTitle = new JLabel("訂單管理");
        lblTitle.setFont(new Font("新細明體", Font.BOLD, 24));
        lblTitle.setBounds(134, 20, 100, 30);
        panel.add(lblTitle);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(193, 193, 255));
        panel_1.setBounds(32, 80, 379, 173);
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        JButton btnAddOrder = new JButton("新增訂單");
        btnAddOrder.setFont(new Font("新細明體", Font.BOLD, 18));
        btnAddOrder.setBounds(110, 30, 145, 43);
        panel_1.add(btnAddOrder);
        btnAddOrder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AddPorderUI addOrder = new AddPorderUI();
                addOrder.setVisible(true);
                dispose();
            }
        });

        JButton btnManageOrder = new JButton("管理訂單");
        btnManageOrder.setFont(new Font("新細明體", Font.BOLD, 18));
        btnManageOrder.setBounds(110, 96, 145, 43);
        panel_1.add(btnManageOrder);
        btnManageOrder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PorderManagerUI manageOrder = new PorderManagerUI();
                manageOrder.setVisible(true);
                dispose();
            }
        });
    }
}