public class FirstFailingVersion {

    public static long firstBadVersion(long n, IsFailingVersion isBadVersion) {
        return versionCheck(0, n, isBadVersion);
    }

    public static long versionCheck(long start, long end, IsFailingVersion iBV) {
        long cur = start + end / 2;
        if (iBV.isFailingVersion(cur) && !iBV.isFailingVersion(cur-1))
            return cur;
        if (iBV.isFailingVersion(cur))
            return versionCheck(start, end/2, iBV);
        if (!iBV.isFailingVersion(cur))
            return versionCheck(cur, end, iBV);
        return -1;
    }
}
