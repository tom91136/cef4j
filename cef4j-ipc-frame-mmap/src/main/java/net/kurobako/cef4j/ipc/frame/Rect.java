package net.kurobako.cef4j.ipc.frame;

/**
 * Pixel-space rectangle. Origin is top-left, {@code (x, y)} is the rectangle's corner; {@code width}/{@code height}
 * extend right and down. Used to describe dirty regions inside a frame so consumers can do partial blits.
 */
public final class Rect {

    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public Rect(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    @Override
    public String toString() {
        return "Rect[" + x + "," + y + " " + width + "x" + height + "]";
    }
}
