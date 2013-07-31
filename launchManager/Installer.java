/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.SpaceEngineDatabase.launchManager;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.SwingUtilities;
import org.openide.modules.ModuleInstall;
import org.openide.windows.WindowManager;

public class Installer extends ModuleInstall {

    @Override
    public void restored() {
        SwingUtilities.invokeLater(new Runnable() {
        @Override
            public void run() {
            Frame f = WindowManager.getDefault().getMainWindow();
            f.addWindowListener(new Listener());
        }
    });
}

//Listener for setting size, because the size is reset after installation:
public class Listener extends WindowAdapter {
    @Override
    public void windowActivated(WindowEvent event) {
        Frame f = WindowManager.getDefault().getMainWindow();
        f.setSize(1600, 1024);
    }
}
}
