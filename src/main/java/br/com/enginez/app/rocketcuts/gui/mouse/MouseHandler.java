package br.com.enginez.app.rocketcuts.gui.mouse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Optional;

public class MouseHandler implements MouseListener {

    private static MouseHandler mouseHandler;
    private HashMap<Object, ExtendedMouseListener> eventHandlersMap = new HashMap<Object, ExtendedMouseListener>();

    private MouseHandler() {
    }

    public static MouseHandler getInstance() {
        if(mouseHandler == null) {
            mouseHandler = new MouseHandler();
        }
        return mouseHandler;
    }

    public void handle(Object eventOwner, ExtendedMouseListener mouseListener) {
        eventHandlersMap.put(eventOwner, mouseListener);
    }

    public void remove(Object eventOwner) {
        eventHandlersMap.remove(eventOwner);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        ExtendedMouseListener listener = getHandler(e).orElseThrow(() -> new RuntimeException("No event to handle the event"));
        listener.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        ExtendedMouseListener listener = getHandler(e).orElseThrow(() -> new RuntimeException("No event to handle the event"));
        listener.mousePressed(e);

        switch(e.getButton()) {
            case MouseEvent.BUTTON1:
                if (e.getClickCount() == 2) {
                    listener.onLeft2Click(e);
                } else {
                    listener.onLeftClick(e, e.getClickCount());
                }
                break;
            case MouseEvent.BUTTON2:
                if (e.getClickCount() == 2) {
                    listener.onMiddle2Click(e);
                } else {
                    listener.onMiddleClick(e, e.getClickCount());
                }
                break;
            case MouseEvent.BUTTON3:
                if (e.getClickCount() == 2) {
                    listener.onRight2Click(e);
                } else {
                    listener.onRightClick(e, e.getClickCount());
                }
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        ExtendedMouseListener listener = getHandler(e).orElseThrow(() -> new RuntimeException("No event to handle the event"));
        listener.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        ExtendedMouseListener listener = getHandler(e).orElseThrow(() -> new RuntimeException("No event to handle the event"));
        listener.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        ExtendedMouseListener listener = getHandler(e).orElseThrow(() -> new RuntimeException("No event to handle the event"));
        listener.mouseExited(e);
    }

    private Optional<ExtendedMouseListener> getHandler(MouseEvent e) {
        if(eventHandlersMap.containsKey(e.getComponent())) {
            return Optional.ofNullable(eventHandlersMap.get(e.getComponent()));
        }
        return Optional.empty();
    }

    public interface ExtendedMouseListener extends MouseListener {

        public void onLeftClick(MouseEvent e, int clickCount);

        public void onMiddleClick(MouseEvent e, int clickCount);

        public void onRightClick(MouseEvent e, int clickCount);

        public void onLeft2Click(MouseEvent e);

        public void onMiddle2Click(MouseEvent e);

        public void onRight2Click(MouseEvent e);

    }

}
