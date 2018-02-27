package readRelap;
import java.util.*;
import java.io.*;
import java.io.*;


class finder{
	final Scanner f;
	final Scanner f2;
	final ArrayList<String> smallf = new ArrayList<String>();
	
	String tobeWritten = "@relation whatever\n"+
							"@attribute mfe numeric\n"+
							"@attribute GB numeric\n"+
							"@attribute GTB numeric\n"+
							"@attribute SS numeric\n"+
							"@attribute Outcome {Yes,No}\n"+
							"@data\n";

	//constructor
	
	finder(String dir) throws IOException{
		InputStream file = new FileInputStream(dir);
		f = new Scanner(file);
		InputStream file2 = new FileInputStream(dir);
		f2 = new Scanner(file2);
	}
	
	double search(String keyword){
		
		ArrayList<String> l = new ArrayList<String>();
		int line = 0;
		int line2 = 0;
		
		if(smallf.size()==0){
			while(f.hasNextLine()){
				f.nextLine();
				line++;
			}
			
			//System.out.println(line);
			while(f2.hasNextLine()){
				String s = f2.nextLine();
				if(line2>=line-200){
					smallf.add(s);
				}
				line2++;
			}
		}
		
		
		for(String s: smallf){
			if(s.contains(" "+keyword+" " )){
				l.add(s);
			}
		}
		
		
		int ID = 0;
		switch(keyword){
		case "cormass": ID = 27;
						break;		
		case "upmass" : ID = 68;
						break;
		case "lpmass" : ID = 28;
						break;
		case "dnmass" : ID = 69;
						break;
		case "uhmass" : ID = 28;
						break;
		case "ihlmass": ID = 68;
						break;
		case "isgpmass" : ID = 28;
						break;
		case "iclpsmss" : ID = 65;
						break;
		case "iclpdmss" : ID = 28;
						break;
		case "presmass" : ID = 64;
						break;
		case "bhlmass" : ID = 26;
						break;
		case "bsgpmass" : ID = 64;
						break;
		case "bclpsmss" : ID = 28;
						break;
		case "bclpdmss" : ID = 63;
						break;
		}
		
		String buffer = l.get(l.size()-1);
		int baris = 0;
		String mass = null;
		for (String i : buffer.split(" ")){
			baris++ ;
			//System.out.println(i+ " " +baris);
			if(baris==ID){
				mass = i;
			}
		}
		
		l.clear();
		return Double.parseDouble(mass);
			
	}
	void write(String[] args) throws IOException{
		StringBuffer bread = new StringBuffer();
		for(String s: args){
			bread.append(search(s)+",");
		}
		bread.append("?");
		BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\lenovo\\Documents\\trainingset\\testset.csv"));
		writer.write(tobeWritten+"\n"+bread.toString());
		writer.newLine();
		writer.flush();
		writer.close();
	}
	
	
}

public class main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
	
		finder c = new finder("output of RELAP5-3D");
		
		System.out.println(c.search("bclpdmss"));
		System.out.println(c.search("cormass"));
		System.out.println(c.search("bhlmass"));
		System.out.println(c.search("dnmass"));
		String[] input = {"bclpdmss","cormass","bhlmass","dnmass"};
		c.write(input);
		
		bayes classifier = new bayes();
		classifier.go();
	
	}

}
