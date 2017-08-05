package com.iceyyy.iceworkday;

import org.junit.Test;

import com.iceyyy.workday.WorkUtils;

public class IceWorkTest {
   @Test
   public void test(){
	   System.out.println(WorkUtils.weekendMap("2013"));
	   System.out.println(WorkUtils.isWorkendDay("20130205"));
   }

}
