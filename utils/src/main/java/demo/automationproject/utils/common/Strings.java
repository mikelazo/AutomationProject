package demo.automationproject.utils.common;

public class Strings {
    // Prevent construction of this class.
    private Strings() {
    }

    /**
     * Determines whether or not the given String is null or empty (length == 0).
     *
     * @param s The String to check
     * @return Returns true when the String is null or empty. Otherwise returns false.
     */
    public static boolean IsNullOrEmpty(String s) {
        return (s == null || s.isEmpty()) ? true : false;
    }
}
