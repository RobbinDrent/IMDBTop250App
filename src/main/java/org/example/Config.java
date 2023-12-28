package org.example;

public class Config {

    private Config(){
        // Private constructor
    }

    //Javalin configuration
    public static final int PORT = 8080;

    // Endpoint
    public static final String IMDB_TOP_250 = "/top250";

    // Config
    public static final String IMDB_TOP_250_URL = "https://www.imdb.com/chart/top/?ref_=nv_mv_250";
    public static final String SUMMARY_ITEM_TAG = ".ipc-metadata-list-summary-item__c";
    public static final String TITLE_TAG = ".ipc-title__text";
    public static final String METADATA_TAG = ".sc-43986a27-7.dBkaPT.cli-title-metadata > span";
    public static final String IMDB_BASE_URL = "https://www.imdb.com";
    public static final String URL_WRAPPER_TAG = ".ipc-title-link-wrapper";
}
