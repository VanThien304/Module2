package com.codegym.services;
import com.codegym.model.Order;
import com.codegym.utils.CSVUtils;
import java.util.ArrayList;
import java.util.List;

public class OrderService implements IOrderService {
    public static String path = "data/orders.csv";

    private static OrderService instance;
    private OrderService(){}

    public static OrderService getInstance(){
        if(instance == null)
            instance = new OrderService();
        return instance;
    }
    @Override
    public List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        List<String> records = CSVUtils.read(path);
        for (String record : records) {
            orders.add(Order.parse(record));
        }
        return orders ;
    }

    @Override
    public void add(Order newOrder) {
        List<Order> orders = getOrders();
        orders.add(newOrder);
        CSVUtils.write(path, orders);
    }

    @Override
    public void update() {
        List<Order> orders = getOrders();
        CSVUtils.write(path, orders);
    }


    @Override
    public Order getOrderById(int id) {
        List<Order> orders = getOrders();
        for (Order order : orders) {
            if (order.getId() == id)
                return order;
        }
        return null;
    }
    @Override
    public boolean exist(int id) {
        return getOrderById(id) != null;
    }

//    @Override
//    public boolean checkDuplicateName(String name) {
//        List<Order> orders = getOrders();
//        for (Order order : orders) {
//            if (order.getName().equals(name))
//                return true;
//        }
//        return false;
//    }

//    @Override
//    public boolean checkDuplicateId(int id) {
//        List<Order> orders = getOrders();
//        for (Order order : orders) {
//            if (order.getId() == id)
//                return true;
//        }
//        return false;
//    }
//    public boolean checkDuplicateColor(String color){
//        for (Order order : orders){
//            if(order.)
//        }
//    }

    @Override
    public void remove(Order order) {
        List<Order> orders = getOrders();
        orders.remove(order);
        update();
    }


}
