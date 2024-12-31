package org.example.bookstore.util;

import java.util.HashMap;

public class Dictionary extends HashMap<String, Object> {

    // static Dicyionaru
    // Example: A method to safely get a value as a specific type
    public <T> T getAs(String key, Class<T> type) {
        Object value = this.get(key);
        if (value == null) {
            return null;
        }
        return type.cast(value); // Cast the value to the desired type
    }
}
