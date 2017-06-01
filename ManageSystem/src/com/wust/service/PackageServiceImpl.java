package com.wust.service;

import com.wust.dao.PackageDao;
import com.wust.domain.Package;
import com.wust.factory.BasicFactory;

public class PackageServiceImpl implements PackageService {
	PackageDao dao = BasicFactory.getFactory().getInstance(PackageDao.class);
	@Override
	public Package getPackageNum(String format) {
		Package Package = dao.getPackageNum(format);
		return Package;
	}

}
