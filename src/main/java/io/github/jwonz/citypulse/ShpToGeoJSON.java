package io.github.jwonz.citypulse;

import java.io.File;
import java.io.IOException;

import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.geojson.feature.FeatureJSON;

public class ShpToGeoJSON {
    public static void main(String[] args) {
        String shpFilePath = "C:\\Users\\wonse\\Documents\\repos\\city-pulse-backend\\data\\TAXPARCEL_CONDOUNITSTACK_LGIM.shp";
        String geoJSONFilePath = "C:\\Users\\wonse\\Documents\\repos\\city-pulse-backend\\data\\TAXPARCEL_CONDOUNITSTACK_LGIM.json";

        try {
            FileDataStore dataStore = FileDataStoreFinder.getDataStore(new File(shpFilePath));
            SimpleFeatureSource featureSource = dataStore.getFeatureSource();
            SimpleFeatureCollection featureCollection = featureSource.getFeatures();

            FeatureJSON fjson = new FeatureJSON();
            fjson.writeFeatureCollection(featureCollection, new File(geoJSONFilePath));

            System.out.println("Shapefile has been successfully converted to GeoJSON.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}