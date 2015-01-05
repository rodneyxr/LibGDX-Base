package com.rodneyxr.testgame;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rodneyxr.testgame.ui.MainMenuScreen;

public class TestGame extends Game {

    private static final String LOG = TestGame.class.getSimpleName(); // this final String is used to LOG info in the console
    private static final String DEBUG = "DEBUG"; // this final String is used to log debugging info in the console

    private static boolean devMode = true; // tells whether we are in developer mode

    private SpriteBatch sb; // a single sprite batch to be used for the entire game

    public static boolean devMode() {
        return devMode;
    }

    public static void toggleDevMode() {
        setDevMode(!devMode);
    }

    public static void setDevMode(boolean flag) {
        devMode = flag;
        TestGame.log("devMode = %b", devMode);
    }

    /**
     * Easy logging method
     *
     * @param message Message
     */
    public static void log(String message) {
        Gdx.app.log(LOG, message);
    }

    /**
     * Easy formatted logging method
     *
     * @param format Format
     * @param args   Arguments
     */
    public static void log(String format, Object... args) {
        Gdx.app.log(LOG, String.format(format, args));
    }

    /**
     * Easy logging method for debugging
     *
     * @param message Message
     */
    public static void debug(String message) {
        if (devMode)
            Gdx.app.log(DEBUG, message);
    }

    /**
     * Easy formatted logging method for debugging
     *
     * @param format Format
     * @param args   Arguments
     */
    public static void debug(String format, Object... args) {
        if (devMode)
            Gdx.app.log(DEBUG, String.format(format, args));
    }

    public SpriteBatch getSpriteBatch() {
        if (sb == null)
            sb = new SpriteBatch();
        return sb;
    }

    @Override
    public void create() {
        TestGame.debug("Creating game");
        if (Gdx.app.getType().equals(Application.ApplicationType.Android)) {
            TestGame.debug("starting android version");
            setDevMode(false);
        } else {
            TestGame.debug("starting desktop version");
            setDevMode(true);
        }

        // let the application handle when the back key is pressed
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void resize(int width, int height) {
        TestGame.log("Resizing window to: " + width + "x" + height);
        super.resize(width, height);

        // call the first screen
        if (getScreen() == null) { // this avoids calling resize repeatedly
            setScreen(new MainMenuScreen(this));
        }
    }

    @Override
    public void pause() {
        TestGame.debug("Pausing game");
        super.pause();
    }

    @Override
    public void resume() {
        TestGame.debug("Resuming game");
        super.resume();
    }

    @Override
    public void dispose() {
        TestGame.debug("Disposing Test Game");
        super.dispose();
        if (sb != null)
            sb.dispose();
    }
}
