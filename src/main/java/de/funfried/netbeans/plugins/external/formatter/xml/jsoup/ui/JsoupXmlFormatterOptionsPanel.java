/*
 * Copyright (c) 2020 bahlef.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * Contributors:
 * bahlef - initial API and implementation and/or initial documentation
 */
package de.funfried.netbeans.plugins.external.formatter.xml.jsoup.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.commons.lang3.StringUtils;
import org.openide.awt.Mnemonics;
import org.openide.util.NbBundle;

import de.funfried.netbeans.plugins.external.formatter.ui.options.AbstractFormatterOptionsPanel;
import de.funfried.netbeans.plugins.external.formatter.xml.jsoup.JsoupXmlFormatterSettings;

/**
 *
 * @author bahlef
 */
public class JsoupXmlFormatterOptionsPanel extends AbstractFormatterOptionsPanel {
	/** Creates new form {@link JsoupXmlFormatterOptionsPanel}. */
	public JsoupXmlFormatterOptionsPanel() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        linefeedLbl = new JLabel();
        linefeedCmbBox = new JComboBox<>();
        prettyPrintChkBox = new JCheckBox();
        outlineChkBox = new JCheckBox();
        indentSizeLbl = new JLabel();
        indentSizeSpn = new JSpinner();

        Mnemonics.setLocalizedText(linefeedLbl, NbBundle.getMessage(JsoupXmlFormatterOptionsPanel.class, "JsoupXmlFormatterOptionsPanel.linefeedLbl.text")); // NOI18N

        linefeedCmbBox.setModel(new DefaultComboBoxModel<>(new String[] { "System", "\\n", "\\r\\n", "\\r" }));
        linefeedCmbBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                linefeedCmbBoxActionPerformed(evt);
            }
        });

        prettyPrintChkBox.setSelected(true);
        Mnemonics.setLocalizedText(prettyPrintChkBox, NbBundle.getMessage(JsoupXmlFormatterOptionsPanel.class, "JsoupXmlFormatterOptionsPanel.prettyPrintChkBox.text")); // NOI18N
        prettyPrintChkBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                prettyPrintChkBoxActionPerformed(evt);
            }
        });

        Mnemonics.setLocalizedText(outlineChkBox, NbBundle.getMessage(JsoupXmlFormatterOptionsPanel.class, "JsoupXmlFormatterOptionsPanel.outlineChkBox.text")); // NOI18N
        outlineChkBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                outlineChkBoxActionPerformed(evt);
            }
        });

        Mnemonics.setLocalizedText(indentSizeLbl, NbBundle.getMessage(JsoupXmlFormatterOptionsPanel.class, "JsoupXmlFormatterOptionsPanel.indentSizeLbl.text")); // NOI18N

        indentSizeSpn.setModel(new SpinnerNumberModel(1, 0, null, 1));
        indentSizeSpn.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                indentSizeSpnStateChanged(evt);
            }
        });

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(prettyPrintChkBox)
                            .addComponent(outlineChkBox))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(indentSizeLbl))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(linefeedLbl)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(indentSizeSpn, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
                    .addComponent(linefeedCmbBox, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(indentSizeLbl)
                            .addComponent(indentSizeSpn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(linefeedCmbBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(linefeedLbl)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(prettyPrintChkBox)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(outlineChkBox)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void prettyPrintChkBoxActionPerformed(ActionEvent evt) {//GEN-FIRST:event_prettyPrintChkBoxActionPerformed
        fireChangedListener();
    }//GEN-LAST:event_prettyPrintChkBoxActionPerformed

    private void outlineChkBoxActionPerformed(ActionEvent evt) {//GEN-FIRST:event_outlineChkBoxActionPerformed
        fireChangedListener();
    }//GEN-LAST:event_outlineChkBoxActionPerformed

    private void indentSizeSpnStateChanged(ChangeEvent evt) {//GEN-FIRST:event_indentSizeSpnStateChanged
        fireChangedListener();
    }//GEN-LAST:event_indentSizeSpnStateChanged

    private void linefeedCmbBoxActionPerformed(ActionEvent evt) {//GEN-FIRST:event_linefeedCmbBoxActionPerformed
        fireChangedListener();
    }//GEN-LAST:event_linefeedCmbBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel indentSizeLbl;
    private JSpinner indentSizeSpn;
    private JComboBox<String> linefeedCmbBox;
    private JLabel linefeedLbl;
    private JCheckBox outlineChkBox;
    private JCheckBox prettyPrintChkBox;
    // End of variables declaration//GEN-END:variables

	private String getLinefeed() {
		if (0 == linefeedCmbBox.getSelectedIndex()) {
			return "";
		}
		return linefeedCmbBox.getSelectedItem().toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void load(Preferences preferences) {
		boolean prettyPrint = preferences.getBoolean(JsoupXmlFormatterSettings.PRETTY_PRINT, true);
		boolean outline = preferences.getBoolean(JsoupXmlFormatterSettings.OUTLINE, false);
		int indentSize = preferences.getInt(JsoupXmlFormatterSettings.INDENT_SIZE, 1);
		String lineFeed = preferences.get(JsoupXmlFormatterSettings.LINEFEED, "");

		prettyPrintChkBox.setSelected(prettyPrint);
		outlineChkBox.setSelected(outline);
		indentSizeSpn.setValue(indentSize);

		if (StringUtils.isBlank(lineFeed)) {
			//default = system-dependend LF
			linefeedCmbBox.setSelectedIndex(0);
		} else {
			linefeedCmbBox.setSelectedItem(lineFeed);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void store(Preferences preferences) {
		preferences.putBoolean(JsoupXmlFormatterSettings.PRETTY_PRINT, prettyPrintChkBox.isSelected());
		preferences.putBoolean(JsoupXmlFormatterSettings.OUTLINE, outlineChkBox.isSelected());
		preferences.putInt(JsoupXmlFormatterSettings.INDENT_SIZE, (int) indentSizeSpn.getValue());
		preferences.put(JsoupXmlFormatterSettings.LINEFEED, getLinefeed());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean valid() {
		return true;
	}
}
