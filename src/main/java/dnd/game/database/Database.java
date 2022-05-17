package dnd.game.database;

import dnd.character.Hero;
import dnd.equipement.Equipement;
import dnd.equipement.Spell;
import dnd.equipement.Weapon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private Connection connection;

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
        //ResultSet heroId;
        try (PreparedStatement preparedStatement = this.connection.prepareStatement("INSERT INTO Hero(Type, Name, Life, Strength, Equipement) VALUES(?, ?, ?, ?,?);", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, hero.getClass().getSimpleName());
            preparedStatement.setString(2, hero.getName());
            preparedStatement.setInt(3, hero.getLife());
            preparedStatement.setInt(4, hero.getStrength());

            if (hero.getEquipement() != null) {
                preparedStatement.setString(5, hero.getEquipement().getClass().getSimpleName());
            } else {
                preparedStatement.setString(5, "rien");
            }
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    hero.setId(generatedKeys.getLong(1));
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            //heroId = preparedStatement.getGeneratedKeys();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateHero(Hero hero) {
        try (PreparedStatement preparedStatement = this.connection.prepareStatement("UPDATE Hero SET Type = ?, Name = ?, Life = ?, Strength = ?, Equipement = ? WHERE Id = ?;")) {
            preparedStatement.setString(1, hero.getClass().getSimpleName());
            preparedStatement.setString(2, hero.getName());
            preparedStatement.setInt(3, hero.getLife());
            preparedStatement.setInt(4, hero.getStrength());
            preparedStatement.setLong(6, hero.getId());

            if (hero.getEquipement() != null) {
                preparedStatement.setString(5, hero.getEquipement().getClass().getSimpleName());
            } else {
                preparedStatement.setString(5, "rien");
            }
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Hero> getHeroes() {
        List<Hero> heroes = new ArrayList<Hero>();
        Hero hero = null;

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM Hero;");

            while (result.next()) {
                Long id = result.getLong("Id");
                String type = result.getString("Type");
                String name = result.getString("Name");
                int life = result.getInt("Life");
                int strength = result.getInt("Strength");
                String equipement = result.getString("Equipement");
                Equipement heroEquipement = null;

                try {
                    Class typeClass = Class.forName("dnd.character.heros." + type);
                    hero = (Hero) typeClass.getDeclaredConstructor().newInstance();

                    if (equipement.equals("Club") || equipement.equals("Sword")) {
                        Class typeEquiepement = Class.forName("dnd.equipement.weapon." + equipement);
                        heroEquipement = (Weapon) typeEquiepement.getDeclaredConstructor().newInstance();
                    } else if (equipement.equals("Flash") || equipement.equals("FireBall")) {
                        Class typeEquiepement = Class.forName("dnd.equipement.spell." + equipement);
                        heroEquipement = (Spell) typeEquiepement.getDeclaredConstructor().newInstance();
                    }

                } catch (Exception e) {
                    System.out.println(e);
                }

                hero.setName(name);
                hero.setLife(life);
                hero.setStrength(strength);
                hero.setEquipement(heroEquipement);
                hero.setId(id);

                heroes.add(hero);
            }

            statement.close();
            result.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (heroes.size() == 0) {
            return null;
        } else {
            return heroes;
        }
    }

    public void closeDatabase() {
        try {
            if (this.connection != null)
                this.connection.close();
        } catch (SQLException ignore) {
        }
    }

}
