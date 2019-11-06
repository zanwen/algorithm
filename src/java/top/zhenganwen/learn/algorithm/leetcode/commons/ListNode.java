package top.zhenganwen.learn.algorithm.leetcode.commons;

/**
 * @author zhenganwen
 */
public class ListNode {

    public int      val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public static ListNode SAMPLE_LIST;

    static {
        SAMPLE_LIST = new ListNode(1);
        SAMPLE_LIST.next = new ListNode(2);
        SAMPLE_LIST.next.next = new ListNode(3);
        SAMPLE_LIST.next.next.next = new ListNode(4);
        SAMPLE_LIST.next.next.next.next = new ListNode(5);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(val));
        ListNode tmp = next;
        while (tmp != null) {
            stringBuilder.append(" -> ").append(tmp.val);
            tmp = tmp.next;
        }
        return stringBuilder.toString();
    }
}
