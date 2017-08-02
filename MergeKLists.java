/* Merge K Sorted Lists */
/*
 * Merge k sorted linked lists and return it as one sorted list.
 */

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     public int val;
 *     public ListNode next;
 *     ListNode(int x) { val = x; next = null; }
 * }
 */
public class Solution {
    public ListNode mergeKLists(ArrayList<ListNode> a) {
        ListNode dummy = new ListNode(0);
        ListNode iter = dummy;

        // copy first LL from array list
        ListNode first = a.get(0);
        while (first != null) {
            iter.next = new ListNode(first.val);
            iter = iter.next;
            first = first.next;
        }

        for (int i=1; i<a.size(); i++) {
            ListNode cur = a.get(i);
            iter = dummy.next;
            ListNode prev = dummy;
            while (cur != null) {
                while (iter.val <= cur.val) {
                    if (iter.next == null) {
                        break;
                    }

                    iter = iter.next;
                    prev = prev.next;
                }

                // last node
                if (iter.next != null || iter.val >= cur.val) {
                    prev.next = new ListNode(cur.val);
                    prev.next.next = iter;
                    iter = prev.next;
                }
                else {
                    iter.next = new ListNode(cur.val);
                    iter = iter.next;
                    prev = prev.next;
                }

                cur = cur.next;
            }
        }

        return dummy.next;
    }
}

