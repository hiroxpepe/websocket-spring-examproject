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

import java.util.Random;

/**
 * @author hiroxpepe
 */
public class MockSiteVisitor implements SiteVisitor {
    
    private String person;
    
    private String country;
    
    private String feeling;
    
    private static Random random = new Random();

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

    ///////////////////////////////////////////////////////////////////////////
    // use generate a random value, and return a new object.
    
    public static MockSiteVisitor newInstance() {
        MockSiteVisitor visitor = new MockSiteVisitor();
        visitor.setPerson(firstNames[random.nextInt(firstNames.length)] + " "  + lastNames[random.nextInt(lastNames.length)]);
        visitor.setCountry(countries[random.nextInt(countries.length)]);
        visitor.setFeeling(feelings[random.nextInt(feelings.length)]);
        return visitor;
    }

    ///////////////////////////////////////////////////////////////////////////
    // this is a  mock values.
    
    private static String[] firstNames = new String[] {
        "John", "Paul", "George", "Ringo"
    };

    private static String[] lastNames = new String[] {
        "Lennon", "McCartney", "Harrison", "Starr"
    };
    
    private static String[] countries = new String[] {
        "USA", "UK", "Japan", "Korea"
    };
    
    private static String[] feelings = new String[] {
        ":)", ":(", ":|", ":0"
    };

}
