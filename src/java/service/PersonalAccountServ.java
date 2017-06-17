/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Personalaccount;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nazrul
 */
public class PersonalAccountServ {
    
    static HashMap<Integer, Personalaccount> countryIdMap = getCountryIdMap();

    public Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/personalaccount", "root", "root");
            System.out.println("OKKKKKKKKK");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    public List<Map<String, String>> getAllEntries() {

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("select * from personalaccount");
            ResultSet rs = ps.executeQuery();

            ResultSetMetaData meta = rs.getMetaData();
            while (rs.next()) {
                Map map = new HashMap();
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    String key = meta.getColumnName(i);
                    String value = rs.getString(key);
                    map.put(key, value);
                }
                list.add(map);

            }
            System.out.println(list);
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public Personalaccount getEntry(int id) {
        Personalaccount pa = countryIdMap.get(id);

        if (pa == null) {
           // throw new CountryNotFoundException("Country with id " + id + " not found");
        }
        return pa;
    }

    public Personalaccount addEntry(Personalaccount pa) {
//        country.setId(getMaxId() + 1);
//        countryIdMap.put(country.getId(), country);
//        return country;
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("insert into personalaccount (type, des, amount, date)values(?,?,?,?)");
            ps.setString(1, pa.getType());
            ps.setString(2, pa.getDes());
            ps.setDouble(3, pa.getAmount());
            ps.setString(4, pa.getDate());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
        return pa;
    }

    public Personalaccount updateEntry(Personalaccount pa) {

        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("update personalaccount SET type=?, des=?, amount=?, date=? where id=?");
             ps.setString(1, pa.getType());
            ps.setString(2, pa.getDes());
            ps.setDouble(3, pa.getAmount());
            ps.setString(4, pa.getDate());

            ps.setInt(5, pa.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
        return pa;
    }

    public void deleteEntry(int id) {
//        countryIdMap.remove(id);
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("delete from personalaccount where id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static HashMap<Integer, Personalaccount> getCountryIdMap() {
        return countryIdMap;
    }

    // Utility method to get max id
    public static int getMaxId() {
        int max = 0;
        for (int id : countryIdMap.keySet()) {
            if (max <= id) {
                max = id;
            }

        }
        return max;
    }
    
    
}
