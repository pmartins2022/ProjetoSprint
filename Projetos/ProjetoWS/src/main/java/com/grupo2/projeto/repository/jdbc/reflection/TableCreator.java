package com.grupo2.projeto.repository.jdbc.reflection;

import com.grupo2.projeto.model.JDBCTable;
import com.grupo2.projeto.model.annotations.ForeignKey;
import com.grupo2.projeto.model.annotations.PrimaryKey;
import com.grupo2.projeto.model.annotations.Table;
import com.grupo2.projeto.model.annotations.Unique;

import java.lang.reflect.Field;

public class TableCreator
{
    public static <T extends JDBCTable> String generateFromTable(Class<T> object) throws IllegalAccessException
    {
        StringBuilder sb = new StringBuilder();

        sb.append("CREATE TABLE ").append(object.getAnnotation(Table.class).tableName()).append("(\n");

        for(Field f : object.getDeclaredFields())
        {
            f.setAccessible(true);

            String type = null;

            try
            {
                type = typeFromObject(f.getType());
            }
            catch (IllegalArgumentException e)
            {
               throw new IllegalArgumentException("A VARIAVEL "+f.getName()+" NAO E DE UM TIPO PERMITIDO "+f.getType().toString());
            }

            sb.append(f.getName().toUpperCase()).append(" ").append(type);

            if (f.isAnnotationPresent(Unique.class))
            {
                sb.append(" UNIQUE");
            } else if (f.isAnnotationPresent(PrimaryKey.class))
            {
                if (f.getAnnotation(PrimaryKey.class).generated())
                {
                    sb.append(" GENERATED AS IDENTITY");
                }
            }
            else
            {
                sb.append(" NOT NULL");
            }

            sb.append(",\n");
        }

        for(Field f : object.getDeclaredFields())
        {
            f.setAccessible(true);

            if (f.isAnnotationPresent(PrimaryKey.class))
            {
                //CONSTRAINT PK_PROJETO_ID PRIMARY KEY (ID)
                sb.append("CONSTRAINT PK_").append(object.getAnnotation(Table.class).tableName().toUpperCase())
                        .append("_").append(f.getName()).append(" PRIMARY KEY (").append(f.getName()).append("),\n");
            }

            if(f.isAnnotationPresent(ForeignKey.class))
            {
                sb.append("CONSTRAINT FK_").append(object.getAnnotation(Table.class).tableName().toUpperCase()).append("_")
                        .append(f.getName()).append(" FOREIGN KEY (").append(f.getName()).append(") REFERENCES ")
                        .append(f.getAnnotation(ForeignKey.class).className().getAnnotation(Table.class).tableName())
                        .append(" (").append(f.getAnnotation(ForeignKey.class).fieldName()).append("),\n");
            }
        }

        sb.deleteCharAt(sb.lastIndexOf(","));

        sb.append(");");

        return sb.toString();
    }

    private static String typeFromObject(Class<?> o)
    {
        if (o.isAssignableFrom(String.class) || o.isEnum())
        {
            return "VARCHAR(100)";
        }

        if (o.isAssignableFrom(Integer.class) || o.isAssignableFrom(Long.class))
        {
            return "INTEGER";
        }

        if (o.isAssignableFrom(Float.class) || o.isAssignableFrom(Double.class))
        {
            return "NUMBER(10,3)";
        }

        throw new IllegalArgumentException();
    }
}
