import java.util.Scanner;

public class TaxCalculator {
	double taxRates[] = {0.1, 0.12, 0.22, 0.24, 0.32, 0.35, 0.37};
	int taxTableSingle[][] = {{0, 9700}, {9700, 39475}, {39475, 84200}, {84200, 160725}
							,{160725, 204100}, {204100, 510300}, {510300, Integer.MAX_VALUE}};
	int taxTableHeadHousehold[][] = {{0, 13850}, {13850, 52850}, {52850, 84200}, {84200, 160700}
							,{160700, 204100}, {204100, 510300}, {510300, Integer.MAX_VALUE}};
	int taxTableJointly[][] = {{0, 19400}, {19400, 78950}, {78950, 168400}, {168400, 321450}
							,{321450, 408200}, {408200, 612350}, {612350, Integer.MAX_VALUE}};
	int taxTableSeparately[][] = {{0, 9700}, {9700, 39475}, {39475, 84200}, {84200, 160725}
							,{160725, 204100}, {204100, 306175}, {306175, Integer.MAX_VALUE}};	
	int table[][];

	//calculate the tax by filing status and income
	public int calculateTax(int filingStatus, int income) {
		//get tax table by filing status
		if (filingStatus == 1) {
			table = taxTableSingle;
		} else if(filingStatus == 2){
			table = taxTableJointly;
		} else if(filingStatus == 3){
			table = taxTableSeparately;
		} else {
			table = taxTableHeadHousehold;
		}
		//calculate the tax
		int tax = 0;
		for(int i = 0;i < table.length;i++){
			if(income > table[i][1]){
				tax += (table[i][1] - table[i][0]) * taxRates[i];
			} else if(income <= table[i][1]){
				tax += (income - table[i][0]) * taxRates[i];
				break;
			}
		}
		return tax;
	}

	//ask user enter filing status and income, then calculate and output the tax
	public void enterData(){
		Scanner scanner = new Scanner(System.in);
		int filingStatus;
		int income;
		int tax;
		//display available filing status and ask user choose one status
		System.out.println("1 Single filers");
		System.out.println("2 Married filing jointly");
		System.out.println("3 Married filing separately");
		System.out.println("4 Head of household");
		System.out.print("Please enter filing status:");
		do{
			try {
				filingStatus = scanner.nextInt();				
			} catch (Exception e) {
				filingStatus = 0;
			}
			scanner.nextLine();
			//need enter again if input status is invalid
			if(filingStatus < 1 || filingStatus > 4){
				System.out.print("Invalid filing status, please enter between 1 and 4:");
			}
		} while(filingStatus < 1 || filingStatus > 4);
		
		//ask user enter income
		System.out.print("Please enter income:");
		try {
			income = scanner.nextInt();				
		} catch (Exception e) {
			income = 0;
		}		
		scanner.nextLine();
		//calculate tax and output the tax due
		tax = calculateTax(filingStatus, income);
		System.out.println("tax due: " + tax);
		scanner.close();
	}
	
	public static void main(String[] args) {
		TaxCalculator taxCalculator = new TaxCalculator();
		taxCalculator.enterData();
	}
}
