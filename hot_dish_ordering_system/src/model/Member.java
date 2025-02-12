package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Member implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String username;
    private String password;
    private String phone;
    private String address;
    private String tel;
    private LocalDate birthday;

    public Member(String name, String username, String password, String phone, String address, String tel, LocalDate birthday) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.tel = tel;
        this.birthday = birthday;
    }

    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getTel() { return tel; }
    public void setTel(String tel) { this.tel = tel; }

    public LocalDate getBirthday() { return birthday; }
    public void setBirthday(LocalDate birthday) { this.birthday = birthday; }
}
