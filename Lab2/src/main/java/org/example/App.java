package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws SQLException {

        ProductDAO<Product, String> productDAO;
        productDAO = new ProductDAO<Product, String>();

        while (true) {
            System.out.println("\n1. Read product list");
            System.out.println("2. Read a product by input id");
            System.out.println("3. Add new product");
            System.out.println("4. Update product");
            System.out.println("5. Delete product");
            System.out.println("6. Exit");
            System.out.print("Please choose your option: ");
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            if (option == 6) {
                break;
            }
            switch (option) {
                case 1 -> {
                    System.out.println("id " + "name " + "color " + "price");
                    for (Product product : productDAO.readAll()) {
                        System.out.println(product.getId() + " " + product.getName() + " " + product.getColor() + " " + product.getPrice());
                    }
                }
                case 2 -> {
                    System.out.print("Please input id: ");
                    String id = scanner.next();
                    Product product = productDAO.read(id);
                    System.out.println(product.getId() + " " + product.getName() + " " + product.getColor() + " " + product.getPrice());
                }
                case 3 -> {
                    System.out.print("Please input name: ");
                    String name = scanner.next();
                    System.out.print("Please input color: ");
                    String color = scanner.next();
                    System.out.print("Please input price: ");
                    double price = scanner.nextDouble();
                    Product product1 = new Product(autoIncreaseIdToAdd(), name, color, price);
                    productDAO.add(product1);
                }
                case 4 -> {
                    System.out.print("Please input id: ");
                    String id2 = scanner.next();
                    System.out.print("Please input name: ");
                    String name1 = scanner.next();
                    System.out.print("Please input color: ");
                    String color1 = scanner.next();
                    System.out.print("Please input price: ");
                    double price1 = scanner.nextDouble();
                    Product product2 = new Product(id2, name1, color1, price1);
                    productDAO.update(product2);
                }
                case 5 -> {
                    System.out.print("Please input id: ");
                    String id3 = scanner.next();
                    productDAO.delete(id3);
                }

                default -> System.out.print("\nPlease choose option from 1 to 6");
            }
        }
    }

    //function auto increase id
    public static String autoIncreaseIdToAdd() throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select id from Product order by id desc limit 1");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        String id = resultSet.getString("id");
        int newId = Integer.parseInt(resultSet.getString("id")) + 1;
        return String.valueOf(newId);
    }
}
