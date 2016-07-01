import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class Converter {

	public Converter() throws IOException {
		
		String gitFolder = System.getProperty("user.home") + "\\git";
		
		//Get .project File
		JFileChooser choose = new JFileChooser(gitFolder);
		choose.showOpenDialog(null);
		File file = choose.getSelectedFile();
		Scanner scan = new Scanner(file);
		
		//Read project file
		List<String> lines = new ArrayList<>();
		while (scan.hasNextLine()){
			lines.add(scan.nextLine());
		}
		scan.close();
		
		System.out.println(file.getPath());
		FileWriter write = new FileWriter(file);
		
		//Write new .project file
		for (String line : lines){
			
			write.write(line + "\n");
			if (line.contains("<buildSpec>")){
				write.write("\t\t<buildCommand>\n");
				write.write("\t\t\t<name>org.eclipse.jdt.core.javabuilder</name>\n");
				write.write("\t\t\t<arguments>\n");
				write.write("\t\t\t</arguments>\n");
				write.write("\t\t</buildCommand>\n");
			}
			else if (line.contains("<natures>"))
				write.write("\t\t<nature>org.eclipse.jdt.core.javanature</nature>\n");
			
		}
		write.close();
		
		//Load .classpath file
		scan = new Scanner(Converter.class.getResourceAsStream("toCopy/classpath.txt"));
		
		//write new .classpath file
		write = new FileWriter(new File(file.getParent() + "\\.classpath"));
		while (scan.hasNextLine())
			write.write(scan.nextLine() + "\n");
		
		scan.close();
		write.close();
		
		//Create src folder
		File src = new File(file.getParent() + "\\src");
		src.mkdir();
		
		//Load Main.java
		scan = new Scanner(Converter.class.getResourceAsStream("toCopy/Main.txt"));
		
		//write new Main.java file
		write = new FileWriter(new File(src.getPath() + "\\Main.java"));
		while (scan.hasNextLine())
			write.write(scan.nextLine() + "\n");
		
		scan.close();
		write.close();	
	}

	public static void main(String[] args) {
		try {
			new Converter();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
