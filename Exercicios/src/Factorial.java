
public class Factorial {
	
public static void main(String[] args) {
		
		for (long i=1; i<=40; i++){
			long resultado = 1;
			for(long factorial=1; factorial<=i; factorial++){
				resultado = resultado * factorial;
			}
			System.out.println(resultado);
		}
	}

}
