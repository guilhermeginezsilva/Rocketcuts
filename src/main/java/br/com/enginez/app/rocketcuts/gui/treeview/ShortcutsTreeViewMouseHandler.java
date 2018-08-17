package br.com.enginez.app.rocketcuts.gui.treeview;

import br.com.enginez.app.rocketcuts.gui.GUIHandler;
import br.com.enginez.app.rocketcuts.gui.mouse.MouseHandler;
import br.com.enginez.app.rocketcuts.model.shortcut.tree.ShortcutsTree;
import br.com.enginez.app.rocketcuts.model.shortcut.tree.item.Shortcut;
import br.com.enginez.app.rocketcuts.model.shortcut.tree.item.ShortcutDirectory;
import br.com.enginez.app.rocketcuts.model.shortcut.tree.item.ShortcutItemType;
import br.com.enginez.app.rocketcuts.model.shortcut.tree.item.ShortcutTreeItem;
import br.com.enginez.app.rocketcuts.util.ImageUtil;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ShortcutsTreeViewMouseHandler implements MouseHandler.ExtendedMouseListener {

    private JTree jTree;
    private ShortcutsTree shortcutsTree;
    private GUIHandler guiHandler;

    public ShortcutsTreeViewMouseHandler(JTree jTree, ShortcutsTree shortcutsTree, GUIHandler guiHandler) {
        this.jTree = jTree;
        this.shortcutsTree = shortcutsTree;
        this.guiHandler = guiHandler;
    }

    @Override
    public void onLeftClick(MouseEvent e, int clickCount) {
        int selRow = jTree.getRowForLocation(e.getX(), e.getY());
        if(selRow != -1) {
            jTree.setSelectionRow(selRow);
        }
    }

    @Override
    public void onMiddleClick(MouseEvent e, int clickCount) {
    }

    @Override
    public void onRightClick(MouseEvent e, int clickCount) {
        int selRow = jTree.getRowForLocation(e.getX(), e.getY());
        if(selRow != -1) {
            jTree.setSelectionRow(selRow);
        }

        DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree.getLastSelectedPathComponent();
        ShortcutTreeItem treeItem = null;
        if (node != null && ShortcutTreeItem.class.isInstance(node.getUserObject())) {
            treeItem = (ShortcutTreeItem) node.getUserObject();
        } else if(node.getParent() != null){
            return;
        }

        final JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem addDirectory = new JMenuItem("Add Directory");
        addDirectory.setIcon(new ImageIcon(ImageUtil.createImage("icons8-open-20.png")));
        addDirectory.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                        jTree.getLastSelectedPathComponent();
                if (node != null && node.getParent() != null) {
                    ShortcutTreeItem treeItem = (ShortcutTreeItem) node.getUserObject();

                    if (treeItem.getType() == ShortcutItemType.DIRECTORY) {
                        ShortcutDirectory newShortcutDirectory = new ShortcutDirectory(null, getDirectoryInput(null), treeItem, shortcutsTree);
                        ShortcutDirectory directory = (ShortcutDirectory) treeItem;
                        directory.add(newShortcutDirectory);

                        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode();
                        newNode.setUserObject(newShortcutDirectory);
                        node.add(newNode);

                        guiHandler.updateGUI();
                    }
                } else if (node.getParent() == null) {
                    ShortcutDirectory newShortcutDirectory = new ShortcutDirectory(null, getDirectoryInput(null), null, shortcutsTree);
                    shortcutsTree.addItem(newShortcutDirectory);

                    DefaultMutableTreeNode newNode = new DefaultMutableTreeNode();
                    newNode.setUserObject(newShortcutDirectory);
                    node.add(newNode);

                    guiHandler.updateGUI();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        if((treeItem != null && treeItem.getType() == ShortcutItemType.DIRECTORY) || node.getParent() == null) {
            popupMenu.add(addDirectory);
        }

        JMenuItem addShortcut = new JMenuItem("Add Shortcut");
        addShortcut.setIcon(new ImageIcon(ImageUtil.createImage("icons8-link-20.png")));
        addShortcut.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                        jTree.getLastSelectedPathComponent();
                if (node != null && node.getParent() != null) {
                    ShortcutTreeItem treeItem = (ShortcutTreeItem) node.getUserObject();

                    if (treeItem.getType() == ShortcutItemType.DIRECTORY) {
                        String[] shortcutInput = getShortcutInput(null, null);

                        Shortcut newShortcut = new Shortcut(null, shortcutInput[0], shortcutInput[1], treeItem, shortcutsTree);
                        ShortcutDirectory directory = (ShortcutDirectory) treeItem;
                        directory.add(newShortcut);

                        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode();
                        newNode.setUserObject(newShortcut);
                        node.add(newNode);

                        guiHandler.updateGUI();
                    }
                } else if (node.getParent() == null) {
                    String[] shortcutInput = getShortcutInput(null, null);

                    Shortcut newShortcut = new Shortcut(null, shortcutInput[0], shortcutInput[1], null, shortcutsTree);
                    shortcutsTree.addItem(newShortcut);

                    DefaultMutableTreeNode newNode = new DefaultMutableTreeNode();
                    newNode.setUserObject(newShortcut);
                    node.add(newNode);

                    guiHandler.updateGUI();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        if((treeItem != null && treeItem.getType() == ShortcutItemType.DIRECTORY) || node.getParent() == null) {
            popupMenu.add(addShortcut);
        }

        if(treeItem != null && treeItem.getType() == ShortcutItemType.DIRECTORY) {
            popupMenu.add(new JPopupMenu.Separator());
        }

        JMenuItem addDelete = new JMenuItem("Delete");
        addDelete.setIcon(new ImageIcon(ImageUtil.createImage("icons8-delete-20.png")));
        addDelete.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                        jTree.getLastSelectedPathComponent();
                if (node != null) {
                    ShortcutTreeItem treeItem = (ShortcutTreeItem) node.getUserObject();

                    switch(treeItem.getType()) {
                        case DIRECTORY:
                            ShortcutDirectory directory = (ShortcutDirectory) treeItem;
                            shortcutsTree.remove(directory);
                        break;
                        case SHORTCUT:
                            Shortcut shortcut = (Shortcut) treeItem;
                            shortcutsTree.remove(shortcut);
                            break;
                    }
                    node.removeFromParent();
                    guiHandler.updateGUI();
                }

            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        if(treeItem != null || node.getParent() != null) {
            popupMenu.add(addDelete);
        }

        popupMenu.show(e.getComponent(), e.getX(), e.getY());
    }

    @Override
    public void onLeft2Click(MouseEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                jTree.getLastSelectedPathComponent();
        if (node != null) {
            ShortcutTreeItem treeItem = (ShortcutTreeItem) node.getUserObject();

            switch (treeItem.getType()) {
                case DIRECTORY:
                    ShortcutDirectory directory = (ShortcutDirectory) treeItem;
                    directory.setName(getDirectoryInput(directory.getName()));

                    break;
                case SHORTCUT:
                    Shortcut shortcut = (Shortcut) treeItem;
                    String[] shortcutInput = getShortcutInput(shortcut.getName(), shortcut.getShortcutPath());
                    if (shortcutInput[0] != null && !shortcutInput[0].equals("")) {
                        shortcut.setName(shortcutInput[0]);
                    }
                    if (shortcutInput[1] != null && !shortcutInput[1].equals("")) {
                        shortcut.setShortcutPath(shortcutInput[1]);
                    }
                    break;
                case ACTION:
                    break;
            }
        }
        this.guiHandler.updateGUI();
    }

    @Override
    public void onMiddle2Click(MouseEvent e) {
    }

    @Override
    public void onRight2Click(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public String getDirectoryInput(String name) {
        String newName = JOptionPane.showInputDialog(null, "Type the new name:", name==null?"":name);

        if(newName != null) {
            return newName;
        }

        return "";
    }

    public String[] getShortcutInput(String name, String path) {
        JTextField shortcutNameField = new JTextField();
        JTextField shortcutPathField = new JTextField();

        JPanel shortcutDialoguePanel = null;
        shortcutDialoguePanel = new JPanel(new GridLayout(2, 2));
        shortcutDialoguePanel.add(new JLabel("Type the new name:"));
        if(name != null) {
            shortcutNameField.setText(name);
        }
        shortcutDialoguePanel.add(shortcutNameField);
        shortcutDialoguePanel.add(Box.createHorizontalStrut(15)); // a spacer
        shortcutDialoguePanel.add(new JLabel("Type the new path:"));
        if(path != null) {
            shortcutPathField.setText(path);
        }
        shortcutDialoguePanel.add(shortcutPathField);

        int result = JOptionPane.showConfirmDialog(null, shortcutDialoguePanel,
                "Fill the fields", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String[] input = {shortcutNameField.getText(),shortcutPathField.getText()};
            return input;
        }
        return new String[]{"",""};
    }
}
