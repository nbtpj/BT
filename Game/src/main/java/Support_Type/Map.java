package Support_Type;


import Application.Game_World;
import Button.*;
import Gobject.*;
import Loader.Data;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Map implements Serializable {
    static public final int SIZE_X = 42, SIZE_Y = 22;
    public double score = 0;
    public double time_index;
    public boolean lost = false;
    public Bomber main_c = null;
    public boolean sound = false, music = false;
    public ImageView like = new ImageView(Data.get("like.gif"));
    public ImageView win = new ImageView(Data.get("win.gif"));
    public Text text = new Text();
    public List<Gobject>[][] data = new List[SIZE_X][SIZE_Y];
    public Canvas Frame;
    public Group graphic, pause;
    public Image background;
    public Core core;
    Resume resume;
    Stage stage;
    AudioClip bck, effect, explosion, cloning;
    Music music_b;
    Sound sound_b;
    private AnimationTimer timer;
    private final EventHandler<MouseEvent> e = new EventHandler<MouseEvent>() {
        public void handle(MouseEvent e) {
            Frame.toFront();
            timer.start();
        }
    };

    public Map(Stage stage) {
        this.stage = stage;
        bck = Data.getInstance().game_world_music;
        cloning = Data.getInstance().cloning;
        effect = Data.getInstance().effect;
        explosion = Data.getInstance().explosion;

        time_index = System.nanoTime();
        this.background = Data.getInstance().background;
        this.graphic = new Group();
        this.pause = new Group();
        this.Frame = new Canvas(SIZE_X * Pos.SIZE, SIZE_Y * Pos.SIZE);
        graphic.getChildren().add(pause);
        graphic.getChildren().add(Frame);

        pause.getChildren().add(new Quit(this, stage, Frame.getWidth() / 2 - Frame.getWidth() / 10, Frame.getHeight() * 3 / 5 - Frame.getHeight() / 16
                , Frame.getWidth() / 5, Frame.getHeight() / 8));
        resume = new Resume(Frame.getWidth() / 2 - Frame.getWidth() / 10, Frame.getHeight() * 1 / 5 - Frame.getHeight() / 16
                , Frame.getWidth() / 5, Frame.getHeight() / 8);
        resume.addEventHandler(MouseEvent.MOUSE_CLICKED, e);
        music_b = new Music(bck, Frame.getWidth() / 2 - Frame.getWidth() / 10, Frame.getHeight() * 4 / 5 - Frame.getHeight() / 16
                , Frame.getWidth() / 5, Frame.getHeight() / 8);
        sound_b = new Sound(Frame.getWidth() / 2, Frame.getHeight() * 4 / 5 - Frame.getHeight() / 16
                , Frame.getWidth() / 5, Frame.getHeight() / 8);
        sound_b.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> sound = !sound);
        pause.getChildren().add(music_b);
        pause.getChildren().add(sound_b);

        try {
            like.setFitHeight(Frame.getHeight());
            like.setFitWidth(Frame.getWidth());
            graphic.getChildren().add(like);
            like.toBack();
            win.setFitHeight(Frame.getHeight());
            win.setFitWidth(Frame.getWidth());
            graphic.getChildren().add(win);
            win.toBack();
            text.setFill(Color.BROWN);
            text.setStrokeWidth(10);
            text.setFont(Data.font);
            text.setWrappingWidth(200);
            text.setX(Frame.getWidth() * 3 / 4);
            text.setY(Frame.getHeight() * 1 / 4);
            text.setTextAlignment(TextAlignment.JUSTIFY);
            graphic.getChildren().add(text);
            text.toFront();
            text.setFill(Color.ORANGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        pause.getChildren().add(resume);
        Main_Menu menu = new Main_Menu(stage, Frame.getWidth() / 2 - Frame.getWidth() / 10, Frame.getHeight() * 2 / 5 - Frame.getHeight() / 16
                , Frame.getWidth() / 5, Frame.getHeight() / 8);
        pause.getChildren().add(menu);
        menu.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            try {
                save();
                bck.stop();
                (new Application.Main_Menu(music, sound)).turnOn(stage);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                data[i][j] = new ArrayList<>();
                if (i == 0 || i == SIZE_X - 1 || j == 0 || j == SIZE_Y - 1 || i * j % 2 == 1) {
                    data[i][j].add(new Wall(new Pos(i, j)));
                }
            }
        }
        this.random_Wall();
        this.setCore();

    }

    public Map(Stage stage, List<Gobject> data) {
        this.stage = stage;
        bck = Data.getInstance().game_world_music;
        cloning = Data.getInstance().cloning;
        effect = Data.getInstance().effect;
        explosion = Data.getInstance().explosion;
        time_index = System.nanoTime();
        this.background = Data.getInstance().background;
        this.graphic = new Group();
        this.pause = new Group();
        this.Frame = new Canvas(SIZE_X * Pos.SIZE, SIZE_Y * Pos.SIZE);
        graphic.getChildren().add(pause);
        graphic.getChildren().add(Frame);
        pause.getChildren().add(new Quit(this, stage, Frame.getWidth() / 2 - Frame.getWidth() / 10, Frame.getHeight() * 3 / 5 - Frame.getHeight() / 16
                , Frame.getWidth() / 5, Frame.getHeight() / 8));
        resume = new Resume(Frame.getWidth() / 2 - Frame.getWidth() / 10, Frame.getHeight() * 1 / 5 - Frame.getHeight() / 16
                , Frame.getWidth() / 5, Frame.getHeight() / 8);
        resume.addEventHandler(MouseEvent.MOUSE_CLICKED, e);
        music_b = new Music(bck, Frame.getWidth() / 2 - Frame.getWidth() / 10, Frame.getHeight() * 4 / 5 - Frame.getHeight() / 16
                , Frame.getWidth() / 5, Frame.getHeight() / 8);
        sound_b = new Sound(Frame.getWidth() / 2, Frame.getHeight() * 4 / 5 - Frame.getHeight() / 16
                , Frame.getWidth() / 5, Frame.getHeight() / 8);
        sound_b.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> sound = !sound);
        pause.getChildren().add(music_b);
        pause.getChildren().add(sound_b);

        try {
            like.setFitHeight(Frame.getHeight());
            like.setFitWidth(Frame.getWidth());
            graphic.getChildren().add(like);
            like.toBack();
            win.setFitHeight(Frame.getHeight());
            win.setFitWidth(Frame.getWidth());
            graphic.getChildren().add(win);
            win.toBack();
            text.setFill(Color.BROWN);
            text.setStrokeWidth(10);

            text.setFont(Data.font);
            text.setWrappingWidth(200);
            text.setX(Frame.getWidth() * 3 / 4);
            text.setY(Frame.getHeight() * 1 / 4);
            text.setTextAlignment(TextAlignment.JUSTIFY);
            graphic.getChildren().add(text);
            text.toFront();
            text.setFill(Color.ORANGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        pause.getChildren().add(resume);
        Main_Menu menu = new Main_Menu(stage, Frame.getWidth() / 2 - Frame.getWidth() / 10, Frame.getHeight() * 2 / 5 - Frame.getHeight() / 16
                , Frame.getWidth() / 5, Frame.getHeight() / 8);
        pause.getChildren().add(menu);
        menu.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {

            try {
                save();
                bck.stop();
                (new Application.Main_Menu(music, sound)).turnOn(stage);

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                this.data[i][j] = new ArrayList<>();
            }
        }

        for (Gobject o : data) {

            this.AddGobject(o);
        }

    }

    public static void main(String[] args) throws Exception {
        Map a = new Map(null);
        int i = 0;
        for (List<Gobject>[] x_Gobject : a.data
        ) {
            for (List<Gobject> y_Gobject : x_Gobject) {
                if (y_Gobject == null) {
                    i++;
                    System.out.println("null");
                }
            }

        }
        System.out.println(i);
    }

    public void setCore() {
        Core o = new Core(new Pos(SIZE_X / 2, SIZE_Y / 2));
        data[SIZE_X / 2][SIZE_Y / 2].clear();
        AddGobject(o);
        core = o;
    }

    public void setCore(Core c) {
        this.core = c;
        AddGobject(c);
    }

    public void setSoundnMusic(boolean sound, boolean music) {
        this.sound = sound;
        this.music = music;
        music_b.set(music);
        sound_b.set(sound);
    }

    public void random_Wall() {
        int count = 0;
        int enemy = 0;


        while (count < 100) {
            //    int r = (int) (Math.random() * 135);
            int i = (int) Math.round(Math.random() * (SIZE_X - 1)), j = (int) Math.round(Math.random() * (SIZE_Y - 1));
            boolean ct = false, kt = false;
            for (Gobject o : data[i][j]) {
                if (!ct && o instanceof Wall) ct = true;
                if (!kt && (o instanceof Movable_Object || ct)) kt = true;
            }
            if (!ct) {
                data[i][j].add(new Soft_Wall(new Pos(i, j)));
                count++;
            }
        }
        while (enemy < 5) {
            int i = (int) Math.round(Math.random() * (SIZE_X - 1)), j = (int) Math.round(Math.random() * (SIZE_Y - 1));
            boolean kt = false;
            for (Gobject o : data[i][j]) {
                if (o instanceof Movable_Object || o instanceof Wall) {
                    kt = true;
                    break;
                }
            }
            if (!kt) {
                Enemy e = new Enemy_1("enemy", new Pos(i, j));
                e.current_map = this;
                data[i][j].add(e);
                enemy++;
            }
        }
    }

    public void save() {
        if (!lost) {
            try {
                List<File> filesInFolder = Files.walk(Paths.get("Game/src/main/resources/data/"))
                        .filter(Files::isRegularFile)
                        .map(Path::toFile)
                        .collect(Collectors.toList());
                for (File f : filesInFolder) System.out.println("deleting " + f.getName() + ": " + f.delete());
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (Gobject o : getAll()) {

                try {
                    FileOutputStream fos = new FileOutputStream(Data.localFilePath + "data/" + o.name + System.nanoTime() + ".bin");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(new Simple_Data(o));
                    oos.close();
                } catch (IOException e) {
                    System.err.println("không ghi được cái: " + o.name);
                    e.printStackTrace();
                }
            }
        }
    }

    public Bomber getMain() {
        return main_c;
    }

    public void setMain(Bomber bomber) {
        this.main_c = bomber;
        this.AddGobject(bomber);
    }

    public List<Gobject> getAll() {
        List<Gobject> rs = new ArrayList<>();
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                rs.addAll(data[i][j]);
            }
        }
        return rs;
    }

    public List<Gobject> get(Pos x) {
        try {
            return data[x.x][x.y];
        } catch (Exception e) {
            return null;
        }
    }

    public void AddGobject(Gobject x) {
        x.current_map = this;
        System.out.println("adding " + x.name);
        try {
            Pos pos = x.pos();
            data[pos.x][pos.y].add(x);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    public void setTimer(AnimationTimer timer) {
        this.timer = timer;
    }

    public void pause() {
        Frame.getGraphicsContext2D().setGlobalAlpha(0.5);
        Frame.getGraphicsContext2D().drawImage(background, 0, 0, SIZE_X * Pos.SIZE, SIZE_Y * Pos.SIZE);
        pause.toFront();
    }

    public String Check(Pos x) {
        if ((x.x - SIZE_X) * (x.x) >= 0 || (x.y - SIZE_Y) * (x.y) >= 0) {
            return "Invalid";
        }
        for (Gobject m : this.get(x)) {
            if (m.invisible <= 0 && (m instanceof Bomb || m instanceof Wall || m instanceof Movable_Object)) {
                //   System.out.println("invalid");
                return "Invalid";
            }
        }
        return "Valid";
    }

    /**
     * update map and this 's Frame in the next t second(s)
     */
    public Canvas render(double t) {
        Frame.getGraphicsContext2D().setGlobalAlpha(1);
        Frame.getGraphicsContext2D().drawImage(background, 0, 0, SIZE_X * Pos.SIZE, SIZE_Y * Pos.SIZE);
        List<Gobject> New = new ArrayList<>(), New_Pos = new ArrayList<>();
        List<List<Gobject>> Old_Pos = new ArrayList<>();
        List<Gobject> new_, delete_;
        boolean elz = false, eff = false;
        /**
         * update all Object in map
         */
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                delete_ = new ArrayList<>();
                for (Gobject m : data[i][j]) {
                    if (m.using) {
                        new_ = m.update(t);
                        if (new_ != null && !new_.isEmpty()) {
                            New.addAll(new_);
                        }
                    } else {
                        if ((!elz) && m instanceof Bomb) {
                            elz = true;
                        }
                        if ((!eff) && m instanceof Effect && m.index > 0) {
                            eff = true;
                        }
                        delete_.add(m);
                    }
                    /**
                     * check the current valid current position of object
                     */
                    if (!m.pos().equals(i, j)) {
                        New_Pos.add(m);
                        Old_Pos.add(data[i][j]);
                    }
                }

                data[i][j].removeAll(delete_);
                delete_.clear();
            }
        }


        /**
         * add new objects (came from updating objects) to the map
         */
        boolean p = false;
        for (Gobject o : New) {
            this.AddGobject(o);
            if (sound && (o instanceof Enemy)) {
                p = true;
            }
        }

        if (p) {
            cloning.play();
        }
        if (elz && sound) {
            explosion.play();
        }
        if (eff && sound) {
            effect.play();
        }
        /**
         * replace objects that have invalid position
         */
        for (int i = 0; i < New_Pos.size(); i++) {
            Gobject o = New_Pos.get(i);
            this.get(o.pos()).add(o);
            Old_Pos.get(i).remove(o);
        }
        /**
         * render the image of all objects on the Frame
         */
        for (int i = 0; i < SIZE_X; i++) {
            for (int j = 0; j < SIZE_Y; j++) {
                for (Gobject m : data[i][j]) {
                    if (m.render() != null) {
                        if (m.invisible > 0) {
                            Frame.getGraphicsContext2D().setGlobalAlpha(0.6);
                        }
                        if (m instanceof Movable_Object) {
                            Frame.getGraphicsContext2D().drawImage(m.render(), m.x, m.y - Pos.SIZE / 2 + Pos.SIZE / 5);
                            m.drawIndexBar(Frame.getGraphicsContext2D());

                        } else {
                            Frame.getGraphicsContext2D().drawImage(m.render(), m.x, m.y, m.width, m.height);
                            if (m instanceof Core) m.drawIndexBar(Frame.getGraphicsContext2D());
                        }
                        Frame.getGraphicsContext2D().setGlobalAlpha(1);
                    }
                }
            }
        }

        if (!core.using) {
            timer.stop();
            new AnimationTimer() {
                final long start = System.nanoTime();

                public void handle(long currentNanoTime) {
                    if ((double) (currentNanoTime - start) / 1_000_000_000.0 >= 6) {
                        pause.toFront();
                        this.stop();
                    } else {
                        if ((double) (currentNanoTime - start) / 1_000_000_000.0 <= 2) {
                            Frame.getGraphicsContext2D().setGlobalAlpha((double) (currentNanoTime - start) / 2_000_000_000.0);
                            Frame.getGraphicsContext2D().drawImage(Data.get("game_over"), (SIZE_X * Pos.SIZE) / 4, (SIZE_Y * Pos.SIZE) / 4, (SIZE_X * Pos.SIZE) / 2
                                    , (SIZE_Y * Pos.SIZE) / 2);
                        } else {
                            Frame.getGraphicsContext2D().setGlobalAlpha(1);
                            Frame.getGraphicsContext2D().drawImage(background, 0, 0, SIZE_X * Pos.SIZE, SIZE_Y * Pos.SIZE);
                            win.toFront();
                        }

                    }
                }
            }.start();
            resume.removeEventHandler(MouseEvent.MOUSE_CLICKED, e);
            resume.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                try {
                    try {
                        List<File> filesInFolder = Files.walk(Paths.get("Game/src/main/resources/data/"))
                                .filter(Files::isRegularFile)
                                .map(Path::toFile)
                                .collect(Collectors.toList());
                        for (File f : filesInFolder) f.delete();
                    } catch (Exception E) {
                        E.printStackTrace();
                    }
                    (new Game_World(main_c.type, music, sound)).turnOn(stage);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
        }
        if (!main_c.using) {
            timer.stop();
            this.lost = true;
            new AnimationTimer() {
                final long start = System.nanoTime();

                public void handle(long currentNanoTime) {
                    if ((double) (currentNanoTime - start) / 1_000_000_000.0 >= 6) {
                        pause.toFront();
                        this.stop();
                    } else {
                        if ((double) (currentNanoTime - start) / 1_000_000_000.0 <= 2) {
                            Frame.getGraphicsContext2D().setGlobalAlpha((double) (currentNanoTime - start) / 2_000_000_000.0);
                            Frame.getGraphicsContext2D().drawImage(Data.get("game_over"), (SIZE_X * Pos.SIZE) / 4, (SIZE_Y * Pos.SIZE) / 4, (SIZE_X * Pos.SIZE) / 2
                                    , (SIZE_Y * Pos.SIZE) / 2);
                        } else {
                            Frame.getGraphicsContext2D().setGlobalAlpha(1);
                            Frame.getGraphicsContext2D().drawImage(background, 0, 0, SIZE_X * Pos.SIZE, SIZE_Y * Pos.SIZE);
                            like.toFront();
                            text.setText((int) main_c.score / 5 + " ");
                            text.toFront();
                        }

                    }
                }
            }.start();

        }
        return Frame;
    }
}
