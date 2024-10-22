package rpg.gui.buttons;

import rpg.utils.FontCache;
import rpg.utils.PictureCache;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class HoverButtonUI extends BasicButtonUI {

    protected int width;
    protected int height;
    protected ImageIcon[] parts;
    protected ImageIcon[] partsHover;

    @Override
    protected void installDefaults(AbstractButton b) {

        initParts();
        b.setFont(FontCache.addFont("M6X",
                "fonts/M6X.ttf").deriveFont(22.5f));
        b.setForeground(Color.BLACK);
        b.setDoubleBuffered(true);
        b.setOpaque(false);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setContentAreaFilled(false);
        b.setIconTextGap(5);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        String text = b.getText();
        this.width = b.getFontMetrics(b.getFont()).stringWidth(text) + (5);
        this.height = 48;
    }

    @Override
    public Dimension getPreferredSize(JComponent c) {

        return new Dimension(Math.max(width + 54, 84), 48);
    }

    @Override
    public Dimension getMaximumSize(JComponent c) {
        return getPreferredSize(c);
    }

    @Override
    public Dimension getMinimumSize(JComponent c) {
        return getPreferredSize(c);
    }

    /**
     * Inicializa las partes del botón.
     * En sus estados normal y hover.
     */
    private void initParts() {
        //Inicializamos los arreglos de imágenes.
        parts = new ImageIcon[3];
        partsHover = new ImageIcon[3];
        // Agregamos las imágenes a la caché.
        PictureCache.addImage("userLeftSide", "buttons/idle/user/leftSide.png");
        PictureCache.addImage("userCenterSide", "buttons/idle/user/centerSide.png");
        PictureCache.addImage("userRightSide", "buttons/idle/user/rightSide.png");
        PictureCache.addImage("userHoverLeftSide", "buttons/hover/user/leftSide.png");
        PictureCache.addImage("userHoverCenterSide", "buttons/hover/user/centerSide.png");
        PictureCache.addImage("userHoverRightSide", "buttons/hover/user/rightSide.png");
        // Obtenemos las imágenes de la caché y las almacenamos en los arreglos correspondientes.
        parts[0] = PictureCache.getImageIcon("userLeftSide");
        parts[1] = PictureCache.getImageIcon("userCenterSide");
        parts[2] = PictureCache.getImageIcon("userRightSide");
        partsHover[0] = PictureCache.getImageIcon("userHoverLeftSide");
        partsHover[1] = PictureCache.getImageIcon("userHoverCenterSide");
        partsHover[2] = PictureCache.getImageIcon("userHoverRightSide");
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);
        Graphics2D g2d = (Graphics2D) g;
        AbstractButton button = (AbstractButton) c;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        ImageIcon[] images = button.getModel().isRollover() ? partsHover : parts;
        drawButtonParts(g2d, images);
        g2d.translate(-width - 27, 0);
        g2d.setColor(Color.WHITE);
        super.paint(g2d, c);
    }

    private void drawButtonParts(Graphics2D g2d, ImageIcon[] parts) {

        g2d.drawImage(parts[0].getImage(), 0, 0, null);
        g2d.translate(27, 0);
        g2d.drawImage(parts[1].getImage(), 0, 0,
                width, height, null);
        g2d.translate(width, 0);
        g2d.drawImage(parts[2].getImage(), 0, 0, null);
    }
}
