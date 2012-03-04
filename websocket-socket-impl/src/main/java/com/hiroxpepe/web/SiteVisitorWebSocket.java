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

import java.io.IOException;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.inject.Inject;

import org.eclipse.jetty.websocket.WebSocket;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author hiroxpepe
 */
@Component
public class SiteVisitorWebSocket implements WebSocket.OnTextMessage {
    
    private final String SITE_VISITOR_BEAN_ID = "siteVisitor";
    
    @Inject
    private final ApplicationContext context = null;
    
    private final Set<SiteVisitorWebSocket> webSockets = new CopyOnWriteArraySet<SiteVisitorWebSocket>();

    private final Random random = new Random();
    
    private final int maxInterval = 10000;
    
    private Timer timer = new Timer();
    
    private WebSocket.Connection connection;

    ///////////////////////////////////////////////////////////////////////////
    // public methods
    
    @Override
    public void onOpen(WebSocket.Connection connection) {
        this.connection = connection;
        webSockets.add(this);
        doOpen();
    }

    @Override
    public void onMessage(String message) {
    }

    @Override
    public void onClose(int closeCode, String message) {
        webSockets.remove(this);
        timer.cancel();
        timer = null;
    }

    ///////////////////////////////////////////////////////////////////////////
    // private methods
    
    private void doOpen() {
        try {
            // create a new object. 
            SiteVisitor visitor = context.getBean(
                SITE_VISITOR_BEAN_ID, SiteVisitor.class
            );
            
            // set to the connection' message.
            connection.sendMessage(
                ObjectUtil.toJsonString(visitor)
            );

            // use timer..
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    doOpen();
                }},
                random.nextInt(maxInterval)
            );
        }
        catch(IOException e) {
            // TODO: do something..
        }
    }
}
