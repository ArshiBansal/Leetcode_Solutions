import java.util.HashSet;
import java.util.Set;

class Solution {
    public ListNode modifiedList(int[] nums, ListNode head) {
        // Put all nums into a set for O(1) lookup
        Set<Integer> removeSet = new HashSet<>();
        for (int num : nums) {
            removeSet.add(num);
        }

        // Dummy node to simplify removal logic
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;
        ListNode curr = head;

        // Traverse the list
        while (curr != null) {
            if (removeSet.contains(curr.val)) {
                // Skip current node
                prev.next = curr.next;
            } else {
                // Move prev only if current node is not removed
                prev = curr;
            }
            curr = curr.next;
        }

        // Return new head
        return dummy.next;
    }
}
