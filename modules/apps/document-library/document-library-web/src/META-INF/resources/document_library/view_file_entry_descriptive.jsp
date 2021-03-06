<%--
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
--%>

<%@ include file="/document_library/init.jsp" %>

<%
FileEntry fileEntry = (FileEntry)request.getAttribute("view_entries.jsp-fileEntry");

FileVersion fileVersion = fileEntry.getFileVersion();

FileVersion latestFileVersion = fileVersion;

if ((user.getUserId() == fileEntry.getUserId()) || permissionChecker.isContentReviewer(user.getCompanyId(), scopeGroupId) || DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.UPDATE)) {
	latestFileVersion = fileEntry.getLatestFileVersion();
}

FileShortcut fileShortcut = (FileShortcut)request.getAttribute("view_entries.jsp-fileShortcut");

PortletURL tempRowURL = (PortletURL)request.getAttribute("view_entries.jsp-tempRowURL");

long assetClassPK = 0;

if (!latestFileVersion.getVersion().equals(DLFileEntryConstants.VERSION_DEFAULT) && (latestFileVersion.getStatus() != WorkflowConstants.STATUS_APPROVED)) {
	assetClassPK = latestFileVersion.getFileVersionId();
}
else {
	assetClassPK = fileEntry.getFileEntryId();
}

String rowCheckerName = FileEntry.class.getSimpleName();
long rowCheckerId = fileEntry.getFileEntryId();

if (fileShortcut != null) {
	rowCheckerName = FileShortcut.class.getSimpleName();
	rowCheckerId = fileShortcut.getFileShortcutId();
}
%>

<liferay-ui:app-view-entry
	actionJsp="/document_library/file_entry_action.jsp"
	actionJspServletContext="<%= application %>"
	assetCategoryClassName="<%= DLFileEntryConstants.getClassName() %>"
	assetCategoryClassPK="<%= assetClassPK %>"
	assetTagClassName="<%= DLFileEntryConstants.getClassName() %>"
	assetTagClassPK="<%= assetClassPK %>"
	author="<%= latestFileVersion.getUserName() %>"
	createDate="<%= latestFileVersion.getCreateDate() %>"
	description="<%= latestFileVersion.getDescription() %>"
	displayStyle="descriptive"
	latestApprovedVersion="<%= fileVersion.getVersion().equals(DLFileEntryConstants.VERSION_DEFAULT) ? null : fileVersion.getVersion() %>"
	latestApprovedVersionAuthor="<%= fileVersion.getVersion().equals(DLFileEntryConstants.VERSION_DEFAULT) ? null : fileVersion.getUserName() %>"
	locked="<%= fileEntry.isCheckedOut() %>"
	modifiedDate="<%= latestFileVersion.getModifiedDate() %>"
	rowCheckerId="<%= String.valueOf(rowCheckerId) %>"
	rowCheckerName="<%= rowCheckerName %>"
	shortcut="<%= fileShortcut != null %>"
	showCheckbox="<%= DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.DELETE) || DLFileEntryPermission.contains(permissionChecker, fileEntry, ActionKeys.UPDATE) %>"
	status="<%= latestFileVersion.getStatus() %>"
	thumbnailDivStyle="<%= DLUtil.getThumbnailStyle(false, 9, 128, 128) %>"
	thumbnailSrc="<%= DLUtil.getThumbnailSrc(fileEntry, latestFileVersion, themeDisplay) %>"
	thumbnailStyle="<%= DLUtil.getThumbnailStyle(true, 0, 128, 128) %>"
	title="<%= latestFileVersion.getTitle() %>"
	url="<%= tempRowURL.toString() %>"
	version="<%= latestFileVersion.getVersion() %>"
/>