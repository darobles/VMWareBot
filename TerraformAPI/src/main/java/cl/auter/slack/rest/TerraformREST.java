package cl.auter.slack.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.auter.slack.service.TerraformService;

import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/terraform")
public class TerraformREST {	
	
	@Autowired
	private TerraformService terraformService;	
	
	@GetMapping("/create/{name}")
	public String createTerraform(@PathVariable("name") String name)  {		
		String newdirectory = name.replaceAll(" ", "");
		//terraformService.runCommand("mkdir " + newdirectory);
		terraformService.runCommand("cp -r base vms/" + newdirectory);
		terraformService.runCommand("sudo sed -i \"/^[^#]*SictravDesarrollo/c\\  default = \\\"" + newdirectory + "\\\"\" /usr/local/terraform/vms/"+newdirectory+"/variables.tf");
		terraformService.runCommand("terraform -chdir=vms/"+ newdirectory +" init");
		String response = terraformService.runCommand("terraform -chdir=vms/"+ newdirectory +" apply -auto-approve");
		response = response.split("Outputs:")[2];
		response = response.replaceAll("\\]", " ");
		response = response.replaceAll("\\[", " ");
		String[] aux = response.split("ip =");
		String finalResponse = aux[1].replaceAll("\"", "").replaceAll(",", "");
		System.out.println("IP: " + finalResponse.trim());
		return "La VM "+ name + " fue creada con la siguiente IP: " + finalResponse.trim();
	}
	
	@GetMapping("/vms")
	public String listVMS()  {		
		String response = terraformService.runCommand("ls vms");
		return "Maquinas virtuales actuales: " + response;
	}
	
	@GetMapping("/destroy/{name}")
	public String destroyTerraform(@PathVariable("name") String name)  {		
		String response = terraformService.runCommand("terraform -chdir=vms/"+ name +" destroy -auto-approve");
		if(response.contains("Destroy complete! Resources: 1 destroyed."))
		{
			terraformService.runCommand("rm -rf vms/"+ name);
			return "Se ha destruido: " + name;	
		}
				
		return "Error al intentar destruir la VM " + name;	
	}
}