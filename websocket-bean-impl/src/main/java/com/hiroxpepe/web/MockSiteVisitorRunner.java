/* 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hiroxpepe.web;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

/**
 * a mock runner Class for emulation of the data update.
 * 
 * @author hiroxpepe
 */
public class MockSiteVisitorRunner {
    
    private static final Log LOG = LogFactory.getLog(MockSiteVisitorRunner.class);
    
    @Inject
    private final ApplicationContext context = null;
    
    @Inject
    private final Observable observable = null;
    
    private final Random random = new Random();
    
    private final int maxInterval = 5000;
    
    private Timer timer = new Timer();
    
    ///////////////////////////////////////////////////////////////////////////
    // bean properties
    
    public void setObserver(Observer observer) {
        observable.addObserver(observer);
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // public methods
    
    public void execute() {
        if (observable.countObservers() == 0) {
            // add mock one..
            observable.addObserver(new MockObserver());
        }
        
        // a simple mock message.
        observable.notifyObservers("mock");
        
        // use timer..
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                LOG.info("run");
                execute();
            }},
            random.nextInt(maxInterval)
        );
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // protected methods
    
    @Override
    protected void finalize() throws Throwable {
        timer.cancel();
        timer = null;
        super.finalize();
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // a mock private class
    
    private class MockObserver implements Observer {
        @Override
        public void update(Observable o, Object arg) {
        }
    }
}
