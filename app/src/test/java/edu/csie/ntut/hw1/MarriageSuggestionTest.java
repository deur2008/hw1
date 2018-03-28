package edu.csie.ntut.hw1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MarriageSuggestionTest {
    private   MarriageSuggestion ms;

    @Before public void setUp(){
        ms = new MarriageSuggestion();
    }

    @After
    public void tearDown(){
        ms = null;
    }

    @Test
    public void testGetSuggestion() {
        assertEquals("建議：趕快結婚！", ms.getSuggestion("男", 1, 3));
        assertEquals("建議：趕快結婚！", ms.getSuggestion("男", 2, 3));
        assertEquals("建議：開始找對象", ms.getSuggestion("男", 3, 3));
        assertEquals("建議：趕快結婚！", ms.getSuggestion("男", 1, 5));
        assertEquals("建議：開始找對象", ms.getSuggestion("男", 2, 5));
        assertEquals("建議：趕快結婚！", ms.getSuggestion("男", 3, 5));
        assertEquals("建議：還不急", ms.getSuggestion("男", 1, 12));
        assertEquals("建議：還不急", ms.getSuggestion("男", 2, 12));
        assertEquals("建議：開始找對象", ms.getSuggestion("男", 3, 12));

        assertEquals("建議：趕快結婚！", ms.getSuggestion("女", 1, 3));
        assertEquals("建議：趕快結婚！", ms.getSuggestion("女", 2, 3));
        assertEquals("建議：開始找對象", ms.getSuggestion("女", 3, 3));
        assertEquals("建議：趕快結婚！", ms.getSuggestion("女", 1, 5));
        assertEquals("建議：開始找對象", ms.getSuggestion("女", 2, 5));
        assertEquals("建議：趕快結婚！", ms.getSuggestion("女", 3, 5));
        assertEquals("建議：還不急", ms.getSuggestion("女", 1, 12));
        assertEquals("建議：還不急", ms.getSuggestion("女", 2, 12));
        assertEquals("建議：開始找對象", ms.getSuggestion("女", 3, 12));

    }
}