package jhonatas.autoupdater;

import java.util.List;

class Checkers {

	public static boolean isNull(String string) {
		
		if(string==null) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isEmpty(String string) {
	
		if(isNull(string)) {
			
			return true;
		
		}
	
		return string.isEmpty();
	}
	
	public static boolean isNotEmpty(String string) {
		
		return !isEmpty(string);
		
	}
	
	public  static boolean isListEmpty(List<?> list) {
		
		if(list==null) {
		
			return true;
		}
		
		return list.isEmpty();
	}
	
	public  static boolean isObjectNull(Object object) {
		
		return object==null;
	}
	
	public static boolean isLongPositive(String objctNumber) {
		
		if(isEmpty(objctNumber)) {
			return false;
		}
		long numero=0;
		
		try {
			
			numero =Long.parseLong(objctNumber);
			
			
		} catch (Exception e) {
			//e.printStackTrace();
			
			return false;
		}
		boolean check = numero>0;
		
		return check;
		
	}
	
	public static boolean isStringEquals(String string1,String string2) {
		
		 boolean checkString1 = isEmpty(string1);
		 boolean checkString2 = isEmpty(string2);
		
		 if(checkString1 && checkString2) {
			return true;
		 }
		 
		if(checkString1 && !checkString2) {
			return false;
		}
		
		return string1.equals(string2);
	}
	
	public static void validateStringNotNull(String string,String nameString) {
		
		if(isEmpty(string)) {
			throw new NullPointerException("Campo "+nameString+" nao pode ser nulo");
		}
		
	}
	
	public static boolean validadeObjectNotNull(Object object,String nameString) {
		
		if(isObjectNull(object)) {
			throw new NullPointerException("O objeto "+nameString+" nao pode ser nulo");
		}
		
		return true;
	}
	
	public static void valideListNotEmpty(List<?> list,String nameList) {
		
		if(validadeObjectNotNull(list, nameList)) {
			if(list.isEmpty()) {
	    		throw new NullPointerException("A lista de campos não pode estar vazia");
	    	}
		}
		
		
	}
	
	public static void validadeLongPositive(String objecNumber,String variableName) {
		if(!isLongPositive(objecNumber)) {
			throw new IllegalArgumentException("O valor da variavel "+variableName+"deve ser uma numero inteiro positivo");
		}
	}
	
}
