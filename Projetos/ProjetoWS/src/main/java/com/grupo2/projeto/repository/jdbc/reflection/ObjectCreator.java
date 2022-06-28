package com.grupo2.projeto.repository.jdbc.reflection;

import com.grupo2.projeto.dto.OrganizacaoDTO;
import com.grupo2.projeto.model.JDBCTable;
import org.springframework.expression.spel.support.ReflectionHelper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectCreator
{/*
    public static <T extends JDBCTable> void populateObject(T objeto, List<Object> values) throws IllegalAccessException
    {
        System.out.println("populate");
        for (int i = 0; i < values.size(); i++)
        {
            if (values.get(i).getClass().isAssignableFrom(BigDecimal.class))
            {
                System.out.println(values.get(i).toString());

                //Long val = Long.parseLong((values.get(i)).toString());
                //Long val = Long.valueOf((values.get(i)).toString());
                Long val = ((BigDecimal)values.get(i)).longValue();

                Field f = objeto.getClass().getDeclaredFields()[i];

                f.setAccessible(true);

                f.setLong(objeto, val);
            } else
            {
                String val = values.get(i).toString();

                Field f = objeto.getClass().getDeclaredFields()[i];

                f.setAccessible(true);

                f.set(objeto, val);
            }
        }
    }
*/
    public static <T extends JDBCTable> T createObject(Class<T> className, List<Object> values) throws IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException
    {
        //Preencher valores
        List<Object> listValues = new ArrayList<>();

        for (int i = 0; i < values.size(); i++)
        {
            if (values.get(i).getClass().isAssignableFrom(BigDecimal.class))
            {
                Long val = Long.parseLong((values.get(i)).toString());
                listValues.add(val);

            }
            listValues.add(values.get(i));
        }

        //Array dos atributos
        Object[] atributesValues = new Object[]{1L, "OLA", 1};
       /* Object[] atributesValues = new Object[listValues.size()];

        for (int i = 0; i < values.size(); i++)
        {
            atributesValues[i] = listValues.get(i);
        }*/

        //Array dos argumentos do construtor
        Class<?>[] cArgs = new Class[atributesValues.length];

        for (int i = 0; i < atributesValues.length; i++) {
            cArgs[i] = atributesValues[i].getClass();
        }

        return className.getConstructor(cArgs).newInstance(atributesValues);
    }


}
