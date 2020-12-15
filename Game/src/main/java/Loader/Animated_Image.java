package Loader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * code to split Image, but now they are not necessary
 */
public class Animated_Image {
    static public void splitAll() {
        try {
            List<File> filesInFolder = Files.walk(Paths.get("C:\\Users\\Nguyen Minh Quang\\Downloads\\Shikashi's Fantasy Icons Pack v2"))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
            for (File myObj : filesInFolder) {
                split(myObj, 32, 32);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    static public void split(File file, int size_x, int size_y) {
        int idx = 0;
        try {
            BufferedImage src = ImageIO.read(file);
            //   if(src.getWidth()!=32*3 || src.getHeight()!=32*4) return;
            for (int y = 0; y < src.getHeight(); y += size_y) {
                for (int x = 0; x < src.getWidth(); x += size_x) {
                    String file_name = file.getName().replace(".png", "").replace(" ", "")
                            + System.nanoTime();
                   /* switch (y){
                        case 0:
                            file_name += "_front_";
                            break;
                        case 32:
                            file_name += "_left_";
                            break;
                        case 64:
                            file_name += "_right_";
                            break;
                        case 96:
                            file_name += "_back_";
                            break;
                    }*
                    switch (x){
                        case 0:
                            file_name += "1";
                            break;
                        case 32:
                            file_name += "0";
                            break;
                        case 64:
                            file_name += "2";
                            break;
                    }*/
                    ImageIO.write(src.getSubimage(x, y, size_x, size_y), "png", new File("C:\\Users\\Nguyen Minh Quang\\Downloads\\Shikashi's Fantasy Icons Pack v2\\data\\" + file_name + ".png"));

                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Animated_Image.splitAll();
    }
}
