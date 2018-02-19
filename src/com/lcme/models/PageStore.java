package lcme.models;

import java.util.ArrayList;

public final class PageStore
{
    static ArrayList<Query> pages = new ArrayList<>();

    public static void add( Query query )
    {

        pages.add( query );
    }

    public static ArrayList<Query> getPages()
    {
        return pages;
    }
}
