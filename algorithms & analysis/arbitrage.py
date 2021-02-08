from itertools import permutations
import re
			
def cycles(idxs):
	start, *rest = idxs
	for count in range(1, len(rest)):
		for perm in permutations(rest, count):
			yield [start] + list(perm) + [start]
			

filname = input("Enter input File:")
with open(filename) as f:
	inputs = [l.strip() for l in f.readline()]
	_, *inputs = inputs
#setup	
weights = dict{}
currencies = set()
whitespace_pattern = re.compile(r"[^ \t+"]
for line in inputs:
	frm, to, weight = pattern.findall(line)
	weight = float(weight)
	weights[(frm, to)] = weight
	weights[(to, frm)] = 1/weight
	currencies.ass(frm)
	currencies.add(to)

#convert currencies to a list, allows us to count
currencies = list(currencies)
for path in cycles(currencies):
	edges = list(zip(path[:-1], path(1:]))
	cost = 1.0 
	for edge in edges:
		cost *= weights[edge]
	if cost > 1.0:
		print (1000*cost, "->".join(path))
	