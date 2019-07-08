package ozbank.ozbankapp.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import ozbank.ozbankapp.dto.ResponseDto;
import ozbank.ozbankapp.entity.Customers;
import ozbank.ozbankapp.exception.OzBankException;
import ozbank.ozbankapp.repository.ICustomerRepo;

@RunWith(SpringRunner.class)
public class TestCustomerServiceImpl {
	
	@InjectMocks
	CustomerServiceImpl customerServiceImpl;
	
	@Mock
	ICustomerRepo customerRepo;
	
	@Mock 
	ResponseDto r;
	
	
	@Test
	public void testAddNewCustomer() {
		
		Customers customer=new Customers();
		customer.setCustomerCreditScore(900);
		//customer.setCustomerDob("1999-05-02");
		customer.setCustomerExperienceInMonths(36);
		customer.setCustomerId(1L);
		customer.setCustomerName("Prabhanjan");
		r.setMessage("Customer Added SuccessFully in the Database...");
		Mockito.when(customerServiceImpl.addNewCustomer(Mockito.anyObject())).thenReturn(r);
		ResponseDto d=customerServiceImpl.addNewCustomer(customer);
		
		assertEquals(d.getMessage(),r.getMessage());
		
		
	}
	
	@Test(expected = OzBankException.class)
	public void testDeleteLoan() {
		customerServiceImpl.deleteLoan("234A");
		
	}
	@Test
	public void testDeleteLoan_1() {
		Long id=234L;
		//Mockito.when(Long.parseLong(Mockito.anyString())).thenReturn(id);
		customerServiceImpl.deleteLoan("234L");
		
	}

}
