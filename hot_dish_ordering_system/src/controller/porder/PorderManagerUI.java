package controller.porder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import model.Porder;
import service.impl.PorderServiceImpl;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class PorderManagerUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextArea output;
    private JTextField idField;
    private JSpinner[] dishSpinners = new JSpinner[5]; 
    private static PorderServiceImpl porderService = new PorderServiceImpl();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                PorderManagerUI frame = new PorderManagerUI();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public PorderManagerUI() {
        setTitle("訂單管理");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 550, 620);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(200, 200, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnView = new JButton("查看所有訂單");
        btnView.setBounds(180, 20, 180, 30);
        contentPane.add(btnView);
        btnView.addActionListener(e -> displayOrders());

        output = new JTextArea();
        output.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(output);
        scrollPane.setBounds(50, 70, 450, 250);
        contentPane.add(scrollPane);

        JLabel lblID = new JLabel("輸入訂單 ID:");
        lblID.setBounds(50, 330, 100, 25);
        contentPane.add(lblID);

        idField = new JTextField();
        idField.setBounds(150, 330, 100, 25);
        contentPane.add(idField);

        // 建立滾動區域
        JScrollPane inputScrollPane = new JScrollPane();
        inputScrollPane.setBounds(50, 360, 450, 160);
        contentPane.add(inputScrollPane);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2, 10, 10));
        inputScrollPane.setViewportView(inputPanel);

        JLabel[] dishLabels = {new JLabel("炒飯"), new JLabel("炒麵"), new JLabel("宮保雞丁"), new JLabel("青椒牛肉"), new JLabel("麻婆豆腐")};

        for (int i = 0; i < dishLabels.length; i++) {
            inputPanel.add(dishLabels[i]);

            dishSpinners[i] = new JSpinner(new SpinnerNumberModel(0, 0, 20, 1)); 
            inputPanel.add(dishSpinners[i]);
        }

        JButton btnUpdate = new JButton("修改訂單");
        btnUpdate.setBounds(180, 530, 120, 30);
        contentPane.add(btnUpdate);
        btnUpdate.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                int[] dishes = new int[5];
                for (int i = 0; i < 5; i++) {
                    dishes[i] = (int) dishSpinners[i].getValue();
                }
                Porder porder = new Porder(id, "", dishes[0], dishes[1], dishes[2], dishes[3], dishes[4], null);
                if (porderService.updatePorder(id, porder)) {
                    JOptionPane.showMessageDialog(null, "訂單修改成功！");
                    displayOrders();
                } else {
                    JOptionPane.showMessageDialog(null, "修改失敗！");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "請輸入有效的訂單 ID！");
            }
        });

        JButton btnDelete = new JButton("刪除訂單");
        btnDelete.setBounds(320, 530, 120, 30);
        contentPane.add(btnDelete);
        btnDelete.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                if (porderService.deletePorder(id)) {
                    JOptionPane.showMessageDialog(null, "訂單刪除成功！");
                    displayOrders();
                } else {
                    JOptionPane.showMessageDialog(null, "刪除失敗！");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "請輸入有效的訂單 ID！");
            }
        });
    }

    private void displayOrders() {
        List<Porder> orders = porderService.getAllPorders();
        if (orders.isEmpty()) {
            output.setText("目前沒有訂單！");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Porder order : orders) {
            sb.append("訂單 ID: ").append(order.getId()).append("\n")
              .append("會員名稱: ").append(order.getMemberName()).append("\n")
              .append("炒飯: ").append(order.getDish1()).append(" 份\n")
              .append("炒麵: ").append(order.getDish2()).append(" 份\n")
              .append("宮保雞丁: ").append(order.getDish3()).append(" 份\n")
              .append("青椒牛肉: ").append(order.getDish4()).append(" 份\n")
              .append("麻婆豆腐: ").append(order.getDish5()).append(" 份\n")
              .append("訂單時間: ").append(order.getOrderDate()).append("\n") 
              .append("===================================\n");
        }
        output.setText(sb.toString());
    }
}
