module maxxam {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens maxxam to javafx.fxml;
    exports maxxam;
}