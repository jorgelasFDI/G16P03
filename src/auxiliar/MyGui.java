package auxiliar;

import java.util.Arrays;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.javatuples.Pair;

public class MyGui {
    
    public static JPanel addLabel(String label, JPanel panel) {
		if (panel == null) panel = new JPanel();
        panel.add(new JLabel(label));
        return panel;
    }

    public static JPanel addComponentPanel(JComponent box, String label, int margin, JPanel panel, JPanel parent) {
		return addComponentPanel(Arrays.asList(new Pair<>(label, box)), margin, panel, parent);
    }

	public static JPanel addComponentPanel(List<Pair<String, JComponent>> box, int margin, JPanel panel, JPanel parent) {
        if (panel == null) {
			panel = new JPanel();
    		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		}

    	for (Pair<String, JComponent> pair: box) {
            panel.add(Box.createVerticalStrut(margin));
			addLabel(pair.getValue0(), panel);
			panel.add(pair.getValue1());
		}
    	
		if (parent != null) parent.add(panel);
		return panel;
    }

    public static void addAllPanels(List<Pair<List<Pair<String, JComponent>>, JPanel>> panels, int inPanelMargin, int interPanelMargin, JPanel parent) {
        for (Pair<List<Pair<String, JComponent>>, JPanel> componentsPanel: panels) {
            JPanel panel = componentsPanel.getValue1();
            List<Pair<String, JComponent>> components = componentsPanel.getValue0();
            panel = addComponentPanel(components, inPanelMargin, panel, parent);
            panel.add(Box.createVerticalStrut(interPanelMargin));
        }
    }

}
