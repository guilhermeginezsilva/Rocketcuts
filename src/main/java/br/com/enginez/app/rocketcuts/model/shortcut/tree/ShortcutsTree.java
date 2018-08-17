package br.com.enginez.app.rocketcuts.model.shortcut.tree;

import br.com.enginez.app.rocketcuts.model.shortcut.tree.item.ShortcutDirectory;
import br.com.enginez.app.rocketcuts.model.shortcut.tree.item.ShortcutTreeItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data public class  ShortcutsTree {

    private List<ShortcutTreeItem> items = new ArrayList<ShortcutTreeItem>();
    @JsonIgnore
    private long lastId=-1;

    public void addItem(ShortcutTreeItem item) {
        if(item.getId() == null) {
            item.setId(getNextId());
        }
        this.items.add(item);
    }
    public void addItems(List<ShortcutTreeItem> items) {
        items.forEach(item->{
            if(item.getId() == null) {
                item.setId(getNextId());
            }
        });
        this.items.addAll(items);
    }

    public Optional<ShortcutTreeItem> find(long id) {
        return this.items.stream().flatMap(ShortcutTreeItem::flattened).filter(i->i.getId().longValue() == id).findFirst();
    }

    public void remove(ShortcutTreeItem treeItem) {
        Optional<ShortcutTreeItem> shortcutTreeItem = this.find(treeItem.getId());
        if(shortcutTreeItem.isPresent()) {
            ShortcutDirectory parentDirectory = ((ShortcutDirectory) shortcutTreeItem.get().getParent());
            if(parentDirectory != null) {
                parentDirectory.remove(treeItem);
            } else {
                this.getItems().remove(treeItem);
            }
        }
    }

    @JsonIgnore
    public Long getNextId() {
        if(lastId==-1) {
            this.lastId = this.items.stream().flatMap(ShortcutTreeItem::flattened).mapToLong(i->i.getId()).max().orElse(0L);
        }
        return ++lastId;
    }
}
