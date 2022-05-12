package dnd.game;

import dnd.character.Hero;

import java.sql.*;

public class Database {
    private Connection connection;
    Statement statement = null;
    ResultSet resultat = null;

    public static void main(String[] args) {
        Database database = new Database();
        database.loadDatabase();
    }

    public Database() {
        this.loadDatabase();
    }

    private void loadDatabase() {
        //Connexion à la base de données
        //Chargement du driver
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

    public void addHeroes(Hero hero) {

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement("INSERT INTO Hero(Type, Name, Life, Strength, Equipement) VALUES(?, ?, ?, ?,?);");
            preparedStatement.setString(1, hero.getClass().getSimpleName());
            preparedStatement.setString(2, hero.getName());
            preparedStatement.setInt(3, hero.getLife());
            preparedStatement.setInt(4, hero.getStrength());
            preparedStatement.setString(5, "rien");

            preparedStatement.executeUpdate();

            this.statement = preparedStatement;

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeDatabase() {
        try {
            if (this.resultat != null)
                this.resultat.close();
            if (this.statement != null)
                this.statement.close();
            if (this.connection != null)
                this.connection.close();
        } catch (SQLException ignore) {
        }
    }


}
