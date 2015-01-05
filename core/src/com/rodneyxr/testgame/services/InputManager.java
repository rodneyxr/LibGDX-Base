package com.rodneyxr.testgame.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by Rodney on 1/5/2015.
 * <p/>
 * The input manager will handle all input processors using a static input multiplexer.
 */
public class InputManager {
    private static final InputMultiplexer inputMultiplexer = new InputMultiplexer();

    public static void addProcessor(InputProcessor processor) {
        inputMultiplexer.addProcessor(processor);
        update();
    }

    public static void remove(InputProcessor processor) {
        inputMultiplexer.removeProcessor(processor);
        update();
    }

    private static void update() {
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    public static void clear() {
        inputMultiplexer.clear();
    }
}
