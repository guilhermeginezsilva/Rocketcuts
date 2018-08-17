package br.com.enginez.app.rocketcuts.dao;

import br.com.enginez.app.rocketcuts.model.shortcut.tree.ShortcutsTree;

public interface ShortcutsDAO {

    public void save(ShortcutsTree tree);
    public ShortcutsTree get();

}
