/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Manager;
import Model.Fruit;
import Model.Order;
import java.util.ArrayList;
import java.util.Hashtable;
/**
 *
 * @author PC
 */
public class Main {
public static void main(String[] args) {
    ArrayList<Fruit> fruits = new ArrayList<>();
    ArrayList<Order> orders = new ArrayList<>();
    Hashtable<String, ArrayList<Order>> ht = new Hashtable<>();
    Manager manager = new Manager(fruits, orders);
    manager.run();
}
}

    
