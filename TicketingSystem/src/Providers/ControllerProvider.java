package Providers;

import controller_interfaces.IDomainController;

public class ControllerProvider {

	private IDomainController domainController;
	
	public ControllerProvider(IDomainController domainController){
		this.domainController = domainController;
	}
	
	public IDomainController getDomainController(){
		return this.domainController;
	}	
}