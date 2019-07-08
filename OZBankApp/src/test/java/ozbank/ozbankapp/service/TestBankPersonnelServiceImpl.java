package ozbank.ozbankapp.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import ozbank.ozbankapp.entity.BankingPersonnels;
import ozbank.ozbankapp.entity.Emi;
import ozbank.ozbankapp.entity.Loans;
import ozbank.ozbankapp.exception.OzBankException;
import ozbank.ozbankapp.repository.EmiRepo;
import ozbank.ozbankapp.repository.IBankPersonnelRepo;
import ozbank.ozbankapp.repository.ILoanRepo;

@RunWith(SpringRunner.class)
public class TestBankPersonnelServiceImpl {

	@InjectMocks
	BankPersonnelServiceImpl bankPersonnelServiceImpl = new BankPersonnelServiceImpl();

	@Mock
	IBankPersonnelRepo bankPersonnelRepo;
	@Mock
	ILoanRepo loanRepo;
	@Mock
	EmiRepo emiRepo;
	
	@Mock
	BankingPersonnels bankingPersonnels;
	
	@Mock
	Loans loan;
	
	@Test(expected = OzBankException.class)
	public void testApproveLoan() throws ParseException {
		
		Emi emi=new Emi();
		emi.setEmiId(1L);
		emi.setEmiIntrestRate(12.0);
		emi.setMonthylyEmi(5000.0);
		List<Emi> emiList=new ArrayList<>();
			
		Mockito.when(emiRepo.isAlreadySetOrNot(Mockito.anyLong())).thenReturn(emiList);
		bankPersonnelServiceImpl.approveLoan(100L, 1L);
		
		
	}
	@Test(expected = OzBankException.class)
	public void testApproveLoan1() throws ParseException {
		
		Emi emi=new Emi();
		emi.setEmiId(1L);
		emi.setEmiIntrestRate(12.0);
		emi.setMonthylyEmi(5000.0);
		List<Emi> emiList=new ArrayList<>();
		emiList.add(emi);
		
		BankingPersonnels bankingPersonnels=new BankingPersonnels();
		bankingPersonnels.setPersonnelDesignation("officer");
		bankingPersonnels.setPersonnelsId(100L);
		
	
		loan.setLoanId(1L);
		loan.setLoanAmount(6000000.0);
		
		
		Mockito.when(emiRepo.isAlreadySetOrNot(Mockito.anyLong())).thenReturn(emiList);
		Mockito.when(bankPersonnelRepo.findById(Mockito.anyLong()).get()).thenReturn(bankingPersonnels);
		Mockito.when(loanRepo.findById(Mockito.anyLong()).get()).thenReturn(loan);
		bankPersonnelServiceImpl.approveLoan(100L, 1L);
		
		
	}
	@Test
	public void testApproveLoan2() throws ParseException {
		
		Emi emi=new Emi();
		emi.setEmiId(1L);
		emi.setEmiIntrestRate(12.0);
		emi.setMonthylyEmi(5000.0);
		List<Emi> emiList=new ArrayList<>();
		emiList.add(emi);
		
		BankingPersonnels b1=new BankingPersonnels();
		b1.setPersonnelDesignation("officer");
		b1.setPersonnelsId(100L);
		
		Loans loan=new Loans();
		loan.setLoanId(1L);
		loan.setLoanAmount(6000000.0);
		
		
		Mockito.when(emiRepo.isAlreadySetOrNot(Mockito.anyLong())).thenReturn(emiList);
		Mockito.when(bankPersonnelRepo.findById(Mockito.anyLong()).get()).thenReturn(b1);
		Mockito.when(loanRepo.findById(Mockito.anyLong()).get()).thenReturn(loan);
		bankPersonnelServiceImpl.approveLoan(100L, 1L);
		
		
	}


}
