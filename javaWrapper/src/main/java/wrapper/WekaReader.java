package wrapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import weka.classifiers.AbstractClassifier;
import weka.classifiers.Classifier;
import weka.classifiers.meta.AdaBoostM1;
import weka.classifiers.meta.CostSensitiveClassifier;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ArffLoader;
import weka.core.converters.ConverterUtils.DataSource;

class WekaReader {
    // final String modelFile = "j48.model";
    // final String unknownFile = "unknown_weather.arff";


    public static void main(String[] args) throws Exception {
        // STEP 2: load a model and use it
        new WekaReader().start();
    }

    private void start(String[] args) {
        String dataFile = args[0];
        System.out.println("Data File loaded:" + dataFile);
        try {
            AbstractClassifier fromFile = loadClassifier();
            Instances unknownInstances = loadArff(dataFile);
            unknownInstances.setClassIndex(1);
            classifyNewInstance(fromFile, unknownInstances);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void classifyNewInstance(AbstractClassifier model, Instances unknownInstances) throws Exception {
        // create copy
        Instances labeled = new Instances(unknownInstances);
        // label instances
        for (int i = 0; i < unknownInstances.numInstances(); i++) {
            double clsLabel = model.classifyInstance(unknownInstances.instance(i));
            labeled.instance(i).setClassValue(clsLabel);
        }
        System.out.println("\nNew, labeled = \n" + labeled);
    }


    private AbstractClassifier loadClassifier() throws Exception {
        // deserialize model
        String modelFile = "/BoostedCostSensitiveRandomForest.model";
        try {
            InputStream in = getClass().getResourceAsStream(modelFile);
            return (AbstractClassifier) SerializationHelper.read(in);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Instances loadArff(String datafile) throws IOException {
        try {
            DataSource source = new DataSource(datafile);
            Instances data = source.getDataSet();
            // setting class attribute if the data format does not provide this information
            // For example, the XRFF format saves the class attribute information as well
            if (data.classIndex() == -1)
                data.setClassIndex(data.numAttributes() - 1);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("could not read from file");
        }
    }
}