# themaopdracht9

This is a project (to be) carried out with the intent of exploring different aspects of data mining (DM) and machine learning (ML).
To study these DM and ML concepts a dataset, in this case regarding heart disease, is to be analysed. This process consists of these following steps:
- Stating project goals & formulating research questions.
- Performing an exploratory data analysis (EDA).
- Preparing/cleaning the dataset.
- Describing the EDA and data preparation results.
- Defining ML algorithm quality metrics & investigate performance.
- Select & optimise algorithms.
- Create a Java wrapper for the ML model. 
- Write a report & project proposal for minor.

## Dataset

Cardiovascular disease gets more prominent by the day. As one of the leading causes of death in the developed world it is an important issue to examine. Getting to know more about this matter and what factors impact risk of complications can therefore be rather interesting.  
The dataset used in this research project contains data from an ongoing cardiovascular study on residents of the town of Framingham, Massachusetts. It contains over 4000 records and 15 variables.  
A detailed summary of the data and it's variables can be found in the code book file. 
The dataset used for this project can be found [here](https://www.kaggle.com/datasets/dileep070/heart-disease-prediction-using-logistic-regression).

### Dataset inconsistencies

The dataset mentioned above was found on Kaggle. There are a few problems with this data. First off there is the "education" variable. This variable consists of values 1 through 4, presumably to indicate education levels. However, what these numbers represent is not explained anywhere in the dataset's description. Thus far it is still unclear what exactly these values specify.  
Also included in this dataset are some numeric variables, described on the dataset's page as "continuous". These values all lack a description of their units. Where a variable's unit can sometimes easily be interpreted, like with heart rate, other times it can mean we end up with no unit whatsoever.  
The source for this data mentioned on the Kaggle page links to another page on Kaggle. Nonetheless, this page no longer exists. We do however know that this data originates from the [Framingham Heart Study](https://www.framinghamheartstudy.org/) (FHS). Searching through this website for our missing units led to [this page](https://www.framinghamheartstudy.org/fhs-for-researchers/data-available-overview/) about available FHS data. From there we find a page on [FHS Dataset Inventory for Public Viewing](https://wwwapp.bumc.bu.edu/FHSresapp/Public/DataSetList) which lists all datasets which can be requested from the FHS team. This page includes documentation on variables. This sounds like progress but there are fifty pages of datasets to choose from. Finding out which of these datasets was used to source the data used in this project is too time intensive. Therefore, there is no other choice but to work with what we have and use the data without it's corresponding units.

## Filestructure 

```bash
themaopdracht9
├── javaWrapper
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── wrapper
│       │   │       ├── Main.java
│       │   │       └── WekaReader.java
│       │   └── resources
│       │       └── BoostedCostSensitiveRandomForest.model
├── loggingAndReporting
│   ├── CodeBook.txt
│   ├── ProjectLog
│   │   ├── ProjectLog.Rmd
│   │   ├── ProjectLog.pdf
│   ├── data
│   │   ├── BoostedCostSensitiveRandomForest.model
│   │   ├── SMOTEprocessedData.arff
│   │   ├── framingham.csv
│   │   ├── nonClassifiedData.arff
│   │   ├── processedData.arff
│   │   ├── processedData.csv
│   │   └── roundedSMOTEprocessedData.arff
│   ├── experiments
│   │   ├── AttributeSelecter.exp
│   │   ├── RandomForestBagging.exp
│   │   ├── RandomForestBoosting.exp
│   │   ├── RandomForestOptimiseIterations.exp
│   │   ├── RedoCostMatrix.exp
│   │   ├── SMOTEcostSensitivedefaultSettings.exp
│   │   ├── SMOTEdefaultSettings.exp
│   │   ├── ZeroROptimiseBucketSize.exp
│   │   ├── defaultSettings.exp
│   │   └── results
│   │       ├── AttributeSelecterResult.arff
│   │       ├── RandomForestBaggingResult.arff
│   │       ├── RandomForestBoostingResult.arff
│   │       ├── RandomForestOptimiseIterationsResult.arff
│   │       ├── RedoCostMatrixResult.arff
│   │       ├── SMOTEcostSensitivedefaultSettingsResult.arff
│   │       ├── SMOTEdefaultSettingsResult.arff
│   │       ├── ZeroROptimiseBucketSizeResult.arff
│   │       └── defaultSettingsResult.arff
│   └── report
│       ├── report.Rmd
│       └── report.pdf
├── .gitignore
├── LICENSE
└── README.md
```