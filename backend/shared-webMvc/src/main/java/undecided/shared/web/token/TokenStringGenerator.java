package undecided.shared.web.token;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.concurrent.atomic.AtomicLong;

public class TokenStringGenerator {

    public static final String DEFAULT_ALGORITHM = "MD5";

    private final AtomicLong counter = new AtomicLong();

    private final String internalSeed;

    private final String algorithm;

    public TokenStringGenerator() {
        this(DEFAULT_ALGORITHM);
    }

    public TokenStringGenerator(final String algorithm) {
        if (algorithm == null) {
            throw new IllegalArgumentException("algorithm must not be null");
        }
        try {
            MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("The given algorithm is invalid. algorithm="
                    + algorithm, e);
        }
        this.algorithm = algorithm;
        this.internalSeed = Long.toHexString(new SecureRandom().nextLong());
    }

    public String generate(final String seed) {
        if (seed == null) {
            throw new IllegalArgumentException("seed must not be null");
        }
        long time = System.currentTimeMillis();

        StringBuilder sb = new StringBuilder(1000);
        sb.append(internalSeed).append(seed).append(time).append(counter
                .getAndIncrement());

        MessageDigest md = createMessageDigest();
        md.update(sb.toString().getBytes());
        byte[] bytes = md.digest();
        return toHexString(bytes);
    }

    MessageDigest createMessageDigest() {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    protected static String toHexString(final byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            String s = Integer.toHexString(b & 0xff);
            if (s.length() == 1) {
                sb.append("0");
            }
            sb.append(s);
        }
        return sb.toString();
    }
}
