class Solution {
    public int findDuplicate(int[] nums) 
    {
        int slow = nums[0];
        int fast = nums[nums[0]]; 

        //find intersection
        while (slow != fast)
        {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        //find entrance
        int ptr = 0; 
        while (ptr != slow)
        {
            ptr = nums[ptr];
            slow = nums[slow];
        }

        return ptr;
    }
}
