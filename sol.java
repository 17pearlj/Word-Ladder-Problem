import java.util.*;

class Solution {
    String str1;
    String str2;
    Hashtable<String, ArrayList<String>> nodes;
    List<String> wordList;
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
       this.wordList = wordList;
       this.wordList.add(beginWord);
      
       fillNodes();
       if (!this.nodes.containsKey(endWord)) { return 0; }
       return bfs(beginWord, endWord);
    }

    public int bfs(String beginWord, String endWord) {
        Hashtable<String, Integer> dst = new Hashtable<String, Integer>();
        HashSet<String> visited = new HashSet<String>();
        Queue<String> q = new LinkedList<String>();

        for (int i = 0; i < wordList.size(); i++) {
            dst.put(wordList.get(i), Integer.MAX_VALUE);
        }

        dst.put(beginWord, 1);
        visited.add(beginWord);
        q.add(beginWord);

        String s;
        while (!q.isEmpty()) {
            s = q.poll();
            for (String nbr : nodes.get(s)) {
                if (!visited.contains(nbr) || dst.get(nbr) > dst.get(s) + 1) {
                    dst.put(nbr, dst.get(s) + 1);
                    visited.add(nbr);
                    q.add(nbr);
                }
            }
        }
        if (dst.get(endWord) == Integer.MAX_VALUE) {
            return 0;
        } else {
            return dst.get(endWord);
        }
    }
    public void fillNodes() {
        this.nodes = new Hashtable<String, ArrayList<String>> ();
        for (int i = 0; i < wordList.size(); i++) {
            nodes.put(wordList.get(i), new ArrayList<String>());
        }
        for (int i = 0; i < wordList.size() - 1; i++) {
            for (int j = i + 1; j < wordList.size(); j++) {
                if (oneLetterAway(wordList.get(i), wordList.get(j))) {
                    nodes.get(wordList.get(i)).add(wordList.get(j));
                    nodes.get(wordList.get(j)).add(wordList.get(i));
                }
            }
        }
    }


    public boolean oneLetterAway(String str1, String str2) {
        int[][]arr = new int[str1.length()][str2.length()];
        for (int i = 0; i < str1.length(); i++) {
            Arrays.fill(arr[i], -1);
        }
        this.str1 = str1;
        this.str2 = str2;
        return (levDistance(0, 0, arr) == 1);
    }

    public int levDistance(int r, int c, int[][] arr) {
        if (c == str2.length()) { return str1.length() - r; }
        if (r == str1.length()) { return str2.length() - c; }
        if (arr[r][c] == -1) {
            int min = Math.min(levDistance(r + 1, c, arr), levDistance(r, c + 1, arr)) + 1;
            min = Math.min(min, levDistance(r + 1, c + 1, arr) + (str1.charAt(r) == str2.charAt(c) ? 0 : 1));
            arr[r][c] = min;
        }
        return arr[r][c];
    }
}
