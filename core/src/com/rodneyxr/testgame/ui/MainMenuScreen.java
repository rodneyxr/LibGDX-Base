package com.rodneyxr.testgame.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rodneyxr.testgame.TestGame;
import com.rodneyxr.testgame.services.InputManager;
import com.rodneyxr.testgame.tools.InputController;

/**
 * Created by Rodney on 1/5/2015.
 * <p/>
 * This screen is the main menu for the game.
 */
public class MainMenuScreen extends AbstractScreen {

    private TextButton optionsButton;

    public MainMenuScreen(TestGame game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        optionsButton = new TextButton("Options", skin);
        optionsButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                TestGame.log("%s: You clicked optionsButton.", getName());
                game.setScreen(new OptionsScreen(game));
            }
        });
        stage.addActor(optionsButton);
    }

    @Override
    public void addProcessor() {
        // listen for user input to return to main menu
        InputManager.addProcessor(new InputController() {

            @Override
            public boolean keyDown(int keycode) {

                switch (keycode) {

                    case Input.Keys.ESCAPE:
                    case Input.Keys.BACK:
                            Gdx.app.exit(); // this will close the game when back / escape is pressed at the main menu
                        return true;
                    default:
                        return false;
                }
            }
        });
    }
}
