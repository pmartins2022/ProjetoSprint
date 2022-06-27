package com.grupo2.projeto.repository.jdbc;

import com.grupo2.projeto.model.annotations.IgnoreField;
import com.grupo2.projeto.model.JDBCTable;
import com.grupo2.projeto.model.annotations.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;

@Repository
public class GenericJDBCRepository<T extends JDBCTable>
{
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    private final T objeto;

    public GenericJDBCRepository(JdbcTemplate jdbcTemplate, T objeto)
    {
        this.jdbcTemplate = jdbcTemplate;
        this.objeto = objeto;
    }

    public static <T extends JDBCTable> GenericJDBCRepository<T> from(JdbcTemplate jdbcTemplate, T objeto)
    {
        return new GenericJDBCRepository<T>(jdbcTemplate,objeto);
    }

    public void save() throws IllegalAccessException
    {
        System.out.println("save");

        StringBuilder sb = new StringBuilder();

        sb.append("INSERT INTO ");

        sb.append(objeto.getClass().getAnnotation(Table.class).tableName());

        sb.append("(");

        for(Field f : objeto.getClass().getDeclaredFields())
        {
            if (!f.isAnnotationPresent(IgnoreField.class))
                sb.append(f.getName().toUpperCase()).append(",");
        }

        sb.deleteCharAt(sb.length()-1);

        sb.append(") VALUES (");

        for(Field f : objeto.getClass().getDeclaredFields())
        {
            if (!f.isAnnotationPresent(IgnoreField.class))
            {
                f.setAccessible(true);
                Object value = f.get(objeto);

                if (value.getClass().isAssignableFrom(String.class) || value.getClass().isEnum())
                {
                    sb.append("'").append(value.toString()).append("',");
                }
                else
                {
                    sb.append(value.toString()).append(",");
                }
            }
        }

        sb.deleteCharAt(sb.length()-1);

        sb.append(")");

        System.out.println(sb.toString());
        jdbcTemplate.update(sb.toString());

        System.out.println("Fim");
    }
}
