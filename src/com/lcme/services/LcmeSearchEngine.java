package lcme.services;

import lcme.config.Config;
import lcme.models.PageStore;
import lcme.models.Query;
import lcme.utils.Utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public final class LcmeSearchEngine
{

    public static Map<Query, Map<Integer, Integer>> getAllWebQueriesResults( ArrayList<Query> webQueries )
    {

        Map<Query, Map<Integer, Integer>> results = new LinkedHashMap<>();

        for ( Query webQuery : webQueries )
        {
            Map<Integer, Integer> webPageScores = LcmeSearchEngine.getAllWebPagesScoresForEach( webQuery );

            results.put( webQuery, webPageScores );
        }

        return results;
    }


    public static Map<Integer, Integer> getAllWebPagesScoresForEach( Query webQuery )
    {
        Map<Integer, Integer> webPageScores = new LinkedHashMap<>();

        ArrayList<Query> webPages = PageStore.getPages();

        int pageNumber = 1;

        for ( Query webPage : webPages )
        {
            int webPageScore = calculateScoreForEach( webPage, webQuery );

            if ( (webPageScore > Config.minimumWebPageScore) )
            {

                webPageScores.put( pageNumber, webPageScore );

            }

            pageNumber++;
        }

        Map<Integer, Integer> sortedWebPages = Utils.sortByValue( webPageScores );

        return getRelevantPages( sortedWebPages );
    }

    public static int calculateScoreForEach( Query webPage, Query webQuery )
    {

        int score = 0;

        ArrayList<String> queryKeywords = webQuery.getKeywords();

        ArrayList<String> pageKeyWords = webPage.getKeywords();

        for ( int i = 0; i < queryKeywords.size(); i++ )
        {
            for ( int j = 0; j < pageKeyWords.size(); j++ )
            {

                if ( Utils.compare( queryKeywords.get( i ), pageKeyWords.get( j ) ) )
                {

                    score += (Query.numberOfKeywords - i) * (Query.numberOfKeywords - j);

                }
            }
        }
        return score;
    }

    public static Map<Integer, Integer> getRelevantPages( Map<Integer, Integer> webPages )
    {

        Map<Integer, Integer> topWebPages = new LinkedHashMap<>();

        int maximumNumberOfWebPages = Config.maximumNumberOfWebPages;

        for ( Map.Entry<Integer, Integer> webPage : webPages.entrySet() )
        {
            if ( maximumNumberOfWebPages > 0 )

                topWebPages.put( webPage.getKey(), webPage.getValue() );

            maximumNumberOfWebPages--;
        }

        return topWebPages;
    }

    ;
}
