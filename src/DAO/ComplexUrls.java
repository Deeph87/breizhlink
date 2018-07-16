package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Instant;

public class ComplexUrls extends Connector {

    private beans.ComplexUrls complexUrls;

    public ComplexUrls(beans.ComplexUrls complexUrls) {
        this.complexUrls = complexUrls;
    }

    public ComplexUrls() {}

    public void save() {

        try {
            Connection connection = this.getConnection();

            String query = "INSERT INTO complexe_urls"
                    + "(id_url, captcha, max_date, date_start, date_end, email) VALUES"
                    + "(?,?,?,?,?,?)";

            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setInt(1, this.complexUrls.getUrlId());
            stmt.setBoolean(2, this.complexUrls.isCaptcha());
            stmt.setLong(3, this.complexUrls.getMaxDate());
            stmt.setDate(4, this.complexUrls.getStartDate());
            stmt.setDate(5, this.complexUrls.getEndDate());
            stmt.setString(6, this.complexUrls.getEmail());

            stmt.executeUpdate();

            this.closeConnection();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public boolean canAccessToUrl(int urlId) {
        boolean ret = true;

        try {
            Connection connection = this.getConnection();

            String query = "SELECT * FROM complexe_urls WHERE id_url = '" + urlId + "'";

            PreparedStatement stmt = connection.prepareStatement(query);

            ResultSet resultSet = stmt.executeQuery();

            this.closeConnection();

            Instant nowInstant = Instant.now();
            long nowLong = nowInstant.getEpochSecond();


            if(resultSet.next()) {
                if(resultSet.getString("max_date") != null){
                    String maxDate = resultSet.getString("max_date");
                    long maxDateLong = Long.parseLong (maxDate);
                    if(maxDateLong < nowLong){
                        ret = false;
                    }
                }
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return ret;
    }

}
