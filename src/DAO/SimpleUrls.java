package DAO;

import beans.UserType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
                    + "(destination_url, generated_urls, user_id) VALUES"
                    + "(?,?,?)";

            PreparedStatement stmt = connection.prepareStatement(query, returnId);

            stmt.setString(1, this.simpleUrls.getDestinationUrl());
            stmt.setString(2, this.simpleUrls.getGeneratedUrl());
            stmt.setInt(3, this.simpleUrls.getUserId());

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

    public beans.SimpleUrls getUrlByGeneratedUrl(){
        try {
            Connection connection = this.getConnection();

            String query = "SELECT * FROM simple_urls WHERE generated_urls = ?";

            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setString(1, this.simpleUrls.getGeneratedUrl());

            ResultSet destinationURL = stmt.executeQuery();

            this.closeConnection();
            while(destinationURL.next()){
                int id = destinationURL.getInt("id");
                String destinationUrl = destinationURL.getString("destination_url");

                this.simpleUrls.setId(id);
                this.simpleUrls.setDestinationUrl(destinationUrl);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return this.simpleUrls;
    }

    public boolean urlNeedsPassword() {
        boolean ret = false;

        this.getUrlByGeneratedUrl();

        try {
            Connection connection = this.getConnection();

            String query = "SELECT * FROM urls_passwords WHERE url_id = '" + this.simpleUrls.getId() + "'";

            PreparedStatement stmt = connection.prepareStatement(query);

            ResultSet resultSet = stmt.executeQuery();

            this.closeConnection();

            if(resultSet.next()) {
                ret = true;
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return ret;
    }

    public void getDestinationUrlById() {
        try {
            Connection connection = this.getConnection();

            String query = "SELECT * FROM simple_urls WHERE id = ?";

            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setInt(1, this.simpleUrls.getId());

            ResultSet destinationURL = stmt.executeQuery();

            this.closeConnection();
            while(destinationURL.next()){
                int id = destinationURL.getInt("id");
                String destinationUrl = destinationURL.getString("destination_url");
                String generatedUrl = destinationURL.getString("generated_urls");

                this.simpleUrls.setId(id);
                this.simpleUrls.setGeneratedUrl(generatedUrl);
                this.simpleUrls.setDestinationUrl(destinationUrl);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public int getGeneratedKey() {
        return this.generatedId;
    }

    public beans.SimpleUrls getSimpleUrls() {
        return this.simpleUrls;
    }

    public ArrayList<beans.SimpleUrls> getUrlsByUserId(int userId) {

        ArrayList<beans.SimpleUrls> listUrls = new ArrayList<>();

        try {
            Connection connection = this.getConnection();

            String query = "SELECT * FROM simple_urls WHERE user_id = '" + userId + "'";

            PreparedStatement stmt = connection.prepareStatement(query);

            ResultSet urls = stmt.executeQuery();

            this.closeConnection();

            while(urls.next()){
                String destination_url = urls.getString("destination_url");
                String generated_urls = urls.getString("generated_urls");
                Integer id = urls.getInt("id");

                beans.SimpleUrls url = new beans.SimpleUrls();

                url.setGeneratedUrl(generated_urls);
                url.setDestinationUrl(destination_url);
                url.setId(id);

                listUrls.add(url);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return listUrls;
    }
}
