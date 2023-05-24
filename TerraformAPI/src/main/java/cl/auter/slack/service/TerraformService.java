package cl.auter.slack.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader; 

import org.springframework.stereotype.Service;

@Service
public class TerraformService {
	
	public String runCommand(String command) {
		
		ProcessBuilder processBuilder = new ProcessBuilder();
		StringBuilder output = new StringBuilder();
		processBuilder.command("bash", "-c", command);
		try {

			Process process = processBuilder.start();

			

			BufferedReader reader = new BufferedReader(
					new InputStreamReader(process.getInputStream()));

			String line;
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}

			int exitVal = process.waitFor();
			if (exitVal == 0) {
				System.out.println("Success!");
				System.out.println(output);
			} else {
				//abnormal...
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return output.toString();
	}
}
