package br.com.enginez.app.rocketcuts.model.shortcut.tree.item;

import br.com.enginez.app.rocketcuts.model.shortcut.tree.ShortcutsTree;
import br.com.enginez.app.rocketcuts.model.shortcut.tree.item.action.Action;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import java.awt.event.ActionListener;
import java.util.stream.Stream;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Shortcut.class),
        @JsonSubTypes.Type(value = ShortcutDirectory.class),
        @JsonSubTypes.Type(value = Action.class),
})
@Data public abstract class  ShortcutTreeItem implements ActionListener {

    protected Long id;
    protected String name;
    protected final ShortcutItemType type;

    @JsonIgnore
    protected ShortcutTreeItem parent;
    @JsonIgnore
    protected ShortcutsTree tree;

    public ShortcutTreeItem(Long id, String name, ShortcutItemType type, ShortcutTreeItem parent, ShortcutsTree tree) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.parent = parent;
        this.tree = tree;
    }

    public Stream<ShortcutTreeItem> flattened() {
        return Stream.of(this);
    }

    public ShortcutTreeItem getParent() {
        return this.parent;
    }
}
