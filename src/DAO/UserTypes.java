package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserTypes extends Connector {

//    private beans.UserTypes UserTypesBean;

//    public UserType() {
//        this.UserTypesBean = new beans.UserTypes();
//    }

    public ArrayList<beans.UserType> getUserTypes()
    {
        ArrayList<beans.UserType> UserTypes = new ArrayList<>();

        try {
            Connection connection = this.getConnection();

            String query = "SELECT * FROM user_types";

            PreparedStatement stmt = connection.prepareStatement(query);

            ResultSet destinationURL = stmt.executeQuery();

            this.closeConnection();

            while(destinationURL.next()){
                int id = destinationURL.getInt("id");
                String name = destinationURL.getString("name");

                beans.UserType type = new beans.UserType();

                type.setId(id);
                type.setName(name);

                UserTypes.add(type);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return UserTypes;
    }

}
