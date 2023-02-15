package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import static hello.jdbc.connection.ConnectionConst.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class MemberRepositoryV1Test {

    MemberRepositoryV1 repository;

    @BeforeEach
    void beforeEach() {
        //기본 DriverManager -  항상 새로운 커넥션을 획득한다.
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        repository = new MemberRepositoryV1(dataSource);
    }

}