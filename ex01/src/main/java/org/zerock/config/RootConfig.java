package org.zerock.config;

import org.springframework.context.annotation.Configuration;

@Configuration
// 지정된 패키지("org.zerock.sample") 경로와 그 하위 경로에서
// @Component, @Service, @Repository, @Controller 등의 어노테이션이 붙은 클래스들을 찾아 빈(Bean)으로 등록
//@ComponentScan(basePackages = {"org.zerock.controller"})
public class RootConfig {



}
