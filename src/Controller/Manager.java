/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Fruit;
import Model.Order;
import Model.StoreManagement;
import View.Menu;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author PC
 */
public class Manager extends Menu<String> {
    ArrayList<Fruit> fruits;
    ArrayList<Order> orders;
    static String[] mc = {"Create Fruit", "View orders","Shopping (for buyer)", "Exit"};

    public  Manager(ArrayList<Fruit> fruits, ArrayList<Order> orders) {
        super("MAIN MENU", mc);
        this.fruits = fruits;
        this.orders = orders;
    }

    public void execute(int n) {
        switch (n) {
            case 1:
                StoreManagement.createFruit(fruits);
                break;
            case 2:
                StoreManagement.viewOrder(orders);
                break;
            case 3:
                StoreManagement.shopping(fruits, orders);
                break;
            case 4:
                return;
        }
    }
}