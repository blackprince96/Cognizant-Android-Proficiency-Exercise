package com.alok.cogz.db;

/**
 * This class mimics the property of Database, exposing a dummy Page Data object which holds the cached value of page
 */
public class DummyPageDatabase {
    private DummyPageDao dummyPageDao;
    private static DummyPageDatabase instance;

    public synchronized static DummyPageDatabase getInstance() {
        if (instance == null) {
            instance = new DummyPageDatabase();
        }
        return instance;
    }

    private DummyPageDatabase() {
        dummyPageDao = new DummyPageDao();
    }

    public DummyPageDao getDummyPageDao() {
        return dummyPageDao;
    }
}
