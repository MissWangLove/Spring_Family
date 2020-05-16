package com.wangzhi.jdbc.springjdbc;

import com.sun.jmx.remote.internal.ArrayQueue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;
import java.util.*;
import java.util.List;

@SpringBootTest
class SpringJdbcApplicationTests {

    private static List<Integer> queue = new ArrayList<>(5);

    @Test
    void contextLoads() {
    }

    public static void main(String[] args) throws InterruptedException {
        SpringJdbcApplicationTests springJdbcApplicationTests = new SpringJdbcApplicationTests();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                springJdbcApplicationTests.startTask(finalI);
            }).start();
        }
    }

    private void startTask(Integer i) {
        try {
            System.out.println(Thread.currentThread().getName() + " 加入任务" + i);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(queue.size() >= 5) {
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + " 任务量已满，等待中" + i);
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        queue.add(i);
        System.out.println(Thread.currentThread().getName() + " 任务开始" + i);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        free(i);

    }

    synchronized void free(Integer i) {
        Iterator<Integer> iterator = queue.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if(next.equals(i)) {
                iterator.remove();
            }
        }
        System.out.println(Thread.currentThread().getName() + " 任务完成" + i);
        notifyAll();
    }

}

