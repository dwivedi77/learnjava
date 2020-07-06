package abhi.learn.java.leetcode.datastructure;

/**
 * Created by Abhishek on 5/14/2020.
 */
class Trie {
    char value;
    Trie[] kids;
    /** Initialize your data structure here. */
    public Trie() {
        value = '0';
        kids = new Trie[26];
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
    }

    private void insert(String word, int idx) {
        if (word == null || word.length() == 0 || word.length() == idx) return;
        char x = word.charAt(idx);
        if (kids[x-'a'] == null){
            Trie trie = new Trie();
            trie.value = x;
            kids[x-'a'] = trie;
            insert(word, idx+1);
        }else {

        }
    }

        /** Returns if the word is in the trie. */
    public boolean search(String word) {
        return  false;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return false;
    }
}
//class TNode{
//    char val;
//    TNode[] kids = new TNode[26];
//}