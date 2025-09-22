import java.util.*;

class TaskManager {

    private static class Task {
        int userId, taskId, priority;
        Task(int userId, int taskId, int priority) {
            this.userId = userId;
            this.taskId = taskId;
            this.priority = priority;
        }
    }

    private PriorityQueue<Task> pq;
    private Map<Integer, int[]> taskMap; // taskId -> [userId, priority]

    public TaskManager(List<List<Integer>> tasks) {
        pq = new PriorityQueue<>((a, b) -> {
            if (a.priority != b.priority) return Integer.compare(b.priority, a.priority);
            return Integer.compare(b.taskId, a.taskId);
        });
        taskMap = new HashMap<>();
        for (List<Integer> t : tasks) {
            int userId = t.get(0), taskId = t.get(1), priority = t.get(2);
            taskMap.put(taskId, new int[]{userId, priority});
            pq.offer(new Task(userId, taskId, priority));
        }
    }
    
    public void add(int userId, int taskId, int priority) {
        taskMap.put(taskId, new int[]{userId, priority});
        pq.offer(new Task(userId, taskId, priority));
    }
    
    public void edit(int taskId, int newPriority) {
        int[] data = taskMap.get(taskId);
        if (data == null) return;
        data[1] = newPriority;
        taskMap.put(taskId, data);
        pq.offer(new Task(data[0], taskId, newPriority));
    }
    
    public void rmv(int taskId) {
        taskMap.remove(taskId);
    }
    
    public int execTop() {
        while (!pq.isEmpty()) {
            Task top = pq.poll();
            int[] data = taskMap.get(top.taskId);
            if (data != null && data[0] == top.userId && data[1] == top.priority) {
                taskMap.remove(top.taskId);
                return top.userId;
            }
            // skip stale entry
        }
        return -1;
    }
}
