package challenges.assorted.tree.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Trie
 * 
 * The trie data structure, also known as a prefix tree are space and time efficient structures for 
 * text storage and search. They let words to share prefixes.
 * 
 * @author Hxkandwal
 */
public class Trie {
	
	private char letter;
	private Trie[] children;
	private boolean isTerminal;
	private List<Integer> positions;

	public Trie (char letter) {
		this.letter = letter;
		this.children = new Trie[256];
		this.positions = new ArrayList<>();
	}

	public static Trie createFreshRoot() {
		return new Trie(' ');
	}
	
	public List<Integer> getPositions() {
		return positions;
	}
	
	public Trie[] getChildren() {
		return children;
	}
	
	/** 
	 * Insert if not present, retrieve positions if present.
	 * To be used from root node.
	 */
	public List<Integer> getItem(String word) {
		Trie traverser = this;
		
		for (int idx = 0; idx < word.length(); idx ++) {
			char ch = word.charAt(idx);
			
			if (traverser.children[ch] == null)
				traverser.children[ch] = new Trie(ch);
			
			traverser = traverser.children[ch];
		}

		traverser.isTerminal = true;
		return traverser.positions;
	}
	
	public boolean contains(String word) {
		Trie traverser = this;
		
		for (int idx = 0; idx < word.length(); idx ++) {
			char ch = word.charAt(idx);
			
			if (traverser.children[ch] == null)
				return false;
			
			traverser = traverser.children[ch];
		}
		
		return traverser.isTerminal;
	}
	
	public List<String> getAllPrefixes(String word) {
		Trie traverser = this;
		
		List<String> prefixes = new ArrayList<>();
		StringBuilder runningPrefix = new StringBuilder();
		
		for (int idx = 0; idx < word.length(); idx ++) {
			char ch = word.charAt(idx);
			
			if (traverser.children [ch] == null)
				return prefixes;
			
			runningPrefix.append(ch);
			
			if (traverser.children [ch].isTerminal) 
				prefixes.add(runningPrefix.toString());
			
			traverser = traverser.children [ch];
		}
		
		return prefixes;
	}
	
	public List<String> getSuggestions(String prefix) {
		Trie traverser = this;
		StringBuilder builder = new StringBuilder();
		List<String> collector = new ArrayList<>();
		
		// loading prefix.
		for (int idx = 0; idx < prefix.length(); idx ++) {
			char ch = prefix.charAt(idx);
			
			if (traverser.children [ch] == null)
				return collector;
			
			builder.append(ch);
			traverser = traverser.children [ch];
		}
		
		// DFS traversal to get the ordered list of suggestions.
		if (traverser.isTerminal)
			collector.add(builder.toString());
		
		dfsOrderedCollection(traverser, collector, builder);
		return collector;
	}
	
	@Override
	public String toString() {
		return String.valueOf("[" + String.valueOf(letter) + "]");
	}
	
	private void dfsOrderedCollection (Trie node, List<String> collector, StringBuilder builder) {
		for (int idx = 0; idx < node.children.length; idx ++) {
			Trie child = node.children [idx];
			StringBuilder childBuilder = new StringBuilder(builder);
			
			if (child != null) {
				childBuilder.append(child.letter);
				
				if (child.isTerminal)
					collector.add(childBuilder.toString());
				
				dfsOrderedCollection (child, collector, childBuilder);
			}
		}
	}

}