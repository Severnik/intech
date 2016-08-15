package com.intechtest.model;

/**
 * Created by Ekaterina Bogolepova on 11.08.2016.
 * Copyright (c) 2016 intech
 */
public class TextSearch extends AbstractModel {
    public static final String BUNDLE_KEY = "text_search";

    private String textSearch;

    public TextSearch(String textSearch) {
        this.textSearch = textSearch;
    }

    public String getTextSearch() {
        return textSearch;
    }

    public void setTextSearch(String textSearch) {
        this.textSearch = textSearch;
    }

    @Override
    protected String getBundleKey() {
        return BUNDLE_KEY;
    }
}
