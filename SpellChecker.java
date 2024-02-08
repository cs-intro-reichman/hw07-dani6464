
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		if (1 < str.length()){
			return str.substring(1);
		}
		else{
			return "";
		}
	}

	public static int levenshtein(String word1, String word2) {
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();

		if (word1.isEmpty()){
			return word2.length();
		}
		if (word2.isEmpty()){
			return word1.length();
		}

		int cost;
		if (word1.charAt(0) == word2.charAt(0)){
			cost = 0;
		}
		else{
			cost = 1;
		}

		return Math.min(levenshtein(tail(word1), word2) + 1,
				Math.min(levenshtein(word1, tail(word2)) + 1,
				levenshtein(tail(word1), tail(word2)) + cost));
	}

	public static String[] readDictionary(String fileName) {
		// init
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		int index = 0;

		// checks if index in boundry and there's more text to read
		while(!in.isEmpty() && index < dictionary.length){
			dictionary[index++] = in.readString().toLowerCase();
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int minLevVal = Integer.MAX_VALUE;
		String similar = "";
		
		for (String dictWord : dictionary){
			int curLev = levenshtein(word, dictWord);
			System.out.println(dictWord + ": " + curLev);  // Debug
			
			if (curLev < minLevVal){
				minLevVal = curLev;
				similar = dictWord;
			}
	}
		if (threshold < minLevVal){
			return word;
		}
		
		return similar;

}

}
