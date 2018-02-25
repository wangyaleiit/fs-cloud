package com.fs.server;

import java.util.UUID;

public class BaseService {

	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}
}
