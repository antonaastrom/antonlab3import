package sorter.control.algorithms;

public class QuickSort extends SortAlgorithm {

	public QuickSort() {
		super("Quicksort");
		
	}

	
	protected int getPivot() {
		
		int first = 0;
		int last = getElementCount(); 
		int median = last/2; 
		int pivot = 0;
		
		if (cmp (first,median) > 0 && cmp(median, last) >0 || cmp (last,median) > 0 && cmp(median, first) >0) {
			 pivot = median; 
		}
		if (cmp (first,last) > 0 && cmp(last, median) >0 || cmp (median,last) > 0 && cmp(last, first) >0) {
			 pivot = last;
		}
		if (cmp(last,first) > 0 && cmp(first, median) >0 || cmp(median,first) > 0 && cmp(first,last) > 0) {
			 pivot = first; 
		}
		
		
		return pivot; 
		
	}
	
	protected void sort() {
		
		int pivot = getPivot(); 
		
		swap(pivot, getElementCount());

		

	}

}
