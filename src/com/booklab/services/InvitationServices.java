/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.services;

import com.booklab.Utils.DataSource;
import com.booklab.models.Invitation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author steam
 */
public class InvitationServices {

    Connection cnx = DataSource.getInstance().getCnx();

    public void envoyerInvitation(Invitation i) {
        try {
            String req = "INSERT INTO Invitation (senderID,receiverID,invitationStatus) VALUES (?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, i.getSernderID());
            st.setInt(2, i.getReceiverID());
            st.setBoolean(3, i.isInvitationStatus());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            while (rs.next()) {
                System.out.println(rs.getInt(1));
            }
            System.out.println("Invitation envoyée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimerInvitation(Invitation i) {
        try {
            String req = "DELETE FROM invitation WHERE id=?";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, i.getInvitationID());
            st.executeUpdate();
            System.out.println("Invitation supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Invitation afficherInvitation(int idInvitation) {
        Invitation invitation = null;
        try {
            String req = "SELECT * FROM event where id=? AND invitationStatus=true";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, idInvitation);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                invitation = new Invitation(res.getInt(1), res.getInt(2), true);
            }
            System.out.println("Invitation récuperée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return invitation;
    }

    public List<Invitation> afficher() {

        List<Invitation> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM invitation where date < SELECT  ";
            PreparedStatement st = cnx.prepareStatement(req);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                list.add(new Invitation(res.getInt(2), res.getInt(3), true));
            }
            System.out.println("Invitations récuperées");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

}
