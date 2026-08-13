package net.kurobako.cef4j.ipc.frame;

/** Pixel-space rectangle with a top-left origin. */
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
