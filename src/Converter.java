import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class Converter {

	public Converter() throws IOException {
		
		JFileChooser choose = new JFileChooser("C:\\Users\\cam80\\git\\Java-Fast-IO\\");
		choose.showOpenDialog(null);
		File file = choose.getSelectedFile();
		Scanner scan = new Scanner(file);
		
		List<String> lines = new ArrayList<>();
		while (scan.hasNextLine()){
			lines.add(scan.nextLine());
		}
		
		scan.close();
		
		System.out.println(file.getPath());
		FileWriter write = new FileWriter(new File(file.getPath() + "1"));
		//FileWriter write = new FileWriter(file);
		
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
		
		File classPath = new File("src/.classpath");
		scan = new Scanner(classPath);
		write = new FileWriter(new File(file.getPath() + "2"));
		
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
