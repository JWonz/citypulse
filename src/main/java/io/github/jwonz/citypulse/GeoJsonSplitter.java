//package io.github.jwonz.citypulse;
//
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ArrayNode;
//
//public class GeoJsonSplitter {
//
//    public static void main(String[] args) throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode rootNode = mapper.readTree(new FileReader("file.geojson"));
//
//        ArrayNode features = (ArrayNode) rootNode.get("features");
//        int featureCount = features.size();
//
//        int partSize = 5000;
//        int parts = (int) Math.ceil(featureCount / partSize);
//
//        for (int i = 0; i < parts; i++) {
//            int startIndex = i * partSize;
//            int endIndex = Math.min(featureCount, startIndex + partSize);
//
//            ArrayNode partFeatures = mapper.createArrayNode();
//            for (int j = startIndex; j < endIndex; j++) {
//                partFeatures.add(features.get(j));
//            }
//
//            JsonNode partRoot = mapper.createObjectNode();
//            ((ObjectNode) partRoot).set("features", partFeatures);
//
//            FileWriter writer = new FileWriter("part_" + i + ".geojson");
//            mapper.writeValue(writer, partRoot);
//            writer.close();
//        }
//    }
//}