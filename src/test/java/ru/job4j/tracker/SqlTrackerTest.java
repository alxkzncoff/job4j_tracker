package ru.job4j.tracker;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class SqlTrackerTest {

    private static Connection connection;

    @BeforeClass
    public static void initConnection() {
        try (InputStream in = SqlTrackerTest.class.getClassLoader().getResourceAsStream(
                "test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterClass
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @After
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("delete from items")) {
            statement.execute();
        }
    }

    @Test
    public void whenCreateItem() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertThat(tracker.findAll().get(0), is(item));
    }

    @Test
    public void whenFindAll() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item1 = new Item("item1");
        Item item2 = new Item("item2");
        Item item3 = new Item("item3");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        assertThat(tracker.findAll(), is(List.of(item1, item2, item3)));
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertThat(tracker.findById(item.getId()), is(item));
    }

    @Test
    public void whenFindByIdNull() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        assertNull(tracker.findById(item.getId()));
    }

    @Test
    public void whenSaveItemAndFindByNameThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item1 = new Item("item1");
        Item item2 = new Item("item2");
        Item item3 = new Item("item1");
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        assertThat(tracker.findByName("item1"), is(List.of(item1, item3)));
    }

    @Test
    public void whenDeleteItem() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        tracker.delete(item.getId());
        assertThat(tracker.findById(item.getId()), is(nullValue()));
    }

    @Test
    public void whenReplaceItem() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item1");
        String replacedItem = "item2";
        tracker.add(item);
        tracker.replace(item.getId(), new Item(replacedItem));
        assertThat(tracker.findById(item.getId()).getName(), is(replacedItem));
    }
}