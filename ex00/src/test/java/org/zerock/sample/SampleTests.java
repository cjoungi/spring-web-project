package org.zerock.sample;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.sample.Restaurant;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
// 테스트 시 사용할 애플리케이션 컨텍스트를 설정하기 위해
// org.zerock.config.RootConfig.class 클래스를 사용하라고 지시합니다.
@ContextConfiguration(classes = {org.zerock.config.RootConfig.class})
@Log4j
public class SampleTests {
    @Setter(onMethod_ = { @Autowired })
    private Restaurant restaurant;

    @Test
    public void testExist(){
        // restaurant 변수가 null이 아니어야만 테스트가 성공하는 것을 의미합니다.
        assertNotNull(restaurant);

        log.info(restaurant);
        log.info("--------------------------");
        log.info(restaurant.getChef());
    }
}
