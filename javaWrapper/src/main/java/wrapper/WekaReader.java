package wrapper;

import java.io.IOException;
import java.io.InputStream;

import weka.classifiers.AbstractClassifier;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils.DataSource;

class WekaReader {

    /**
     * Initialises the class and calls the start method.
     * @param args arguments from the command line.
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        new WekaReader().start(args);
    }

    /**
     * Starts up the program by loading the ARFF file with instances to classify and
     * loads the classifier
     * @param args arguments from the command line.
     */
    private void start(String[] args) {
        String dataFile = args[0];
        System.out.println("Data File loaded:" + dataFile);
        try {
            // load the classifier
            AbstractClassifier fromFile = loadClassifier();
            // load the ARFF with unknown instances
            Instances unknownInstances = loadArff(dataFile);
            // set the class variable to be the last one in the dataset
            unknownInstances.setClassIndex(unknownInstances.numAttributes() - 1);
            // classify new instances
            classifyNewInstance(fromFile, unknownInstances);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void classifyNewInstance(AbstractClassifier model, Instances unknownInstances) throws Exception {
        // create copy of the new instances
        Instances labeled = new Instances(unknownInstances);
        // label instances according to the model's prediction
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