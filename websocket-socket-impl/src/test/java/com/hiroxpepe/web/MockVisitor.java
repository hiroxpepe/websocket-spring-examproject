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

/**
 * @author hiroxpepe
 */
public class MockVisitor implements SiteVisitor {
    
    private String person;
    
    private String country;
    
    private String feeling;

    ///////////////////////////////////////////////////////////////////////////
    // simple bean properties.
    
    @Override
    public String getPerson() {
        return person;
    }

    @Override
    public void setPerson(String person) {
        this.person = person;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public void setCountry(String country) {
        this.country = country;
    }
    
    @Override
    public String getFeeling() {
        return feeling;
    }

    @Override
    public void setFeeling(String feeling) {
        this.feeling = feeling;
    }
    
}
