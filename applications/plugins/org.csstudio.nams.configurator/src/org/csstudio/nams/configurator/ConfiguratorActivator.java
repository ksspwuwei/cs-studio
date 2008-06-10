package org.csstudio.nams.configurator;

import org.csstudio.ams.configurationStoreService.declaration.ConfigurationEditingStoreService;
import org.csstudio.ams.configurationStoreService.declaration.ConfigurationStoreService;
import org.csstudio.ams.service.logging.declaration.Logger;
import org.csstudio.nams.common.activatorUtils.AbstractBundleActivator;
import org.csstudio.nams.common.activatorUtils.OSGiBundleActivationMethod;
import org.csstudio.nams.common.activatorUtils.OSGiBundleDeactivationMethod;
import org.csstudio.nams.common.activatorUtils.OSGiService;
import org.csstudio.nams.common.activatorUtils.Required;
import org.csstudio.nams.configurator.treeviewer.ConfigurationTreeView;
import org.osgi.framework.BundleActivator;

/**
 * The activator class controls the bundles life cycle
 */
public class ConfiguratorActivator extends AbstractBundleActivator implements
		BundleActivator {

	/**
	 * The plug-in ID
	 */
	public static final String PLUGIN_ID = "org.csstudio.nams.configurator";

	/**
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	@OSGiBundleActivationMethod
	public void bundleStart(
			@OSGiService @Required ConfigurationEditingStoreService editingStoreService,
			@OSGiService @Required ConfigurationStoreService storeService,
			@OSGiService @Required Logger logger
	) {
		// for debugging : Alle Services injected?
		if (editingStoreService != null && storeService != null
				&& logger != null) {
			logger.logInfoMessage(this, "activated...");
		}

		// Inject into extension classes.
		ConfigurationTreeView
				.staticInjectEditingStoreService(editingStoreService);
		ConfigurationTreeView.staticInjectStoreService(storeService);
		ConfigurationTreeView.staticInjectLogger(logger);
	}
	
	@OSGiBundleDeactivationMethod
	public void stopBundle(@OSGiService @Required Logger logger) throws Exception {
		logger.logInfoMessage(this, "Plugin " + PLUGIN_ID
				+ " stopped succesfully.");
	}
}
