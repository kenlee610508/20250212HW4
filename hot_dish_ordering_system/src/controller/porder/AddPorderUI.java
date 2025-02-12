package controller.porder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import model.Member;
import model.Porder;
import service.impl.PorderServiceImpl;
import util.Tool;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPorderUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JSpinner[] spinners = new JSpinner[5];

    private static Member member = (Member) Tool.read("member.txt");
    private static PorderServiceImpl porderService = new PorderServiceImpl();

    // 設定餐點價格
    private final int[] prices = {100, 120, 150, 180, 130}; // 炒飯 100, 炒麵 120, 宮保雞丁 150, 青椒牛肉 180, 麻婆豆腐 130
    private final String[] dishNames = {"炒飯", "炒麵", "宮保雞丁", "青椒牛肉", "麻婆豆腐"};

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AddPorderUI frame = new AddPorderUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public AddPorderUI() {
        setTitle("新增訂單");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 439, 378);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(128, 255, 128));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 208, 232));
        panel.setBounds(53, 10, 327, 50);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblTitle = new JLabel("新增訂單");
        lblTitle.setFont(new Font("新細明體", Font.BOLD, 16));
        lblTitle.setBounds(130, 10, 80, 30);
        panel.add(lblTitle);

        JButton btnBack = new JButton("返回主頁");
        btnBack.setBounds(220, 13, 100, 25);
        panel.add(btnBack);
        btnBack.addActionListener(e -> {
            Tool.gotoPorderMain();
            dispose();
        });

        // 滾動面板
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(53, 70, 327, 230);
        contentPane.add(scrollPane);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 208, 232));
        panel_1.setLayout(null);
        scrollPane.setViewportView(panel_1);

        JLabel[] labels = new JLabel[dishNames.length];

        int y = 20;
        for (int i = 0; i < dishNames.length; i++) {
            labels[i] = new JLabel(dishNames[i] + "（$" + prices[i] + "）");
            labels[i].setBounds(30, y, 120, 25);
            panel_1.add(labels[i]);

            spinners[i] = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1));
            spinners[i].setBounds(160, y, 80, 25);
            panel_1.add(spinners[i]);

            y += 35;
        }

        JButton btnSubmit = new JButton("確定下單");
        btnSubmit.setBounds(91, y + 20, 150, 30);
        panel_1.add(btnSubmit);
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (member == null || member.getName() == null) {
                    JOptionPane.showMessageDialog(null, "請先登入會員！", "錯誤", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    int[] order = new int[5];
                    int totalAmount = 0;
                    for (int i = 0; i < 5; i++) {
                        order[i] = (int) spinners[i].getValue();
                        totalAmount += order[i] * prices[i];
                    }

                    if (totalAmount == 0) {
                        JOptionPane.showMessageDialog(null, "請選擇至少一項餐點！", "錯誤", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    Porder porder = new Porder(member.getName(), order[0], order[1], order[2], order[3], order[4]);

                    if (porderService.addPorder(porder)) {
                        showOrderSummary(order, totalAmount);
                    } else {
                        JOptionPane.showMessageDialog(null, "訂單提交失敗，請重試！", "錯誤", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "發生錯誤，請重新操作！", "錯誤", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // 設置滾動面板內部大小
        panel_1.setPreferredSize(new Dimension(300, y + 120));
    }

    /**
     * 顯示訂單明細與合計金額
     */
    private void showOrderSummary(int[] order, int totalAmount) {
        StringBuilder details = new StringBuilder("訂單明細：\n");
        for (int i = 0; i < order.length; i++) {
            if (order[i] > 0) {
                details.append(dishNames[i]).append("：").append(order[i]).append(" 份（$")
                        .append(order[i] * prices[i]).append("）\n");
            }
        }
        details.append("\n總金額：$").append(totalAmount);

        JOptionPane.showMessageDialog(null, details.toString(), "訂單確認", JOptionPane.INFORMATION_MESSAGE);

        Tool.gotoPorderMain();
        dispose();
    }
}
