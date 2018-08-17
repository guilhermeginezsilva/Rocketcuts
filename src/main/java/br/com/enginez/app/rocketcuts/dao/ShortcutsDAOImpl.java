package br.com.enginez.app.rocketcuts.dao;

import br.com.enginez.app.rocketcuts.model.shortcut.tree.ShortcutsTree;
import br.com.enginez.app.rocketcuts.model.shortcut.tree.item.Shortcut;
import br.com.enginez.app.rocketcuts.model.shortcut.tree.item.ShortcutDirectory;
import br.com.enginez.app.rocketcuts.model.shortcut.tree.item.ShortcutTreeItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ShortcutsDAOImpl implements ShortcutsDAO {

    @Override
    public void save(ShortcutsTree tree) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            mapper.writeValue(new FileWriter("shortcuts.json"), tree);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ShortcutsTree get() {

        ShortcutsTree tree = new ShortcutsTree();

//        ShortcutDirectory ftpGroup = new ShortcutDirectory(null, "FTP", null, tree);
//        ftpGroup.add(new Shortcut(null, "FileZilla", "C:\\Program Files\\FileZilla FTP Client\\filezilla.exe", ftpGroup, tree));
//        ftpGroup.add(new Shortcut(null, "Google Chrome", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe", ftpGroup, tree));
//
//        tree.addItem(ftpGroup);
//        tree.addItem(new Shortcut(null, "Notepad++", "C:\\Program Files (x86)\\Notepad++\\notepad++.exe", null, tree));
        try {
            ObjectMapper mapper = new ObjectMapper();
            tree = mapper.readValue(new File("shortcuts.json"), ShortcutsTree.class);
            setNodeParents(tree);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tree;
    }

    public void setNodeParents(ShortcutsTree tree) {
        tree.getItems().stream().forEach(item-> {
            setNodeParents(item, null, tree);
        });
    }

    public void setNodeParents(ShortcutTreeItem treeItem, ShortcutTreeItem parent, ShortcutsTree tree) {
        switch (treeItem.getType()) {
            case DIRECTORY:
                ShortcutDirectory directory = (ShortcutDirectory) treeItem;
                directory.getItems().stream().forEach(subDirectoryItem-> {
                    setNodeParents(subDirectoryItem, directory, tree);
                });
                break;
        }
        treeItem.setParent(parent);
        treeItem.setTree(tree);
    }
}
