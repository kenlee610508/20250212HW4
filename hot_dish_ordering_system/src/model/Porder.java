package model;

import java.time.LocalDateTime;

public class Porder {
    private int id;
    private String memberName;
    private int dish1;
    private int dish2;
    private int dish3;
    private int dish4;
    private int dish5;
    private LocalDateTime orderDate;

    
    public Porder(String memberName, int dish1, int dish2, int dish3, int dish4, int dish5) {
        this.memberName = memberName;
        this.dish1 = dish1;
        this.dish2 = dish2;
        this.dish3 = dish3;
        this.dish4 = dish4;
        this.dish5 = dish5;
        this.orderDate = LocalDateTime.now();  
    }

    
    public Porder(int id, String memberName, int dish1, int dish2, int dish3, int dish4, int dish5, LocalDateTime orderDate) {
        this.id = id;
        this.memberName = memberName;
        this.dish1 = dish1;
        this.dish2 = dish2;
        this.dish3 = dish3;
        this.dish4 = dish4;
        this.dish5 = dish5;
        this.orderDate = orderDate;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public int getDish1() {
        return dish1;
    }

    public void setDish1(int dish1) {
        this.dish1 = dish1;
    }

    public int getDish2() {
        return dish2;
    }

    public void setDish2(int dish2) {
        this.dish2 = dish2;
    }

    public int getDish3() {
        return dish3;
    }

    public void setDish3(int dish3) {
        this.dish3 = dish3;
    }

    public int getDish4() {
        return dish4;
    }

    public void setDish4(int dish4) {
        this.dish4 = dish4;
    }

    public int getDish5() {
        return dish5;
    }

    public void setDish5(int dish5) {
        this.dish5 = dish5;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    
    @Override
    public String toString() {
        return "訂單編號：" + id + "\n"
                + "會員姓名：" + memberName + "\n"
                + "炒飯：" + dish1 + " 份\n"
                + "炒麵：" + dish2 + " 份\n"
                + "宮保雞丁：" + dish3 + " 份\n"
                + "青椒牛肉：" + dish4 + " 份\n"
                + "麻婆豆腐：" + dish5 + " 份\n"
                + "訂單時間：" + orderDate + "\n";
    }
}