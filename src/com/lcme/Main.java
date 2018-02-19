package lcme;

import lcme.config.Config;
import lcme.models.Query;
import lcme.services.LcmeSearchEngine;
import lcme.utils.QueryParser;
import lcme.utils.Utils;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Main
{

    public static void main( String[] args ) throws Exception
    {

        try
        {
            System.out.println("Enter maximum number of keywords for query: ");

            Scanner scanner = new Scanner( System.in );

            Query.numberOfKeywords = scanner.nextInt();

            BufferedReader bufferedReader = Utils.readInputFrom( Config.inputPath );

            ArrayList<Query> webQueries = QueryParser.getWebQueries( bufferedReader );

            Map<Query, Map<Integer, Integer>> webQueriesResults = LcmeSearchEngine.getAllWebQueriesResults( webQueries );

            print( webQueriesResults );
        }
        catch ( Exception e )
        {
            System.out.println( e );
        }
    }

    public static void print( Map<Query, Map<Integer, Integer>> webQueriesResults )
    {

        int webQueryNumber = 1;

        for ( Map.Entry<Query, Map<Integer, Integer>> queryResult : webQueriesResults.entrySet() )
        {
            System.out.print( Config.webQueryIdentifier + webQueryNumber + ":" );

            for ( Map.Entry<Integer, Integer> webPageScore : queryResult.getValue().entrySet() )
            {

                System.out.print( Config.webPageIdentifier + webPageScore.getKey() + Config.defaultDelimiter );
            }

            webQueryNumber++;

            System.out.print( "\n" );
        }
    }

}
