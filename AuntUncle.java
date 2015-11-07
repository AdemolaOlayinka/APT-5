import java.util.*;

public class AuntUncle {

	private String[][] createFam(String[] parents){
		String[][] fams = new String[parents.length][3];

		int i = 0;

		for (String fam : parents){
			fams[i] = fam.split(" ");
			i++;
		}

		return fams;
	}

	private String[] getParents(String[][] fams, String target){
		String[] parents = new String[2];
		for (String[] x : fams){
			if (x[2].equals(target)){
				parents[0] = x[0]; parents[1] = x[1];
				return parents;
			}
		}
		return parents;
	}

	private List<String> findKids(List<String> grand, String[][] fams){
		List<String> kids = new ArrayList<String>();
		for (String[] fam : fams){
			if (grand.contains(fam[0]) || grand.contains(fam[1])){
				kids.add(fam[2]);
			}
		}
		return kids;
	}

	public String[] list(String[] parents, String target) {
		String[][] fams = createFam(parents);
		String[] targetParents = getParents(fams, target);
		List<String> grandparents = new ArrayList<String>();

		String[] p1Parents = getParents(fams, targetParents[0]);
		String[] p2Parents = getParents(fams, targetParents[1]);

		grandparents.add(p1Parents[0]); grandparents.add(p1Parents[1]);
		grandparents.add(p2Parents[0]); grandparents.add(p2Parents[1]);

		List<String> piblings = findKids(grandparents, fams);
		piblings.remove(targetParents[0]); piblings.remove(targetParents[1]); piblings.remove(target);
		Collections.sort(piblings);

		String[] auncles = new String[piblings.size()];

		int i = 0;
		for (String x : piblings){
			auncles[i] = x;
			i++;
		}

		return auncles;
	}	
}