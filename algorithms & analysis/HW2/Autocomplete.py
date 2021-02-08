from tkinter import *
# Python program for insert and search 
# operation in a Trie 

class TrieNode: 
	
	# Trie node class 
	def __init__(self): 
		self.children = [None]*26

		# isEndOfWord is True if node represent the end of the word 
		self.isEndOfWord = False

class Trie: 
	
	# Trie data structure class 
	def __init__(self): 
		self.root = self.getNode() 

	def getNode(self): 
	
		# Returns new trie node (initialized to NULLs) 
		return TrieNode() 

	def _charToIndex(self,ch): 
		
		# private helper function 
		# Converts key current character into index 
		# use only 'a' through 'z' and lower case 
		
		return ord(ch)-ord('a') 


	def insert(self,key): 
		
		# If not present, inserts key into trie 
		# If the key is prefix of trie node, 
		# just marks leaf node 
		pCrawl = self.root 
		length = len(key) - 1
		for level in range(length): 
			index = self._charToIndex(key[level]) 

			# if current character is not present 
			if not pCrawl.children[index]: 
				pCrawl.children[index] = self.getNode() 
			pCrawl = pCrawl.children[index] 

		# mark last node as leaf 
		pCrawl.isEndOfWord = True

	def search(self, key): 
		
		# Search key in the trie 
		# Returns true if key presents 
		# in trie, else false 
		pCrawl = self.root 
		length = len(key) 
		for level in range(length): 
			index = self._charToIndex(key[level]) 
			if not pCrawl.children[index]: 
				return False
			pCrawl = pCrawl.children[index] 

		return pCrawl != None and pCrawl.isEndOfWord 

# driver function 
def main(): 

	root = Tk()
	root.geometry("400x250")
	root.title("Autocomplete")
	textentry = Entry(root, width = 30, bg = 'white')
	textentry.grid(row = 0, column = 0)
	filename = open("words.txt", "r")
	keys = []
	for line in filename:
		keys.append(line)
	filename.close()
	# Input keys (use only 'a' through 'z' and lower case) 
	#keys = ["the","a","there","anaswe","any","by","their"] 
	output = ["Not present in trie","Present in trie"] 

	# Trie object 
	t = Trie() 

	# Construct trie 
	for key in keys: 
		t.insert(key) 
	# Search for different keys 
	print("{} ---- {}".format("the",output[t.search("the")]))  
	root.mainloop()
if __name__ == '__main__': 
	main() 
    