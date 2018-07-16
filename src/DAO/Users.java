package DAO;

import beans.User;
import beans.UserType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Users extends Connector {

    private User User;

    public Users(User User)
    {
        this.User = User;
    }

    public void save()
    {
        try {
            Connection connection = this.getConnection();

            String query = "INSERT INTO users"
                    + "(email, password, type_id, enabled) VALUES"
                    + "(?,?,?,?)";

            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setString(1, this.User.getEmail());
            stmt.setString(2, this.User.getPassword());
            stmt.setString(3, this.User.getTypeId());
            stmt.setBoolean(4, this.User.getEnabled());

            stmt.executeUpdate();
            this.closeConnection();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public boolean userExists() {

        boolean ret = false;

        try {
            Connection connection = this.getConnection();

            String query = "SELECT * FROM users WHERE email = '" + this.User.getEmail() + "' AND password = '" + this.User.getPassword() + "'";

            PreparedStatement stmt = connection.prepareStatement(query);

            ResultSet userFound = stmt.executeQuery();

            this.closeConnection();

            if (userFound.next()) {
                if(userFound.getString("email") != null){
                    ret = true;
                    this.User.setId(userFound.getInt("id"));
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return ret;
    }
}
