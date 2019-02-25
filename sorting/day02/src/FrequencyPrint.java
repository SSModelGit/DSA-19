import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class FrequencyPrint {

    static String frequencyPrint(String s) {
        String[] words = s.split("\\s+");
        String[] keys, output;
        output = new String[words.length];

        HashMap<String, Integer> map = new HashMap<>();

        // Initialize HashMap
        for (int i = 0; i < words.length; i++) {
            if (map.containsKey(words[i])) {
                map.put(words[i], map.get(words[i]) + 1);
            }
            else {
                map.put(words[i], 1);
            }
        }
        Set<String> keySet = map.keySet();
        keys = new String[keySet.size()];

        Iterator<String> it = keySet.iterator();
        int j = 0;
        while(it.hasNext()){
            keys[j] = it.next();
            j++;
        }

        int keyLen = j;
        int[] wordPos = new int[words.length+1];
        for (int k = 0; k < keyLen; k++) {
            wordPos[map.get(keys[k])]++;
        }

        for (int l = 1; l < wordPos.length; l++) {
            wordPos[l] = wordPos[l-1]+l*wordPos[l];
        }

        int temp, downer;
        for (int m = 0; m < keyLen; m++) {
            temp = downer = map.get(keys[m]);
            while (downer > 0) {
                output[wordPos[temp-1]] = keys[m];
                wordPos[temp-1]++;
                downer--;
            }
        }

        String out = "";
        for (int u = 0; u < output.length; u++) {
            out += output[u] + " ";
        }
        return out;
    }

    static void printArray(String[] arr) 
    { 
        int n = arr.length;
        for (int i=0; i<n; ++i) 
            System.out.println(arr[i]+" "); 
    }

    static void printArray(int[] arr) 
    { 
        int n = arr.length;
        for (int i=0; i<n; ++i) 
            System.out.print(arr[i]+" "); 
    } 

    // Debugger program
    /* public static void main(String args[]) 
    { 
        String str = "to be or not to be to";
        String[] words = str.split("\\s+");
        String[] keys, output;
        output = new String[words.length];

        HashMap<String, Integer> map = new HashMap<>();

        // Initialize HashMap
        for (int i = 0; i < words.length; i++) {
            if (map.containsKey(words[i])) {
                map.put(words[i], map.get(words[i]) + 1);
            }
            else {
                map.put(words[i], 1);
            }
        }
        Set<String> keySet = map.keySet();
        keys = new String[keySet.size()];

        Iterator<String> it = keySet.iterator();
        int j = 0;
        while(it.hasNext()){
            keys[j] = it.next();
            j++;
        }
        printArray(keys);
        System.out.println(j);
        int keyLen = j;
        int[] wordPos = new int[words.length+1];
        for (int k = 0; k < keyLen; k++) {
            wordPos[map.get(keys[k])]++;
        }
        printArray(wordPos);
        System.out.println();
        for (int l = 1; l < wordPos.length; l++) {
            wordPos[l] = wordPos[l-1]+l*wordPos[l];
        }
        printArray(wordPos);
        System.out.println("-----");

        int temp, downer;
        for (int m = 0; m < keyLen; m++) {
            temp = downer = map.get(keys[m]);
            while (downer > 0) {
                output[wordPos[temp-1]] = keys[m];
                wordPos[temp-1]++;
                downer--;
            }
        }

        System.out.println(str);
        printArray(words);
        System.out.println("---------");
        printArray(output);
        System.out.println("-------");
        String out = "";
        for (int u = 0; u < output.length; u++) {
            out += output[u] + " ";
        }
        System.out.print(out);
    } */
}
