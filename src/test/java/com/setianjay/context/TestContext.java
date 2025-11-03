package com.setianjay.context;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class TestContext {
    @Setter
    @Getter
    private WebDriver driver;
    private final Map<String, Object> data;

    public TestContext() {
        this.data = new HashMap<>();
    }

    // ===== Map methods =====
    public Object get(String key) {
        return data.get(key);
    }

    public void set(String key, Object value) {
        data.put(key, value);
    }
}
