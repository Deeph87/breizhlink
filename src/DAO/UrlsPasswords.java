package DAO;

import beans.SimpleUrls;
import beans.URLSPasswords;
import services.Password;

import java.sql.*;

public class UrlsPasswords extends Connector {

    private URLSPasswords urlsPasswords;

    public UrlsPasswords(URLSPasswords urlsPasswords) {
        super();
        this.urlsPasswords = urlsPasswords;
    }

    public void save() {
        try {
            Connection connection = this.getConnection();

            String query = "INSERT INTO urls_passwords"
                    + "(url_id, password) VALUES"
                    + "(?,?)";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, this.urlsPasswords.getUrlId());
            statement.setString(2, this.urlsPasswords.getPassword());

            statement.executeUpdate();

            this.closeConnection();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public boolean canAccessToUrl(String Id) {
        boolean ret = false;

        Password passwordService = new Password(this.urlsPasswords.getPassword());
        String encryptedPassword = passwordService.encrypt();

        try {
            Connection connection = this.getConnection();

            String query = "SELECT * FROM urls_passwords WHERE url_id = '" + Id + "'";

            PreparedStatement stmt = connection.prepareStatement(query);

            ResultSet resultSet = stmt.executeQuery();

            this.closeConnection();

            if(resultSet.next()) {
                if(resultSet.getString("password").equals(encryptedPassword)){
                    ret = true;
                }
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return ret;
    }
}
