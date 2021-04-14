package validators;

import java.util.Arrays;
import java.util.List;

import Constants.Constants;
import domain.ApplicationUser;

public class RoleValidator {
	private static String technicianRole = Constants.TECHNICIAN_ROLE;
	private static String supportManagerRole = Constants.SUPPORTMANAGER_ROLE;
	private static String administratorRole = Constants.ADMINISTRATOR_ROLE;
	
	private static boolean Validate(List<String> allowedRoles, ApplicationUser userToValidate) {
		if(userToValidate.getUserRoles() == null)
			return false;
		
		for(var role : userToValidate.getUserRoles()) {
			if(allowedRoles.contains(role.getName())) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean ValidateTechnician(ApplicationUser userToValidate) {
		return Validate(Arrays.asList(technicianRole), userToValidate);
	}
	
	public static boolean ValidateSupportManager(ApplicationUser userToValidate) {
		return Validate(Arrays.asList(supportManagerRole), userToValidate);
	}
	
	public static boolean ValidateAdministrator(ApplicationUser userToValidate) {
		return Validate(Arrays.asList(administratorRole), userToValidate);
	}
	
	public static boolean ValidateRolesAllowedInApplication(ApplicationUser userToValidate) {
		return Validate(Arrays.asList(technicianRole, supportManagerRole, administratorRole), userToValidate);
	}
}