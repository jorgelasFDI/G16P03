package auxiliar.vuelo;

import auxiliar.MyRandom;

public class RandomGenerator {
	public static String generateRandomString() {       //Devuelve un string aleatorio para el id de los vuelos con su indice
    	int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 6;

        String generatedString = MyRandom.getInstance().ints(leftLimit, rightLimit + 1)
          .limit(targetStringLength)
          .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
          .toString();

        return generatedString.toUpperCase();
    }
}
