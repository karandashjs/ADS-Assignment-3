import java.util.ArrayList;
import java.util.List;

public class BST<K extends Comparable<K>, V> {
    private Node root;
    private int size; // Added size variable to keep track of the number of elements in the BST.

    private class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public void put(K key, V val) {
        root = put(root, key, val);
    }

    private Node put(Node node, K key, V val) {
        if (node == null) {
            size++;
            return new Node(key, val);
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, val);
        } else if (cmp > 0) {
            node.right = put(node.right, key, val);
        } else {
            node.val = val;
        }
        return node;
    }

    public V get(K key) {
        return get(root, key);
    }

    private V get(Node node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return get(node.left, key);
        } else if (cmp > 0) {
            return get(node.right, key);
        } else {
            return node.val;
        }
    }

    public void delete(K key) {
        root = delete(root, key);
    }

    private Node delete(Node node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null) {
                size--;
                return node.right;
            } else if (node.right == null) {
                size--;
                return node.left;
            } else {
                Node minRight = findMin(node.right);
                node.key = minRight.key;
                node.val = minRight.val;
                node.right = deleteMin(node.right);
            }
        }
        return node;
    }

    private Node findMin(Node node) {
        if (node.left == null) {
            return node;
        }
        return findMin(node.left);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            size--;
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
    }

    public Iterable<K> iterator() {
        List<K> keys = new ArrayList<>();
        inOrderTraversal(root, keys);
        return keys;
    }

    private void inOrderTraversal(Node node, List<K> keys) {
        if (node == null) {
            return;
        }

        inOrderTraversal(node.left, keys);
        keys.add(node.key);
        inOrderTraversal(node.right, keys);
    }

    // Getter method for size
    public int size() {
        return size;
    }

    // Additional method to get a list of all values in the tree
    public Iterable<V> values() {
        List<V> values = new ArrayList<>();
        inOrderTraversalValues(root, values);
        return values;
    }

    private void inOrderTraversalValues(Node node, List<V> values) {
        if (node == null) {
            return;
        }

        inOrderTraversalValues(node.left, values);
        values.add(node.val);
        inOrderTraversalValues(node.right, values);
    }
}
