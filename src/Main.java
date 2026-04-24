import java.lang.reflect.Array;
import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        int[] arr = {1,2,4,3,6,5};

        mergeSort(arr,0, arr.length-1);

        System.out.print(Arrays.toString(arr));

    }

    public static void mergeSort(int[] arr,int start,int end){
        if(start<end){

            int mid = start + (end-start)/2;

            mergeSort(arr,start,mid);
            mergeSort(arr,mid+1,end);
            merge(arr,start,mid,end);

        }
    }

    public static void merge(int[] arr,int start,int mid,int end){
            int[] temp = new int[end - start+1];
            int i = start;
            int j = mid+1;
            int k = 0;

            while(i<=mid && j<=end){
                if (arr[i] <= arr[j]) {
                    temp[k++] = arr[i++];
                } else {
                    temp[k++] = arr[j++];
                }
            }

            while (i<=mid){temp[k++] = arr[i++];}
        while (j<=end){temp[k++] = arr[j++];}

        for (int m = 0;m< temp.length;m++){
            arr[start+m] = temp[m];
        }
    }
}