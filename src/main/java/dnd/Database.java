package dnd;

import dnd.character.Hero;

import java.sql.*;

public class Database {
    private Connection connection;

    public static void main(String[] args) {
        Database database = new Database();
        database.loadDatabase();
    }

private void loadDatabase(){
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

    try {
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DnD", "invite", "boolean");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void addHeroes(Hero hero){
        //Connexion à la base de données
        //Chargement du driver
        this.loadDatabase();

    try {
        PreparedStatement preparedStatement = this.connection.prepareStatement("INSERT INTO Hero(Type, Name, Life, Strength, Equipement) VALUES(?, ?, ?, ?,?);");
        preparedStatement.setString(1, hero.getClass().getSimpleName());
        preparedStatement.setString(2, hero.getName());
        preparedStatement.setInt(3, hero.getLife());
        preparedStatement.setInt(4, hero.getStrength());
        preparedStatement.setString(5, "rien");

        preparedStatement.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }

}

}
