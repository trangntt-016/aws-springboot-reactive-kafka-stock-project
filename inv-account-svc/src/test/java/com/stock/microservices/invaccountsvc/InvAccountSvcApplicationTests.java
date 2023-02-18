package com.stock.microservices.invaccountsvc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InvAccountSvcApplicationTests {

    @Test
    void contextLoads() {

    }

    @Test
    void test(){
        int a = 1;
        Assertions.assertEquals(a, 1);
    }

}
