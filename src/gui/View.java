package gui;

import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;

import org.javatuples.Pair;

public interface View {

    public List<Pair<List<Pair<String, JComponent>>, JPanel>> getPanelList();
    
}
