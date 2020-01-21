package Model.Dict;

import java.util.List;

public class SemaphoreTableValue {
    int name;
    List<Integer> threads;
    int taken;

    public SemaphoreTableValue(){}

    public int getName() {
        return name;
    }

    public List<Integer> getThreads() {
        return threads;
    }

    public int getTaken() {
        return taken;
    }

    public void setName(int name) {
        this.name = name;
    }

    public void setThreads(List<Integer> threads) {
        this.threads = threads;
    }

    public void setTaken(int taken) {
        this.taken = taken;
    }
}
