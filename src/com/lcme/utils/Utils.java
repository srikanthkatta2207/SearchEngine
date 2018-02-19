package lcme.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class Utils
{
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue( Map<K, V> map )
    {
        List<Map.Entry<K, V>> list = new LinkedList<>( map.entrySet() );
        Collections.sort( list, new Comparator<Map.Entry<K, V>>()
        {
            @Override
            public int compare( Map.Entry<K, V> e1, Map.Entry<K, V> e2 )
            {
                return (e2.getValue()).compareTo( e1.getValue() );
            }
        } );

        Map<K, V> result = new LinkedHashMap<>();
        for ( Map.Entry<K, V> entry : list )
        {
            result.put( entry.getKey(), entry.getValue() );
        }

        return result;
    }

    public static BufferedReader readInputFrom( String path ) throws Exception
    {

        File file = new File( path );

        FileReader fileReader = new FileReader( file );

        return new BufferedReader( fileReader );
    }

    ;

    public static boolean compare( String word, String anotherWord )
    {

        return (word.toUpperCase()).equals( anotherWord.toUpperCase() );
    }

    ;

}
