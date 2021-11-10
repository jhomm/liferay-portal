/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.search.experiences.web.internal.blueprint.admin.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.display.context.SearchContainerManagementToolbarDisplayContext;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemBuilder;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.ViewTypeItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.ViewTypeItemList;
import com.liferay.petra.portlet.url.builder.PortletURLBuilder;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;
import java.util.Objects;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Petteri Karttunen
 */
public abstract class BaseManagementToolbarDisplayContext
	extends SearchContainerManagementToolbarDisplayContext {

	public BaseManagementToolbarDisplayContext(
		String displayStyle, HttpServletRequest httpServletRequest,
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse,
		SearchContainer<?> searchContainer) {

		super(
			httpServletRequest, liferayPortletRequest, liferayPortletResponse,
			searchContainer);

		this.displayStyle = displayStyle;

		themeDisplay = (ThemeDisplay)liferayPortletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	@Override
	public String getClearResultsURL() {
		return PortletURLBuilder.create(
			getPortletURL()
		).setMVCRenderCommandName(
			getMVCRenderCommandName()
		).setKeywords(
			StringPool.BLANK
		).setTabs1(
			ParamUtil.getString(liferayPortletRequest, "tabs1", "sxpBlueprints")
		).setParameter(
			"orderByCol", getOrderByCol()
		).setParameter(
			"orderByType", getOrderByCol()
		).buildString();
	}

	@Override
	public String getDefaultEventHandler() {
		return "BASE_MANAGEMENT_TOOLBAR_DEFAULT_EVENT_HANDLER";
	}

	@Override
	public String getSearchActionURL() {
		return PortletURLBuilder.create(
			getPortletURL()
		).setMVCRenderCommandName(
			getMVCRenderCommandName()
		).setTabs1(
			ParamUtil.getString(liferayPortletRequest, "tabs1", "sxpBlueprints")
		).setParameter(
			"orderByCol", getOrderByCol()
		).setParameter(
			"orderByType", getOrderByCol()
		).buildString();
	}

	@Override
	public List<ViewTypeItem> getViewTypeItems() {
		PortletURL portletURL = PortletURLBuilder.createRenderURL(
			liferayPortletResponse
		).setMVCRenderCommandName(
			getMVCRenderCommandName()
		).setTabs1(
			ParamUtil.getString(liferayPortletRequest, "tabs1", "sxpBlueprints")
		).setParameter(
			"orderByCol", searchContainer.getOrderByCol()
		).setParameter(
			"orderByCol", searchContainer.getOrderByCol()
		).build();

		return new ViewTypeItemList(portletURL, displayStyle) {
			{
				addListViewTypeItem();

				addTableViewTypeItem();
			}
		};
	}

	protected String createActionURL(String actionName, String cmd) {
		return PortletURLBuilder.createActionURL(
			liferayPortletResponse
		).setActionName(
			actionName
		).setCMD(
			() -> {
				if (!Validator.isBlank(cmd)) {
					return Constants.ADD;
				}

				return null;
			}
		).setRedirect(
			currentURLObj
		).setTabs1(
			ParamUtil.getString(liferayPortletRequest, "tabs1", "sxpBlueprints")
		).buildString();
	}

	protected PortletURL getCurrentSortingURL() {
		return PortletURLBuilder.create(
			getPortletURL()
		).setMVCRenderCommandName(
			getMVCRenderCommandName()
		).setTabs1(
			ParamUtil.getString(liferayPortletRequest, "tabs1", "sxpBlueprints")
		).setParameter(
			SearchContainer.DEFAULT_CUR_PARAM, "0"
		).build();
	}

	protected abstract String getMVCRenderCommandName();

	@Override
	protected List<DropdownItem> getOrderByDropdownItems() {
		return DropdownItemList.of(
			DropdownItemBuilder.setActive(
				Objects.equals(getOrderByCol(), Field.TITLE)
			).setHref(
				getCurrentSortingURL(), "orderByCol", Field.TITLE
			).setLabel(
				LanguageUtil.get(httpServletRequest, "title")
			).build(),
			DropdownItemBuilder.setActive(
				Objects.equals(getOrderByCol(), Field.CREATE_DATE)
			).setHref(
				getCurrentSortingURL(), "orderByCol", Field.CREATE_DATE
			).setLabel(
				LanguageUtil.get(httpServletRequest, "created")
			).build(),
			DropdownItemBuilder.setActive(
				Objects.equals(getOrderByCol(), Field.MODIFIED_DATE)
			).setHref(
				getCurrentSortingURL(), "orderByCol", Field.MODIFIED_DATE
			).setLabel(
				LanguageUtil.get(httpServletRequest, "modified")
			).build());
	}

	protected final String displayStyle;
	protected final ThemeDisplay themeDisplay;

}