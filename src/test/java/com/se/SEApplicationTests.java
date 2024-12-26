package com.se;

import com.se.board.domain.BoardCategory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SEApplicationTests {

    @Test
    void contextLoads() {
        BoardCategory q = BoardCategory.valueOf("QUESTION");
        System.out.println(q);
    }

}
