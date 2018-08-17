package br.com.enginez.app.rocketcuts.model.shortcut.tree.item;

import br.com.enginez.app.rocketcuts.RocketcutsApplication;
import br.com.enginez.app.rocketcuts.model.shortcut.tree.ShortcutsTree;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import java.awt.event.ActionEvent;
import java.util.Optional;
import java.util.stream.Stream;

@Data public class  Shortcut extends ShortcutTreeItem {

    private String shortcutPath;

    public Shortcut(Long id, String name, String shortcutPath, ShortcutTreeItem parent, ShortcutsTree tree) {
        super(id, name, ShortcutItemType.SHORTCUT, parent, tree);
        this.shortcutPath = shortcutPath;
    }

    @JsonCreator
    public Shortcut(@JsonProperty("id") Long id, @JsonProperty("name") String name, @JsonProperty("shortcutPath") String shortcutPath) {
        super(id, name, ShortcutItemType.SHORTCUT, null, null);
        this.shortcutPath = shortcutPath;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RocketcutsApplication.getRocketcuts().startExternalApplication(Optional.ofNullable(this.shortcutPath));
    }

    @Override
    public String toString() {
        return this.name + " = " + this.shortcutPath;
    }
}
