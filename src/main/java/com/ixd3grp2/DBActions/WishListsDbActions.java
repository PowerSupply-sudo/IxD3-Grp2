package com.ixd3grp2.DBActions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ixd3grp2.DBConnection;
import com.ixd3grp2.frontend.wishLists.wishListsBoxes;

public class WishListsDbActions {
    private DBConnection dbConn;

    public WishListsDbActions() throws SQLException {
        this.dbConn = DBConnection.getInstance();
    }

    // Read a specific wishlist by ID
    public wishListsBoxes getWishlistById(int id) throws SQLException {
        String sql = "SELECT id, name FROM wishlists WHERE id = ?";
        try (PreparedStatement ps = dbConn.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        }
        return null;
    }

    // Delete a wishlist
    public void deleteWishlist(int id) throws SQLException {
        String sql = "DELETE FROM wishlists WHERE id = ?";
        try (PreparedStatement ps = dbConn.getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
        }
    } 


    // Update a wishlist
    public boolean updateWishlist(int id, wishListsBoxes updatedWishlist) throws SQLException {
        String sql = "UPDATE wishlists SET name = ? WHERE id = ?";
        try (PreparedStatement ps = dbConn.getConnection().prepareStatement(sql)) {
            ps.setString(1, updatedWishlist.getName());
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        }

    }

    private wishListsBoxes mapRow(ResultSet rs) throws SQLException {
        String name = rs.getString("name");
        return new wishListsBoxes(name);
    }
}
