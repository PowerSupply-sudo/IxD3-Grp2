package com.ixd3grp2.DBActions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ixd3grp2.DBConnection;
import com.ixd3grp2.frontend.wishLists.listOfWishesBoxes;

public class WishDbActions {
    private DBConnection dbConn;

    public WishDbActions() throws SQLException {
        this.dbConn = DBConnection.getInstance();
    }

    // Read a specific wish by ID
    public listOfWishesBoxes getWishById(int id) throws SQLException {
        String sql = "SELECT id, name FROM wish WHERE id = ?";
        try (PreparedStatement ps = dbConn.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        }
        return null;
    }

    // Delete a wish
    public void deleteWish(int id) throws SQLException {
        String sql = "DELETE FROM wish WHERE id = ?";
        try (PreparedStatement ps = dbConn.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
        }
    } 


    // Update a wish
    public boolean updateWish(int id, listOfWishesBoxes updatedWish) throws SQLException {
        String sql = "UPDATE wish SET name = ? WHERE id = ?";
        try (PreparedStatement ps = dbConn.getConnection().prepareStatement(sql)) {
            ps.setString(1, updatedWish.getName());
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        }

    }

    private listOfWishesBoxes mapRow(ResultSet rs) throws SQLException {
        String name = rs.getString("name");
        String timeLeft = rs.getString("time_left");
        return new listOfWishesBoxes(name, timeLeft);
    }
}
