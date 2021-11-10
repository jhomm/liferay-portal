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

package com.liferay.search.experiences.internal.blueprint.highlight;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.search.highlight.FieldConfigBuilderFactory;
import com.liferay.portal.search.highlight.HighlightBuilder;
import com.liferay.portal.search.highlight.HighlightBuilderFactory;
import com.liferay.search.experiences.rest.dto.v1_0.Highlight;
import com.liferay.search.experiences.rest.dto.v1_0.HighlightField;

import java.util.Map;

/**
 * @author Petteri Karttunen
 */
public class HighlightConverter {

	public HighlightConverter(
		FieldConfigBuilderFactory fieldConfigBuilderFactory,
		HighlightBuilderFactory highlightBuilderFactory) {

		_fieldConfigBuilderFactory = fieldConfigBuilderFactory;
		_highlightBuilderFactory = highlightBuilderFactory;
	}

	public com.liferay.portal.search.highlight.Highlight toHighlight(
		Highlight highlight) {

		HighlightBuilder highlightBuilder = _highlightBuilderFactory.builder();

		Map<String, HighlightField> highlightFields = highlight.getFields();

		MapUtil.isNotEmptyForEach(
			highlightFields,
			(name, highlightField) -> highlightBuilder.addFieldConfig(
				_fieldConfigBuilderFactory.builder(
					name
				).fragmentOffset(
					highlightField.getFragment_offset()
				).fragmentSize(
					highlightField.getFragment_size()
				).numFragments(
					highlightField.getNumber_of_fragments()
				).build()));

		highlightBuilder.fragmentSize(
			highlight.getFragment_size()
		).highlighterType(
			highlight.getType()
		).numOfFragments(
			highlight.getNumber_of_fragments()
		).postTags(
			highlight.getPost_tags()
		).preTags(
			highlight.getPre_tags()
		).requireFieldMatch(
			highlight.getRequire_field_match()
		);

		return highlightBuilder.build();
	}

	public com.liferay.portal.search.highlight.Highlight toHighlight(
		JSONObject jsonObject) {

		if (jsonObject == null) {
			return null;
		}

		Highlight highlight = Highlight.toDTO(jsonObject.toString());

		return toHighlight(highlight);
	}

	private final FieldConfigBuilderFactory _fieldConfigBuilderFactory;
	private final HighlightBuilderFactory _highlightBuilderFactory;

}