/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

/**
 *
 * @author PC
 */
public class StoreManagement {

   


    //allow user create fruit
   public static void createFruit(ArrayList<Fruit> lf) {
        //loop until user don't want to create fruit
        while (true) {
            System.out.print("Enter fruit id: ");
            String fruitId = Validation.checkInputString();
            //check id exist
            if (!Validation.checkIdExist(lf, fruitId)) {
                System.err.println("Id exist");
                return;
            }
            System.out.print("Enter fruit name: ");
            String fruitName = Validation.checkInputString();
            System.out.print("Enter price: ");
            double price = Validation.checkInputDouble();
            System.out.print("Enter quantity: ");
            int quantity = Validation.checkInputInt();
            System.out.print("Enter origin: ");
            String origin = Validation.checkInputString();
            lf.add(new Fruit(fruitId, fruitName, price, quantity, origin));
            //check user want to continue or not
            if (!Validation.checkInputYN()) {
                return;
            }
        }
    }

    //allow user show view order
public static void viewOrder(ArrayList<Order> orders) {
    Hashtable<String, ArrayList<Order>> ht = new Hashtable<String, ArrayList<Order>>();
    for (Order order : orders) {
        String customerName = order.getCustomerName();
        if (ht.containsKey(customerName)) {
            ArrayList<Order> customerOrders = ht.get(customerName);
            customerOrders.add(order);
        } else {
            ArrayList<Order> customerOrders = new ArrayList<Order>();
            customerOrders.add(order);
            ht.put(customerName, customerOrders);
        }
    }
    for (String name : ht.keySet()) {
        System.out.println("Customer: " + name);
        ArrayList<Order> customerOrders = ht.get(name);
        displayListOrder(customerOrders);
    }
}


public static void shopping(ArrayList<Fruit> lf, ArrayList<Order> orders) {
    Hashtable<String, ArrayList<Order>> ht = new Hashtable<String, ArrayList<Order>>();
    if (lf.isEmpty()) {
        System.err.println("No items available.");
        return;
    }
    ArrayList<Order> lo = new ArrayList<>();
    while (true) {
        displayListFruit(lf);
        System.out.print("Enter item: ");
        int item = Validation.checkInputIntLimit(1, lf.size());
        Fruit fruit = getFruitByItem(lf, item);
        System.out.print("Enter quantity: ");
        int quantity = Validation.checkInputIntLimit(1, fruit.getQuantity());
        fruit.setQuantity(fruit.getQuantity() - quantity);
        if (!Validation.checkItemExist(lo, fruit.getFruitId())) {
            lo.add(new Order(fruit.getFruitId(), fruit.getFruitName(),
                    quantity, fruit.getPrice()));
        } else {
            updateOrder(lo, fruit.getFruitId(), quantity);
        }
        if (!Validation.checkInputYN()) {
            break;
        }
    }
    displayListOrder(lo);
        System.out.print("Enter name: ");
        String name = Validation.checkInputString();
        ht.put(name, lo);
        System.err.println("Add successfull");
    }

    //display list fruit in shop
 public   static void displayListFruit(ArrayList<Fruit> lf) {
        int countItem = 1;
        System.out.printf("%-10s%-20s%-20s%-15s\n", "Item", "Fruit name", "Origin", "Price");
        for (Fruit fruit : lf) {
            //check shop have item or not 
            if (fruit.getQuantity() != 0) {
                System.out.printf("%-10d%-20s%-20s%-15.0f$\n", countItem++,
                        fruit.getFruitName(), fruit.getOrigin(), fruit.getPrice());
            }
        }
    }

    //get fruint user want to by
public    static Fruit getFruitByItem(ArrayList<Fruit> lf, int item) {
        int countItem = 1;
        for (Fruit fruit : lf) {
            //check shop have item or not 
            if (fruit.getQuantity() != 0) {
                countItem++;
            }
            if (countItem - 1 == item) {
                return fruit;
            }
        }
        return null;
    }

    //display list order
  public  static void displayListOrder(ArrayList<Order> lo) {
        double total = 0;
        System.out.printf("%15s%15s%15s%15s\n", "Product", "Quantity", "Price", "Amount");
        for (Order order : lo) {
            System.out.printf("%15s%15d%15.0f$%15.0f$\n", order.getFruitName(),
                    order.getQuantity(), order.getPrice(),
                    order.getPrice() * order.getQuantity());
            total += order.getPrice() * order.getQuantity();
        }
        System.out.println("Total: " + total);
    }

    //if order exist then update order
 public   static void updateOrder(ArrayList<Order> lo, String id, int quantity) {
        for (Order order : lo) {
            if (order.getFruitId().equalsIgnoreCase(id)) {
                order.setQuantity(order.getQuantity() + quantity);
                return;
            }
        }
    }
}
