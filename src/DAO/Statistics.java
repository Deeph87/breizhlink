package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Statistics extends Connector {
    private beans.Statistics statistics;

    public Statistics(beans.Statistics statistics) {
        this.statistics = statistics;
    }

    public Statistics() {}

    public void createOrUpdate() {

        Date date = new Date(System.currentTimeMillis());
//        SimpleDateFormat today = new SimpleDateFormat("YYYY-MM-dd");
//        String todayString = today.format(date);

        int urlId = this.statistics.getUrl_id();

        if(this.areStatsExistTodayByUrlId(urlId)) {
            try {
                Connection connection = this.getConnection();

                String query = "UPDATE statistics SET visites = visites + 1 WHERE url_id = '" + urlId + "'";

                PreparedStatement stmt = connection.prepareStatement(query);

                stmt.executeUpdate();

                this.closeConnection();

            } catch (Exception exception) {
                exception.printStackTrace();
            }

        } else {
            try {
                Connection connection = this.getConnection();

                String query = "INSERT INTO statistics"
                        + "(url_id, visites, date) VALUES"
                        + "(?,?,?)";

                PreparedStatement stmt = connection.prepareStatement(query);



                stmt.setInt(1, this.statistics.getUrl_id());
                stmt.setInt(2, 1);
                stmt.setDate(3, date);

                stmt.executeUpdate();

                this.closeConnection();

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public boolean areStatsExistTodayByUrlId(int urlId) {
        boolean ret = false;

        try {
            Connection connection = this.getConnection();

            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat today = new SimpleDateFormat("YYYY-MM-dd");
            String todayString = today.format(date);

            String query = "SELECT * FROM statistics WHERE url_id = '"  + urlId + "' and date = '" + todayString + "'";

            PreparedStatement stmt = connection.prepareStatement(query);

            ResultSet statsFound = stmt.executeQuery();

            this.closeConnection();

            if(statsFound.next()){
                if (statsFound.getDate("date") != null) {
                    ret = true;
                }
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return ret;
    }

    public HashMap<String, ArrayList> getStatsByUrlId(int urlId)
    {
        HashMap<String, ArrayList> map = new HashMap<>();

        try {
            Connection connection = this.getConnection();

            String query = "SELECT * FROM statistics WHERE url_id = '"  + urlId + "' ORDER BY date ASC";

            PreparedStatement stmt = connection.prepareStatement(query);

            ResultSet statsFound = stmt.executeQuery();

            this.closeConnection();

            ArrayList<String> days = new ArrayList<>();
            ArrayList<String> visites = new ArrayList<>();

            while(statsFound.next()){
                days.add(statsFound.getString("date"));
                visites.add(statsFound.getString("visites"));
            }

            map.put("days", days);
            map.put("visites", visites);

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return map;
    }

    public void setStatisticsBean(beans.Statistics statistics)
    {
        this.statistics = statistics;
    }
}
