package Gobject;

import Loader.Data;
import Support_Type.Pos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

import static Support_Type.Map.SIZE_X;
import static Support_Type.Map.SIZE_Y;

public class Core extends Gobject {
    double last_update = 15;

    public Core(Simple_Data data) {
        super(data);
    }

    public Core(double x, double y) {
        super(100, "center_core" + System.nanoTime(), x, y);
    }

    public Core(Pos pos) {
        super(100, "center_core" + System.nanoTime(), pos);
    }

    @Override
    public List<Gobject> update(double t) {
        if (index < 0) {
            using = false;
            return null;
        }
        if (last_update > 0) {
            last_update -= t;
        } else {
            last_update = 15;
            List<Gobject> rs = new ArrayList<>();
            int count = 30;
            int i = (int) Math.round(Math.random() * (SIZE_X - 1)), j = (int) Math.round(Math.random() * (SIZE_Y - 1));
            while (!current_map.get(new Pos(i, j)).isEmpty() && count > 0) {
                count--;
                i = (int) Math.round(Math.random() * (SIZE_X - 1));
                j = (int) Math.round(Math.random() * (SIZE_Y - 1));
            }
            if (count >= 0) {
                rs.add(new Appear(name + "'s child", new Pos(i, j)));
                return rs;
            }
        }
        return null;
    }

    @Override
    public Image render() {
        return Data.get("core");
    }

    public void drawIndexBar(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.fillRect(x, y - 0.1 * height, width * index / maxIndex, height * 0.15);

        if (last_update >= 0) {
            gc.setFill(Color.RED);
            gc.fillRect(x, y - 0.2 * height, width * (1 - last_update / 15), height * 0.15);
        } else {
            gc.setFill(Color.RED);
            gc.fillRect(x, y - 0.2 * height, width, height * 0.15);
        }
    }
}
