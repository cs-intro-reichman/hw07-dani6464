public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
	}

	public static String[] readDictionary(String fileName) {
		// init
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		int index = 0;

		// checks if index in boundry and there's more text to read
		while(!in.isEmpty() && index < dictionary.length){
			dictionary[index++] = in.readString();
		}

		return dictionary;	
	}

	public static boolean existInDictionary(String word, String []dictionary) {
		// iterates over all keys in dictionary and then check if key == word
		for(String dictWord : dictionary){
			if (dictWord != null && dictWord.equals(word)){
				return true;
			}
		}

		return false;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {
		hashtag = hashtag.toLowerCase();
		breakHashTagHelper(hashtag, dictionary, "");
	}

	private static void breakHashTagHelper(String remaining, String[] dictionary, String constructed){
		// Base case: do nothing (return) if hashtag is an empty string.
        if (remaining.isEmpty()) {
			if (!constructed.isEmpty()){
			System.out.println(constructed.trim());
			}
			
			return;
        }

        int N = remaining.length();

        for (int i = 1; i <= N; i++) {
			String prefix = remaining.substring(0, i);
			
			// If the prefix is found in the dictionary, print it and recursively call the function for the rest of the string
			if (existInDictionary(prefix, dictionary)){
				System.out.println(prefix);
				breakHashTagHelper(remaining.substring(i), dictionary, "");
				break;
			}
        }
    }

}
