package dao;

import model.Member;

public interface MemberDao {
    boolean addMember(Member member);  
    boolean isUsernameExists(String username);
    Member getMemberByUsername(String username);
}
