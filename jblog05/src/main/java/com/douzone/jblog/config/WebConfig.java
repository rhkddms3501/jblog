package com.douzone.jblog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.douzone.jblog.config.web.FileuploadConfig;
import com.douzone.jblog.config.web.MvcConfig;
import com.douzone.jblog.config.web.SecurityConfig;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.douzone.jblog.controller"})
@Import({MvcConfig.class, SecurityConfig.class, FileuploadConfig.class})
public class WebConfig {

}


/**
*
* @Configuration
* 		- 설정 파일 or Bean을 등록하기 위한 Annotation
* 		- @Configuration을 단 클래스는 빈 설정을 담당하는 클래스
* 		- Bean을 등록할 때 싱글톤(singleton)이 되도록 보장
* 		- 스프링 컨테이너에서 Bean을 관리 할 수 있게 됨
* 
* @Import
* 		- @Configuration으로 설정한 설정 파일을 두개 이상 사용하는 경우 사용
* 		- 두개 이상의 설정 파일을 연결하며 @Import를 사용하면 설정 파일간의 계층을 만들수 있음
* 		- ex) DB빈 설정과 DB를 활용하는 빈(Mybatis 등)을 같이 써야 할 경우 사용
* 			- 이런 경우 빈 선언 시 실행 순서도 보장 죄기 때문 빈 선언 오류도 막을 수 있음
* 
* @ComponentScan
* 		- @Component 및 streotype( @Controller, @Service, @Repository ) 어노테이션이 부여된 Class들을 자동으로 Scan하여 Bean으로 등록 해주는 역할
* 		- 프로젝트 내부에 있는 모든 @Component를 ApplicationContext(Beanfactory를 상속한 인터페이스, 스프링 컨테이너)로 수집하는 역할
* 		- 명시한곳 하위 패키지들에 대해서 탐색
* 			- @Component는 Spring bean으로 등록하겠다고 표시 하는 역할 
* 				- Spring은 @Component가 등록된 클래스를 자동으로 탐색하여 인스턴스화 하고 해당 클래스를 필요로 하는 곳에 의존성 주입
* 			- streotype( @Controller, @Service, @Repository )
*				- @Component를 상속 받고 있으며 비즈니스 레이어를 구분하기 위해 사용
*
* @EnableAspectJAutoProxy
* 		- @EnableAspectJAutoProxy를 사용하면 AOP를 사용할 수 있게 됨
* 		- 스프링이 자동으로 개발자의 메소드를 호출 하기 전에 가로챌 수 있게 하는 옵션
* 		- 스프링의 AOP는 기본적으로 프록시 방식으로 동작
* 		- AOP란?
* 			- Aspect Object Programming (관점 지향 프로그래밍)
* 			- 기능을 핵심 비즈니스 기능과 공통 기능으로 구분하고, 
* 			  핵심 비즈니스 기능에서 따로 빼놓은 공통 기능을 불러와서 적용하는 방법
*
*/