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

$(function() {
    
    var visitorSocket = {
        socket: null,
        init: function() {
            console.log("init");
            
            // create a new websocket object.
            // if necessary, change it to your server address.
            this.socket = new WebSocket("ws://127.0.0.1:8081/");
            this.socket.onopen = this.open;
            this.socket.onclose = this.close;
            this.socket.onmessage = this.message;
            this.socket.onerror = this.error;
        },
        open: function() {
            console.log("open");
        },
        close: function() {
            console.log("close");
        },
        message: function(message) {
            console.log("message");
            
            // been pushed a message from WebSocket server.
            var visitor = eval("(" + message.data + ")");
            $("#visitor-feed").prepend(
                $("<li />").html(
                    "<span class='feeling'>" + visitor.feeling + "</span>" + 
                    "<b>" + visitor.person + "</b>" + " from " + "<b>" + visitor.country + "</b>"
                )
            )
        },
        error: function(e) {
            console.log("error");
            console.log(e);
        }
    };

    // run a websocket object.
    visitorSocket.init();
});