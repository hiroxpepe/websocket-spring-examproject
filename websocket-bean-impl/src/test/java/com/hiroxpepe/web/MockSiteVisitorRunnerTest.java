package com.hiroxpepe.web;

import javax.inject.Inject;
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
public class MockSiteVisitorRunnerTest {
    
    @Inject
    MockSiteVisitorRunner instance;

    @Test
    public void testExecute() throws Exception {
        System.out.println("execute");
        instance.execute();
        Thread.sleep(2000);
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
