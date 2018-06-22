package com.leo.jdk8;

/**
 * Java 8 新增了接口的默认方法
 * 简单说，默认方法就是接口可以有实现方法，而且不需要实现类去实现其方法
 * 多继承问题：@Override重写，Interface.super.method调用指定接口method
 */
public class testDefaultMethod {

   public static void main(String args[]){
      Vehicle vehicle = new Carz();
      vehicle.print();
   }
}

interface Vehicle {
   default void print(){
      System.out.println("我是一辆车!");
   }
    
   static void blowHorn(){
      System.out.println("按喇叭!!!");
   }
}
 
interface FourWheeler {
   default void print(){
      System.out.println("我是一辆四轮车!");
   }
}
 
class Carz implements Vehicle, FourWheeler {
	
   @Override
   public void print(){
      Vehicle.super.print();
      FourWheeler.super.print();
      Vehicle.blowHorn();
      System.out.println("我是一辆汽车!");
   }
}