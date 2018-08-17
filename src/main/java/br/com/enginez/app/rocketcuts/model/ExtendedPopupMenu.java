package br.com.enginez.app.rocketcuts.model;

import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.util.List;


import br.com.enginez.app.rocketcuts.model.shortcut.tree.ShortcutsTree;
import br.com.enginez.app.rocketcuts.model.shortcut.tree.item.ShortcutDirectory;
import br.com.enginez.app.rocketcuts.model.shortcut.tree.item.ShortcutTreeItem;
import br.com.enginez.app.rocketcuts.model.shortcut.tree.item.action.Action;

public class ExtendedPopupMenu extends PopupMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2111360333631495293L;

	public void addItemsToRoot(ShortcutsTree tree) {
		Menu menuRoot = this;
		addItemsTo(menuRoot, tree.getItems());
	}
	
	public void addItemsTo(Menu owner, List<ShortcutTreeItem> treeItems) {

        treeItems.stream().forEach(item -> {
			switch (item.getType()) {
                case SHORTCUT:
                    MenuItem shortcutMenuItem = new MenuItem(item.getName());
                    shortcutMenuItem.addActionListener(item);
                    owner.add(shortcutMenuItem);
                    break;
                case DIRECTORY:
                    Menu group = new Menu(item.getName());
                    addItemsTo(group, ((ShortcutDirectory) item).getItems());
                    owner.add(group);
                    break;
                case ACTION:
                    MenuItem actionMenuItem = new MenuItem(item.getName());
                    actionMenuItem.addActionListener(item);
                    owner.add(actionMenuItem);
                    break;
                default:
                    break;
			}
		});
	}

    public void add(ShortcutTreeItem treeItem) {
        Menu owner = this;
        switch (treeItem.getType()) {
            case SHORTCUT:
                MenuItem shortcutMenuItem = new MenuItem(treeItem.getName());
                shortcutMenuItem.addActionListener(treeItem);
                owner.add(shortcutMenuItem);
                break;
            case DIRECTORY:
                Menu group = new Menu(treeItem.getName());
                addItemsTo(group, ((ShortcutDirectory) treeItem).getItems());
                owner.add(group);
                break;
            case ACTION:
                MenuItem actionMenuItem = new MenuItem(treeItem.getName());
                actionMenuItem.addActionListener(treeItem);
                owner.add(actionMenuItem);
                break;
            default:
                break;
        }
    }
}
