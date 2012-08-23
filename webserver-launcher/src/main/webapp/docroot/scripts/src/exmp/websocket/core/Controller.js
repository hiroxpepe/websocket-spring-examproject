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
* a controller class of the application.
*/
exmp.websocket.core.Controller = window; {

    ///////////////////////////////////////////////////////////////////////////
    // public methods

    // init the controller class.
    exmp.websocket.core.Controller.init = function() {
        exmp.websocket.core.Controller._initComponent();
    }

    ///////////////////////////////////////////////////////////////////////////
    // event handler methods

    // an entry pad window be opened.
    exmp.websocket.core.Controller._doOpenCommandOnClick = function() {
        $("#open-command").click(function() {
            $("#pad-wrapper").show().css({
                top: -50,
                left: -50
            });
            $("#fine-entry-feeling").click();
        });
    }

    // an entry pad window be closed.
    exmp.websocket.core.Controller._doCloseCommandOnClick = function() {
        $("#close-command").click(function() {
            $("#pad-wrapper").hide(); 
        });
    }

    // a message be sent to server by the websocket object.
    exmp.websocket.core.Controller._doSendCommandOnClick = function() {
        $("#send-command").click(function() {
            // create a message.
            var first_name = exmp.websocket.Util.escapeHtml($("#first-name").val());
            var last_name = exmp.websocket.Util.escapeHtml($("#last-name").val());
            var feeling = $("#feeling-value").val();
            var country = exmp.websocket.CountryValueFactory.create();
            var param =
                '{"person":"' + first_name + " " + last_name + 
                '","feeling":"' + feeling + 
                '","country":"' + country + '"}';

            // send a message using the websocket object.
            exmp.websocket.VisitorSocket.send(
                param
            );
        });
    }

    // a feeling icon is clicked, 
    // the value of the param of feeling is set to hidden area.
    exmp.websocket.core.Controller._doFineEntryFeelingOnClick = function() {
        $("#fine-entry-feeling").click(function() {
            $("a.entry-feeling").css({"background-color": "silver"});
            $("#fine-entry-feeling").css({"background-color": "lemonchiffon"});
            $("#fine-entry-feeling").focus();
            $("#feeling-value").val(":)");
        });
    }

    exmp.websocket.core.Controller._doSadEntryFeelingOnClick = function() {
        $("#sad-entry-feeling").click(function() {
            $("a.entry-feeling").css({"background-color": "silver"});
            $("#sad-entry-feeling").css({"background-color": "lemonchiffon"});
            $("#sad-entry-feeling").focus();
            $("#feeling-value").val(":(");
        });
    }

    exmp.websocket.core.Controller._doCoolEntryFeelingOnClick = function() {
        $("#cool-entry-feeling").click(function() {
            $("a.entry-feeling").css({"background-color": "silver"});
            $("#cool-entry-feeling").css({"background-color": "lemonchiffon"});
            $("#cool-entry-feeling").focus();
            $("#feeling-value").val(":|");
        });
    }

    exmp.websocket.core.Controller._doWonderEntryFeelingOnClick = function() {
        $("#wonder-entry-feeling").click(function() {
            $("a.entry-feeling").css({"background-color": "silver"});
            $("#wonder-entry-feeling").css({"background-color": "lemonchiffon"});
            $("#wonder-entry-feeling").focus();
            $("#feeling-value").val(":o");
        });
    }

    ///////////////////////////////////////////////////////////////////////////
    // private methods

    // init a component of the controller class.
    exmp.websocket.core.Controller._initComponent = function() {
        
        // initialize the websocket object.
        exmp.websocket.VisitorSocket.init();
        
        // initialize event handler methods.
        exmp.websocket.core.Controller._doOpenCommandOnClick();
        exmp.websocket.core.Controller._doCloseCommandOnClick();
        exmp.websocket.core.Controller._doSendCommandOnClick();
        exmp.websocket.core.Controller._doFineEntryFeelingOnClick();
        exmp.websocket.core.Controller._doSadEntryFeelingOnClick();
        exmp.websocket.core.Controller._doCoolEntryFeelingOnClick();
        exmp.websocket.core.Controller._doWonderEntryFeelingOnClick();
        
        // and, do a some initialize.
        $("div.wrapper").draggable();
        $("#pad-wrapper").hide();
    }
}