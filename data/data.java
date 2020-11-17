public class data{
    public static ImageView bomb() throws FileNotFoundException {
        ImageView rs = new ImageView(new Image(new FileInputStream("data\\Bomb\\bomb.gif")));
        rs.setFitWidth(W);
        rs.setFitHeight(H);
        return rs;
    }
    public static ImageView explose() throws FileNotFoundException {
        ImageView rs = new ImageView(new Image(new FileInputStream("data\\Bomb\\explose.gif")));
        rs.setFitWidth(W);
        rs.setFitHeight(H);
        return rs;
    }
}