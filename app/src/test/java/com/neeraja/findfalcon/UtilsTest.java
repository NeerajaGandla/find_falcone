package com.neeraja.findfalcon;

import com.neeraja.findfalcon.utils.Utils;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;

public class UtilsTest {
    @Test
    public void isValidString_isInvalid() {
        assertFalse("This Should Return False", Utils.isValidString(null));
    }

    @Test
    public void isValidString_isValid() {
        assertTrue("This Should Return True", Utils.isValidString("Neeraja"));
    }

    @Test
    public void isValidArrayList_isInvalid() {
        assertFalse("This Should Return False",Utils.isValidArrayList(new ArrayList<String>()));
    }

    @Test
    public void isValidArrayList_isValid() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Test 1");
        list.add("Test 2");
        assertTrue("This Should Return True", Utils.isValidArrayList(list));
    }

}
