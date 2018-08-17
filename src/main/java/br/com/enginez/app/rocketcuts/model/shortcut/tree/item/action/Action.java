package br.com.enginez.app.rocketcuts.model.shortcut.tree.item.action;

import br.com.enginez.app.rocketcuts.model.shortcut.tree.ShortcutsTree;
import br.com.enginez.app.rocketcuts.model.shortcut.tree.item.ShortcutItemType;
import br.com.enginez.app.rocketcuts.model.shortcut.tree.item.ShortcutTreeItem;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.awt.event.ActionEvent;

@Data public class Action extends ShortcutTreeItem {

    private ActionProvider actionProvider;

    public Action(Long id, String name, ActionProvider actionProvider, ShortcutTreeItem parent, ShortcutsTree tree) {
        super(id, name, ShortcutItemType.ACTION, parent, tree);
        this.actionProvider = actionProvider;
    }

    @JsonCreator
    public Action(@JsonProperty("id") Long id, @JsonProperty("name") String name, @JsonProperty("actionProvider") ActionProvider actionProvider) {
        super(id, name, ShortcutItemType.ACTION, null, null);
        this.actionProvider = actionProvider;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.actionProvider.execute();
    }

}
