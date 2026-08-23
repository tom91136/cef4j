package net.kurobako.cef4j.remote;

public final class RemoteViewportConstraints {
    public static final int MAX_DIMENSION = 8192;
    public static final long MAX_PIXELS = 3840L * 2160L;

    private RemoteViewportConstraints() {}

    public static void validate(int width, int height) {
        long pixels = (long) width * height;
        if (width <= 0 || height <= 0 || width > MAX_DIMENSION || height > MAX_DIMENSION || pixels > MAX_PIXELS) {
            throw new IllegalArgumentException("viewport exceeds " + MAX_DIMENSION + " per dimension or " + MAX_PIXELS
                    + " pixels: " + width + "x" + height);
        }
    }
}
