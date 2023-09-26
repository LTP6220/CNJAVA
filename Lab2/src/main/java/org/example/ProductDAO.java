package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO<P extends Product, S extends String> implements Repository<Product, String> {
    private final Connection connection = ConnectionDB.getConnection();

    @Override
    public String add(Product item) {
        String id = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into Product(id,name,color,price) values (?,?,?,?)");
            preparedStatement.setString(1, item.getId());
            preparedStatement.setString(2, item.getName());
            preparedStatement.setString(3, item.getColor());
            preparedStatement.setDouble(4, item.getPrice());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;

    }

    @Override
    public List<Product> readAll() {

        List<Product> result = new ArrayList<Product>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from Product");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String color = resultSet.getString("color");
                double price = resultSet.getDouble("price");
                Product product = new Product(id, name, color, price);
                result.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Product read(String id) {
        Product product = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from Product where id = ?");
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String color = resultSet.getString("color");
                double price = resultSet.getDouble("price");
                product = new Product(id, name, color, price);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    public boolean update(Product item) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update Product set name = ?, color = ?, price = ? where id = ?");
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getColor());
            preparedStatement.setDouble(3, item.getPrice());
            preparedStatement.setString(4, item.getId());
            preparedStatement.executeUpdate();
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from Product where id = ?");
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
