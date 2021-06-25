package com.data.basics.design.observer;


import com.data.StructureApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test:
 *
 * @author sunchen
 * @date 2020/7/23 8:06 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StructureApplication.class)
public class ObTest {
    @Autowired
    UserService userService;
    @Test
    public void test() {
        userService.insert(2, "sun");
        Model query = userService.query(1);
        userService.update(2,"zhang");
        userService.delete(2);
    }
}
