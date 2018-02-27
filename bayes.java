package readRelap;
import java.util.*;
import weka.classifiers.*;
import weka.classifiers.bayes.NaiveBayes;
import java.io.*;
import weka.core.*;


class bayes{
	
	String trainpath = "C:\\Users\\lenovo\\Documents\\trainingset\\trainset.csv";
	String testpath = "C:\\Users\\lenovo\\Documents\\trainingset\\testset.csv";
	String trainpath1 = "C:\\Users\\lenovo\\Documents\\trainingset\\trainset1.csv";
	
	boolean predict(String trainpath,String testpath) throws Exception{
		
		BufferedReader breader = null;	
		Instances train = new Instances(
				new BufferedReader(
					new FileReader(trainpath)));
		train.setClassIndex(train.numAttributes()-1);
		
		NaiveBayes nb = new NaiveBayes();
		nb.buildClassifier(train);
		
		Instances test = new Instances(
                new BufferedReader(
                  new FileReader(testpath)));

		// set class attribute
		test.setClassIndex(test.numAttributes() - 1);
		
		// create copy
		Instances labeled = new Instances(test);
		
		// label instances
		for (int i = 0; i < test.numInstances(); i++) {
			double clsLabel = nb.classifyInstance(test.instance(i));
			labeled.instance(i).setClassValue(clsLabel);
		}
		// save labeled data
		BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\lenovo\\Documents\\trainingset\\hasil.csv"));
		
		String result = labeled.toString().split("\n")[labeled.toString().split("\n").length-1];
		
		if(result.split(",")[result.split(",").length-1].contains("Yes")){
			return true;
		}else{
			return false;
		}
	}
	
	void go()throws Exception{
		if(predict(trainpath,testpath)){
			System.out.println("There is a LBLOCA");
		}else{
			if(predict(trainpath1,testpath)){
				System.out.println("there is a SBLOCA");
			}else{
				System.out.println("normal");
			}
		}
	}
	
	
}

