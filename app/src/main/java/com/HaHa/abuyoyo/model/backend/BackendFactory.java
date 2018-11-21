package com.HaHa.abuyoyo.model.backend;

import com.HaHa.abuyoyo.model.datasource.Firebase_DBManager;

public class BackendFactory {

    private static Backend backend = null;

    public static Backend getBackend(){
        if(backend == null){
            backend = new Firebase_DBManager();
        }
        return backend;
    }
}
