//package engine;
//
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//
//import java.net.URL;
//import java.util.HashMap;
//import java.util.Map;
//
//public class ImageClass {
//    Map<String, Image> imageMap = new HashMap<>();
//
//    public ImageClass() {
//        loadImage("player", getClass().getResource("/maxxam/images/Player.png"));
//        loadImage("enemy", getClass().getResource("/maxxam/images/Enemy.png"));
//        loadImage("ground", getClass().getResource("/maxxam/images/Ground.png"));
//        loadImage("wall", getClass().getResource("/maxxam/images/Wall.png"));
//        loadImage("bomb", getClass().getResource("/maxxam/images/Bomb.png"));
//        loadImage("explore", getClass().getResource("/maxxam/images/Explore.png"));
//    }
//
//    public void loadImage(String id, URL url) {
//        if (url == null) {
//            System.out.println("Image url is null!");
//            return;
//        }
//        Image image = new Image(url.toExternalForm());
//        imageMap.put(id, image);
//    }
//
//    public Image getImage(String id) {
//        return imageMap.get(id);
//    }
//
//    public ImageView getImageView(String id, double height, double width) {
//        ImageView imageView = new ImageView();
//        imageView.setImage(getImage(id));
//        imageView.setFitHeight(height);
//        imageView.setFitWidth(width);
//
//        return imageView;
//    }
//}
