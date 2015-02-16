package sorter.control.algorithms;

public class InsertionSort extends SortAlgorithm {

	public InsertionSort() {
		super("Insertion Sort");

	}

	
	protected void sort() {

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
