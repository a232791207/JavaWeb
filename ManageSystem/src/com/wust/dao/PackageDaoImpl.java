package com.wust.dao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.wust.domain.Package;
import com.wust.util.DaoUtils;

public class PackageDaoImpl implements PackageDao{

	@Override
	public Package getPackageNum(String format) {
		String sql = "select * from package where format = ?";
		try{
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<Package>(Package.class), format);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
