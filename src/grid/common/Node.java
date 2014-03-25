package grid.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Node.java 2013-10-4 11:19:22
 * 
 * @Author George Bourne
 */
public class Node<T> {
	protected List<Node<T>> children;

	protected Node<T> parent;

	protected T value;

	Node(T value) {
		this.value = value;
	}

	public Node<T> add(T value) {
		if (null == children) {
			children = new ArrayList<Node<T>>();
		}
		Node<T> child = new Node<T>(value);
		child.setParent(this);
		children.add(child);
		return child;
	}

	public T getValue() {
		return value;
	}

	public Node<T> getParent() {
		return parent;
	}

	public void setParent(Node<T> parent) {
		this.parent = parent;
	}

	private void recurseChildren(List<Node<T>> list, Node<T> parent) {
		if (null == parent.children) {
			list.add(parent);
		} else {
			for (Node<T> node : parent.children) {
				recurseChildren(list, node);
			}
		}
	}

	public List<Node<T>> getLeaves() {
		List<Node<T>> list = new ArrayList<Node<T>>();
		recurseChildren(list, this);
		return list;

	}

	public List<T> getBranchPath() {
		List<T> list = new ArrayList<T>();
		Node<T> node = this;
		do {
			list.add(node.getValue());
			node = node.parent;
		} while (null != node && !(node instanceof Tree<?>));
		Collections.reverse(list);
		return list;
	}

	private void append(StringBuilder builder, int deep, Node<T> node) {
		for (int i = 0; i < deep; i++) {
			builder.append("  ");
		}
		builder.append("|--");
		builder.append(node.getValue());
		builder.append("\n");
		if (null != node.children) {
			for (Node<T> child : node.children) {
				append(builder, deep + 1, child);
			}
		}
	}

	public String dump() {
		StringBuilder builder = new StringBuilder();
		append(builder, 0, this);
		return builder.toString();
	}

	public String toString() {
		return value.toString();
	}
}
