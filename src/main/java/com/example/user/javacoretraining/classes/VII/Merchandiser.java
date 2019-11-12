package com.example.user.javacoretraining.classes.VII;

import java.util.ArrayList;
import java.util.List;

public class Merchandiser {

    private List<Product> products = new ArrayList<>();
    private List<Client> blackList = new ArrayList<>();
    private List<Sale> sales = new ArrayList<>();

    public void addProduct(double price, double size) {
        products.add(new Product(price, size));
    }

    public Product getProduct(int i) {
        return products.get(i);
    }

    public void registerSale(Sale sale, Client client, boolean pay) {
        sale.setClient(client);
        sale.setPaid(client.pay(pay));
        sales.add(sale);
        for (Product item : client.getOrder().getProducts()) {
            products.remove(item);
        }
        if (!pay) blackList.add(client);
    }
}
