# Config_JAVA_WEB

## Config jsp 
```
spring.mvc.view.prefix: /WEB-INF/view/
spring.mvc.view.suffix: .jsp
```

## Nhúng Tomcat 
```
<dependency>
    <groupId>org.apache.tomcat.embed</groupId>
    <artifactId>tomcat-embed-jasper</artifactId>
</dependency>
```

## JSTL 
```
<dependency>
    <groupId>jakarta.servlet.jsp.jstl</groupId>
    <artifactId>jakarta.servlet.jsp.jstl-api</artifactId>
    <version>2.0.0</version>
</dependency>
<dependency>
    <groupId>org.glassfish.web</groupId>
    <artifactId>jakarta.servlet.jsp.jstl</artifactId>
    <version>2.0.0</version>
</dependency>
```

## Spring Validation 
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```


## Form - JSTL 
```
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
```


## Loop - JSTL 
```
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
```

## https://github.com/Yennth4/Java-5-BTVN : Đề thi thử và các đề BTVN 
