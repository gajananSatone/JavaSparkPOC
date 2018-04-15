package gajanans.coreJavaPoc;
//package gajanans.brushup;
//
//public class WeakReferenceStack<E> {
//	private final List<WeakReference<E>> stackReferences;
//	private int stackPointer = 0;
//	public WeakReferenceStack() {
//		this.stackReferences = new ArrayList<>();
//	}
//	public void push(E element) {
//		this.stackReferences.add(
//				stackPointer, new WeakReference<>(element));
//		stackPointer++;
//	}
//	public E pop() {
//		stackPointer--;
//		return this.stackReferences.get(stackPointer).get();
//	}
//	public E peek() {
//		return this.stackReferences.get(stackPointer-1).get();
//	}
//	@Test
//	public void weakReferenceStackManipulation() {
//		final WeakReferenceStack<ValueContainer> stack = new WeakReferenceStack<>();
//		final ValueContainer expected = new ValueContainer("Value for the stack");
//		stack.push(new ValueContainer("Value for the stack"));
//		ValueContainer peekedValue = stack.peek();
//		assertEquals(expected, peekedValue);
//		assertEquals(expected, stack.peek());
//		peekedValue = null;
//		System.gc();
//		assertNull(stack.peek());
//	}
//}
//
//
