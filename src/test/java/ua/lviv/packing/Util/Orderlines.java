package ua.lviv.packing.Util;

import java.util.ArrayList;
import java.util.Comparator;

import ua.lviv.packing.Entity.Orderline;

public class Orderlines {

    private static ArrayList<Orderline> orderlines = new ArrayList<>();

    public static void setOrderlines(ArrayList<Orderline> orderlines) {
        if(Orderlines.orderlines == null) {
            Orderlines.orderlines = orderlines;
        }
    }

    public static ArrayList<Orderline> getOrderlines() {
        return orderlines;
    }

    public static void sortByVolume() {
        Comparator<Orderline> byVolume = (Orderline orderline1, Orderline orderline2) -> {
            int volume1 = orderline1.getProducts().get(0).getVolume();
            int volume2 = orderline2.getProducts().get(0).getVolume();
            return -1 * Integer.compare(volume1, volume2);
        };
        orderlines.sort(byVolume);
    }

    public static void showAllProducts() {
        orderlines.stream().forEach(orderline -> {
            orderline.getProducts().stream().forEach(System.out::println);
        });
    }
}