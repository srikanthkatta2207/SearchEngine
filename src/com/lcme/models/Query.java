package lcme.models;

import java.util.ArrayList;

public abstract class Query
{

    private ArrayList<String> keywords;

    public static int numberOfKeywords;

    public Query( ArrayList<String> keywords )
    {
        this.keywords = keywords;
    }


    public ArrayList<String> getKeywords()
    {
        return keywords;
    }
}
