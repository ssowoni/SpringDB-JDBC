package hello.jdbc.repository;

import hello.jdbc.connection.DBConnectionUtil;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;


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
            pstmt.executeUpdate(); //숫자를 반환하는데, 만약 row 10개가 영향을 받는다면 10이라는 숫자 반환
            return member;
        } catch (SQLException e) {
            log.error("db error",e);
            throw e;
        }finally {
            close(con, pstmt, null);
        }
    }

    private void close(Connection con, Statement stmt, ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                log.error("con error",e);
            }
        }
        if(stmt !=null){
            try {
                stmt.close(); //만약 여기서 exception이 생기면 ? 아래 코드는 호출되지 않는다.
            } catch (SQLException e) {
                log.error("stmt error",e);

            }
        }
        if(con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                log.error("con error",e);
            }
        }
    }
    private Connection getConnection() {
        return DBConnectionUtil.getConnection();
    }
}
