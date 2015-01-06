package com.rodneyxr.testgame.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.rodneyxr.testgame.TestGame;
import com.rodneyxr.testgame.services.InputManager;
import com.rodneyxr.testgame.tools.InputController;

/**
 * Created by Matthew on 1/6/2015.
 * <p/>
 * Shows game options
 */

public class OptionsScreen extends AbstractScreen {

    private Label titleLabel;

    public OptionsScreen(TestGame game){
        super(game);
    }

    @Override
    public void show(){
        super.show();
        titleLabel = new Label("Options", skin);
        stage.addActor(titleLabel);
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
                            game.setScreen(new MainMenuScreen(game)); // this will close the game when back / escape is pressed at the main menu
                        return true;
                    default:
                        return false;
                }
            }
        });
    }
}
