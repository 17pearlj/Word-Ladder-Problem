class Solution {
public:
    bool areNeighbors(string a, string b) {
        if (a.length() != b.length()) { return false;}
        if (a == b) { return false;}
        bool oneFound = false;
        for (int i = 0; i < a.length(); i++) {
            if (a[i] != b[i]) { if (oneFound) { return false; } else { oneFound = true;}}
        }
        return true;
    }
    int ladderLength(string beginWord, string endWord, vector<string>& wordList) {

        if (beginWord == endWord) { return 0; }
        wordList.push_back(beginWord);
        vector<vector<int>> nbrs;
        vector<int> v;
        nbrs.resize(wordList.size(), v);
        int endWordI = -1;
        for (int i = 0; i < wordList.size(); i++) {
            if (wordList[i] == endWord) {endWordI = i;}
            for (int j = i + 1; j < wordList.size(); j++) {
                if (areNeighbors(wordList[i], wordList[j])) {
                    nbrs[i].push_back(j);
                    nbrs[j].push_back(i);
                }
            }
        }
        if (endWordI == -1) { return 0;}

        queue<int> q;
        vector<int> dst;
        dst.resize(wordList.size(), INT_MAX);
        q.push(wordList.size() - 1);
        dst[wordList.size() - 1] = 1;
        while (!q.empty()) {
            int b = q.front();
            q.pop();
            for (int n: nbrs[b]) {
                if (dst[n] > dst[b] + 1) {
                    dst[n] = dst[b] + 1;
                    if (n == endWordI) { return dst[n];} 
                    q.push(n);
                }
            }

        }
        return (dst[endWordI] == INT_MAX) ? 0 : dst[endWordI];
        
    }
};
