<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  
  <h1>Custom Login Page</h1>
  <h2><c:out value="${error}"/></h2>
  <h2><c:out value="${logout}"/></h2>
  
  <form method='post' action="/login">
  
  <div>
    <input type='text' name='username' value='admin'>
  </div>
  <div>
    <input type='password' name='password' value='admin'>
  </div>
  <div>
  <div>
    <input type='checkbox' name='remember-me'> Remember Me
  </div>

  <div>
    <input type='submit'>
  </div>
    <!--_csrf 변수는 스프링 시큐리티의 CSRF (Cross-Site Request Forgery) 보호 기능을 통해 자동으로 생성되는 토큰-->
    <!--서버에서는 이 토큰을 검증하여 요청이 유효한지 확인. 만약 CSRF 토큰이 누락되거나 잘못된 경우, 스프링 시큐리티는 요청을 거부하고 에러 페이지나 로그인 페이지로 리디렉션.-->
    <input type="hidden" name="${_csrf.parameterName}"
    value="${_csrf.token}" />
  
  </form>
  
</body>
</html>
