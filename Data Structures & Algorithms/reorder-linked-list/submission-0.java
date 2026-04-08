/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {
    public void reorderList(ListNode head) 
    {
        if (head == null || head.next == null) 
        return;

        //Slow and fast pointer approach
        ListNode slow = head;
        ListNode fast = head;

        while(fast.next!=null&&fast.next.next!=null)
        {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode second = slow.next;
        slow.next = null;

        //Reversing 2nd half
        ListNode prev = null;    
        while(second!=null)
        {
            ListNode next = second.next;
            second.next = prev;
            prev = second;
            second = next;
        }

        //Merging lists
        ListNode first = head;
        ListNode secondHalf = prev;
        
        while (secondHalf != null)
        {
            ListNode temp1 = first.next;
            ListNode temp2 = secondHalf.next;

            first.next = secondHalf;
            secondHalf.next = temp1;

            first = temp1;
            secondHalf = temp2;
        }
    }
}
