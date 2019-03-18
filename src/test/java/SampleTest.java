
import com.xiongya.sprngboot.mybatisplus.Application;
import com.xiongya.sprngboot.mybatisplus.domain.User;
import com.xiongya.sprngboot.mybatisplus.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author xiongzhilong
 * @Date 2019-03-1808:42
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class)
public class SampleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect(){

        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }


}
