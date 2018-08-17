package br.com.enginez.app.rocketcuts.model;

import lombok.Data;

import java.awt.*;

@Data
public class Tray {

    private ExtendedPopupMenu popup ;
    private TrayIcon trayIcon;
    private SystemTray systemTray;

    public Tray(ExtendedPopupMenu extendedPopupMenu, TrayIcon trayIcon, SystemTray systemTray) {
        this.popup = extendedPopupMenu;
        this.trayIcon = trayIcon;
        this.systemTray = systemTray;
    }
}
