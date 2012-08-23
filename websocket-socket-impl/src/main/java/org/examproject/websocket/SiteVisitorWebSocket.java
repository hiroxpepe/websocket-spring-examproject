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

package org.examproject.websocket;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jetty.websocket.WebSocket;
import org.springframework.context.ApplicationContext;

/**
 * @author hiroxpepe
 */
public class SiteVisitorWebSocket implements WebSocket.OnTextMessage, Observer {
    
    private static final Log LOG = LogFactory.getLog(SiteVisitorWebSocket.class);
    
    private final String SITE_VISITOR_BEAN_ID = "siteVisitor";
    
    @Inject
    private final ApplicationContext context = null;
    
    @Inject
    private final CopyOnWriteArraySet<SiteVisitorWebSocket> webSockets = null;
    
    @Inject
    private final Observable observable = null;
    
    private WebSocket.Connection connection;

    private boolean isConnect = false;
        
    ///////////////////////////////////////////////////////////////////////////
    // public methods
    
    @Override
    public void onOpen(WebSocket.Connection connection) {
        LOG.info("onOpen");
        
        this.connection = connection;

        // set myself, to the global websockets object.
        webSockets.add(this);

        // set myself, to the global observable object.
        observable.addObserver(this);

        isConnect = true;
    }

    @Override
    public void onMessage(String message) {
        LOG.info("onMessage");

        // push a message to websocket client.
        // in this case simply, message is send to all of websocket objects.
        push(message);
    }

    @Override
    public void onClose(int closeCode, String message) {
        LOG.info("onClose");
        
        // remove myself, to the global websockets object.
        webSockets.remove(this);
        
        // remove myself, to the global observable object.
        observable.deleteObserver(this);
    }

    // this method be called from observable object.
    @Override
    public void update(Observable o, Object arg) {
        LOG.info("update");
        if (isConnect) {
            
            // get a domain object.
            // TODO: I will need to change to service object..
            SiteVisitor visitor = context.getBean(
                SITE_VISITOR_BEAN_ID, SiteVisitor.class
            );
            
            // transform a domain object to a json string.
            String message = ObjectUtil.toJsonString(visitor);
            
            // push a message to websocket client.
            push(message);
        }
    }
    
    ///////////////////////////////////////////////////////////////////////////
    // private methods
    
    // to notify the information to all of websocket objects.
    private void push(String message) {
        try {
            // set a message to all of connections.
            for (SiteVisitorWebSocket webSocket : webSockets) {
                webSocket.connection.sendMessage(
                    message
                );
                LOG.trace(message);
            }
        }
        catch(IOException ioe) {
            LOG.error("error.");
            throw new RuntimeException(ioe);
        }
    }
    
}