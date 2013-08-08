/**
 * OpenFilePluginMenuAction.java
 * Created on 06.11.2005
 * (c) 2005 by "Wolschon Softwaredesign und Beratung".
 *
 *  Permission is granted to use, modify, publish and sub-license this code
 *  as specified in the contract. If nothing else is specified these rights
 *  are given non-exclusively with no restrictions solely to the contractor(s).
 *  If no specified otherwise I reserve the right to use, modify, publish and
 *  sub-license this code to other parties myself.
 *
 * Otherwise, this code is made available under GPLv3 or later.
 *
 * -----------------------------------------------------------
 * major Changes:
 *  06.11.2005 - initial version
 * ...
 *
 */
package biz.wolschon.finance.jgnucash.actions;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.java.plugin.registry.Extension;

import biz.wolschon.fileformats.gnucash.GnucashWritableFile;
import biz.wolschon.finance.jgnucash.JGnucash;
import biz.wolschon.finance.jgnucash.plugin.DataSourcePlugin;

/**
 * The action-listeners we use for the ImportMenu.
 */
public final class OpenFilePluginMenuAction implements ActionListener {
    /**
     * Our logger for debug- and error-output.
     */
    static final Log LOGGER = LogFactory.getLog(OpenFilePluginMenuAction.class);

    /**
     * Our JGnucash.java.
     * @see OpenFilePluginMenuAction
     */
    private final JGnucash myJGnucashEditor;
    /**
     * @param aPlugin The import-plugin.
     * @param aPluginName The name of the plugin
     * @param aGnucash TODO
     */
    public OpenFilePluginMenuAction(final JGnucash aGnucash, final Extension aPlugin, final String aPluginName) {
        super();
        myJGnucashEditor = aGnucash;
        ext = aPlugin;
        pluginName = aPluginName;
    }
    /**
     * The import-plugin.
     */
    private final Extension ext;

    /**
     * The name of the plugin.
     */
    private final String pluginName;

    @Override
    public void actionPerformed(final ActionEvent e) {
        try {

            // Activate plug-in that declares extension.
            myJGnucashEditor.getPluginManager().activatePlugin(ext.getDeclaringPluginDescriptor().getId());
            // Get plug-in class loader.
            ClassLoader classLoader = myJGnucashEditor.getPluginManager().getPluginClassLoader(
                                      ext.getDeclaringPluginDescriptor());
            // Load Tool class.
            Class toolCls = classLoader.loadClass(
                    ext.getParameter("class").valueAsString());
            // Create Tool instance.
            Object o = toolCls.newInstance();
            if (!(o instanceof DataSourcePlugin)) {
                LOGGER.error("Plugin '" + pluginName + "' does not implement DataSourcePlugin-interface.");
                JOptionPane.showMessageDialog(myJGnucashEditor, "Error",
                        "Plugin '" + pluginName + "' does not implement DataSourcePlugin-interface.",
                        JOptionPane.ERROR_MESSAGE);
                return;

            }
            DataSourcePlugin importer = (DataSourcePlugin) o;
            try {
                myJGnucashEditor.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                //workaround because of a deadlock in log4j-consoleAppender in the JPf-classloader
                Logger.getLogger("org.java.plugin.standard.StandardPluginClassLoader").removeAllAppenders();
                Logger.getLogger("org.java.plugin.standard.StandardPluginClassLoader").setLevel(Level.FATAL);
                GnucashWritableFile loadedFile = importer.loadFile();
                if (loadedFile != null) {
                    myJGnucashEditor.setWritableModel(loadedFile);
                }
            } catch (Exception e1) {
                LOGGER.error("Load via Plugin '" + pluginName + "' failed.", e1);
                JOptionPane.showMessageDialog(myJGnucashEditor, "Error",
                        "Load via Plugin '" + pluginName + "' failed.\n"
                        + "[" + e1.getClass().getName() + "]: " + e1.getMessage(),
                        JOptionPane.ERROR_MESSAGE);
            } finally {
                myJGnucashEditor.setCursor(Cursor.getDefaultCursor());
            }
        } catch (Exception e1) {
            LOGGER.error("Could not activate requested Loader-plugin '" + pluginName + "'.", e1);
            JOptionPane.showMessageDialog(myJGnucashEditor, "Error",
                    "Could not activate requested Loader-plugin '" + pluginName + "'.\n"
                    + "[" + e1.getClass().getName() + "]: " + e1.getMessage(),
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
