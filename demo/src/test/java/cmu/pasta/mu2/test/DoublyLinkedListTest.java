package cmu.pasta.mu2.test;

import cmu.pasta.mu2.DoublyLinkedList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DoublyLinkedListTest {

	private DoublyLinkedList<Integer> list;

	@Before
	public void setUp() {
		list = new DoublyLinkedList<>();
	}

	@Test
	public void testInsertAtHead() {
		list.insertAtHead(1);
		list.insertAtHead(2);
		list.insertAtHead(3);

		assertEquals(3, list.size());
		assertEquals((Integer) 3, list.searchByIndex(0).item);
		assertEquals((Integer) 2, list.searchByIndex(1).item);
		assertEquals((Integer) 1, list.searchByIndex(2).item);
	}

	@Test
	public void testInsertAtTail() {
		list.insertAtTail(1);
		list.insertAtTail(2);
		list.insertAtTail(3);

		assertEquals(3, list.size());
		assertEquals((Integer) 1, list.searchByIndex(0).item);
		assertEquals((Integer) 2, list.searchByIndex(1).item);
		assertEquals((Integer) 3, list.searchByIndex(2).item);
	}

	@Test
	public void testInsertAtPosition() {
		list.insertAtHead(1);
		list.insertAtTail(3);
		list.insertAtPosition(2, 1);

		assertEquals(3, list.size());
		assertEquals((Integer) 1, list.searchByIndex(0).item);
		assertEquals((Integer) 3, list.searchByIndex(1).item);
		assertEquals((Integer) 2, list.searchByIndex(2).item);
	}

	@Test
	public void testDeleteFromHead() {
		list.insertAtHead(1);
		list.insertAtHead(2);
		list.insertAtHead(3);

		list.deleteFromHead();
		assertEquals(2, list.size());
		assertEquals((Integer) 2, list.searchByIndex(0).item);
		assertEquals((Integer) 1, list.searchByIndex(1).item);
	}

	@Test
	public void testDeleteFromTail() {
		list.insertAtHead(1);
		list.insertAtHead(2);
		list.insertAtHead(3);

		list.deleteFromTail();
		assertEquals(2, list.size());
		assertEquals((Integer) 3, list.searchByIndex(0).item);
		assertEquals((Integer) 2, list.searchByIndex(1).item);
	}

	@Test
	public void testDeleteFromPosition() {
		list.insertAtHead(1);
		list.insertAtTail(3);
		list.insertAtPosition(2, 1);

		list.deleteFromPosition(1);
		assertEquals(2, list.size());
		assertEquals((Integer) 1, list.searchByIndex(0).item);
		assertEquals((Integer) 2, list.searchByIndex(1).item);
	}

	@Test
	public void testToArray() {
		list.insertAtHead(1);
		list.insertAtTail(2);
		list.insertAtTail(3);

		Object[] array = list.toArray();
		assertEquals(3, array.length);
		assertEquals((Integer) 1, array[0]);
		assertEquals((Integer) 2, array[1]);
		assertEquals((Integer) 3, array[2]);
	}
}
