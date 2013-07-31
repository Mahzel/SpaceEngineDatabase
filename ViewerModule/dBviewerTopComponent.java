/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.SpaceEngineDatabase.ViewerModule;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.explorer.view.BeanTreeView;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.WindowManager;


/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//org.SpaceEngineDatabase.ViewerModule//dBviewer//EN",
        autostore = false)
@TopComponent.Description(
        preferredID = "dBviewerTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "properties", openAtStartup = true)
@ActionID(category = "Window", id = "org.SpaceEngineDatabase.ViewerModule.dBviewerTopComponent")
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_dBviewerAction",
        preferredID = "dBviewerTopComponent")
@Messages({
    "CTL_dBviewerAction=dBviewer",
    "CTL_dBviewerTopComponent=Universe Explorer",
    "HINT_dBviewerTopComponent=This is a dBviewer window"
})
public final class dBviewerTopComponent extends TopComponent implements ExplorerManager.Provider {
    nodeViewerTopComponent nViewer;
    private final InstanceContent content = new InstanceContent();
    public dBviewerTopComponent() {
        initComponents();
        fFlags = new boolean[5];
        jButtonRaf = new javax.swing.JButton();
        associateLookup(ExplorerUtils.createLookup(mgr, getActionMap()));
        setName(Bundle.CTL_dBviewerTopComponent());
        setToolTipText(Bundle.HINT_dBviewerTopComponent());
        setLayout(new BorderLayout());
        add(new BeanTreeView(), BorderLayout.CENTER);
        add(jButtonRaf, BorderLayout.SOUTH);
        jButtonRaf.setText("Refresh");
        jButtonRaf.addActionListener(new java.awt.event.ActionListener(){
        public void actionPerformed(ActionEvent evt) {
                refreshAction(evt);
            }
        });
        noFilterTree();
    }
    javax.swing.JButton jButtonRaf;
    public boolean[] fFlags;
    public String[] fStrings;
    
    @Override
    public boolean canClose()
    {return false;}
    
    public void noFilterTree(){
        mgr.setRootContext(new AbstractNode(Children.create(new UniverseChildrenFactory(), true)));
        mgr.getRootContext().setName("Universe");
    }
    
    public void refresh()
    { TopComponent tcf = WindowManager.getDefault().findTopComponent("FiltersTopComponent");
       FiltersTopComponent ftc = (FiltersTopComponent)tcf;
       fFlags = ftc.fFlags;
       fStrings = ftc.fStrings;
       if(fFlags[5]){
        mgr.setRootContext(new AbstractNode(Children.create(new UniverseChildrenFactory(fFlags, fStrings), true)));
        mgr.getRootContext().setName("Universe");
       }
        else{noFilterTree();}
    }
    
    public void refreshAction(ActionEvent evt)  
    {  refresh();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        
    }

    @Override
    public void componentClosed() {
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }
    public final ExplorerManager mgr = new ExplorerManager();
    @Override
    public ExplorerManager getExplorerManager() {
    return mgr;
    }
}
