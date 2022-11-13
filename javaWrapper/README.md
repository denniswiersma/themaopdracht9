# Java Wrapper

This program only takes a single argument: an ARFF file containing new instances to classify. The program will output the new classified ARFF file to the CLI.
An example of unclassified data can be found at `../loggingAndReporting/data/nonClassifiedData`.
This program is compatible with java version 17.

## Usage

`java -jar <jar-file> <ARFF-file>`
example:
`java -jar build/libs/javaWrapper-0.1-all.jar ../loggingAndReporting/data/nonClassifiedData.arff`