import com.macie.service.UserInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Macie
 * @date 2021/7/5 -14:42
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class TestSpringBean {
    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void test1() {
        System.out.println(userInfoService.getClass());
        userInfoService.getPwdByUserName(null);
    }
}
