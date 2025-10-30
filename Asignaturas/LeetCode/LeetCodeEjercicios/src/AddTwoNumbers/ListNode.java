package AddTwoNumbers;

/**
 * Definition for singly-linked list. **/

 public class ListNode {
    int val;
    ListNode next;

    ListNode() {

    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
 }

class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

       ListNode resultado = new ListNode();
       ListNode actual = resultado;
       int acarreo = 0;

       while(l1!=null || l2!=null || acarreo!=0){
           int suma = acarreo;

           if(l1!=null){
               suma+=l1.val;
               l1 = l1.next;
           }

           if(l2!=null){
               suma+=l2.val;
               l2 = l2.next;
           }

           acarreo = suma / 10;

           actual.next = new ListNode(suma%10);
           actual = actual.next;
       }

       return resultado.next;


    }


}