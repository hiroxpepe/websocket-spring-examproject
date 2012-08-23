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

///////////////////////////////////////////////////////////////////////////////
/**
* a class of the application.
* used to wrap the websocket object.
*/
exmp.websocket.VisitorSocket = {
    
    ///////////////////////////////////////////////////////////////////////////
    // fields
    
    _socket: null,
    
    ///////////////////////////////////////////////////////////////////////////
    // public methods
    
    // initialize the object.
    init: function() {
        console.log("init");

        // create a new websocket object.
        // if necessary, change it to your server address.
        this._socket = new WebSocket("ws://127.0.0.1:8081/");
        
        // set the callback handler methods.
        this._socket.onopen = this._onopen;
        this._socket.onclose = this._onclose;
        this._socket.onmessage = this._onmessage;
        this._socket.onerror = this._onerror;
    },
    
    // send a message using the WebSocket.
    send: function(value) {
        console.log("send");

        // a value of string is like that,
        // {"person":"John Lennon","feeling":":)","country":"UK"}
        this._socket.send(value);
    },
    
    ///////////////////////////////////////////////////////////////////////////
    // callback methods
    
    // called when the WebSocket onopen. 
    _onopen: function() {
        console.log("open");
    },
    
    // called when the WebSocket onclose.
    _onclose: function() {
        console.log("close");
    },
    
    // called when the WebSocket onmessage.
    _onmessage: function(message) {
        console.log("message");

        // be pushed a message from WebSocket server.
        var visitor = eval("(" + message.data + ")");
        $("ul.feeds").prepend(
            $("<li />").html(
                "<a class='feed-feeling'>" + visitor.feeling + 
                "</a>" + "<b>" + visitor.person + "</b>" + " from " +
                "<b>" + visitor.country + "</b>"
            )
        )
    },
    
    // called when the WebSocket onerror.
    _onerror: function(e) {
        console.log("error");
        console.log(e);
    }
}