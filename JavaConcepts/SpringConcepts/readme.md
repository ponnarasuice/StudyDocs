## Spring concept

### 

### how spring singleton handles multiple requests?


## Web development in Spring
First understand the the how web server/servlet container loads servlets.
#### web server And servlet loading
Servlet containers/servers like Apache Tomcat, Jetty, and Undertow are responsible for managing servlets. 
    - When the server starts, it reads the configuration files or scans for annotations to detect servlets. The container scans the classpath of the web application for classes that contain servlet annotations (e.g., @WebServlet, @WebFilter, @WebListener)
    - servlet contains the get/post methods to handle incoming requests.
    ```
@WebServlet("/hello") --- Earlier servlet is configured in web.xml
public class HelloServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().println("<h1>Hello, World!</h1>");
    }
}
    ```
So, webservers find and executes classes extends httpservlet. This is base class.
Packaged to JAR or WAR file which is extracted and served in servlet container. Refer below structure.
```
your-web-app/
    WEB-INF/
        web.xml (optional) --- not needed in servlet 3+
        classes/
            com/
                example/
                    HelloServlet.class
        lib/
    index.html

```

#### spring web servlet
We know httpservlet is base class. 
In spring, DispatcherServlet(internally extends the httpservlet) is base servlet. It is front controller which receives incoming http requests and sends the response(rest/html) back to client.
    - Spring Boot application, the DispatcherServlet is automatically configured.  if you include the spring-boot-starter-web dependency, Spring Boot will automatically configure a web application context, register a DispatcherServlet
    - in a traditional Spring MVC application, you might configure it in web.xml
```
web.xml
<web-app>
    <servlet>
        <servlet-name>dispatcher</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>dispatcher</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
</web-app>
```