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
 * @author hiroxpepe
 */

// the global object for namespace.
var exam = {};

///////////////////////////////////////////////////////////////////////////////
/**
* a class of the application.
* used to wrap the websocket object.
*/
exam.VisitorSocket = {
    _socket: null,
    init: function() {
        console.log("init");

        // create a new websocket object.
        // if necessary, change it to your server address.
        this._socket = new WebSocket("ws://127.0.0.1:8081/");
        this._socket.onopen = this.open;
        this._socket.onclose = this.close;
        this._socket.onmessage = this.message;
        this._socket.onerror = this.error;
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
        $("ul.feeds").prepend(
            $("<li />").html(
                "<a class='feed_feeling'>" + visitor.feeling + 
                "</a>" + "<b>" + visitor.person + "</b>" + " from " +
                "<b>" + visitor.country + "</b>"
            )
        )
    },
    send: function(value) {
        console.log("send");

        // a value of string is like that,
        // {"person":"John Lennon","feeling":":)","country":"UK"}
        this._socket.send(value);
    },
    error: function(e) {
        console.log("error");
        console.log(e);
    }
}

///////////////////////////////////////////////////////////////////////////////
/**
* the entry class of the application.
*/
exam.Application = function(sender) {
    // the jQuery's way.
    $(document).ready(function() {
        sender.init();
    })
}

///////////////////////////////////////////////////////////////////////////////
/**
* the controller class of the application.
*/
exam.Controller = window; {

    ///////////////////////////////////////////////////////////////////////////
    // public methods

    // init the controller class.
    exam.Controller.init = function() {
        exam.Controller._initComponent();
    }

    ///////////////////////////////////////////////////////////////////////////
    // event handler methods

    // an entry pad window be opened.
    exam.Controller._doOpenCommandOnClick = function() {
        $("#open_command").click(function() {
            $("#pad_wrapper").show().css({
                top: -50,
                left: -50
            });
            $("#fine_entry_feeling").click();
        });
    }

    // an entry pad window be closed.
    exam.Controller._doCloseCommandOnClick = function() {
        $("#close_command").click(function() {
            $("#pad_wrapper").hide(); 
        });
    }

    // a message be sent to server by the websocket object.
    exam.Controller._doSendCommandOnClick = function() {
        $("#send_command").click(function() {
            var first_name = exam.Util.escapeHtml($("#first_name_text").val());
            var last_name = exam.Util.escapeHtml($("#last_name_text").val());
            var feeling = $("#feel_param_hidden").val();
            var country = exam.CountryValueFactory.create();
            var param = '{"person":"' + first_name + " " + last_name + 
                '","feeling":"' + feeling + '","country":"' + country + '"}';

            // send a message using the websocket object.
            exam.VisitorSocket.send(
                param
            );
        });
    }

    // a feeling icon is clicked, 
    // the value of the param of feeling is set to hidden area.
    exam.Controller._doFineEntryFeelingOnClick = function() {
        $("#fine_entry_feeling").click(function() {
            $("a.entry_feeling").css({"background-color": "silver"});
            $("#fine_entry_feeling").css({"background-color": "lemonchiffon"});
            $("#fine_entry_feeling").focus();
            $("#feel_param_hidden").val(":)");
        });
    }

    exam.Controller._doSadEntryFeelingOnClick = function() {
        $("#sad_entry_feeling").click(function() {
            $("a.entry_feeling").css({"background-color": "silver"});
            $("#sad_entry_feeling").css({"background-color": "lemonchiffon"});
            $("#sad_entry_feeling").focus();
            $("#feel_param_hidden").val(":(");
        });
    }

    exam.Controller._doCoolEntryFeelingOnClick = function() {
        $("#cool_entry_feeling").click(function() {
            $("a.entry_feeling").css({"background-color": "silver"});
            $("#cool_entry_feeling").css({"background-color": "lemonchiffon"});
            $("#cool_entry_feeling").focus();
            $("#feel_param_hidden").val(":|");
        });
    }

    exam.Controller._doWonderEntryFeelingOnClick = function() {
        $("#wonder_entry_feeling").click(function() {
            $("a.entry_feeling").css({"background-color": "silver"});
            $("#wonder_entry_feeling").css({"background-color": "lemonchiffon"});
            $("#wonder_entry_feeling").focus();
            $("#feel_param_hidden").val(":o");
        });
    }

    ///////////////////////////////////////////////////////////////////////////
    // private methods

    // init a component of the controller class.
    exam.Controller._initComponent = function() {
        
        // initialize the websocket object.
        exam.VisitorSocket.init();
        
        // initialize event handler methods.
        exam.Controller._doOpenCommandOnClick();
        exam.Controller._doCloseCommandOnClick();
        exam.Controller._doSendCommandOnClick();
        exam.Controller._doFineEntryFeelingOnClick();
        exam.Controller._doSadEntryFeelingOnClick();
        exam.Controller._doCoolEntryFeelingOnClick();
        exam.Controller._doWonderEntryFeelingOnClick();
        
        // and, do a some initialize.
        $("div.wrapper").draggable();
        $("#pad_wrapper").hide();
    }
}

///////////////////////////////////////////////////////////////////////////////
/**
* a functor class of the application.
* get the value of country
*/
exam.CountryValueFactory = {
    // a dummy function..
    create: function() {
        var locale = window.navigator.language.toLowerCase();
        if (locale == "en") {
            return "UK";
        } else if (locale == "ja") {
            return "Japan";
        } else if (locale == "ko") {
            return "Korea";
        }
        // defalut is USA..
        return "USA";
    }
}

///////////////////////////////////////////////////////////////////////////////
/**
* a utility class of the application.
*/
exam.Util = { 
    escapeHtml: function(value) {
        return $("<div />").text(value).html();
    }
}

///////////////////////////////////////////////////////////////////////////////
/**
* call the main entry.
*/
new exam.Application(this);
