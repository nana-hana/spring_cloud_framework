package com.kuukokawaii.security.provider.config;


import com.kuukokawaii.security.provider.SecurityProviderApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author kuukokawaii
 * @Date 08/19/2020
 * @Description WebSecurityConfig test
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SecurityProviderApplication.class})
public class BCryptTest {

    @Test
    public void test() {
        String password = BCrypt.hashpw("qwe123", BCrypt.gensalt());
        System.out.println(password);
    }

}
