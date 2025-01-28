package gis.insight.e2e.global.methods;

import java.util.Iterator;
import java.util.Map;

import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;
import org.testng.collections.Maps;

public class NumberedSoftAssert extends SoftAssert {
    private final Map<AssertionError, IAssert<?>> m_errors = Maps.newLinkedHashMap();
    private static final String DEFAULT_SOFT_ASSERT_MESSAGE = "The following asserts failed:";
    private int assertionCounter = 1; // Assertion counter

    @Override
    protected void doAssert(IAssert<?> a) {
        this.onBeforeAssert(a);

        try {
            a.doAssert();
            this.onAssertSuccess(a);
            System.out.println("Assertion #" + assertionCounter + " passed."); // Log successful assertion
        } catch (AssertionError ex) {
            AssertionError numberedError = new AssertionError("Assertion #" + assertionCounter + ": " + ex.getMessage());
            this.onAssertFailure(a, numberedError);
            this.m_errors.put(numberedError, a);
        } finally {
            this.onAfterAssert(a);
            assertionCounter++; // Increment the counter
        }
    }

    @Override
    public void assertAll(String message) {
        if (!this.m_errors.isEmpty()) {
            StringBuilder sb = new StringBuilder(null == message ? "The following asserts failed:" : message);
            boolean first = true;
            Iterator<AssertionError> it = this.m_errors.keySet().iterator();

            while (it.hasNext()) {
                AssertionError error = it.next();
                if (first) {
                    first = false;
                } else {
                    sb.append(",");
                }

                sb.append("\n\t");
                sb.append(this.getErrorDetails(error));
            }

            throw new AssertionError(sb.toString());
        }
    }
}
