package Controller;

import engine.Pair;
import maxxam.App;
import object.Enemy;

import java.util.*;

/**
 * 0: do nothing -> 1 2 3 5 6
 * -> actionStatus: time done = 0
 *
 * 1: destroy box -> 3
 * -> actionStatus: go particular o done
 *
 * 2: get item -> 0
 * -> actionStatus: go particular o done
 *
 * 3: store bomb -> 4
 * -> actionStatus: store bomb done
 *
 * 4: avoid bomb -> 0
 * -> actionStatus: go particular white o done
 *
 * 5: catch player -> 3
 * -> actionStatus: time done
 *
 * 6: rand -> 0
 * -> actionStatus: go particular o done
 *
 * 7: go up
 * 8: go down
 * 9: go left
 * 10: go right
 */
public class EnemyController {
    public static int[] x = {0, -1, 0, +1};
    public static int[] y = {-1, 0, +1, 0};
    public static Random random = new Random();

    /**
     * get a copy of map.
     * then mark o as 'd' if it is dangerous.
     */
    public static char[][] execute_map() {
        char[][] map = new char[App.gameWorld.height][App.gameWorld.width];
        for (int i = 0; i < App.gameWorld.height; i++) {
            if (App.gameWorld.width >= 0)
                System.arraycopy(App.gameWorld.sprite_map[i], 0, map[i], 0, App.gameWorld.width);
        }
        for (int i = 0; i < App.gameWorld.height; i++) {
            for (int j = 0; j < App.gameWorld.width; j++) {
                if (map[i][j] == 'b') {
                    // dangerous
                    map[i][j] = 'd';
                    int a, b;

                    a = i+1; b = j;
                    while (map[a][b] != '#' && map[a][b] != '*' && map[a][b] != 'b') {
                        map[a][b] = 'd';
                        a++;
                    }
                    a = i-1; b = j;
                    while (map[a][b] != '#' && map[a][b] != '*' && map[a][b] != 'b') {
                        map[a][b] = 'd';
                        a--;
                    }
                    a = i; b = j+1;
                    while (map[a][b] != '#' && map[a][b] != '*' && map[a][b] != 'b') {
                        map[a][b] = 'd';
                        b++;
                    }
                    a = i; b = j-1;
                    while (map[a][b] != '#' && map[a][b] != '*' && map[a][b] != 'b') {
                        map[a][b] = 'd';
                        b--;
                    }
                }
            }
        }
//        System.out.println(Arrays.deepToString(map));
        return map;
    }

    /**
     * get a random integer >=0 and <x
     */
    public static int rand_int_in(int x) {
        return Math.abs(random.nextInt()) % x;
    }


    /**
     * the object does nothing
     */
    public static void get_0(Enemy enemy) {
        enemy.action = 0;
        enemy.actionStatus = 3;
        enemy.done_action = false;
    }


    /**
     * get an o that is next to a box.
     * if not found, return action done.
     */
    public static void get_1(Enemy enemy, Pair pe, char[][] map) {
        enemy.action = 1;

        LinkedList<Pair> list = new LinkedList<>();
        list.add(pe);
        while (!list.isEmpty()) {
            Pair np = list.remove();
            if (map[np.y][np.x] != ' ' && map[np.y][np.x] != 'd')
                continue;
            if (map[np.y][np.x+1] == '*' ||
                    map[np.y][np.x-1] == '*' ||
                    map[np.y+1][np.x] == '*' ||
                    map[np.y-1][np.x] == '*') {
                enemy.pairAim = new Pair(np.x, np.y);
                enemy.done_action = false;
                return;
            }
            map[np.y][np.x] = '.';
            list.add(new Pair(np.x+1, np.y));
            list.add(new Pair(np.x-1, np.y));
            list.add(new Pair(np.x, np.y+1));
            list.add(new Pair(np.x, np.y-1));
        }

        enemy.done_action = true;
    }


    /**
     * get an o that has an item.
     * if not found, return action done.
     */
    public static void get_2(Enemy enemy, Pair pe, char[][] map) {
        enemy.action = 2;

        LinkedList<Pair> list = new LinkedList<>();
        list.add(pe);
        while (!list.isEmpty()) {
            Pair np = list.remove();
            if (map[np.y][np.x] == '#' || map[np.y][np.x] != '*')
                continue;
            if (map[np.y][np.x] == 'B' ||
                    map[np.y][np.x] == 'F' ||
                    map[np.y][np.x] == 'S') {
                enemy.pairAim = new Pair(np.x, np.y);
                enemy.done_action = false;
                return;
            }
            map[np.y][np.x] = '.';
            list.add(new Pair(np.x+1, np.y));
            list.add(new Pair(np.x-1, np.y));
            list.add(new Pair(np.x, np.y+1));
            list.add(new Pair(np.x, np.y-1));
        }

        enemy.done_action = true;
    }


    /**
     * set store bomb in the next step
     */
    public static void get_3(Enemy enemy) {
        enemy.action = 3;
        enemy.actionStatus = 0;
        enemy.done_action = false;
    }


    /**
     * get an o that be safe. if not found, return action done.
     */
    public static void get_4(Enemy enemy, Pair pe, char[][] map) {
        enemy.action = 4;

        LinkedList<Pair> list = new LinkedList<>();
        list.add(pe);
        while (!list.isEmpty()) {
            Pair np = list.remove();
            if (map[np.y][np.x] != ' ' && map[np.y][np.x] != 'd')
                continue;
            if (map[np.y][np.x] == ' ') {
                enemy.pairAim = new Pair(np.x, np.y);
                enemy.done_action = false;
                return;
            }
            map[np.y][np.x] = '.';
            list.add(new Pair(np.x+1, np.y));
            list.add(new Pair(np.x-1, np.y));
            list.add(new Pair(np.x, np.y+1));
            list.add(new Pair(np.x, np.y-1));
        }
        enemy.done_action = true;
    }

    /**
     * follow the player. if not found, return action done.
     */
    public static void get_5(Enemy enemy, Pair pe, Pair pp, char[][] map) {
        enemy.action = 5;

        LinkedList<Pair> list = new LinkedList<>();
        list.add(pe);
        while (!list.isEmpty()) {
            Pair np = list.remove();
            if (map[np.y][np.x] != ' ' && map[np.y][np.x] != 'd')
                continue;
            if (np.x == pp.x && np.y == pp.y) {
                enemy.actionStatus = 15;
                enemy.done_action = false;
                return;
            }
            map[np.y][np.x] = '.';
            list.add(new Pair(np.x+1, np.y));
            list.add(new Pair(np.x-1, np.y));
            list.add(new Pair(np.x, np.y+1));
            list.add(new Pair(np.x, np.y-1));
        }

        enemy.done_action = true;
    }


    /**
     * get a random o nearby enemy's o. if not satisfied, return action done.
     */
    public static void get_6(Enemy enemy, Pair pe, char[][] map) {
        enemy.action = 6;
        enemy.actionStatus = 0;

        int r = rand_int_in(4);
        enemy.pairAim = new Pair(pe.x + x[r], pe.y + y[r]) ;
        if (map[enemy.pairAim.y][enemy.pairAim.x] != ' ') {
            enemy.done_action = true;
            return;
        }
        enemy.done_action = false;
    }


    /**
     * when enemy does nothing
     */
    public static void exe_do_nothing(Enemy enemy) {
        if (enemy.actionStatus <= 0) {
            enemy.done_action = true;
        }
        enemy.actionStatus -= 1.0f/App.gameWorld.getFramesPerSecond();
    }


    /**
     * when enemy want to go a particular o... enemy.pairAim
     */
    public static void exe_go_to_a_o(Enemy enemy, Pair pe) {
        if (enemy.node.getTranslateX() % App.gameWorld.getScale() != 0 ||
                enemy.node.getTranslateY() % App.gameWorld.getScale() != 0) {
            return;
        }
        if ((int)enemy.node.getTranslateX() == (int)(App.gameWorld.getScale() * enemy.pairAim.x) &&
                (int)enemy.node.getTranslateY() == (int)(App.gameWorld.getScale() * enemy.pairAim.y)) {
            enemy.done_action = true;
            return;
        }
        Pair[][] pair_map = new Pair[App.gameWorld.height][App.gameWorld.width];
        char[][] map = execute_map();

        LinkedList<Pair> list = new LinkedList<>();
        list.add(pe);
        map[pe.y][pe.x] = '.';
        pair_map[pe.y][pe.x] = new Pair(pe.x, pe.y);

        while (!list.isEmpty()) {
            Pair np = list.remove();
            if (np.x == enemy.pairAim.x && np.y == enemy.pairAim.y) {
                // trace
                while (true) {
                    if (pair_map[np.y][np.x].x == pe.x &&
                        pair_map[np.y][np.x].y == pe.y) {
                        // decide direct
                        if (np.y == pe.y) {
                            if (np.x < pe.x) {
                                enemy.pressA = true;
                                enemy.pressW = false;
                                enemy.pressD = false;
                                enemy.pressS = false;
                            } else {
                                enemy.pressA = false;
                                enemy.pressW = false;
                                enemy.pressD = true;
                                enemy.pressS = false;
                            }
                        } else {
                            if (np.y < pe.y) {
                                enemy.pressA = false;
                                enemy.pressW = true;
                                enemy.pressD = false;
                                enemy.pressS = false;
                            } else {
                                enemy.pressA = false;
                                enemy.pressW = false;
                                enemy.pressD = false;
                                enemy.pressS = true;
                            }
                        }
                        break;
                    }
                    np = pair_map[np.y][np.x];
                }
                break;
            }
            if (map[np.y][np.x+1] == ' ' || map[np.y][np.x+1] == 'd') {
                list.add(new Pair(np.x+1, np.y));
                pair_map[np.y][np.x+1] = new Pair(np.x, np.y);
                map[np.y][np.x+1] = '.';
            }
            if (map[np.y][np.x-1] == ' ' || map[np.y][np.x-1] == 'd') {
                list.add(new Pair(np.x-1, np.y));
                pair_map[np.y][np.x-1] = new Pair(np.x, np.y);
                map[np.y][np.x-1] = '.';
            }
            if (map[np.y+1][np.x] == ' ' || map[np.y+1][np.x] == 'd') {
                list.add(new Pair(np.x, np.y+1));
                pair_map[np.y+1][np.x] = new Pair(np.x, np.y);
                map[np.y+1][np.x] = '.';
            }
            if (map[np.y-1][np.x] == ' ' || map[np.y-1][np.x] == 'd') {
                list.add(new Pair(np.x, np.y-1));
                pair_map[np.y-1][np.x] = new Pair(np.x, np.y);
                map[np.y-1][np.x] = '.';
            }
        }
    }

    /**
     * decide the direct which enemy will follow by changing the pressWASD
     */
    public static void getController(Enemy enemy) {
        // Position player
        Pair relativePairPlayer = new Pair(
                (int)(App.gameWorld.player.node.getTranslateX()/App.gameWorld.getScale() + 0.5f),
                (int)(App.gameWorld.player.node.getTranslateY()/App.gameWorld.getScale() + 0.5f)
        );

        // Position enemy
        Pair absolutePairEnemy = new Pair(
                (int)(enemy.node.getTranslateX()/App.gameWorld.getScale()),
                (int)(enemy.node.getTranslateY()/App.gameWorld.getScale())
        );

        // gen virtual map
        char[][] map = execute_map();

        if (enemy.done_action) {
            /*
             * 0: do nothing -> 1 2 3 5 6
             * -> actionStatus: time done = 0
             *
             * 1: destroy box -> 3
             * -> actionStatus: go particular o done
             *
             * 2: get item -> 0
             * -> actionStatus: go particular o done
             *
             * 3: store bomb -> 4
             * -> actionStatus: store bomb done
             *
             * 4: avoid bomb -> 0
             * -> actionStatus: go particular white o done
             *
             * 5: catch player -> 3
             * -> actionStatus: time done
             *
             * 6: rand -> 0
             * -> actionStatus: go particular o done
             */
            switch (enemy.action) {
                case 0:
                    switch (rand_int_in(5)) {
                        case 0:
                            get_1(enemy, absolutePairEnemy, map);
                            break;
                        case 1:
                            get_2(enemy, absolutePairEnemy, map);
                            break;
                        case 2:
                            get_3(enemy);
                            break;
                        case 3:
                            get_5(enemy, absolutePairEnemy, relativePairPlayer, map);
                            break;
                        case 4:
                            get_6(enemy, absolutePairEnemy, map);
                    }
                    break;
                case 1:
                case 5:
                    get_3(enemy);
                    break;
                case 2:
                case 4:
                case 6:
                    get_0(enemy);
                    break;
                case 3:
                    get_4(enemy, absolutePairEnemy, map);
            }
        } else {
            switch (enemy.action) {
                case 0:
                    exe_do_nothing(enemy);
                    break;
                case 1:
                case 2:
                case 4:
                case 6:
                    exe_go_to_a_o(enemy, absolutePairEnemy);
                    break;
                case 5:
                    if (enemy.actionStatus <= 0) {
                        enemy.done_action = true;
                        break;
                    }
                    enemy.actionStatus -= 1.0f/App.gameWorld.getFramesPerSecond();

                    enemy.pairAim = relativePairPlayer;
                    exe_go_to_a_o(enemy, absolutePairEnemy);
                    break;
                case 3:
                    enemy.storeBomb();
                    enemy.done_action = true;
            }
        }
    }
}
