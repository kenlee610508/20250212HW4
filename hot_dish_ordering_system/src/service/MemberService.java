package service;

import model.Member;

public interface MemberService {
    boolean registerMember(Member member);
    Member login(String username, String password);
}