import java.util.Scanner;

public class Calculator{
	public native double add(double a, double b);
	public native double sub(double a, double b);
	public native double mul(double a, double b);
	public native double div(double a, double b);

	static{
		System.load("/home/shubham/DynamicLinkLibrary/calc/calculator.so");
	}

	public static void main(String[] args) {
		Calculator calc = new Calculator();
		Scanner cin = new Scanner(System.in);
		int ch;
		double num1, num2;

		do{
			System.out.println("\nMENU");
			System.out.println("1. Addition");
			System.out.println("2. Subtraction");
			System.out.println("3. Multiplication");
			System.out.println("4. Division");
			System.out.println("0. Exit");
			System.out.print("\nEnter Choice : ");
			ch = cin.nextInt();

			switch(ch){
				case 1:
				System.out.print("\nEnter Number 1 : ");
				num1 = cin.nextInt();
				System.out.print("\nEnter Number 2 : ");
				num2 = cin.nextInt();
				System.out.println("\nResult is : " + calc.add(num1, num2));
				break;

				case 2:
				System.out.print("\nEnter Number 1 : ");
				num1 = cin.nextInt();
				System.out.print("\nEnter Number 2 : ");
				num2 = cin.nextInt();
				System.out.println("\nResult is : " + calc.sub(num1, num2));
				break;
				
				case 3:
				System.out.print("\nEnter Number 1 : ");
				num1 = cin.nextInt();
				System.out.print("\nEnter Number 2 : ");
				num2 = cin.nextInt();
				System.out.println("\nResult is : " + calc.mul(num1, num2));
				break;

				case 4:
				System.out.print("\nEnter Number 1 : ");
				num1 = cin.nextInt();
				System.out.print("\nEnter Number 2 : ");
				num2 = cin.nextInt();
				System.out.println("\nResult is : " + calc.div(num1, num2));
				break;
				
				case 0:
				System.out.println("Thank You!");
				break;
				
				default:
				System.out.println("Enter Valid Choice");
				break;
			}
		}while(ch!=0);
	}
}