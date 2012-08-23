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
* a functor class of the application.
* get the value of country
*/
exmp.websocket.CountryValueFactory = {
    // a dummy function..
    create: function() {
        var locale = window.navigator.language.toLowerCase();
        if (locale == "en") {
            return "UK";
        } else if (locale == "ja") {
            return "Japan";
        }
        // defalut is USA..
        return "USA";
    }
}