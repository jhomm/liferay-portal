/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.saml.persistence.internal.upgrade.v3_0_0.util;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * @author	  Brian Wing Shun Chan
 * @generated
 */
public class SamlSpSessionTable {

	public static final String TABLE_NAME = "SamlSpSession";

	public static final Object[][] TABLE_COLUMNS = {
		{"samlSpSessionId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"samlSpSessionKey", Types.VARCHAR}, {"assertionXml", Types.CLOB},
		{"jSessionId", Types.VARCHAR}, {"samlPeerBindingId", Types.BIGINT},
		{"sessionIndex", Types.VARCHAR}, {"terminated_", Types.BOOLEAN}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
new HashMap<String, Integer>();

static {
TABLE_COLUMNS_MAP.put("samlSpSessionId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);

TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);

TABLE_COLUMNS_MAP.put("samlSpSessionKey", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("assertionXml", Types.CLOB);

TABLE_COLUMNS_MAP.put("jSessionId", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("samlPeerBindingId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("sessionIndex", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("terminated_", Types.BOOLEAN);

}
	public static final String TABLE_SQL_CREATE =
"create table SamlSpSession (samlSpSessionId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,samlSpSessionKey VARCHAR(75) null,assertionXml TEXT null,jSessionId VARCHAR(200) null,samlPeerBindingId LONG,sessionIndex VARCHAR(75) null,terminated_ BOOLEAN)";

	public static final String TABLE_SQL_DROP = "drop table SamlSpSession";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create index IX_85F532ED on SamlSpSession (jSessionId[$COLUMN_LENGTH:200$])",
		"create unique index IX_C66E4319 on SamlSpSession (samlSpSessionKey[$COLUMN_LENGTH:75$])",
		"create index IX_2001B382 on SamlSpSession (sessionIndex[$COLUMN_LENGTH:75$])"
	};

}