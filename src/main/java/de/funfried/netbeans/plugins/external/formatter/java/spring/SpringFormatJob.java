/*
 * Copyright (c) 2020 bahlef.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * Contributors:
 * bahlef - initial API and implementation and/or initial documentation
 */
package de.funfried.netbeans.plugins.external.formatter.java.spring;

import java.util.SortedSet;
import java.util.prefs.Preferences;

import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import org.apache.commons.lang3.tuple.Pair;
import org.netbeans.editor.BaseDocument;
import org.openide.awt.NotificationDisplayer;
import org.openide.awt.StatusDisplayer;

import de.funfried.netbeans.plugins.external.formatter.AbstractFormatJob;
import de.funfried.netbeans.plugins.external.formatter.exceptions.FormattingFailedException;
import de.funfried.netbeans.plugins.external.formatter.ui.Icons;
import de.funfried.netbeans.plugins.external.formatter.ui.options.Settings;

/**
 * Spring formatter implementation of the {@link AbstractFormatJob} to
 * format a given document using the {@link SpringJavaFormatterWrapper}.
 *
 * @author bahlef
 */
class SpringFormatJob extends AbstractFormatJob {
	/** The {@link SpringJavaFormatterWrapper} implementation. */
	private final SpringJavaFormatterWrapper formatter;

	/**
	 * Package private constructor to create a new instance of {@link SpringFormatJob}.
	 *
	 * @param document        the {@link StyledDocument} which sould be formatted
	 * @param formatter       the {@link SpringJavaFormatterWrapper} to use
	 * @param changedElements the ranges which should be formatted
	 */
	SpringFormatJob(StyledDocument document, SpringJavaFormatterWrapper formatter, SortedSet<Pair<Integer, Integer>> changedElements) {
		super(document, changedElements);

		this.formatter = formatter;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void format() throws BadLocationException {
		Preferences pref = Settings.getActivePreferences(document);

		String lineFeedSetting = pref.get(SpringJavaFormatterSettings.LINEFEED, "");
		String lineFeed = Settings.getLineFeed(lineFeedSetting, System.getProperty("line.separator"));

		//save with configured linefeed
		if (null != lineFeed) {
			document.putProperty(BaseDocument.READ_LINE_SEPARATOR_PROP, lineFeed);
			document.putProperty(BaseDocument.WRITE_LINE_SEPARATOR_PROP, lineFeed);
		}

		String code = getCode();

		SortedSet<Pair<Integer, Integer>> regions = getFormatableSections(code);

		try {
			String formattedContent = formatter.format(code, lineFeed, regions);
			if (setFormattedCode(code, formattedContent)) {
				SwingUtilities.invokeLater(() -> {
					if (pref.getBoolean(Settings.SHOW_NOTIFICATIONS, false)) {
						NotificationDisplayer.getDefault().notify("Format using Spring formatter", Icons.ICON_SPRING, null, null);
					}

					StatusDisplayer.getDefault().setStatusText("Format using Spring formatter");
				});
			}
		} catch (FormattingFailedException ex) {
			SwingUtilities.invokeLater(() -> {
				StatusDisplayer.getDefault().setStatusText("Failed to format using Spring formatter: " + ex.getMessage());
			});

			throw ex;
		}
	}
}
