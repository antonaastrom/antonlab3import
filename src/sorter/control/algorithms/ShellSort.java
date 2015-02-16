package sorter.control.algorithms;


public class ShellSort extends SortAlgorithm {

	public ShellSort() {
		super("Shellsort");
		
	}

	@Override
	protected void sort() {
		
		double gap = 1;
		
		while (gap*2.2 < getElementCount()) {
			gap=gap*2.2;
		}
		int gapI = (int) gap;
		
		if (gap < gapI) {
			gapI = gapI -1;
		}
		
		while(gapI>1){
			int start=0;
			for (int  i= gapI; i<getElementCount(); i++) {
				
				if (cmp(start , i) > 0) {
					swap(start,i);
				}
				start++;
				
			}
			gap = (double) gapI;
			gap = gap / 2.2;
			gapI = (int)gap;
			if (gap < gapI) {
				gapI = gapI -1;
			}
			
			
		}
		for (int i = 0; i < getElementCount() - 1; i++) {

			for (int j = i; j >= 0; j--) {

				if (cmp(j, j + 1) > 0) {
					swap(j, j + 1);

				} else {
					break;
				}
			}

		}
	}

}
