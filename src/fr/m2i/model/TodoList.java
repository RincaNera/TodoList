package fr.m2i.model;

import fr.m2i.database.DataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe de gestion d'une liste ToDo.
 *
 * @author Thomas Seiler
 */
public class TodoList {
    private final DataAccess db;

    private final String QUERY_SELECT_ALL = "SELECT * FROM todo";
    private final String INSERT = "INSERT INTO todo (titre, description, idUrgence) VALUES (?, ?, ?)";
    private final String DELETE_BY_ID = "DELETE FROM todo WHERE idTodo = ?";
    private final String DELETE_LAST = "DELETE FROM todo ORDER BY idTodo DESC LIMIT 1";
    private final String SELECT_BY_ID = "SELECT * FROM todo WHERE idTodo = ?";
    private final String SELECT_LAST = "SELECT * FROM todo ORDER BY idTodo DESC LIMIT 1";

    // Constructeur
    public TodoList() {
        this.db = DataAccess.getInstance();
    }

    /**
     * Méthode ajoutant un todo à la liste
     *
     * @param todo à ajouter
     */
    public void addTodo(Todo todo) {
        try (
                PreparedStatement statement = db.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        ) {
            statement.setString(1, todo.getTitre());
            statement.setString(2, todo.getDescription());
            statement.setInt(3, todo.getUrgence().ordinal() + 1);
            statement.executeUpdate();
            db.commit();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                todo.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            db.rollback();
            throw new RuntimeException(e);
        }
    }

    /**
     * Méthode supprimant un todo via son index
     *
     * @param index todo à supprimer.
     */
    public void removeTodoByIndex(int index) {
        try (
                PreparedStatement statement = db.getConnection().prepareStatement(DELETE_BY_ID);
        ) {
            statement.setInt(1, index);
            statement.executeUpdate();
            db.commit();
        } catch (SQLException e) {
            db.rollback();
            throw new RuntimeException(e);
        }
    }

    /**
     * Méthode supprimant le dernier ToDo ajouté.
     */
    public void removeLastTodo() {
        try (
                PreparedStatement statement = db.getConnection().prepareStatement(DELETE_LAST);
        ) {
            statement.executeUpdate();
            db.commit();
        } catch (SQLException e) {
            db.rollback();
            throw new RuntimeException(e);
        }
    }

    /**
     * Méthode affichant la liste des Todo (si il n'y a pas de todo, affiche "rien à faire".
     */
    public void showTodoList() {
        try (
                PreparedStatement statement = db.getConnection().prepareStatement(QUERY_SELECT_ALL);
        ) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Todo todo = createTodo(rs);
                System.out.println(todo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Méthode qui affiche le todo demandé via son index
     *
     * @param index index du todo voulu.
     */
    public void showTodoByIndex(int index) {
        try (
                PreparedStatement statement = db.getConnection().prepareStatement(SELECT_BY_ID);
        ) {
            statement.setInt(1, index);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Todo todo = createTodo(rs);
                System.out.println(todo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Affiche le dernier todo créé.
     */
    public void showLastTodo() {
        try (
                PreparedStatement statement = db.getConnection().prepareStatement(SELECT_LAST);
        ) {
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Todo todo = createTodo(rs);
                System.out.println(todo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Crée un todo à partir d'une entrée de base de données.
     * @param rs Une entrée de base de données
     * @return Un Todo
     * @throws SQLException
     */
    private Todo createTodo(ResultSet rs) throws SQLException {
        return new Todo(
                rs.getInt(1),
                Urgence.values()[rs.getInt(4) - 1],
                rs.getString(2),
                rs.getString(3)
        );
    }
}
