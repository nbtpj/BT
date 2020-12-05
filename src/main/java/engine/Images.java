package engine;

import javafx.scene.image.*;

/**
 * Lưu trữ thông tin các pixel của 1 sprite (hình ảnh game)
 */
public class Images {

    public static final int DEFAULT_SIZE = 16;
    public static final int SCALED_SIZE = DEFAULT_SIZE * 2;
    private static final int TRANSPARENT_COLOR = 0xffff00ff;
    /*
    |--------------------------------------------------------------------------
    | Background sprites
    |--------------------------------------------------------------------------
     */
    public static Images background = new Images(DEFAULT_SIZE, 0xff000000);
    /*
    |--------------------------------------------------------------------------
    | Board sprites
    |--------------------------------------------------------------------------
     */
    public static Images grass = new Images(DEFAULT_SIZE, 6, 0, ImageSheet.tiles, 16, 16);
    public static Images brick = new Images(DEFAULT_SIZE, 7, 0, ImageSheet.tiles, 16, 16);
    public static Images wall = new Images(DEFAULT_SIZE, 5, 0, ImageSheet.tiles, 16, 16);
    public static Images portal = new Images(DEFAULT_SIZE, 4, 0, ImageSheet.tiles, 14, 14);

    public static Images[] player_up = {
            new Images(DEFAULT_SIZE, 0, 0, ImageSheet.tiles, 12, 16),
            new Images(DEFAULT_SIZE, 0, 1, ImageSheet.tiles, 12, 16),
            new Images(DEFAULT_SIZE, 0, 2, ImageSheet.tiles, 12, 15)
    };
    public static Images[] player_down = {
            new Images(DEFAULT_SIZE, 2, 0, ImageSheet.tiles, 12, 15),
            new Images(DEFAULT_SIZE, 2, 1, ImageSheet.tiles, 12, 15),
            new Images(DEFAULT_SIZE, 2, 2, ImageSheet.tiles, 12, 16)
    };
    public static Images[] player_left = {
            new Images(DEFAULT_SIZE, 3, 0, ImageSheet.tiles, 10, 15),
            new Images(DEFAULT_SIZE, 3, 1, ImageSheet.tiles, 11, 16),
            new Images(DEFAULT_SIZE, 3, 2, ImageSheet.tiles, 12, 16)
    };
    public static Images[] player_right = {
            new Images(DEFAULT_SIZE, 1, 0, ImageSheet.tiles, 10, 16),
            new Images(DEFAULT_SIZE, 1, 1, ImageSheet.tiles, 11, 16),
            new Images(DEFAULT_SIZE, 1, 2, ImageSheet.tiles, 12, 16)
    };
    public static Images[] player_dead = {
            new Images(DEFAULT_SIZE, 4, 2, ImageSheet.tiles, 14, 16),
            new Images(DEFAULT_SIZE, 5, 2, ImageSheet.tiles, 13, 15),
            new Images(DEFAULT_SIZE, 6, 2, ImageSheet.tiles, 16, 16)
    };
    /*
    |--------------------------------------------------------------------------
    | Bomber Sprites
    |--------------------------------------------------------------------------
     */
    //BALLOON
    public static Images[] balloon_left = {
            new Images(DEFAULT_SIZE, 9, 0, ImageSheet.tiles, 16, 16),
            new Images(DEFAULT_SIZE, 9, 1, ImageSheet.tiles, 16, 16),
            new Images(DEFAULT_SIZE, 9, 2, ImageSheet.tiles, 16, 16)
    };
    public static Images[] balloon_right = {
            new Images(DEFAULT_SIZE, 10, 0, ImageSheet.tiles, 16, 16),
            new Images(DEFAULT_SIZE, 10, 1, ImageSheet.tiles, 16, 16),
            new Images(DEFAULT_SIZE, 10, 2, ImageSheet.tiles, 16, 16)
    };
    public static Images balloon_dead =
            new Images(DEFAULT_SIZE, 9, 3, ImageSheet.tiles, 16, 16);
    //ONEAL
    public static Images[] oneal_left = {
            new Images(DEFAULT_SIZE, 11, 0, ImageSheet.tiles, 16, 16),
            new Images(DEFAULT_SIZE, 11, 1, ImageSheet.tiles, 16, 16),
            new Images(DEFAULT_SIZE, 11, 2, ImageSheet.tiles, 16, 16)
    };
    public static Images[] oneal_right = {
            new Images(DEFAULT_SIZE, 12, 0, ImageSheet.tiles, 16, 16),
            new Images(DEFAULT_SIZE, 12, 1, ImageSheet.tiles, 16, 16),
            new Images(DEFAULT_SIZE, 12, 2, ImageSheet.tiles, 16, 16)
    };
    public static Images oneal_dead =
            new Images(DEFAULT_SIZE, 11, 3, ImageSheet.tiles, 16, 16);
    //Doll
    public static Images[] doll_left = {
            new Images(DEFAULT_SIZE, 13, 0, ImageSheet.tiles, 16, 16),
            new Images(DEFAULT_SIZE, 13, 1, ImageSheet.tiles, 16, 16),
            new Images(DEFAULT_SIZE, 13, 2, ImageSheet.tiles, 16, 16)
    };
    public static Images[] doll_right = {
            new Images(DEFAULT_SIZE, 14, 0, ImageSheet.tiles, 16, 16),
            new Images(DEFAULT_SIZE, 14, 1, ImageSheet.tiles, 16, 16),
            new Images(DEFAULT_SIZE, 14, 2, ImageSheet.tiles, 16, 16)
    };
    public static Images doll_dead = new Images(DEFAULT_SIZE, 13, 3, ImageSheet.tiles, 16, 16);
    //Minvo
    public static Images[] minvo_left = {
            new Images(DEFAULT_SIZE, 8, 5, ImageSheet.tiles, 16, 16),
            new Images(DEFAULT_SIZE, 8, 6, ImageSheet.tiles, 16, 16),
            new Images(DEFAULT_SIZE, 8, 7, ImageSheet.tiles, 16, 16)
    };
    public static Images[] minvo_right = {
            new Images(DEFAULT_SIZE, 9, 5, ImageSheet.tiles, 16, 16),
            new Images(DEFAULT_SIZE, 9, 6, ImageSheet.tiles, 16, 16),
            new Images(DEFAULT_SIZE, 9, 7, ImageSheet.tiles, 16, 16)
    };
    public static Images minvo_dead = new Images(DEFAULT_SIZE, 8, 8, ImageSheet.tiles, 16, 16);
    //Kondoria
    public static Images[] kondoria_left = {
            new Images(DEFAULT_SIZE, 10, 5, ImageSheet.tiles, 16, 16),
            new Images(DEFAULT_SIZE, 10, 6, ImageSheet.tiles, 16, 16),
            new Images(DEFAULT_SIZE, 10, 7, ImageSheet.tiles, 16, 16)
    };
    public static Images[] kondoria_right = {
            new Images(DEFAULT_SIZE, 11, 5, ImageSheet.tiles, 16, 16),
            new Images(DEFAULT_SIZE, 11, 6, ImageSheet.tiles, 16, 16),
            new Images(DEFAULT_SIZE, 11, 7, ImageSheet.tiles, 16, 16)
    };
    public static Images kondoria_dead = new Images(DEFAULT_SIZE, 10, 8, ImageSheet.tiles, 16, 16);
    //ALL
    public static Images[] mob_dead = {
            new Images(DEFAULT_SIZE, 15, 0, ImageSheet.tiles, 16, 16),
            new Images(DEFAULT_SIZE, 15, 1, ImageSheet.tiles, 16, 16),
            new Images(DEFAULT_SIZE, 15, 2, ImageSheet.tiles, 16, 16)
    };
    /*
    |--------------------------------------------------------------------------
    | Bomb Sprites
    |--------------------------------------------------------------------------
     */
    public static Images[] bomb = {
            new Images(DEFAULT_SIZE, 0, 3, ImageSheet.tiles, 15, 15),
            new Images(DEFAULT_SIZE, 1, 3, ImageSheet.tiles, 13, 15),
            new Images(DEFAULT_SIZE, 2, 3, ImageSheet.tiles, 12, 14)
    };
    /*
    |--------------------------------------------------------------------------
    | FlameSegment Sprites
    |--------------------------------------------------------------------------
     */
    public static Images[] bomb_exploded = {
            new Images(DEFAULT_SIZE, 0, 4, ImageSheet.tiles, 16, 16),
            new Images(DEFAULT_SIZE, 0, 5, ImageSheet.tiles, 16, 16),
            new Images(DEFAULT_SIZE, 0, 6, ImageSheet.tiles, 16, 16)
    };
    public static Images[] explosion_vertical = {
            new Images(DEFAULT_SIZE, 1, 5, ImageSheet.tiles, 16, 16),
            new Images(DEFAULT_SIZE, 2, 5, ImageSheet.tiles, 16, 16),
            new Images(DEFAULT_SIZE, 3, 5, ImageSheet.tiles, 16, 16)
    };
    public static Images[] explosion_horizontal = {
            new Images(DEFAULT_SIZE, 1, 7, ImageSheet.tiles, 16, 16),
            new Images(DEFAULT_SIZE, 1, 8, ImageSheet.tiles, 16, 16),
            new Images(DEFAULT_SIZE, 1, 9, ImageSheet.tiles, 16, 16)
    };
    public static Images[] explosion_horizontal_left_last = {
            new Images(DEFAULT_SIZE, 0, 7, ImageSheet.tiles, 16, 16),
            new Images(DEFAULT_SIZE, 0, 8, ImageSheet.tiles, 16, 16),
            new Images(DEFAULT_SIZE, 0, 9, ImageSheet.tiles, 16, 16)
    };
    public static Images[] explosion_horizontal_right_last = {
            new Images(DEFAULT_SIZE, 2, 7, ImageSheet.tiles, 16, 16),
            new Images(DEFAULT_SIZE, 2, 8, ImageSheet.tiles, 16, 16),
            new Images(DEFAULT_SIZE, 2, 9, ImageSheet.tiles, 16, 16)
    };
    public static Images[] explosion_vertical_top_last = {
            new Images(DEFAULT_SIZE, 1, 4, ImageSheet.tiles, 16, 16),
            new Images(DEFAULT_SIZE, 2, 4, ImageSheet.tiles, 16, 16),
            new Images(DEFAULT_SIZE, 3, 4, ImageSheet.tiles, 16, 16)
    };
    public static Images[] explosion_vertical_down_last = {
            new Images(DEFAULT_SIZE, 1, 6, ImageSheet.tiles, 16, 16),
            new Images(DEFAULT_SIZE, 2, 6, ImageSheet.tiles, 16, 16),
            new Images(DEFAULT_SIZE, 3, 6, ImageSheet.tiles, 16, 16)
    };
    /*
    |--------------------------------------------------------------------------
    | Brick FlameSegment
    |--------------------------------------------------------------------------
     */
    public static Images[] brick_exploded = {
            new Images(DEFAULT_SIZE, 7, 1, ImageSheet.tiles, 16, 16),
            new Images(DEFAULT_SIZE, 7, 2, ImageSheet.tiles, 16, 16),
            new Images(DEFAULT_SIZE, 7, 3, ImageSheet.tiles, 16, 16)
    };
    /*
    |--------------------------------------------------------------------------
    | Powerups
    |--------------------------------------------------------------------------
     */
    public static Images power_up_bombs = new Images(DEFAULT_SIZE, 0, 10, ImageSheet.tiles, 16, 16);
    public static Images power_up_flames = new Images(DEFAULT_SIZE, 1, 10, ImageSheet.tiles, 16, 16);
    public static Images power_up_speed = new Images(DEFAULT_SIZE, 2, 10, ImageSheet.tiles, 16, 16);
    public static Images power_up_wall_pass = new Images(DEFAULT_SIZE, 3, 10, ImageSheet.tiles, 16, 16);
    public static Images power_up_detonator = new Images(DEFAULT_SIZE, 4, 10, ImageSheet.tiles, 16, 16);
    public static Images power_up_bomb_pass = new Images(DEFAULT_SIZE, 5, 10, ImageSheet.tiles, 16, 16);
    public static Images power_up_flame_pass = new Images(DEFAULT_SIZE, 6, 10, ImageSheet.tiles, 16, 16);
    public final int SIZE;
    public int[] _pixels;
    protected int _realWidth;
    protected int _realHeight;
    private int _x, _y;
    private ImageSheet _sheet;

    public Images(int size, int x, int y, ImageSheet sheet, int rw, int rh) {
        SIZE = size;
        _pixels = new int[SIZE * SIZE];
        _x = x * SIZE;
        _y = y * SIZE;
        _sheet = sheet;
        _realWidth = rw;
        _realHeight = rh;
        load();
    }

    public Images(int size, int color) {
        SIZE = size;
        _pixels = new int[SIZE * SIZE];
        setColor(color);
    }

    public static Images movingSprite(Images normal, Images x1, Images x2, int animate, int time) {
        int calc = animate % time;
        int diff = time / 3;

        if (calc < diff) {
            return normal;
        }

        if (calc < diff * 2) {
            return x1;
        }

        return x2;
    }

    public static Images movingSprite(Images x1, Images x2, int animate, int time) {
        int diff = time / 2;
        return (animate % time > diff) ? x1 : x2;
    }

    private void setColor(int color) {
        for (int i = 0; i < _pixels.length; i++) {
            _pixels[i] = color;
        }
    }

    private void load() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                _pixels[x + y * SIZE] = _sheet._pixels[(x + _x) + (y + _y) * _sheet.SIZE];
            }
        }
    }

    public int getSize() {
        return SIZE;
    }

    public int getPixel(int i) {
        return _pixels[i];
    }

    public Image getFxImage() {
        WritableImage wr = new WritableImage(SIZE, SIZE);
        PixelWriter pw = wr.getPixelWriter();
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                if (_pixels[x + y * SIZE] == TRANSPARENT_COLOR) {
                    pw.setArgb(x, y, 0);
                } else {
                    pw.setArgb(x, y, _pixels[x + y * SIZE]);
                }
            }
        }
        return new ImageView(wr).getImage();
//        return resample(new ImageView(wr).getImage(), SCALED_SIZE / DEFAULT_SIZE);
    }

    private Image resample(Image input, int scaleFactor) {
        final int W = (int) input.getWidth();
        final int H = (int) input.getHeight();
        final int S = scaleFactor;

        WritableImage output = new WritableImage(
                W * S,
                H * S
        );

        PixelReader reader = input.getPixelReader();
        PixelWriter writer = output.getPixelWriter();

        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                final int argb = reader.getArgb(x, y);
                for (int dy = 0; dy < S; dy++) {
                    for (int dx = 0; dx < S; dx++) {
                        writer.setArgb(x * S + dx, y * S + dy, argb);
                    }
                }
            }
        }

        return output;
    }

    public ImageView getImageView(double height, double width) {
        ImageView imageView = new ImageView();
        imageView.setImage(this.getFxImage());
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);

        return imageView;
    }
}
