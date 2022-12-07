package hello.jdbc.repository;

import hello.jdbc.connection.DBConnectionUtil;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@Slf4j
public class MemberRepositoryVO {

    public Member save(Member member) throws SQLException {
        String sql = "inset into member(member_id, money) values(?,?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,member.getMemberId());
            pstmt.setInt(2,member.getMoney());
            return member;
        } catch (SQLException e) {
            log.error("db error",e);
            throw e;
        }finally {
            pstmt.close(); //만약 여기서 exception이 생기면 ? 아래 코드는 호출되지 않는다.
            con.close();
        }
    }

    private Connection getConnection() {
        return DBConnectionUtil.getConnection();
    }
}
