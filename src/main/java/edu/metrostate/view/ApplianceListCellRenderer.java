package edu.metrostate.view;

import edu.metrostate.model.Appliance;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.UIManager;

public class ApplianceListCellRenderer extends DefaultListCellRenderer {
    public ApplianceListCellRenderer() {
    }

    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof Appliance appliance) {
            String var10001 = appliance.getApplianceType();
            this.setText(var10001 + " - " + appliance.getModel());
            this.setIcon(UIManager.getIcon("FileView.fileIcon"));
        }

        return this;
    }
}
