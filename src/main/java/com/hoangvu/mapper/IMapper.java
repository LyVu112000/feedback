package com.hoangvu.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IMapper<T> {

    T map(ResultSet resultSet) throws SQLException;

    default List<T> mapList(ResultSet resultSet) {
        try {
            List<T> list = new ArrayList<>();
            while (resultSet.next()) {
                T t = map(resultSet);
                list.add(t);
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}


