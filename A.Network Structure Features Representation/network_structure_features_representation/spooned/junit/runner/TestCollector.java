package junit.runner;
/**
 * Collects Test class names to be presented
 * by the TestSelector.
 *
 * @see TestSelector
 */
public interface TestCollector {
    /**
     * Returns an enumeration of Strings with qualified class names
     */
    public java.util.Enumeration collectTests();
}