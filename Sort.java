import java.util.Comparator;
import java.util.ListIterator;

/**
 * Class for sorting lists that implement the IndexedUnsortedList interface,
 * using ordering defined by class of objects in list or a Comparator.
 * As written uses Quicksort algorithm.
 *
 * @author CS221
 */
public class Sort {
	/**
	 * Returns a new list that implements the IndexedUnsortedList interface.
	 * As configured, uses WrappedDLL. Must be changed if using
	 * your own IUDoubleLinkedList class.
	 * 
	 * @return a new list that implements the IndexedUnsortedList interface
	 */
	private static <T> IndexedUnsortedList<T> newList() {
		return new IUDoubleLinkedList<T>(); // TODO: replace with your IUDoubleLinkedList for extra-credit
	}

	/**
	 * Sorts a list that implements the IndexedUnsortedList interface
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *             The class of elements in the list, must extend Comparable
	 * @param list
	 *             The list to be sorted, implements IndexedUnsortedList interface
	 * @see IndexedUnsortedList
	 */
	public static <T extends Comparable<T>> void sort(IndexedUnsortedList<T> list) {
		quicksort(list);
	}

	/**
	 * Sorts a list that implements the IndexedUnsortedList interface
	 * using given Comparator.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *             The class of elements in the list
	 * @param list
	 *             The list to be sorted, implements IndexedUnsortedList interface
	 * @param c
	 *             The Comparator used
	 * @see IndexedUnsortedList
	 */
	public static <T> void sort(IndexedUnsortedList<T> list, Comparator<T> c) {
		quicksort(list, c);
	}

	/**
	 * Quicksort algorithm to sort objects in a list
	 * that implements the IndexedUnsortedList interface,
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *             The class of elements in the list, must extend Comparable
	 * @param list
	 *             The list to be sorted, implements IndexedUnsortedList interface
	 */
	private static <T extends Comparable<T>> void quicksort(IndexedUnsortedList<T> list) {
		// one element list or less, list is already sorted
		if (list.size() <= 1) {
			return;
		}
		// 1st, remove pivot
		T pivot = list.removeFirst();

		// 2nd, assign next lists
		IndexedUnsortedList<T> left = newList();
		IndexedUnsortedList<T> right = newList();

		ListIterator<T> it = list.listIterator();
		while (it.hasNext()) {
			T elem = it.next();
			it.remove(); // get rid of original list as we go
			if (elem.compareTo(pivot) < 0) {
				left.addToRear(elem);
			} else {
				right.addToRear(elem);
			}
		}

		//3rd, Recursively sort each partition
		quicksort(left);
		quicksort(right);

		//4th, Rebuild original list — left elements, pivot, right elements
		ListIterator<T> leftIt = left.listIterator();
		while (leftIt.hasNext()) {
			list.addToRear(leftIt.next());
		}

		list.addToRear(pivot);

		ListIterator<T> rightIt = right.listIterator();
		while (rightIt.hasNext()) {
			list.addToRear(rightIt.next());
		}
	}

	/**
	 * Quicksort algorithm to sort objects in a list
	 * that implements the IndexedUnsortedList interface,
	 * using the given Comparator.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *             The class of elements in the list
	 * @param list
	 *             The list to be sorted, implements IndexedUnsortedList interface
	 * @param c
	 *             The Comparator used
	 */
	private static <T> void quicksort(IndexedUnsortedList<T> list, Comparator<T> c) {
		// one element list or less, list is already sorted
		if (list.size() <= 1) {
			return;
		}
		//1st, Remove the pivot (first element)
		T pivot = list.removeFirst();
		// 2nd, Partition remaining elements into left (< pivot) and right (>= pivot)
		// Iterator-only traversal — no indexed methods — preserves O(n) per level
		IndexedUnsortedList<T> left  = newList();
		IndexedUnsortedList<T> right = newList();
 
		ListIterator<T> it = list.listIterator();
		while (it.hasNext()) {
			T elem = it.next();
			it.remove(); // get rid of original list as we go
			if (c.compare(elem, pivot) < 0) {
				left.addToRear(elem);
			} else {
				right.addToRear(elem);
			}
		}
 
		//3rd, Recursively sort each partition
		quicksort(left, c);
		quicksort(right, c);
 
		//4th, Rebuild original list — left elements, pivot, right elements
		ListIterator<T> leftIt = left.listIterator();
		while (leftIt.hasNext()) {
			list.addToRear(leftIt.next());
		}
 
		list.addToRear(pivot);
 
		ListIterator<T> rightIt = right.listIterator();
		while (rightIt.hasNext()) {
			list.addToRear(rightIt.next());
		}
	}

}
