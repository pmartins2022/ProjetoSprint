package com.grupo2.projeto.model.annotations;

import com.grupo2.projeto.model.JDBCTable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DateConstraint
{
}
