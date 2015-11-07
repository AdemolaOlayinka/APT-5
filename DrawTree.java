import java.util.*;
public class DrawTree {
	
	List<String> myGlob = new ArrayList<String>();
	
	class Node{
		
		int index;
		String name;
		int level;
		List<Node> children;
		int ID;
		
		public Node(int ind, String nam, int lev, int id){
			this.index = ind;
			this.name = nam;
			this.level = lev;
			this.ID = id;
			this.children = new ArrayList<Node>();
		}
		
		public void addKids(int ind, String nam, int lev, int id){
			children.add(new Node(ind, nam, lev, id));
		}
		public void addKids(Node point){
			children.add(point);
		}
	}
	
	public int findAdam(int[] pars){
		for (int i = 0; i < pars.length; i++){
			if (pars[i] == -1){
				return i;
			}
		}
		return -2;
	}
	
	public String toString(Node that, String gap){
		return gap + "+-" + that.name;
	}
	
	public void adder(Node targ, String gap){
		myGlob.add(gap + targ.name);
		if (targ.children.size() == 0){
			return;
		}
		else{
			int len = targ.children.size();
			for (Node x : targ.children){
				adder(x, "  " + gap);
			}
		}
	
	}
	
	public int findPlus(String val){
		for (int i = 0; i < val.length(); i++){
			if (val.charAt(i) == '+'){
				return i;
			}
		}
		return -1;
	}
	
	public boolean anotherPlus(String[] val, int start, int ind){
		if (ind == -1){
			return false;
		}
		for (int i = start+1; i < val.length; i++){
			if (val[i].equals("    +-D")){
			}
			if (val[i].length() < val[start].length()){
				return false;
			}
			if (findPlus(val[i]) == ind){
				return true;
			}
		}
		return false;
	}
	
	public String convert(String val, int ind){
		if (val.length() < ind){
			return val;
		}
		char[] mod = val.toCharArray();
		if (mod[ind] == ' '){
			mod[ind] = '|';
		}
		return String.valueOf(mod);
	}
	
	public int nextPlus(String[] vals, int ind, int j){
		for (int i = j + 1; i < vals.length; i++){
			if (findPlus(vals[i]) == ind){
				return i;
			}
		}
		return -1;
	}
	
	public String[] piping(String[] unpiped){
		
		for (int i = 0; i < unpiped.length; i++){
			int ind = findPlus(unpiped[i]);
			int next = nextPlus(unpiped, ind, i);
			if (anotherPlus(unpiped, i, ind) && next != -1){
				for (int j = i+1; j < next; j++){
					unpiped[j] = convert(unpiped[j], ind);
				}
			}
		}
		return unpiped;
	}
	
	public String[] draw(int[] parents, String[] names) {
		
		int parInd = findAdam(parents);
		Map<String, Node> nodeMap = new TreeMap<String, Node>();
		int i = 0;
		for (String x : names){
			nodeMap.put(x, new Node(i, x, parents[i], i));
			i++;
		}
				
		for (int k = 0; k < parents.length; k++){
			if (parents[k] == -1){
				continue;
			}
			nodeMap.get(names[parents[k]]).children.add(nodeMap.get(names[k]));
		}
		
		String[] wellDamn = new String[names.length];
		Node begin = nodeMap.get(names[parInd]);
		adder(begin, "+-");
		int k = 0;
		for (String x : myGlob){
			wellDamn[k] = x;
			System.out.println(x);
			k++;
		}
		return piping(wellDamn);
		
	}

}