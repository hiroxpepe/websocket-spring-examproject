==============================
    
     websocket-spring-examproject

    by hiroxpepe
       hiroxpepe@gmail.com
    
==============================

-----------------------------------------------------------
* What's this?

This project shows how to work with 
  
  WebSocketServlet
  Spring IoC
  
and how to configure.

-----------------------------------------------------------
* How to run on the local?

You will need to get Apache Maven.
    http://maven.apache.org/

You will need to compile this example first.
  > cd {path to this README.txt directory!}
  > mvn compile

You need to start the two servers. use the terminal of two.

-----------------------------------------------------------
* first terminal, run for a WebSocket Server.

Move to launcher project directory.
  > cd websocket-launcher

To run the .war application by jetty plugin.
  > mvn jetty:run

websocket server be run on http://localhost:8081/

-----------------------------------------------------------
* second terminal, run for a Web Server.

Move to launcher project directory.
  > cd {path to this README.txt directory!}
  > cd webserver-launcher

To run the .war application by jetty plugin.
  > mvn jetty:run

You can access to http://localhost:8080/ on your web browser.

because the example of websocket, 
the site is not visited people actually..

To stop both the server  hit ctrl + c 

-----------------------------------------------------------

This project is hosted on GitHub.
  https://github.com/hiroxpepe/websocket-spring-examproject

-----------------------------------------------------------
enjoy! Spring! :)
