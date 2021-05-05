package repository;

import domain.Contract;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

public class ContractDaoJpa extends GenericDaoJpa<Contract> implements ContractDao  {

		public ContractDaoJpa() {
        super(Contract.class);
    }

    @Override
    public void createContract(Contract contract) throws EntityExistsException {
        try {
        	 em.getTransaction().begin();
             em.persist(contract);   
             em.getTransaction().commit();
            } catch (EntityExistsException ex) {
            throw new EntityExistsException();
        } 
    }
    
    @Override
    public void updateContract(Contract contract) throws EntityNotFoundException {
        try {   
        		em.getTransaction().begin();
        		em.merge(contract); 
        		em.getTransaction().commit();
            } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException();
        }
    }   
    
    @Override
    public List<Contract> getContracts() throws EntityNotFoundException{
    	try {
    		return em.createNamedQuery("Contract.getAll",Contract.class)
    		.getResultList();    		
    	} catch (EntityNotFoundException ex) {
    		throw new EntityNotFoundException();
    	}
    }
    
    @Override
    public Contract getContractById(int id) throws EntityNotFoundException{
    	try {
    		return em.createNamedQuery("Contract.getById",Contract.class)
    				.setParameter("id",id)
    				.getSingleResult();    		
    	} catch (EntityNotFoundException ex) {
    		throw new EntityNotFoundException();
    	}
    }
    
}
