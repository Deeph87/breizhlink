package DAO;

import beans.URLSPasswords;

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
}
