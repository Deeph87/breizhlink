package beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAOSimpleUrls extends DAOConnector {

    private SimpleUrls simpleUrls;

    public DAOSimpleUrls(SimpleUrls simpleUrls) {
        super();
        this.simpleUrls = simpleUrls;
    }

    public void save() {
        try {
            Connection connection = this.getConnection();

            String query = "INSERT INTO simple_urls"
                    + "(destination_url, generated_urls) VALUES"
                    + "(?,?)";

            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setString(1, this.simpleUrls.getDestinationUrl());
            stmt.setString(2, this.simpleUrls.getGeneratedUrl());

            stmt.executeUpdate();
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
}
