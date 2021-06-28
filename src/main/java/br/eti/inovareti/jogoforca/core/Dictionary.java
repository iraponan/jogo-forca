package br.eti.inovareti.jogoforca.core;

import java.lang.reflect.Constructor;

public abstract class Dictionary {

    private static Dictionary instance;

    public static Dictionary getInstance() {
        if (instance == null) {
            try {
                String dictionaryClassName = Config.get("dictionaryClassName");
                Class<?> aClass = Class.forName(dictionaryClassName);
                Constructor<?> constructor = aClass.getConstructor();
                instance = (Dictionary) constructor.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    public abstract Word nextWord();

    public abstract String getName();
}
