package br.com.enginez.app.rocketcuts;

import br.com.enginez.app.rocketcuts.dao.ShortcutsDAO;
import br.com.enginez.app.rocketcuts.dao.ShortcutsDAOImpl;
import br.com.enginez.app.rocketcuts.gui.GUIHandler;
import br.com.enginez.app.rocketcuts.gui.ShortcutsManagerSwingGUI;
import br.com.enginez.app.rocketcuts.model.ExtendedPopupMenu;
import br.com.enginez.app.rocketcuts.model.Tray;
import br.com.enginez.app.rocketcuts.model.shortcut.tree.ShortcutsTree;
import br.com.enginez.app.rocketcuts.model.shortcut.tree.item.action.Action;
import br.com.enginez.app.rocketcuts.util.ImageUtil;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Optional;

public class RocketcutsApplication implements GUIHandler {

    private static RocketcutsApplication applicationInstance;
    private Tray tray;
    private ShortcutsTree tree;
    ShortcutsManagerSwingGUI managerGUI;

    private RocketcutsApplication() {
    }

    public static RocketcutsApplication getRocketcuts() {
        if (applicationInstance == null) {
            applicationInstance = new RocketcutsApplication();
        }
        return applicationInstance;
    }

    public void execute() {
        setupApplication();
        configureVisualStyle();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setupApplication() {
        this.tray = new Tray(new ExtendedPopupMenu(),
                new TrayIcon(ImageUtil.createImage("rocket-16.png")),
                SystemTray.getSystemTray());
    }

    private void configureVisualStyle() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            // UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        UIManager.put("swing.boldMetal", Boolean.FALSE);
    }

    private void createAndShowGUI() throws IOException {

        checkTraySupport();
        ShortcutsDAO shortcutsDAO = new ShortcutsDAOImpl();
        this.tree = shortcutsDAO.get();
        createTrayMenu();

        try {
            this.tray.getSystemTray().add(this.tray.getTrayIcon());
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
            return;
        }
    }

    @Override
    public void updateGUI() {
        createTrayMenu();
        ShortcutsDAO shortcutsDAO = new ShortcutsDAOImpl();
        shortcutsDAO.save(this.tree);
    }

    @Override
    public void closeGUI() {
    }

    @Override
    public void hideGUI() {
    }

    @Override
    public void showGUI() {
    }

    private static void checkTraySupport() {
        if (!SystemTray.isSupported()) {
            throw new RuntimeException("SystemTray is not supported");
        }
    }

    private void createTrayMenu() {
        this.tray.getPopup().removeAll();
        this.tray.getPopup().addItemsToRoot(this.tree);

        this.tray.getPopup().addSeparator();
        this.tray.getPopup().add(new Action(null, "Manage Shortcuts", () -> {
            managerGUI = new ShortcutsManagerSwingGUI(this);
            managerGUI.showGUI(this.tree);
        }, null, this.tree));

        this.tray.getPopup().add(new Action(null, "Save", () -> {
            ShortcutsDAO shortcutsDAO = new ShortcutsDAOImpl();
            shortcutsDAO.save(this.tree);
        }, null, this.tree));

        this.tray.getPopup().add(new Action(null, "Exit", () -> {
            System.exit(0);
        }, null, this.tree));

        this.tray.getTrayIcon().setPopupMenu(this.tray.getPopup());
    }

    public void startExternalApplication(Optional<String> path) {
        try {
            if (path.isPresent()) {
                this.tray.getTrayIcon().displayMessage("Rocketcuts", "Opening: " + path.get(), TrayIcon.MessageType.INFO);
//                Runtime.getRuntime().exec(path.get());
                ProcessBuilder pb = new ProcessBuilder(path.get());
                Process p = pb.start();
            } else {
                this.tray.getTrayIcon().displayMessage("Rocketcuts", "No shortcut found", TrayIcon.MessageType.ERROR);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
