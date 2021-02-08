from tkinter import *

class Gui(Entry):

	def __init__(self, lista, *args, **kwargs):
	    Entry.__init__(self, *args, **kwargs)
        self.lista = lista
        self.var = self["textvariable"]
        if self.var == '':
            self.var = self["textvariable"] = StringVar()

        self.var.trace('w', self.changed)

	def changed(self, name, index, mode):

		words = self.comparison()
		self.lb = Listbox()
		self.lb.place(x=self.winfo_x(), y=self.winfo_y() + self.winfo_height())
		self.lb.delete(0, END)
		for w in words:
			self.lb.insert(END, w)

	def comparison(self):
        tree.auto_suggestions(self.var.get())
        pattern = re.compile(self.var.get() + '.*') # This line is prefix only search
        # pattern = re.compile('.*' + self.var.get() + '.*') # This line instead is FUZZY SEARCH
        returns = [w for w in tree.word_list if re.match(pattern, w)]
        returns = list(dict.fromkeys(returns))
        return [r for r in returns[:10]]

class treeNode(): 
	def __init__(self): 
		
		# Initialising one node for tree 
		self.children = {} 
		self.last = False

class tree(): 
	def __init__(self): 
		
		# Initialising the tree structure. 
		self.root = treeNode() 
		self.word_list = [] 

	def formtree(self, keys): 
		
		# Forms a tree structure with the given set of strings 
		# if it does not exists already else it merges the key 
		# into it by extending the structure as required 
		for key in keys: 
			self.insert(key) # inserting one key to the tree. 

	def insert(self, key): 
		
		# Inserts a key into tree if it does not exist already. 
		# And if the key is a prefix of the tree node, just 
		# marks it as leaf node. 
		node = self.root 

		for a in list(key): 
			if not node.children.get(a): 
				node.children[a] = treeNode() 

			node = node.children[a] 

		node.last = True

	def search(self, key): 
		
		# Searches the given key in tree for a full match 
		# and returns True on success else returns False. 
		node = self.root 
		found = True

		for a in list(key): 
			if not node.children.get(a): 
				found = False
				break

			node = node.children[a] 

		return node and node.last and found 

	def suggestionsRec(self, node, word): 
		
		#recursively traverse the tree and return whole word
		if node.last: 
			self.word_list.append(word) 

		for a,n in node.children.items(): 
			self.suggestionsRec(n, word + a)

	def printAutoSuggestions(self, key): 
		
		# Returns all the words in the tree whose common 
		# prefix is the given key thus listing out all 
		# the suggestions for autocomplete. 
		node = self.root 
		not_found = False
		temp_word = '' 

		for a in list(key): 
			if not node.children.get(a): 
				not_found = True
				break

			temp_word += a 
			node = node.children[a] 

		if not_found: 
			return 0
		elif node.last and not node.children: 
			return -1

		self.suggestionsRec(node, temp_word) 

		for s in self.word_list: 
			print(s) 
		return 1

# Driver Code 
if __name__ == "__main__":
	pass
filename = open("words.txt", "r")    
keys = []
for line in filename:
	keys.append(line)
filename.close()

#key = input("Search word: ")  #vkey for autocomplete suggestions. 

# creating tree 
t = tree() 

# creating the tree structure 
t.formtree(keys) 

root = Tk()
root.geometry("200x185")
root.title("Autocomplete")

textentry = Entry(root, width=20, bg='white')
textentry.grid(row=0, column=0)

textentry = t.printAutoSuggestions(key)

root.mainloop()
