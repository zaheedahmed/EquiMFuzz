package cmu.pasta.mu2.equiMFuzz;

import cmu.pasta.mu2.DoublyLinkedList;
import com.pholser.junit.quickcheck.generator.InRange;
import edu.berkeley.cs.jqf.fuzz.JQF;
import edu.berkeley.cs.jqf.fuzz.difffuzz.DiffFuzz;
import org.junit.runner.RunWith;

@RunWith(JQF.class)
public class DoublyLinkedListTest {

	@DiffFuzz
	public void testInsertAtHead(@InRange(minInt = 1, maxInt = 100) int[] values) {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		for (int value : values) {
			list.insertAtHead(value);
		}
		// Verify the size of the list
		assert list.size() == values.length;
		// Verify the contents of the list by traversing forward
		for (int i = 0; i < values.length; i++) {
			assert list.searchByIndex(i).item == values[values.length - i - 1];
		}
	}

	@DiffFuzz
	public void testInsertAtTail(@InRange(minInt = 1, maxInt = 100) int[] values) {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		for (int value : values) {
			list.insertAtTail(value);
		}
		// Verify the size of the list
		assert list.size() == values.length;
		// Verify the contents of the list by traversing forward
		for (int i = 0; i < values.length; i++) {
			assert list.searchByIndex(i).item == values[i];
		}
	}

	/**@DiffFuzz
	public void testInsertAtPosition(@InRange(minInt = 1, maxInt = 100) int[] values, @InRange(minInt = 0, maxInt = 99) int[] positions) {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
		for (int i = 0; i < values.length; i++) {
			// Adjust position to be within current size bounds (0 to size)
			int position = Math.min(positions[i], list.size());
			list.insertAtHead(values[i]);
		}
		// Verify the size of the list
		assert list.size() == values.length; // Assuming all insertions are valid
		// Verify the contents of the list
		// This part might need to be adjusted based on the actual list behavior
		// since the original verification logic might not directly apply.
	}**/

}
