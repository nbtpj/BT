module maxxam {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;

    opens maxxam to javafx.fxml;
    exports maxxam;
}