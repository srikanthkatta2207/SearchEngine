package lcme.utils;

import lcme.config.Config;
import lcme.models.PageStore;
import lcme.models.Query;
import lcme.models.WebPage;
import lcme.models.WebQuery;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;

public class QueryParser
{

    static ArrayList<Query> webQueries = new ArrayList<>();

    public static ArrayList<Query> getWebQueries( BufferedReader bufferedReader ) throws Exception
    {

        String line;

        while ( (line = bufferedReader.readLine()) != null )
        {
            ArrayList<String> keyWords = new ArrayList( Arrays.asList( line.split( Config.inputSeparator ) ) );

            if ( (keyWords.get( 0 ).toUpperCase()).equals( Config.webPageIdentifier ) )
            {

                keyWords.remove( 0 );

                Query query = new WebPage( keyWords );

                PageStore.add( query );
            }

            if ( (keyWords.get( 0 ).toUpperCase()).equals( Config.webQueryIdentifier ) )
            {

                keyWords.remove( 0 );

                Query query = new WebQuery( keyWords );

                webQueries.add( query );
            }

        }
        return webQueries;
    }
}
