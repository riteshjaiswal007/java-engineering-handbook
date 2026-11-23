package leetcode.binarysearch;

public class BinarySearchTest {
    // 2,4,6,7  ,8,9,23,34
    public int binarySearch(int[] arr,int start,int end,int target){

        if (start > end) {
            return -1; // Base case: element not found
        }

        int mid = start + (end - start) / 2; // Prevents integer overflow


        if(target==arr[mid]){
            return mid;
        }
        else if(target < arr[mid]){
            end =mid-1;
        }else{
            start=mid+1;
        }
        return binarySearch(arr,start,end,target);
    }
}
