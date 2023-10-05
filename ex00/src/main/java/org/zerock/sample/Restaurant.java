package org.zerock.sample;

import lombok.Data;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
public class Restaurant {
    // Chef 타입의 빈을 Spring 컨테이너에서 찾고
    // setChef() 메소드는 Chef 타입의 객체를 chef 필드에 자동으로 할당합니다.
    @Setter(onMethod_ = @Autowired)
    private Chef chef;
}
