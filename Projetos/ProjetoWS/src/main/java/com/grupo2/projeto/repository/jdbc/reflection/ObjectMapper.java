package com.grupo2.projeto.repository.jdbc.reflection;

import com.grupo2.projeto.model.JDBCTable;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ObjectMapper
{
    public <T extends JDBCTable> T mapToObject(Map<String, Object> map, Class<T> finalObject) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        ArrayList<Object> mp = (ArrayList<Object>) map.get("return");
        LinkedCaseInsensitiveMap<Object> obj1 = (LinkedCaseInsensitiveMap<Object>) mp.get(0);

        T object = ObjectCreator.createObject(finalObject, obj1.values().stream().toList());

        return object;
    }

    public <T extends JDBCTable> List<T> mapToObjectList(Map<String, Object> map, Class<T> finalObject) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        ArrayList<Object> mp = (ArrayList<Object>) map.get("return");

        ArrayList<T> list = new ArrayList<>();

        for(int i = 0; i < mp.size();i++)
        {
            LinkedCaseInsensitiveMap<Object> obj1 = (LinkedCaseInsensitiveMap<Object>) mp.get(i);

            list.add(ObjectCreator.createObject(finalObject, obj1.values().stream().toList()));
        }

        return list;
    }
}
