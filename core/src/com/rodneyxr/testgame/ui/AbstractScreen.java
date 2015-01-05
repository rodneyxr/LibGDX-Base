package com.rodneyxr.testgame.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.rodneyxr.testgame.TestGame;
import com.rodneyxr.testgame.services.InputManager;
import com.rodneyxr.testgame.tools.InputController;

/**
 * Created by Rodney on 1/5/2015.
 * <p/>
 * This abstract class it the base for all screens in the game.
 */
public abstract class AbstractScreen implements Screen {

    protected final TestGame game;
    protected final Stage stage;
    protected final SpriteBatch sb;
    protected final Skin skin;
    protected BitmapFont font;

    protected int width;
    protected int height;

    /**
     * Constructor
     *
     * @param game the main Game of the program
     */
    public AbstractScreen(TestGame game) {
        this.game = game;
        sb = game.getSpriteBatch();
        stage = new Stage();
        font = new BitmapFont();
        skin = new Skin(Gdx.files.internal("data/skin.json"));

    }

    /**
     * Gives you the simple name of the class
     *
     * @return a string containing the name of the class
     */
    protected String getName() {
        return ((Object) this).getClass().getSimpleName();
    }

    @Override
    public void render(float delta) {
        // update the stage
        stage.act();

        // draw back buffer
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // draw the stage
        stage.draw();

        sb.begin();
        if (TestGame.devMode())
            font.draw(sb, String.format("FPS: %d", Gdx.graphics.getFramesPerSecond()), 5, height - 5);
        sb.end();
    }

    @Override
    public void resize(int width, int height) {
        TestGame.log("Resizing screen: %s to: %d x %d", getName(), width, height);
        this.width = width;
        this.height = height;
    }

    @Override
    public void pause() {
        TestGame.debug("Pausing screen: " + getName());
    }

    @Override
    public void resume() {
        TestGame.debug("Resuming screen: " + getName());
    }

    @Override
    public void hide() {
        TestGame.debug("Hiding screen: " + getName());
    }

    @Override
    public void show() {
        TestGame.debug("Showing screen: " + getName());
        // set the stage as the input processor
        InputManager.addProcessor(stage);

        // listen for user input to return to main menu
        InputManager.addProcessor(new InputController() {

            @Override
            public boolean keyDown(int keycode) {

                switch (keycode) {

                    case Keys.ESCAPE:
                    case Keys.BACK:
                        if (game.getScreen() instanceof MainMenuScreen)
                            Gdx.app.exit(); // this will close the game when back / escape is pressed at the main menu
                        return true;

                    case Keys.GRAVE:
                    case Keys.F1:
                        TestGame.toggleDevMode(); // toggles developer mode
                        return true;

                    default:
                        return false;
                }
            }
        });
    }

    @Override
    public void dispose() {
        TestGame.debug("Disposing screen: " + getName());
        stage.dispose();
        skin.dispose();
        font.dispose();
        InputManager.clear();
    }

}
