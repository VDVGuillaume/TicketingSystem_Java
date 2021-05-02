package controller;

import domain.Contract;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import repository.ContractDao;
import repository.ContractDaoJpa;

public class ContractController {
	private ContractDao contractRepo;
	private ObservableList<Contract> contracts;
	
	public ContractController() {
		contractRepo = new ContractDaoJpa();
		contracts = FXCollections.observableArrayList(contractRepo.getContracts());
	}
	
	public void createContract(Contract contract) {
		contractRepo.createContract(contract);
		contracts.add(contract);
	}
	
	public ObservableList<Contract> getContracts() {
    return FXCollections.unmodifiableObservableList(contracts);	
	}
	
	public void updateContract(Contract contract) {
		contractRepo.updateContract(contract);
	}
	
	public Contract getContractById(int id) {
		return contractRepo.getContractById(id);
	}
	
	public void addObserver(ListChangeListener<Contract> listener){
		contracts.addListener(listener);
	}
}
