package com.evernym.verity.sdk.protocols.presentproof.common;

import org.json.JSONArray;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class AttributeTest {
    @Test
    public void nullTest() {
        Attribute r = new Attribute("test", (Restriction) null);
        assertEquals("test", r.data.getString("name"));
        assertEquals(0, r.data.getJSONArray("restrictions").length());
    }

    @Test
    public void noRestrictionTest() {
        Attribute r = new Attribute("test");
        assertEquals(0, r.data.getJSONArray("restrictions").length());
    }

    @Test public void oneRestrictionTest() {
        Restriction r = RestrictionBuilder.blank()
                .credDefId("SDFSDF")
                .build();
        Attribute a = new Attribute("test", r);
        JSONArray restrictions = a.data.getJSONArray("restrictions");
        assertEquals(1, restrictions.length());
        assertEquals(restrictions.toString(), "[{\"cred_def_id\":\"SDFSDF\"}]");
    }

    @Test
    public void namesTest() {
        String[] names = {"A", "B", "C"};
        Attribute a = new Attribute(names, (Restriction) null);
        assertEquals(Arrays.asList(names), a.data.getJSONArray("names").toList());
        assertEquals(0, a.data.getJSONArray("restrictions").length());
    }

    @Test
    public void namesOneRestrictionTest() {
        String[] names = {"A", "B", "C"};
        Restriction r = RestrictionBuilder.blank()
                .credDefId("SDFSDF")
                .build();
        Attribute a = new Attribute(names, r);
        assertEquals(Arrays.asList(names), a.data.getJSONArray("names").toList());
        JSONArray restrictions = a.data.getJSONArray("restrictions");
        assertEquals(1, restrictions.length());
        assertEquals(restrictions.toString(), "[{\"cred_def_id\":\"SDFSDF\"}]");
    }
}
