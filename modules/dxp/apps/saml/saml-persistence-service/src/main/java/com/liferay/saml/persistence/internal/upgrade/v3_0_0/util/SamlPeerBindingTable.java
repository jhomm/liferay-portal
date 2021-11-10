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
public class SamlPeerBindingTable {

	public static final String TABLE_NAME = "SamlPeerBinding";

	public static final Object[][] TABLE_COLUMNS = {
		{"samlPeerBindingId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"createDate", Types.TIMESTAMP}, {"userId", Types.BIGINT},
		{"userName", Types.VARCHAR}, {"deleted", Types.BOOLEAN},
		{"samlNameIdFormat", Types.VARCHAR},
		{"samlNameIdNameQualifier", Types.VARCHAR},
		{"samlNameIdSpNameQualifier", Types.VARCHAR},
		{"samlNameIdSpProvidedId", Types.VARCHAR},
		{"samlNameIdValue", Types.VARCHAR}, {"samlPeerEntityId", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
new HashMap<String, Integer>();

static {
TABLE_COLUMNS_MAP.put("samlPeerBindingId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);

TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("deleted", Types.BOOLEAN);

TABLE_COLUMNS_MAP.put("samlNameIdFormat", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("samlNameIdNameQualifier", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("samlNameIdSpNameQualifier", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("samlNameIdSpProvidedId", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("samlNameIdValue", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("samlPeerEntityId", Types.VARCHAR);

}
	public static final String TABLE_SQL_CREATE =
"create table SamlPeerBinding (samlPeerBindingId LONG not null primary key,companyId LONG,createDate DATE null,userId LONG,userName VARCHAR(75) null,deleted BOOLEAN,samlNameIdFormat VARCHAR(75) null,samlNameIdNameQualifier VARCHAR(75) null,samlNameIdSpNameQualifier VARCHAR(75) null,samlNameIdSpProvidedId VARCHAR(75) null,samlNameIdValue VARCHAR(75) null,samlPeerEntityId VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table SamlPeerBinding";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
		"create index IX_E642E1AE on SamlPeerBinding (companyId, deleted, samlNameIdFormat[$COLUMN_LENGTH:75$], samlNameIdNameQualifier[$COLUMN_LENGTH:75$], samlNameIdValue[$COLUMN_LENGTH:75$], samlPeerEntityId[$COLUMN_LENGTH:75$])",
		"create index IX_622F5E2E on SamlPeerBinding (companyId, deleted, samlPeerEntityId[$COLUMN_LENGTH:75$], samlNameIdFormat[$COLUMN_LENGTH:75$], samlNameIdNameQualifier[$COLUMN_LENGTH:75$], samlNameIdValue[$COLUMN_LENGTH:75$])",
		"create index IX_54241963 on SamlPeerBinding (companyId, deleted, samlPeerEntityId[$COLUMN_LENGTH:75$], samlNameIdSpNameQualifier[$COLUMN_LENGTH:75$], samlNameIdSpProvidedId[$COLUMN_LENGTH:75$])"
	};

}