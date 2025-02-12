package service.impl;

import dao.MemberDao;
import dao.impl.MemberDaoImpl;
import model.Member;
import service.MemberService;

public class MemberServiceImpl implements MemberService {
    private final MemberDao memberDao = new MemberDaoImpl();

    @Override
    public boolean registerMember(Member member) {
        if (member == null) {
            System.out.println("❌ 錯誤：會員資料不能為 null！");
            return false;
        }
        if (member.getUsername() == null || member.getUsername().isEmpty()) {
            System.out.println("❌ 錯誤：帳號不能為空！");
            return false;
        }
        if (member.getPassword() == null || member.getPassword().isEmpty()) {
            System.out.println("❌ 錯誤：密碼不能為空！");
            return false;
        }

        
        if (memberDao == null) {
            System.out.println("❌ 錯誤：會員 DAO 尚未初始化！");
            return false;
        }

        
        if (memberDao.isUsernameExists(member.getUsername())) {
            System.out.println("❌ 錯誤：帳號已存在，請使用其他帳號！");
            return false;
        }

       
        boolean success = memberDao.addMember(member);
        if (success) {
            System.out.println("✅ 註冊成功！");
        } else {
            System.out.println("❌ 錯誤：註冊失敗，請稍後再試！");
        }
        return success;
    }

    @Override
    public Member login(String username, String password) {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            System.out.println("❌ 錯誤：帳號或密碼不能為空！");
            return null;
        }

        Member member = memberDao.getMemberByUsername(username);
        if (member == null) {
            System.out.println("❌ 錯誤：用戶不存在！");
            return null;
        }

        if (member.getPassword().equals(password)) {
            System.out.println("✅ 登入成功！");
            return member;
        } else {
            System.out.println("❌ 錯誤：密碼錯誤！");
            return null;
        }
    }
}