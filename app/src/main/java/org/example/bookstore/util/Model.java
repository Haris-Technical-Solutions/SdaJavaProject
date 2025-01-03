package org.example.bookstore.util;

import java.sql.*;
import java.util.*;
import java.io.InputStream; // Add this import for InputStream
import java.io.IOException;  // Add this import for IOException
import java.util.Properties; // Add this import for Properties
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import org.example.bookstore.util.DB;
import org.example.bookstore.util.Dictionary;
import java.lang.reflect.Field;

import java.lang.reflect.ParameterizedType;

public abstract class Model<T> {
    private Connection connection;
    private String table;
    private List<String> columns;
    private List<String> joins;
    private String whereClause;
    private String orWhereClause;
    private String limitClause;
    private String orderByClause;
    
    public Model() {
        Properties config = loadConfig();
        try {
            this.connection = DB.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Properties loadConfig() {
        Properties config = new Properties();
        try (FileInputStream input = new FileInputStream("config.properties")) {
            config.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return config;
    }

    public Connection getConnection() {
        return connection;
    }

    public Model<T> select(String... columns) {
        this.columns = Arrays.asList(columns);
        return this;
    }

    public Model<T> table(String table) {
        this.table = table;
        return this;
    }

    public Model<T> where(String column, String operator, String value) {
        if (whereClause == null) {
            whereClause = column + " " + operator + " '" + value + "'";
        } else {
            whereClause += " AND " + column + " " + operator + " '" + value + "'";
        }
        return this;
    }

    public Model<T> orWhere(String column, String operator , String value) {
        if (orWhereClause == null) {
            orWhereClause = column + " " + operator + " '" + value + "'";
        } else {
            orWhereClause += " OR " + column + " " + operator + " '" + value + "'";
        }
        return this;
    }

    public Model<T> join(String table, String column1, String operator, String column2) {
        if (joins == null) {
            joins = new ArrayList<>();
        }
        joins.add("JOIN " + table + " ON " + column1 + " " + operator + " " + column2);
        return this;
    }

    public Model<T> leftJoin(String table, String column1, String operator, String column2) {
        if (joins == null) {
            joins = new ArrayList<>();
        }
        joins.add("LEFT JOIN " + table + " ON " + column1 + " " + operator + " " + column2);
        return this;
    }

    public Model<T> rightJoin(String table, String column1, String operator, String column2) {
        if (joins == null) {
            joins = new ArrayList<>();
        }
        joins.add("RIGHT JOIN " + table + " ON " + column1 + " " + operator + " " + column2);
        return this;
    }

    public Model<T> orderBy(String column, String direction) {
        orderByClause = "ORDER BY " + column + " " + direction;
        return this;
    }

    public Model<T> limit(int limit) {
        limitClause = "LIMIT " + limit;
        return this;
    }

    // Fetch an object by ID (assuming the table has an `id` column)
    public T findById(long id) {
        where("id", String.valueOf(id), "=");
        List<T> results = get();
        return results.isEmpty() ? null : results.get(0);
    }

    // Fetch an object using a where condition
    public T findByCondition(String condition) {
        whereClause = condition; // Set the whereClause dynamically
        List<T> results = get();
        return results.isEmpty() ? null : results.get(0);
    }

    protected List<Map<String, Object>> getResultsFromDatabase() throws SQLException {
        String query = buildQuery();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        ResultSetMetaData metadata = resultSet.getMetaData();
        int columnCount = metadata.getColumnCount();
        List<Map<String, Object>> result = new ArrayList<>();
        
        while (resultSet.next()) {
            Map<String, Object> row = new HashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                row.put(metadata.getColumnName(i), resultSet.getObject(i));
            }
            result.add(row);
        }
        return result;
    }
    // protected List<Map<String, Object>> getResultsFromDatabase() {
    //     // Implementation to fetch data from the database
    //     return new ArrayList<>();
    // }

    @SuppressWarnings("unchecked")
    // public List<T> get() {
    //     List<T> models = new ArrayList<>();
    //     try {
    //         List<Map<String, Object>> results = getResultsFromDatabase();
    //         Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    //         for (Map<String, Object> result : results) {
    //             T model = clazz.getDeclaredConstructor().newInstance();
    //             for (Map.Entry<String, Object> entry : result.entrySet()) {
    //                 String fieldName = entry.getKey();
    //                 Object fieldValue = entry.getValue();
    //                 try {
    //                     clazz.getDeclaredField(fieldName).setAccessible(true);
    //                     clazz.getDeclaredField(fieldName).set(model, fieldValue);
    //                 } catch (NoSuchFieldException e) {
    //                     // Ignore fields that are not declared in the class
    //                 }
    //             }
    //             models.add(model);
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     return models;
    // }
    public List<T> get() {
        List<T> models = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM ").append(table);

        // Add WHERE clause if present
        if (whereClause != null) {
            query.append(" WHERE ").append(whereClause);
        }

        try (PreparedStatement statement = connection.prepareStatement(query.toString())) {
            ResultSet rs = statement.executeQuery();

            // Dynamically resolve the class type of T
            Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

            while (rs.next()) {
                T model = clazz.getDeclaredConstructor().newInstance();

                // Map the database columns to the object's fields
                for (Field field : clazz.getDeclaredFields()) {
                    field.setAccessible(true);
                    try {
                        Object value = rs.getObject(field.getName());
                        field.set(model, value);
                    } catch (SQLException | IllegalArgumentException e) {
                        // Ignore columns that do not match fields
                    }
                }

                models.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return models;
    }
    public List<T> findByWhereClause() {
        if (whereClause == null || whereClause.isEmpty()) {
            throw new IllegalStateException("Where clause is not set.");
        }
        return get(); // Reuse the existing `get()` method for retrieval.
    }




    // public static <T> List<T> get(Class<T> clazz) throws SQLException {
    //     try {
    //         Model<T> modelInstance = (Model<T>) clazz.getDeclaredConstructor().newInstance();
    //         return modelInstance.get();
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return new ArrayList<>();
    //     }
    // }


    private String buildQuery() {
        StringBuilder query = new StringBuilder("SELECT ");
        
        if (columns == null || columns.isEmpty()) {
            query.append("*");
        } else {
            query.append(String.join(", ", columns));
        }

        query.append(" FROM ").append(table);

        if (joins != null) {
            for (String join : joins) {
                query.append(" ").append(join);
            }
        }

        if (whereClause != null) {
            query.append(" WHERE ").append(whereClause);
        }

        if (orWhereClause != null) {
            query.append(" OR ").append(orWhereClause);
        }

        if (orderByClause != null) {
            query.append(" ").append(orderByClause);
        }

        if (limitClause != null) {
            query.append(" ").append(limitClause);
        }

        return query.toString();
    }

    public T create(Dictionary values) {
        StringBuilder query = new StringBuilder("INSERT INTO ").append(table).append(" (");

        // Add column names to the query
        String columnsStr = String.join(", ", values.keySet());
        query.append(columnsStr).append(") VALUES (");

        // Add placeholders for values
        String placeholders = String.join(", ", Collections.nCopies(values.size(), "?"));
        query.append(placeholders).append(")");

        try (PreparedStatement statement = connection.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS)) {
            // Set the values in the prepared statement
            int index = 1;
            for (Object value : values.values()) {
                statement.setObject(index++, value);
            }

            // Execute the insert query
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                // Retrieve the generated ID (if using auto-increment)
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        long generatedId = generatedKeys.getLong(1);
                        // Retrieve and return the created object using the generated ID
                        return findById(generatedId);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public T update(Dictionary values) {
        StringBuilder query = new StringBuilder("UPDATE ").append(table).append(" SET ");

        List<String> setClauses = new ArrayList<>();
        for (Map.Entry<String, Object> entry : values.entrySet()) {
            setClauses.add(entry.getKey() + " = ?");
        }
        query.append(String.join(", ", setClauses));

        if (whereClause != null) {
            query.append(" WHERE ").append(whereClause);
        }

        try (PreparedStatement statement = connection.prepareStatement(query.toString())) {
            int index = 1;
            for (Object value : values.values()) {
                statement.setObject(index++, value);
            }

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                List<T> results = findByWhereClause(); // Fetch updated results
                return results.isEmpty() ? null : results.get(0); // Return the first result or null if empty
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }



    // Helper method to retrieve the updated record
    private T findUpdatedRecord() {
        String query = "SELECT * FROM " + table;
        if (whereClause != null) {
            query += " WHERE " + whereClause;
        }

        try (Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()) {
                Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
                T updatedObject = clazz.getDeclaredConstructor().newInstance();

                // Map the result set to the object
                ResultSetMetaData metadata = resultSet.getMetaData();
                int columnCount = metadata.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metadata.getColumnName(i);
                    Object value = resultSet.getObject(i);

                    try {
                        clazz.getDeclaredField(columnName).setAccessible(true);
                        clazz.getDeclaredField(columnName).set(updatedObject, value);
                    } catch (NoSuchFieldException e) {
                        // Ignore fields not in the class
                    }
                }

                return updatedObject;
            }
        } catch (SQLException | ReflectiveOperationException e) {
            e.printStackTrace();
        }

        return null; // Return null if no record is found or an error occurs
    }


}
