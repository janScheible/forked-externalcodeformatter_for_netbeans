/*
 * Copyright (c) 2013 markiewb.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * Contributors:
 * markiewb - initial API and implementation and/or initial documentation
 */
package de.funfried.netbeans.plugins.external.formatter.ui.customizer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;

import javax.swing.JComponent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectUtils;
import org.netbeans.modules.maven.api.NbMavenProject;
import org.netbeans.spi.project.ui.support.ProjectCustomizer;
import org.netbeans.spi.project.ui.support.ProjectCustomizer.Category;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.WeakListeners;

import de.funfried.netbeans.plugins.external.formatter.ui.Icons;
import de.funfried.netbeans.plugins.external.formatter.ui.options.ExternalFormatterPanel;

@NbBundle.Messages({ "LBL_Config=External Formatting" })
@ProjectCustomizer.CompositeCategoryProvider.Registrations({
		@ProjectCustomizer.CompositeCategoryProvider.Registration(category = "Formatting", projectType = "org-netbeans-modules-j2ee-clientproject", position = 1000),
		@ProjectCustomizer.CompositeCategoryProvider.Registration(category = "Formatting", projectType = "org-netbeans-modules-j2ee-ejbjarproject", position = 1000),
		@ProjectCustomizer.CompositeCategoryProvider.Registration(category = "Formatting", projectType = "org-netbeans-modules-java-j2seproject", position = 1000),
		@ProjectCustomizer.CompositeCategoryProvider.Registration(category = "Formatting", projectType = "org-netbeans-modules-j2ee-ejbjarproject", position = 1000),
		@ProjectCustomizer.CompositeCategoryProvider.Registration(category = "Formatting", projectType = "org-netbeans-modules-ant-freeform", position = 1000),
		@ProjectCustomizer.CompositeCategoryProvider.Registration(category = "Formatting", projectType = "org-netbeans-modules-web-project", position = 1000),
		@ProjectCustomizer.CompositeCategoryProvider.Registration(category = "Formatting", projectType = "org-netbeans-modules-maven/" + NbMavenProject.TYPE_OSGI, position = 1000),
		@ProjectCustomizer.CompositeCategoryProvider.Registration(category = "Formatting", projectType = "org-netbeans-modules-maven", position = 1000),
		@ProjectCustomizer.CompositeCategoryProvider.Registration(projectType = "org-netbeans-modules-gradle", position = 1000),
		@ProjectCustomizer.CompositeCategoryProvider.Registration(category = "Formatting", projectType = "org-netbeans-modules-apisupport-project", position = 1000)
})
public class ExternalFormatterCustomizerTab implements ProjectCustomizer.CompositeCategoryProvider {
	@Override
	public Category createCategory(Lookup lkp) {
		return ProjectCustomizer.Category.create("external-format", Bundle.LBL_Config(), ImageUtilities.loadImage(Icons.EXTERNAL_FORMATTER_ICON_PATH, false));
	}

	@Override
	public JComponent createComponent(final Category category, final Lookup lkp) {
		Preferences projectPreferences = ProjectUtils.getPreferences(lkp.lookup(Project.class), ExternalFormatterPanel.class, true);
		final ExternalFormatterPanel configPanel = new ExternalFormatterPanel(projectPreferences, true);
		final ProjectSpecificSettingsPanel projectSpecificSettingsPanel = new ProjectSpecificSettingsPanel(configPanel, projectPreferences);
		configPanel.load();
		projectSpecificSettingsPanel.load();

		category.setStoreListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				projectSpecificSettingsPanel.store();
				configPanel.store();
			}
		});

		ValidationListener listener = new ValidationListener(category, projectSpecificSettingsPanel);
		configPanel.addChangeListener(WeakListeners.change(listener, configPanel));
		projectSpecificSettingsPanel.addChangeListener(WeakListeners.change(listener, projectSpecificSettingsPanel));
		return projectSpecificSettingsPanel;
	}

	private static class ValidationListener implements ChangeListener {
		private final Category category;

		private final ProjectSpecificSettingsPanel projectSpecificPanel;

		private ValidationListener(Category category, ProjectSpecificSettingsPanel projectSpecificPanel) {
			this.category = category;
			this.projectSpecificPanel = projectSpecificPanel;
		}

		@Override
		public void stateChanged(ChangeEvent e) {
			category.setValid(projectSpecificPanel.valid());
		}
	}
}