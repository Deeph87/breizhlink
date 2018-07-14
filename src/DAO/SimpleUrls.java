package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SimpleUrls extends Connector {

    private beans.SimpleUrls simpleUrls;
    private int generatedId;

    public SimpleUrls(beans.SimpleUrls simpleUrls) {
        super();
        this.simpleUrls = simpleUrls;
    }

    public void save() {
        String[] returnId = { "ID" };

        try {
            Connection connection = this.getConnection();

            String query = "INSERT INTO simple_urls"
                    + "(destination_url, generated_urls) VALUES"
                    + "(?,?)";

            PreparedStatement stmt = connection.prepareStatement(query, returnId);

            stmt.setString(1, this.simpleUrls.getDestinationUrl());
            stmt.setString(2, this.simpleUrls.getGeneratedUrl());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if(rs.next()) {
                    this.generatedId = rs.getInt(1);
                }
            }

            this.closeConnection();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public String getDestinationUrlByGeneratedUrl(){
        String ret = "";
        try {
            Connection connection = this.getConnection();

            String query = "SELECT destination_url FROM simple_urls WHERE generated_urls = ?";

            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setString(1, this.simpleUrls.getGeneratedUrl());

            ResultSet destinationURL = stmt.executeQuery();

            this.closeConnection();
            while(destinationURL.next()){
                ret = destinationURL.getString("destination_url");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return ret;
    }

    public int getGeneratedKey() {
        return this.generatedId;
    }
}
