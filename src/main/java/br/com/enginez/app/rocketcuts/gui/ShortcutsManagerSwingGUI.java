package br.com.enginez.app.rocketcuts.gui;

import br.com.enginez.app.rocketcuts.gui.mouse.MouseHandler;
import br.com.enginez.app.rocketcuts.gui.treeview.ShortcutsTreeViewMouseHandler;
import br.com.enginez.app.rocketcuts.model.shortcut.tree.ShortcutsTree;
import br.com.enginez.app.rocketcuts.model.shortcut.tree.item.Shortcut;
import br.com.enginez.app.rocketcuts.model.shortcut.tree.item.ShortcutDirectory;
import br.com.enginez.app.rocketcuts.model.shortcut.tree.item.ShortcutTreeItem;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.util.List;

public class ShortcutsManagerSwingGUI extends JFrame implements GUIHandler {

    private GUIHandler callerGuiHandler;
    private JFrame mainFrame = new JFrame("Shortcuts Manager");
    private ShortcutsTree tree;
    JTree jTree ;


    public ShortcutsManagerSwingGUI(GUIHandler callerGuiHandler) {
        super("Shortcuts Manager");
        this.callerGuiHandler = callerGuiHandler;
    }

    public void showGUI(ShortcutsTree tree) {
        this.tree = tree;
        JTree jTree = createJTree(tree);

        JScrollPane mainScrollPane = new JScrollPane();
        mainScrollPane.getViewport().add(jTree);

        this.mainFrame.getContentPane().setLayout(new BorderLayout());
        this.mainFrame.getContentPane().add("Center", mainScrollPane);

        this.mainFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.mainFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.mainFrame.setVisible(true);
    }

    private JTree createJTree(ShortcutsTree tree) {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Shortcuts");

        List<ShortcutTreeItem> shortcuts = tree.getItems();
        fillJTree(shortcuts, rootNode);
        jTree = new JTree(rootNode);
        jTree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);

        MouseHandler mouseHandler = MouseHandler.getInstance();
        jTree.addMouseListener(mouseHandler);
        mouseHandler.handle(jTree, new ShortcutsTreeViewMouseHandler(jTree, tree, this));

        return jTree;
    }

    private void fillJTree(List<ShortcutTreeItem> shortcutTreeItems, DefaultMutableTreeNode owner) {
        shortcutTreeItems.stream().forEach(item -> {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode();
            switch (item.getType()) {
                case DIRECTORY:
                    ShortcutDirectory directory = (ShortcutDirectory) item;
                    System.out.println(directory.getName());

                    node.setUserObject(directory);
                    fillJTree(directory.getItems(), node);
                    owner.add(node);
                    break;
                case SHORTCUT:
                    Shortcut shortcut = (Shortcut) item;
                    System.out.println(shortcut.getName());

                    node.setUserObject(shortcut);
                    owner.add(node);
                    break;
                case ACTION:
                    break;
            }
        });
    }

    @Override
    public void updateGUI() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                callerGuiHandler.updateGUI();
                DefaultTreeModel model = (DefaultTreeModel) jTree.getModel();
                model.reload();
            }
        });

    }

    @Override
    public void closeGUI() {
    }

    @Override
    public void hideGUI() {
    }

    @Override
    public void showGUI() {
    }
}
//
//class Renderer extends JLabel implements TreeCellRenderer {
//    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected,
//                                                  boolean expanded, boolean leaf, int row, boolean hasFocus) {
//        setText(value.toString() + "                   ");
//        return this;
//    }
//
//}
