package com.sanker.service.utils;

import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.dialect.SQLServerDialect;


public class CustomSQLServerDialect extends SQLServerDialect {

	@SuppressWarnings("deprecation")
	public CustomSQLServerDialect(){
		this.registerHibernateType(Types.NVARCHAR, Hibernate.STRING.getName());
		this.registerColumnType(-9, "nvarchar");
	}
}
