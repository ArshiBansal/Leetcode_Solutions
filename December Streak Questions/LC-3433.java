import java.util.*;
class Solution {
    public int[] countMentions(int numberOfUsers, List<List<String>> events) {
        int[] mentions = new int[numberOfUsers];

        boolean[] online = new boolean[numberOfUsers];
        Arrays.fill(online, true);

        int[] returnTime = new int[numberOfUsers];
        Arrays.fill(returnTime, -1);

        // Convert List<List<String>> to array for easy sorting
        List<List<String>> ev = new ArrayList<>(events);

        ev.sort((a, b) -> Integer.parseInt(a.get(1)) - Integer.parseInt(b.get(1)));

        int i = 0;
        int n = ev.size();

        while (i < n) {
            int currentTime = Integer.parseInt(ev.get(i).get(1));

            // Process auto-online updates
            for (int u = 0; u < numberOfUsers; u++) {
                if (!online[u] && returnTime[u] != -1 && returnTime[u] <= currentTime) {
                    online[u] = true;
                    returnTime[u] = -1;
                }
            }

            // First pass: OFFLINE events at this timestamp
            int j = i;
            while (j < n && Integer.parseInt(ev.get(j).get(1)) == currentTime) {
                if (ev.get(j).get(0).equals("OFFLINE")) {
                    int user = Integer.parseInt(ev.get(j).get(2));
                    online[user] = false;
                    returnTime[user] = currentTime + 60;
                }
                j++;
            }

            // Second pass: MESSAGE events
            j = i;
            while (j < n && Integer.parseInt(ev.get(j).get(1)) == currentTime) {
                if (ev.get(j).get(0).equals("MESSAGE")) {
                    processMessage(ev.get(j).get(2), online, mentions);
                }
                j++;
            }

            i = j;
        }

        return mentions;
    }

    private void processMessage(String msg, boolean[] online, int[] mentions) {
        if (msg.equals("ALL")) {
            for (int i = 0; i < mentions.length; i++) {
                mentions[i]++;
            }
            return;
        }

        if (msg.equals("HERE")) {
            for (int i = 0; i < mentions.length; i++) {
                if (online[i]) mentions[i]++;
            }
            return;
        }

        // Handle idX tokens
        String[] parts = msg.split(" ");
        for (String p : parts) {
            if (p.startsWith("id")) {
                int user = Integer.parseInt(p.substring(2));
                mentions[user]++;
            }
        }
    }
}
