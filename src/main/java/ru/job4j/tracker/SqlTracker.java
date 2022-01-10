package ru.job4j.tracker;

import java.io.InputStream;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store, AutoCloseable {

    private Connection cn;

    public SqlTracker() { }

    public SqlTracker(Connection cn) {
        this.cn = cn;
    }

    /**
     * Метод инициализирует подключение к БД.
     */
    public void init() {
        try (InputStream in = SqlTracker.class.getResourceAsStream("/app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Вспомогательный метод для обработки ResultSet.
     * @param rs ResultSet.
     * @return Item.
     * @throws SQLException
     */
    private Item resultSetHandle(ResultSet rs) throws SQLException {
        return new Item(
                rs.getString("name"),
                rs.getInt("id"),
                rs.getTimestamp("created").toLocalDateTime());
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    /**
     * Метод добавляет запись в БД.
     * @param item добавляемая запись.
     * @return запись, которую добавили в БД.
     */
    @Override
    public Item add(Item item) {
        try (PreparedStatement ps = cn.prepareStatement(
                "insert into items(name, created) values (?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, item.getName());
            ps.setTimestamp(2, Timestamp.valueOf(item.getDateTime()));
            ps.execute();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    item.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    /**
     * Метод изменяет запись в БД.
     * @param id идентификационный номер записи, которую нужно заменить.
     * @param item запись, на которую меняем.
     * @return true если запись изменилась, иначе false.
     */
    @Override
    public boolean replace(int id, Item item) {
        boolean result = false;
        try (PreparedStatement ps = cn.prepareStatement(
                "update items set name = ?, created = ? where id = ?"
        )) {
            ps.setString(1, item.getName());
            ps.setTimestamp(2, Timestamp.valueOf(item.getDateTime()));
            ps.setInt(3, id);
            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод удаляет запись из БД.
     * @param id идентификационный номер записи, которую нужно удалить.
     * @return true если запись удалена, иначе false.
     */
    @Override
    public boolean delete(int id) {
        boolean result = false;
        try (PreparedStatement ps = cn.prepareStatement(
                "delete from items where id = ?"
        )) {
            ps.setInt(1, id);
            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Метод находит все записи БД.
     * @return список с записями БД.
     */
    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement ps = cn.prepareStatement(
                "select * from items"
        )) {
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    items.add(resultSetHandle(resultSet));
                }
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    /**
     * Метод ищет запись в БД по имени.
     * @param key имя записи.
     * @return список найденных записей.
     */
    @Override
    public List<Item> findByName(String key) {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement ps = cn.prepareStatement(
                "select * from items where name = ?"
        )) {
            ps.setString(1, key);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    items.add(resultSetHandle(resultSet));
                }
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    /**
     * Метод ищет записи в БД по идентификационному номеру
     * @param id идентификационный номер записи.
     * @return найденная запись.
     */
    @Override
    public Item findById(int id) {
        Item item = null;
        try (PreparedStatement ps = cn.prepareStatement(
                "select * from items where id = ?"
        )) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    item = resultSetHandle(resultSet);
                }
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }
}
