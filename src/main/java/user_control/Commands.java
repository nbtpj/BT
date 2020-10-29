package user_control;

public class Commands {
    private String direction;
    private boolean set_bomb;

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public boolean isSet_bomb() {
        return set_bomb;
    }

    public void setSet_bomb(boolean set_bomb) {
        this.set_bomb = set_bomb;
    }

    public Commands(String direction, boolean set_bomb) {
        this.direction = direction;
        this.set_bomb = set_bomb;
    }
}
