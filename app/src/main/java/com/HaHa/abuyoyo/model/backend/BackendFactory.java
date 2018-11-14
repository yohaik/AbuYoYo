package com.HaHa.abuyoyo.model.backend;

import com.HaHa.abuyoyo.model.datasource.Firebase_DBManager;

public class BackendFactory {

    private static Backend beckend = null;

    public static Backend getBeckend(){
        if(beckend == null){
            beckend = new Firebase_DBManager();
        }
        return beckend;
    }
}
