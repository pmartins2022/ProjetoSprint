package com.grupo2.projeto.repository.jdbc.reflection;

import com.grupo2.projeto.dto.OrganizacaoDTO;
import com.grupo2.projeto.model.JDBCTable;
import oracle.sql.TIMESTAMP;
import org.springframework.expression.spel.support.ReflectionHelper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectCreator
{
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

            else if (values.get(i).getClass().isAssignableFrom(Timestamp.class))
            {
                String val = LocalDate.parse((values.get(i)).toString().substring(0,10), DateTimeFormatter.ofPattern("yyyy-MM-dd")).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                listValues.add(val);
            }
            else
            {
                listValues.add(values.get(i));
            }
        }
        //Array dos atributos
        Object[] atributesValues = new Object[listValues.size()];

        for (int i = 0; i < values.size(); i++)
        {
            atributesValues[i] = listValues.get(i);
        }

        //Array dos argumentos do construtor
        Class<?>[] cArgs = new Class[atributesValues.length];

        for (int i = 0; i < atributesValues.length; i++) {
            cArgs[i] = atributesValues[i].getClass();
        }

        return className.getConstructor(cArgs).newInstance(atributesValues);
    }


}
