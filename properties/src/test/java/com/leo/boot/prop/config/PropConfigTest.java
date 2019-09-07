package com.leo.boot.prop.config;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.leo.boot.prop.domain.Prop;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PropConfigTest {

    @Autowired
    private PropConfig propConfig;

    @Test
    public void testGetSingle() {
        assertEquals("single", propConfig.getSingle());
    }

    @Test
    public void testGetArray() {
        assertArrayEquals(new String[] {"1", "2", "3"}, propConfig.getArray());
    }

    @Test
    public void testGetList() {
        assertEquals(Arrays.asList("1", "2", "3"), propConfig.getList());
    }

    @Test
    public void testGetMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        assertEquals(map, propConfig.getMap());
    }

    @Test
    public void testGetMapList() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        ArrayList<Map<String, String>> list = new ArrayList<>();
        list.add(map);
        assertEquals(list, propConfig.getMapList());
    }

    @Test
    public void testGetListMap() {
        Map<String, List<String>> map = new HashMap<>();
        map.put("1", Arrays.asList("1","2","3"));
        assertEquals(map, propConfig.getListMap());
    }

    @Test
    public void testGetProp() {
        Prop prop = new Prop();
        prop.setName("name");
        prop.setValue("value");
        assertEquals(prop, propConfig.getProp());
    }
    
}
