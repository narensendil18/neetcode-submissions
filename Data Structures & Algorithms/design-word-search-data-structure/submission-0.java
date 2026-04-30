class WordDictionary {
    
    private TrieNode root;
    
    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }
    
    public WordDictionary() {
        root = new TrieNode();
    }
    
    public void addWord(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (curr.children[i] == null)
                curr.children[i] = new TrieNode();
            curr = curr.children[i];
        }
        curr.isEnd = true;
    }
    
    public boolean search(String word) {
        return dfs(word, 0, root);
    }
    
    private boolean dfs(String word, int idx, TrieNode node) {
        // Base case: consumed entire word
        if (idx == word.length())
            return node.isEnd;
        
        char c = word.charAt(idx);
        
        if (c == '.') {
            // Try all 26 possible children
            for (TrieNode child : node.children) {
                if (child != null && dfs(word, idx + 1, child))
                    return true;
            }
            return false;
        } else {
            // Normal character — go to that child
            int i = c - 'a';
            if (node.children[i] == null)
                return false;
            return dfs(word, idx + 1, node.children[i]);
        }
    }
}