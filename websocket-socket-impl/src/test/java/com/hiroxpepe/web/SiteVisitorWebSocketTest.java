package com.hiroxpepe.web;

import java.util.Observable;
import javax.inject.Inject;
import org.eclipse.jetty.websocket.WebSocket.Connection;
import org.eclipse.jetty.websocket.WebSocketConnectionD00;
import org.eclipse.jetty.websocket.WebSocketServletConnectionD00;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author hiroxpepe
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("test-app-config.xml")
public class SiteVisitorWebSocketTest {

    @Inject
    SiteVisitorWebSocket instance;

    /**
     * Test of onOpen method, of class SiteVisitorWebSocket.
     */
//    @Test
//    public void testOnOpen() {
//        System.out.println("onOpen");
//        instance.onOpen(null);
//    }

    /**
     * Test of onMessage method, of class SiteVisitorWebSocket.
     */
    @Test
    public void testOnMessage() {
        System.out.println("onMessage");
        String message = "";
        instance.onMessage(message);
    }

    /**
     * Test of onClose method, of class SiteVisitorWebSocket.
     */
    @Test
    public void testOnClose() {
        System.out.println("onClose");
        int closeCode = 0;
        String message = "";
        instance.onClose(closeCode, message);
    }

    /**
     * Test of update method, of class SiteVisitorWebSocket.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Observable o = null;
        Object arg = null;
        instance.update(o, arg);
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
}
