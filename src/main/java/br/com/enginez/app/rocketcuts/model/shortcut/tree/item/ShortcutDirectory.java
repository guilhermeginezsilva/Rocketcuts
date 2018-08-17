package br.com.enginez.app.rocketcuts.model.shortcut.tree.item;

import br.com.enginez.app.rocketcuts.model.shortcut.tree.ShortcutsTree;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Data public class  ShortcutDirectory extends ShortcutTreeItem {

    public ShortcutDirectory(Long id, String name, ShortcutTreeItem parent, ShortcutsTree tree) {
        super(id, name, ShortcutItemType.DIRECTORY, parent,tree);
    }

    @JsonCreator
    public ShortcutDirectory(@JsonProperty("id") Long id, @JsonProperty("name") String name) {
        super(id, name, ShortcutItemType.DIRECTORY, null, null);
    }

    private List<ShortcutTreeItem> items = new ArrayList<>();

    public Stream<ShortcutTreeItem> flattened() {
        return Stream.concat(
                Stream.of(this),
                items.stream().flatMap(ShortcutTreeItem::flattened));
    }

    public void add(ShortcutTreeItem item) {
        if(item.getId() == null) {
            item.setId(this.tree.getNextId());
        }
        this.items.add(item);
    }

    public void remove(ShortcutTreeItem item) {
        this.items.remove(item);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public String toString() {
        return this.name;
    }

}
