package com.rodneyxr.testgame.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.rodneyxr.testgame.TestGame;

/**
 * Created by Rodney on 1/5/2015.
 * <p/>
 * This screen is the main menu for the game.
 */
public class MainMenuScreen extends AbstractScreen {

    private TextButton helloButton;

    public MainMenuScreen(TestGame game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        helloButton = new TextButton("Say Hello", skin);
        helloButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                TestGame.log("%s: You clicked helloButton.", getName());
            }
        });
        stage.addActor(helloButton);
    }

}
