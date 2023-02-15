package io.github.jwonz.citypulse;

import java.io.File;
import java.io.IOException;

import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.data.store.ReprojectingFeatureCollection;
import org.geotools.feature.FeatureCollection;
import org.geotools.geojson.feature.FeatureJSON;
import org.geotools.referencing.CRS;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

public class ShpToGeoJSON {
    public static void main(String[] args) {
        String shpFilePath = "C:\\Users\\wonse\\Documents\\repos\\city-pulse-backend\\data\\TAXPARCEL_CONDOUNITSTACK_LGIM.shp";
        String geoJSONFilePath = "C:\\Users\\wonse\\Documents\\repos\\city-pulse-backend\\data\\TAXPARCEL_CONDOUNITSTACK_LGIM_4326.json";

        try {
//            FileDataStore dataStore = FileDataStoreFinder.getDataStore(new File(shpFilePath));
//            SimpleFeatureSource featureSource = dataStore.getFeatureSource();
//            SimpleFeatureCollection featureCollection = featureSource.getFeatures();
//
//            FeatureJSON fjson = new FeatureJSON();
//            fjson.writeFeatureCollection(featureCollection, new File(geoJSONFilePath));
//
//            System.out.println("Shapefile has been successfully converted to GeoJSON.");


            File file = new File(shpFilePath);
            FileDataStore store = FileDataStoreFinder.getDataStore(file);
            SimpleFeatureSource featureSource = store.getFeatureSource();
            FeatureCollection<SimpleFeatureType, SimpleFeature> features = featureSource.getFeatures();

            CoordinateReferenceSystem inputCRS = featureSource.getInfo().getCRS();
            CoordinateReferenceSystem outputCRS = CRS.decode("EPSG:4326");

            FeatureCollection<SimpleFeatureType, SimpleFeature> reprojectedFeatures = new ReprojectingFeatureCollection(features, inputCRS, outputCRS);

            FeatureJSON io = new FeatureJSON();
            File output = new File(geoJSONFilePath);
            io.writeFeatureCollection(reprojectedFeatures, output);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}