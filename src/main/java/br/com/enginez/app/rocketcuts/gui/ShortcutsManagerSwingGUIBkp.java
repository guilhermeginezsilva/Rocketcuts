//package br.com.enginez.app.rocketcuts.gui;
//
//import br.com.enginez.app.rocketcuts.RocketcutsApplication;
//import br.com.enginez.app.rocketcuts.model.shortcut.tree.ShortcutsTree;
//import br.com.enginez.app.rocketcuts.model.shortcut.tree.item.Shortcut;
//import br.com.enginez.app.rocketcuts.model.shortcut.tree.item.ShortcutDirectory;
//import br.com.enginez.app.rocketcuts.model.shortcut.tree.item.ShortcutTreeItem;
//
//import javax.swing.*;
//import javax.swing.tree.DefaultMutableTreeNode;
//import javax.swing.tree.TreePath;
//import javax.swing.tree.TreeSelectionModel;
//import java.awt.*;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.util.List;
//
//public class ShortcutsManagerSwingGUIBkp extends JFrame {
//
//    private RocketcutsApplication mainApplication;
//    private JFrame mainFrame = new JFrame("Shortcuts Manager");
//
//    private ShortcutsTree shortcutsTree;
//
////    JTree tree;
//
////    DefaultMutableTreeNode nba = new DefaultMutableTreeNode("National Basketball Association");
////
////    DefaultMutableTreeNode western = new DefaultMutableTreeNode("Western Conference");
////
////    DefaultMutableTreeNode pacific = new DefaultMutableTreeNode("Pacific Division Teams");
////
////
////    DefaultMutableTreeNode midwest = new DefaultMutableTreeNode("Midwest Division Teams");
////
////    DefaultMutableTreeNode denver = new DefaultMutableTreeNode("Denver");
////
////    DefaultMutableTreeNode eastern = new DefaultMutableTreeNode("Eastern Conference");
////
////    DefaultMutableTreeNode atlantic = new DefaultMutableTreeNode("Atlantic Division Teams");
////    DefaultMutableTreeNode central = new DefaultMutableTreeNode("Central Division Teams");
//
//    public ShortcutsManagerSwingGUIBkp(RocketcutsApplication mainApplication) {
//        super("Shortcuts Manager");
//        this.mainApplication =mainApplication;
////        this.shortcutsTree = tree;
////        nba.add(western);
////        nba.add(eastern);
////        western.add(pacific);
////        western.add(midwest);
////        eastern.add(atlantic);
////        eastern.add(central);
////        pacific.add(new DefaultMutableTreeNode("Los Angeles (Lakers)"));
////        pacific.add(new DefaultMutableTreeNode("Los Angeles (Clippers)"));
////        pacific.add(new DefaultMutableTreeNode("San Francisco"));
////        pacific.add(new DefaultMutableTreeNode("Seattle"));
////        pacific.add(new DefaultMutableTreeNode("Phoenix"));
////        pacific.add(new DefaultMutableTreeNode("Portland"));
////        pacific.add(new DefaultMutableTreeNode("Sacramento"));
////        midwest.add(new DefaultMutableTreeNode("Utah"));
////        midwest.add(new DefaultMutableTreeNode("San Antonio"));
////        midwest.add(new DefaultMutableTreeNode("Houston"));
////        midwest.add(new DefaultMutableTreeNode("Minnesota"));
////        midwest.add(new DefaultMutableTreeNode("Vancouver"));
////        midwest.add(new DefaultMutableTreeNode("Dallas"));
////        midwest.add(denver);
////        atlantic.add(new DefaultMutableTreeNode("Miami"));
////        atlantic.add(new DefaultMutableTreeNode("New York"));
////        atlantic.add(new DefaultMutableTreeNode("New Jersey"));
////        atlantic.add(new DefaultMutableTreeNode("Washington"));
////        atlantic.add(new DefaultMutableTreeNode("Orlando"));
////        atlantic.add(new DefaultMutableTreeNode("Boston"));
////        atlantic.add(new DefaultMutableTreeNode("Philadelphia"));
////        central.add(new DefaultMutableTreeNode("Chicago"));
////        central.add(new DefaultMutableTreeNode("Indiana"));
////        central.add(new DefaultMutableTreeNode("Charlotte"));
////        central.add(new DefaultMutableTreeNode("Atlanta"));
////        central.add(new DefaultMutableTreeNode("Cleveland"));
////        central.add(new DefaultMutableTreeNode("Detroit"));
////        central.add(new DefaultMutableTreeNode("Milwaukee"));
////        central.add(new DefaultMutableTreeNode("Toronto"));
////        tree = new JTree(nba);
//    }
//
//    public void showGUI(ShortcutsTree tree) {
//        JTree jTree = createJTree(tree);
//        JScrollPane mainScrollPane = new JScrollPane();
//        mainScrollPane.getViewport().add(jTree);
//
//        this.mainFrame.getContentPane().setLayout(new BorderLayout());
//        this.mainFrame.getContentPane().add("Center", mainScrollPane);
//
//        this.mainFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//        this.mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        this.mainFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
//        this.mainFrame.setVisible(true);
//    }
//
//    private JTree createJTree(ShortcutsTree tree) {
//        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Shortcuts");
//
//        List<ShortcutTreeItem> shortcuts = tree.getItems();
//        fillJTree(shortcuts, rootNode);
//        JTree jTree = new JTree(rootNode);
//        jTree.getSelectionModel().setSelectionMode
//                (TreeSelectionModel.SINGLE_TREE_SELECTION);
//
//        MouseListener ml = new MouseAdapter() {
//            public void mousePressed(MouseEvent e) {
//                int selRow = jTree.getRowForLocation(e.getX(), e.getY());
//                TreePath selPath = jTree.getPathForLocation(e.getX(), e.getY());
//
//                if(selRow != -1) {
//
//                    jTree.setSelectionRow(selRow);
//
//                    if(e.getButton() == MouseEvent.BUTTON3) {
////                        final JPopupMenu popupMenu = new JPopupMenu();
////
////                        JMenuItem addShortcut = new JMenuItem("Add Shortcut");
////                        addShortcut.setIcon(new ImageIcon(ImageUtil.createImage("icons8-link-100.png")));
////                        popupMenu.add(addShortcut);
////
////                        JMenuItem addDirectory = new JMenuItem("Add Directory");
////                        addDirectory.setIcon(new ImageIcon(ImageUtil.createImage("icons8-open-100.png")));
////                        popupMenu.add(addDirectory);
////
////                        popupMenu.add(new JPopupMenu.Separator());
////
////                        JMenuItem addDelete = new JMenuItem("Delete");
////                        addDelete.setIcon(new ImageIcon(ImageUtil.createImage("icons8-delete-100.png")));
////                        popupMenu.add(addDelete);
////
////                        popupMenu.show(e.getComponent(), e.getX(), e.getY());
//                    } else if(e.getClickCount() == 1) {
////                        mySingleClick(selRow, selPath);
//                    }
//                    else if(e.getClickCount() == 2) {
////                        myDoubleClick(selRow, selPath);
//                        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
//                                jTree.getLastSelectedPathComponent();
//                        if (node != null) {
//                            ShortcutTreeItem treeItem = (ShortcutTreeItem) node.getUserObject();
//
//                            switch (treeItem.getType()) {
//                                case DIRECTORY:
//                                    ShortcutDirectory directory = (ShortcutDirectory) treeItem;
//
//                                    String newName = JOptionPane.showInputDialog(null, "Type the new name:", directory.getName());
//                                    if(newName != null) {
//                                        directory.setName(newName);
//                                    }
//
//                                    break;
//                                case SHORTCUT:
//                                    Shortcut shortcut = (Shortcut) treeItem;
//
//                                    JTextField xField = new JTextField();
//                                    JTextField yField = new JTextField();
//
//                                    JPanel myPanel = null;
//                                    myPanel = new JPanel(new GridLayout(2, 2));
//                                    myPanel.add(new JLabel("Type the new name:"));
//                                    xField.setText(shortcut.getName());
//                                    myPanel.add(xField);
//                                    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
//                                    myPanel.add(new JLabel("Type the new path:"));
//                                    yField.setText(shortcut.getShortcutPath());
//                                    myPanel.add(yField);
//
//                                    int result = JOptionPane.showConfirmDialog(null, myPanel,
//                                            "Fill the fields", JOptionPane.OK_CANCEL_OPTION);
//                                    if (result == JOptionPane.OK_OPTION) {
//                                        shortcut.setName(xField.getText());
//                                        shortcut.setShortcutPath(yField.getText());
//                                    }
//
//                                    break;
//                                case ACTION:
//                                    break;
//                            }
//                        }
//                        mainApplication.updateGUI();
//                    }
//                }
//            }
//        };
//        jTree.addMouseListener(ml);
//
//        return jTree;
//    }
//
//    private void myDoubleClick(int selRow, TreePath selPath) {
//
//        System.out.println(selRow + " / " +selPath);
//
//    }
//
//    private void fillJTree(List<ShortcutTreeItem> shortcutTreeItems, DefaultMutableTreeNode owner) {
//        shortcutTreeItems.stream().forEach(item-> {
//            DefaultMutableTreeNode node = new DefaultMutableTreeNode();
//            switch (item.getType()) {
//                case DIRECTORY:
//                    ShortcutDirectory directory = (ShortcutDirectory) item;
//
//                    node.setUserObject(directory);
//                    fillJTree(directory.getItems(), node);
//                    owner.add(node);
//                    break;
//                case SHORTCUT:
//                    Shortcut shortcut = (Shortcut) item;
//
//                    node.setUserObject(shortcut);
//                    owner.add(node);
//                    break;
//                case ACTION:
//                    break;
//            }
//        });
//    }
//
//}
////
////class Renderer extends JLabel implements TreeCellRenderer {
////    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected,
////                                                  boolean expanded, boolean leaf, int row, boolean hasFocus) {
////        setText(value.toString() + "                   ");
////        return this;
////    }
////
////}
