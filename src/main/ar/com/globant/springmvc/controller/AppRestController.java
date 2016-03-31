package main.ar.com.globant.springmvc.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import main.ar.com.globant.springmvc.model.Employee;
import main.ar.com.globant.springmvc.model.User;
import main.ar.com.globant.springmvc.model.UserProfile;
import main.ar.com.globant.springmvc.service.EmployeeService;
import main.ar.com.globant.springmvc.service.UserService;

@RestController
@RequestMapping("/api")
public class AppRestController {

	@Autowired
	EmployeeService service;
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/getUserDetails/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserDetails(@RequestParam("apiKey") String apiKey) {
		System.out.println("Tu Api Key es: " + apiKey);
		if (!checkApiKey(apiKey)){
			System.out.println("ApiKey inválida");
			return new ResponseEntity<User>(HttpStatus.FORBIDDEN);
		}
		
		System.out.println("Cargando usuario con apiKey: "+apiKey);
		User user = userService.findByApiKey(apiKey);
		
        if (user == null) {
        	System.out.println("No encontré usuario!");        
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        System.out.println("Encontré un usuario!");
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/employee/ssn/{ssn}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getEmployee(@PathVariable("ssn") String ssn, @RequestParam("apiKey") String apiKey) {
		System.out.println("Tu Api Key es: " + apiKey);
		if (!checkApiKey(apiKey)){
			return new ResponseEntity<Employee>(HttpStatus.FORBIDDEN);
		}
		
		System.out.println("Cargando el empleado con SSN: " + ssn);
        Employee employee = service.findEmployeeBySsn(ssn);
        if (employee == null) {
            System.out.println("No existe empleado con SSN "+ssn);
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/employee/id/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id, @RequestParam("apiKey") String apiKey) {
		System.out.println("Tu Api Key es: " + apiKey);
		if (!checkApiKey(apiKey)){
			return new ResponseEntity<Employee>(HttpStatus.FORBIDDEN);
		}
		
		System.out.println("Cargando el empleado con el id: " + id);
        Employee employee = service.findById(id);
        if (employee == null) {
            System.out.println("No existe empleado con el id " + id);
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/employee/", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> listAllEmployees(@RequestParam("apiKey") String apiKey) {
		if (!checkApiKey(apiKey)){
			return new ResponseEntity<List<Employee>>(HttpStatus.FORBIDDEN);
		}
		List<Employee> employees = service.findAllEmployees();
        if(employees.isEmpty()){
            return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }
	
	//@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/employee/", method = RequestMethod.POST)
    public ResponseEntity<Void> createEmployee(@RequestParam("apiKey") String apiKey, @RequestBody Employee employee, UriComponentsBuilder ucBuilder) {
		
		if (!checkApiKeyAdmin(apiKey)){
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
        System.out.println("Creando empleado: "+employee.getName());
 
        if (service.findEmployeeBySsn(employee.getSsn())!=null) {
            System.out.println("Ya existe un empleado con el ssn " + employee.getSsn());
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
 
        service.saveEmployee(employee);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/employee/{ssn}").buildAndExpand(employee.getSsn()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
	
	//@Secured("ROLE_ADMIN")	
	@RequestMapping(value = "/employee/ssn/{ssn}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> updateEmployee(@PathVariable("ssn") String ssn, @RequestParam("apiKey") String apiKey, @RequestBody Employee employee) {
		if (!checkApiKeyAdmin(apiKey)){
			return new ResponseEntity<Employee>(HttpStatus.FORBIDDEN);
		}
		
		System.out.println("Actualizando empleado " + ssn);
         
        Employee currentEmployee = service.findEmployeeBySsn(ssn);
         
        if (currentEmployee==null) {
            System.out.println("No se encontró usuario con el SSN: "+ssn);
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }

        if(!service.isEmployeeSsnUnique(employee.getId(), employee.getSsn())){
        	System.out.println("Ya existe otro empleado con el SSN: "+ssn);
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
        
        service.updateEmployee(employee);
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }
	
	//@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/employee/ssn/{ssn}", method = RequestMethod.DELETE)
    public ResponseEntity<Employee> deleteEmployee(@PathVariable("ssn") String ssn, @RequestParam("apiKey") String apiKey) {
		if (!checkApiKeyAdmin(apiKey)){
			return new ResponseEntity<Employee>(HttpStatus.FORBIDDEN);
		}
		
		System.out.println("Buscando y eliminando el empleado con SSN: " + ssn);
 
        Employee employee = service.findEmployeeBySsn(ssn);
        if (employee == null) {
            System.out.println("No se puede eliminar. No existe empleado con el SSN: "+ssn);
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
 
        service.deleteEmployeeBySsn(ssn);;
        return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
    }
	
	private boolean checkApiKeyAdmin(String apiKey){
		User user = userService.findByApiKey(apiKey);
		if (user != null) {
			Set<UserProfile> userProfiles = user.getUserProfiles();
			
			for(UserProfile userProfile : userProfiles){
				if(userProfile.getType().equals("ADMIN")) return true;	
			}
		} 
		return false;
	}
	
	private boolean checkApiKey(String apiKey){
		User user = userService.findByApiKey(apiKey);
		if (user != null) {
			return true;	
		}
		return false;
	}
	
}
