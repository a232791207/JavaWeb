package com.sanker.service.utils;

import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQLDialect;

public class MySQLDialectExt extends MySQLDialect {

	@SuppressWarnings("deprecation")
	public MySQLDialectExt(){
		super();
		registerHibernateType(-1, Hibernate.TEXT.getName());
	}
}
