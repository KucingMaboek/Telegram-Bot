package utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Query {
    //Query
    public void checkChatId(String chatId, String text, String username) {
        String query = String.format("INSERT INTO conversation (chatId, username, requestCode, text)\n" +
                "SELECT * FROM (SELECT \'%s\', \'%s\', '000', \'%s\') AS tmp\n" +
                "WHERE NOT EXISTS (\n" +
                "    SELECT chatId FROM conversation WHERE chatId = \'%s\'\n" +
                ") LIMIT 1;", chatId, username, text, chatId);
        String queryUpdate = String.format("update conversation set username = \'%s\' where chatId = \'%s\'", username, chatId);
        try (Statement stats = Helper.connectDatabase().createStatement();) {
            stats.executeUpdate(query);
            stats.executeUpdate(queryUpdate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getRequestCode(String chatId) {
        String query = String.format("select * from conversation WHERE chatId = \'%s\'\n;", chatId);
        String requestCode = null;
        try {
            Statement stats = Helper.connectDatabase().createStatement();
            ResultSet rs = stats.executeQuery(query);
            while (rs.next()) {
                requestCode = rs.getString("requestCode");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requestCode;
    }

    public void updateConversation(String requestCode, String chatId, String text) {
        String query = String.format("update conversation set requestCode = \'%s\',text = \'%s\' where chatId = %s", requestCode, text, chatId);
        try (Statement stats = Helper.connectDatabase().createStatement()) {
            stats.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateData(String table, String column, String chatId, String text) {
        String query = String.format("UPDATE `%s` SET `%s`= \'%s\' WHERE  id = (SELECT MAX(id) FROM `%s` WHERE chatId= \'%s\');", table, column, text, table, chatId);
        try (Statement stats = Helper.connectDatabase().createStatement()) {
            stats.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cancelInsert(String table, String chatId) {
        String query = String.format("delete from `%s` WHERE  id = (SELECT MAX(id) FROM `%s` WHERE chatId= \'%s\');", table, table, chatId);
        try (Statement stats = Helper.connectDatabase().createStatement()) {
            stats.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertData(String table, String chatId) {
        String query = String.format("INSERT INTO `%s`(`chatId`) " +
                "VALUES (\'%s\')", table, chatId);
        try (Statement stats = Helper.connectDatabase().createStatement()) {
            stats.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setCurrentDate(String table, String chatId) {
        String query = String.format("UPDATE `%s` SET date= NOW() WHERE  id = (SELECT MAX(id) FROM `%s` WHERE chatId= \'%s\');", table, table, chatId);
        try (Statement stats = Helper.connectDatabase().createStatement()) {
            stats.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setCurrentTime(String table, String chatId) {
        String query = String.format("UPDATE `%s` SET time= CURRENT_TIME WHERE  id = (SELECT MAX(id) FROM `%s` WHERE chatId= \'%s\');", table, table, chatId);
        try (Statement stats = Helper.connectDatabase().createStatement()) {
            stats.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
