package physics_sims;


public class Coffee_machine {
	private static enum coffeeTypes {javaChip, mocha, expresso};
	

	private static void getCoffee(coffeeTypes cType) {	
		if (cType.equals(coffeeTypes.javaChip)) {
			System.out.println("you have some really good coffee");
		}else {
			System.out.println("your coffee sucks");
		}
	}
	
	public static void main(String[] args) {
		coffeeTypes myCoffee = coffeeTypes.expresso; //create a new variable of the enum type
		
		getCoffee(myCoffee); //pass the enum into the function getCoffee(coffeeTypes cType)
	}
}