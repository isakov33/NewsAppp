package space.abdilazov.gruop39.data.locals;


import java.util.ArrayList;

import space.abdilazov.gruop39.models.News;

public class DataNews {
    private ArrayList<News> newsList;

    public DataNews() {
        newsList  = new ArrayList<>();
    }

    public ArrayList<News> getNewsList() {
        return newsList;
    }
}
