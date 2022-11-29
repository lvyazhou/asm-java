package asm.platform.repository.id;

import org.springframework.stereotype.Component;

/**
 * 其原理结构如下，分别用一个"0"表示一位，用"—"分割开部分的作用：
 * 0---0000000000 0000000000 0000000000 0000000000 0 --- 00000 ---00000 ---0000000000 00
 * 在上面的字符串中，第一位为未使用（实际上也可作为long的符号位），接下来的41位为毫秒级时间，然后5位datacenter标识位，5位机器ID（并不算标识符，实际是为线程标识），
 * 然后12位该毫秒内的当前毫秒内的计数，加起来刚好64位，为一个Long型。这样的好处是，整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞（由datacenter和机器ID
 * 作区分），并且效率较高，经测试，snowflake每秒能够产生26万ID左右，完全满足需要。
 *
 * @author liuli
 */
@Component
public class IdGeneratorWorker {
    private long workerId =1;
    private final static long twepoch = 1361753741828L;  //生成id的初始毫秒数[参照值]，对应时间：Mon, 25 Feb 2013 00:55:41 GMT
    private long sequence = 0L;
    //	private final static long workerIdBits = 4L;  //旧的workId使用4位，值为0~15
    private final static long workerIdBits = 9L; //新的workId使用9位，值为0~511
    public final static long maxWorkerId = -1L ^ -1L << workerIdBits;
    //	private final static long sequenceBits = 10L; //旧的序号，每毫秒能生成10位,值为0~1023
    private final static long sequenceBits = 10L; //新的序号，每毫秒能生成12位,值为0~1023
    private final static long workerIdShift = sequenceBits;
    private final static long timestampLeftShift = sequenceBits + workerIdBits;
    public final static long sequenceMask = -1L ^ -1L << sequenceBits;
    private long lastTimestamp = -1L;


    public synchronized long nextId() {
        long timestamp = this.timeGen();
        if (this.lastTimestamp == timestamp) {
            this.sequence = (this.sequence + 1) & this.sequenceMask;
            if (this.sequence == 0) {
                System.out.println("###########" + sequenceMask);
                timestamp = this.tilNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = 0;
        }
        if (timestamp < this.lastTimestamp) {
            try {
                throw new Exception(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", this.lastTimestamp - timestamp));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.lastTimestamp = timestamp;
        long nextId = ((timestamp - twepoch << timestampLeftShift)) | (this.workerId << this.workerIdShift) | (this.sequence);
        return nextId;
    }

    private long tilNextMillis(final long lastTimestamp) {
        long timestamp = this.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }


}
