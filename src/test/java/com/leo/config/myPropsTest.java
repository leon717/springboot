package com.leo.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class myPropsTest {
	@Autowired  
    private MyProps myProps;   
      
    @Test  
    public void propsTest(){  
        System.out.println("simpleProp: " + myProps.getSimpleProp());  
        System.out.println("arrayProps: " + myProps.getArrayProps());  
        System.out.println("listProp1: " + myProps.getListProp1());  
        System.out.println("listProp2: " + myProps.getListProp2());  
        System.out.println("mapProps: " + myProps.getMapProps());  
    }  
}
