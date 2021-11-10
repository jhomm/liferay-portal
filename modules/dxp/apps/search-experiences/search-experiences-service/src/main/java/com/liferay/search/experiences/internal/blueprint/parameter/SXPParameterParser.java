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

package com.liferay.search.experiences.internal.blueprint.parameter;

import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.search.experiences.blueprint.parameter.DateSXPParameter;
import com.liferay.search.experiences.blueprint.parameter.SXPParameter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Petteri Karttunen
 */
public class SXPParameterParser {

	public static String parse(String json, SXPParameterData sxpParameterData) {
		if (json == null) {
			return null;
		}

		Set<SXPParameter> sxpParameters = sxpParameterData.getSXPParameters();

		if (!json.contains("${") || sxpParameters.isEmpty()) {
			return json;
		}

		for (SXPParameter sxpParameter : sxpParameterData.getSXPParameters()) {
			if (!sxpParameter.isTemplateVariable()) {
				continue;
			}

			json = _parse(json, sxpParameter);

			if (!json.contains("${")) {
				break;
			}
		}

		return json;
	}

	private static Map<String, String> _getOptions(String optionsString) {
		Map<String, String> options = new HashMap<>();

		if (Validator.isNull(optionsString)) {
			return options;
		}

		for (String string : optionsString.split(",")) {
			String[] array = string.split("=");

			if (array.length == 1) {
				options.put(array[0], null);
			}
			else {
				options.put(array[0], array[1]);
			}
		}

		return options;
	}

	private static String _parse(String json, SXPParameter sxpParameter) {
		String templateVariable = sxpParameter.getTemplateVariable();

		String parameterizedTemplateVariable =
			templateVariable.substring(0, templateVariable.length() - 1) + "|";

		if (json.contains(parameterizedTemplateVariable) &&
			(sxpParameter instanceof DateSXPParameter)) {

			int index = json.indexOf(parameterizedTemplateVariable);

			while (index >= 0) {
				String optionsString = json.substring(
					index + parameterizedTemplateVariable.length(),
					json.indexOf("}", index));

				String evaluatedToString = sxpParameter.evaluateToString(
					_getOptions(optionsString));

				json = _replace(
					json,
					StringBundler.concat(
						parameterizedTemplateVariable, optionsString, "}"),
					evaluatedToString);

				index = json.indexOf(parameterizedTemplateVariable, index);
			}
		}

		if (json.contains(templateVariable)) {
			String evaluatedToString = sxpParameter.evaluateToString(null);

			json = _replace(json, templateVariable, evaluatedToString);
		}

		return json;
	}

	private static String _replace(
		String json, String templateVariable, String evaluatedToString) {

		if (evaluatedToString.startsWith("[")) {
			return StringUtil.replace(
				json, StringUtil.quote(templateVariable, CharPool.QUOTE),
				evaluatedToString);
		}

		return StringUtil.replace(json, templateVariable, evaluatedToString);
	}

}