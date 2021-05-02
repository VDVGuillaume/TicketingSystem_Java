package repository;
import domain.Contract;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

public interface ContractDao extends GenericDao<Contract>  {
        public void createContract(Contract contract) throws EntityExistsException;  
        public List<Contract> getContracts() throws EntityNotFoundException; 
        public void updateContract(Contract contract) throws EntityNotFoundException;
        public Contract getContractById(int id) throws EntityNotFoundException;
}