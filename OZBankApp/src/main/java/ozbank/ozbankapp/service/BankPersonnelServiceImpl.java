package ozbank.ozbankapp.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ozbank.ozbankapp.dto.ResponseDto;
import ozbank.ozbankapp.entity.BankingPersonnels;
import ozbank.ozbankapp.entity.Customers;
import ozbank.ozbankapp.entity.Emi;
import ozbank.ozbankapp.entity.Loans;
import ozbank.ozbankapp.exception.OzBankException;
import ozbank.ozbankapp.repository.EmiRepo;
import ozbank.ozbankapp.repository.IBankPersonnelRepo;
import ozbank.ozbankapp.repository.ILoanRepo;

@Service
public class BankPersonnelServiceImpl implements IBankPersonnelService {

	@Autowired
	IBankPersonnelRepo bankPersonnelRepo;
	@Autowired
	ILoanRepo loanRepo;
	@Autowired
	EmiRepo emiRepo;

	@SuppressWarnings({ "deprecation", "unused" })
	@Override
	public ResponseDto approveLoan(Long personnelId, Long loanId) throws ParseException {
		// TODO Auto-generated method stub
		BankingPersonnels bankingPersonnel;
		Loans loan;

		List<Emi> lstEmi = emiRepo.isAlreadySetOrNot(loanId);
		if (lstEmi != null && !lstEmi.isEmpty())
			throw new OzBankException("Emi for this Particular LoanId is Already set ...");

		try {
			bankingPersonnel = bankPersonnelRepo.findById(personnelId).get();
			loan = loanRepo.findById(loanId).get();
		} catch (NoSuchElementException ex) {
			throw new OzBankException("Please provide valid PersonnelId/LoanId");
		}
		Customers customer = loan.getCustomers();
		Date currentDate = new Date();
		int age = currentDate.getYear() - customer.getCustomerDob().getYear();
		if ("deleted".equalsIgnoreCase(String.valueOf(loan.getLoanStatus())))
			throw new OzBankException("This Customer's Loan is already Deleted...");
		else if (!"officer".equalsIgnoreCase(bankingPersonnel.getPersonnelDesignation()))
			throw new OzBankException("Only Officer can Approve the Loan... ");
		else if (customer.getCustomerCreditScore() <= 900)
			throw new OzBankException(" Credit Score is Low...!!");

		else if (customer.getCustomerExperienceInMonths() <= 24)
			throw new OzBankException("Work Experience is Less..");
		else if (!(age > 22 && age < 55))
			throw new OzBankException("Age criteria is not matchings ");
		loan.setBankingPersonnel(bankingPersonnel);
		loan.setLoanStatus("Approved");
		loanRepo.save(loan);
		Double monthlyEmi = getEmi(loan);
		Double totalRemainingToPay = monthlyEmi * loan.getLoanPayInYears() * 12;
		// List<Emi>lstEmi=new ArrayList<>();
		Date nextPaymentDate = setPaymentDate();
		int counter = 1;
		for (; monthlyEmi * counter <= totalRemainingToPay;) {
			Emi emi1 = new Emi();

			emi1.setEmiIntrestRate(loan.getLoanIntrestRate());
			emi1.setLoans(loan);
			emi1.setMonthylyEmi(monthlyEmi);
			emi1.setRestAmountOfLoan(totalRemainingToPay - (monthlyEmi * counter));
			// emi1.setRestAmountOfLoan(totalRemainingToPay - monthlyEmi);

			Calendar cal = Calendar.getInstance();
			cal.setTime(nextPaymentDate);
			cal.add(Calendar.MONTH, counter);
			System.out.println(cal.getTime());
			counter++;

			emi1.setEmiPaymentDate(cal.getTime());

			// totalRemainingToPay -= monthlyEmi;

			emiRepo.save(emi1);

		}
		return new ResponseDto("Loan is Approved");
	}

	private Date setPaymentDate() throws ParseException {
		DateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy");
		String basePayDate = "05/Jul/2019";
		Date payDate = formatter.parse(basePayDate);// new Date();

		Date paymentDate = formatter.parse(formatter.format(payDate));
		// System.out.print(paymentDate);
		// String s1=todayWithZeroTime.toLocaleString();
		// System.out.println(s1);
		// System.out.println(s1.substring(0, 11));

		// Calendar cal = Calendar.getInstance();
		// cal.setTime(todayWithZeroTime);
		// cal.add(Calendar.MONTH, 6);
		// System.out.print(cal.getTime());
		return paymentDate;

	}

	private Double getEmi(Loans loans) {

		Double monthlyIntrestRate = loans.getLoanIntrestRate() / (12 * 100);
		Double partRes = Math.pow((1 + monthlyIntrestRate), loans.getLoanPayInYears() * 12);
		Double monthlyEmi = (loans.getLoanAmount() * monthlyIntrestRate) / (1 - 1 / (partRes));
		// Double monthlyEmi=

		return monthlyEmi;

	}

}
