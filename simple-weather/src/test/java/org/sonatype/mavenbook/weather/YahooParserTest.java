package org.sonatype.mavenbook.weather;

import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class YahooParserTest {
    @Test
    public void testParser() throws Exception {
        InputStream nyData = getClass().getClassLoader().getResourceAsStream("ny-weather.xml");
        Weather weather = new YahooParser().parse(nyData);
        assertEquals("New York", weather.getCity());
        assertEquals("NY", weather.getRegion());
        assertEquals("US", weather.getCountry());
        assertEquals("39", weather.getTemp());
        assertEquals("Fair", weather.getCondition());
        assertEquals("39", weather.getChill());
        assertEquals("67", weather.getHumidity());
    }
}
